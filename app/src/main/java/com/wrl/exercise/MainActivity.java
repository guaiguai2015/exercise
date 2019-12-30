package com.wrl.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author wangrulin
 * @description:
 * @date :2019-12-30 15:38
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.bottom_sheet:
                BottomSheetActivity.start(this);
                break;

            case R.id.aspect_btn:
                AspectActivity.start(this);
                break;

                default:
                    break;
        }
    }
}
