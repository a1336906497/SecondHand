package com.example.qiao.secondhand.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.qiao.secondhand.R;


/**
 * Created by qiao on 2017/5/12.
 * 1,主要的方法 判断是否到底部list.setOnScrollListener()
 *
 * 2,提供一个方法 给我们逻辑代码 设置底布局的显示和隐藏setLoadingState(boolean loading)
 *
 * 3,设置一个回调接口  给逻辑代码使用  和下拉刷新的操作  保持一致
 *
 */
public class RefreshView extends SwipeRefreshLayout implements AbsListView.OnScrollListener{

    private View footView;
    //是否可以进行加载
    private boolean isLoading = false;

    private ListView listView;

    public RefreshView(Context context) {
        this(context,null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFootView(context);
    }

    //初始化底部布局
    public void initFootView(Context context){
        footView = LayoutInflater.from(context).inflate(R.layout.foot_view,null);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //获取子View的数量
        int  count = getChildCount();
        if(count>0){
            for (int i=0;i<count;i++){
                View view = getChildAt(i);
                if(view instanceof ListView){
                    listView = (ListView) view;
                    listView.setOnScrollListener(this);
                }
            }

        }
    }

    //滑动状态改变的监听
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    //滑动的监听
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //到底部 且可以刷新
        if((firstVisibleItem+visibleItemCount == totalItemCount)&&isLoading==false){
            //1,显示底View
            setLoadingState(true);
            //2,调用这个回调接口里的方法 进行处理事件
            if(onRefreshLoadingCallBack!=null) {
                onRefreshLoadingCallBack.onLoading();
            }

        }
    }

    //使用使用这个方法  控制  加载
    public void setLoadingState(boolean loading){
        isLoading = loading;
        if(loading){
            //把底布局 添加到  listVIew的底部
            listView.addFooterView(footView);
        }else{
            //移除底部布局
            listView.removeFooterView(footView);
        }

    }


    private OnRefreshLoadingCallBack onRefreshLoadingCallBack;
    public void setLoadingListner(OnRefreshLoadingCallBack onRefreshLoadingCallBack){
        this.onRefreshLoadingCallBack = onRefreshLoadingCallBack;
    }


    //回调接口  加载更多时   触发此方法
    public interface OnRefreshLoadingCallBack{
        public void onLoading();
    }
}
