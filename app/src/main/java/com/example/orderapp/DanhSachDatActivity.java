package com.example.orderapp;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderapp.dao.ChiTietHoaDon;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DanhSachDatActivity extends AppCompatActivity {
    ListView lvSanPhamDat;
    ArrayList<ChiTietHoaDon> arrSanPhamDat;
    DanhSachAdapter adapter;

    String idTaiKhoanHienTai, idBanHienTai, idHoaDonHienTai;

    DatabaseReference root;
    DatabaseReference ChiTietHoaDon_Tbl;

    Button btnDoUong;
    Button btnMonAn;
    Button btnXacNhan;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachdat);

        Intent intent = getIntent();
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        idBanHienTai = intent.getStringExtra("idBanHienTai");
        idHoaDonHienTai = intent.getStringExtra("idHoaDonHienTai");

        AnhXa();
        KhoiTao();
        GanSuKien();
    }

    private void  AnhXa(){
        lvSanPhamDat = (ListView) findViewById(R.id.listViewDanhSachDat);
        btnDoUong = (Button) findViewById(R.id.buttonChondouong);
        btnMonAn = (Button) findViewById(R.id.buttonChonmonan);
        btnXacNhan = (Button) findViewById(R.id.buttonXacnhan);
    }

    private void KhoiTao() {
        arrSanPhamDat = new ArrayList<>();
        adapter = new DanhSachAdapter(getApplicationContext(), arrSanPhamDat, arrSanPhamDat);
        lvSanPhamDat.setAdapter(adapter);

        // Trỏ tới root  trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới SanPham_Tbl trong Firebase
        this.ChiTietHoaDon_Tbl = root.child("ChiTietHoaDon_Tbl");
    }

    private void GanSuKien(){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentchonmonan = new Intent(DanhSachDatActivity.this, ChonMonAnActivity.class);
                intentchonmonan.putExtra("idBanHienTai", idBanHienTai);
                intentchonmonan.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intentchonmonan.putExtra("idHoaDonHienTai", idTaiKhoanHienTai);
                startActivity(intentchonmonan);
            }
        });

        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachDatActivity.this, ChonDoUongActivity.class);
                intent.putExtra("idBanHienTai", idBanHienTai);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("idHoaDonHienTai", idHoaDonHienTai);
                startActivity(intent);
            }
        });

        this.ChiTietHoaDon_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon mondadat = snapshot.getValue(ChiTietHoaDon.class);
                mondadat.setIdChiTietHoaDon(keycthoadon);
                if(idHoaDonHienTai.equals(mondadat.getIdHoaDon())){
                    arrSanPhamDat.add(mondadat);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon mondadat = snapshot.getValue(ChiTietHoaDon.class);
                mondadat.setIdChiTietHoaDon(keycthoadon);
                if(idHoaDonHienTai.equals(mondadat.getIdHoaDon())){
                    for (int i = 0; i < arrSanPhamDat.size(); i++) {
                        ChiTietHoaDon mon = arrSanPhamDat.get(i);

                        if (mon.getIdChiTietHoaDon().compareTo(keycthoadon) == 0) {
                            mon = mondadat;
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon mondadat = snapshot.getValue(ChiTietHoaDon.class);
                mondadat.setIdChiTietHoaDon(keycthoadon);
                if(idHoaDonHienTai.equals(mondadat.getIdHoaDon())){
                    for (int i = 0; i < arrSanPhamDat.size(); i++) {
                        ChiTietHoaDon mon = arrSanPhamDat.get(i);

                        if (mon.getIdChiTietHoaDon().compareTo(keycthoadon) == 0) {
                            arrSanPhamDat.remove(mon);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
