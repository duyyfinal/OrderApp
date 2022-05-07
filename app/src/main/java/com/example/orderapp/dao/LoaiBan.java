package com.example.orderapp.dao;

public class LoaiBan {
    String idLoaiBan;
    String tenLoai;
    int soChoNgoi;

    public String getIdLoaiBan() {
        return idLoaiBan;
    }

    public void setIdLoaiBan(String idLoaiBan) {
        this.idLoaiBan = idLoaiBan;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(int soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }

    public LoaiBan() {
    }

    public LoaiBan(String tenLoai, int soChoNgoi) {
        this.tenLoai = tenLoai;
        this.soChoNgoi = soChoNgoi;
    }

    @Override
    public String toString() {
        return "LoaiBan{" +
                "idLoaiBan='" + idLoaiBan + '\'' +
                ", tenLoai='" + tenLoai + '\'' +
                ", soChoNgoi=" + soChoNgoi +
                '}';
    }
}
