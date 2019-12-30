package com.wrl.exercise;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wrl.exercise.util.LogUtil;

/**
 * @author wangrulin
 * @description:
 * @date :2019-12-30 11:12
 *
 * bottomSheet使用
 */
public class BottomSheetActivity extends AppCompatActivity {

    private LinearLayout mlinearlayout;
    private Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        mlinearlayout = findViewById(R.id.mLinearLayout);
        btn = findViewById(R.id.btn);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(mlinearlayout);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        mlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, BottomSheetActivity.class);
        context.startActivity(starter);
    }
}
