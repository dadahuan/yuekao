package com.example.liuhuan20181025;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.liuhuan20181025.classiyl.ClassiylFragment;
import com.example.liuhuan20181025.first.FirstFragment;
import com.example.liuhuan20181025.shopping.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;
    private TextView txtfirst;
    private TextView txtclassiyl;
    private TextView txtshopping;
    private List<Fragment> list;
    private FirstFragment firstFragment;
    private ClassiylFragment classiylFragment;
    private ShoppingFragment shoppingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        pager = findViewById(R.id.pager);
        txtfirst = findViewById(R.id.txt_first);
        txtclassiyl = findViewById(R.id.txt_classiy);
        txtshopping = findViewById(R.id.txt_shopping);
        list = new ArrayList<>();
        firstFragment = new FirstFragment();
        classiylFragment = new ClassiylFragment();
        shoppingFragment = new ShoppingFragment();
        list.add(firstFragment);
        list.add(classiylFragment);
        list.add(shoppingFragment);
        txtfirst.setOnClickListener(this);
        txtclassiyl.setOnClickListener(this);
        txtshopping.setOnClickListener(this);
        txtfirst.setBackgroundColor(Color.RED);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                color(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_first:
                pager.setCurrentItem(0);
                break;
            case R.id.txt_classiy:
                pager.setCurrentItem(1);
                break;
            case R.id.txt_shopping:
                pager.setCurrentItem(2);
                break;
        }
    }
    private void color(int index){
        txtfirst.setBackgroundColor(index==0?Color.RED:Color.WHITE);
        txtclassiyl.setBackgroundColor(index==1? Color.RED:Color.WHITE);
        txtshopping.setBackgroundColor(index==2? Color.RED:Color.WHITE);
    }
}
