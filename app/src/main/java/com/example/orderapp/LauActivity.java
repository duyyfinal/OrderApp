package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LauActivity extends AppCompatActivity {
    ImageButton banNho1IB, banNho2IB, banNho3IB, banVua1IB, banVua2IB, banLonIB;
    TextView lauTV;
    ImageView logoIV;
    String idTaiKhoanHienTai, lauDaChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lau);

        Intent intent = getIntent();
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        lauDaChon = intent.getStringExtra("lauDaChon");

        AnhXa();
        GanSuKien();
        ThietLapGiaoDien();
    }

    private void AnhXa(){
        banNho1IB = findViewById(R.id.banNho1IB);
        banNho2IB = findViewById(R.id.banNho2IB);
        banNho3IB = findViewById(R.id.banNHo3IB);
        banVua1IB = findViewById(R.id.banVua1IB);
        banVua2IB = findViewById(R.id.banVua2IB);
        banLonIB = findViewById(R.id.banLonIB);
        lauTV = findViewById(R.id.lauTV);
        logoIV = findViewById(R.id.logoIV);
    }

    private void GanSuKien(){
        // Gán sự kiên khi nhấn vào bàn nhỏ 1
        banNho1IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "1");
                startActivity(intent);
            }
        });

        // Gán sự kiện khi nhấn vào bàn nhỏ 2
        banNho2IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "2");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào bàn nhỏ 3
        banNho3IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "3");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào bàn vừa 1
        banVua1IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "4");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào bàn vừa 2
        banVua2IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "5");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào bàn lớn
        banLonIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TinhTrangBanActivity.class);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("lauDaChon", lauDaChon);
                intent.putExtra("banDaChon", "6");
                startActivity(intent);

            }
        });

        // Gán sự kiện khi nhấn vào logo
        logoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // TODO: quay về trang chủ
            }
        });
    }

    private void ThietLapGiaoDien(){

        switch ( lauDaChon ) {
            case  "G":
                lauTV.setText("Lầu G");
                break;
            case  "1":
                lauTV.setText("Lầu 1");
                break;
            case  "2":
                lauTV.setText("Lầu 2");
                break;
            case  "3":
                lauTV.setText("Lầu 3");
                break;
            default:
                lauTV.setText("Vô lý");
        }

    }



}