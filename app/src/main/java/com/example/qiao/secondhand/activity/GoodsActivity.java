package com.example.qiao.secondhand.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.adapter.GoodsListViewAdapter;
import com.example.qiao.secondhand.adapter.GoodsViewPagerAdapter;
import com.example.qiao.secondhand.bean.SecondHandCommend;
import com.example.qiao.secondhand.util.LoginUtil;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.CircleImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends AppCompatActivity {

    @BindView(R.id.goods_viewpager)
    ViewPager goodsViewpager;
    @BindView(R.id.goods_back)
    ImageView goodsBack;
    @BindView(R.id.goods_radiogroup)
    RadioGroup goodsRadiogroup;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_place_image)
    ImageView goodsPlaceImage;
    @BindView(R.id.goods_place)
    TextView goodsPlace;
    @BindView(R.id.goods_time)
    TextView goodsTime;
    @BindView(R.id.goods_user_image)
    CircleImageView goodsUserImage;
    @BindView(R.id.goods_user_name)
    TextView goodsUserName;
    @BindView(R.id.goods_user_college)
    TextView goodsUserCollege;
    @BindView(R.id.goods_user_phone)
    TextView goodsUserPhone;
    @BindView(R.id.goods_info_image)
    ImageView goodsInfoImage;
    @BindView(R.id.goods_info_text)
    TextView goodsInfoText;
    @BindView(R.id.goods_commend_image)
    ImageView goodsCommendImage;
    @BindView(R.id.goods_commend_listview)
    ListView goodsCommendListview;
    @BindView(R.id.goods_contact)
    TextView goodsContact;
    @BindView(R.id.goods_collection)
    TextView goodsCollection;
    @BindView(R.id.goods_true_text)
    TextView goodsTrueText;
    @BindView(R.id.goods_commend_edit)
    EditText goodsCommendEdit;
    @BindView(R.id.goods_input_relative)
    RelativeLayout goodsInputRelative;
    @BindView(R.id.activity_goods)
    RelativeLayout activityGoods;
    @BindView(R.id.goods_browsetimes)
    TextView goodsBrowsetimes;
    @BindView(R.id.goods_chat_button)
    TextView goodsChatButton;

    private List<String> data;


    private Bundle bundle;
    private Dialog dialog;

    private Intent intent;
    private GoodsViewPagerAdapter adapter;
    private List<SecondHandCommend> list;

    private GoodsListViewAdapter listViewAdapter;

    private InputMethodManager imm;
    private SharedPreferences preferences;
    private int userid;

    private boolean isCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);

        data = new ArrayList<>();
        preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);

        //获取其他activity传来的数据
        intent = getIntent();
        bundle = intent.getExtras();

