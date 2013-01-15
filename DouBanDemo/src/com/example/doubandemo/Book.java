package com.example.doubandemo;

import java.util.ArrayList;
import java.util.List;

public class Book{
	//�Ǽ�
	private Rating rating;
	
	//С����
	private String subtitle;
	//��������
	private String pubdate;
	//ͼƬ
	private String image;
	//ִ��
	private String bingding;
	
	//ͼƬ��
	private Images images;
	
	private String alt;
	//ID
	private String id;
	//����
	private String title;
	//���߽���
	private String author_intro;
	
	//�ؼ��ֱ�ǩ
	private List<Tags> list_tags;
	
	private String origin_title;
	//�۸�
	private String price;
	//������
	private String translator;
	//ҳ��
	private String pages;
	//������
	private String publisher;
	//������1
	private String isbn10;
	//������2
	private String isbn13;
	private String alt_title;
	//·��
	private String url;
	//����
	private String author;
	//ժҪ
	private String summary;
	
	public Book(){
		list_tags=new ArrayList<Tags>();
	}
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBingding() {
		return bingding;
	}

	public void setBingding(String bingding) {
		this.bingding = bingding;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
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

	public String getAuthor_intro() {
		return author_intro;
	}

	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}

	public List<Tags> getTags() {
		return list_tags;
	}

	public void setTags(Tags tags){
		list_tags.add(tags);
	}

	public String getOrigin_titel() {
		return origin_title;
	}

	public void setOrigin_title(String origin_title) {
		this.origin_title = origin_title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getAlt_title() {
		return alt_title;
	}

	public void setAlt_title(String alt_title) {
		this.alt_title = alt_title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}