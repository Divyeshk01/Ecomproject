package com.example.thedstore;

public class sellerdetails {
    String sname,semail;

    public sellerdetails(String sname, String semail) {
        this.sname = sname;
        this.semail = semail;
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
