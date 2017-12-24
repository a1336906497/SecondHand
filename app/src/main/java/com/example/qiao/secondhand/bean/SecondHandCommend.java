package com.example.qiao.secondhand.bean;

/**
 * Created by qiao on 2017/5/16.
 */

public class SecondHandCommend {

    /**
     * commentID : 1
     * goods : {"goodsID":4,"type":{"typeID":2,"typeDesc":"手机"},"border":{"borderID":49,"typeID":{"typeID":2,"typeDesc":"手机"},"borderDesc":"手机"},"goodsName":"小米5全网通高配版3+ 64GB手机","goodsText":"小米5全网通高配版3+ 64GB手机","goodsConent":"朋友自用半年左右 4K屏 前几天在酒吧喝酒不小心在茶几上上把开机旁边磕到一下（如图）机器一切正常 无任何问题 其它95新左右 无拆 无修 无进水 所有功能正常 无暗病 只有单机加一条原装耳机 其它无 秒价1850出 不刀 不刀 不刀 重要的事情说三遍 收货24小时内能确认的就联系","goodsPicSmall":"good3_1.jpg","goodsPicLarge":"good3_1.jpg;good3_2.jpg;good3_3.jpg;good3_4.jpg;good3_5.jpg","goodsPriceShop":"1850","Reviews":true,"goodsInTime":"May 15, 2017 4:24:15 PM","user":{"UserID":5,"UserName":"fsafada","UserPwd":"321324","UserSex":"男","UserAge":13,"UserSignature":"dsfsfs","UserClass":6,"UserMobile":"11111111111","UserCreateTime":"May 9, 2017 9:03:31 PM","UserImage":"fsafada.jpeg","CollegeID":{"CollegeID":1,"CollegeDesc":"矿业与安全工程学院"}},"browseTimes":4,"tradingLocation":"科大南门","isActive":true}
     * user : {"UserID":5,"UserName":"fsafada","UserPwd":"321324","UserSex":"男","UserAge":13,"UserSignature":"dsfsfs","UserClass":6,"UserMobile":"11111111111","UserCreateTime":"May 9, 2017 9:03:31 PM","UserImage":"fsafada.jpeg","CollegeID":{"CollegeID":1,"CollegeDesc":"矿业与安全工程学院"}}
     * comment : 我不知道说啥号
     * commentTime : May 16, 2017 4:44:42 PM
     */

