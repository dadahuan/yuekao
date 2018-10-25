package com.example.liuhuan20181025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhuan20181025.AddDecreaseView;
import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.bean.Product;
import com.example.liuhuan20181025.httputils.StringUtils;

import java.util.List;
import java.util.logging.Handler;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> list;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }
    public interface OnProductClickListener{
        void OnProductClickListener(int position,boolean isChecked);
    }
    private OnProductClickListener productClickListener;
    public void setOnProductClickListener(OnProductClickListener listener){
        this.productClickListener=listener;
    }
    public interface OnAddDecreaseProductListener{
        void onChange(int position,int num);
    }
    private OnAddDecreaseProductListener productListener;
    public void setOnAddDecreaseProductListener(OnAddDecreaseProductListener listener){
        this.productListener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.item_product,null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Product product=list.get(position);
        String images=product.getImages();
        if(TextUtils.isEmpty(images)){
            String[] strings=images.split("\\|");
            if(strings.length>0){
                Glide.with(context).load(StringUtils.https2Htp(strings[0])).into(holder.imgproduct);
            }
        }
        holder.txtproductname.setText(product.getTitle());
        holder.txtsingleprice.setText(String.valueOf(product.getPrice()));
        holder.advproduct.setOnAddDecreaseListener(new AddDecreaseView.OnAddDecreaseListener() {
            @Override
            public void add(int num) {
                product.setNum(num);
                if(productListener!=null){
                    productListener.onChange(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                product.setNum(num);
                if(productListener!=null){
                    productListener.onChange(position,num);
                }
            }
        });
        holder.cbproduct.setOnCheckedChangeListener(null);
        holder.cbproduct.setChecked(product.isChecked());
        holder.cbproduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setChecked(isChecked);
                if(productClickListener!=null){
                    productClickListener.OnProductClickListener(position,isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbproduct;
        private ImageView imgproduct;
        private TextView txtsingleprice;
        private AddDecreaseView advproduct;
        private TextView txtproductname;

        public ViewHolder(View itemView) {
            super(itemView);
            cbproduct = itemView.findViewById(R.id.cb_product);
            imgproduct = itemView.findViewById(R.id.img_product);
            txtsingleprice = itemView.findViewById(R.id.txt_single_price);
            advproduct = itemView.findViewById(R.id.adv_product);
            txtproductname = itemView.findViewById(R.id.txt_product_name);
        }
    }
}
