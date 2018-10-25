package com.example.liuhuan20181025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.liuhuan20181025.bean.Banner;
import com.example.liuhuan20181025.httputils.StringUtils;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner.DataBean> list;

    public BannerAdapter(Context context, List<Banner.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView img=new ImageView(context);
        Banner.DataBean bean=list.get(position);
        String imgaes=bean.getIcon();
        if(!TextUtils.isEmpty(imgaes)){
            String[] strings=imgaes.split("\\|");
            Glide.with(context).load(StringUtils.https2Htp(strings[0])).into(img);
        }
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
