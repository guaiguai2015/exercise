package com.wrl.exercise.design.factoryDesign.abs;

import android.util.Log;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:52
 */
public class Battery5000ma implements Component.Battery {
    @Override
    public void showBatteryName() {
        Log.e("wrl","Battery5000ma");
    }
}
