package com.example.zhangping.facelovestudio.faceup;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Zhangping on 16/1/15.
 */
public class faceupViewPagerAdapter extends PagerAdapter{

    private ArrayList<GridView> m_gridViews;


    public faceupViewPagerAdapter(ArrayList<GridView> gridViews)
    {
        m_gridViews = gridViews;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        return m_gridViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(m_gridViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        super.instantiateItem(container, position);
        container.addView(m_gridViews.get(position));
        return m_gridViews.get(position);
    }

}
