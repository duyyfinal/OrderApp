package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMonAn;
    Button btnDoUong;
    String idBanHienTai;
    String idTaiKhoanHienTai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMonAn = (Button) findViewById(R.id.buttonMonAn);
        btnMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDanhSachMonAn = new Intent(MainActivity.this, ChonMonAnActivity.class);
                intentDanhSachMonAn.putExtra("idBanHienTai", idBanHienTai);
                intentDanhSachMonAn.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                startActivity(intentDanhSachMonAn);
            }
        });

        btnDoUong = (Button) findViewById(R.id.buttonDoUong);
        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDanhSachDoUong = new Intent(MainActivity.this, ChonDoUongActivity.class);
                intentDanhSachDoUong.putExtra("idBanHienTai", idBanHienTai);
                intentDanhSachDoUong.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                startActivity(intentDanhSachDoUong);
            }
        });

        //idBanHienTai = getIntent().getStringExtra("idBanHienTai");
        //idTaiKhoanHienTai = getIntent().getStringExtra("idTaiKhoanHienTai");

        idBanHienTai = "-N0fFRpY9rz1LK0MTOtB";
        idTaiKhoanHienTai = "-N0PeT6_4ss920fg6Gt5";
    }
}