package com.example.orderapp.dao;

public class TaiKhoan {

    String idTaiKhoan;
    String tenDangNhap;
    String matKhau;
    String chucVu;
    String diaChiAnh;
    String hoTen;
    String soDienThoai;
    String diaChi;

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getDiaChiAnh() {
        return diaChiAnh;
    }

    public void setDiaChiAnh(String diaChiAnh) {
        this.diaChiAnh = diaChiAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String chucVu, String diaChiAnh, String hoTen, String soDienThoai, String diaChi) {

        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        this.diaChiAnh = diaChiAnh;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "idTaiKhoan='" + idTaiKhoan + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", diaChiAnh='" + diaChiAnh + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
