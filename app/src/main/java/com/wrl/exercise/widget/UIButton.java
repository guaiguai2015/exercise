package com.wrl.exercise.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.wrl.exercise.R;
import com.wrl.exercise.util.DisplayUtil;
import com.wrl.exercise.util.Utils;

/**
 * @author wangrulin
 * @description:
 * @date :2019-10-17 11:11
 */
public class UIButton extends AppCompatButton {

    private Context mContext;

    private static final int PRIMARY = 0x01;
    private static final int NORMAL = 0x02;

    private static final int LARGE = 0x01;
    private static final int MIDDLE = 0x02;
    private static final int SMALL = 0x03;

    private int mCurrentType = PRIMARY;
    private int mCurrentSize = MIDDLE;

    private int height;

    public UIButton(Context context) {
        this(context, null);
    }


    public UIButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public UIButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        parseAttr(attrs);
        initView();
    }

    private void parseAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.UIButton);
        mCurrentType = typedArray.getInt(R.styleable.UIButton_UIButtonType, PRIMARY);
        mCurrentSize = typedArray.getInt(R.styleable.UIButton_UIButtonSize, MIDDLE);
        typedArray.recycle();
    }

    private void initView() {
        this.setGravity(Gravity.CENTER);
        updateButtonStyle(mCurrentType, mCurrentSize);
    }

    private void updateButtonStyle(int mCurrentType, int mCurrentSize) {
        verifyParmas(mCurrentType, mCurrentSize);
    }

    private void verifyParmas(int mCurrentType, int mCurrentSize) {
        this.mCurrentType = mCurrentType;
        this.mCurrentSize = mCurrentSize;

        switch (mCurrentSize) {
            case LARGE:
                height = DisplayUtil.dip2px(52);
                this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                break;
            case MIDDLE:
                height = DisplayUtil.dip2px(44);
                this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                break;
            case SMALL:
                height = DisplayUtil.dip2px(35);
                this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                break;

            default:
                break;
        }
        this.setPadding(0, 0, 0, 0);

        Drawable drawable = null;
        switch (mCurrentType) {
            case PRIMARY:
                setTextColor(ContextCompat.getColorStateList(mContext, R.color.normal_button_text_color));
                drawable = createPrimaryDrawable();
                break;
            case NORMAL:
                setTextColor(ContextCompat.getColorStateList(mContext, R.color.normal_button_text_color));
                drawable = createNormalDrawable();
                break;
            default:
                break;
        }

        if (drawable != null) {
            this.setBackgroundDrawable(drawable);
        }
        setEnabled(true);
    }

    private Drawable createPrimaryDrawable() {
        Drawable normalDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#FD4145"),10);
        Drawable pressedDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#CA3437"),10);
        Drawable disableDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#ffa7a8"),10);

        return Utils.Companion.createSelectorDrawable(normalDrawable,pressedDrawable,disableDrawable);
    }

    private Drawable createNormalDrawable() {
        Drawable normalDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#E5E9EF"),10);
        Drawable pressedDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#BEC4CF"),10);
        Drawable disableDrawable = Utils.Companion.createRoundCornerDrawable(TypedValue.COMPLEX_UNIT_DIP,Color.parseColor("#E5E9EF"),10);

        return Utils.Companion.createSelectorDrawable(normalDrawable,pressedDrawable,disableDrawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (height > 0) {
            setMeasuredDimension(getMeasuredWidth(), height);
        }
    }
}