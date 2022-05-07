package com.example.orderapp.dao;

public class LoaiSanPham {
    String idLoaiSanPham;
    String tenLoaiSanPham;
    String diaChiAnh;

    public String getIdLoaiSanPham() {
        return idLoaiSanPham;
    }

    public void setIdLoaiSanPham(String idLoaiSanPham) {
        this.idLoaiSanPham = idLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getDiaChiAnh() {
        return diaChiAnh;
    }

    public void setDiaChiAnh(String diaChiAnh) {
        this.diaChiAnh = diaChiAnh;
    }

    public LoaiSanPham() {
    }

    public LoaiSanPham(String tenLoaiSanPham, String diaChiAnh) {
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.diaChiAnh = diaChiAnh;
    }

    @Override
    public String toString() {
        return "LoaiSanPham{" +
                "idLoaiSanPham='" + idLoaiSanPham + '\'' +
                ", tenLoaiSanPham='" + tenLoaiSanPham + '\'' +
                ", diaChiAnh='" + diaChiAnh + '\'' +
                '}';
    }
}
