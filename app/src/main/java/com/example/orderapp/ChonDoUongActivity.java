package com.example.orderapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderapp.dao.Ban;
import com.example.orderapp.dao.ChiTietHoaDon;
import com.example.orderapp.dao.HoaDon;
import com.example.orderapp.dao.SanPham;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChonDoUongActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayList<SanPham> arrAllSanPham;
    SanPhamAdapter adapter;

    ArrayList<ChiTietHoaDon> arrSanPhamDat;
    ArrayList<HoaDon> arrHoaDon;
    ArrayList<Ban> arrBan;

    String idTaiKhoanHienTai, idBanHienTai, idHoaDonHienTai;
    String idLoaiSanPham_DoUong = "-N0f6id90a4Xi-EQQnXU";


    DatabaseReference root;
    DatabaseReference sanPham_Tbl;
    DatabaseReference chiTietHoaDon_Tbl;
    DatabaseReference hoaDon_Tbl;
    DatabaseReference ban_Tbl;

    Button btnMonAn;
    Button btnXemLai2;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chondouong);

        Intent intent = getIntent();
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        idBanHienTai = intent.getStringExtra("idBanHienTai");
        idHoaDonHienTai = intent.getStringExtra("idHoaDonHienTai");
        AnhXa();
        KhoiTao();
        GanSuKien();



    }
    private void AnhXa(){
        lvSanPham =(ListView) findViewById(R.id.listViewDoUong);
        btnMonAn = (Button) findViewById(R.id.buttonChonmonan1);
        btnXemLai2 = (Button) findViewById(R.id.buttonXemLai2);
    }
    private void KhoiTao(){
        arrAllSanPham = new ArrayList<>();
        arrSanPhamDat = new ArrayList<>();
        arrHoaDon = new ArrayList<>();
        arrBan = new ArrayList<>();
        adapter = new SanPhamAdapter(getApplicationContext(), arrAllSanPham,arrAllSanPham, arrHoaDon, idBanHienTai, idTaiKhoanHienTai, arrSanPhamDat);
        lvSanPham.setAdapter(adapter);

        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới SanPham_Tbl trong Firebase
        this.sanPham_Tbl = root.child("SanPham_Tbl");
        this.chiTietHoaDon_Tbl = root.child("ChiTietHoaDon_Tbl");
        this.hoaDon_Tbl = root.child("HoaDon_Tbl");
        this.ban_Tbl = root.child("Ban_Tbl");
    }
    private  void GanSuKien(){

        ThongTinHoaDon();
        ThongTinChiTietHoaDon();

        btnMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentchonmonan = new Intent(ChonDoUongActivity.this, ChonMonAnActivity.class);
                intentchonmonan.putExtra("idBanHienTai", idBanHienTai);
                intentchonmonan.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intentchonmonan.putExtra("idHoaDonHienTai", idTaiKhoanHienTai);
                startActivity(intentchonmonan);
            }
        });
        btnXemLai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChonDoUongActivity.this, DanhSachDatActivity.class);
                intent1.putExtra("idBanHienTai", idBanHienTai);
                intent1.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent1.putExtra("idHoaDonHienTai", idHoaDonHienTai);
                startActivity(intent1);

            }
        });

        this.sanPham_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                String idSanPham = snapshot.getKey();
                sanPham.setIdSanPham(idSanPham);
                if(idLoaiSanPham_DoUong.equals(sanPham.getIdLoaiSanPham())) {
                    arrAllSanPham.add(sanPham);
                    adapter.notifyDataSetChanged();
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
    }
    private void ThongTinChiTietHoaDon() {
        this.chiTietHoaDon_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon chiTietHoaDon = snapshot.getValue(ChiTietHoaDon.class);
                chiTietHoaDon.setIdChiTietHoaDon(keycthoadon);
                arrSanPhamDat.add(chiTietHoaDon);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon chiTietHoaDon = snapshot.getValue(ChiTietHoaDon.class);
                chiTietHoaDon.setIdChiTietHoaDon(keycthoadon);
                for (int i = 0; i < arrSanPhamDat.size(); i++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(i);

                    if (mon.getIdChiTietHoaDon().compareTo(keycthoadon) == 0) {
                        mon = chiTietHoaDon;
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String keycthoadon = snapshot.getKey();
                ChiTietHoaDon chiTietHoaDon = snapshot.getValue(ChiTietHoaDon.class);
                chiTietHoaDon.setIdChiTietHoaDon(keycthoadon);
                for (int i = 0; i < arrSanPhamDat.size(); i++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(i);

                    if (mon.getIdChiTietHoaDon().compareTo(keycthoadon) == 0) {
                        arrSanPhamDat.remove(mon);
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

    private void ThongTinHoaDon() {
        this.hoaDon_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keyhoadon = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(keyhoadon);
                arrHoaDon.add(hoaDon);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String keyhoadon = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(keyhoadon);
                for (int i = 0; i < arrHoaDon.size(); i++) {
                    HoaDon hoaDon1 = arrHoaDon.get(i);

                    if (hoaDon1.getIdHoaDon().compareTo(keyhoadon) == 0) {
                        hoaDon1 = hoaDon;
                    }
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String keyhoadon = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(keyhoadon);
                for (int i = 0; i < arrHoaDon.size(); i++) {
                    HoaDon hoaDon1 = arrHoaDon.get(i);

                    if (hoaDon1.getIdHoaDon().compareTo(keyhoadon) == 0) {
                        arrHoaDon.remove(hoaDon1);
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
