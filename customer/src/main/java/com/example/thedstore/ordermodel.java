package com.example.thedstore;

import android.text.format.Time;

public class ordermodel {
    String product_id, customer_uid,total_payment,payment_type,date,time;
    int qty;


    public ordermodel(String product_id, String customer_uid, int qty, String total_payment, String payment_type, String date, String time) {
        this.product_id = product_id;
        this.customer_uid = customer_uid;
        this.qty = qty;
        this.total_payment = total_payment;
        this.payment_type = payment_type;
        this.date = date;
        this.time = time;
    }


    public String getProduct_id() {
        return product_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_uid() {
        return customer_uid;
    }

    public void setCustomer_uid(String customer_uid) {
        this.customer_uid = customer_uid;
    }




    public String getTotal_payment() {
        return total_payment;
    }

    public void setTotal_payment(String total_payment) {
        this.total_payment = total_payment;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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
