package com.example.zhangping.facelovestudio.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.CampusCenterResponse;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.jsonparseclass.UserInfoResponse;
import com.example.zhangping.utils.MacroClass;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import javax.crypto.Mac;

public class CampusCenterActivity extends BaseActivity {

    private ListView idivcampuscenter;
    private ArrayList<String> arr_headimage;
    private ArrayList<String > arr_nickname;
    private ArrayList<String > arr_userid;
    private ArrayList<String > arr_popular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_center);
        initialize();

        arr_headimage = new ArrayList<>();
        arr_nickname = new ArrayList<>();
        arr_userid = new ArrayList<>();
        arr_popular = new ArrayList<>();

        final CampusCenterAdapter adapter = new CampusCenterAdapter();
        idivcampuscenter.setAdapter(adapter);

        urlStr = MacroClass.URL_PREXI + "connect_campusCenter.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "")
                + "&sex=a&hot=l&page=1";
        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                CampusCenterResponse campusCenterResponse = gson.fromJson(responseInfo.result, CampusCenterResponse.class);
                if (campusCenterResponse.getResult().equals("success")) {
                    for (UserInfoResponse response : campusCenterResponse.getCampus()
                            ) {
                        arr_headimage.add(response.getHeadimage());
                        arr_nickname.add(response.getNickname());
                        arr_popular.add(response.getPopular());
                        arr_userid.add(response.getUserId());
                    }
                    adapter.notifyDataSetChanged();
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

        idivcampuscenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString(MacroClass.USER_ID, arr_userid.get(position));
                bundle.putBoolean("from_campuscenterActivity",true);
                bundle.putString(MacroClass.USER_ID, arr_userid.get(position));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getBaseContext(),DetailInformationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {

        idivcampuscenter = (ListView) findViewById(R.id.id_iv_campuscenter);
    }

    public class CampusCenterAdapter extends BaseAdapter{

        ViewHolder viewHolder;

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (null == convertView)
            {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_campuscenter,null);
                viewHolder.holder_iv_head= (ImageView) convertView.findViewById(R.id.id_iv_campuscenter_head);
                viewHolder.holder_tv_nickname = (TextView) convertView.findViewById(R.id.id_tv_campuscenter_nickname);
                viewHolder.holder_iv_praise = (ImageView) convertView.findViewById(R.id.id_iv_campuscenter_praise);
                viewHolder.holder_tv_popular = (TextView) convertView.findViewById(R.id.id_tv_campuscenter_praise);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.holder_tv_popular.setText(arr_popular.get(position));
            viewHolder.holder_tv_nickname.setText(arr_nickname.get(position));
            ImageLoader.getInstance().displayImage(MacroClass.headUrl(arr_headimage.get(position)), viewHolder.holder_iv_head, displayImageOptions);
            viewHolder.holder_iv_praise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点赞
                    urlStr = MacroClass.URL_PREXI + "connect_addZanNum.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "")
                            + "&friendid=" + arr_userid.get(position);
                    httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            GeneralResultResponse generalResultResponse = gson.fromJson(responseInfo.result,
                                    GeneralResultResponse.class);
                            if (generalResultResponse.getResult().equals("success")) {

                                arr_popular.set(position, (Integer.parseInt(arr_popular.get(position))+1) + "" );
                                View view = idivcampuscenter.getChildAt(position - idivcampuscenter.getFirstVisiblePosition());
                                idivcampuscenter.getAdapter().getView(position,view,idivcampuscenter);
                            } else {
                                Toast.makeText(getBaseContext(), generalResultResponse.getResult(), Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(HttpException e, String s) {

                        }

                    });
                }
            });

            return convertView;

        }

    public class ViewHolder{
        private ImageView holder_iv_head;
        private ImageView holder_iv_praise;
        private TextView holder_tv_nickname;
        private TextView holder_tv_popular;
    }
}}
