package net.lantrack.project.search.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import net.lantrack.framework.core.util.GsonUtil;

/**
 * elastic search 工具类 参考文档
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
 * https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html
 * ES vs Mysql 存储过程中各名词的对比 Index DataBase 数据库 Type Table 表 Document Row 字段 Field
 * Column 字段
 * 
 * @Description:
 * @author lin
 * @date 2018年7月20日
 */
public class ESUtils {
//	private static Logger logger = LoggerFactory.getLogger(ESUtils.class);
	private static Logger logger = LogManager.getLogger("mylog");
	
	private static TransportClient client;
	/**
	 * 多个host用 ',' 分隔
	 */
	public static String hostNames;
	/**
	 * 集群名称
	 */
	public static String clusterName;
	/**
	 * 是否忽略集群名称
	 */
	public static boolean ignoreClusterName;
	/**
	 * 是否开启自动探测
	 */
	public static boolean clientTransportSniff = true;
	/**
	 * 连接等待时长 默认为5s
	 */
	public static int clientTransportPingTimeout = 5;
	/**
	 * 检测各节点连接间隔时间 默认为5s
	 */
	public static int nodesSamplerInterval = 5;

	static {
		InputStream inputStream = ESUtils.class.getClassLoader()
				.getResourceAsStream("properties/elasticsearch.properties");
		Properties props = new Properties();
		try {
			props.load(inputStream);
			if (props.containsKey("es.host")) {
				hostNames = props.getProperty("es.host");
			}
			getClient();
			System.err.println(client.listedNodes());
		} catch (IOException e) {
			e.printStackTrace();
//			if (logger.isErrorEnabled()) {
//				logger.error("配置文件加载失败：" + e.getMessage());
//			}
		}

	}

	private ESUtils() {

	}

	@SuppressWarnings("resource")
	public static TransportClient getClient() throws UnknownHostException {
		// 带参数的设置
		// 设置集群名称
		// Settings settings = Settings.builder()
		// .put("cluster.name", "myClusterName").build();
		// 开启自动检测节点
		// Settings settings = Settings.builder()
		// .put("client.transport.sniff", true).build();
		// TransportClient client = new PreBuiltTransportClient(settings);
		//
		// 其他参数
		/**
		 * Set to true to ignore cluster name validation of connected nodes. (since
		 * 0.19.4)
		 */
		// client.transport.ignore_cluster_name
		/**
		 * The time to wait for a ping response from a node. Defaults to 5s.
		 */

		// client.transport.ping_timeout

		/**
		 * How often to sample / ping the nodes listed and connected. Defaults to 5s.
		 */
		// client.transport.nodes_sampler_interval

		// Add transport addresses and do something with the client...
		// 连接
		// on startup
		if (client == null) {
			String[] hosts = hostNames.split(",");
			TransportAddress[] address = new TransportAddress[hosts.length];
			for (int i = 0; i < hosts.length; i++) {
				String[] host = hosts[i].split(":");
				address[i] = new TransportAddress(InetAddress.getByName(host[0]), Integer.valueOf(host[1]));
			}
			client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddresses(address);
			// .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"),
			// 9300))
			// .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"),
			// 9300));
		}
		// on shutdown
		// client.close();
		return client;
	}

