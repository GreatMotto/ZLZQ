package com.bm.zlzq.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.shopcar.AdapterClickInterface;
import com.bm.zlzq.shopcar.MerchantChooseActivity;

import java.util.Calendar;

/**
 * Created by wangwm on 2015/12/15.
 */
public class DialogUtil {
    /**
     * 配送方式
     * @param context
     */
    public static void showDialog(final Activity context, final TextView textView, final AdapterClickInterface clickInterface) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dlganim);
        window.setContentView(R.layout.dlg_choose_sendway);
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        window.setLayout(width, height * 2 / 5);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
        TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) window.findViewById(R.id.tv_sure);
        RelativeLayout rl_kuaidi = (RelativeLayout) window.findViewById(R.id.rl_kuaidi);
        RelativeLayout rl_ziti = (RelativeLayout) window.findViewById(R.id.rl_ziti);
        RelativeLayout rl_shifushangmen = (RelativeLayout) window.findViewById(R.id.rl_shifushangmen);

        final ImageView iv_kuaidi = (ImageView) window.findViewById(R.id.iv_kuaidi);
        final ImageView iv_ziti = (ImageView) window.findViewById(R.id.iv_ziti);
        final ImageView iv_shifushangmen = (ImageView) window.findViewById(R.id.iv_shifushangmen);

        // 快递
        rl_kuaidi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_kuaidi.setImageResource(R.mipmap.gwc_xz);
                iv_ziti.setImageResource(R.mipmap.gwc_wxz);
                iv_shifushangmen.setImageResource(R.mipmap.gwc_wxz);
                textView.setText("快递");
            }
        });

        // 自提
        rl_ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_kuaidi.setImageResource(R.mipmap.gwc_wxz);
                iv_ziti.setImageResource(R.mipmap.gwc_xz);
                iv_shifushangmen.setImageResource(R.mipmap.gwc_wxz);
                textView.setText("自提");
                showZiTiDialog(context, clickInterface);
            }
        });

        // 师傅上门
        rl_shifushangmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_kuaidi.setImageResource(R.mipmap.gwc_wxz);
                iv_ziti.setImageResource(R.mipmap.gwc_wxz);
                iv_shifushangmen.setImageResource(R.mipmap.gwc_xz);
                textView.setText("师傅上门");
                showSFSMDialog(context);
            }
        });

        // 确定
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        // 取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                dialog.cancel();
            }
        });
    }

    /**
     * 自提方式
     *
     * @param context
     */
    public static void showZiTiDialog(final Activity context, final AdapterClickInterface clickInterface) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dlganim);
        window.setContentView(R.layout.dlg_choose_sendway);
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        window.setLayout(width, height * 2 / 5);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
        tv_title.setText("自提方式");
        TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) window.findViewById(R.id.tv_sure);
        ImageView iv_back = (ImageView) window.findViewById(R.id.iv_back);
        RelativeLayout rl_kuaidi = (RelativeLayout) window.findViewById(R.id.rl_kuaidi);
        View view_1 = window.findViewById(R.id.view_1);
        RelativeLayout rl_ziti = (RelativeLayout) window.findViewById(R.id.rl_ziti);
        View view_2 = window.findViewById(R.id.view_2);
        RelativeLayout rl_shifushangmen = (RelativeLayout) window.findViewById(R.id.rl_shifushangmen);
        View view_3 = window.findViewById(R.id.view_3);

        TextView tv_ziti = (TextView) window.findViewById(R.id.tv_ziti);
        tv_ziti.setText("预约自提时间");
        TextView tv_shifushangmen = (TextView) window.findViewById(R.id.tv_shifushangmen);
        tv_shifushangmen.setText("选择商户");
        final TextView tv_zitiDate = (TextView) window.findViewById(R.id.tv_zitiDate);
        final TextView tv_shangmen_date = (TextView) window.findViewById(R.id.tv_shangmen_date);
        tv_shangmen_date.setVisibility(View.VISIBLE);
        tv_zitiDate.setVisibility(View.VISIBLE);
        tv_shangmen_date.setText(MerchantChooseActivity.shopName);

        ImageView iv_ziti = (ImageView) window.findViewById(R.id.iv_ziti);
        ImageView iv_shifushangmen = (ImageView) window.findViewById(R.id.iv_shifushangmen);

        iv_ziti.setVisibility(View.GONE);
        iv_shifushangmen.setVisibility(View.GONE);
        iv_back.setVisibility(View.VISIBLE);
        tv_cancel.setVisibility(View.GONE);
        tv_sure.setVisibility(View.GONE);
        rl_kuaidi.setVisibility(View.GONE);
        view_1.setVisibility(View.GONE);

        // 预约自提时间
        rl_ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(context, tv_zitiDate, 1);
            }
        });

        // 选择商户
        rl_shifushangmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.cancel();
