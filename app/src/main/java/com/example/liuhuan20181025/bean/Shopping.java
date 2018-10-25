package com.example.liuhuan20181025.bean;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class Shopping<T> {
    private String sellerid;
    private String sellerName;
    private boolean isChecked;
    private T list;

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
