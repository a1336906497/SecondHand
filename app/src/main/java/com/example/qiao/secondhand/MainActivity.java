package com.example.qiao.secondhand;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.qiao.secondhand.fragment.MainHomeFragment;
import com.example.qiao.secondhand.fragment.MainMyFragment;
import com.example.qiao.secondhand.fragment.MainReleaseFragment;
import com.example.qiao.secondhand.fragment.MainTypeFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private static RadioGroup radiogroup;
    private static FragmentManager manager;
    private static FragmentTransaction fragmentTransaction;
    private MainHomeFragment mainHomeFragment;
    private MainMyFragment mainMyFragment;
    private static MainReleaseFragment mainReleaseFragment;
    private MainTypeFragment mainTypeFragment;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //初始化View
        initView();

        //初始化数据
        initData();

        //fragment管理器
        manager=getSupportFragmentManager();

        fragmentTransaction = manager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment,mainHomeFragment);

        fragmentTransaction.commit();

        RadioButton radioButton= (android.widget.RadioButton) radiogroup.getChildAt(0);
        radioButton.setChecked(true);

        //radiogroup监听
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = manager.beginTransaction();
                switch(checkedId){
                    case R.id.main_radiobutton_home:
                        fragmentTransaction.replace(R.id.main_fragment,mainHomeFragment);
                        break;
                    case  R.id.main_radiobutton_release:
                        fragmentTransaction.replace(R.id.main_fragment,mainReleaseFragment);
                        break;

                    case R.id.main_radiobutton_my:
                        fragmentTransaction.replace(R.id.main_fragment,mainMyFragment);
                        break;

                    case R.id.main_radiobutton_type:
                        fragmentTransaction.replace(R.id.main_fragment,mainTypeFragment);
                        break;
                }

                fragmentTransaction.commit();
            }
        });

    }

    private void initData() {

        mainHomeFragment = new MainHomeFragment();
        mainMyFragment = new MainMyFragment();
        mainReleaseFragment = new MainReleaseFragment();
        mainTypeFragment=new MainTypeFragment();

    }

    private void initView() {
        frameLayout= (FrameLayout) findViewById(R.id.main_fragment);
        radiogroup= (RadioGroup) findViewById(R.id.main_radiogroup);
    }




    //退出时弹出是否退出按钮
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_main,null);
        TextView delete_no_text= (TextView) view.findViewById(R.id.delete_no_text);
        TextView delete_yes_text= (TextView) view.findViewById(R.id.delete_yes_text);
        builder.setView(view);
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
                finish();
            }
        });

        return super.onKeyDown(keyCode, event);
    }

    //选择第三个
    public static void setReleaseFragment(){
        fragmentTransaction = manager.beginTransaction();

        fragmentTransaction.replace(R.id.main_fragment,mainReleaseFragment);

        fragmentTransaction.commit();
        RadioButton radioButton= (android.widget.RadioButton) radiogroup.getChildAt(2);
        radioButton.setChecked(true);

    }
}
