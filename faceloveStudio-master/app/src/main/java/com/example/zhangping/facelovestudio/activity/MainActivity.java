package com.example.zhangping.facelovestudio.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.LoginResponse;
import com.example.zhangping.utils.CheckNetwork;
import com.example.zhangping.utils.MacroClass;
import com.example.zhangping.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.lang.reflect.Type;
import java.util.LinkedList;

import javax.crypto.Mac;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btn_login;
    private TextView txt_register;
    private EditText et_login_password_input;
    private EditText et_login_user_input;

    private  SharedPreferences settings;
    private  SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//requestWindowFeature必须要在setcontent之前，否则会崩溃。先由标题栏，再决定布局。
        setContentView(R.layout.activity_main);
        btn_login = (Button)findViewById(R.id.signin_button);
        txt_register = (TextView)findViewById(R.id.register_link);
        et_login_user_input = (EditText)findViewById(R.id.username_edit);
        et_login_password_input = (EditText)findViewById(R.id.password_edit);

        btn_login.setOnClickListener(this);
        txt_register.setOnClickListener(this);
        settings = getSharedPreferences(MacroClass.SHAREDPREFERENCES,0);
        editor = settings.edit();

        et_login_user_input.setText((String)(SPUtils.get(MainActivity.this, MacroClass.USER_NAME,"")));
        et_login_password_input.setText((String)SPUtils.get(MainActivity.this,MacroClass.PASSWORD,""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch( view.getId() ){

            case R.id.register_link:

                AlertDialog.Builder  builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("请选择性别");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("男", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putString(MacroClass.FLOW,"THE_REGISTER_FLOW");
                        editor.putString(MacroClass.SEX, "boy");
                        editor.commit();
                        dialogInterface.dismiss();
                        Intent intent = new Intent(MainActivity.this,registerinfoactivity.class);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("女", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putString(MacroClass.FLOW,"THE_REGISTER_FLOW");
                        editor.putString(MacroClass.SEX,"girl");
                        editor.commit();
                        dialogInterface.dismiss();
                        Intent intent = new Intent(MainActivity.this,registerinfoactivity.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();
                break;
            case  R.id.signin_button:
                boolean isNetworkConnected = CheckNetwork.isNetworkConnected(this);
                if (!isNetworkConnected)
                {
                    Toast.makeText(getBaseContext(),"网络未连接",Toast.LENGTH_LONG);
                    return;
                }
                if (et_login_user_input.length() <= 0)
                {
                    Toast.makeText(getBaseContext(), "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (et_login_password_input.length() <= 0)
                {
                    Toast.makeText(getBaseContext(),"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }

                String urlStr = MacroClass.URL_PREXI + "login_login.action?phone=" + et_login_user_input.getText().toString() + "&password=" + et_login_password_input.getText().toString();

                HttpUtils httpUtils = new HttpUtils();

                httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.i("onFailure","onFailure on");

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.i("onSuccess","onSuccess on");
                        String  result = responseInfo.result;

                        Gson gson = new Gson();
                        LoginResponse loginResponse = gson.fromJson(result,LoginResponse.class);
                        if (loginResponse.getResult().equals("success"))
                        {

                            Intent intent = new Intent(MainActivity.this,tabActivity.class);
                            startActivity(intent);
                            editor.putString(MacroClass.USER_ID, loginResponse.getUserid());
                            editor.putString(MacroClass.NICK_NAME,loginResponse.getNickname());
                            editor.putString(MacroClass.HEAD_URL,loginResponse.getHeadurl());
                            editor.putString(MacroClass.USER_NAME, et_login_user_input.getText().toString());
                            editor.putString(MacroClass.PASSWORD,et_login_password_input.getText().toString());
                            editor.commit();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, loginResponse.getResult(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                break;
        }

    }
}
