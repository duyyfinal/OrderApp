package com.example.orderapp.dao;

public class HoaDon {
    String idHoaDon;
    int ngayLap;
    int thangLap;
    int namLap;
    int gioLap;
    int phutLap;
    String idBan;
    String idTaiKhoan;
    int trangThai;

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(int ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getThangLap() {
        return thangLap;
    }

    public void setThangLap(int thangLap) {
        this.thangLap = thangLap;
    }

    public int getNamLap() {
        return namLap;
    }

    public void setNamLap(int namLap) {
        this.namLap = namLap;
    }

    public int getGioLap() {
        return gioLap;
    }

    public void setGioLap(int gioLap) {
        this.gioLap = gioLap;
    }

    public int getPhutLap() {
        return phutLap;
    }

    public void setPhutLap(int phutLap) {
        this.phutLap = phutLap;
    }

    public String getIdBan() {
        return idBan;
    }

    public void setIdBan(String idBan) {
        this.idBan = idBan;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDon() {
    }

    public HoaDon(int ngayLap, int thangLap, int namLap, int gioLap, int phutLap, String idBan, String idTaiKhoan, int trangThai) {
        this.ngayLap = ngayLap;
        this.thangLap = thangLap;
        this.namLap = namLap;
        this.gioLap = gioLap;
        this.phutLap = phutLap;
        this.idBan = idBan;
        this.idTaiKhoan = idTaiKhoan;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "idHoaDon='" + idHoaDon + '\'' +
                ", ngayLap=" + ngayLap +
                ", thangLap=" + thangLap +
                ", namLap=" + namLap +
                ", gioLap=" + gioLap +
                ", phutLap=" + phutLap +
                ", idBan='" + idBan + '\'' +
                ", idTaiKhoan='" + idTaiKhoan + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
