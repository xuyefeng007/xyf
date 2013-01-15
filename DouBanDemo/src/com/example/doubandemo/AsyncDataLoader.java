package com.example.doubandemo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

public class AsyncDataLoader{
	private Handler handler;
	//http资源url
	private String url="https://api.douban.com/v2/book/";
	//请求图书ID
	private String id="";
	//请求的结果
	private String resultJson="";
	private int ms=1;
	
	//构造函数---列表页数据获取
	public AsyncDataLoader(Handler handler,String id){
		this.handler=handler;
		this.id=id;
	}
	
	//加载文字信息
	public void loadData(){
		//完整的请求url
		url+=id;
		
		//开启线程获取文字信息
		new Thread(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpGet httpGet=new HttpGet(url);
				HttpResponse httpResponse=null;
				try {
					httpResponse=new DefaultHttpClient().execute(httpGet);
					if(httpResponse.getStatusLine().getStatusCode()==200){
						resultJson=EntityUtils.toString(httpResponse.getEntity());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(!resultJson.equals("")){
					// 发送消息传递数据
					Book book=setData(resultJson);
					Message message = handler.obtainMessage(ms,
							book);
					handler.sendMessage(message);
				}
				else {
					//发送消息传递数据
					Message message=handler.obtainMessage(-1);
					handler.sendMessage(message);
				}
			}
		}.start();
	}
	
	//解析json:列表页
	public Book setData(String resultJson){
		Book book=new Book();
		try {
			JSONObject jsonObject=new JSONObject(resultJson);
			
			JSONObject jsonObject_rating=jsonObject.getJSONObject("rating");
			Rating rating=new Rating(
					jsonObject_rating.getString("max"), jsonObject_rating.getString("numRaters"), 
					jsonObject_rating.getString("average"), jsonObject_rating.getString("min"));
			book.setRating(rating);
			
			book.setSubtitle(jsonObject.getString("subtitle"));
			book.setPubdate(jsonObject.getString("pubdate"));
			book.setImage(jsonObject.getString("image"));
			book.setBingding(jsonObject.getString("bingding"));
			
			JSONObject jsonObject_images=jsonObject.getJSONObject("images");
			Images images=new Images(jsonObject_images.getString("small"), 
					jsonObject_images.getString("large"), jsonObject_images.getString("medium"));
			book.setImages(images);
			
			book.setAlt(jsonObject.getString("alt"));
			book.setId(jsonObject.getString(id));
			book.setTitle(jsonObject.getString("title"));
			book.setAuthor_intro(jsonObject.getString("author_intro"));
			
			JSONArray jsonObjs_tags= jsonObject.getJSONArray("tags");
			for(int i=0;i<jsonObjs_tags.length();i++){
				JSONObject jsonObject_tags=jsonObjs_tags.getJSONObject(i);
				Tags tags=new Tags(jsonObject_tags.getString("count"), jsonObject_tags.getString("name"),
						jsonObject_tags.getString("title"));
				book.setTags(tags);
			}
			
			book.setOrigin_title(jsonObject.getString("origin_title"));
			book.setPrice(jsonObject.getString("price"));
			
			JSONArray jsonObjs_translator= jsonObject.getJSONArray("translator");
			for(int i=0;i<jsonObjs_translator.length();i++){
				String translator=jsonObjs_translator.getString(i);
				book.setTranslator(translator);
			}
			
			book.setPages(jsonObject.getString("pages"));
			book.setPublisher(jsonObject.getString("publisher"));
			book.setIsbn10(jsonObject.getString("isbn10"));
			book.setIsbn13(jsonObject.getString("isbn13"));
			book.setAlt_title(jsonObject.getString("alt_title"));
			book.setUrl(jsonObject.getString("url"));
			
			JSONArray jsonObjs_author= jsonObject.getJSONArray("author");
			for(int i=0;i<jsonObjs_author.length();i++){
				String author=jsonObjs_author.getString(i);
				book.setAuthor(author);
			}
			
			book.setSummary(jsonObject.getString("summary"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		return book;
	}
}