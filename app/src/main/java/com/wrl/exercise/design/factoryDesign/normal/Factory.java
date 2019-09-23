package com.wrl.exercise.design.factoryDesign.normal;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:42
 */
public abstract class Factory {

    public abstract <T extends NokiaPhone> T createPhone (Class <T> clz);
}
