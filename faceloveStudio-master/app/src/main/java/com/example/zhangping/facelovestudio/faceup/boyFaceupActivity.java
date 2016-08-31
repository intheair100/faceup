package com.example.zhangping.facelovestudio.faceup;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangping.facelovestudio.Base.BaseActivity;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.utils.MacroClass;
import com.lidroid.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.Map;

public class boyFaceupActivity extends BaseActivity implements ViewPager.OnPageChangeListener,AdapterView.OnItemClickListener,View.OnClickListener{

    private RelativeLayout rl_idivfaceup;
    private HorizontalListView idhorizontallistviewfaceup;
    private ViewPager idviewpagerfaceup;

    private ArrayList<GridView> gridViews = new ArrayList<GridView>();

    private ArrayList<Map<String,Object>> list = new ArrayList<>();
    private int[] i_face = new int[30];
    private int[] i_body = new int[30];
    private int[] i_eye = new int[30];
    private int[] i_mouth = new int[30];
    private int[] i_glass = new int[30];
    private int[] i_hair = new int[30];
    private int[] i_eyebrow = new int[30];
    private int[] i_type = {R.drawable.face_grey_bt,R.drawable.eyebrows_grey_bt,R.drawable.hair_grey_bt,R.drawable.body_grey_bt,
            R.drawable.eyes_grey_bt,R.drawable.glasses_grey_bt,R.drawable.mouth_grey_bt};
    private int[] i_type_selected = {R.drawable.face_grey_bt_on,R.drawable.eyebrows_grey_bt_on,R.drawable.hair_grey_bt_on,R.drawable.body_grey_bt_on,
            R.drawable.eyes_grey_bt_on,R.drawable.glasses_grey_bt_on,R.drawable.mouth_grey_bt_on};


    private GridView gridView_face;
    private GridView gridView_body;
    private GridView gridView_eye;
    private GridView gridView_mouth;
    private GridView gridView_glass;
    private GridView gridView_hair;
    private GridView gridView_eyebrow;

    horizontalListviewAdapter horizontalListviewAdapter;
    private ImageView idivfaceupbody;
    private ImageView idivfaceupface;
    private ImageView idivfaceupmouth;
    private ImageView idivfaceupeyes;
    private ImageView idivfaceupeyebrows;
    private ImageView idivfaceuphair;
    private ImageView idivfaceupglasses;
    private LinearLayout lltitle;
    private LinearLayout llmore;

    Button btn_more;
    View v_more;

