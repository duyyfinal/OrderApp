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
    // Ch???n m??n ??n
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
        // Duy???t danh s??ch h??a ????n
        for (int sohoadon = 0; sohoadon < arrHoaDon.size(); sohoadon++) {
            // L???y h??a ????n th??? i
            HoaDon hoaDon =  arrHoaDon.get(sohoadon);

            // Ki???m tra t???n t???i h??a ????n t???i b??n ???? ch???n c?? tr???ng th??i ??ang ?????t hay kh??ng (0=??ang ?????t, 1=???? thanh to??n)
            if (hoaDon.getIdBan().compareTo(idBanHienTai) == 0 && hoaDon.getTrangThai() == 0) {

                // th??m chi ti???t h??a ????n v???i idhoadon ???? l??u
                boolean daTonTaiMon = false;
                String idHoaDon = hoaDon.getIdHoaDon();
                for (int j = 0; j < arrSanPhamDat.size(); j++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(j);

                    // Ki???m tra t???n t???i m??n ch??a ?
                    if (mon.getIdHoaDon().compareTo(idHoaDon) == 0 && mon.getIdSanPham().compareTo(sanPham.getIdSanPham()) == 0) {

                        // C???p nh??t s??? l?????ng c???a m??n ????
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

                // Tr?????ng h???p ch??a c?? m??n
                if (daTonTaiMon == false) {
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(idHoaDon, sanPham.getIdSanPham(), sanPham.getTenSanPham(), sanPham.getDonGia(), sanPham.getDiaChiAnh(), 1, sanPham.getDonGia()*1);
                    chiTietHoaDon_Tbl.push().setValue(chiTietHoaDon);
                }

                daTonTaiHoaDon = true;
                break;
            }
        }
        // N???u kh??ng t???n t???i h??a ????n n??o t???i b??n ???? ch???n c?? trang th??i ??ang ?????t
        if (daTonTaiHoaDon == false) {

            // th??m m???i h??a ????n
            HoaDon hoaDon = new HoaDon(ngay, thang, nam, gio, phut, idBanHienTai, idTaiKhoanHienTai, 0);
            String key = hoaDon_Tbl.push().getKey();
            hoaDon_Tbl.child(key).setValue(hoaDon);

            //Th??m chi ti???t h??a ????n
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


        // Duy???t danh s??ch h??a ????n
        for (int sohoadon = 0; sohoadon < arrHoaDon.size(); sohoadon++) {
            // L???y h??a ????n th??? i
            HoaDon hoaDon =  arrHoaDon.get(sohoadon);

            // Ki???m tra t???n t???i h??a ????n t???i b??n ???? ch???n c?? tr???ng th??i ??ang ?????t hay kh??ng (0=??ang ?????t, 1=???? thanh to??n)
            if (hoaDon.getIdBan().compareTo(idBanHienTai) == 0 && hoaDon.getTrangThai() == 0) {

                // B???t chi ti???t h??a ????n v???i idhoadon ???? l??u
                String idHoaDon = hoaDon.getIdHoaDon();
                for (int j = 0; j < arrSanPhamDat.size(); j++) {
                    ChiTietHoaDon mon = arrSanPhamDat.get(j);

                    // Ki???m tra t???n t???i m??n ch??a ?
                    if (mon.getIdHoaDon().compareTo(idHoaDon) == 0 && mon.getIdSanPham().compareTo(sanPham.getIdSanPham()) == 0) {

                        // Ki???m tra v?? b???t s??? l?????ng ho???c x??a m??n ????
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
