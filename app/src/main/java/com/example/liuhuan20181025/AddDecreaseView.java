package com.example.liuhuan20181025;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by 。。。。 on 2018/10/25.
 */

public class AddDecreaseView extends RelativeLayout implements View.OnClickListener {

    private TextView txtadd;
    private TextView txtdecrease;
    private TextView txtnum;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        txtnum.setText(num+"");
    }

    private int num;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_add:
                num++;
                txtnum.setText(num+"");
                if(listener!=null){
                    listener.add(num);
                }
                break;
            case R.id.txt_decrease:
                if(num>1){
                    num--;
                }
                txtnum.setText(num+"");
                if(listener!=null){
                    listener.decrease(num);
                }
                break;
        }
    }

    public interface OnAddDecreaseListener{
        void add(int num);
        void decrease(int num);
    }
    private OnAddDecreaseListener listener;
    public void setOnAddDecreaseListener(OnAddDecreaseListener listener){
        this.listener=listener;
    }

    public AddDecreaseView(Context context) {
        this(context,null);
    }

    public AddDecreaseView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddDecreaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context,R.layout.item_view,this);
        txtadd = findViewById(R.id.txt_add);
        txtdecrease = findViewById(R.id.txt_decrease);
        txtnum = findViewById(R.id.txt_num);
        txtnum.setText("1");
        txtadd.setOnClickListener(this);
        txtdecrease.setOnClickListener(this);
    }
}
