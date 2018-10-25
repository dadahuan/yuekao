package com.example.liuhuan20181025;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtmaipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtmaipo = findViewById(R.id.txt_maipo);
        ObjectAnimator txanimator=ObjectAnimator.ofFloat(
                txtmaipo,
                "translationX",
                0,
                670
        );
        ObjectAnimator tyanimator=ObjectAnimator.ofFloat(
                txtmaipo,
                "translationY",
                0,
                1120
        );
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(txanimator).with(tyanimator);
        animatorSet.setDuration(3000);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