    TextView tv_find;
    TextView tv_store;
    TextView tv_thisme;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faceup);
        initialize();

        horizontalListviewAdapter = new horizontalListviewAdapter();
        idhorizontallistviewfaceup.setAdapter(horizontalListviewAdapter);

        View v_face = getLayoutInflater().inflate(R.layout.gridview_face,null);
        gridView_face= (GridView)v_face.findViewById(R.id.id_gridview_face);

        View v_body = getLayoutInflater().inflate(R.layout.gridview_body,null);
        gridView_body= (GridView)v_body.findViewById(R.id.id_gridview_body);

        View v_eye = getLayoutInflater().inflate(R.layout.gridview_eye,null);
        gridView_eye= (GridView)v_eye.findViewById(R.id.id_gridview_eye);

        View v_mouth = getLayoutInflater().inflate(R.layout.gridview_mouth,null);
        gridView_mouth= (GridView)v_mouth.findViewById(R.id.id_gridview_mouth);

        View v_glass = getLayoutInflater().inflate(R.layout.gridview_glass,null);
        gridView_glass= (GridView)v_glass.findViewById(R.id.id_gridview_glass);

        View v_hair = getLayoutInflater().inflate(R.layout.gridview_hair,null);
        gridView_hair= (GridView)v_hair.findViewById(R.id.id_gridview_hair);

        View v_eyebrow = getLayoutInflater().inflate(R.layout.gridview_eyebrow,null);
        gridView_eyebrow = (GridView)v_eyebrow.findViewById(R.id.id_gridview_eyebrow);

        for (int i = 0; i <  30; i++) {
            i_face[i] = R.drawable.face_b1 + i;
            i_body[i] = R.drawable.body_b1 + i;
            i_eye[i] = R.drawable.eyes_1+ i;
            i_mouth[i] = R.drawable.mouth_1 + i;
            i_glass[i] = R.drawable.glasses_1 + i;
            i_eyebrow[i] = R.drawable.eyebrows_1 + i;
            i_hair[i] = R.drawable.hairboy1 + i;
        }

        faceGridViewAdapter faceGridViewAdapter = new faceGridViewAdapter(i_face,R.layout.item_gridview_face,R.id.id_iv_grid_face,this);
        gridView_face.setAdapter(faceGridViewAdapter);

        eyeGridViewAdapter eyeGridViewAdapter = new eyeGridViewAdapter(i_eye,R.layout.item_gridview_eye,R.id.id_iv_grid_eye,this);
        gridView_eye.setAdapter(eyeGridViewAdapter);

        hairGridViewAdapter hairGridViewAdapter = new hairGridViewAdapter(i_hair,R.layout.item_gridview_hair,R.id.id_iv_grid_hair,this);
        gridView_hair.setAdapter(hairGridViewAdapter);

        bodyGridViewAdapter bodyGridViewAdapter = new bodyGridViewAdapter(i_body,R.layout.item_gridview_body,R.id.id_iv_grid_body,this);
        gridView_body.setAdapter(bodyGridViewAdapter);





        eyebrowGridViewAdapter eyebrowGridViewAdapter = new eyebrowGridViewAdapter(i_eyebrow,R.layout.item_gridview_eyebrow,R.id.id_iv_grid_eyebrow,this);
        gridView_eyebrow.setAdapter(eyebrowGridViewAdapter);


        glassGridViewAdapter glassGridViewAdapter = new glassGridViewAdapter(i_glass,R.layout.item_gridview_glass,R.id.id_iv_grid_glase,this);
        gridView_glass.setAdapter(glassGridViewAdapter);


        mouthGridViewAdapter mouthGridViewAdapter = new mouthGridViewAdapter(i_mouth,R.layout.item_gridview_mouth,R.id.id_iv_grid_mouth,this);
        gridView_mouth.setAdapter(mouthGridViewAdapter);



        gridViews.add(gridView_face);
        gridViews.add(gridView_eyebrow);
        gridViews.add(gridView_hair);
        gridViews.add(gridView_body);
        gridViews.add(gridView_eye);
        gridViews.add(gridView_glass);
        gridViews.add(gridView_mouth);

        faceupViewPagerAdapter faceupViewPagerAdapter = new faceupViewPagerAdapter(gridViews);
        idviewpagerfaceup.setAdapter(faceupViewPagerAdapter);
        idviewpagerfaceup.setOffscreenPageLimit(7);

        idviewpagerfaceup.addOnPageChangeListener(this);
        idhorizontallistviewfaceup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("-zhangping", position + "");
