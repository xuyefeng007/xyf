package com.example.doubandemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private LinearLayout layout;
	private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        layout=(LinearLayout)findViewById(R.id.layout);
        layout.setOnClickListener(new LayoutClickListener());
        
        editText=(EditText)findViewById(R.id.edit);
    }

    public class LayoutClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(!TextUtils.isEmpty(editText.getText())){
				String id=editText.getText().toString();
				Intent intent=new Intent(getApplicationContext(),ShowActivity.class);
				intent.putExtra("id", id);
				startActivity(intent);
				finish();
				//设置切换动画，从右边进入，从左边退出
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
			else {
				Toast.makeText(getApplicationContext(), "请输入查询图书的ID", Toast.LENGTH_SHORT).show();
			}		
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
