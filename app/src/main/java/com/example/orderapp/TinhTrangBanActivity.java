package com.example.orderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orderapp.dao.Ban;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TinhTrangBanActivity extends AppCompatActivity {
    String idTaiKhoanHienTai, lauDaChon, banDaChon;
    ImageView logoIV;
    TextView banTV;
    CheckBox trongCB, coKhachCB, baoTriCB, datCB;
    Button tTDatBanBT, capNhatBT;
    int tinhTrang;
    ArrayList<Ban> banArrayList;

    DatabaseReference root;
    DatabaseReference Ban_Tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtrangban);

        Intent intent = getIntent();
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        lauDaChon = intent.getStringExtra("lauDaChon");
        banDaChon = intent.getStringExtra("banDaChon");

        AnhXa();
        KhoiTao();
        GanSuKien();
        ThietLapGiaoDien();
    }

    private void AnhXa(){
        logoIV = findViewById(R.id.logoIV);
        trongCB = findViewById(R.id.trongCB);
        coKhachCB = findViewById(R.id.coKhachCB);
        baoTriCB = findViewById(R.id.baoTriCB);
        datCB = findViewById(R.id.datCB);
        tTDatBanBT = findViewById(R.id.tTDatBanBT);
        capNhatBT = findViewById(R.id.capNhatBT);
        banTV = findViewById(R.id.banTV);
    }

    private void KhoiTao(){
        root = FirebaseDatabase.getInstance().getReference();
        Ban_Tbl = root.child("Ban_Tbl");
        banArrayList = new ArrayList<>();
    }

    private void GanSuKien(){
        // Gán sự kiện khi nhấn vào trống checkbox
        trongCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 0;
                coKhachCB.setChecked(false);
                baoTriCB.setChecked(false);
                datCB.setChecked(false);

            }
        });

        // Gán sự kiện khi nhấn vào có khách checkbox
        coKhachCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 1;
                trongCB.setChecked(false);
                baoTriCB.setChecked(false);
                datCB.setChecked(false);

            }
        });

        // Gán sự kiện khi nhấn vào bảo trì checkbox
        baoTriCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 2;
                trongCB.setChecked(false);
                coKhachCB.setChecked(false);
                datCB.setChecked(false);
            }
        });

        // Gán sự kiện khi nhấn vào đặt checkbox
        datCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 3;
                trongCB.setChecked(false);
                coKhachCB.setChecked(false);
                baoTriCB.setChecked(false);

            }
        });

        // Gán sự kiện khi nhấn vào thông tin đặt bàn button
        tTDatBanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongTinDatBanActivity.class);

                String tenBan = lauDaChon+"-"+banDaChon;

                // Truyền dữ liệu vô intent là một id bàn hiện tại
                for (int i = 0; i < banArrayList.size(); i++) {
                    if (banArrayList.get(i).getTenBan().compareTo(tenBan) == 0) {
                        String idBan = banArrayList.get(i).getIdBan();
                        intent.putExtra("idBanHienTai", idBan);
                        break;
                    }
                }
                intent.putExtra("iTaiKhoanHienTai", idTaiKhoanHienTai);
                startActivity(intent);
            }
        });

        // Gán sự kiện khi nhấn vào cập nhật button
        capNhatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenBan = lauDaChon+"-"+banDaChon;
                for (int i = 0; i < banArrayList.size(); i++) {

                    if (banArrayList.get(i).getTenBan().compareTo(tenBan) == 0) {
                        String idBan = banArrayList.get(i).getIdBan();
                        Ban ban = banArrayList.get(i);
                        ban.setTrangThai(tinhTrang);

                        // Cập nhât firebase
                        Map<String, Object> map = new HashMap<>();
                        map.put(idBan, ban);
                        Ban_Tbl.updateChildren(map);
                        Intent intent = new Intent(TinhTrangBanActivity.this, MainActivity.class);
                        intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                        intent.putExtra("idBanHienTai", banDaChon);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        // Gán sự kiện khi nhấn vào logo
        logoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Đọc dữ liệu trong Ban_Tbl
        Ban_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ban ban = snapshot.getValue(Ban.class);
                String key = snapshot.getKey();
                ban.setIdBan(key);
                banArrayList.add(ban);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ThietLapGiaoDien(){
        banTV.setText(banDaChon + " - " + "Lầu " +lauDaChon);

    }
}