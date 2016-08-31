package com.example.zhangping.facelovestudio.activity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.jsonparseclass.XingXiangResponse;
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

public class XingXiangActivity extends BaseActivity {

    private GridView idgridviewxingxiang;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageLoader imageLoader;
    private ImageLoaderConfiguration imageLoaderConfiguration;
    private DisplayImageOptions displayImageOptions;

    private ArrayList<String> arrayList;
    XingXiangAdapter xingXiangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xing_xiang);
        initialize();
        arrayList = new ArrayList<>();
        imageLoaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageLoaderConfiguration);
        displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        xingXiangAdapter = new XingXiangAdapter();

        idgridviewxingxiang.setAdapter(xingXiangAdapter);
        urlStr = MacroClass.URL_PREXI + "head_getFigure.action?userid=" + getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "");
        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                XingXiangResponse xingXiangResponse = gson.fromJson(responseInfo.result,XingXiangResponse.class);

                if (xingXiangResponse.getResult().equals("success")) {
                    for (int i = 0; i < xingXiangResponse.getModul().length; i++) {
                       arrayList.add(MacroClass.figureUrl(xingXiangResponse.getModul()[i].getFilename()));
                    }
                    xingXiangAdapter.notifyDataSetChanged();

                }
                else
                {
                    Toast.makeText(getBaseContext(), xingXiangResponse.getResult().toString(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getBaseContext(), s,Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initialize() {

        idgridviewxingxiang = (GridView) findViewById(R.id.id_gridview_xingxiang);
    }

    class XingXiangAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
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
            ViewHolder viewHolder;
            if (null == convertView){
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_gridview_getxingxiang,null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.id_iv_grid_xingxiang);
                convertView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            ImageLoader.getInstance().displayImage(arrayList.get(position), viewHolder.imageView, displayImageOptions);
            return convertView;
        }
    }

    class ViewHolder
    {
        ImageView imageView;
    }
}
