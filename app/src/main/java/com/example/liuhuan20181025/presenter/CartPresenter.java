package com.example.liuhuan20181025.presenter;

import com.example.liuhuan20181025.bean.MessageBean;
import com.example.liuhuan20181025.bean.Product;
import com.example.liuhuan20181025.bean.Shopping;
import com.example.liuhuan20181025.icallback.ICallback;
import com.example.liuhuan20181025.model.Model;
import com.example.liuhuan20181025.shopping.ShoppingFragment;
import com.example.liuhuan20181025.view.ProductView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class CartPresenter {
    private ProductView iv;
    private Model model;

    public void attach(ProductView iv) {
        this.iv = iv;
        model = new Model();
    }
    public void getData(){
        String url="http://www.zhaoapi.cn/product/getCarts?uid=1538";
        Type type=new TypeToken<MessageBean<List<Shopping<List<Product>>>>>(){}.getType();
        model.login(url, new ICallback() {
            @Override
            public void OnSuccess(Object o) {
                MessageBean<List<Shopping<List<Product>>>> data= (MessageBean<List<Shopping<List<Product>>>>) o;
                iv.success(data);
            }

            @Override
            public void OnFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void detach(){
        if(iv!=null){
            iv=null;
        }
    }
}
