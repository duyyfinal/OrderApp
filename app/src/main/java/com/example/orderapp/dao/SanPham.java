package com.example.orderapp.dao;

public class SanPham {
    String idSanPham;
    String tenSanPham;
    double donGia;
    String diaChiAnh;
    String idLoaiSanPham;

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

    public String getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(String idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public SanPham() {
    }

    public SanPham(String tenSanPham, double donGia, String diaChiAnh, String idLoaiSanPham) {
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.diaChiAnh = diaChiAnh;
        this.idLoaiSanPham = idLoaiSanPham;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "idSanPham='" + idSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", donGia=" + donGia +
                ", diaChiAnh='" + diaChiAnh + '\'' +
                ", idLoaiSanPham='" + idLoaiSanPham + '\'' +
                '}';
    }
}
