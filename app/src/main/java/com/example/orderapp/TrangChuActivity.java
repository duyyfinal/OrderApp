package com.example.orderapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderapp.dao.TaiKhoan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;



public class TrangChuActivity extends AppCompatActivity {

    DatabaseReference root;
    DatabaseReference taiKhoan_Tbl;
    CircularImageView anhDaiDienCircularImageView;
    TextView tenTextView, chucVuTextView, soDienThoaiTextView, diaChiTextView;
    Button diemDanhButton, quanLyBanButton;
    String idTaiKhoanHienTai;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        AnhXa();
        KhoiTao();
        GanSuKien();


    }
    private void AnhXa(){
        this.anhDaiDienCircularImageView = findViewById(R.id.anhDaiDienCircularImageView);
        this.tenTextView = findViewById(R.id.tenTextView);
        this.chucVuTextView = findViewById(R.id.chucVuTextView);
        this.soDienThoaiTextView = findViewById(R.id.soDienThoaiTextView);
        this.diaChiTextView = findViewById(R.id.diachiTextView);
        this.diemDanhButton = findViewById(R.id.diemDanhButton);
        quanLyBanButton = findViewById(R.id.quanLyBanButton);
    }

    private void KhoiTao(){
        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới TaiKhoan_Tbl trong Firebase
        this.taiKhoan_Tbl = root.child("TaiKhoan_Tbl");
        // Lấy id tài khoản hiện tại truyền từ XacThucTaiKhoanActivity
        this.idTaiKhoanHienTai = getIntent().getStringExtra("idTaiKhoanHienTai");
    }

    private void GanSuKien(){

        // Gán sự kiện đọc dữ liệu từ TaiKhoan_Tbl
        this.taiKhoan_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Load thông tin tài khoản với idTaiKhoan bằng với idTaiKhoan hiện tại
                String idTaiKhoan = snapshot.getKey();
                if (idTaiKhoan.compareTo(idTaiKhoanHienTai)==0) {

                    //Lấy thông tin tài khoản
                    TaiKhoan taiKhoan = snapshot.getValue(TaiKhoan.class);
                    // Hiển thị thông tin tài khoản lên giao diện
                    Picasso.get().load(taiKhoan.getDiaChiAnh()).into(anhDaiDienCircularImageView);
                    tenTextView.setText(taiKhoan.getHoTen());
                    chucVuTextView.setText(taiKhoan.getChucVu());
                    diaChiTextView.setText(taiKhoan.getDiaChi());
                    soDienThoaiTextView.setText(taiKhoan.getSoDienThoai());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                // Kiểm tra có phải tài khoản hiện tại không
                String idTaiKhoan = snapshot.getKey();
                if (idTaiKhoan.compareTo(idTaiKhoanHienTai)==0) {

                    //Lấy thông tin tài khoản
                    TaiKhoan taiKhoan = snapshot.getValue(TaiKhoan.class);
                    //Hiển thị thông tin tài khoản lên giao diện
                    Picasso.get().load(taiKhoan.getDiaChiAnh()).into(anhDaiDienCircularImageView);
                    tenTextView.setText(taiKhoan.getHoTen());
                    chucVuTextView.setText(taiKhoan.getChucVu());
                    diaChiTextView.setText(taiKhoan.getDiaChi());
                    soDienThoaiTextView.setText(taiKhoan.getSoDienThoai());

                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                // Kiểm tra có phải tài khoản hiện tại không
                String idTaiKhoan = snapshot.getKey();
                if (idTaiKhoan.compareTo(idTaiKhoanHienTai)==0) {

                    // Thiết lập mặc định
                    Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(anhDaiDienCircularImageView);
                    tenTextView.setText("");
                    chucVuTextView.setText("");
                    diaChiTextView.setText("");
                    soDienThoaiTextView.setText("");
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Gán sự kiện onClick vào ảnh đại diện
        this.anhDaiDienCircularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hienThiCapNhatTaiKhoanActivity = new Intent(getApplicationContext(), CapNhatTaiKhoanActivity.class);
                hienThiCapNhatTaiKhoanActivity.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                startActivity(hienThiCapNhatTaiKhoanActivity);
            }
        });

        //Gán sự kiện nhấn vào nút quản lý bàn
        this.quanLyBanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hienThiGiaoDienQuanLyBanIntent = new Intent(getApplicationContext(), QuanLyCaiBanActivity.class);
                hienThiGiaoDienQuanLyBanIntent.putExtra("idTaiKhoanHienTai", idTaiKhoanHienTai);
                startActivity(hienThiGiaoDienQuanLyBanIntent);
            }
        });




    }
}