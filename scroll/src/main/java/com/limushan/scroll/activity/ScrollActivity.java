package com.limushan.scroll.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.limushan.scroll.R;

public class ScrollActivity extends AppCompatActivity {

    private View mLayout;


    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, ScrollActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mLayout = findViewById(R.id.layout);
    }

    public void scrollTo(View view) {
        mLayout.scrollTo(-100, -200);
    }

    public void scrollBy(View view) {
        mLayout.scrollBy(-100, -200);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
