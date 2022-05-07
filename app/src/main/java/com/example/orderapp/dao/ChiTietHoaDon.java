package com.example.orderapp.dao;

public class ChiTietHoaDon {
    String idChiTietHoaDon;
    String idHoaDon;
    String idSanPham;
    String tenSanPham;
    double donGia;
    String diaChiAnh;
    int soLuong;
    double thanhTien;

    public String getIdChiTietHoaDon() {
        return idChiTietHoaDon;
    }

    public void setIdChiTietHoaDon(String idChiTietHoaDon) {
        this.idChiTietHoaDon = idChiTietHoaDon;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getDiaChiAnh() {
        return diaChiAnh;
    }

    public void setDiaChiAnh(String diaChiAnh) {
        this.diaChiAnh = diaChiAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String idHoaDon, String idSanPham, String tenSanPham, double donGia, String diaChiAnh, int soLuong, double thanhTien) {
        this.idHoaDon = idHoaDon;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.diaChiAnh = diaChiAnh;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "idChiTietHoaDon='" + idChiTietHoaDon + '\'' +
                ", idHoaDon='" + idHoaDon + '\'' +
                ", idSanPham='" + idSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", donGia=" + donGia +
                ", diaChiAnh='" + diaChiAnh + '\'' +
                ", soLuong=" + soLuong +
                ", thanhTien=" + thanhTien +
                '}';
    }
}
