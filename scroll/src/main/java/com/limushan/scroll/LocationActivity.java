package com.limushan.scroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * @author libinbin
 *         2017/9/20
 */

public class LocationActivity extends Activity {

    private View locationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationView = findViewById(R.id.locationView);

        int[] location = new int[2];
        locationView.getLocationOnScreen(location);

        Logger.d(location);

        locationView.post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                locationView.getLocationOnScreen(location);
                Logger.d(location);
            }
        });



    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, LocationActivity.class));
    }


}
