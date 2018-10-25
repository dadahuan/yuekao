package com.example.liuhuan20181025.classiyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liuhuan20181025.R;
import com.example.liuhuan20181025.adapter.SubClassAdapter;
import com.example.liuhuan20181025.adapter.MainAdapter;
import com.example.liuhuan20181025.bean.Mainbean;
import com.example.liuhuan20181025.bean.SubClassbean;
import com.example.liuhuan20181025.presenter.ClassPresenter;
import com.example.liuhuan20181025.view.ClassView;

import java.util.ArrayList;
import java.util.List;


public class ClassiylFragment extends Fragment implements ClassView {

    private RecyclerView rvmainclass;
    private LinearLayout linear;
    private List<Mainbean.DataBean> mainlist;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter mainAdapter;
    private ClassPresenter presenter;
    private List<SubClassbean.DataBean> sublist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_classiyl, container, false);
        rvmainclass = v.findViewById(R.id.rv_mainclass);
        linear = v.findViewById(R.id.linear);
        mainlist = new ArrayList<>();
        sublist = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        rvmainclass.setLayoutManager(layoutManager);
        rvmainclass.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mainAdapter = new MainAdapter(getActivity(),mainlist);
        rvmainclass.setAdapter(mainAdapter);
        presenter = new ClassPresenter();
        presenter.attach(this);
        presenter.main();
        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Mainbean.DataBean bean=mainlist.get(position);
                presenter.subclass("http://www.zhaoapi.cn/product/getProductCatagory?cid="+bean.getCid());
            }
        });
        return v;
    }

    @Override
    public void getmain(List<Mainbean.DataBean> list) {
        if(list!=null){
            mainlist.clear();
            mainlist.addAll(list);
            mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getsubclass(List<SubClassbean.DataBean> list) {
        if(list!=null){
            linear.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                TextView textView=new TextView(getActivity());
                textView.setText(list.get(i).getName());
                RecyclerView recyclerView=new RecyclerView(getActivity());

                SubClassAdapter subClassAdapter=new SubClassAdapter(getActivity(),list.get(i).getList());
                recyclerView.setAdapter(subClassAdapter);

                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(layoutManager);

                linear.addView(textView);
                linear.addView(recyclerView);

                sublist.clear();
                sublist.addAll(list);
                subClassAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subclass("http://www.zhaoapi.cn/product/getProductCatagory?cid=1");
    }
}
