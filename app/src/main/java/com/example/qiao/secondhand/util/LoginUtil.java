package com.example.qiao.secondhand.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.qiao.secondhand.bean.SecondHandUser;

/**
 * Created by qiao on 2017/4/15.
 */

public class LoginUtil {

    private static SharedPreferences preferences;


    //判断是否登录
    public static boolean isLogin(Context context){
        preferences=context.getSharedPreferences("USER",Context.MODE_PRIVATE);

        //取姓名
        String userName= preferences.getString("userName","");

        if(TextUtils.isEmpty(userName)){
            return false;
        }else {
            return true;
        }
    }


    //存储用户数据
    public static void login(Context context,SecondHandUser user){

        preferences=context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        /**
         * UserID : 3
         * UserName : 李四
         * UserPwd : 123456
         * UserSex : 男
         * UserAge : 18
         * UserSignature : 空
         * UserClass : 1
         * UserMobile : 15610036197
         * UserCreateTime : Apr 15, 2017 10:51:21 AM
         * UserImage : 2.png
         * CollegeID : {"CollegeID":6,"CollegeDesc":"计算机科学与工程学院"}
         */
        editor.putString("userName",user.getUserName())
                .putInt("userID",user.getUserID())
                .putString("userPwd",user.getUserPwd())
                .putString("userSex",user.getUserSex())
                .putString("userSignature",user.getUserSignature())
                .putInt("userClass",user.getUserClass())
                .putString("userMobile",user.getUserMobile())
                .putString("userCreateTime",user.getUserCreateTime())
                .putString("userImage",user.getUserImage())
                .putInt("collegeID",user.getCollegeID().getCollegeID())
                .putString("collegeDesc",user.getCollegeID().getCollegeDesc())
                .putInt("userAge",user.getUserAge()).commit();





    }

}
