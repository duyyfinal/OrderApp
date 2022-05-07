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
 * Use the {@link MatKhauTaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatKhauTaiKhoanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MatKhauTaiKhoanFragment() {
        // Required empty public constructor
    }

    public MatKhauTaiKhoanFragment(Context parentContext){
        this.parentContext = parentContext;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountPass.
     */
    // TODO: Rename and change types and number of parameters
    public static MatKhauTaiKhoanFragment newInstance(String param1, String param2) {
        MatKhauTaiKhoanFragment fragment = new MatKhauTaiKhoanFragment();
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

    Button capNhatMatKhauButton;
    EditText matKhauCuEditText, matKhauMoiEditText, nhapLaiMatKhauMoiEditText;
    DatabaseReference root, taiKhoan_Tbl;
    TaiKhoan taiKhoanHienTai;
    Context parentContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Ánh xạ
        View view = inflater.inflate(R.layout.fragment_matkhautaikhoan, container, false);
        capNhatMatKhauButton = view.findViewById(R.id.capNhatMatKhauButton);
        matKhauCuEditText = view.findViewById(R.id.matKhauCuEditText);
        matKhauMoiEditText = view.findViewById(R.id.matKhauMoiEditText);
        nhapLaiMatKhauMoiEditText = view.findViewById(R.id.nhapLaiMatKhauMoiEditText);

        // Khởi tạo
        // Trỏ tới root trong Firebase
        this.root = FirebaseDatabase.getInstance().getReference();
        // Trỏ tới TaiKhoan_Tbl trong Firebase
        this.taiKhoan_Tbl = root.child("TaiKhoan_Tbl");

        //Gán sự kiện
        this.capNhatMatKhauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapNhatMatKhau();
            }
        });

        return view;
    }

    public void CapNhatThongTinTaiKhoan(TaiKhoan taiKhoan){
        this.taiKhoanHienTai = taiKhoan;
    }

    private void CapNhatMatKhau(){
        boolean matKhauCuGiongMatKhauHienTai = false;
        boolean matKhauMoiGiongNhapLaiMatKhauMoi = false;

        String matKhauHienTai = this.taiKhoanHienTai.getMatKhau();
        String matKhauCu = this.matKhauCuEditText.getText().toString();
        String matKhauMoi = this.matKhauMoiEditText.getText().toString();
        String nhapLaiMatKhauMoi = this.nhapLaiMatKhauMoiEditText.getText().toString();


        if (matKhauCu.compareTo(matKhauHienTai) == 0) {
            matKhauCuGiongMatKhauHienTai = true;
        }

        if (matKhauMoi.compareTo(nhapLaiMatKhauMoi) == 0) {
            matKhauMoiGiongNhapLaiMatKhauMoi = true;
        }

        if (matKhauCuGiongMatKhauHienTai && matKhauMoiGiongNhapLaiMatKhauMoi) {
            taiKhoanHienTai.setMatKhau(matKhauMoi);

            Map<String, Object> map = new HashMap<>();
            map.put(taiKhoanHienTai.getIdTaiKhoan(), taiKhoanHienTai);
            taiKhoan_Tbl.updateChildren(map);
            Toast.makeText(parentContext, "Cập nhât mật khẩu thành công", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(parentContext, "Sai mật khẩu hoặc nhập lại mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
        }


    }
}