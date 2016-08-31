package com.example.zhangping.facelovestudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class modifyPersonSginActivity extends BaseActivity {

    private EditText idetmodifysgin;
    private Button idbtnsavesgin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_person_sgin);

        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("修改签名");
        initialize();
        idbtnsavesgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpUtils httpUtils = new HttpUtils();
                String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&signature=" + idetmodifysgin.getText().toString();
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
                            intent.putExtra(MacroClass.SGIN,idetmodifysgin.getText().toString());
                            setResult(0,intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),generalResultResponse.getResult(),Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getBaseContext(),"修改失败",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    private void initialize() {

        idetmodifysgin = (EditText) findViewById(R.id.id_et_modifysgin);
        idbtnsavesgin = (Button) findViewById(R.id.id_btn_savesgin);
    }
}
