package com.example.zhangping.facelovestudio.tabfragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.facelovestudio.activity.CampusCenterActivity;
import com.example.zhangping.facelovestudio.activity.DetailInformationActivity;
import com.example.zhangping.facelovestudio.activity.newfriendActivity;
import com.example.zhangping.jsonparseclass.GeneralResultResponse;
import com.example.zhangping.jsonparseclass.UserInfoArrayResponse;
import com.example.zhangping.jsonparseclass.UserInfoResponse;
import com.example.zhangping.utils.MacroClass;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.crypto.Mac;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link contactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link contactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contactFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView lv_contact;

    private TextView tv_newfriend;
    private ImageView iv_newfriend;
    private TextView tv_item;
    private ImageView iv_item;
    private contactAdapter conAdapter;
    private HttpUtils httpUtils;
    private Gson gson;

    private ArrayList<String> arr_id;
    private ArrayList<String> arr_headimage;
    private ArrayList<String> arr_nickname;

    DisplayImageOptions displayImageOptions;

    public contactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static contactFragment newInstance(String param1, String param2) {
        contactFragment fragment = new contactFragment();
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
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration imageLoaderConfiguration = ImageLoaderConfiguration.createDefault(this.getActivity());
        ImageLoader.getInstance().init(imageLoaderConfiguration);


        displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();

        //获取网络请求,得到用户的好友列表
        httpUtils = new HttpUtils();
        gson = new Gson();

        arr_headimage = new ArrayList<String>();
        arr_id = new ArrayList<String>();
        arr_nickname = new ArrayList<String>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        lv_contact = (ListView) v.findViewById(R.id.id_lv_contact);
        TextView tv = (TextView) v.findViewById(R.id.tab_title_text);
        tv.setText("通讯录");
        conAdapter = new contactAdapter();
        lv_contact.setAdapter(conAdapter);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), newfriendActivity.class);
                    startActivity(intent);

                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(), CampusCenterActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    ActionSheet.createBuilder(getActivity(), getFragmentManager()).setCancelButtonTitle("取消")
                            .setOtherButtonTitles("男", "女")
                            .setCancelableOnTouchOutside(true)
                            .setListener(new ActionSheet.ActionSheetListener() {
                                @Override
                                public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                                }

                                @Override
                                public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                                    if (index  == 0)
                                    {
                                        String urlStr = MacroClass.URL_PREXI + "user_modifyUserInfo.action?userid=" +getActivity().getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "") + "&sex=" + "boy";
                                        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
                                            @Override
                                            public void onSuccess(ResponseInfo<String> responseInfo) {
//                        tv_sex.setText("男");
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
//                        tv_sex.setText("女");
                                            }

                                            @Override
                                            public void onFailure(HttpException e, String s) {
                                                Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_LONG);
                                            }
                                        });
                                    }
                                }

                            }).show();
                } else {
                    Intent intent = new Intent(getActivity(), DetailInformationActivity.class);
                    intent.putExtra(MacroClass.USER_ID, arr_id.get(position - 3));
                    startActivity(intent);
                }
            }
        });
        String urlStr = MacroClass.URL_PREXI + "connect_getAllFriendList.action?userid=" + getActivity().getSharedPreferences(MacroClass.SHAREDPREFERENCES, 0).getString(MacroClass.USER_ID, "");


        httpUtils.send(HttpRequest.HttpMethod.GET, urlStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                UserInfoArrayResponse userInfoArrayResponse = gson.fromJson(responseInfo.result, UserInfoArrayResponse.class);
                if (userInfoArrayResponse.getResult().equals(MacroClass.SUCCESS)) {
                    UserInfoResponse[] userInfoResponses = userInfoArrayResponse.getModul();
                    for (UserInfoResponse u :
                            userInfoResponses) {
                        arr_nickname.add(u.getNickName());
                        arr_id.add(u.getUserId());
                        arr_headimage.add(MacroClass.headUrl(u.getHeadimage()));
                    }
                    conAdapter.notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
        return v;
    }

    public class contactAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;
            if (null == convertView) {
                holder = new Holder();
                convertView = View.inflate(getActivity(), R.layout.item_contact, null);
                holder.holder_iv_friend = (ImageView) convertView.findViewById(R.id.id_iv_contact_newfriend);
                holder.holder_tv_friend = (TextView) convertView.findViewById(R.id.id_tv_contact_newfriend);

                holder.holder_iv_item = (ImageView) convertView.findViewById(R.id.id_iv_contact_item);
                holder.holder_tv_item = (TextView) convertView.findViewById(R.id.id_tv_contact_item);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            if (position == 0) {
                holder.holder_tv_friend.setText("新的朋友");
                holder.holder_iv_friend.setImageResource(R.drawable.tnewfriend);
                holder.holder_iv_item.setVisibility(View.INVISIBLE);
                holder.holder_tv_item.setVisibility(View.INVISIBLE);

                holder.holder_iv_friend.setVisibility(View.VISIBLE);
                holder.holder_tv_friend.setVisibility(View.VISIBLE);
            } else if (position == 1) {
                holder.holder_tv_friend.setText("校园中心");
                holder.holder_iv_friend.setImageResource(R.drawable.tschool);
                holder.holder_iv_item.setVisibility(View.INVISIBLE);
                holder.holder_tv_item.setVisibility(View.INVISIBLE);
                holder.holder_iv_friend.setVisibility(View.VISIBLE);
                holder.holder_tv_friend.setVisibility(View.VISIBLE);

            } else if (position == 2) {
                holder.holder_tv_friend.setText("添加好友");
                holder.holder_iv_friend.setImageResource(R.drawable.taddfriends);
                holder.holder_iv_item.setVisibility(View.INVISIBLE);
                holder.holder_tv_item.setVisibility(View.INVISIBLE);
                holder.holder_iv_friend.setVisibility(View.VISIBLE);
                holder.holder_tv_friend.setVisibility(View.VISIBLE);

            } else {
                holder.holder_tv_friend.setVisibility(View.INVISIBLE);
                holder.holder_iv_friend.setVisibility(View.INVISIBLE);

                holder.holder_iv_item.setVisibility(View.VISIBLE);
                holder.holder_tv_item.setVisibility(View.VISIBLE);

                holder.holder_tv_item.setText(arr_nickname.get(position - 3));


                ImageLoader.getInstance().displayImage(arr_headimage.get(position - 3), holder.holder_iv_item, displayImageOptions);


            }

            return convertView;
        }

        @Override
        public int getCount() {
            if (arr_id == null) {
                return 3;
            } else {
                return arr_id.size() + 3;

            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    class Holder {
        public TextView holder_tv_friend;
        public ImageView holder_iv_friend;

        public TextView holder_tv_item;
        public ImageView holder_iv_item;

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




}
