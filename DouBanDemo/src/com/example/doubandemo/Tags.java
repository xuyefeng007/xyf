package com.example.doubandemo;

public class Tags{
	private String count;
	private String name;
	private String title;
	
	public Tags(String count,String name,String title){
		this.count=count;
		this.name=name;
		this.title=title;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}