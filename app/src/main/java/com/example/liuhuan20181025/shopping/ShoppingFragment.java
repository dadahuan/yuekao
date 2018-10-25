package com.example.liuhuan20181025.shopping;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.adapter.ProductAdapter;
import com.example.liuhuan20181025.adapter.ShoppingAdapter;
import com.example.liuhuan20181025.bean.MessageBean;
import com.example.liuhuan20181025.bean.Product;
import com.example.liuhuan20181025.bean.Shopping;
import com.example.liuhuan20181025.presenter.CartPresenter;
import com.example.liuhuan20181025.view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment implements ProductView {
    private static final String TAG = "ShoppingFragment";
    private CheckBox cbtotal;
    private TextView txtpric;
    private RecyclerView rvshopper;
    private List<Shopping<List<Product>>> list;
    private ShoppingAdapter adapter;
    private CartPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_shopping, container, false);
        initView(v);
        list = new ArrayList<>();
        setlistener();
        adapter = new ShoppingAdapter(getActivity(),list);
        adapter.setOnShopperClickListener(new ShoppingAdapter.OnShopperClickListener() {
            @Override
            public void OnShopperClick(int position, boolean isChecked) {
                if(!isChecked){
                    cbtotal.setChecked(false);
                }else{
                    boolean isAllShopperChecked=true;
                    for (Shopping<List<Product>> listShopping : list) {
                        if(!listShopping.isChecked()){
                            isAllShopperChecked=false;
                            break;
                        }
                    }
                    cbtotal.setChecked(isAllShopperChecked);
                }
                calculatePrice();
            }
        });
        adapter.setOnAddDecreaseProductListener(new ProductAdapter.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rvshopper.setLayoutManager(layoutManager);
        rvshopper.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvshopper.setAdapter(adapter);
        presenter = new CartPresenter();
        presenter.attach(this);
        presenter.getData();
        return v;
    }

    private void setlistener() {
        cbtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked=cbtotal.isChecked();
                for (Shopping<List<Product>> listShopping : list) {
                    listShopping.setChecked(isChecked);
                    List<Product> products=listShopping.getList();
                    for (Product product : products) {
                        product.setChecked(isChecked);
                    }
                }
                calculatePrice();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void calculatePrice(){
        float totalPrice=0;
        for (Shopping<List<Product>> listShopping : list) {
            List<Product> list=listShopping.getList();
            for (Product product : list) {
                if(product.isChecked()) {
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }
        txtpric.setText("总价"+totalPrice);
    }
    private void initView(View v) {
        cbtotal = v.findViewById(R.id.cb_total_select);
        txtpric = v.findViewById(R.id.txt_total_price);
        rvshopper = v.findViewById(R.id.rv_shopper);
    }

    @Override
    public void success(MessageBean<List<Shopping<List<Product>>>> data) {
        if(data!=null){
            List<Shopping<List<Product>>> shoppings=data.getData();
            if(shoppings!=null){
                list.clear();
                list.addAll(shoppings);
                Log.i(TAG, "111111111111111111111111111111111111111111111111111111111111onCreateView: "+list);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {

    }
}
