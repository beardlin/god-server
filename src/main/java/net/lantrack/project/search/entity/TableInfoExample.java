package net.lantrack.project.search.entity;

import java.util.ArrayList;
import java.util.List;

public class TableInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TableInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andZhNameIsNull() {
            addCriterion("zh_name is null");
            return (Criteria) this;
        }

        public Criteria andZhNameIsNotNull() {
            addCriterion("zh_name is not null");
            return (Criteria) this;
        }

        public Criteria andZhNameEqualTo(String value) {
            addCriterion("zh_name =", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotEqualTo(String value) {
            addCriterion("zh_name <>", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameGreaterThan(String value) {
            addCriterion("zh_name >", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameGreaterThanOrEqualTo(String value) {
            addCriterion("zh_name >=", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLessThan(String value) {
            addCriterion("zh_name <", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLessThanOrEqualTo(String value) {
            addCriterion("zh_name <=", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameLike(String value) {
            addCriterion("zh_name like", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotLike(String value) {
            addCriterion("zh_name not like", value, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameIn(List<String> values) {
            addCriterion("zh_name in", values, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotIn(List<String> values) {
            addCriterion("zh_name not in", values, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameBetween(String value1, String value2) {
            addCriterion("zh_name between", value1, value2, "zhName");
            return (Criteria) this;
        }

        public Criteria andZhNameNotBetween(String value1, String value2) {
            addCriterion("zh_name not between", value1, value2, "zhName");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNull() {
            addCriterion("full_name is null");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNotNull() {
            addCriterion("full_name is not null");
            return (Criteria) this;
        }

        public Criteria andFullNameEqualTo(String value) {
            addCriterion("full_name =", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotEqualTo(String value) {
            addCriterion("full_name <>", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThan(String value) {
            addCriterion("full_name >", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("full_name >=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThan(String value) {
            addCriterion("full_name <", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThanOrEqualTo(String value) {
            addCriterion("full_name <=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLike(String value) {
            addCriterion("full_name like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotLike(String value) {
            addCriterion("full_name not like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameIn(List<String> values) {
            addCriterion("full_name in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotIn(List<String> values) {
            addCriterion("full_name not in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameBetween(String value1, String value2) {
            addCriterion("full_name between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotBetween(String value1, String value2) {
            addCriterion("full_name not between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andTableRemarkIsNull() {
            addCriterion("table_remark is null");
            return (Criteria) this;
        }

        public Criteria andTableRemarkIsNotNull() {
            addCriterion("table_remark is not null");
            return (Criteria) this;
        }

        public Criteria andTableRemarkEqualTo(String value) {
            addCriterion("table_remark =", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkNotEqualTo(String value) {
            addCriterion("table_remark <>", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkGreaterThan(String value) {
            addCriterion("table_remark >", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("table_remark >=", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkLessThan(String value) {
            addCriterion("table_remark <", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkLessThanOrEqualTo(String value) {
            addCriterion("table_remark <=", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkLike(String value) {
            addCriterion("table_remark like", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkNotLike(String value) {
            addCriterion("table_remark not like", value, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkIn(List<String> values) {
            addCriterion("table_remark in", values, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkNotIn(List<String> values) {
            addCriterion("table_remark not in", values, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkBetween(String value1, String value2) {
            addCriterion("table_remark between", value1, value2, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andTableRemarkNotBetween(String value1, String value2) {
            addCriterion("table_remark not between", value1, value2, "tableRemark");
            return (Criteria) this;
        }

        public Criteria andColumnNumIsNull() {
            addCriterion("column_num is null");
            return (Criteria) this;
        }

        public Criteria andColumnNumIsNotNull() {
            addCriterion("column_num is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNumEqualTo(Integer value) {
            addCriterion("column_num =", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumNotEqualTo(Integer value) {
            addCriterion("column_num <>", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumGreaterThan(Integer value) {
            addCriterion("column_num >", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_num >=", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumLessThan(Integer value) {
            addCriterion("column_num <", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumLessThanOrEqualTo(Integer value) {
            addCriterion("column_num <=", value, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumIn(List<Integer> values) {
            addCriterion("column_num in", values, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumNotIn(List<Integer> values) {
            addCriterion("column_num not in", values, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumBetween(Integer value1, Integer value2) {
            addCriterion("column_num between", value1, value2, "columnNum");
            return (Criteria) this;
        }

        public Criteria andColumnNumNotBetween(Integer value1, Integer value2) {
            addCriterion("column_num not between", value1, value2, "columnNum");
            return (Criteria) this;
        }

        public Criteria andTableEngineIsNull() {
            addCriterion("table_engine is null");
            return (Criteria) this;
        }

        public Criteria andTableEngineIsNotNull() {
            addCriterion("table_engine is not null");
            return (Criteria) this;
        }

        public Criteria andTableEngineEqualTo(String value) {
            addCriterion("table_engine =", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineNotEqualTo(String value) {
            addCriterion("table_engine <>", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineGreaterThan(String value) {
            addCriterion("table_engine >", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineGreaterThanOrEqualTo(String value) {
            addCriterion("table_engine >=", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineLessThan(String value) {
            addCriterion("table_engine <", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineLessThanOrEqualTo(String value) {
            addCriterion("table_engine <=", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineLike(String value) {
            addCriterion("table_engine like", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineNotLike(String value) {
            addCriterion("table_engine not like", value, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineIn(List<String> values) {
            addCriterion("table_engine in", values, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineNotIn(List<String> values) {
            addCriterion("table_engine not in", values, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineBetween(String value1, String value2) {
            addCriterion("table_engine between", value1, value2, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableEngineNotBetween(String value1, String value2) {
            addCriterion("table_engine not between", value1, value2, "tableEngine");
            return (Criteria) this;
        }

        public Criteria andTableGroupIsNull() {
            addCriterion("table_group is null");
            return (Criteria) this;
        }

        public Criteria andTableGroupIsNotNull() {
            addCriterion("table_group is not null");
            return (Criteria) this;
        }

        public Criteria andTableGroupEqualTo(String value) {
            addCriterion("table_group =", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupNotEqualTo(String value) {
            addCriterion("table_group <>", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupGreaterThan(String value) {
            addCriterion("table_group >", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupGreaterThanOrEqualTo(String value) {
            addCriterion("table_group >=", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupLessThan(String value) {
            addCriterion("table_group <", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupLessThanOrEqualTo(String value) {
            addCriterion("table_group <=", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupLike(String value) {
            addCriterion("table_group like", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupNotLike(String value) {
            addCriterion("table_group not like", value, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupIn(List<String> values) {
            addCriterion("table_group in", values, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupNotIn(List<String> values) {
            addCriterion("table_group not in", values, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupBetween(String value1, String value2) {
            addCriterion("table_group between", value1, value2, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andTableGroupNotBetween(String value1, String value2) {
            addCriterion("table_group not between", value1, value2, "tableGroup");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNull() {
            addCriterion("db_name is null");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNotNull() {
            addCriterion("db_name is not null");
            return (Criteria) this;
        }

        public Criteria andDbNameEqualTo(String value) {
            addCriterion("db_name =", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotEqualTo(String value) {
            addCriterion("db_name <>", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThan(String value) {
            addCriterion("db_name >", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThanOrEqualTo(String value) {
            addCriterion("db_name >=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThan(String value) {
            addCriterion("db_name <", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThanOrEqualTo(String value) {
            addCriterion("db_name <=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLike(String value) {
            addCriterion("db_name like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotLike(String value) {
            addCriterion("db_name not like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameIn(List<String> values) {
            addCriterion("db_name in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotIn(List<String> values) {
            addCriterion("db_name not in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameBetween(String value1, String value2) {
            addCriterion("db_name between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotBetween(String value1, String value2) {
            addCriterion("db_name not between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andPIdIsNull() {
            addCriterion("p_id is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("p_id is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(Integer value) {
            addCriterion("p_id =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(Integer value) {
            addCriterion("p_id <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(Integer value) {
            addCriterion("p_id >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_id >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(Integer value) {
            addCriterion("p_id <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(Integer value) {
            addCriterion("p_id <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<Integer> values) {
            addCriterion("p_id in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<Integer> values) {
            addCriterion("p_id not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(Integer value1, Integer value2) {
            addCriterion("p_id between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(Integer value1, Integer value2) {
            addCriterion("p_id not between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andStandby1IsNull() {
            addCriterion("standby1 is null");
            return (Criteria) this;
        }

        public Criteria andStandby1IsNotNull() {
            addCriterion("standby1 is not null");
            return (Criteria) this;
        }

        public Criteria andStandby1EqualTo(String value) {
            addCriterion("standby1 =", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1NotEqualTo(String value) {
            addCriterion("standby1 <>", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1GreaterThan(String value) {
            addCriterion("standby1 >", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1GreaterThanOrEqualTo(String value) {
            addCriterion("standby1 >=", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1LessThan(String value) {
            addCriterion("standby1 <", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1LessThanOrEqualTo(String value) {
            addCriterion("standby1 <=", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1Like(String value) {
            addCriterion("standby1 like", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1NotLike(String value) {
            addCriterion("standby1 not like", value, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1In(List<String> values) {
            addCriterion("standby1 in", values, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1NotIn(List<String> values) {
            addCriterion("standby1 not in", values, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1Between(String value1, String value2) {
            addCriterion("standby1 between", value1, value2, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby1NotBetween(String value1, String value2) {
            addCriterion("standby1 not between", value1, value2, "standby1");
            return (Criteria) this;
        }

        public Criteria andStandby2IsNull() {
            addCriterion("standby2 is null");
            return (Criteria) this;
        }

        public Criteria andStandby2IsNotNull() {
            addCriterion("standby2 is not null");
            return (Criteria) this;
        }

        public Criteria andStandby2EqualTo(String value) {
            addCriterion("standby2 =", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2NotEqualTo(String value) {
            addCriterion("standby2 <>", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2GreaterThan(String value) {
            addCriterion("standby2 >", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2GreaterThanOrEqualTo(String value) {
            addCriterion("standby2 >=", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2LessThan(String value) {
            addCriterion("standby2 <", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2LessThanOrEqualTo(String value) {
            addCriterion("standby2 <=", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2Like(String value) {
            addCriterion("standby2 like", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2NotLike(String value) {
            addCriterion("standby2 not like", value, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2In(List<String> values) {
            addCriterion("standby2 in", values, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2NotIn(List<String> values) {
            addCriterion("standby2 not in", values, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2Between(String value1, String value2) {
            addCriterion("standby2 between", value1, value2, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby2NotBetween(String value1, String value2) {
            addCriterion("standby2 not between", value1, value2, "standby2");
            return (Criteria) this;
        }

        public Criteria andStandby3IsNull() {
            addCriterion("standby3 is null");
            return (Criteria) this;
        }

        public Criteria andStandby3IsNotNull() {
            addCriterion("standby3 is not null");
            return (Criteria) this;
        }

        public Criteria andStandby3EqualTo(String value) {
            addCriterion("standby3 =", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3NotEqualTo(String value) {
            addCriterion("standby3 <>", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3GreaterThan(String value) {
            addCriterion("standby3 >", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3GreaterThanOrEqualTo(String value) {
            addCriterion("standby3 >=", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3LessThan(String value) {
            addCriterion("standby3 <", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3LessThanOrEqualTo(String value) {
            addCriterion("standby3 <=", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3Like(String value) {
            addCriterion("standby3 like", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3NotLike(String value) {
            addCriterion("standby3 not like", value, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3In(List<String> values) {
            addCriterion("standby3 in", values, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3NotIn(List<String> values) {
            addCriterion("standby3 not in", values, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3Between(String value1, String value2) {
            addCriterion("standby3 between", value1, value2, "standby3");
            return (Criteria) this;
        }

        public Criteria andStandby3NotBetween(String value1, String value2) {
            addCriterion("standby3 not between", value1, value2, "standby3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}