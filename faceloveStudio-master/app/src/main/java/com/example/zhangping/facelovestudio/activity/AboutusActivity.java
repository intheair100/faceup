package com.example.zhangping.facelovestudio.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;

public class AboutusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("关于");
    }
}
