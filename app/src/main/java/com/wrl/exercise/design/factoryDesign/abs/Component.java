package com.wrl.exercise.design.factoryDesign.abs;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:49
 */
public interface Component {

    interface Cpu {

        void showCpuName();

    }
    interface  Battery {
        void showBatteryName();

    }
}
