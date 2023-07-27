package com.example.thedstore;

public class PSmodel {
    String img, pname ,price,  deliv,  descp, type,  uniqueid,  s_id,  sname,  semail;


    public PSmodel(String img, String pname, String price, String deliv, String descp, String type, String uniqueid, String s_id, String sname, String semail) {
        this.img = img;
        this.pname = pname;
        this.price = price;
        this.deliv = deliv;
        this.descp = descp;
        this.type = type;
        this.uniqueid = uniqueid;
        this.s_id = s_id;
        this.sname = sname;
        this.semail = semail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliv() {
        return deliv;
    }

    public void setDeliv(String deliv) {
        this.deliv = deliv;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }
}
