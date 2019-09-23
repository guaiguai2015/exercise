package com.wrl.exercise.design.factoryDesign.abs;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:53
 */
public abstract class Factory {

    abstract Component.Cpu createCpu();

    abstract Component.Battery createBattery();
}
