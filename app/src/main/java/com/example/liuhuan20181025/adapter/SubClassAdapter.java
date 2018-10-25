package com.example.liuhuan20181025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.bean.SubClassbean;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class SubClassAdapter extends RecyclerView.Adapter<SubClassAdapter.ViewHolder> {
    private Context context;
    private List<SubClassbean.DataBean.ListBean> list;

    public SubClassAdapter(Context context, List<SubClassbean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.item_subclass,null);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imageView);
        holder.txtsubclassname.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView imageView;
        private  TextView txtsubclassname;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_subclass);
            txtsubclassname = itemView.findViewById(R.id.txt_subclaaname);
        }
    }
}
