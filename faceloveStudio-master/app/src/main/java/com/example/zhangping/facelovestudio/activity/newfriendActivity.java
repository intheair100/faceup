package com.example.zhangping.facelovestudio.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.CampusCenterResponse;
import com.example.zhangping.jsonparseclass.UserInfoArrayResponse;
import com.example.zhangping.jsonparseclass.UserInfoResponse;
import com.example.zhangping.utils.MacroClass;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import javax.crypto.Mac;

public class newfriendActivity extends BaseActivity {

    private ArrayList<String> arr_nickname;
    private ArrayList<String> arr_headimage;
    private  ArrayList<String > arr_userid;
    private ArrayList<String> arr_personalsign;
    private ArrayList<String> arr_addstatus;
    private ArrayList<String> arr_sex;
    private ArrayList<String> arr_school;


    private newfriendAdapter newfriendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfriend);

        arr_headimage = new ArrayList<>();
        arr_userid = new ArrayList<>();
        arr_personalsign = new ArrayList<>();
        arr_addstatus = new ArrayList<>();
        arr_sex = new ArrayList<>();
        arr_school = new ArrayList<>();

        urlStr = MacroClass.URL_PREXI + "connect_getNewFriend.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "");
        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                CampusCenterResponse campusCenterResponse = gson.fromJson(responseInfo.result,CampusCenterResponse.class);
                if (campusCenterResponse.getResult().equals("success"))
                {
                    for (UserInfoResponse u :
                            campusCenterResponse.getCampus()) {
                        arr_headimage.add(u.getHeadimage());
                        arr_nickname.add(u.getNickName());
                        arr_userid.add(u.getUserId());
                        arr_personalsign.add(u.getUserId());
                        arr_userid.add(u.getUserId());
                        arr_userid.add(u.getUserId());
                    }
                }
                else
                {
                    Toast.makeText(getBaseContext(),campusCenterResponse.getResult(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();

            }
        });
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration loaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(loaderConfiguration);
        displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageOnFail(null)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    private class holder
    {
        public TextView tv_nickname;
        public  TextView tv_ps;
        public TextView tv_add;
        public ImageView iv_headimage;
    }
    private class newfriendAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return arr_userid.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            holder holder = new holder();
            if (null == convertView)
            {
                convertView = getLayoutInflater().inflate(R.layout.item_newfriend,null);
                holder.iv_headimage = (ImageView)convertView.findViewById(R.id.id_iv_newfriend_headimage);
                holder.tv_add = (TextView)convertView.findViewById(R.id.id_tv_newfrend_add);
                holder.tv_nickname = (TextView)convertView.findViewById(R.id.id_tv_newfriend_nickname);
                holder.tv_ps = (TextView)convertView.findViewById(R.id.id_tv_newfrend_ps);
                holder.tv_add = (TextView)convertView.findViewById(R.id.id_tv_newfrend_add);
                convertView.setTag(holder);
            }
            else
            {
                holder = (holder)convertView.getTag();
            }
            holder.tv_ps.setText(arr_personalsign.get(position));
            holder.tv_add.setText(arr_addstatus.get(position));
            holder.tv_nickname.setText(arr_nickname.get(position));
            ImageLoader.getInstance().displayImage(MacroClass.headUrl(arr_headimage.get(position)),holder.iv_headimage,displayImageOptions);
            return convertView;
        }
    }
}