//                Intent intent = new Intent(context, MerchantChooseActivity.class);
//                intent.putExtra(Constant.TAG, (Serializable) clickInterface);
//                context.startActivity(intent);
                clickInterface.isFirst(1);
                ((BaseActivity) context).gotoOtherActivity(MerchantChooseActivity.class);
                dialog.cancel();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    /**
     * 师傅上门
     *
     * @param context
     */
    public static void showSFSMDialog(final Activity context) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dlganim);
        window.setContentView(R.layout.dlg_choose_sendway);
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        window.setLayout(width, height * 2 / 5);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_title);
        tv_title.setText("师傅上门(仅限徐州)");
        final TextView tv_shangmen_date = (TextView) window.findViewById(R.id.tv_shangmen_date);

        TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView tv_sure = (TextView) window.findViewById(R.id.tv_sure);
        ImageView iv_back = (ImageView) window.findViewById(R.id.iv_back);
        RelativeLayout rl_kuaidi = (RelativeLayout) window.findViewById(R.id.rl_kuaidi);
        View view_1 = window.findViewById(R.id.view_1);
        RelativeLayout rl_ziti = (RelativeLayout) window.findViewById(R.id.rl_ziti);
        View view_2 = window.findViewById(R.id.view_2);
        RelativeLayout rl_shifushangmen = (RelativeLayout) window.findViewById(R.id.rl_shifushangmen);
        View view_3 = window.findViewById(R.id.view_3);

        TextView tv_shifushangmen = (TextView) window.findViewById(R.id.tv_shifushangmen);
        tv_shifushangmen.setText("预约送货时间");

        final ImageView iv_shifushangmen = (ImageView) window.findViewById(R.id.iv_shifushangmen);

        iv_shifushangmen.setVisibility(View.GONE);
        iv_back.setVisibility(View.VISIBLE);
        tv_cancel.setVisibility(View.GONE);
        tv_sure.setVisibility(View.GONE);
        rl_kuaidi.setVisibility(View.GONE);
        view_1.setVisibility(View.GONE);
        rl_ziti.setVisibility(View.GONE);
        view_2.setVisibility(View.GONE);

        // 预约送货时间
        rl_shifushangmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog(context, tv_shangmen_date, 0);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    /**
     * 日历对话框
     *
     * @param context
     * @param textView //     * @param flag 1-自提时间  0-送货时间
     */
    public static void showDatePickDialog(Activity context, final TextView textView, final int flag) {
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dlganim);
        window.setContentView(R.layout.dlg_choose_date);
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        window.setLayout(width, height * 2 / 5);
        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
        final DatePicker datePicker = (DatePicker) window.findViewById(R.id.date_picker);
        TextView tv_sure = (TextView) window.findViewById(R.id.tv_sure);
        TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);

        if (flag == 1) {// 限制自提时间
            final Calendar ca1 = Calendar.getInstance();
            final Calendar ca2 = Calendar.getInstance();
            final Calendar ca = Calendar.getInstance();
            final int fyear = ca1.get(Calendar.YEAR);
            final int fmonth = ca1.get(Calendar.MONTH);
            final int fday = ca1.get(Calendar.DAY_OF_MONTH);

            ca1.add(Calendar.DAY_OF_MONTH, 2);
            final int yearaft3 = ca1.get(Calendar.YEAR);
            final int monthaft3 = ca1.get(Calendar.MONTH);
            final int dayaft3 = ca1.get(Calendar.DAY_OF_MONTH);

            datePicker.init(fyear, fmonth, fday, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    ca.set(year, monthOfYear, dayOfMonth);
                    if (ca.after(ca1)) {
                        datePicker.init(yearaft3, monthaft3, dayaft3, this);
                    }else if (ca.before(ca2)){
                        datePicker.init(fyear, fmonth, fday, this);
                    }
                }
            });
        }else {// 限制送货时间
            final Calendar ca1 = Calendar.getInstance();
            final Calendar ca = Calendar.getInstance();
            final int fyear = ca1.get(Calendar.YEAR);
            final int fmonth = ca1.get(Calendar.MONTH);
            final int fday = ca1.get(Calendar.DAY_OF_MONTH);

            datePicker.init(fyear, fmonth, fday, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    ca.set(year, monthOfYear, dayOfMonth);
                    if (ca.before(ca1)){
                        datePicker.init(fyear, fmonth, fday, this);
                    }
                }
            });
        }
        // 确定
        tv_sure.setTag(R.id.pop1, datePicker);
        tv_sure.setTag(R.id.pop2, textView);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = (DatePicker) v.getTag(R.id.pop1);
                TextView textView = (TextView) v.getTag(R.id.pop2);
                int year = picker.getYear();
                int month = picker.getMonth() + 1;
                int day = picker.getDayOfMonth();
                String data = "";
                String textdata = "";
                String month1 = String.valueOf(month);
                String day1 = String.valueOf(day);
                if (month < 10) {
                    month1 = "0" + month;
                }
                if (day < 10) {
                    day1 = "0" + day;
                }
                textdata = year + "-" + month1 + "-" + day1;
                textView.setVisibility(View.VISIBLE);
                textView.setText(textdata);
                dialog.cancel();
            }
        });

        // 取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    textView.setText("仅可以选择填写日的今明后天");
                } else {
                    textView.setText("");
                }
                dialog.cancel();
            }
        });
    }
}
