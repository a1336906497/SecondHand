package com.example.qiao.secondhand.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.MainActivity;
import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.SecondHandClass;
import com.example.qiao.secondhand.bean.SecondHandCollege;
import com.example.qiao.secondhand.util.URL;
import com.example.qiao.secondhand.widget.CircleImageView;
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

import static android.os.Environment.*;


public class RegisterActivity extends AppCompatActivity {


    private boolean pictureFlag=false;
    private static final int PHOTO_REQUEST_GALLERY=5;
    private static final int PHOTO_REQUEST_CAREMA=6;
    private static final int PHOTO_REQUEST_CUT=7;
    @BindView(R.id.register_back_image)
    ImageView registerBackImage;
    @BindView(R.id.register_relativeLayout)
    RelativeLayout registerRelativeLayout;
    @BindView(R.id.register_head_image)
    CircleImageView registerHeadImage;
    @BindView(R.id.register_name_image)
    ImageView registerNameImage;
    @BindView(R.id.register_name_edit)
    EditText registerNameEdit;
    @BindView(R.id.register_name_relativelayout)
    RelativeLayout registerNameRelativelayout;
    @BindView(R.id.register_pwd_image)
    ImageView registerPwdImage;
    @BindView(R.id.register_pwd_edit)
    EditText registerPwdEdit;
    @BindView(R.id.register_pwd_relativelayout)
    RelativeLayout registerPwdRelativelayout;
    @BindView(R.id.register_sex_image)
    ImageView registerSexImage;
    @BindView(R.id.register_sex_spinner)
    Spinner registerSexSpinner;
    @BindView(R.id.register_sex_relativelayout)
    RelativeLayout registerSexRelativelayout;
    @BindView(R.id.register_age_image)
    ImageView registerAgeImage;
    @BindView(R.id.register_age_edit)
    EditText registerAgeEdit;
    @BindView(R.id.register_age_relativelayout)
    RelativeLayout registerAgeRelativelayout;
    @BindView(R.id.register_phone_image)
    ImageView registerPhoneImage;
    @BindView(R.id.register_phone_edit)
    EditText registerPhoneEdit;
    @BindView(R.id.register_phone_relativelayout)
    RelativeLayout registerPhoneRelativelayout;
    @BindView(R.id.register_college_text)
    TextView registerCollegeText;
    @BindView(R.id.register_college_spinner)
    Spinner registerCollegeSpinner;
    @BindView(R.id.register_college_relativelayout)
    RelativeLayout registerCollegeRelativelayout;
    @BindView(R.id.register_class_text)
    TextView registerClassText;
    @BindView(R.id.register_class_spinner)
    Spinner registerClassSpinner;
    @BindView(R.id.register_class_relativelayout)
    RelativeLayout registerClassRelativelayout;
    @BindView(R.id.register_signature_edit)
    EditText registerSignatureEdit;
    @BindView(R.id.activity_register)
    RelativeLayout activityRegister;
    @BindView(R.id.register_true)
    TextView registerTrue;
    private ArrayAdapter collegeAdapter;
    private ArrayAdapter classAdapter;
    private List<String> data;
    private List<SecondHandCollege> list;
    private List<SecondHandClass> classList;
    private File tempFile;
    private AlertDialog alertDialog;
    private String timestamp;
    private Intent intent;
    private int classID=6;
    private SharedPreferences preferences;
    private boolean isChaneImage=false;
    private int hflag;
    private  int collegeid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        intent=getIntent();

        hflag = intent.getIntExtra("flag",1);


        //后退键监听
        registerBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获得学院名称
        final RequestParams params=new RequestParams(URL.GETCOLLEGES);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
               // Log.e("+++++++++++", "onSuccess: "+result );
                if (!TextUtils.isEmpty(result)){

                    Gson gson=new Gson();
                    list = gson.fromJson(result,new TypeToken<List<SecondHandCollege>>() {  }.getType());
                    data = getStringArraty(list);
                    collegeAdapter=new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item, data);
                    registerCollegeSpinner.setAdapter(collegeAdapter);

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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


