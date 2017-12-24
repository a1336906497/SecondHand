package com.example.qiao.secondhand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.adapter.NewListViewAdapter;
import com.example.qiao.secondhand.bean.SecondHandGoods;
import com.example.qiao.secondhand.util.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsListActivity extends AppCompatActivity {

    @BindView(R.id.login_back_image)
    ImageView loginBackImage;
    @BindView(R.id.lgoodslist_relativeLayout)
    RelativeLayout lgoodslistRelativeLayout;
    @BindView(R.id.goodslist_listview)
    ListView goodslistListview;
    @BindView(R.id.activity_goods_list)
    RelativeLayout activityGoodsList;
    @BindView(R.id.goodslist_text)
    TextView goodslistText;

    private Intent intent;
    private String type;
    private int typeID;
    private NewListViewAdapter adapter;
    private List<SecondHandGoods> list;
    private RequestParams params;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);

        intent = getIntent();

        type = intent.getStringExtra("type");
        int search=intent.getIntExtra("issearch",0);
        goodslistText.setText(type);
        if (search==0) {

            //按类型查询
            typeID = intent.getIntExtra("typeid", 1);
            list = new ArrayList<>();
            params = new RequestParams(URL.GETGOODSBYTYPE);
            params.addParameter("typeid", typeID);

        }else if (search==1){

            //模糊查询
            String name=intent.getStringExtra("name");

            params=new RequestParams(URL.GETGOODSBYNAME);
            params.addParameter("name",name);

        }else if (search==2){
            //收藏
            final int userid=intent.getIntExtra("userid",3);
            params=new RequestParams(URL.GETCOLLECTIONGOODS);
            params.addParameter("userid",userid);


            goodslistListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int i, long l) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(GoodsListActivity.this);
                    View view1= LayoutInflater.from(GoodsListActivity.this).inflate(R.layout.dialog_main,null);
                    TextView delete_no_text= (TextView) view1.findViewById(R.id.delete_no_text);
                    TextView delete_yes_text= (TextView) view1.findViewById(R.id.delete_yes_text);
                    TextView text_delete= (TextView) view1.findViewById(R.id.text_delete);
                    text_delete.setText("确定要取消收藏吗？");
                    builder.setView(view1);
                    dialog = builder.create();
                    dialog.show();
                    delete_no_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    delete_yes_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            dialog.cancel();
                            RequestParams requestParams=new RequestParams(URL.CANCLECOLLECTION);
                            requestParams.addParameter("userid",userid);
                            requestParams.addParameter("goodsid",list.get(i).getGoodsID());
                            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    //发送网络请求
                                    x.http().get(params, new Callback.CommonCallback<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            if (!TextUtils.isEmpty(result)) {

                                                if ("null".equals(result)) {
                                                    Toast.makeText(GoodsListActivity.this, "没有您需要的物品", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    //Toast.makeText(GoodsListActivity.this, result, Toast.LENGTH_SHORT).show();
                                                    Gson gson = new Gson();
                                                    list = gson.fromJson(result, new TypeToken<List<SecondHandGoods>>() {
                                                    }.getType());

                                                    adapter = new NewListViewAdapter(list, GoodsListActivity.this);
                                                    goodslistListview.setAdapter(adapter);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {

                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {

                                        }

                                        @Override
                                        public void onFinished() {

                                        }
                                    });
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });

                        }
                    });
                    return true;
                }
            });



        }else if(search==3){
            //发布
            final int userid=intent.getIntExtra("userid",3);
            params=new RequestParams(URL.GETRELEASEGOODS);
            params.addParameter("userid",userid);

            goodslistListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int i, long l) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(GoodsListActivity.this);
                    View view1= LayoutInflater.from(GoodsListActivity.this).inflate(R.layout.dialog_main,null);
                    TextView delete_no_text= (TextView) view1.findViewById(R.id.delete_no_text);
                    TextView delete_yes_text= (TextView) view1.findViewById(R.id.delete_yes_text);
                    TextView text_delete= (TextView) view1.findViewById(R.id.text_delete);
                    text_delete.setText("确定要取消发布吗？");
                    builder.setView(view1);
                    dialog = builder.create();
                    dialog.show();
                    delete_no_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    delete_yes_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            dialog.cancel();
                            RequestParams requestParams=new RequestParams(URL.CANCLERELEASE);
                            requestParams.addParameter("goodsid",list.get(i).getGoodsID());
                            x.http().get(requestParams, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    //发送网络请求
                                    x.http().get(params, new Callback.CommonCallback<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            if (!TextUtils.isEmpty(result)) {

                                                if ("null".equals(result)) {
                                                    Toast.makeText(GoodsListActivity.this, "没有您需要的物品", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    //Toast.makeText(GoodsListActivity.this, result, Toast.LENGTH_SHORT).show();
                                                    Gson gson = new Gson();
                                                    list = gson.fromJson(result, new TypeToken<List<SecondHandGoods>>() {
                                                    }.getType());

                                                    adapter = new NewListViewAdapter(list, GoodsListActivity.this);
                                                    goodslistListview.setAdapter(adapter);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {

                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {

                                        }

                                        @Override
                                        public void onFinished() {

                                        }
                                    });
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });

                        }
                    });
                    return true;
                }
            });


        }

        //发送网络请求
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {

                    if ("null".equals(result)) {
                        Toast.makeText(GoodsListActivity.this, "没有您需要的物品", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(GoodsListActivity.this, result, Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        list = gson.fromJson(result, new TypeToken<List<SecondHandGoods>>() {
                        }.getType());

                        adapter = new NewListViewAdapter(list, GoodsListActivity.this);
                        goodslistListview.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        //listview点击事件  跳转信息详情界面
        goodslistListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SecondHandGoods goods = list.get(i);
                Intent intent = new Intent(GoodsListActivity.this, GoodsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", goods.getGoodsID());
                bundle.putString("conent", goods.getGoodsConent());
                bundle.putString("name", goods.getGoodsName());
                bundle.putString("images", goods.getGoodsPicLarge());
                bundle.putString("text", goods.getGoodsConent());
                bundle.putString("price", goods.getGoodsPriceShop());
                bundle.putString("phone", goods.getUser().getUserMobile());
                bundle.putString("userimage", goods.getUser().getUserImage());
                bundle.putString("username", goods.getUser().getUserName());
                bundle.putInt("userid", goods.getUser().getUserID());
                bundle.putString("usercollege", goods.getUser().getCollegeID().getCollegeDesc());
                bundle.putString("time", goods.getGoodsInTime());
                bundle.putString("place", goods.getTradingLocation());
                bundle.getInt("times", goods.getBrowseTimes());
                intent.putExtras(bundle);


                startActivity(intent);
            }
        });



        //后退键监听
        loginBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
