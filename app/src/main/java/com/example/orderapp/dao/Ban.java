package com.example.orderapp.dao;

public class Ban {
    String idBan;
    String tenBan;
    int trangThai;
    String idTang;
    String idLoaiBan;

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdTang() {
        return idTang;
    }

    public void setIdTang(String idTang) {
        this.idTang = idTang;
    }

    public String getIdLoaiBan() {
        return idLoaiBan;
    }

    public void setIdLoaiBan(String idLoaiBan) {
        this.idLoaiBan = idLoaiBan;
    }

    public Ban() {
    }

    public Ban(String tenBan, int trangThai, String idTang, String idLoaiBan) {
        this.tenBan = tenBan;
        this.trangThai = trangThai;
        this.idTang = idTang;
        this.idLoaiBan = idLoaiBan;
    }

    @Override
    public String toString() {
        return "Ban{" +
                "idBan='" + idBan + '\'' +
                ", tenBan='" + tenBan + '\'' +
                ", trangThai=" + trangThai +
                ", idTang='" + idTang + '\'' +
                ", idLoaiBan='" + idLoaiBan + '\'' +
                '}';
    }
}
