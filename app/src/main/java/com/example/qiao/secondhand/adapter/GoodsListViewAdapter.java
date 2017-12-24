package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.SecondHandCommend;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiao on 2017/5/16.
 */

public class GoodsListViewAdapter extends BaseAdapter {
    private List<SecondHandCommend> data;
    private Context context;

    public GoodsListViewAdapter(List<SecondHandCommend> data, Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.goods_commend_listview_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        SecondHandCommend commend=data.get(i);

        holder.goodsListviewName.setText(commend.getUser().getUserName());
        holder.goodsListviewNumber.setText(i+1+"#");
        holder.goodsListviewText.setText(commend.getComment());
        Picasso.with(context).load(URL.IMAGE+commend.getUser().getUserImage()).into(holder.goodsListviewImage);


        return view;
    }

    static class ViewHolder {
        @BindView(R.id.goods_listview_image)
        CircleImageView goodsListviewImage;
        @BindView(R.id.goods_listview_name)
        TextView goodsListviewName;
        @BindView(R.id.goods_listview_text)
        TextView goodsListviewText;
        @BindView(R.id.goods_listview_number)
        TextView goodsListviewNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
