
package net.lantrack.framework.sysbase.util.page;

import net.lantrack.framework.core.entity.PageEntity;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {
        Connection.class
    })
})
public class PagePlugin implements Interceptor {

    private static String dialect = "";
    private static String pageSqlId = "";

    @SuppressWarnings("unchecked")
    public Object intercept(Invocation ivk) throws Throwable {

        if (ivk.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
                    .getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(
                    delegate, "mappedStatement");
            if (mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject error");
                } else {
                    Connection connection = (Connection) ivk.getArgs()[0];
                    String sql = boundSql.getSql();
                    String countSql = "select count(0) from (" + sql + ") myCount";
//                    System.out.println("总数sql 语句:" + countSql);
                    PreparedStatement countStmt = connection.prepareStatement(countSql);
                    BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                            boundSql.getParameterMappings(), parameterObject);
                    setParameters(countStmt, mappedStatement, countBS, parameterObject);
                    ResultSet rs = countStmt.executeQuery();
                    int count = 0;
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                    rs.close();
                    countStmt.close();
                    //此处用自己的分页信息
                    PageEntity page = null;
                    if (parameterObject instanceof PageEntity) {
                        page = (PageEntity) parameterObject;
                        page.setTotalCount(count);
                    } else if (parameterObject instanceof Map) {
                        Map<String, Object> map = (Map<String, Object>) parameterObject;
                        page = (PageEntity) map.get("page");
                        if (page == null)
                            page = new PageEntity();
                        page.setTotalCount(count);
                    } else {
                        Field pageField = ReflectHelper
                                .getFieldByFieldName(parameterObject, "page");
                        if (pageField != null) {
                            page = (PageEntity) ReflectHelper
                                    .getValueByFieldName(parameterObject, "page");
                            if (page == null)
                                page = new PageEntity();
                            page.setTotalCount(count);
                            ReflectHelper.setValueByFieldName(parameterObject, "page", page);
                        } else {
                            throw new NoSuchFieldException(parameterObject.getClass().getName());
                        }
                    }
                    /**
                     * TODO
                     * 统一在setTotalCount进行分页处理
                     * @author : lihuadong@lantrack.net
                     * @date : 2018/1/31 14:38
                     */
//                    if (count != 0L) {
//                        long pageNumber = count % (long) page.getPerPageCount() != 0L ? count
//                                / (long) page.getPerPageCount() + 1L : count / (long) page.getPerPageCount();
//                        if ((long) page.getCurrentPage() > pageNumber){
//                            page.setCurrentPage((int)pageNumber);
//                        }
//                        page.setTotalPage(pageNumber);
//                    }
                    String pageSql = generatePageSql(sql, page);
//                    System.out.println("page sql:" + pageSql);
                    ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }
        return ivk.proceed();
    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,
            BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters")
                .object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration
                    .newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
                            && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(
                                    propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter "
                                + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private String generatePageSql(String sql, PageEntity page) {
        if (page != null && (dialect != null || !dialect.equals(""))) {
            StringBuffer pageSql = new StringBuffer();
            long currentResult = (page.getCurrentPage()-1)*page.getPerPageCount();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                if(StringUtils.isNotBlank(page.getOrderField())){
                    pageSql.append(" order by ").append(page.getOrderField())
                           .append(" ").append(page.getOrderSort());
                }
                pageSql.append(" limit " + currentResult + "," + page.getPerPageCount());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                if(StringUtils.isNotBlank(page.getOrderField())){
                    pageSql.append(" order by ").append(page.getOrderField())
                           .append(" ").append(page.getOrderSort());
                }
                pageSql.append(")  tmp_tb where ROWNUM<=");
                pageSql.append(currentResult + page.getPerPageCount());
                pageSql.append(") where row_id>");
                pageSql.append(currentResult);
            }
            
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties p) {
        dialect = p.getProperty("dialect");
        if (dialect == null || dialect.equals("")) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        pageSqlId = p.getProperty("pageSqlId");
        if (dialect == null || dialect.equals("")) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
    }

}