//        bundle.putInt("id",goods.getGoodsID());
//        bundle.putString("conent",goods.getGoodsConent());
//        bundle.putString("name",goods.getGoodsName());
//        bundle.putString("images",goods.getGoodsPicLarge());
//        bundle.putString("text",goods.getGoodsText());
//        bundle.putString("price",goods.getGoodsPriceShop());
//        bundle.putString("phone",goods.getUser().getUserMobile());
//        bundle.putString("userimage",goods.getUser().getUserImage());
//        bundle.putString("username",goods.getUser().getUserName());
//        bundle.putInt("userid",goods.getUser().getUserID());
//        bundle.putString("usercollege",goods.getUser().getCollegeID().getCollegeDesc());


        String images = bundle.getString("images");


        //判断是否收藏
        isCollection();


        //商品浏览次数+1
        addGoodsTimers();

        //分割字符串 转成list
        String[] arrys = images.split(";");
        data = Arrays.asList(arrys);


        adapter = new GoodsViewPagerAdapter(data, this);

        goodsViewpager.setAdapter(adapter);


        //初始化radiogroup并建立与viewpager的联动
        initRadioGroup();

        //后退键设置监听
        goodsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //控件赋值
        initData();

        //网络请求评论列表
        commendIntnet();



        //chat
        goodsChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GoodsActivity.this,ChatActivity.class);
                intent.putExtra("id",bundle.getInt("userid",3));
                intent.putExtra("userimage",bundle.getString("userimage"));
                startActivity(intent);

            }
        });


        //点击评论弹出输入框
        goodsCommendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsInputRelative.setVisibility(View.VISIBLE);
                goodsCommendEdit.setFocusable(true);
                //打开软键盘
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


        goodsTrueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!LoginUtil.isLogin(GoodsActivity.this)) {
                    Toast.makeText(GoodsActivity.this, "您还没有登录，请先登录", Toast.LENGTH_SHORT).show();
                } else {

                    //软键盘消失
                    imm.hideSoftInputFromWindow(goodsCommendEdit.getWindowToken(), 0);
                    goodsInputRelative.setVisibility(View.GONE);
                    RequestParams params = new RequestParams(URL.ADDCOMMEND);


                    SharedPreferences.Editor editor = preferences.edit();


                    params.addParameter("goodsid", bundle.getInt("id"));
                    params.addParameter("userid", preferences.getInt("userID", 0));

                    params.addParameter("commend", goodsCommendEdit.getText().toString());
                    //  Toast.makeText(GoodsActivity.this, goodsCommendEdit.getText().toString() , Toast.LENGTH_SHORT).show();
                    goodsCommendEdit.setText("");
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(GoodsActivity.this, "评论成功！" + result, Toast.LENGTH_SHORT).show();
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


                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            commendIntnet();
                        }
                    }, 2000, 2000);

                }
            }
        });


        //点击联系人
        goodsContact.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(GoodsActivity.this);

                View view1 = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.contact_diolag, null);
                TextView phoneText = (TextView) view1.findViewById(R.id.dialog_phone);
                TextView messageText = (TextView) view1.findViewById(R.id.dialog_message);

                builder.setView(view1);
                dialog = builder.create();
                dialog.show();

                //点击跳转到拨打电话界面
                phoneText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("tel:" + bundle.getString("phone"));

                        Intent intent1 = new Intent(Intent.ACTION_DIAL, uri);

                        startActivity(intent1);
                        dialog.dismiss();
                    }
                });

                //点击跳转到短信界面
                messageText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri smsToUri = Uri.parse("smsto://" + bundle.getString("phone"));
                        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                        startActivity(mIntent);
                        dialog.dismiss();
                    }
                });

            }
        });


        //点击收藏
        goodsCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!LoginUtil.isLogin(GoodsActivity.this)) {
                    Toast.makeText(GoodsActivity.this, "您还没有登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                if (isCollection == true) {
                    RequestParams params = new RequestParams(URL.CANCLECOLLECTION);
                    params.addParameter("goodsid", bundle.getInt("id"));
                    params.addParameter("userid", preferences.getInt("userID", 0));
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(GoodsActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
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
                    goodsCollection.setText("收藏商品");
                    Drawable rightDrawable = getResources().getDrawable(R.mipmap.white_heart);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    goodsCollection.setCompoundDrawables(rightDrawable, null, null, null);
                    isCollection = false;


                } else if (isCollection == false) {
                    RequestParams params = new RequestParams(URL.ADDCOLLECTION);
                    params.addParameter("goodsid", bundle.getInt("id"));
                    params.addParameter("userid", preferences.getInt("userID", 0));
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(GoodsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
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
                    goodsCollection.setText("取消收藏");
                    Drawable rightDrawable = getResources().getDrawable(R.mipmap.red_pink_heart);
                    rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                    goodsCollection.setCompoundDrawables(rightDrawable, null, null, null);
                    isCollection = true;
                }
            }
        });


    }

    private void addGoodsTimers() {

        RequestParams params1 = new RequestParams(URL.ADDGOODSTMES);
        params1.addParameter("goodsid", bundle.getInt("id"));

        x.http().get(params1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

    private void commendIntnet() {

        list = new ArrayList<>();

        RequestParams params = new RequestParams(URL.GETCOMMENDBYGOODS);
        params.addParameter("goodsid", bundle.getInt("id"));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)) {
                    if (!"null".equals(result)) {
                        Gson gson = new Gson();
                        list = gson.fromJson(result, new TypeToken<List<SecondHandCommend>>() {
                        }.getType());
                        listViewAdapter = new GoodsListViewAdapter(list, GoodsActivity.this);
                        goodsCommendListview.setAdapter(listViewAdapter);
                        setListViewHeightBasedOnChildren(goodsCommendListview);
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

    private void initData() {
        goodsName.setText(bundle.getString("name"));
        goodsPlace.setText("交易地点 " + bundle.getString("place"));
        goodsTime.setText(bundle.getString("time"));
        goodsInfoText.setText(bundle.getString("text"));
        goodsPrice.setText("￥\n" + bundle.getString("price"));
        goodsUserCollege.setText(bundle.getString("usercollege"));
        goodsUserName.setText("发布人 " + bundle.getString("username"));
        Picasso.with(this).load(URL.IMAGE + bundle.getString("userimage")).into(goodsUserImage);
        goodsUserPhone.setText("手机号" + bundle.getString("phone"));
        goodsBrowsetimes.setText("浏览次数 " + bundle.getInt("times"));
    }

    private void initRadioGroup() {
        //初始化RadioGroup

        for (int i = 0; i < data.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setEnabled(false);
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setButtonDrawable(getResources().getDrawable(R.drawable.viewpager_radiobutton));
            goodsRadiogroup.addView(radioButton);
        }


        //滑动监听 RadioGroup联动
        goodsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                goodsViewpager.setCurrentItem(position, false);

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) (goodsRadiogroup.getChildAt(position))).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        goodsViewpager.setCurrentItem(1);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public void isCollection() {
        if (!LoginUtil.isLogin(GoodsActivity.this)) {
            // Toast.makeText(GoodsActivity.this, "您还没有登录，请先登录", Toast.LENGTH_SHORT).show();
        } else {
            RequestParams params = new RequestParams(URL.ISCOLLECTION);
            params.addParameter("goodsid", bundle.getInt("id"));
            params.addParameter("userid", preferences.getInt("userID", 0));
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if ("true".equals(result)) {
                        goodsCollection.setText("取消收藏");
                        Drawable rightDrawable = getResources().getDrawable(R.mipmap.red_pink_heart);
                        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                        goodsCollection.setCompoundDrawables(rightDrawable, null, null, null);
                        isCollection = true;

                    } else if ("false".equals(result)) {
                        isCollection = false;
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
    }
}