    private int commentID;
    private GoodsBean goods;
    private UserBeanX user;
    private String comment;
    private String commentTime;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public static class GoodsBean {
        /**
         * goodsID : 4
         * type : {"typeID":2,"typeDesc":"手机"}
         * border : {"borderID":49,"typeID":{"typeID":2,"typeDesc":"手机"},"borderDesc":"手机"}
         * goodsName : 小米5全网通高配版3+ 64GB手机
         * goodsText : 小米5全网通高配版3+ 64GB手机
         * goodsConent : 朋友自用半年左右 4K屏 前几天在酒吧喝酒不小心在茶几上上把开机旁边磕到一下（如图）机器一切正常 无任何问题 其它95新左右 无拆 无修 无进水 所有功能正常 无暗病 只有单机加一条原装耳机 其它无 秒价1850出 不刀 不刀 不刀 重要的事情说三遍 收货24小时内能确认的就联系
         * goodsPicSmall : good3_1.jpg
         * goodsPicLarge : good3_1.jpg;good3_2.jpg;good3_3.jpg;good3_4.jpg;good3_5.jpg
         * goodsPriceShop : 1850
         * Reviews : true
         * goodsInTime : May 15, 2017 4:24:15 PM
         * user : {"UserID":5,"UserName":"fsafada","UserPwd":"321324","UserSex":"男","UserAge":13,"UserSignature":"dsfsfs","UserClass":6,"UserMobile":"11111111111","UserCreateTime":"May 9, 2017 9:03:31 PM","UserImage":"fsafada.jpeg","CollegeID":{"CollegeID":1,"CollegeDesc":"矿业与安全工程学院"}}
         * browseTimes : 4
         * tradingLocation : 科大南门
         * isActive : true
         */

        private int goodsID;
        private TypeBean type;
        private BorderBean border;
        private String goodsName;
        private String goodsText;
        private String goodsConent;
        private String goodsPicSmall;
        private String goodsPicLarge;
        private String goodsPriceShop;
        private boolean Reviews;
        private String goodsInTime;
        private UserBean user;
        private int browseTimes;
        private String tradingLocation;
        private boolean isActive;

        public int getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(int goodsID) {
            this.goodsID = goodsID;
        }

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public BorderBean getBorder() {
            return border;
        }

        public void setBorder(BorderBean border) {
            this.border = border;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsText() {
            return goodsText;
        }

        public void setGoodsText(String goodsText) {
            this.goodsText = goodsText;
        }

        public String getGoodsConent() {
            return goodsConent;
        }

        public void setGoodsConent(String goodsConent) {
            this.goodsConent = goodsConent;
        }

        public String getGoodsPicSmall() {
            return goodsPicSmall;
        }

        public void setGoodsPicSmall(String goodsPicSmall) {
            this.goodsPicSmall = goodsPicSmall;
        }

        public String getGoodsPicLarge() {
            return goodsPicLarge;
        }

        public void setGoodsPicLarge(String goodsPicLarge) {
            this.goodsPicLarge = goodsPicLarge;
        }

        public String getGoodsPriceShop() {
            return goodsPriceShop;
        }

        public void setGoodsPriceShop(String goodsPriceShop) {
            this.goodsPriceShop = goodsPriceShop;
        }

        public boolean isReviews() {
            return Reviews;
        }

        public void setReviews(boolean Reviews) {
            this.Reviews = Reviews;
        }

        public String getGoodsInTime() {
            return goodsInTime;
        }

        public void setGoodsInTime(String goodsInTime) {
            this.goodsInTime = goodsInTime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getBrowseTimes() {
            return browseTimes;
        }

        public void setBrowseTimes(int browseTimes) {
            this.browseTimes = browseTimes;
        }

        public String getTradingLocation() {
            return tradingLocation;
        }

        public void setTradingLocation(String tradingLocation) {
            this.tradingLocation = tradingLocation;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public static class TypeBean {
            /**
             * typeID : 2
             * typeDesc : 手机
             */

            private int typeID;
            private String typeDesc;

            public int getTypeID() {
                return typeID;
            }

            public void setTypeID(int typeID) {
                this.typeID = typeID;
            }

            public String getTypeDesc() {
                return typeDesc;
            }

            public void setTypeDesc(String typeDesc) {
                this.typeDesc = typeDesc;
            }
        }

        public static class BorderBean {
            /**
             * borderID : 49
             * typeID : {"typeID":2,"typeDesc":"手机"}
             * borderDesc : 手机
             */

            private int borderID;
            private TypeIDBean typeID;
            private String borderDesc;

            public int getBorderID() {
                return borderID;
            }

            public void setBorderID(int borderID) {
                this.borderID = borderID;
            }

            public TypeIDBean getTypeID() {
                return typeID;
            }

            public void setTypeID(TypeIDBean typeID) {
                this.typeID = typeID;
            }

            public String getBorderDesc() {
                return borderDesc;
            }

            public void setBorderDesc(String borderDesc) {
                this.borderDesc = borderDesc;
            }

            public static class TypeIDBean {
                /**
                 * typeID : 2
                 * typeDesc : 手机
                 */

                private int typeID;
                private String typeDesc;

                public int getTypeID() {
                    return typeID;
                }

                public void setTypeID(int typeID) {
                    this.typeID = typeID;
                }

                public String getTypeDesc() {
                    return typeDesc;
                }

                public void setTypeDesc(String typeDesc) {
                    this.typeDesc = typeDesc;
                }
            }
        }

        public static class UserBean {
            /**
             * UserID : 5
             * UserName : fsafada
             * UserPwd : 321324
             * UserSex : 男
             * UserAge : 13
             * UserSignature : dsfsfs
             * UserClass : 6
             * UserMobile : 11111111111
             * UserCreateTime : May 9, 2017 9:03:31 PM
             * UserImage : fsafada.jpeg
             * CollegeID : {"CollegeID":1,"CollegeDesc":"矿业与安全工程学院"}
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
                 * CollegeID : 1
                 * CollegeDesc : 矿业与安全工程学院
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
    }

    public static class UserBeanX {
        /**
         * UserID : 5
         * UserName : fsafada
         * UserPwd : 321324
         * UserSex : 男
         * UserAge : 13
         * UserSignature : dsfsfs
         * UserClass : 6
         * UserMobile : 11111111111
         * UserCreateTime : May 9, 2017 9:03:31 PM
         * UserImage : fsafada.jpeg
         * CollegeID : {"CollegeID":1,"CollegeDesc":"矿业与安全工程学院"}
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
        private CollegeIDBeanX CollegeID;

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

        public CollegeIDBeanX getCollegeID() {
            return CollegeID;
        }

        public void setCollegeID(CollegeIDBeanX CollegeID) {
            this.CollegeID = CollegeID;
        }

        public static class CollegeIDBeanX {
            /**
             * CollegeID : 1
             * CollegeDesc : 矿业与安全工程学院
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
}
