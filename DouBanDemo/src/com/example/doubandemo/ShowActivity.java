package com.example.doubandemo;

import java.net.URL;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShowActivity extends Activity {
	//图书ID
	private String id;
	
	//加载
	private LinearLayout load;
	//数据
	private LinearLayout layout;
	//图片
	private ImageView img;
	//书名
	private TextView name;
	//作者
	private TextView author;
	//翻译
	private TextView translator;
	//价格
	private TextView price;
	//出版社
	private TextView publisher;
	//ID
	private TextView id_text;
	//页数
	private TextView pages;
	//出版日期
	private TextView pubdate;
	//条形码1
	private TextView isbn10;
	//条形码2
	private TextView isbn13;
	//关键字标签
	private TextView tags;
	//摘要
	private TextView summary;
	
	//异步数据加载
	private AsyncDataLoader asyncDataLoader;
	//数据源
	private Book book;

	//线程通信
	private final Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			book=(Book)msg.obj;
			switch (msg.what) {
			//连接服务器失败
			case -1:
				//隐藏加载进度条和文字
				load.setVisibility(View.GONE);
				Toast.makeText(ShowActivity.this, "连接服务器超时，请检查网络后重试！", Toast.LENGTH_SHORT).show();
				break;
				
			//显示数据
			case 1:
				if(book!=null){
					setData(book);
					
					//隐藏加载进度条和文字
					load.setVisibility(View.GONE);
					//显示内容
					layout.setVisibility(View.VISIBLE);
				}
				else {
					//隐藏加载进度条和文字
					load.setVisibility(View.GONE);
					Toast.makeText(ShowActivity.this, "服务器暂无该数据！", Toast.LENGTH_SHORT).show();
				}
				break;
				
			default:
				break;
			}
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        
        //获取用户输入的书ID
        id=getIntent().getExtras().getString("id");
        
        load=(LinearLayout)findViewById(R.id.load);
        layout=(LinearLayout)findViewById(R.id.layout);
        img=(ImageView)findViewById(R.id.img);
        name=(TextView)findViewById(R.id.name);
        author=(TextView)findViewById(R.id.author);
        translator=(TextView)findViewById(R.id.translator);
        price=(TextView)findViewById(R.id.price);
        publisher=(TextView)findViewById(R.id.publisher);
        id_text=(TextView)findViewById(R.id.id);
        pages=(TextView)findViewById(R.id.pages);
        pubdate=(TextView)findViewById(R.id.pubdate);
        isbn10=(TextView)findViewById(R.id.isbn10);
        isbn13=(TextView)findViewById(R.id.isbn13);
        tags=(TextView)findViewById(R.id.tags);
        summary=(TextView)findViewById(R.id.summary);
        
        asyncDataLoader=new AsyncDataLoader(handler, id);
        asyncDataLoader.loadData();
    }
    
    //初始化数据
    public void setData(Book book){
    	img.setImageBitmap(loadImageFromUrl(book.getImage()));
    	name.setText("书名："+book.getTitle());
    	author.setText("作者："+book.getAuthor());
    	translator.setText("翻译："+book.getTranslator());
    	price.setText("售价："+book.getPrice());
    	publisher.setText("出版社："+book.getPublisher());
    	id_text.setText("ID："+book.getId());
    	pages.setText("页数："+book.getPages());
    	pubdate.setText("出版日期："+book.getPubdate());
    	isbn10.setText("isbn10："+book.getIsbn10());
    	isbn13.setText("isbn13："+book.getIsbn13());
    	
    	List<Tags> list=book.getTags();
    	String tString="";
    	for(int i=0;i<list.size();i++){
    		String s=list.get(i).getTitle();
    		tString=tString+s+" ";
    	}
    	tags.setText(tString);
    	summary.setText(book.getSummary());
    }

    //根据url获取图片
  	public Bitmap loadImageFromUrl(String imageUrl){
  		Bitmap bitmap=null;
  		try {
  			//根据url得到输入流来生成bitmap对象
  			bitmap=BitmapFactory.decodeStream(new URL(imageUrl).openStream());
  		} catch (Exception e) {
  			// TODO: handle exception
  		}	
  		
  		return bitmap;
  	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //监听手机back键事件
  	@Override
  	public boolean onKeyDown(int keyCode, KeyEvent event) {
  		// TODO Auto-generated method stub
  		switch(keyCode){
  		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent(ShowActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			// 设置切换动画，从左边进入，从右边退出
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);			
  			break;
  		}
  		return super.onKeyDown(keyCode, event);
  	}
}
