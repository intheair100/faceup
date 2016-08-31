package com.example.zhangping.facelovestudio.activity;

 import android.content.SharedPreferences;
 import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
 import android.util.Log;
 import android.view.LayoutInflater;
import android.view.View;
 import android.view.Window;
 import android.widget.ArrayAdapter;
 import android.widget.EditText;
 import android.widget.LinearLayout;
 import android.widget.ListView;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;

import android.widget.TableLayout;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.example.zhangping.facelovestudio.Base.BaseActivity;
 import com.example.zhangping.facelovestudio.R;

 import java.util.ArrayList;
import java.util.List;

public class registerinfoactivity extends BaseActivity implements SearchView.OnQueryTextListener{

    private  SearchView searchView;
    private EditText profess_edit;
    private EditText password_edit;
    private ListView listView;
    LinearLayout relativeLayout;
    Object[] schools;
    private View layoutInflaterView;
    ArrayList<String> mAllList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerinfoactivity);
        TextView tv = (TextView)findViewById(R.id.title_text);
        tv.setText("注册");

        searchView =(SearchView)findViewById(R.id.search_view);
        relativeLayout = (LinearLayout)findViewById(R.id.registerinfo);
        profess_edit = (EditText)findViewById(R.id.profess_edit );
        password_edit = (EditText)findViewById(R.id.profess_edit );

        schools= loadData();
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        layoutInflaterView = layoutInflater.inflate(R.layout.schoollist,null);
        listView= (ListView)layoutInflaterView.findViewById(R.id.school_list);
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(getApplicationContext(),android.R.layout.simple_list_item_1,schools);
        listView.setAdapter(arrayAdapter);
        listView.setTextFilterEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        relativeLayout.addView(layoutInflaterView);
        layoutInflaterView.setVisibility(View.INVISIBLE);

//        //自定义actionBar
//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.title);
//        actionBar.getCustomView().findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//
//            }
//        });
//        actionBar.getCustomView().findViewById(R.id.title_next).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences sharedPreferences = getSharedPreferences("setting",0);
//                final  SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("school",searchView.getQuery().toString());
//                editor.putString("profess",profess_edit.getText().toString());
//                editor.putString("password",password_edit.getText().toString());
//            }
//        });
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(TextUtils.isEmpty(s)){
            listView.clearTextFilter();
            layoutInflaterView.setVisibility(View.INVISIBLE);
        } else{
            listView.setFilterText(s.toString());
            layoutInflaterView.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    public Object[] loadData() {
        mAllList.add("aa");
        mAllList.add("ddfa");
        mAllList.add("qw");
        mAllList.add("sd");
        mAllList.add("fd");
        mAllList.add("cf");
        mAllList.add("re");
        return mAllList.toArray();
    }
}
