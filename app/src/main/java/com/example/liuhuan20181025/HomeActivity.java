package com.example.liuhuan20181025;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FlyBanner banner;
    private List<String> list;
    private EditText searchView;
    private Button btnadd;
    private Button btndecrease;
    private FlowLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchView = findViewById(R.id.et_text);
        fl = findViewById(R.id.flow);
        btnadd = findViewById(R.id.btn_add);
        btndecrease = findViewById(R.id.btn_decrease);
        addHistor();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHistor();
            }
        });
        btndecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl.removeAllViews();
            }
        });
//        banner = findViewById(R.id.images);
//        list = new ArrayList<>();
//        list.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
//        list.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
//        list.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
//        list.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
//        banner.setImagesUrl(list);
    }

    private void addHistor() {
        TextView txt=new TextView(this);
        String s=searchView.getText().toString();
        if(!TextUtils.isEmpty(s)){
            txt.setText(""+s);
            txt.setPadding(15,15,15,15);
            fl.addView(txt);
        }
    }
}
