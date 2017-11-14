package com.limushan.databinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.limushan.databinding.databinding.ActivityDatabindingTestBinding;

/**
 * @author libinbin
 *         2017/10/26
 */

public class DataBindingTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabindingTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_test);
        binding.setUser(new UserBean("测试", "666"));
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, DataBindingTestActivity.class));
    }
}
