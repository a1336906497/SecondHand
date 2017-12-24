package com.example.qiao.secondhand.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.adapter.RecommendListViewAdapter;
import com.example.qiao.secondhand.adapter.RecommendViewPagerAdapter;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.util.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment_Recommend extends Fragment {
    @BindView(R.id.home_recommend_listView)
    ListView homeRecommendListView;
    Unbinder unbinder;
    private List<List<SecondHandGoods>> data;
    private RecommendListViewAdapter adapter;

    private View headView;
    private ViewPager viewPager;
    private List<String> viewPagerData;
    private TextView textView;
    private int index;


    public MainHomeFragment_Recommend() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_home_fragment__recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        data=new ArrayList<>();


        //初始化头视图
        initHeadView();


        RequestParams params=new RequestParams(URL.GETREOMMENDINFO);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){
                    Gson gson=new Gson();
                    data=gson.fromJson(result,new TypeToken<List<List<SecondHandGoods>>>() {}.getType());
                    adapter=new RecommendListViewAdapter(data,getContext());
                    homeRecommendListView.setAdapter(adapter);

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return view;
    }

    private void initHeadView() {
        viewPagerData=new ArrayList<>();
        viewPagerData.add("上架物品");
        viewPagerData.add("山东科技大学官网");
        headView=LayoutInflater.from(getContext()).inflate(R.layout.fragment_main_home_recommend_headview,null);
        viewPager = (ViewPager) headView.findViewById(R.id.home_recommend_listView_head_viewpager);
        textView = (TextView) headView.findViewById(R.id.headview_text);

        RecommendViewPagerAdapter adapter=new RecommendViewPagerAdapter(viewPagerData,getContext());
        viewPager.setAdapter(adapter);

        //初始化RadioGroup
        final RadioGroup radioGroup= (RadioGroup) headView.findViewById(R.id.home_recommend_listView_radiogroup);
        for (int i = 0; i <2; i++) {
            RadioButton radioButton=new RadioButton(getContext());
            radioButton.setEnabled(false);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setButtonDrawable(getContext().getResources().getDrawable(R.drawable.viewpager_radiobutton));
            radioGroup.addView(radioButton);
        }



        //滑动监听 RadioGroup联动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(positionOffset==0){
                    //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                    if(position==0){
                        viewPager.setCurrentItem(viewPagerData.size(),false);
                    }else if(position==viewPagerData.size()+1){
                        viewPager.setCurrentItem(1,false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){

                }else if(position==viewPagerData.size()+1){

                }else {
                    if (position==1) {
                        textView.setText("山东科技大学");
                    }else if(position==2){
                        textView.setText("上架物品");
                    }
                    ((RadioButton)(radioGroup.getChildAt(position-1))).setChecked(true);
                }
                index=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(1);
        homeRecommendListView.addHeaderView(headView);
        index=1;




        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(index ==0){
                    index =1;
                }else if(index ==data.size()+1){
                    index =1;
                }
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(index);
                    }
                });

                index++;
            }
        },2000,2000);

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
