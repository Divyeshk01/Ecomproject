package com.example.thedstore;

public class Mycustorder   {
    String pid, sid, paym,  qty, ptype,  date, time,  orderid;

    public Mycustorder(String pid, String sid, String paym, String qty, String ptype, String date, String time, String orderid) {
        this.pid = pid;
        this.sid = sid;
        this.paym = paym;
        this.qty = qty;
        this.ptype = ptype;
        this.date = date;
        this.time = time;
        this.orderid = orderid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPaym() {
        return paym;
    }

    public void setPaym(String paym) {
        this.paym = paym;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
