package com.example.qiao.secondhand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.ChatBean;
import com.example.qiao.secondhand.bean.SecondHandCommend;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiao on 2017/5/24.
 */

public class ChatListViewAdapter extends BaseAdapter {
    private List<ChatBean> data;
    private Context context;

    public ChatListViewAdapter(List<ChatBean> data, Context context) {
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

        ChatBean bean=data.get(i);
        if (bean.getType()==0){
            view = LayoutInflater.from(context).inflate(R.layout.chatlist_item1, viewGroup, false);
            TextView textView= (TextView) view.findViewById(R.id.chat_list_item1_text);
            CircleImageView imageView= (CircleImageView) view.findViewById(R.id.chat_item1_image);

            textView.setText(bean.getText());
            Picasso.with(context).load(URL.IMAGE+bean.getImage()).error(R.mipmap.main_my_defaultavatar) .into(imageView);
        }else if (bean.getType()==1){
            view = LayoutInflater.from(context).inflate(R.layout.chatlist_item2, viewGroup, false);
            TextView textView= (TextView) view.findViewById(R.id.chat_list_item2_text);
            CircleImageView imageView= (CircleImageView) view.findViewById(R.id.chat_item2_image);

            textView.setText(bean.getText());
            Picasso.with(context).load(URL.IMAGE+bean.getImage()).error(R.mipmap.main_my_defaultavatar) .into(imageView);
        }

        return view;
    }


}
