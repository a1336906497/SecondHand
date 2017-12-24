package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.GoodsActivity;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.util.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiao on 2017/5/15.
 */

public class RecommendGridViewAdapter extends BaseAdapter {
    private List<SecondHandGoods> data;
    private Context context;

    public RecommendGridViewAdapter(List<SecondHandGoods> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder=null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_main_home_recommend_gridview_item, viewGroup, false);
            holder=new ViewHolder(view);

            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        final SecondHandGoods goods=data.get(i);
        holder.homeRecommendGridviewName.setText(goods.getGoodsName());
        holder.homeRecommendGridviewPlace.setText(goods.getTradingLocation());
        holder.homeRecommendGridviewPrice.setText("￥"+goods.getGoodsPriceShop());

        Picasso.with(context).load(URL.IMAGE+goods.getGoodsPicSmall()).error(R.mipmap.ic_empty).into(holder.homeRecommendGridviewImage);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GoodsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id",goods.getGoodsID());
                bundle.putString("conent",goods.getGoodsConent());
                bundle.putString("name",goods.getGoodsName());
                bundle.putString("images",goods.getGoodsPicLarge());
                bundle.putString("text",goods.getGoodsConent());
                bundle.putString("price",goods.getGoodsPriceShop());
                bundle.putString("phone",goods.getUser().getUserMobile());
                bundle.putString("userimage",goods.getUser().getUserImage());
                bundle.putString("username",goods.getUser().getUserName());
                bundle.putInt("userid",goods.getUser().getUserID());
                bundle.putString("usercollege",goods.getUser().getCollegeID().getCollegeDesc());
                bundle.putString("time",goods.getGoodsInTime());
                bundle.putString("place",goods.getTradingLocation());
                bundle.putInt("times",goods.getBrowseTimes());
                Log.e("+++++++++++++++++", "onClick: "+goods.getBrowseTimes() );
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.home_recommend_gridview_image)
        ImageView homeRecommendGridviewImage;
        @BindView(R.id.home_recommend_gridview_name)
        TextView homeRecommendGridviewName;
        @BindView(R.id.home_recommend_gridview_place)
        TextView homeRecommendGridviewPlace;
        @BindView(R.id.home_recommend_gridview_price)
        TextView homeRecommendGridviewPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
