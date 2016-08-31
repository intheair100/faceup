package com.example.zhangping.facelovestudio.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.utils.MacroClass;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Timer;
import java.util.TimerTask;

public class modifySchoolActivity extends BaseActivity {

    private EditText idetmodifyschool;
    private Button idbtnsaveschool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_school);

        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("修改学校");

        initialize();
        idetmodifyschool.setFocusable(true);
        idetmodifyschool.setFocusableInTouchMode(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) idetmodifyschool.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(idetmodifyschool, 0);
            }
        },998);
        idbtnsaveschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&school=" + idetmodifyschool.getText().toString();

                HttpUtils httpUtils  =new HttpUtils();
                httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        String result = responseInfo.result;
                        Gson gson = new Gson();
                        GeneralResultResponse generalResultResponse = gson.fromJson(result,GeneralResultResponse.class);
                        if (generalResultResponse.getResult().equals("success"))
                        {
                            Toast.makeText(getBaseContext(),"修改成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.putExtra(MacroClass.SCHOOL,idetmodifyschool.getText().toString());
                            setResult(0,intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });

            }
        });
    }

    private void initialize() {

        idetmodifyschool = (EditText) findViewById(R.id.id_et_modifyschool);
        idbtnsaveschool = (Button) findViewById(R.id.id_btn_saveschool);
    }
}
