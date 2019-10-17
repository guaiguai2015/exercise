package com.wrl.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wrl.exercise.design.factoryDesign.normal.Nokia2000Phone;
import com.wrl.exercise.design.factoryDesign.normal.Nokia99Phone;
import com.wrl.exercise.design.factoryDesign.normal.PhoneFactory;
import com.wrl.exercise.widget.UIButton;

public class MainActivity extends AppCompatActivity {

    private UIButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"店家",Toast.LENGTH_LONG).show();
            }
        });

        //普通工厂模式
        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.createPhone(Nokia99Phone.class);
        phoneFactory.createPhone(Nokia2000Phone.class);
    }
}
