package com.jiangfw.common.lang.test.json.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class TestBean {

	private String id;
	private String name;
	private java.util.Date birthday;

	public TestBean() {
		super();
	}

	public TestBean(String id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
