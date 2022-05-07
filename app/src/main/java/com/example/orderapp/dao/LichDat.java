package com.example.orderapp.dao;

public class LichDat {

    String idLichDat;
    String idBan;
    int gio;
    int phut;
    int ngay;
    int thang;
    int nam;
    String tenKhachHang;
    String soDienThoai;
    String idTaiKhoan;

    public String getIdLichDat() {
        return idLichDat;
    }

    public void setIdLichDat(String idLichDat) {
        this.idLichDat = idLichDat;
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public int getGio() {
        return gio;
    }

    public void setGio(int gio) {
        this.gio = gio;
    }

    public int getPhut() {
        return phut;
    }

    public void setPhut(int phut) {
        this.phut = phut;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public LichDat() {
    }

    public LichDat(String idBan, int gio, int phut, int ngay, int thang, int nam, String tenKhachHang, String soDienThoai, String idTaiKhoan) {
        this.idBan = idBan;
        this.gio = gio;
        this.phut = phut;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.idTaiKhoan = idTaiKhoan;
    }

    @Override
    public String toString() {
        return "LichDat{" +
                "idLichDat='" + idLichDat + '\'' +
                ", idBan='" + idBan + '\'' +
                ", gio=" + gio +
                ", phut=" + phut +
                ", ngay=" + ngay +
                ", thang=" + thang +
                ", nam=" + nam +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", idTaiKhoan='" + idTaiKhoan + '\'' +
                '}';
    }

}
