package com.wrl.exercise.design.factoryDesign.normal;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-16 17:44
 */
public class PhoneFactory extends Factory {
    @Override
    public <T extends NokiaPhone> T createPhone(Class<T> clz) {
        NokiaPhone nokiaPhone = null;

        try {
            nokiaPhone = (NokiaPhone) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) nokiaPhone;
    }
}
