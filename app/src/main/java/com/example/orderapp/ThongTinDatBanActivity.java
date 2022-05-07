package com.example.orderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderapp.dao.LichDat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ThongTinDatBanActivity extends AppCompatActivity {

    String idTaiKhoanHienTai, idBanHienTai;
    EditText sdtET, tenET, gioEt, ngayET;
    Button capNhatBT;
    String idLichDat;

    DatabaseReference root;
    DatabaseReference LichDat_Tbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtindatban);

        Intent intent = getIntent();
        idBanHienTai = intent.getStringExtra("idBanHienTai");
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");

        AnhXa();
        KhoiTao();
        GanSuKien();
    }

    private void AnhXa(){
        sdtET = findViewById(R.id.sdtET);
        tenET = findViewById(R.id.tenED);
        gioEt = findViewById(R.id.gioET);
        ngayET = findViewById(R.id.ngayET);
        capNhatBT = findViewById(R.id.capNhatBT);
    }

    private void KhoiTao(){
        root = FirebaseDatabase.getInstance().getReference();
        LichDat_Tbl = root.child("LichDat_Tbl");
        idLichDat = "";
    }

    private void GanSuKien(){

        LichDat_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Lấy thông tin và key của mỗi lịch đặt
                LichDat lichDat = snapshot.getValue(LichDat.class);
                String key = snapshot.getKey();
                lichDat.setIdLichDat(key);


                if (idBanHienTai.compareTo(lichDat.getIdBan())==0) {
                    idLichDat = lichDat.getIdLichDat();
                    gioEt.setText(lichDat.getGio());
                    ngayET.setText(lichDat.getNgay());
                    tenET.setText(lichDat.getTenKhachHang());
                    sdtET.setText(lichDat.getSoDienThoai());
                }
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

        // Gán sự kiên cập nhật lịch đặt
        capNhatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Nếu chưa bao giờ có lịch đặt
                if (idLichDat.compareTo("")==0) {
                    LichDat lichDat = new LichDat();

                    int ngay = Integer.parseInt(String.valueOf(ngayET.getText()));
                    int gio = Integer.parseInt(String.valueOf(gioEt.getText()));
                    String ten = tenET.getText().toString();
                    String sdt = sdtET.getText().toString();

                    lichDat.setNgay(ngay);
                    lichDat.setGio(gio);
                    lichDat.setTenKhachHang(ten);
                    lichDat.setSoDienThoai(sdt);
                    lichDat.setIdBan(idBanHienTai);

                    LichDat_Tbl.push().setValue(lichDat);


                }
                // Nếu như đã có lịch đặt trước đó
                else{

                    // Lấy dữ liệu cập nhật
                    LichDat lichDat = new LichDat();
                    int ngay = Integer.parseInt(String.valueOf(ngayET.getText()));
                    int gio = Integer.parseInt(String.valueOf(gioEt.getText()));
                    String ten = tenET.getText().toString();
                    String sdt = sdtET.getText().toString();
                    lichDat.setNgay(ngay);
                    lichDat.setGio(gio);
                    lichDat.setTenKhachHang(ten);
                    lichDat.setSoDienThoai(sdt);
                    lichDat.setIdBan(idBanHienTai);

                    // Tạp map và cập nhật
                    Map<String, Object> map = new HashMap<>();
                    map.put(idLichDat, lichDat);
                    LichDat_Tbl.updateChildren(map);
                }
            }
        });
    }




}