package net.lantrack.project.search.util;

import java.util.Map;


public class ESTest {

	public static String index="myindex";
	
	public static String type="article";
	
	public static void main(String[] args) {
//		插入
//		Article article = new Article("世界杯冠军产生", "2018-07-21", "大花猫", "体育", 80);
//		article.setContent("你能想象会有如今的高度规模化和商业化的互联网吗？多少个Google、Facebook还不都给罚破产了。我甚至觉得，欧盟可能贴罚单贴上瘾了。虽然欧盟没什么像样的互联网公司，但在连篇累牍的罚单中，欧盟还是贴出了不一样的存在感和自信心。");
//		String json = GsonUtil.toJson(article);
//		ESUtils.insert(json, index, type, "3");
//		主键查询
//		String  json = ESUtils.getById(index, type, "1");
//		Article article = GsonUtil.toObject(json, Article.class);
//		System.out.println(article);
//		id删除
//		Object deleteById = ESUtils.deleteById(index, type,"1");
//		条件删除
//		Map<String, Object> cond = new HashMap<>();
//		cond.put("autor", "大胡子");
//		long condition = ESUtils.deleteByCondition(cond, index);
//		System.out.println(condition);
//		修改
//		Map<String, Object> source = new HashMap<>();
//		source.put("content", "根据媒体报道，长生生物暴利超茅台，100元疫苗，92元是利润！我是真的不明白，这些人钱都挣了那么多，为什么还要做恶？\r\n" + 
//				"\r\n" + 
//				"直到这件事愈演愈烈，长生生物董事长高俊芳儿媳妇隋嘉的奢靡被曝光后我才明白：她们永远要赚更多的钱！她们再有钱也成为不了贵族！");
//		source.put("title", "长生生物");
//		ESUtils.update(source, index, type, "2");
//		ids查询
//		List<String> list = ESUtils.queryByIds(index, type, "1","2","3");
//		for(String art:list) {
//			System.out.println(art);
//		}
//		查询
		Map<String, Object> search = ESUtils.search(index, "title,content",4, 
				Article.class,type,"事件冠军疫苗",new String[] {"title", "content","autor"});
		System.out.println(search.size());
//		ESUtils.searchListData(index, type, 10, "title,autor,content", "autor", true, "content", 
//				"content=疫苗");
		
	}
}
