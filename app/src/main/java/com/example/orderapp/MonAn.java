package com.example.orderapp;

public class MonAn {

    private String tenMonAn;
    private int donGia;


    public MonAn(String tenMonAn, int donGia) {

        this.tenMonAn = tenMonAn;
        this.donGia = donGia;

    }



    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }



}
