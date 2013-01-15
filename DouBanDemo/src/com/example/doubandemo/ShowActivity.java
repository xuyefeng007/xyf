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
	//ͼ��ID
	private String id;
	
	//����
	private LinearLayout load;
	//����
	private LinearLayout layout;
	//ͼƬ
	private ImageView img;
	//����
	private TextView name;
	//����
	private TextView author;
	//����
	private TextView translator;
	//�۸�
	private TextView price;
	//������
	private TextView publisher;
	//ID
	private TextView id_text;
	//ҳ��
	private TextView pages;
	//��������
	private TextView pubdate;
	//������1
	private TextView isbn10;
	//������2
	private TextView isbn13;
	//�ؼ��ֱ�ǩ
	private TextView tags;
	//ժҪ
	private TextView summary;
	
	//�첽���ݼ���
	private AsyncDataLoader asyncDataLoader;
	//����Դ
	private Book book;

	//�߳�ͨ��
	private final Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			book=(Book)msg.obj;
			switch (msg.what) {
			//���ӷ�����ʧ��
			case -1:
				//���ؼ��ؽ�����������
				load.setVisibility(View.GONE);
				Toast.makeText(ShowActivity.this, "���ӷ�������ʱ��������������ԣ�", Toast.LENGTH_SHORT).show();
				break;
				
			//��ʾ����
			case 1:
				if(book!=null){
					setData(book);
					
					//���ؼ��ؽ�����������
					load.setVisibility(View.GONE);
					//��ʾ����
					layout.setVisibility(View.VISIBLE);
				}
				else {
					//���ؼ��ؽ�����������
					load.setVisibility(View.GONE);
					Toast.makeText(ShowActivity.this, "���������޸����ݣ�", Toast.LENGTH_SHORT).show();
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
        
        //��ȡ�û��������ID
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
    
    //��ʼ������
    public void setData(Book book){
    	img.setImageBitmap(loadImageFromUrl(book.getImage()));
    	name.setText("������"+book.getTitle());
    	author.setText("���ߣ�"+book.getAuthor());
    	translator.setText("���룺"+book.getTranslator());
    	price.setText("�ۼۣ�"+book.getPrice());
    	publisher.setText("�����磺"+book.getPublisher());
    	id_text.setText("ID��"+book.getId());
    	pages.setText("ҳ����"+book.getPages());
    	pubdate.setText("�������ڣ�"+book.getPubdate());
    	isbn10.setText("isbn10��"+book.getIsbn10());
    	isbn13.setText("isbn13��"+book.getIsbn13());
    	
    	List<Tags> list=book.getTags();
    	String tString="";
    	for(int i=0;i<list.size();i++){
    		String s=list.get(i).getTitle();
    		tString=tString+s+" ";
    	}
    	tags.setText(tString);
    	summary.setText(book.getSummary());
    }

    //����url��ȡͼƬ
  	public Bitmap loadImageFromUrl(String imageUrl){
  		Bitmap bitmap=null;
  		try {
  			//����url�õ�������������bitmap����
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
    
    //�����ֻ�back���¼�
  	@Override
  	public boolean onKeyDown(int keyCode, KeyEvent event) {
  		// TODO Auto-generated method stub
  		switch(keyCode){
  		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent(ShowActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			// �����л�����������߽��룬���ұ��˳�
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);			
  			break;
  		}
  		return super.onKeyDown(keyCode, event);
  	}
}