	/**
	 * 数据插入
	 * 
	 * @param json(document)
	 *            文本对象 Bean
	 * @param index
	 *            库名 DataBase
	 * @param type
	 *            表名 table
	 * @param id
	 *            唯一标识 id(如为null可自己生成)
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static String insert(String json, String index, String type, String id) {
		// String json = "{" +
		// "\"user\":\"kimchy\"," +
		// "\"postDate\":\"2013-01-30\"," +
		// "\"message\":\"trying out Elasticsearch\"" +
		// "}";
		IndexResponse response;
		if (id != null && !"".equals(id.trim())) {
			response = client.prepareIndex(index, type, id).setSource(json, XContentType.JSON).get();
		} else {
			response = client.prepareIndex(index, type).setSource(json, XContentType.JSON).get();
		}
		// Index name
		String _index = response.getIndex();
		// Type name
		String _type = response.getType();
		// Document ID (generated or not)
		String _id = response.getId();
		// Version (if it's the first time you index this document, you will get: 1)
		long _version = response.getVersion();
		// status has stored current instance statement.
		RestStatus status = response.status();
		System.out.println(
				"index:" + _index + ",type:" + _type + ",id:" + _id + ",version:" + _version + ",status:" + status);
		return _id;
	}

	/**
	 * 主键查询
	 * 
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static String getById(String index, String type, String id) {
		GetResponse response = client.prepareGet(index, type, id).get();
		if (response.isExists()) {
			String source = response.getSourceAsString();
			System.out.println(source);
			return source;
		}
		return null;
	}

	/**
	 * 数据删除
	 * 
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static Object deleteById(String index, String type, String id) {
		DeleteResponse response = client.prepareDelete(index, type, id).get();
		RestStatus status = response.status();
		System.out.println(status);
		return "";
	}

	/**
	 * 条件删除
	 * 
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static long deleteByCondition(Map<String, Object> conditions, String index) {
		if (conditions == null || conditions.size() == 0) {
			return 0L;
		}
		// BulkByScrollResponse response =
		// DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
		// .filter(QueryBuilders.matchQuery("gender", "male"))
		// .filter(QueryBuilders.matchQuery("gender", "male"))
		// .source("persons")
		// .get();
		DeleteByQueryRequestBuilder builder = DeleteByQueryAction.INSTANCE.newRequestBuilder(client);
		for (Map.Entry<String, Object> entry : conditions.entrySet()) {
			builder.filter(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		BulkByScrollResponse response = builder.source(index).get();
		return response.getDeleted();
	}

	/**
	 * 更新数据
	 * 
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static Object update(Map<String, Object> source, String index, String type, String id) {
		// UpdateRequest updateRequest = new UpdateRequest("index", "type", "1")
		// .doc(jsonBuilder().startObject()
		// .field("gender", "male")
		// .endObject());
		UpdateRequest updateRequest = new UpdateRequest(index, type, id);
		updateRequest.doc(source);
		try {
			UpdateResponse response = client.update(updateRequest).get();
			return response.status().getStatus();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据多个id查询
	 * 
	 * @author lin
	 * @date 2018年7月23日
	 */
	public static List<String> queryByIds(String index, String type, String... id) {
		// MultiGetResponse multiGetItemResponses =
		// client.prepareMultiGet().add("twitter", "tweet", "1")
		// .add("twitter", "tweet", "2", "3", "4").add("another", "type", "foo").get();
		MultiGetResponse multiGetItemResponses = client.prepareMultiGet().add(index, type, id).get();
		List<String> list = new ArrayList<String>();
		for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
			GetResponse response = itemResponse.getResponse();
			if (response.isExists()) {
				String json = response.getSourceAsString();
				list.add(json);
			}
		}
		return list;
	}

	/**
	 * 全文检索
	 * 
	 * @author lin
	 * @date 2018年7月24日
	 */
	public static Map<String, Object> search(String index, String highlightFields,
			int size,Class<?> clazz, String type,String keyword,String[] searchField) {
		// QueryBuilder qb = termQuery("multi", "test");
//		MatchQueryBuilder qb = QueryBuilders.matchQuery("title", "事件");
		MultiMatchQueryBuilder qb = QueryBuilders.multiMatchQuery(keyword,searchField);
//		SearchResponse scrollResp = client.prepareSearch(index).addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
//				.setScroll(new TimeValue(60000)).setQuery(qb).setSize(size).get(); // max of 100 hits will be returned
		SearchRequestBuilder prepareSearch = client.prepareSearch(index);											// for each scroll
		if(StringUtils.isNotBlank(type)) {
			prepareSearch.setTypes(type.split(","));
		}
		logger.info("highlightFields==[{}],keyword==[{}]", highlightFields,keyword);
//		高亮显示
//		// 设置高亮字段
		if(highlightFields!=null) {
			HighlightBuilder highlightBuilder = new HighlightBuilder();
//			highlightBuilder.tagsSchema("default");
			highlightBuilder.preTags("<span style='color:red;font-size: 18px' >");//设置前缀
			highlightBuilder.postTags("</span>");//设置后缀
			String[] lightfields = highlightFields.split(",");
			for(String field:lightfields) {
				highlightBuilder.field(field);
			}
			prepareSearch.highlighter(highlightBuilder);
		}
		SearchResponse scrollResp = prepareSearch.addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
		.setScroll(new TimeValue(60000)).setQuery(qb).setSize(size).get();
		// Scroll until no hits are returned
		Map<String, Object> resultMap = new HashMap<>(scrollResp.getHits().getHits().length);
		for (SearchHit hit : scrollResp.getHits().getHits()) {
			// Handle the hit...
			Object bean = GsonUtil.toObject(hit.getSourceAsString(), clazz);
			resultMap.put(hit.getId(), bean);
			Map<String, HighlightField> highLightMap = hit.getHighlightFields();
            for(Map.Entry<String, HighlightField> entry:highLightMap.entrySet()) {
            	String field = entry.getKey();
            	Text[] texts = entry.getValue().getFragments();
            	StringBuffer sbf = new StringBuffer();
            	for(Text text:texts) {
            		sbf.append(text);
            	}
            	try {
					java.lang.reflect.Field declaredField = bean.getClass().getDeclaredField(field);
					declaredField.setAccessible(true);
					declaredField.set(bean, sbf.toString());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			
		}
																// loop.
		return resultMap;
	}

	

}
