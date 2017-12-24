package com.example.qiao.secondhand.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by qiao on 2017/5/12.
 */

public class CommonAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> nameList;

    public CommonAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list=list;
    }
    public CommonAdapter(FragmentManager fm, List<Fragment> list, List<String> nameList) {
        super(fm);
        this.list=list;
        this.nameList=nameList;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (nameList!=null){
            return nameList.get(position);
        }else {
            return super.getPageTitle(position);
        }
    }
}
