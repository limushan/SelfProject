package com.limushan.mushanproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.limushan.databinding.DataBindingTestActivity;
import com.limushan.scroll.activity.ScrollActivity;
import com.limushan.scroll.activity.WebViewActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Android 滑动分析
     * @param view
     */
    public void scroll(View view) {
        WebViewActivity.start(this);
    }

    public void dataBind(View view){
        DataBindingTestActivity.start(this);
    }


}
