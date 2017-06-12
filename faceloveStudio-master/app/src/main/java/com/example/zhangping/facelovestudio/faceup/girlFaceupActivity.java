package com.example.zhangping.facelovestudio.faceup;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.Base.BaseGridViewAdapter;
import com.example.zhangping.facelovestudio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class girlFaceupActivity extends BaseActivity {

    private ImageView idivfaceup;
    private HorizontalListView idhorizontallistviewfaceup;
    private ViewPager idviewpagerfaceup;

    private ArrayList<GridView> gridViews = new ArrayList<GridView>();

    private ArrayList<Map<String,Object>> list = new ArrayList<>();
    private int[] i_head = new int[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faceup);
        initialize();

        idhorizontallistviewfaceup.setAdapter(new horizontalListviewAdapter());

        View v_face = getLayoutInflater().inflate(R.layout.gridview_face,null);
        final GridView gridView_face= (GridView)v_face.findViewById(R.id.id_gridview_face);

        View v_body = getLayoutInflater().inflate(R.layout.gridview_body,null);
        final GridView gridView_body= (GridView)v_body.findViewById(R.id.id_gridview_body);

        View v_eye = getLayoutInflater().inflate(R.layout.gridview_eye,null);
        final GridView gridView_eye= (GridView)v_eye.findViewById(R.id.id_gridview_eye);

        View v_mouth = getLayoutInflater().inflate(R.layout.gridview_mouth,null);
        final GridView gridView_mouth= (GridView)v_mouth.findViewById(R.id.id_gridview_mouth);

        View v_glass = getLayoutInflater().inflate(R.layout.gridview_glass,null);
        final GridView gridView_glass= (GridView)v_glass.findViewById(R.id.id_gridview_glass);

        View v_hair = getLayoutInflater().inflate(R.layout.gridview_hair,null);
        final GridView gridView_hair= (GridView)v_hair.findViewById(R.id.id_gridview_hair);

        View v_eyebrow = getLayoutInflater().inflate(R.layout.gridview_eyebrow,null);
        final GridView gridView_eyebrow = (GridView)v_eyebrow.findViewById(R.id.id_gridview_eyebrow);

        for (int i = 0; i < 30; i++) {
            i_head[i] = R.drawable.like;
        }

        faceGridViewAdapter faceGridViewAdapter = new faceGridViewAdapter(i_head,R.layout.item_gridview_face,R.id.id_iv_grid_face,this);
        gridView_face.setAdapter(faceGridViewAdapter);

        eyebrowGridViewAdapter eyebrowGridViewAdapter = new eyebrowGridViewAdapter(i_head,R.layout.item_gridview_eye,R.id.id_iv_grid_eye,this);
        gridView_eye.setAdapter(eyebrowGridViewAdapter);

        hairGridViewAdapter hairGridViewAdapter = new hairGridViewAdapter(i_head,R.layout.item_gridview_hair,R.id.id_iv_grid_hair,this);
        gridView_hair.setAdapter(hairGridViewAdapter);

        bodyGridViewAdapter bodyGridViewAdapter = new bodyGridViewAdapter(i_head,R.layout.item_gridview_body,R.id.id_iv_grid_body,this);
        gridView_body.setAdapter(bodyGridViewAdapter);





        eyebrowGridViewAdapter eyebrowGridViewAdapter1 = new eyebrowGridViewAdapter(i_head,R.layout.item_gridview_eyebrow,R.id.id_iv_grid_eyebrow,this);
        gridView_eyebrow.setAdapter(eyebrowGridViewAdapter);


        glassGridViewAdapter glassGridViewAdapter = new glassGridViewAdapter(i_head,R.layout.item_gridview_glass,R.id.id_iv_grid_glase,this);
        gridView_glass.setAdapter(glassGridViewAdapter);


        mouthGridViewAdapter mouthGridViewAdapter = new mouthGridViewAdapter(i_head,R.layout.item_gridview_mouth,R.id.id_iv_grid_mouth,this);
        gridView_mouth.setAdapter(mouthGridViewAdapter);



        gridViews.add(gridView_face);
        gridViews.add(gridView_eye);
        gridViews.add(gridView_body);
        gridViews.add(gridView_eyebrow);
        gridViews.add(gridView_glass);
        gridViews.add(gridView_hair);
        gridViews.add(gridView_mouth);

        idviewpagerfaceup.setAdapter(new faceupViewPagerAdapter(gridViews));
        idviewpagerfaceup.setOffscreenPageLimit(7);
    }

    private void initialize() {

//        idivfaceup = (ImageView) findViewById(R.id.id_iv_faceup);
        idhorizontallistviewfaceup = (HorizontalListView) findViewById(R.id.id_horizontallistview_faceup);
        idviewpagerfaceup = (ViewPager) findViewById(R.id.id_viewpager_faceup);
    }

//    class gridviewAdapter extends BaseAdapter
//    {
//        @Override
//        public int getCount() {
//            return i_head.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            GridViewHolder gridViewHolder;
//            if (null != convertView)
//            {
//                gridViewHolder = (GridViewHolder)convertView.getTag();
//            }
//            else
//            {
//                gridViewHolder = new GridViewHolder();
//                convertView = getLayoutInflater().inflate(R.layout.item_grid_head,null);
//                gridViewHolder.iv_head = (ImageView)convertView.findViewById(R.id.id_iv_gridhead);
//                convertView.setTag(gridViewHolder);
//            }
//            gridViewHolder.iv_head.setImageResource(i_head[position]);
//            return convertView;
//        }
//
//        class GridViewHolder
//        {
//            ImageView iv_head;
//        }
//    }
    class horizontalListviewAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return 7;
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

            viewHolder viewHolder;
            if (null != convertView)
            {
                viewHolder = (viewHolder)convertView.getTag();
            }
            else
            {
                viewHolder = new viewHolder();
                convertView = getLayoutInflater().inflate(R.layout.horizontal_list_item,null);
                viewHolder.iv_type = (ImageView)convertView.findViewById(R.id.id_iv_horizontal_item);
                convertView.setTag(viewHolder);
            }
            viewHolder.iv_type.setImageResource(R.drawable.lists_on);
            return convertView;
        }


        private  class viewHolder
        {
            ImageView iv_type;
        }

    }



}
