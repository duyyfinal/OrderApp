package com.example.orderapp.dao;

public class Tang {
    String idTang;
    String tenTang;

    public String getIdTang() {
        return idTang;
    }

    public void setIdTang(String idTang) {
        this.idTang = idTang;
    }

    public String getTenTang() {
        return tenTang;
    }

    public void setTenTang(String tenTang) {
        this.tenTang = tenTang;
    }

    public Tang() {
    }

    public Tang(String tenTang) {
        this.tenTang = tenTang;
    }

    @Override
    public String toString() {
        return "Tang{" +
                "idTang='" + idTang + '\'' +
                ", tenTang='" + tenTang + '\'' +
                '}';
    }
}
