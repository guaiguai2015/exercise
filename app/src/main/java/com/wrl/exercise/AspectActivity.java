package com.wrl.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wrl.exercise.aopModel.SingleClick;

/**
 * @author wangrulin
 * @description:
 * @date :2019-09-26 14:59
 */
public class AspectActivity extends AppCompatActivity {

    private Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    public void test(){
        Log.e("wrl","test()");
    }
}
