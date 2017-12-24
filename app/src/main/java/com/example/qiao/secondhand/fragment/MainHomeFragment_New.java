package com.example.qiao.secondhand.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.GoodsActivity;
import com.example.qiao.secondhand.adapter.NewListViewAdapter;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.RefreshView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainHomeFragment_New extends Fragment {
    public  int index = 0;
    @BindView(R.id.home_new_listview)
    ListView homeNewListview;
    @BindView(R.id.home_new_refreshview)
    RefreshView homeNewRefreshview;
    Unbinder unbinder;

    List<SecondHandGoods> data;
    NewListViewAdapter adapter;


    private static boolean flag=false;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    //关闭下拉刷新

                    try {
                        homeNewRefreshview.setRefreshing(false);
                    }catch (Exception e){

                    }

                    index=0;
                    data=new ArrayList<>();
                    initData();
                    break;
                case 300:
                    initData();
                    //移除加载更多
                    try {
                        homeNewRefreshview.setLoadingState(false);
                    }catch (Exception e){

                    }

                    break;
            }
        }
    };


    //发送网络请求获取数据
    private void initData() {
        RequestParams params=new RequestParams(URL.GETGOODSBYTIME);
        params.addParameter("index",index);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("+++++++++++++++", "onSuccess: "+result );
                if ("null".equals(result)){
                    flag=true;
                    return;

                }else {
                    List<SecondHandGoods> list=new ArrayList<>();
                    Gson gson=new Gson();
                    list= gson.fromJson(result, new TypeToken<List<SecondHandGoods>>() {}.getType());
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                    index++;


                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                    flag=true;
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


    private List<SecondHandGoods> list;


    private View view;

    public MainHomeFragment_New() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_home_fragment__new, container, false);
        unbinder = ButterKnife.bind(this, view);
        data=new ArrayList<>();
        adapter=new NewListViewAdapter(data,getContext());
        homeNewListview.setAdapter(adapter);
        initData();

        //下拉刷新和上拉加载刷新
        initRefreshViewListener();



        //listview点击事件  跳转信息详情界面
        homeNewListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SecondHandGoods goods=data.get(i);
                Intent intent=new Intent(getContext(), GoodsActivity.class);
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
                bundle.getInt("times",goods.getBrowseTimes());
                intent.putExtras(bundle);


                getContext().startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRefreshViewListener() {

        //下拉刷新的监听
        homeNewRefreshview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(200,1000);
            }
        });

        //加载更多的回调
        homeNewRefreshview.setLoadingListner(new RefreshView.OnRefreshLoadingCallBack() {
            @Override
            public void onLoading() {
                handler.sendEmptyMessageDelayed(300,1000);
            }
        });
    }
}
