package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChonMonActivity extends AppCompatActivity {

    ListView lvMonAn;
    ArrayList<MonAn> arrMonAn;
    MonAnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mon);

        AnhXa();
        adapter =new MonAnAdapter(this,R.layout.mon_an, arrMonAn);
        lvMonAn.setAdapter(adapter);
    }
    private void AnhXa(){
        lvMonAn =(ListView) findViewById(R.id.listViewMonAn);
        arrMonAn = new ArrayList<>();
        arrMonAn.add(new MonAn("Cá lóc kho tộ",200000));
        arrMonAn.add(new MonAn("Lẩu cá kèo",250000));
    }
}