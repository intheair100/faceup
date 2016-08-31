package com.example.zhangping.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangping.facelovestudio.R;

import org.w3c.dom.Text;

/**
 * Created by Zhangping on 16/1/12.
 */
public class NavigationView extends LinearLayout implements View.OnClickListener {

    public TextView tv_title;
    public Button btn_leftbutton;
    public Button btn_rightbutton;
    public ClickCallBack clickCallBack;
    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.title,this,true);
        tv_title = (TextView)findViewById(R.id.title_text);
        btn_leftbutton = (Button)findViewById(R.id.title_left);
        btn_rightbutton = (Button)findViewById(R.id.title_right);

        btn_rightbutton.setOnClickListener(this);
        btn_leftbutton.setOnClickListener(this);


    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setRigthVisibility(int Visibility) {
        btn_rightbutton.setVisibility(Visibility);
    }


    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public static interface  ClickCallBack
    {
        public void onLeftClick();
        public void onRightClick();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.title_left)
        {
            this.clickCallBack.onLeftClick();
            return;
        }
        else if (v.getId() == R.id.title_right)
        {
            this.clickCallBack.onRightClick();
            return;
        }
    }
}
