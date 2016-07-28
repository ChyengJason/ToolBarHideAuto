package com.jscheng.jshttpapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Toolbar toolbar;
    float startX = 0;
    float startY = 0;
    float endX = 0;
    float endY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        textView = (TextView)findViewById(R.id.textview);
    }

    private void hideToolBar(){
        Animator animator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), -toolbar.getHeight());
        animator.setDuration(600);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setTarget(toolbar);
        animator.start();
    }

    private void showToolBar(){
        Animator animator = ObjectAnimator.ofFloat(toolbar, "translationY", toolbar.getTranslationY(), 0);
        animator.setDuration(600);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setTarget(toolbar);
        animator.start();
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        //在这里判断下滑还是上滑

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = ev.getX();
                endY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (endY - startY > 0 && (Math.abs(endY - startY) > 25)) {
                    //向下滑動
                    showToolBar();
                    Toast.makeText(this,"上滑",Toast.LENGTH_SHORT).show();
                } else if (endY - startY < 0  && (Math.abs(endY - startY) > 25)) {
                    //向上滑动
                    hideToolBar();
                    Toast.makeText(this,"下滑",Toast.LENGTH_SHORT).show();
                }
                startX = startY = endX = endY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);//不消费触摸事件
    }
}