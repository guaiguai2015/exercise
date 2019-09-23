package com.wrl.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wrl.exercise.design.factoryDesign.normal.Nokia2000Phone;
import com.wrl.exercise.design.factoryDesign.normal.Nokia99Phone;
import com.wrl.exercise.design.factoryDesign.normal.PhoneFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //普通工厂模式
        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.createPhone(Nokia99Phone.class);
        phoneFactory.createPhone(Nokia2000Phone.class);
    }
}
