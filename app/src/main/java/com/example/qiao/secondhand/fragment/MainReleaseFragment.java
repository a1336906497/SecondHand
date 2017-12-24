package com.example.qiao.secondhand.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.activity.RegisterActivity;
import com.example.qiao.secondhand.bean.SecondHandClass;
import com.example.qiao.secondhand.bean.SecondHandCollege;
import com.example.qiao.secondhand.bean.SecondHandGoodsBorder;
import com.example.qiao.secondhand.bean.SecondHandGoodsType;
import com.example.qiao.secondhand.util.LoginUtil;
import com.example.qiao.secondhand.util.URL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.qiao.secondhand.activity.RegisterActivity.isSdCardExist;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainReleaseFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.release_relativeLayout)
    RelativeLayout releaseRelativeLayout;
    @BindView(R.id.release_image1)
    ImageView releaseImage1;
    @BindView(R.id.release_image2)
    ImageView releaseImage2;
    @BindView(R.id.release_image3)
    ImageView releaseImage3;
    @BindView(R.id.release_image4)
    ImageView releaseImage4;
    @BindView(R.id.release_image_linear)
    LinearLayout releaseImageLinear;
    @BindView(R.id.release_name_text)
    TextView releaseNameText;
    @BindView(R.id.release_name_edit)
    EditText releaseNameEdit;
    @BindView(R.id.release_price_text)
    TextView releasePriceText;
    @BindView(R.id.release_price_edit)
    EditText releasePriceEdit;
    @BindView(R.id.release_context_text)
    TextView releaseContextText;
    @BindView(R.id.release_context_edit)
    EditText releaseContextEdit;
    @BindView(R.id.release_place_text)
    TextView releasePlaceText;
    @BindView(R.id.release_place_edit)
    EditText releasePlaceEdit;
    @BindView(R.id.release_type_text)
    TextView releaseTypeText;
    @BindView(R.id.release_type_spinner)
    Spinner releaseTypeSpinner;
    @BindView(R.id.release_type_relativelayout)
    RelativeLayout releaseTypeRelativelayout;
    @BindView(R.id.release_border_text)
    TextView releaseBorderText;
    @BindView(R.id.register_class_spinner)
    Spinner registerClassSpinner;
    @BindView(R.id.release_class_relativelayout)
    RelativeLayout releaseClassRelativelayout;
    @BindView(R.id.release_button_textview)
    TextView releaseButtonTextview;
    Unbinder unbinder;
    private ArrayAdapter typeAdapter;
    private ArrayAdapter borderAdapter;
    private List<SecondHandGoodsType> list;
    private List<String> data;
    private List<SecondHandGoodsBorder> borderList;

    private int index=1;
    private String timestamp;
    private File tempFile;

    private boolean pictureFlag=false;
    private static final int PHOTO_REQUEST_GALLERY=5;
    private static final int PHOTO_REQUEST_CAREMA=6;
    private static final int PHOTO_REQUEST_CUT=7;
    private AlertDialog alertDialog;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String images;

    private int borderID=1;
    private SharedPreferences preferences;


    public MainReleaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_release, container, false);
        unbinder = ButterKnife.bind(this, view);
        
        releaseImage1.setOnClickListener(this);






    //获得一级分类
        RequestParams params=new RequestParams(URL.GETALLTYPES);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (!TextUtils.isEmpty(result)){

                    Gson gson=new Gson();
                    list = gson.fromJson(result,new TypeToken<List<SecondHandGoodsType>>() {  }.getType());
                    data = getStringArraty(list);
                    typeAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, data);
                    releaseTypeSpinner.setAdapter(typeAdapter);

                    //下拉列表点击监听
                    setCollegeSpinnerClick();

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




        //发布按钮
        releaseButtonTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String goodsName=releaseNameEdit.getText().toString();
                String goodsText=releaseContextEdit.getText().toString();
                String goodsPlace=releasePlaceEdit.getText().toString();
                String goodsPrice=releasePriceEdit.getText().toString();
                if ( TextUtils.isEmpty(releaseNameEdit.getText().toString())){
                    Toast.makeText(getContext(), "请输入商品名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(releaseContextEdit.getText().toString())){
                    Toast.makeText(getContext(), "请输入商品描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(releasePlaceEdit.getText().toString())){
                    Toast.makeText(getContext(), "请输入交易地点", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(releasePriceEdit.getText().toString())){
                    Toast.makeText(getContext(), "请输入交易价格", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(image1)){
                    Toast.makeText(getContext(), "请至少添加一张图片", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!LoginUtil.isLogin(getContext())){
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }



                RequestParams params=new RequestParams(URL.releaseGoods);
                params.addParameter("goodsName",goodsName);
                params.addParameter("goodsText",goodsText);
                params.addParameter("goodsPlace",goodsPlace);
                params.addParameter("goodsPrice",goodsPrice);

                params.addParameter("imageLage",image1);

                params.addParameter("images",images);

                params.addParameter("borderID",borderID);
                preferences =getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);

                int userID=preferences.getInt("userID",3);

                params.addParameter("userID",userID);


                //发布新商品
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
                        releaseImage1.setBackgroundResource(R.drawable.addimage);
                        releaseImage2.setImageBitmap(null);
                        releaseImage3.setImageBitmap(null);
                        releaseImage4.setImageBitmap(null);
                        releaseImage1.setOnClickListener(MainReleaseFragment.this);
                        releaseImage2.setOnClickListener(null);
                        releaseImage3.setOnClickListener(null);
                        releaseImage4.setOnClickListener(null);

                        releasePriceEdit.setText("");
                        releaseContextEdit.setText("");
                        releaseNameEdit.setText("");
                        releasePlaceEdit.setText("");

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
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







        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            //拍照选择
            case PHOTO_REQUEST_CAREMA:
                if(isSdCardExist()){
                    crop(Uri.fromFile(tempFile));
                }else{
                    Toast.makeText(getContext(),"未找到内存卡，无法存储",Toast.LENGTH_LONG).show();
                }
                break;
            //图片裁剪
            case PHOTO_REQUEST_CUT:
                if(data != null) {
                    pictureFlag=true;

                    Bitmap bitmap = data.getParcelableExtra("data");//拿到剪切后的照片
                    //  pictureDatabase.savePhoto(bitmap);//存储到数据库
                    switch (index){

                        case 1:
                            releaseImage1.setImageBitmap(bitmap);//显示拿到的照片
                            releaseImage2.setOnClickListener(this);
                            releaseImage1.setOnClickListener(null);
                            break;
                        case 2:
                            releaseImage2.setImageBitmap(bitmap);
                            releaseImage3.setOnClickListener(this);
                            releaseImage2.setOnClickListener(null);
                            break;
                        case 3:
                            releaseImage3.setImageBitmap(bitmap);
                            releaseImage4.setOnClickListener(this);
                            releaseImage3.setOnClickListener(null);
                            break;
                        case 4:
                            releaseImage4.setImageBitmap(bitmap);
                            releaseImage4.setOnClickListener(null);
                            break;
                    }



                    //将图片存储在本地
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
                        //根据当前时间生成图片的名称
                        timestamp = formatter.format(new Date())+".jpeg";
                        saveFile(bitmap,timestamp);
                        switch (index){

                            case 1:
                               image1=timestamp;
                                images=image1;
                                releaseImage2.setBackgroundResource(R.drawable.addimage);
                                break;

                            case 2:
                                image2=timestamp;
                                images=images+";"+image2;
                                releaseImage3.setBackgroundResource(R.drawable.addimage);
                                break;
                            case 3:
                                image3=timestamp;
                                images=images+";"+image3;
                                releaseImage4.setBackgroundResource(R.drawable.addimage);
                                break;
                            case 4:
                                image4=timestamp;
                                images=images+";"+image4;

                                break;
                        }

                        index++;
                        //上传图片
                        RequestParams params1=new RequestParams(URL.SAVEIMAGE);
                        params1.addBodyParameter("imagename",timestamp);
                        params1.addBodyParameter(timestamp, new File(getActivity().getExternalFilesDir(null),timestamp));
                        x.http().post(params1, new Callback.CommonCallback<String>() {
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


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    tempFile.delete();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            //从相册中选择
            case PHOTO_REQUEST_GALLERY:

                if (data!=null){
                    crop(data.getData());
                }
                break;

        }
    }


    //将图片存储本地
    public void saveFile(Bitmap bm, String fileName) throws IOException {

        File myCaptureFile = new File(getActivity().getExternalFilesDir(null),fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }


    //图片裁剪
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    //分类列表监听
    private void setCollegeSpinnerClick() {
        releaseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //得到一级代码
               SecondHandGoodsType type = list.get(i);

                final int typeID= type.getTypeID();


                //网络请求 每次点击获得不同二级分类
                RequestParams params=new RequestParams(URL.GETBORDERBYTYPE);
                params.addParameter("typeid",typeID);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Log.e("++++++++++++++", "onSuccess: "+result );
                        if(!TextUtils.isEmpty(result)){
                            Gson gson=new  Gson();
                            borderList =gson.fromJson(result,new TypeToken<List<SecondHandGoodsBorder>>(){  }.getType());
                            List<String> classString = getBorderStringArraty(borderList);
                            borderAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,classString);
                            registerClassSpinner.setAdapter(borderAdapter);
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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        registerClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                borderID= borderList.get(i).getBorderID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private List<String> getBorderStringArraty(List<SecondHandGoodsBorder> borderList) {
        List<String> data1=new ArrayList<>();
        for (int i = 0; i < borderList.size(); i++) {
            data1.add(borderList.get(i).getBorderDesc());
        }
        return data1;
    }

    private List<String> getStringArraty(List<SecondHandGoodsType> list) {
        List<String> data1=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            data1.add(list.get(i).getTypeDesc());
        }
        return data1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        final String[] items = new String[] { "拍照", "从相册中选择" };
        // 创建对话框构建器
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 设置参数
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), items[which],
                        Toast.LENGTH_SHORT).show();

                Log.e("-------------", "onClick: "+new Date() );


                //拍照
                if (which==0){
                    // 激活相机
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    // 判断存储卡是否可以用，可用进行存储
                    if (isSdCardExist()) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
                        //根据当前时间生成图片的名称
                        timestamp = formatter.format(new Date())+".jpeg";
                        tempFile = new File(Environment.getExternalStorageDirectory(),
                                timestamp);
                        // 从文件中创建uri
                        Uri uri = Uri.fromFile(tempFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    }
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                    startActivityForResult(intent, PHOTO_REQUEST_CAREMA);

                }

                //从相册中选取
                if (which==1){
                    // 激活系统图库，选择一张图片
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                }

                alertDialog.dismiss();


            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }


}
