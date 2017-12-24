package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qiao.secondhand.MainActivity;
import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.WebActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by qiao on 2017/5/15.
 */

public class RecommendViewPagerAdapter extends PagerAdapter {
    private List<String> data;
    private Context context;

    public RecommendViewPagerAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if(position==1||position==3){
            imageView.setBackgroundResource(R.mipmap.main_pic);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击跳转页面
                    Intent intent=new Intent(context, WebActivity.class);
                    context.startActivity(intent);
                }
            });
        }else if (position ==0||position==2) {
            //  Log.e("+++++++++", "instantiateItem: "+data.get(0).getCover() );
            imageView.setBackgroundResource(R.mipmap.update);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.setReleaseFragment();
                }
            });

        }


        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return data!=null?(data.size()+2):0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
