package com.wrl.exercise.util;

/**
 * Created by sohu-mac on 16/9/18.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.wrl.exercise.MApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(float pxValue) {
        final float scale = MApplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue) {
        final float scale = MApplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static float dip2pxFloat(float dipValue) {
        final float scale = MApplication.mContext.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = MApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = MApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = MApplication.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MApplication.mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Point getScreenSize() {
        WindowManager wm = (WindowManager) MApplication.mContext.getSystemService(Context.WINDOW_SERVICE);

        Point outSize = new Point(0, 0);
        wm.getDefaultDisplay().getSize(outSize);
        return outSize;
    }


    /**
     * 获取view在屏幕坐标
     *
     * @param view
     * @return 0是x  1是y
     */
    public static int[] getViewLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

    /**
     * 获取度量对象
     * DisplayMetrics
     * // int width = metric.widthPixels;  // 屏幕宽度（像素）
     * // int height = metric.heightPixels;  // 屏幕高度（像素）
     * // 屏幕密度DPI（120 / 160 / 240）
     *
     * @return
     */
    public static DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) MApplication.mContext
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 获取虚拟功能键高度
     */
    public static int getVirtualBarHeigh() {
        int vh = 0;
        WindowManager windowManager = (WindowManager) MApplication.mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    /**
     * dp换算成当前屏幕坐标 ，以px为单位
     *
     * @param dp
     * @return
     */
    public static int dpToPxLength(int dp) {
        return dp * getDPi() / 160;
    }

    /**
     * 获取系统屏幕初略dpi
     *
     * @return
     */

    public static int getDPi() {
        return getDisplayMetrics().densityDpi;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64PngString(Bitmap bitmap) {
        StringBuffer string = new StringBuffer();
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            bStream.flush();
            bStream.close();
            byte[] bytes = bStream.toByteArray();
            string.append(Base64.encodeToString(bytes, Base64.NO_WRAP));
        } catch (IOException e) {
            LogUtil.printeException(e);
        } finally {
            try {
                if (bStream != null) {
                    bStream.flush();
                    bStream.close();
                }
            } catch (IOException e) {
                LogUtil.printeException(e);
            }
        }
        return string.toString();
    }

    /**
     * gif转为base64
     *
     * @param gif
     * @return
     */
    public static String gifToBase64PngString(byte[] gif) {
        StringBuffer string = new StringBuffer();
        string.append(Base64.encodeToString(gif, Base64.NO_WRAP));

        return string.toString();
    }

    /**
     * 获取系统Windowmanager
     *
     * @return
     */
    public static WindowManager getWindowmanager() {
        return (WindowManager) MApplication.mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 百分比计算
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int getPercentage(int num1, int num2) {
        // 创建一个数值格式化对象

        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        numberFormat.setRoundingMode(RoundingMode.HALF_UP); //四舍五入

        float n = (float) num1 / (float) num2 * 100;
        if (n < 1) {
            n = 0;
        } else if (n > 100) {
            n = 100;
        }
        return (int) n;
    }


    public static int getPercentage(long num1, long num2) {
        // 创建一个数值格式化对象

        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        numberFormat.setRoundingMode(RoundingMode.HALF_UP); //四舍五入

        String result = numberFormat.format((double) num1 / (double) num2 * 100);

        int p = Float.valueOf(result).intValue();
        return p;
    }


    public static SpannableStringBuilder builerSpannable(Context context, String s1, String s2, int color1, int color2, int f1, int f2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(s2);
        SpannableStringBuilder takebuilder = new SpannableStringBuilder(sb.toString());

        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        if (color1 != -1) {
            ForegroundColorSpan c1 = new ForegroundColorSpan(context.getResources().getColor(color1));
            takebuilder.setSpan(c1, 0, s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (color2 != -1) {
            ForegroundColorSpan c2 = new ForegroundColorSpan(context.getResources().getColor(color2));
            takebuilder.setSpan(c2, s1.length(), sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }

        if (f1 != -1) {
            AbsoluteSizeSpan font1 = new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(f1));
            takebuilder.setSpan(font1, 0, s1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (f2 != -1) {
            AbsoluteSizeSpan font2 = new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(f2));
            takebuilder.setSpan(font2, s1.length(), sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return takebuilder;

    }

    public static boolean checkViewVisible(View v) {
        Rect rect = new Rect();
        return v.getGlobalVisibleRect(rect);
    }


    public static int[] unDisplayViewSize(View view) {
        int[] size = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }

    public static int measureHeight(View view, int width){
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredHeight();

    }

    /**
     * 得到一个字符串的前几位,一个汉字或日韩文或大写的字母长度为1,其他长度为0.5
     *
     * @param s
     * @param maxChar
     * @return
     */
    public static String getSubString(String s, int maxChar) {
        if (TextUtils.isEmpty(s)) {
            Log.d("buxq", "getSubString: s = null");
            return "";
        }
        if (s.length() <= maxChar) {
            return s;
        }

        double valueLength = 0;
        StringBuilder sb = new StringBuilder();

        //unicode编码范围
        String chinese = "[\u4e00-\u9fa5]";
        String upcaseLetter = "[\u0041-\u005a]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else if (temp.matches(upcaseLetter)) {
                //大写的字母长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
            if (valueLength <= maxChar) {
                sb.append(temp);
            } else {
                sb.append("...");
                return sb.toString().trim();
            }
        }
        return sb.toString().trim();
    }


    /**
     * 得到一个字符串的长度,一个汉字或日韩文或大写的字母长度为1,其他长度为0.5
     *
     * @return
     */
    public static int getStringLength(String msg) {
        if (TextUtils.isEmpty(msg)) {
            Log.d("buxq", "getSubString: s = null");
            return 0;
        }
        double valueLength = 0;
        //unicode编码范围
        String chinese = "[\u4e00-\u9fa5]";
        String upcaseLetter = "[\u0041-\u005a]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < msg.length(); i++) {
            // 获取一个字符
            String temp = msg.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else if (temp.matches(upcaseLetter)) {
                //大写的字母长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        return (int) (valueLength + 0.5);
    }

    /**
     * 设置TextView首行偏移量
     * @param contentView  TextView
     * @param contentStr   内容
     * @param leftView     左侧View
     * @param marginLeftValue   与左侧View的间距
     */
    public static void setTextViewLeadingMarginSpan(TextView contentView, String contentStr, View leftView, int marginLeftValue) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1<<30) - 1, View.MeasureSpec.AT_MOST);
        leftView.measure(widthMeasureSpec, 0);

        SpannableString spannableString = new SpannableString(contentStr);

        LeadingMarginSpan.Standard what = new LeadingMarginSpan.Standard(leftView.getMeasuredWidth() + marginLeftValue, 0);
        spannableString.setSpan(what, 0, spannableString.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        contentView.setText(spannableString);
    }

}
