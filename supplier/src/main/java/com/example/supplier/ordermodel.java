package com.example.supplier;

public class ordermodel {
    String pid,cid,payment,type,date,time,order_id;
    String qty;

    public ordermodel(String pid, String cid, String qty, String payment, String type, String date, String time, String order_id) {
        this.pid = pid;
        this.cid = cid;
        this.payment = payment;
        this.type = type;
        this.date = date;
        this.time = time;
        this.qty = qty;
        this.order_id=order_id;
    }

    public String getPid() {
        return pid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }




}
