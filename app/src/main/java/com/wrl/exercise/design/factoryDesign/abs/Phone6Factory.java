package com.wrl.exercise.design.factoryDesign.abs;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:54
 */
public class Phone6Factory extends Factory {
    @Override
    Component.Cpu createCpu() {
        return new Cpu10();
    }

    @Override
    Component.Battery createBattery() {
        return new Battery1200ma();
    }
}
