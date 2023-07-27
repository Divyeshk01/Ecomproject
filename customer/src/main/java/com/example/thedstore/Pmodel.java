package com.example.thedstore;

public class Pmodel {
    String p_img,p_name,p_prc,p_delivery,p_desc,p_type;
    String pid,sid;
    public Pmodel(String p_img, String p_name, String p_prc, String p_delivery, String p_desc, String p_type,String pid,String sid) {
        this.p_img = p_img;
        this.p_name = p_name;
        this.p_prc = p_prc;
        this.p_delivery = p_delivery;
        this.p_desc = p_desc;
        this.p_type = p_type;
        this.pid = pid;
        this.sid=sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_prc() {
        return p_prc;
    }

    public void setP_prc(String p_prc) {
        this.p_prc = p_prc;
    }

    public String getP_delivery() {
        return p_delivery;
    }

    public void setP_delivery(String p_delivery) {
        this.p_delivery = p_delivery;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }
}
