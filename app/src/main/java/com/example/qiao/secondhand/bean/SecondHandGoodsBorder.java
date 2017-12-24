package com.example.qiao.secondhand.bean;

/**
 * Created by qiao on 2017/5/17.
 */

public class SecondHandGoodsBorder {

    /**
     * borderID : 1
     * typeID : {"typeID":1,"typeDesc":"校园代步"}
     * borderDesc : 自行车
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
         * typeID : 1
         * typeDesc : 校园代步
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
