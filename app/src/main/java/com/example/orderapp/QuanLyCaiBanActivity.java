package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class QuanLyCaiBanActivity extends AppCompatActivity {
    ImageButton lau3IB, lau2IB, lau1IB, lauGIB;
    ImageView logoIV;
    String idTaiKhoanHienTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlycaiban);


        // Lấy dữ liệu được truyền từ Activity đằng trước
        //Intent intent = getIntent();
        //idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        idTaiKhoanHienTai = "-N0PeT6_4ss920fg6Gt5";

        AnhXa();
        GanSuKien();


    }

    private void AnhXa(){
        lau3IB = findViewById(R.id.lau3IB);
        lau2IB = findViewById(R.id.lau2IB);
        lau1IB = findViewById(R.id.lau1IB);
        lauGIB = findViewById(R.id.lauGIB);
        logoIV = findViewById(R.id.logoIV);
    }

    private void GanSuKien(){

        // Gán sự kiện khi nhấn vào tầng G
        lauGIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LauActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", "G");
                startActivity(intent);
            }
        });

        // Gán sự kiện khi nhấn vào tầng 1
        lau1IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LauActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", "1");
                startActivity(intent);
            }
        });

        // Gán sự kiện khi nhấn vào tầng 2
        lau2IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LauActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", "2");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhán vào tầng 3
        lau3IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LauActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", "3");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào logo
        logoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: quay về trang chủ
            }
        });
    }
}