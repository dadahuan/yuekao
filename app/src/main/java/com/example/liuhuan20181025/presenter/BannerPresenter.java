package com.example.liuhuan20181025.presenter;

import com.example.liuhuan20181025.bean.Banner;
import com.example.liuhuan20181025.icallback.ICallback;
import com.example.liuhuan20181025.model.Model;
import com.example.liuhuan20181025.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class BannerPresenter {
    private IView iv;
    private Model model;

    public void attach(IView iv) {
        this.iv = iv;
        model = new Model();
    }
    public void getBanner(){
        Type type=new TypeToken<Banner>(){}.getType();
        model.login("http://www.zhaoapi.cn/ad/getAd", new ICallback() {
            @Override
            public void OnSuccess(Object o) {
                Banner banner= (Banner) o;
                if(banner!=null){
                    iv.getBanner(banner.getData());
                }
            }

            @Override
            public void OnFailed(Exception e) {

            }
        },type);
    }
    public void detach(){
        if(iv!=null){
            iv=null;
        }
    }
}
