package com.example.liuhuan20181025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.bean.Product;
import com.example.liuhuan20181025.bean.Shopping;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    private Context context;
    private List<Shopping<List<Product>>> list;

    public ShoppingAdapter(Context context, List<Shopping<List<Product>>> list) {
        this.context = context;
        this.list = list;
    }
    public interface OnShopperClickListener{
        void OnShopperClick(int position,boolean isChecked);
    }
    private OnShopperClickListener shopperClickListener;
    public void setOnShopperClickListener(OnShopperClickListener listener){
        this.shopperClickListener=listener;
    }
    private ProductAdapter.OnAddDecreaseProductListener productListener;
    public void setOnAddDecreaseProductListener(ProductAdapter.OnAddDecreaseProductListener listener){
        this.productListener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shopping, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Shopping<List<Product>> shopping = list.get(position);
        holder.txtshoppername.setText(shopping.getSellerName());
        RecyclerView.LayoutManager playoutManager=new LinearLayoutManager(context);
        holder.rvproduct.setLayoutManager(playoutManager);
        final ProductAdapter adapter=new ProductAdapter(context,shopping.getList());
        if(productListener!=null){
            adapter.setOnAddDecreaseProductListener(productListener);
        }
        adapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void OnProductClickListener(int position, boolean isChecked) {
                if(!isChecked){
                    shopping.setChecked(false);
                    shopperClickListener.OnShopperClick(position,false);
                }else{
                    boolean isAllProductSelected=true;
                    for (Product product : shopping.getList()) {
                        if(!product.isChecked()) {
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    shopping.setChecked(isAllProductSelected);
                    shopperClickListener.OnShopperClick(position,true);
                }
                notifyDataSetChanged();
               productListener.onChange(0,0);
            }
        });
        holder.rvproduct.setAdapter(adapter);
        holder.cbshopper.setOnCheckedChangeListener(null);
        holder.cbshopper.setChecked(shopping.isChecked());
        holder.cbshopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopping.setChecked(isChecked);
                if(isChecked){
                    List<Product> productList=shopping.getList();
                    for (Product product : productList) {
                        product.setChecked(isChecked);
                    }
                    adapter.notifyDataSetChanged();
                }
                if(shopperClickListener!=null){
                    shopperClickListener.OnShopperClick(position,isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbshopper;
        private TextView txtshoppername;
        private RecyclerView rvproduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbshopper = itemView.findViewById(R.id.cb_shopper);
            txtshoppername = itemView.findViewById(R.id.txt_shopping_name);
            rvproduct = itemView.findViewById(R.id.rv_product);
        }
    }
}
