package com.example.liuhuan20181025.first;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.liuhuan20181025.HomeActivity;
import com.example.liuhuan20181025.MainActivity;
import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.adapter.BannerAdapter;
import com.example.liuhuan20181025.bean.Banner;
import com.example.liuhuan20181025.presenter.BannerPresenter;
import com.example.liuhuan20181025.view.IView;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment implements IView<Banner> {

    private ViewPager viewPager;
    private List<Banner.DataBean> bannerlist;
    private BannerAdapter bannerAdapter;
    private BannerPresenter presenter;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_first, container, false);
        searchView = v.findViewById(R.id.search);
        viewPager = v.findViewById(R.id.viewpager);
        bannerlist = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(),bannerlist);
        viewPager.setAdapter(bannerAdapter);
        presenter = new BannerPresenter();
        presenter.attach(this);
        presenter.getBanner();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void success(Banner banner) {

    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void getBanner(List<Banner.DataBean> list) {
        if(list!=null){
            bannerlist.clear();
            bannerlist.addAll(list);
            bannerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();{
            if(presenter!=null){
                presenter.detach();
            }
        }
    }
}
