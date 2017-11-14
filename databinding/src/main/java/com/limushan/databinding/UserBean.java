package com.limushan.databinding;

import android.util.Log;
import android.view.View;

/**
 * @author libinbin
 *         2017/10/26
 */

public class UserBean {
    public String name;
    public String id;

    public void onClick(View view) {
        view.setVisibility(View.GONE);
        Log.e("log---------","onClick(UserBean.java:16)-->>"+"");
    }

    public UserBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
