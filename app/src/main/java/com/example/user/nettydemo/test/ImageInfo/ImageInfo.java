package com.example.user.nettydemo.test.ImageInfo;

public class ImageInfo {

    private String trid;

    private String lrid;

    public String getTrid() {


        return trid == null ? "" : trid;
    }

    public void setTrid(String trid) {
        this.trid = trid;
    }

    public String getLrid() {


        return lrid == null ? "" : lrid;
    }

    public void setLrid(String lrid) {
        this.lrid = lrid;
    }
}
