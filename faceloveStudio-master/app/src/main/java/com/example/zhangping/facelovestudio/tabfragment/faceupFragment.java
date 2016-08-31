package com.example.zhangping.facelovestudio.tabfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zhangping.facelovestudio.R;
import com.example.zhangping.facelovestudio.activity.XingXiangActivity;
import com.example.zhangping.facelovestudio.faceup.girlFaceupActivity;
import com.example.zhangping.facelovestudio.faceup.boyFaceupActivity;
import com.example.zhangping.utils.MacroClass;
import com.example.zhangping.utils.SPUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link faceupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link faceupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class faceupFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ImageView ibtn_head;
    private ImageView ibtn_boyhead;
    private ImageView ibtn_girlhead;
    public faceupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment faceupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static faceupFragment newInstance(String param1, String param2) {
        faceupFragment fragment = new faceupFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faceup, container, false);
        ibtn_head = (ImageView)view.findViewById(R.id.id_ibtn_head);
        String url = (String )SPUtils.get(this.getActivity(), MacroClass.HEAD_URL,"");
        url = MacroClass.headUrl(url);

        ibtn_boyhead = (ImageView)view.findViewById(R.id.id_ibtn_boyhead);
        ibtn_girlhead = (ImageView)view.findViewById(R.id.id_ibtn_girlhead);

        ibtn_head.setOnClickListener(this);
        ibtn_girlhead.setOnClickListener(this);
        ibtn_boyhead.setOnClickListener(this);

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration imageLoaderConfiguration = ImageLoaderConfiguration.createDefault(this.getActivity());
        ImageLoader.getInstance().init(imageLoaderConfiguration);


        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.lists)
                .showImageOnFail(R.drawable.lists_on)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();

        ImageLoader.getInstance().displayImage(url, ibtn_head, displayImageOptions);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    public void onClick(View view) {
        int viewId = view.getId();
        Intent intent;

        switch (viewId)
        {
            case R.id.id_ibtn_head:
                intent = new Intent(getActivity(),XingXiangActivity.class);
                startActivity(intent);
                break;
            case R.id.id_ibtn_boyhead:
                intent = new Intent(getActivity(),boyFaceupActivity.class);
                startActivity(intent);
                break;
            case R.id.id_ibtn_girlhead:

                break;
            default:
                break;
        }
    }
}
