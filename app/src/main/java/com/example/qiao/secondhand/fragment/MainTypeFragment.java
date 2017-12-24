package com.example.qiao.secondhand.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.GoodsActivity;
import com.example.qiao.secondhand.activity.GoodsListActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTypeFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.main_type_search_canclebutton)
    Button mainTypeSearchCanclebutton;
    @BindView(R.id.main_type_search_image)
    ImageView mainTypeSearchImage;
    @BindView(R.id.main_type_search_edit)
    EditText mainTypeSearchEdit;
    @BindView(R.id.main_type_search_relativelayout)
    RelativeLayout mainTypeSearchRelativelayout;
    @BindView(R.id.goods_type_bicycle_image)
    ImageView goodsTypeBicycleImage;
    @BindView(R.id.goods_type_bicycle_type)
    TextView goodsTypeBicycleType;
    @BindView(R.id.goods_type_bicycle_relative)
    RelativeLayout goodsTypeBicycleRelative;
    @BindView(R.id.goods_type_phone_image)
    ImageView goodsTypePhoneImage;
    @BindView(R.id.goods_type_phone_type)
    TextView goodsTypePhoneType;
    @BindView(R.id.goods_type_phone_relative)
    RelativeLayout goodsTypePhoneRelative;
    @BindView(R.id.goods_type_computer_image)
    ImageView goodsTypeComputerImage;
    @BindView(R.id.goods_type_computer_type)
    TextView goodsTypeComputerType;
    @BindView(R.id.goods_type_computer_relative)
    RelativeLayout goodsTypeComputerRelative;
    @BindView(R.id.goods_type_accessory_image)
    ImageView goodsTypeAccessoryImage;
    @BindView(R.id.goods_type_accessory_type)
    TextView goodsTypeAccessoryType;
    @BindView(R.id.goods_type_accessory_relative)
    RelativeLayout goodsTypeAccessoryRelative;
    @BindView(R.id.goods_type_ditial_image)
    ImageView goodsTypeDitialImage;
    @BindView(R.id.goods_type_ditial_type)
    TextView goodsTypeDitialType;
    @BindView(R.id.goods_type_ditial_relative)
    RelativeLayout goodsTypeDitialRelative;
    @BindView(R.id.goods_type_electric_image)
    ImageView goodsTypeElectricImage;
    @BindView(R.id.goods_type_electric_type)
    TextView goodsTypeElectricType;
    @BindView(R.id.goods_type_electric_relative)
    RelativeLayout goodsTypeElectricRelative;
    @BindView(R.id.goods_type_sports_image)
    ImageView goodsTypeSportsImage;
    @BindView(R.id.goods_type_sports_type)
    TextView goodsTypeSportsType;
    @BindView(R.id.goods_type_sports_relative)
    RelativeLayout goodsTypeSportsRelative;
    @BindView(R.id.goods_type_clothes_image)
    ImageView goodsTypeClothesImage;
    @BindView(R.id.goods_type_clothes_type)
    TextView goodsTypeClothesType;
    @BindView(R.id.goods_type_clothes_relative)
    RelativeLayout goodsTypeClothesRelative;
    @BindView(R.id.goods_type_book_image)
    ImageView goodsTypeBookImage;
    @BindView(R.id.goods_type_book_type)
    TextView goodsTypeBookType;
    @BindView(R.id.goods_type_book_relative)
    RelativeLayout goodsTypeBookRelative;
    @BindView(R.id.goods_type_rent_image)
    ImageView goodsTypeRentImage;
    @BindView(R.id.goods_type_rent_type)
    TextView goodsTypeRentType;
    @BindView(R.id.goods_type_rent_relative)
    RelativeLayout goodsTypeRentRelative;
    @BindView(R.id.goods_type_life_image)
    ImageView goodsTypeLifeImage;
    @BindView(R.id.goods_type_life_type)
    TextView goodsTypeLifeType;
    @BindView(R.id.goods_type_life_relative)
    RelativeLayout goodsTypeLifeRelative;
    @BindView(R.id.goods_type_other_image)
    ImageView goodsTypeOtherImage;
    @BindView(R.id.goods_type_other_type)
    TextView goodsTypeOtherType;
    @BindView(R.id.goods_type_other_relative)
    RelativeLayout goodsTypeOtherRelative;
    Unbinder unbinder;
    @BindView(R.id.main_type_search_typetext)
    TextView mainTypeSearchTypetext;
    @BindView(R.id.main_type_relativelayout)
    RelativeLayout mainTypeRelativelayout;
    @BindView(R.id.main_type_linearlayout_text)
    LinearLayout mainTypeLinearlayoutText;
    @BindView(R.id.main_type_hstory_listview)
    ListView mainTypeHstoryListview;
    @BindView(R.id.main_type_hotsearch_listview)
    ListView mainTypeHotsearchListview;
    @BindView(R.id.main_type_linearlayout_list)
    LinearLayout mainTypeLinearlayoutList;
    @BindView(R.id.clearhistory)
    TextView clearhistory;

    private Set<String> historySet;
    private SharedPreferences preferences;


    public MainTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_type, container, false);
        unbinder = ButterKnife.bind(this, view);

        //初始化搜索历史
        initSet();


        mainTypeSearchEdit.setFocusable(true);
        mainTypeSearchEdit.setFocusableInTouchMode(true);
        clearhistory.setVisibility(View.GONE);
        mainTypeSearchCanclebutton.setVisibility(View.GONE);
        mainTypeSearchTypetext.setVisibility(View.VISIBLE);
        mainTypeRelativelayout.setVisibility(View.VISIBLE);
        mainTypeLinearlayoutText.setVisibility(View.GONE);
        mainTypeLinearlayoutList.setVisibility(View.GONE);

        //搜索框获得焦点监听
        mainTypeSearchEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mainTypeSearchCanclebutton.setVisibility(View.VISIBLE);
                    mainTypeSearchTypetext.setVisibility(View.GONE);
                    clearhistory.setVisibility(View.GONE);
                    mainTypeRelativelayout.setVisibility(View.GONE);
                    mainTypeLinearlayoutText.setVisibility(View.VISIBLE);
                    mainTypeLinearlayoutList.setVisibility(View.VISIBLE);
                } else {
                    try {
                        mainTypeSearchCanclebutton.setVisibility(View.GONE);
                        mainTypeSearchTypetext.setVisibility(View.VISIBLE);
                        mainTypeRelativelayout.setVisibility(View.VISIBLE);
                        clearhistory.setVisibility(View.GONE);
                        mainTypeLinearlayoutText.setVisibility(View.GONE);
                        mainTypeLinearlayoutList.setVisibility(View.GONE);
                    }catch (Exception e){

                    }

                }

            }
        });
        mainTypeSearchCanclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTypeSearchEdit.clearFocus();
                mainTypeSearchEdit.setFocusable(false);


            }
        });

        //搜索框内容监听
        mainTypeSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, final int i, int i1, int i2) {
                    if (i2==0){
                        mainTypeSearchCanclebutton.setText("取消");
                        mainTypeSearchCanclebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mainTypeSearchEdit.clearFocus();
                                mainTypeSearchEdit.setFocusable(false);
                            }
                        });
                    }else {
                        mainTypeSearchCanclebutton.setText("搜索");
                        mainTypeSearchCanclebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SharedPreferences.Editor editor= preferences.edit();
                                String text=mainTypeSearchEdit.getText().toString().trim();
                                historySet.add(text);
                                editor.putStringSet("his",historySet);
                                editor.commit();

                                Intent intent=new Intent(getContext(),GoodsListActivity.class);
                                intent.putExtra("name",text);
                                intent.putExtra("issearch",1);
                                intent.putExtra("type",text);
                                startActivity(intent);

                            }
                        });




                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //typeListener
        setTypeListener();

        return view;

    }

    private void setTypeListener() {
        goodsTypeAccessoryRelative.setOnClickListener(this);
        goodsTypeBicycleRelative.setOnClickListener(this);
        goodsTypeBookRelative.setOnClickListener(this);
        goodsTypeClothesRelative.setOnClickListener(this);
        goodsTypeComputerRelative.setOnClickListener(this);
        goodsTypeDitialRelative.setOnClickListener(this);
        goodsTypeElectricRelative.setOnClickListener(this);
        goodsTypeLifeRelative.setOnClickListener(this);
        goodsTypePhoneRelative.setOnClickListener(this);
        goodsTypeOtherRelative.setOnClickListener(this);
        goodsTypeRentRelative.setOnClickListener(this);
        goodsTypeSportsRelative.setOnClickListener(this);

    }


    private void initSet() {
        historySet=new HashSet<>();

        preferences =getContext().getSharedPreferences("history", Context.MODE_PRIVATE);
        historySet=preferences.getStringSet("his",new HashSet<String>());
        List<String> lsit=new ArrayList<>();
        lsit.addAll(historySet);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,lsit);
        mainTypeHstoryListview.setAdapter(arrayAdapter);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getContext(), GoodsListActivity.class);
        switch (view.getId()){
            case R.id.goods_type_bicycle_relative:
                intent.putExtra("type","校园代步");
                intent.putExtra("typeid",1);
                break;
            case R.id.goods_type_phone_relative:
                intent.putExtra("type","手机");
                intent.putExtra("typeid",2);
                break;

            case R.id.goods_type_computer_relative:
                intent.putExtra("type","电脑");
                intent.putExtra("typeid",3);
                break;

            case R.id.goods_type_accessory_relative:
                intent.putExtra("type","数码配件");
                intent.putExtra("typeid",4);
                break;

            case R.id.goods_type_ditial_relative:
                intent.putExtra("type","数码");
                intent.putExtra("typeid",5);
                break;

            case R.id.goods_type_electric_relative:
                intent.putExtra("type","电器");
                intent.putExtra("typeid",6);
                break;
            case R.id.goods_type_sports_relative:
                intent.putExtra("type","运动健身");
                intent.putExtra("typeid",7);
                break;

            case R.id.goods_type_clothes_relative:
                intent.putExtra("type","衣物伞帽");
                intent.putExtra("typeid",8);
                break;

            case R.id.goods_type_book_relative:
                intent.putExtra("type","图书教材");
                intent.putExtra("typeid",9);
                break;

            case R.id.goods_type_rent_relative:
                intent.putExtra("type","租赁");
                intent.putExtra("typeid",10);
                break;

            case R.id.goods_type_life_relative:
                intent.putExtra("type","生活娱乐");
                intent.putExtra("typeid",11);
                break;

            case R.id.goods_type_other_relative:
                intent.putExtra("type","其他");
                intent.putExtra("typeid",12);
                break;
        }
        intent.putExtra("issearch",0);
        startActivity(intent);
    }
}
