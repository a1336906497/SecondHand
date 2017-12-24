package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.util.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiao on 2017/5/15.
 */

public class NewListViewAdapter extends BaseAdapter {
    private List<SecondHandGoods> list;

    private Context context;

    public NewListViewAdapter(List<SecondHandGoods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_main_home_new_listview_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {

            holder= (ViewHolder) view.getTag();
        }
        SecondHandGoods goods=list.get(i);
        holder.homeNewListviewDate.setText(goods.getGoodsInTime());
        holder.homeNewListviewPrice.setText("￥"+goods.getGoodsPriceShop());
        holder.homeNewListviewwName.setText(goods.getGoodsName());
        holder.homeNewListviewPlace.setText(goods.getTradingLocation());

        Picasso.with(context).load(URL.IMAGE+goods.getGoodsPicSmall()).error(R.mipmap.ic_empty).into(holder.homeNewListviewImage);



        return view;
    }

    static class ViewHolder {
        @BindView(R.id.home_new_listview_image)
        ImageView homeNewListviewImage;
        @BindView(R.id.home_new_listview_place)
        TextView homeNewListviewPlace;
        @BindView(R.id.home_new_listvieww_name)
        TextView homeNewListviewwName;
        @BindView(R.id.home_new_listview_price)
        TextView homeNewListviewPrice;
        @BindView(R.id.home_new_listview_date)
        TextView homeNewListviewDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