//                ImageView iv = (ImageView)view.findViewById(R.id.id_iv_horizontal_item);
//                int i = (int)iv.getTag();
//                iv.setImageResource(i_type_selected[i]);
                idviewpagerfaceup.setCurrentItem(position, true);

            }
        });
         setGridViewItemOnClick();

        createPopWindow();

    }

    private void createPopWindow(){
        v_more = getLayoutInflater().inflate(R.layout.more_faceup_right,null);
        popupWindow = new PopupWindow(v_more, 160, 200,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        tv_thisme = (TextView)v_more.findViewById(R.id.id_tv_faceup_me);
        tv_store = (TextView)v_more.findViewById(R.id.id_tv_faceup_store);
        tv_find = (TextView)v_more.findViewById(R.id.id_tv_faceup_find);

        btn_more = (Button)lltitle.findViewById(R.id.title_more);

        btn_more.setOnClickListener(this);
        tv_thisme.setOnClickListener(this);
        tv_find.setOnClickListener(this);
        tv_store.setOnClickListener(this);
    }

    private void setGridViewItemOnClick()
    {
        gridView_body.setOnItemClickListener(this);
        gridView_eye.setOnItemClickListener(this);
        gridView_eyebrow.setOnItemClickListener(this);
        gridView_face.setOnItemClickListener(this);
        gridView_glass.setOnItemClickListener(this);
        gridView_hair.setOnItemClickListener(this);
        gridView_mouth.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == gridView_body)
        {
            idivfaceupbody.setImageResource(i_body[position]);
        }else if (parent == gridView_eye)
        {
            idivfaceupeyes.setImageResource(i_eye[position]);
        }else if (parent == gridView_eyebrow)
        {
            idivfaceupeyebrows.setImageResource(i_eyebrow[position]);
        }else if (parent == gridView_hair)
        {
            idivfaceuphair.setImageResource(i_hair[position]);
        }else if (parent == gridView_mouth)
        {
            idivfaceupmouth.setImageResource(i_mouth[position]);
        }else if (parent == gridView_glass)
        {
            idivfaceupglasses.setImageResource(i_glass[position]);
        }else if (parent == gridView_face)
        {
            idivfaceupface.setImageResource(i_face[position]);
        }
    }

    private void initialize() {

        rl_idivfaceup = (RelativeLayout) findViewById(R.id.id_iv_faceup);
        idhorizontallistviewfaceup = (HorizontalListView) findViewById(R.id.id_horizontallistview_faceup);
        idviewpagerfaceup = (ViewPager) findViewById(R.id.id_viewpager_faceup);


        idivfaceupbody = (ImageView) findViewById(R.id.id_iv_faceup_body);
        idivfaceupface = (ImageView) findViewById(R.id.id_iv_faceup_face);
        idivfaceupmouth = (ImageView) findViewById(R.id.id_iv_faceup_mouth);
        idivfaceupeyes = (ImageView) findViewById(R.id.id_iv_faceup_eyes);
        idivfaceupeyebrows = (ImageView) findViewById(R.id.id_iv_faceup_eyebrows);
        idivfaceuphair = (ImageView) findViewById(R.id.id_iv_faceup_hair);
        idivfaceupglasses = (ImageView) findViewById(R.id.id_iv_faceup_glasses);
        lltitle = (LinearLayout) findViewById(R.id.ll_title);

    }



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
            viewHolder.iv_type.setImageResource(i_type[position]);
            viewHolder.iv_type.setTag(position);

            return convertView;
        }


        private  class viewHolder
        {
            ImageView iv_type;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setSelection(int position) {
        //TODO: implement
        int positionX = position*idhorizontallistviewfaceup.getWidth();
        int maxWidth = idhorizontallistviewfaceup.getChildCount()*idhorizontallistviewfaceup.getWidth();
        if(positionX <=0){
            positionX  = 0;
        }
        if(positionX >maxWidth){
            positionX =maxWidth;
        }
        idhorizontallistviewfaceup.scrollTo(positionX );
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id)
        {
            case R.id.title_more:

                popupWindow.showAsDropDown(view);
                break;
            case R.id.id_tv_faceup_me:
                Toast.makeText(this,"faceup_me",Toast.LENGTH_LONG).show();
                urlStr = MacroClass.URL_PREXI + "head_addNewFigure.action";
                RequestParams params = new RequestParams();


                break;

            case R.id.id_tv_faceup_store:
                Toast.makeText(this,"id_tv_faceup_store",Toast.LENGTH_LONG).show();

                break;
            case R.id.id_tv_faceup_find:
                Toast.makeText(this,"id_tv_faceup_find",Toast.LENGTH_LONG).show();

                break;
        }

    }
}
