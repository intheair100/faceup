package com.example.zhangping.facelovestudio.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.zhangping.facelovestudio.R;

import java.util.ArrayList;
import java.util.List;
import com.example.zhangping.facelovestudio.tabfragment.*;
public class tabActivity extends FragmentActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list = new ArrayList<Fragment>();

    private LinearLayout ll_faceup;
    private LinearLayout ll_chat;
    private LinearLayout ll_contact;
    private LinearLayout ll_me;

    private ImageButton ib_faceup;
    private ImageButton ib_chat;
    private  ImageButton ib_contact;
    private ImageButton ib_me;

    private TextView tv_faceup;
    private TextView tv_chat;
    private TextView tv_contact;
    private TextView tv_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
        viewPager = (ViewPager)findViewById(R.id.id_vp);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };

        ib_faceup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTabBtn();
                ib_faceup.setImageResource(R.drawable.home_on);
                tv_faceup.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                viewPager.setCurrentItem(0);

            }
        });

        ib_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTabBtn();
                ib_chat.setImageResource(R.drawable.chating_on);
                tv_chat.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                viewPager.setCurrentItem(1);

            }
        });

        ib_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTabBtn();
                ib_contact.setImageResource(R.drawable.lists_on);
                tv_contact.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                viewPager.setCurrentItem(2);

            }
        });

        ib_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTabBtn();
                ib_me.setImageResource(R.drawable.me_on);
                tv_me.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                viewPager.setCurrentItem(3);

            }
        });

        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private  int currentIndex;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                resetTabBtn();
                switch (position)
                {
                    case 0:
                        ib_faceup.setImageResource(R.drawable.home_on);
                        tv_faceup.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));
                        break;
                    case 1:
                        ib_chat.setImageResource(R.drawable.chating_on);
                        tv_chat.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                        break;
                    case 2:
                        ib_contact.setImageResource(R.drawable.lists_on);
                        tv_contact.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                        break;
                    case 3:
                        ib_me.setImageResource(R.drawable.me_on);
                        tv_me.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_pressed_color));

                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    protected void resetTabBtn(){
        ib_faceup.setImageResource(R.drawable.home);
        ib_chat.setImageResource(R.drawable.chating);
        ib_contact.setImageResource(R.drawable.lists);
        ib_me.setImageResource(R.drawable.me);

        tv_faceup.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_unpressed_color));
        tv_chat.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_unpressed_color));
        tv_contact.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_unpressed_color));
        tv_me.setTextColor(getBaseContext().getResources().getColorStateList(R.color.tab_unpressed_color));


    }
    public void initView(){
        ll_faceup = (LinearLayout)findViewById(R.id.id_tab_bottom_faceup);
        ll_chat = (LinearLayout)findViewById(R.id.id_tab_bottom_chat);
        ll_contact = (LinearLayout)findViewById(R.id.id_tab_bottom_contact);
        ll_me = (LinearLayout)findViewById(R.id.id_tab_bottom_me);


        ib_faceup = ((ImageButton)ll_faceup.findViewById(R.id.id_btn_faceup));
        tv_faceup = (TextView)ll_faceup.findViewById(R.id.id_tv_faceup);

        ib_chat = ((ImageButton)ll_chat.findViewById(R.id.id_btn_chat));
        tv_chat = (TextView)ll_chat.findViewById(R.id.id_tv_chat);

        ib_contact = ((ImageButton)ll_contact.findViewById(R.id.id_btn_contact));
        tv_contact = (TextView)ll_contact.findViewById(R.id.id_tv_contact);

        ib_me = ((ImageButton)ll_me.findViewById(R.id.id_btn_me));
        tv_me = (TextView)ll_me.findViewById(R.id.id_tv_me);


        faceupFragment faceup = new faceupFragment();
        chatFragment chat = new chatFragment();
        contactFragment contact = new contactFragment();
        meFragment me = new meFragment();

        list.add(faceup);
        list.add(chat);
        list.add(contact);
        list.add(me);
    }
}
