package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.WebActivity;
import com.example.qiao.secondhand.util.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by qiao on 2017/5/16.
 */

public class GoodsViewPagerAdapter extends PagerAdapter {
    private List<String> data;
    private Context context;

    public GoodsViewPagerAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Picasso.with(context).load(URL.IMAGE+data.get(position)).error(R.mipmap.ic_empty).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
