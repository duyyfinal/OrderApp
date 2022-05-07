package com.example.orderapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.orderapp.dao.TaiKhoan;
import com.example.orderapp.fragments.MatKhauTaiKhoanFragment;
import com.example.orderapp.fragments.ThongTinNguoiDungFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;



public class CapNhatTaiKhoanActivity extends AppCompatActivity {

    DatabaseReference root,taiKhoan_Tbl;
    ThongTinNguoiDungFragment thongTinNguoiDungFragment;
    MatKhauTaiKhoanFragment matKhauTaiKhoanFragment;
    Button thongTinNguoiDungButton, matKhauTaiKhoanButton;
    CircularImageView anhDaiDienCircularImageView2;
    String idTaiKhoanHienTai;
    TaiKhoan taiKhoanHienTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhattaikhoan);
        AnhXa();
        KhoiTao();
        GanSuKien();
        CauHinhGiaoDien();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    private void AnhXa(){
        this.thongTinNguoiDungButton = findViewById(R.id.thongTinNguoiDungButton);
        this.matKhauTaiKhoanButton = findViewById(R.id.matKhauTaiKhoanButton);
        this.anhDaiDienCircularImageView2 = findViewById(R.id.anhDaiDienCircularImageView2);

    }

    private void KhoiTao(){
        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới TaiKhoan_Tbl trong Firebase
        this.taiKhoan_Tbl = root.child("TaiKhoan_Tbl");
        // Lấy id tài khoản hiện tại truyền từ XacThucTaiKhoanActivity
        this.idTaiKhoanHienTai = getIntent().getStringExtra("idTaiKhoanHienTai");
        // Khởi tạo các fragment
        thongTinNguoiDungFragment = new ThongTinNguoiDungFragment(getApplicationContext());
        matKhauTaiKhoanFragment = new MatKhauTaiKhoanFragment(getApplicationContext());

    }

    private void GanSuKien(){

        //Gán sự kiện chuyển đổi giao diện giữa 2 Fragment: thông tin người dùng, mật khẩu tài khoản
        this.thongTinNguoiDungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(thongTinNguoiDungFragment);

            }
        });
        this.matKhauTaiKhoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(matKhauTaiKhoanFragment);
            }
        });

        //Gán sự kiện đọc thông tin từ TaiKhoan_Tbl trên Firebase
        this.taiKhoan_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Load thông tin tài khoản với idTaiKhoan bằng với idTaiKhoan hiện tại
                String idTaiKhoan = snapshot.getKey();
                if (idTaiKhoan.compareTo(idTaiKhoanHienTai)==0) {

                    //Lưu thông tin tài khoản
                    taiKhoanHienTai = snapshot.getValue(TaiKhoan.class);
                    taiKhoanHienTai.setIdTaiKhoan(idTaiKhoan);

                    //Hiển thị thông tin tài khoản
                    Picasso.get().load(taiKhoanHienTai.getDiaChiAnh()).into(anhDaiDienCircularImageView2);
                    thongTinNguoiDungFragment.CapNhatThongTinTaiKhoan(taiKhoanHienTai);
                    matKhauTaiKhoanFragment.CapNhatThongTinTaiKhoan(taiKhoanHienTai);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Load thông tin tài khoản với idTaiKhoan bằng với idTaiKhoan hiện tại
                String idTaiKhoan = snapshot.getKey();
                if (idTaiKhoan.compareTo(idTaiKhoanHienTai)==0) {

                    //Lưu thông tin tài khoản
                    taiKhoanHienTai = snapshot.getValue(TaiKhoan.class);
                    taiKhoanHienTai.setIdTaiKhoan(idTaiKhoan);

                    //Hiển thị thông tin tài khoản
                    Picasso.get().load(taiKhoanHienTai.getDiaChiAnh()).into(anhDaiDienCircularImageView2);
                    thongTinNguoiDungFragment.CapNhatThongTinTaiKhoan(taiKhoanHienTai);
                    matKhauTaiKhoanFragment.CapNhatThongTinTaiKhoan(taiKhoanHienTai);
                }
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

    private void CauHinhGiaoDien() {
        // load fragment
        loadFragment(thongTinNguoiDungFragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.constraint_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}