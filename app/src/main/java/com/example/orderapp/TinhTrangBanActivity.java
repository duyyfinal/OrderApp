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
        // G??n s??? ki???n khi nh???n v??o tr???ng checkbox
        trongCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 0;
                coKhachCB.setChecked(false);
                baoTriCB.setChecked(false);
                datCB.setChecked(false);

            }
        });

        // G??n s??? ki???n khi nh???n v??o c?? kh??ch checkbox
        coKhachCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 1;
                trongCB.setChecked(false);
                baoTriCB.setChecked(false);
                datCB.setChecked(false);

            }
        });

        // G??n s??? ki???n khi nh???n v??o b???o tr?? checkbox
        baoTriCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 2;
                trongCB.setChecked(false);
                coKhachCB.setChecked(false);
                datCB.setChecked(false);
            }
        });

        // G??n s??? ki???n khi nh???n v??o ?????t checkbox
        datCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinhTrang = 3;
                trongCB.setChecked(false);
                coKhachCB.setChecked(false);
                baoTriCB.setChecked(false);

            }
        });

        // G??n s??? ki???n khi nh???n v??o th??ng tin ?????t b??n button
        tTDatBanBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongTinDatBanActivity.class);

                String tenBan = lauDaChon+"-"+banDaChon;

                // Truy???n d??? li???u v?? intent l?? m???t id b??n hi???n t???i
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

        // G??n s??? ki???n khi nh???n v??o c???p nh???t button
        capNhatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenBan = lauDaChon+"-"+banDaChon;
                for (int i = 0; i < banArrayList.size(); i++) {

                    if (banArrayList.get(i).getTenBan().compareTo(tenBan) == 0) {
                        String idBan = banArrayList.get(i).getIdBan();
                        Ban ban = banArrayList.get(i);
                        ban.setTrangThai(tinhTrang);

                        // C???p nh??t firebase
                        Map<String, Object> map = new HashMap<>();
                        map.put(idBan, ban);
                        Ban_Tbl.updateChildren(map);
                        Intent intent = new Intent(TinhTrangBanActivity.this, MainActivity.class);
                        intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                        intent.putExtra("idBanHienTai", idBan);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        // G??n s??? ki???n khi nh???n v??o logo
        logoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // ?????c d??? li???u trong Ban_Tbl
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
        banTV.setText(banDaChon + " - " + "L???u " +lauDaChon);

    }
}