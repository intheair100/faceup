package com.example.zhangping.facelovestudio.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.jsonparseclass.LoginResponse;
import com.example.zhangping.utils.MacroClass;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Timer;
import java.util.TimerTask;

public class modifyNicknameActivity extends BaseActivity {

    private EditText et_nickname;
    private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nickname);
        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("修改昵称");
        //首先要对指定的输入框请求焦点。然后调用输入管理器弹出软键盘。
        //警告：对于刚跳到一个新的界面就要弹出软键盘的情况上述代码可能由于界面为加载完全而无法弹出软键盘。此时应该适当的延迟弹出软键盘如998毫秒（保证界面的数据加载完成）。
        et_nickname = (EditText)findViewById(R.id.id_et_modifynickname);
        btn_save = (Button)findViewById(R.id.id_btn_savenickname);
        et_nickname.setFocusable(true);
        et_nickname.setFocusableInTouchMode(true);
        et_nickname.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) et_nickname.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(et_nickname, 0);
            }
        },998);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&nickname=" + et_nickname.getText().toString();

                HttpUtils httpUtils = new HttpUtils();

                httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.i("onFailure", "onFailure on");

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.i("onSuccess","onSuccess on");
                        String  result = responseInfo.result;

                        Gson gson = new Gson();
                        GeneralResultResponse generalResultResponse = gson.fromJson(result,GeneralResultResponse.class);
                        if (generalResultResponse.getResult().equals("success"))
                        {
                            Toast.makeText(getBaseContext(), "修改成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra(MacroClass.NICK_NAME,et_nickname.getText().toString());
                            setResult(0, intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), generalResultResponse.getResult(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}
