package com.example.qiao.secondhand.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainHomeFragment extends Fragment {


    @BindView(R.id.home_fragment_tablayout)
    TabLayout homeFragmentTablayout;
    @BindView(R.id.home_fragment_viewpager)
    ViewPager homeFragmentViewpager;
    Unbinder unbinder;

    private List<Fragment> fragmentList;
    private List<String> nameList;
    private View view;

    public MainHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_home, container, false);
        unbinder = ButterKnife.bind(this, view);


        initList();


        //新建adapter  给viwepager设置adapter
        CommonAdapter viewPageradapter=new CommonAdapter(getChildFragmentManager(),fragmentList,nameList);
        homeFragmentViewpager.setAdapter(viewPageradapter);

        //设置标签栏模式
        homeFragmentTablayout.setTabMode(TabLayout.MODE_FIXED);
        //viewpager联动
        homeFragmentTablayout.setupWithViewPager(homeFragmentViewpager);
        setViewPagerListener();
        return view;
    }

    private void setViewPagerListener() {
        //viewPager联动tabLayout
        homeFragmentViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeFragmentTablayout));

        homeFragmentTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //第二个参数： 是否平稳的滚动
                homeFragmentViewpager.setCurrentItem(position,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initList() {
        nameList=new ArrayList<>();
        nameList.add("推荐商品");
        nameList.add("最新发布");

        fragmentList=new ArrayList<>();
        MainHomeFragment_New newFragment=new MainHomeFragment_New();
        MainHomeFragment_Recommend recommendFragment=new MainHomeFragment_Recommend();

        fragmentList.add(recommendFragment);
        fragmentList.add(newFragment);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
