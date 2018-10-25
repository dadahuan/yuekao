package com.example.liuhuan20181025.view;

import com.example.liuhuan20181025.bean.Banner;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public interface IView<T> {
    void success(T t);
    void failed(Exception e);
    void getBanner(List<Banner.DataBean> list);
}
