package com.example.qiao.secondhand.bean;

/**
 * Created by qiao on 2017/4/15.
 */

public class SecondHandUser {

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

    private int UserID;
    private String UserName;
    private String UserPwd;
    private String UserSex;
    private int UserAge;
    private String UserSignature;
    private int UserClass;
    private String UserMobile;
    private String UserCreateTime;
    private String UserImage;
    private CollegeIDBean CollegeID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String UserPwd) {
        this.UserPwd = UserPwd;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String UserSex) {
        this.UserSex = UserSex;
    }

    public int getUserAge() {
        return UserAge;
    }

    public void setUserAge(int UserAge) {
        this.UserAge = UserAge;
    }

    public String getUserSignature() {
        return UserSignature;
    }

    public void setUserSignature(String UserSignature) {
        this.UserSignature = UserSignature;
    }

    public int getUserClass() {
        return UserClass;
    }

    public void setUserClass(int UserClass) {
        this.UserClass = UserClass;
    }

    public String getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(String UserMobile) {
        this.UserMobile = UserMobile;
    }

    public String getUserCreateTime() {
        return UserCreateTime;
    }

    public void setUserCreateTime(String UserCreateTime) {
        this.UserCreateTime = UserCreateTime;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String UserImage) {
        this.UserImage = UserImage;
    }

    public CollegeIDBean getCollegeID() {
        return CollegeID;
    }

    public void setCollegeID(CollegeIDBean CollegeID) {
        this.CollegeID = CollegeID;
    }

    public static class CollegeIDBean {
        /**
         * CollegeID : 6
         * CollegeDesc : 计算机科学与工程学院
         */

        private int CollegeID;
        private String CollegeDesc;

        public int getCollegeID() {
            return CollegeID;
        }

        public void setCollegeID(int CollegeID) {
            this.CollegeID = CollegeID;
        }

        public String getCollegeDesc() {
            return CollegeDesc;
        }

        public void setCollegeDesc(String CollegeDesc) {
            this.CollegeDesc = CollegeDesc;
        }
    }
}
