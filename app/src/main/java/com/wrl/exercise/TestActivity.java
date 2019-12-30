package com.wrl.exercise;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wrl.exercise.design.factoryDesign.normal.Nokia2000Phone;
import com.wrl.exercise.design.factoryDesign.normal.Nokia99Phone;
import com.wrl.exercise.design.factoryDesign.normal.PhoneFactory;
import com.wrl.exercise.widget.UIButton;

public class TestActivity extends AppCompatActivity {

    private UIButton button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"店家",Toast.LENGTH_LONG).show();
            }
        });

        //普通工厂模式
        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.createPhone(Nokia99Phone.class);
        phoneFactory.createPhone(Nokia2000Phone.class);


        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        textView.setTypeface(fromAsset);


    }
}
