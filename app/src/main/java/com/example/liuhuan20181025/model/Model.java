package com.example.liuhuan20181025.model;

import com.example.liuhuan20181025.httputils.HttpUtils;
import com.example.liuhuan20181025.icallback.ICallback;

import java.lang.reflect.Type;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class Model {
    public void login(String url, ICallback callback, Type type){
        HttpUtils.getInstance().get(url,callback,type);
    }
}
