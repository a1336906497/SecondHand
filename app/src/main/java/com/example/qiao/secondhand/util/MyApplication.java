package com.example.qiao.secondhand.util;

import android.app.Application;

import org.xutils.x;

/**
 * Created by qiao on 2017/4/15.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        x.Ext.init(this);
        super.onCreate();

    }


}
