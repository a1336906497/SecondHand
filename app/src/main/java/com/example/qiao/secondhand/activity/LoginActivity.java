package com.example.qiao.secondhand.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.bean.SecondHandUser;
import com.example.qiao.secondhand.fragment.MainMyFragment;
import com.example.qiao.secondhand.util.LoginUtil;
import com.example.qiao.secondhand.util.URL;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginActivity extends AppCompatActivity {

    private ImageView login_back_image;
    private EditText login_name_edittext;
    private EditText login_pwd_edittext;
    private TextView logn_button_textview;
    private TextView login_register_textview;
    private String userName;
    private String passWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);

        //初始化控件
        initView();

        //返回键设置监听
        login_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //登录监听
        logn_button_textview.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                userName = login_name_edittext.getText().toString();
                passWord = login_pwd_edittext.getText().toString();

                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(passWord)){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;

                }

                final RequestParams params=new RequestParams(URL.SECONDHANDLOGIN);
                params.addParameter("userName",userName);
                params.addParameter("passWord", passWord);

                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                        if ("登陆成功".equals(result)) {
                            // Log.e("111111100000", "onSuccess: " );
                            RequestParams params1 = new RequestParams(URL.GETUSERINFO);
                            params1.addParameter("userName", userName);
                            params1.addParameter("passWord", passWord);
                            x.http().post(params1, new CacheCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    //  Log.e("+++++++++++++++++++++", "onSuccess: "+result );
                                    Gson gson = new Gson();
                                    SecondHandUser user = gson.fromJson(result, SecondHandUser.class);
                                    LoginUtil.login(LoginActivity.this, user);

                                    finish();

                                   Intent intent=new Intent();
                                    intent.setAction("login");
                                    sendBroadcast(intent);

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

                        } else{
                            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
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



        //注册按钮跳转注册界面
        login_register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        login_back_image= (ImageView) findViewById(R.id.login_back_image);
        login_name_edittext= (EditText) findViewById(R.id.login_name_edittext);
        login_pwd_edittext= (EditText) findViewById(R.id.login_pwd_edittext);
        logn_button_textview= (TextView) findViewById(R.id.logn_button_textview);
        login_register_textview= (TextView) findViewById(R.id.login_register_textview);
        login_register_textview= (TextView) findViewById(R.id.login_register_textview);

    }
}
