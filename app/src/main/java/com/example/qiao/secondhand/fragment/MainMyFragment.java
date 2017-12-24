package com.example.qiao.secondhand.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.GoodsListActivity;
import com.example.qiao.secondhand.activity.LoginActivity;
import com.example.qiao.secondhand.activity.RegisterActivity;
import com.example.qiao.secondhand.util.LoginUtil;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainMyFragment extends Fragment {



    private static CircleImageView mainMyAvatarImage;

    private static TextView aminMyNameText;
    private static TextView mainMySignature;
    @BindView(R.id.main_my_login_image)
    ImageView mainMyLoginImage;
    @BindView(R.id.main_my_relative_1)
    RelativeLayout mainMyRelative1;
    @BindView(R.id.main_my_order_image)
    ImageView mainMyOrderImage;
    @BindView(R.id.main_my_release_image)
    ImageView mainMyReleaseImage;
    @BindView(R.id.main_my_info_image)
    ImageView mainMyInfoImage;
    @BindView(R.id.main_my_linearlayout)
    LinearLayout mainMyLinearlayout;
    Unbinder unbinder;

    private static TextView takeoffImage;
    private static ImageView main_my_login_image;
    private View view;
    private static SharedPreferences preferences;
    private static String userName;
    private static int userid;
    private static  Context context;


    public MainMyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_my, container, false);
        unbinder = ButterKnife.bind(this, view);

        context=getContext();

        preferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);


        //初始化view
        initView();

        //判断是否有登录缓存
        boolean flag = LoginUtil.isLogin(getContext());
        if (flag) {
            showLogin();

        } else {
            notLogin();
        }


        //給view设置监听
        setClickListener();


        return view;
    }


    private void setClickListener() {

        //登录按钮监听
        main_my_login_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        //我的收藏
        mainMyOrderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //判断是否有登录缓存
                boolean flag = LoginUtil.isLogin(getContext());
                if (!flag) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), GoodsListActivity.class);
                intent.putExtra("type", "我的收藏");
                int usetid = preferences.getInt("userID", 3);
                intent.putExtra("issearch", 2);
                startActivity(intent);
            }
        });
        //我的发布
        mainMyReleaseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否有登录缓存
                boolean flag = LoginUtil.isLogin(getContext());
                if (!flag) {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getContext(), GoodsListActivity.class);
                intent.putExtra("type", "我的发布");
                int usetid = preferences.getInt("userID", 3);
                intent.putExtra("issearch", 3);
                startActivity(intent);
            }
        });

        //退出登录
        takeoffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.commit();
                notLogin();
            }
        });

        //修改个人信息
        mainMyInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), RegisterActivity.class);
                intent.putExtra("flag",2);
                startActivity(intent);

            }
        });


    }

    private void initView() {
        main_my_login_image = (ImageView) view.findViewById(R.id.main_my_login_image);
        mainMyAvatarImage= (CircleImageView) view.findViewById(R.id.main_my_avatar_image);
        aminMyNameText= (TextView) view.findViewById(R.id.amin_my_name_text);
        mainMySignature= (TextView) view.findViewById(R.id.main_my_signature);
        takeoffImage= (TextView) view.findViewById(R.id.takeoff_image);
    }


    //登录状态显示
    public static void showLogin() {
        aminMyNameText.setVisibility(View.VISIBLE);
        mainMySignature.setVisibility(View.VISIBLE);
        main_my_login_image.setVisibility(View.GONE);
        //取姓名
        userName = preferences.getString("userName", "");

        userid = preferences.getInt("userID", 3);
        aminMyNameText.setText(userName);

        String signature = preferences.getString("userSignature", "");

        String image = preferences.getString("userImage", "");

        mainMySignature.setText(signature);

        Picasso.with(context).load(URL.IMAGE + image).error(R.mipmap.main_my_defaultavatar).into(mainMyAvatarImage);
        takeoffImage.setVisibility(View.VISIBLE);


    }

    //非登录状态
    public void notLogin() {
        aminMyNameText.setVisibility(View.GONE);
        mainMySignature.setVisibility(View.GONE);
        main_my_login_image.setVisibility(View.VISIBLE);
        takeoffImage.setVisibility(View.GONE);
        Picasso.with(context).load(URL.IMAGE + "defaultavatar.png").error(R.mipmap.main_my_defaultavatar).into(mainMyAvatarImage);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if ("login".equals(intent.getAction())) {

                showLogin();

            }
            ;
        }
    }


}
