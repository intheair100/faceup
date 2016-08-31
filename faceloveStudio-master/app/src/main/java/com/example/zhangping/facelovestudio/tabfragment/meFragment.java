package com.example.zhangping.facelovestudio.tabfragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.example.zhangping.facelovestudio.Base.AppManager;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.facelovestudio.activity.AboutusActivity;
import com.example.zhangping.facelovestudio.activity.modifyNicknameActivity;
import com.example.zhangping.facelovestudio.activity.modifyPersonSginActivity;
import com.example.zhangping.facelovestudio.activity.modifySchoolActivity;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.jsonparseclass.UserInfoResponse;
import com.example.zhangping.utils.MacroClass;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.FileNotFoundException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link meFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link meFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class meFragment extends Fragment implements View.OnClickListener ,ActionSheet.ActionSheetListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView iv_head;
    private TextView tv_nickname;
    private TextView tv_school;
    private TextView tv_sex;
    private TextView tv_personsgin;
    private TextView tv_logout;


    private LinearLayout ll_me_nickname;
    private LinearLayout ll_me_sex;
    private LinearLayout ll_me_school;
    private LinearLayout ll_me_about;
    private LinearLayout ll_me_personsgin;
    private LinearLayout ll_me_head;
    private LinearLayout ll_me_logout;

    final int TAKE_PICTURE = 0;
    final int TAKE_ALBUM = 1;
    final  int GET_NICKNAME = 2;
    final int GET_SCHOOL = 3;
    final int GET_SEX = 4;
    final int GET_SGIN = 5;

    private HttpUtils httpUtils;
    private OnFragmentInteractionListener mListener;

    public meFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment meFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static meFragment newInstance(String param1, String param2) {
        meFragment fragment = new meFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me,container,false);
        TextView tv_title = (TextView)v.findViewById(R.id.tab_title_text);
        //代码中使用资源文件
        tv_title.setText(this.getResources().getString(R.string.tab_me_title));

        tv_nickname = (TextView)v.findViewById(R.id.id_tv_me_nickname_change);
        tv_school = (TextView)v.findViewById(R.id.id_tv_me_school_change);
        tv_sex = (TextView)v.findViewById(R.id.id_tv_me_sex_change);
        tv_personsgin = (TextView)v.findViewById(R.id.id_tv_me_personsgin_change);
        tv_logout = (TextView)v.findViewById(R.id.logout);
        iv_head  = (ImageView)v.findViewById(R.id.id_iv_me_head_change);

        ll_me_nickname = (LinearLayout)v.findViewById(R.id.ll_me_nickname);
        ll_me_about = (LinearLayout)v.findViewById(R.id.ll_me_about);
        ll_me_head = (LinearLayout)v.findViewById(R.id.ll_me_head);
        ll_me_school = (LinearLayout)v.findViewById(R.id.ll_me_school);
        ll_me_sex = (LinearLayout)v.findViewById(R.id.ll_me_sex);
        ll_me_personsgin = (LinearLayout)v.findViewById(R.id.ll_me_personsgin);
        ll_me_logout = (LinearLayout)v.findViewById(R.id.ll_me_logout);

        //右边的箭头按钮点击事件
        ll_me_nickname.setOnClickListener(this);
        ll_me_about.setOnClickListener(this);
        ll_me_head.setOnClickListener(this);
        ll_me_school.setOnClickListener(this);
        ll_me_sex.setOnClickListener(this);
        ll_me_personsgin.setOnClickListener(this);
        ll_me_logout.setOnClickListener(this);

        // Inflate the layout for this fragment

        tv_logout.setOnClickListener(this);
        httpUtils = new HttpUtils();
        String urlStr = MacroClass.URL_PREXI + "user_getUserDetailById.action?userid=" +getActivity().getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "");
        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson  gson = new Gson();
                GeneralResultResponse generalResultResponse = gson.fromJson(responseInfo.result, GeneralResultResponse.class);
                String str  = (MacroClass.headUrl(generalResultResponse.getModul().getHeadimage()));
                ContentResolver contentResolver = getActivity().getContentResolver();
                Bitmap bm = null;
                try {
                    bm = BitmapFactory.decodeStream(contentResolver.openInputStream(Uri.parse(str)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                iv_head.setImageBitmap(bm);
                tv_nickname.setText(generalResultResponse.getModul().getNickname());
                tv_personsgin.setText(generalResultResponse.getModul().getSignature());
                tv_school.setText(generalResultResponse.getModul().getSchool());
                if (generalResultResponse.getModul().getSexy().equals("girl"))
                {
                    tv_sex.setText("女");
                }
                else
                {
                    tv_sex.setText("男");
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case GET_NICKNAME:
                tv_nickname.setText(data.getStringExtra(MacroClass.NICK_NAME));
                break;
            case GET_SCHOOL:
                tv_school.setText(data.getStringExtra(MacroClass.SCHOOL));
                break;
            case GET_SEX:
                tv_sex.setText(data.getStringExtra(MacroClass.SEX));
                break;
            case GET_SGIN:
                tv_personsgin.setText(data.getStringExtra(MacroClass.SGIN));
                break;
            case TAKE_ALBUM:
                 Uri uri = data.getData();
                ContentResolver contentResolver = getActivity().getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                    iv_head.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case TAKE_PICTURE:
                Bitmap bm = (Bitmap)data.getExtras().get("data");
                iv_head.setImageBitmap(bm);

                break;
        }
    }

    //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ll_me_head:
                ActionSheet.createBuilder(getActivity(),getFragmentManager()).setCancelButtonTitle("取消")
                        .setOtherButtonTitles("拍照","相机")
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).setTag(MacroClass.HEAD_URL).show();
                break;
            case R.id.ll_me_nickname:
                Intent intent = new Intent(getActivity(),modifyNicknameActivity.class);
                startActivityForResult(intent,GET_NICKNAME);
                break;
            case R.id.ll_me_school:
                intent = new Intent(getActivity(), modifySchoolActivity.class);
                startActivityForResult(intent,GET_SCHOOL);
                break;
            case R.id.ll_me_sex:
                ActionSheet.createBuilder(getActivity(),getFragmentManager()).setCancelButtonTitle("取消")
                        .setOtherButtonTitles("男", "女")
                        .setCancelableOnTouchOutside(true)
                        .setListener(this).setTag(MacroClass.SEX).show();
                break;
            case R.id.ll_me_personsgin:
                intent = new Intent(getActivity(),modifyPersonSginActivity.class);
                startActivityForResult(intent,GET_SGIN);
                break;
            case R.id.ll_me_about:
                intent = new Intent(getActivity(), AboutusActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_me_logout:
                AppManager.getAppManager().AppExit(getActivity());

                break;

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (actionSheet.getTag().equals(MacroClass.SEX))
        {
            if (index  == 0)
            {
                String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" +getActivity().getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&sex=" + "boy";
                httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        tv_sex.setText("男");
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
            }
            else if(index  == 1)
            {
                String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" +getActivity().getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&sex=" + "girl";
                httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        tv_sex.setText("女");
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_LONG);
                    }
                });
            }
        }
        else if (actionSheet.getTag().equals(MacroClass.HEAD_URL))
        {
            if (index  == 0)
            {
                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
            }
            else if(index  == 1)
            {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, TAKE_ALBUM);
            }
        }

    }

    public void getUserInfo(){

    }
}
