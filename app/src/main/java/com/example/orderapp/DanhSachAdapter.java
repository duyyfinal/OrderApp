package com.example.orderapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.orderapp.dao.ChiTietHoaDon;
import com.example.orderapp.dao.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DanhSachAdapter extends BaseAdapter {

    private Context context;
    private List<ChiTietHoaDon> danhSachDat;
    private ArrayList<ChiTietHoaDon> arrSanPhamDat;
    private DatabaseReference root;
    private DatabaseReference chiTietHoaDon_Tbl;

    public DanhSachAdapter(Context context, List<ChiTietHoaDon> danhSachDat, ArrayList<ChiTietHoaDon> arrSanPhamDat) {
        this.context = context;
        this.danhSachDat = danhSachDat;
        this.arrSanPhamDat = arrSanPhamDat;
        this.root = FirebaseDatabase.getInstance().getReference();
        this.chiTietHoaDon_Tbl = root.child("ChiTietHoaDon_Tbl");
    }

    @Override
    public int getCount() {
        return danhSachDat.size();
    }

    @Override
    public Object getItem(int i) {
        return danhSachDat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.sanphamdat, null);

        TextView txtTenSP = view.findViewById(R.id.textViewTenSP);
        TextView txtSoLuong = view.findViewById(R.id.textViewSoluong);
        Button btnXoa = view.findViewById(R.id.buttonXoaSP);

        txtTenSP.setText(danhSachDat.get(i).getTenSanPham());
        txtSoLuong.setText(String.valueOf(danhSachDat.get(i).getSoLuong()));
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                XoaMon(i);
            }
        });

        return view;
    }
    private void XoaMon(int i){
        ChiTietHoaDon mon = danhSachDat.get(i);
        String keymon = mon.getIdChiTietHoaDon();
        for (int j =0; j<arrSanPhamDat.size(); j++){
            ChiTietHoaDon chiTietHoaDon = arrSanPhamDat.get(j);
            String keyCTHoaDon = chiTietHoaDon.getIdChiTietHoaDon();
            if(keymon == keyCTHoaDon){
                chiTietHoaDon_Tbl.child(keyCTHoaDon).removeValue();

                break;
            }
        }
    }
}
