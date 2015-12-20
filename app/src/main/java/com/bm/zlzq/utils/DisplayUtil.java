package com.bm.zlzq.utils;

import android.content.Context;
import android.view.View;

/**
 * dp、sp、px转换
 * 
 * @author yangk
 * 
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * 
     * @param context
     * @param pxValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * 
     * @param context
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param context
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param context
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的宽
     */
    public static int getWidth(Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    /**
     * 根据手机的高
     */
    public static int getHeight(Context context) {
        int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    /**
     * 重新设置view 的宽高
     *
     * @param v
     * @param width
     * @param height
     */
    public static void setLayoutParams(View v, int width, int height) {
        android.view.ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.width = width;
        lp.height = height;
        v.setLayoutParams(lp);
    }
}
