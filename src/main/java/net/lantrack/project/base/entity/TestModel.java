package net.lantrack.project.base.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TestModel {

	private String name;
	private String like;
	
	
	@Length(min=0,max=6,message="姓名不准超过6个字符")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@Length(min=0,max=6,message="爱好不能为空")
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	
	
	
}
