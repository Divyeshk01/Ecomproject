package com.example.supplier;

public class Pro_model {

    String delivery,desc,imgurl,name,price,type;

    public Pro_model(String imgurl, String name, String price, String delivery, String desc, String type) {
        this.imgurl = imgurl;
        this.name = name;
        this.price = price;
        this.delivery = delivery;
        this.desc = desc;
        this.type = type;
    }

    public Pro_model() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
