package com.wrl.exercise;

import android.app.Application;
import android.content.Context;

/**
 * @author wangrulin
 * @description:
 * @date :2019-10-17 11:27
 */
public class MApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }
}
