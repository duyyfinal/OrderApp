package com.example.orderapp;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.orderapp.dao.ChiTietHoaDon;
import com.example.orderapp.dao.HoaDon;
import com.example.orderapp.dao.SanPham;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamAdapter extends BaseAdapter{
    private Context context;
    private List<SanPham> sanPhamList;
    private ArrayList<SanPham> arrAllSanPham;
    private ArrayList<HoaDon> arrHoaDon;
    private String idBanHienTai;
    private String idTaiKhoanHienTai;
    private ArrayList<ChiTietHoaDon> arrSanPhamDat;
    private DatabaseReference root;
    private DatabaseReference chiTietHoaDon_Tbl;
    private DatabaseReference hoaDon_Tbl;

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList,ArrayList<SanPham> arrAllSanPham,
                          ArrayList<HoaDon> arrHoaDon, String idBanHienTai, String idTaiKhoanHienTai,
                          ArrayList<ChiTietHoaDon> arrSanPhamDat){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.arrAllSanPham = arrAllSanPham;
        this.arrHoaDon = arrHoaDon;
        this.idBanHienTai = idBanHienTai;
        this.idTaiKhoanHienTai = idTaiKhoanHienTai;
        this.arrSanPhamDat = arrSanPhamDat;
        this.root = FirebaseDatabase.getInstance().getReference();
        this.chiTietHoaDon_Tbl = root.child("ChiTietHoaDon_Tbl");
        this.hoaDon_Tbl = root.child("HoaDon_Tbl");
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int i) {
        return sanPhamList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.sanpham,null);

        ImageView imgSanPham = view.findViewById(R.id.imgVSanPham);
        TextView txtTenSanPham = view.findViewById(R.id.textViewTenMon);
        TextView txtGiaSanPham = view.findViewById(R.id.textViewGia);
        Button themButton = view.findViewById(R.id.buttonThemMon);
        themButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                ThemMon(i);

            }
        });
        Button botButton = view.findViewById(R.id.buttonBotMon);
        botButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                BotMon(i);

            }
        });

        Picasso.get().load(sanPhamList.get(i).getDiaChiAnh()).into(imgSanPham);
        txtTenSanPham.setText(sanPhamList.get(i).getTenSanPham());
        txtGiaSanPham.setText(String.valueOf(sanPhamList.get(i).getDonGia()));
        return view;


    }
    // Chọn món ăn
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ThemMon(int i){
        String thoigianhientai = java.time.LocalDateTime.now().toString();

        int ngay = Integer.parseInt(thoigianhientai.substring(8, 10));
        int thang = Integer.parseInt(thoigianhientai.substring(5, 7));
        int nam = Integer.parseInt(thoigianhientai.substring(0, 4));
        int gio = Integer.parseInt(thoigianhientai.substring(11, 13));
        int phut = Integer.parseInt(thoigianhientai.substring(14, 16));
        SanPham sanPham = arrAllSanPham.get(i);

        boolean daTonTaiHoaDon = false;
        // Duyệt danh sách hóa đơn
        for (int sohoadon = 0; sohoadon < arrHoaDon.size(); sohoadon++) {
            // Lấy hóa đơn thứ i
            HoaDon hoaDon =  arrHoaDon.get(sohoadon);

            // Kiểm tra tồn tại hóa đơn tại bàn đã chọn có trạng thái đang đặt hay không (0=Đang đặt, 1=Đã thanh toán)
            if (hoaDon.getIdBan().compareTo(idBanHienTai) == 0 && hoaDon.getTrangThai() == 0) {

                // thêm chi tiết hóa đơn với idhoadon đã lưu
                boolean daTonTaiMon = false;
                String idHoaDon = hoaDon.getIdHoaDon();
                for (int j = 0; j < arrSanPhamDat.size(); j++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(j);

                    // Kiểm tra tồn tại món chưa ?
                    if (mon.getIdHoaDon().compareTo(idHoaDon) == 0 && mon.getIdSanPham().compareTo(sanPham.getIdSanPham()) == 0) {

                        // Cập nhât số lượng của món đó
                        String keyChiTietHoaDon = mon.getIdChiTietHoaDon();
                        mon.setSoLuong(mon.getSoLuong() + 1);
                        mon.setThanhTien(mon.getDonGia() * mon.getSoLuong());

                        Map<String, Object> map = new HashMap<>();
                        map.put(keyChiTietHoaDon, mon);
                        chiTietHoaDon_Tbl.updateChildren(map);

                        daTonTaiMon = true;
                        break;

                    }
                }

                // Trường hợp chưa có món
                if (daTonTaiMon == false) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(idHoaDon, sanPham.getIdSanPham(), sanPham.getTenSanPham(), sanPham.getDonGia(), sanPham.getDiaChiAnh(), 1, sanPham.getDonGia()*1);
                    chiTietHoaDon_Tbl.push().setValue(chiTietHoaDon);
                }

                daTonTaiHoaDon = true;
                break;
            }
        }
        // Nếu không tồn tại hóa đơn nào tại bàn đã chọn có trang thái đang đặt
        if (daTonTaiHoaDon == false) {

            // thêm mới hóa đơn
            HoaDon hoaDon = new HoaDon(ngay, thang, nam, gio, phut, idBanHienTai, idTaiKhoanHienTai, 0);
            String key = hoaDon_Tbl.push().getKey();
            hoaDon_Tbl.child(key).setValue(hoaDon);

            //Thêm chi tiết hóa đơn
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(key, sanPham.getIdSanPham(), sanPham.getTenSanPham(), sanPham.getDonGia(), sanPham.getDiaChiAnh(), 1, sanPham.getDonGia()*1);
            chiTietHoaDon_Tbl.push().setValue(chiTietHoaDon);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void BotMon(int i){
        String thoigianhientai = java.time.LocalDateTime.now().toString();

        int ngay = Integer.parseInt(thoigianhientai.substring(8, 10));
        int thang = Integer.parseInt(thoigianhientai.substring(5, 7));
        int nam = Integer.parseInt(thoigianhientai.substring(0, 4));
        int gio = Integer.parseInt(thoigianhientai.substring(11, 13));
        int phut = Integer.parseInt(thoigianhientai.substring(14, 16));
        SanPham sanPham = arrAllSanPham.get(i);


        // Duyệt danh sách hóa đơn
        for (int sohoadon = 0; sohoadon < arrHoaDon.size(); sohoadon++) {
            // Lấy hóa đơn thứ i
            HoaDon hoaDon =  arrHoaDon.get(sohoadon);

            // Kiểm tra tồn tại hóa đơn tại bàn đã chọn có trạng thái đang đặt hay không (0=Đang đặt, 1=Đã thanh toán)
            if (hoaDon.getIdBan().compareTo(idBanHienTai) == 0 && hoaDon.getTrangThai() == 0) {

                // Bớt chi tiết hóa đơn với idhoadon đã lưu
                String idHoaDon = hoaDon.getIdHoaDon();
                for (int j = 0; j < arrSanPhamDat.size(); j++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(j);

                    // Kiểm tra tồn tại món chưa ?
                    if (mon.getIdHoaDon().compareTo(idHoaDon) == 0 && mon.getIdSanPham().compareTo(sanPham.getIdSanPham()) == 0) {

                        // Kiểm tra và bớt số lượng hoặc xóa món đó
                        if(mon.getSoLuong()>1){
                            String keyChiTietHoaDon = mon.getIdChiTietHoaDon();
                            mon.setSoLuong(mon.getSoLuong() - 1);
                            mon.setThanhTien(mon.getDonGia() * mon.getSoLuong());

                            Map<String, Object> map = new HashMap<>();
                            map.put(keyChiTietHoaDon, mon);
                            chiTietHoaDon_Tbl.updateChildren(map);

                            break;
                        }
                        else {
                            String keyChiTietHoaDon = mon.getIdChiTietHoaDon();
                            chiTietHoaDon_Tbl.child(keyChiTietHoaDon).removeValue();

                            break;
                        }

                    }
                }

            }
        }

    }


}
