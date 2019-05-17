package net.lantrack.project.search.util;

public class Article {
	private String id;
	private String title;
	private String date;
	private String autor;
	private String content;
	private String titleType;
	private Integer lookRecord;
	
	
	public Article(String title, String date, String autor, String titleType, Integer lookRecord) {
		super();
		this.title = title;
		this.date = date;
		this.autor = autor;
		this.titleType = titleType;
		this.lookRecord = lookRecord;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitleType() {
		return titleType;
	}
	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}
	public Integer getLookRecord() {
		return lookRecord;
	}
	public void setLookRecord(Integer lookRecord) {
		this.lookRecord = lookRecord;
	}


	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", date=" + date + ", autor=" + autor + ", content=" + content
				+ ", titleType=" + titleType + ", lookRecord=" + lookRecord + "]";
	}
	
	

}
