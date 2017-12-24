package com.example.qiao.secondhand.bean;

/**
 * Created by qiao on 2017/5/24.
 */

public class ChatBean {

    private String image;

    private String text;

    private int type;

    public ChatBean(String image, String text, int type) {
        this.image = image;
        this.text = text;
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(int type) {
        this.type = type;
    }
}
