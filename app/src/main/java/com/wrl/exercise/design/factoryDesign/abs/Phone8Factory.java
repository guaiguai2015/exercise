package com.wrl.exercise.design.factoryDesign.abs;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:55
 */
public class Phone8Factory extends Factory {
    @Override
    Component.Cpu createCpu() {
        return new Cpu20();
    }

    @Override
    Component.Battery createBattery() {
        return new Battery5000ma();
    }
}