        //点击头像上传图片
        registerHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChaneImage=true;
                final String[] items = new String[] { "拍照", "从相册中选择" };
                // 创建对话框构建器
                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                // 设置参数
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(RegisterActivity.this, items[which],
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
        });



        if (hflag ==1) {

            //确定注册
            registerTrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(registerNameEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(registerPwdEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(registerAgeEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入年龄", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(registerPhoneEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String phoneNumber = registerPhoneEdit.getText().toString().trim();
                    if (phoneNumber.length() != 11) {

                        Toast.makeText(RegisterActivity.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    final String userName = registerNameEdit.getText().toString().trim();
                    final String pwd = registerPwdEdit.getText().toString().trim();
                    final String age = registerAgeEdit.getText().toString().trim();
                    final String phone = registerPhoneEdit.getText().toString();
                    final String college = registerCollegeSpinner.getSelectedItem().toString();
                    final String regsterclass = registerClassSpinner.getSelectedItem().toString();
                    final String signature = registerSignatureEdit.getText().toString();
                    final String sex = registerSexSpinner.getSelectedItem().toString();


                    //检查是否重名
                    RequestParams params = new RequestParams(URL.ISSAMENAME);
                    params.addParameter("userName", userName);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                            if (result.equals("success")) {
                                RequestParams params1 = new RequestParams(URL.REGISTER);

                                params1.addBodyParameter("userName", userName);
                                params1.addBodyParameter("pwd", pwd);
                                params1.addBodyParameter("age", age);
                                params1.addBodyParameter("phone", phone);
                                params1.addBodyParameter("college", collegeid+"");
                                params1.addBodyParameter("class", classID + "");
                                params1.addBodyParameter("signature", signature);
                                params1.addBodyParameter("sex", sex);

                                params1.addBodyParameter(userName + ".jpeg", new File(getExternalFilesDir(null), timestamp));

                                x.http().post(params1, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                                        finish();
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


                            } else {
                                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
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
            });


        }else if(hflag ==2){

            preferences =getSharedPreferences("USER", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor=preferences.edit();
//            /**
//             * UserID : 3
//             * UserName : 李四
//             * UserPwd : 123456
//             * UserSex : 男
//             * UserAge : 18
//             * UserSignature : 空
//             * UserClass : 1
//             * UserMobile : 15610036197
//             * UserCreateTime : Apr 15, 2017 10:51:21 AM
//             * UserImage : 2.png
//             * CollegeID : {"CollegeID":6,"CollegeDesc":"计算机科学与工程学院"}
//             */
//            editor.putString("userName",user.getUserName())
//                    .putInt("userID",user.getUserID())
//                    .putString("userPwd",user.getUserPwd())
//                    .putString("userSex",user.getUserSex())
//                    .putString("userSignature",user.getUserSignature())
//                    .putInt("userClass",user.getUserClass())
//                    .putString("userMobile",user.getUserMobile())
//                    .putString("userCreateTime",user.getUserCreateTime())
//                    .putString("userImage",user.getUserImage())
//                    .putInt("collegeID",user.getCollegeID().getCollegeID())
//                    .putString("collegeDesc",user.getCollegeID().getCollegeDesc()).commit();



           // registerAgeEdit.setText(preferences.getInt("userAge",0));
            registerNameEdit.setText(preferences.getString("userName",""));
            registerPhoneEdit.setText(preferences.getString("userMobile",""));
            registerPwdEdit.setText(preferences.getString("userPwd",""));
            registerSignatureEdit.setText(preferences.getString("userSignature",""));


            //确定修改
            registerTrue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(registerNameEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(registerPwdEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(registerAgeEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入年龄", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(registerPhoneEdit.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String phoneNumber = registerPhoneEdit.getText().toString().trim();
                    if (phoneNumber.length() != 11) {

                        Toast.makeText(RegisterActivity.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    final String userName = registerNameEdit.getText().toString().trim();
                    final String pwd = registerPwdEdit.getText().toString().trim();
                    final String age = registerAgeEdit.getText().toString().trim();
                    final String phone = registerPhoneEdit.getText().toString();
                    final String college = registerCollegeSpinner.getSelectedItem().toString();
                    final String regsterclass = registerClassSpinner.getSelectedItem().toString();
                    final String signature = registerSignatureEdit.getText().toString();
                    final String sex = registerSexSpinner.getSelectedItem().toString();



                    //检查是否重名
                    RequestParams params = new RequestParams(URL.ISSAMENAME);
                    params.addParameter("userName", userName);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                            if (result.equals("success")) {
                                RequestParams params1 = new RequestParams(URL.CHANGEUSERINFO);

                                params1.addBodyParameter("userid",preferences.getInt("userID",3)+"");
                                params1.addBodyParameter("userName", userName);
                                params1.addBodyParameter("pwd", pwd);
                                params1.addBodyParameter("age", age);
                                params1.addBodyParameter("phone", phone);
                                params1.addBodyParameter("college", collegeid+"");
                                params1.addBodyParameter("class", classID + "");
                                params1.addBodyParameter("signature", signature);
                                params1.addBodyParameter("sex", sex);

                                if (isChaneImage==true){
                                    params1.addBodyParameter(userName + ".jpeg", new File(getExternalFilesDir(null), timestamp));
                                    params1.addParameter("ischangeimage",1);
                                }else{
                                    params1.addParameter("ischangeimage",2);
                                }


                                x.http().post(params1, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                                        finish();
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


                            } else {
                                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
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
            });




        }


    }


    //学院列表点击监听
    private void setCollegeSpinnerClick() {
        registerCollegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                //得到学院代码
                SecondHandCollege secondHandCollege = list.get(position);

                final int collegeID = secondHandCollege.getCollegeID();

                Log.e("++++++++++++++", collegeID+"" );
                //网络请求 每次点击获得不同学院的专业
                RequestParams params=new RequestParams(URL.GETCLASS);
                params.addParameter("classCollege",collegeID);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Log.e("++++++++++++++", "onSuccess: "+result );
                        if(!TextUtils.isEmpty(result)){
                            Gson gson=new  Gson();
                            classList=gson.fromJson(result,new TypeToken<List<SecondHandClass>>(){  }.getType());

                            classID=classList.get(0).getClassID();
                            List<String> classString =getClassStringArraty(classList);
                            classAdapter=new ArrayAdapter(RegisterActivity.this,android.R.layout.simple_spinner_item,classString);
                            registerClassSpinner.setAdapter(classAdapter);
                            collegeid=list.get(position).getCollegeID();
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //专业列表点击监听
    public void setClassSpinnerClick(){
        registerClassSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classID=classList.get(position).getClassID();
            }
        });

    }


    //学院转字符串
    private List<String> getStringArraty(List<SecondHandCollege> list1) {
        List<String> datatmpe=new ArrayList<>();
        for (int i = 0; i <list1.size() ; i++) {
            datatmpe.add(list1.get(i).getCollegeDesc());
        }
        return datatmpe;

    }
    //班级转字符串
    private List<String> getClassStringArraty(List<SecondHandClass> list1) {
        List<String> datatmpe=new ArrayList<>();
        for (int i = 0; i <list1.size() ; i++) {
            datatmpe.add(list1.get(i).getClassDesc());
        }
        return datatmpe;

    }




    //判断存储卡是否可用
    public static boolean isSdCardExist() {

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {//判断是否已经挂载
            return true;
        }
        return false;
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            //拍照选择
            case PHOTO_REQUEST_CAREMA:
                if(isSdCardExist()){
                    crop(Uri.fromFile(tempFile));
                }else{
                    Toast.makeText(RegisterActivity.this,"未找到内存卡，无法存储",Toast.LENGTH_LONG).show();
                }
                break;
            //图片裁剪
            case PHOTO_REQUEST_CUT:
                if(data != null) {
                    pictureFlag=true;

                    Bitmap bitmap = data.getParcelableExtra("data");//拿到剪切后的照片
                  //  pictureDatabase.savePhoto(bitmap);//存储到数据库
                   registerHeadImage.setImageBitmap(bitmap);//显示拿到的照片

                    //将图片存储在本地
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
                        //根据当前时间生成图片的名称
                        timestamp = formatter.format(new Date())+".jpeg";
                        saveFile(bitmap,timestamp);
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

        File myCaptureFile = new File(getExternalFilesDir(null),fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }



}
