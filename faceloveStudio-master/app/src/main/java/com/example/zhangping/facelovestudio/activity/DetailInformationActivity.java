package com.example.zhangping.facelovestudio.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.DetailInfoResponse;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.jsonparseclass.UserInfoResponse;
import com.example.zhangping.utils.MacroClass;
import com.example.zhangping.utils.NavigationView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import javax.crypto.Mac;

public class DetailInformationActivity extends BaseActivity{


    private String str_addstatus;
    private String str_headimage;
    private String str_sex;

    private String str_userid;
    private String str_ps;
    private String str_school;
    private String str_nickname;
    private TextView idtvdetailinfohead;
    private ImageView idivdetailinfohead;
    private TextView idtvdetailinfops;
    private TextView idtvdetailinfosgin;
    private TextView idtvdetailinfopd;
    private TextView idtvdetailinfoschool;
    private TextView idtvdetailinfonickname;
    private LinearLayout idlldetailinfopd;
    private ImageView idivdetailinfosex;
    private Button idbtndetailinfosendmessage;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);
        str_userid = getIntent().getStringExtra(MacroClass.USER_ID);

        TextView tv= (TextView)findViewById(R.id.title_text);
        tv.setText("详细资料");

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration loaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(loaderConfiguration);
        final DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageOnFail(null)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();

        urlStr = MacroClass.URL_PREXI + "user_detailedInfo.action?userid=" + str_userid;
        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new Gson();
                DetailInfoResponse detailInfoResponse = gson.fromJson(responseInfo.result, DetailInfoResponse.class);
                idtvdetailinfonickname.setText(detailInfoResponse.getFriend().getNickname());
                idtvdetailinfosgin.setText(detailInfoResponse.getFriend().getSignature());
                idtvdetailinfoschool.setText(detailInfoResponse.getFriend().getSchool());

                if (detailInfoResponse.getFriend().getSex().equals("girl")) {
                    idivdetailinfosex.setImageResource(R.drawable.sexgirl);
                } else {
                    idivdetailinfosex.setImageResource(R.drawable.sexboy);

                }

                ImageLoader.getInstance().displayImage(MacroClass.headUrl(detailInfoResponse.getFriend().getHeadimage()), idivdetailinfohead, displayImageOptions);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

        initialize();
        navigationView.setTitle("详细资料");
        navigationView.setRigthVisibility(View.INVISIBLE);
        navigationView.setClickCallBack(new NavigationView.ClickCallBack() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(getBaseContext(),"点击右边",Toast.LENGTH_LONG).show();

            }
        });
        final Bundle bundle = getIntent().getExtras();
        if (bundle.getBoolean("from_campuscenterActivity") == true)
        {
            idbtndetailinfosendmessage.setText("添加好友");
            idbtndetailinfosendmessage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    urlStr = MacroClass.URL_PREXI + "connect_addfriend.action?userid=" + str_userid + "&friendid=" + bundle.getString(MacroClass.USER_ID);
                    httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {

                            GeneralResultResponse generalResultResponse = gson.fromJson(responseInfo.result, GeneralResultResponse.class);
                            if (generalResultResponse.getResult().equals(MacroClass.SUCCESS))
                            {
                                Toast.makeText(getBaseContext(),"添加好友成功",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getBaseContext(),generalResultResponse.getErrorcode(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(getBaseContext(),"添加好友失败",Toast.LENGTH_LONG).show();

                        }
                    });
                }
            });
        }

    }


    private void initialize() {

        idtvdetailinfohead = (TextView) findViewById(R.id.id_tv_detailinfo_head);
        idivdetailinfohead = (ImageView) findViewById(R.id.id_iv_detailinfo_head);
        idtvdetailinfops = (TextView) findViewById(R.id.id_tv_detailinfo_ps);
        idtvdetailinfosgin = (TextView) findViewById(R.id.id_tv_detailinfo_sgin);
        idtvdetailinfopd = (TextView) findViewById(R.id.id_tv_detailinfo_pd);
        idtvdetailinfoschool = (TextView) findViewById(R.id.id_tv_detailinfo_school);
        idtvdetailinfonickname = (TextView) findViewById(R.id.id_tv_detailinfo_nickname);
        idlldetailinfopd = (LinearLayout) findViewById(R.id.id_ll_detailinfo_pd);
        idivdetailinfosex = (ImageView) findViewById(R.id.id_iv_detailinfo_sex);
        idbtndetailinfosendmessage = (Button) findViewById(R.id.id_btn_detailinfo_sendmessage);
        navigationView = (NavigationView)findViewById(R.id.id_detailinfo_title);
    }
}
