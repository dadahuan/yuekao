package com.example.liuhuan20181025.view;

import com.example.liuhuan20181025.bean.MessageBean;
import com.example.liuhuan20181025.bean.Product;
import com.example.liuhuan20181025.bean.Shopping;

import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public interface ProductView {
    void success(MessageBean<List<Shopping<List<Product>>>> data);
    void failed(Exception e);
}
