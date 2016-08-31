package com.example.zhangping.facelovestudio.Base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.zhangping.facelovestudio.R;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class BaseActivity extends Activity implements View.OnClickListener {

    private boolean mAllowFullScreen = true;
    public String urlStr;
    public HttpUtils httpUtils = new HttpUtils();
    public Gson gson = new Gson();
    protected DisplayImageOptions displayImageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen)
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onClick(View view) {

    }
}
