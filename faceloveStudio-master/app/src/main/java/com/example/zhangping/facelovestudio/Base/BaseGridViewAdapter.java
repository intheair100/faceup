package com.example.zhangping.facelovestudio.Base;

import android.content.Context;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.zhangping.facelovestudio.R;

import java.util.zip.Inflater;

/**
 * Created by Zhangping on 16/1/16.
 */
public class BaseGridViewAdapter extends BaseAdapter {

    int[] m_item_array;
    Context m_baseGridViewAdapterContext;
    int m_layout_id;
    int m_imageview_id;
    WindowManager wm;
    public BaseGridViewAdapter(int[]itemarr,int layoutid, int iv_id,Context context) {
        super();
        m_item_array = itemarr;
        m_layout_id = layoutid;
        m_imageview_id = iv_id;
        m_baseGridViewAdapterContext = context;
        wm= (WindowManager) m_baseGridViewAdapterContext.getSystemService(Context.WINDOW_SERVICE);

    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public int getCount() {
        return m_item_array.length;
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
        GridViewHolder gridViewHolder;
        if (null != convertView)
        {
            gridViewHolder = (GridViewHolder)convertView.getTag();
        }
        else
        {
            gridViewHolder = new GridViewHolder();
            LayoutInflater li = (LayoutInflater)m_baseGridViewAdapterContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(m_layout_id,null);
            gridViewHolder.iv_item = (ImageView)convertView.findViewById(m_imageview_id);
            convertView.setTag(gridViewHolder);


            int width =  wm.getDefaultDisplay().getWidth();
            int w = (width -(2 * Dp2Px(m_baseGridViewAdapterContext, 2)))/3;
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(w, w);//传入自己需要的宽高
            convertView.setLayoutParams(param);




        }
        gridViewHolder.iv_item.setImageResource(m_item_array[position]);
        return convertView;
    }

    class GridViewHolder
    {
        ImageView iv_item;
    }
}
