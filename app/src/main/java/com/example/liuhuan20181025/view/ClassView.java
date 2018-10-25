package com.example.liuhuan20181025.view;

import com.example.liuhuan20181025.bean.Mainbean;
import com.example.liuhuan20181025.bean.SubClassbean;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public interface ClassView {
    void getmain(List<Mainbean.DataBean> list);
    void getsubclass(List<SubClassbean.DataBean> list);
    void failed(Exception e);
}
