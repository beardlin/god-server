package net.lantrack.project.search.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.project.search.util.Article;
import net.lantrack.project.search.util.ESTest;
import net.lantrack.project.search.util.ESUtils;

@Controller
@RequestMapping("fulltext")
public class FullTextSearchController {

	
//	fulltext/keyword.json
//	关键字搜索
	@RequestMapping("/keyword")
	public String getTextByKeyWord(String key,ReturnEntity info) {
		Map<String, Object> search = ESUtils.search(ESTest.index, "title,content",4, 
				Article.class,ESTest.type,key,new String[] {"title", "content","autor"});
		List<Article> articles = new ArrayList<>(search.size());
		for(Map.Entry<String, Object> entry:search.entrySet()) {
			Article value = (Article) entry.getValue();
			value.setId(entry.getKey());
			articles.add(value);
		}
		info.setResult(articles);
		return "";
	}
}
