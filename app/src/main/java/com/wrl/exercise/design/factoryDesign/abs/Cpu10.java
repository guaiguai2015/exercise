package com.wrl.exercise.design.factoryDesign.abs;

import android.util.Log;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:51
 */
public class Cpu10 implements Component.Cpu{
    @Override
    public void showCpuName() {
        Log.e("wrl","cpu10");

    }
}
