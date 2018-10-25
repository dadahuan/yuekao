package com.example.liuhuan20181025.presenter;

import com.example.liuhuan20181025.bean.Mainbean;
import com.example.liuhuan20181025.bean.SubClassbean;
import com.example.liuhuan20181025.classiyl.ClassiylFragment;
import com.example.liuhuan20181025.icallback.ICallback;
import com.example.liuhuan20181025.model.Model;
import com.example.liuhuan20181025.view.ClassView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class ClassPresenter {
    private ClassView iv;
    private Model model;

    public void attach(ClassView iv){
        this.iv=iv;
        model = new Model();
    }
    public void main(){
        Type type=new TypeToken<Mainbean>(){}.getType();
        model.login("http://www.zhaoapi.cn/product/getCatagory", new ICallback() {
            @Override
            public void OnSuccess(Object o) {
                Mainbean mainbean= (Mainbean) o;
                iv.getmain(mainbean.getData());
            }

            @Override
            public void OnFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void subclass(String url){
        Type type=new TypeToken<SubClassbean>(){}.getType();
        model.login(url, new ICallback() {
            @Override
            public void OnSuccess(Object o) {
                SubClassbean classbean= (SubClassbean) o;
                iv.getsubclass(classbean.getData());
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
