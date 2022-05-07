package com.example.orderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.orderapp.dao.Ban;
import com.example.orderapp.dao.ChiTietHoaDon;
import com.example.orderapp.dao.HoaDon;
import com.example.orderapp.dao.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.security.PrivateKey;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChonMonAnActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayList<SanPham> arrAllSanPham;
    SanPhamAdapter adapter;

    ArrayList<ChiTietHoaDon> arrSanPhamDat;
    ArrayList<HoaDon> arrHoaDon;
    ArrayList<Ban> arrBan;

    String idTaiKhoanHienTai, idBanHienTai, idHoaDonHienTai;
    String idLoaiSanPham_MonAn = "-N0f6id8wDF6PSWuuqqR";

    DatabaseReference root;
    DatabaseReference sanPham_Tbl;
    DatabaseReference chiTietHoaDon_Tbl;
    DatabaseReference hoaDon_Tbl;
    DatabaseReference ban_Tbl;

    Button btnDoUong;
    Button btnXemLai1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonmonan);

        // Lấy id tài khoản hiện tại và id bàn hiện tại
        Intent intent = getIntent();
        idTaiKhoanHienTai = intent.getStringExtra("idTaiKhoanHienTai");
        idBanHienTai = intent.getStringExtra("idBanHienTai");
        idHoaDonHienTai = intent.getStringExtra("idHoaDonHienTai");

        AnhXa();
        KhoiTao();
        GanSuKien();


    }

    private void AnhXa() {
        lvSanPham = (ListView) findViewById(R.id.listViewMonAn);
        btnDoUong = (Button) findViewById(R.id.buttonChondouong1);
        btnXemLai1 = (Button) findViewById(R.id.buttonXemLai1);
    }

    private void KhoiTao() {
        arrAllSanPham = new ArrayList<>();
        arrSanPhamDat = new ArrayList<>();
        arrHoaDon = new ArrayList<>();
        arrBan = new ArrayList<>();
        adapter = new SanPhamAdapter(getApplicationContext(), arrAllSanPham, arrAllSanPham, arrHoaDon, idBanHienTai, idTaiKhoanHienTai, arrSanPhamDat);
        lvSanPham.setAdapter(adapter);

        // Trỏ tới root  trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới SanPham_Tbl trong Firebase
        this.sanPham_Tbl = root.child("SanPham_Tbl");
        this.chiTietHoaDon_Tbl = root.child("ChiTietHoaDon_Tbl");
        this.hoaDon_Tbl = root.child("HoaDon_Tbl");
        this.ban_Tbl = root.child("Ban_Tbl");

    }

    private void GanSuKien() {

        ThongTinHoaDon();
        ThongTinChiTietHoaDon();

        btnDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChonMonAnActivity.this, ChonDoUongActivity.class);
                intent.putExtra("idBanHienTai", idBanHienTai);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("idHoaDonHienTai", idHoaDonHienTai);
                startActivity(intent);
            }
        });
        btnXemLai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChonMonAnActivity.this, DanhSachDatActivity.class);
                intent.putExtra("idBanHienTai", idBanHienTai);
                intent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                intent.putExtra("idHoaDonHienTai", idHoaDonHienTai);
                startActivity(intent);

            }
        });


        this.sanPham_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                String idSanPham = snapshot.getKey();
                sanPham.setIdSanPham(idSanPham);
                if (idLoaiSanPham_MonAn.equals(sanPham.getIdLoaiSanPham())) {
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
                
                // Duyệt danh sách chi tiết hóa đơn. Nếu idchitiethoadon đang xét = idchitiethoadon của snapshot thì cập nhật lại thông tin
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
                idHoaDonHienTai = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(idHoaDonHienTai);
                arrHoaDon.add(hoaDon);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                idHoaDonHienTai = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(idHoaDonHienTai);
                for (int i = 0; i < arrHoaDon.size(); i++) {
                    HoaDon hoaDon1 = arrHoaDon.get(i);

                    if (hoaDon1.getIdHoaDon().compareTo(idHoaDonHienTai) == 0) {
                        hoaDon1 = hoaDon;
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                idHoaDonHienTai = snapshot.getKey();
                HoaDon hoaDon = snapshot.getValue(HoaDon.class);
                hoaDon.setIdHoaDon(idHoaDonHienTai);
                for (int i = 0; i < arrHoaDon.size(); i++) {
                    HoaDon hoaDon1 = arrHoaDon.get(i);

                    if (hoaDon1.getIdHoaDon().compareTo(idHoaDonHienTai) == 0) {
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