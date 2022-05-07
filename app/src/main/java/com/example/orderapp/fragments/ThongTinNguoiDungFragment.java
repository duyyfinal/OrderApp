package com.example.orderapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.orderapp.R;
import com.example.orderapp.dao.TaiKhoan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongTinNguoiDungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongTinNguoiDungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThongTinNguoiDungFragment() {
        // Required empty public constructor
    }
    
    public ThongTinNguoiDungFragment(Context parentContext){
        this.parentContext = parentContext;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountInfor.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongTinNguoiDungFragment newInstance(String param1, String param2) {
        ThongTinNguoiDungFragment fragment = new ThongTinNguoiDungFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button capNhatThongTinNguoiDungButton;
    EditText hoTenEditText, soDienThoaiEditText, diaChiEditText;
    private TaiKhoan taiKhoanHienTai;
    DatabaseReference root, taiKhoan_Tbl;
    Context parentContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ánh xạ
        View view = inflater.inflate(R.layout.fragment_thongtinnguoidung, container, false);
        capNhatThongTinNguoiDungButton = view.findViewById(R.id.capNhatThongTinNguoiDungButton);
        hoTenEditText = view.findViewById(R.id.hoTenEditText);
        soDienThoaiEditText = view.findViewById(R.id.soDienThoaiEditText);
        diaChiEditText = view.findViewById(R.id.diaChiEditText);

        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới TaiKhoan_Tbl trong Firebase
        this.taiKhoan_Tbl = root.child("TaiKhoan_Tbl");

        // Gán sự kiện cập nhât tài khoản
        capNhatThongTinNguoiDungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hoTenCapNhat = hoTenEditText.getText().toString();
                String diaChiCapNhat = diaChiEditText.getText().toString();
                String soDienThoaiCapNhat = soDienThoaiEditText.getText().toString();
                taiKhoanHienTai.setHoTen(hoTenCapNhat);
                taiKhoanHienTai.setDiaChi(diaChiCapNhat);
                taiKhoanHienTai.setSoDienThoai(soDienThoaiCapNhat);

                Map<String, Object> map = new HashMap<>();
                map.put(taiKhoanHienTai.getIdTaiKhoan(), taiKhoanHienTai);
                taiKhoan_Tbl.updateChildren(map);
                Toast.makeText(parentContext, "Cập nhật thông tin người dùng thành công", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    public void CapNhatThongTinTaiKhoan(TaiKhoan taiKhoan){
        // Cập nhật thông tin tài khoản hiện tại
        this.taiKhoanHienTai = taiKhoan;

        // Cập nhật giao diện hiển thị
        this.hoTenEditText.setText(taiKhoanHienTai.getHoTen());
        this.soDienThoaiEditText.setText(taiKhoanHienTai.getSoDienThoai());
        this.diaChiEditText.setText(taiKhoanHienTai.getDiaChi());
    }

}