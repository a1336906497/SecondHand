package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.widget.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiao on 2017/5/15.
 */

public class RecommendListViewAdapter extends BaseAdapter {
    private List<List<SecondHandGoods>> data;

    private Context context;

    public RecommendListViewAdapter(List<List<SecondHandGoods>> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.fragment_main_home_recommend_listview_item, viewGroup, false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();

        }

        RecommendGridViewAdapter adapter=null;

        switch (i){
            case 0:
                holder.textRecommendIshasmore.setText("更多>");
                holder.textRecommendMainTitle.setText("最新上架");
                break;
            case 1:
                holder.textRecommendIshasmore.setText("更多>");
                holder.textRecommendMainTitle.setText("浏览最多");
                break;
            case 2:
                holder.textRecommendIshasmore.setText("更多>");
                holder.textRecommendMainTitle.setText("强烈推荐");
                break;

        }

        adapter=new RecommendGridViewAdapter(data.get(i),context);
        holder.homeRecommendListviewGridview.setAdapter(adapter);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_recommend_main_title)
        TextView textRecommendMainTitle;
        @BindView(R.id.text_recommend_ishasmore)
        TextView textRecommendIshasmore;
        @BindView(R.id.home_recommend_listview_gridview)
        MyGridView homeRecommendListviewGridview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
