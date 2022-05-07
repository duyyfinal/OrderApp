package com.example.orderapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderapp.dao.TaiKhoan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class XacThucTaiKhoanActivity extends AppCompatActivity {
    Button dangNhapButton;
    EditText tenDangNhapEditText;
    EditText matKhauEditText;
    DatabaseReference root;
    DatabaseReference taiKhoan_Tbl;
    ArrayList<TaiKhoan> taiKhoans;
    String idTaiKhoanHienTai;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacthuctaikhoan);
        AnhXa();
        KhoiTao();
        GanSuKien();
    }
    private void AnhXa(){
        dangNhapButton = findViewById(R.id.dangNhapButton);
        tenDangNhapEditText = findViewById(R.id.tenDangNhapEditText);
        matKhauEditText = findViewById(R.id.matKhauEditText);
    }

    private void KhoiTao(){
        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới TaiKhoan_Tbl trong Firebase
        this.taiKhoan_Tbl = root.child("TaiKhoan_Tbl");
        // Tạo mới danh sách tài khoản để chứa dữ liệu các tài khoản trên Firebase
        this.taiKhoans = new ArrayList<TaiKhoan>();

    }

    private void GanSuKien(){
        // Sự kiện nhấn nút đăng nhập
        dangNhapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacThucTaiKhoan();
                //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                //startActivity(intent);
            }
        });

        // Sự kiện đọc dữ liệu trong TaiKhoan_Tbl trên Firebase
        this.taiKhoan_Tbl.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Lấy key xác định tài khoản
                String key = snapshot.getKey();
                // Lấy dữ liệu của tài khoản
                TaiKhoan taiKhoan = snapshot.getValue(TaiKhoan.class);
                // Chuyển key thành id của tài khoản
                taiKhoan.setIdTaiKhoan(key);
                // Thêm tài khoản vào danh sách tài khoản
                taiKhoans.add(taiKhoan);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Lấy key xác định tài khoản
                String key = snapshot.getKey();
                // Lấy dữ liệu của tài khoản
                TaiKhoan taiKhoan = snapshot.getValue(TaiKhoan.class);
                // Duyệt danh sách tài khoản
                for (int i = 0; i < taiKhoans.size(); i++) {
                    // So sánh key nếu đúng thì thay đổi dữ liệu và thoát vòng lặp
                    if (taiKhoans.get(i).getIdTaiKhoan().compareTo(key) == 0) {
                        taiKhoans.set(i, taiKhoan);
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // Lấy key xác định tài khoản
                String key = snapshot.getKey();
                // Duyệt danh sách tài khoản
                for (int i = 0; i < taiKhoans.size(); i++) {
                    // So sánh key nếu đúng thì xóa tài khoản tại vị trí thứ i
                    if (taiKhoans.get(i).getIdTaiKhoan().compareTo(key) == 0) {
                        taiKhoans.remove(i);
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

    private void XacThucTaiKhoan(){
        // Lấy input
        String tenDangNhap = this.tenDangNhapEditText.getText().toString();
        String matKhau = this.matKhauEditText.getText().toString();
        // Khởi tạo biến lưu kết quả xác thực tài khoản
        boolean xacThucThanhCong = false;

        // Kiểm tra có tồn tại tài khoản và mật khẩu trong Firebase không ?
        for (int i = 0; i < taiKhoans.size(); i++) {
            // Tham chiếu đến tài khoản thứ i trong danh sách tài khoản.
            // Nếu đúng sẽ cập nhật biến xacThucThanhCong == true và lưu tài khoản vào
            // biến taiKhoanHienTai
            TaiKhoan taiKhoan = taiKhoans.get(i);
            if (taiKhoan.getTenDangNhap().compareTo(tenDangNhap) == 0  && taiKhoan.getMatKhau().compareTo(matKhau) == 0 ) {
                xacThucThanhCong = true;
                idTaiKhoanHienTai = taiKhoan.getIdTaiKhoan();
                break;
            }
        }
        
        // Kiểm tra kết quả xác thực
        if (!xacThucThanhCong) {
           DangNhapThatBai();  
        }
        else{
            DangNhapThanhCong();
        }
    }
    
    private void DangNhapThatBai(){
        Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        // To Do
    }
    
    private void DangNhapThanhCong(){
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Log.d("TaiKhoanHienTai",idTaiKhoanHienTai);
        // Tạo mới một intent, lưu giá trị id tài khoản hiện tại và chuyển qua TrangChuActivity
        Intent hienThiTrangChuActivityIntent = new Intent(getApplicationContext(), TrangChuActivity.class);
        hienThiTrangChuActivityIntent.putExtra("idTaiKhoanHienTai", this.idTaiKhoanHienTai);
        startActivity(hienThiTrangChuActivityIntent);
    }
}