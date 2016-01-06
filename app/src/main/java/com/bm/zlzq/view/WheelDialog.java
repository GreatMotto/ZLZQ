package com.bm.zlzq.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.AreaBean;
import com.bm.zlzq.bean.CityBean;
import com.bm.zlzq.bean.GuigeBean;
import com.bm.zlzq.bean.ProvinceBean;
import com.bm.zlzq.utils.AddressUtil;

import java.util.ArrayList;
import java.util.List;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.adapters.AbstractWheelTextAdapter;

/**
 * Created by yangk on 2015/7/22.
 */
public class WheelDialog {

    private Dialog alertDialog;
    private String province = "", city = "", area = "";
    public static final String ENCODING = "UTF-8";
    public String item = "", guigeItem = "", provinceId, cityId, areaId;
    private AbstractWheel wv_province, wv_city, wv_area;

    private WheelDialog() {

    }

    private static class Datacontroller {

        /**
         * 单例变量
         */
        private static WheelDialog instance = new WheelDialog();
    }

    public static WheelDialog getInstance() {
        return Datacontroller.instance;
    }

    private void showDialog(Activity context) {
        alertDialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dlganim);
        alertDialog.show();
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        alertDialog.getWindow().setLayout(width,
                WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.setCanceledOnTouchOutside(true);
    }

    public List<ProvinceBean> list = new ArrayList<ProvinceBean>();

    public void chooseCity(final Activity context, final TextView v,
                           final GetCityIdListener listener) {
        list.clear();
        list.addAll(AddressUtil.getInstance(context).getListProvince());
        showDialog(context);
        alertDialog.getWindow().setContentView(R.layout.wheel_area);
        TextView tv_title = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_title);
        TextView tv_sure = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_sure);
        tv_title.setText("选择地区");

        province = list.get(0).name;
        city = list.get(0).cityList.get(0).name;
        area = list.get(0).cityList.get(0).areaList.get(0).name;

        provinceId = list.get(0).id;
        cityId = list.get(0).cityList.get(0).id;
        areaId = list.get(0).cityList.get(0).areaList.get(0).id;

        wv_province = (AbstractWheel) alertDialog.findViewById(R.id.wv_province);
        wv_city = (AbstractWheel) alertDialog.findViewById(R.id.wv_city);
        wv_area = (AbstractWheel) alertDialog.findViewById(R.id.wv_area);

        wv_province.setVisibleItems(3);
        wv_province.setViewAdapter(new IdentifyAdapter(context, null, null, true, false));
        wv_province.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                province = list.get(newValue).name;
                if (list.get(newValue).cityList.size() > 0) {
                    city = list.get(newValue).cityList.get(0).name;
                    area = list.get(newValue).cityList.get(0).areaList.get(0).name;
                }
                provinceId = list.get(newValue).id;
                if (list.get(newValue).cityList.size() > 0) {
                    cityId = list.get(newValue).cityList.get(0).id;
                    areaId = list.get(newValue).cityList.get(0).areaList.get(0).id;
                }

                if (list.get(newValue).cityList.size() > 0) {
                    wv_city.setVisibility(View.VISIBLE);
                    wv_area.setVisibility(View.VISIBLE);
                    wv_city.setViewAdapter(new IdentifyAdapter(context, list.get(newValue).cityList, null, false, true));
                    wv_area.setViewAdapter(new IdentifyAdapter(context, null, list.get(newValue).cityList.get(0).areaList, false, false));
                    wv_city.setCurrentItem(0);
                    wv_area.setCurrentItem(0);
                } else {
                    wv_city.setVisibility(View.GONE);
                    wv_area.setVisibility(View.GONE);
                    city = "";
                    area = "";
                    cityId = "";
                    areaId = "";
                }
            }
        });
        wv_city.setVisibleItems(3);
        wv_city.setViewAdapter(new IdentifyAdapter(context, list.get(0).cityList, null, false, true));
        wv_city.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                city = list.get(wv_province.getCurrentItem()).cityList.get(newValue).name;
                area = list.get(wv_province.getCurrentItem()).cityList.get(newValue).areaList.get(0).name;

                cityId = list.get(wv_province.getCurrentItem()).cityList.get(newValue).id;
                areaId = list.get(wv_province.getCurrentItem()).cityList.get(newValue).areaList.get(0).id;

                wv_area.setViewAdapter(new IdentifyAdapter(context, null, list.get(wv_province.getCurrentItem()).cityList.get(newValue).areaList, false, false));
                wv_area.setCurrentItem(0);
            }
        });
        wv_area.setVisibleItems(3);
        wv_area.setViewAdapter(new IdentifyAdapter(context, null, list.get(wv_province.getCurrentItem()).cityList.get(0).areaList, false, false));
        wv_area.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                area = list.get(wv_province.getCurrentItem()).cityList.get(wv_city.getCurrentItem()).areaList.get(newValue).name;
                areaId = list.get(wv_province.getCurrentItem()).cityList.get(wv_city.getCurrentItem()).areaList.get(newValue).id;
            }
        });
//        tv_cancel.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                alertDialog.cancel();
//
//            }
//        });
        tv_sure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                if (city.equals("市辖区") || city.equals("县")) {
                    city = "";
                }
                if (area.equals("市辖区")) {
                    area = "";
                }
                v.setText(province + city + area);
                listener.GetCityId(provinceId, cityId, areaId);
                alertDialog.cancel();
            }
        });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e("setOnCancelListener", "setOnCancelListener");
                list.clear();
            }
        });

    }


    private List<String> lists = new ArrayList<String>();

    public void ChossDateOrNumDlg(final Activity context, final String title, final TextView textView, List<String> list,
                                  final GetCityIdListener listener) {
        lists.clear();
        showDialog(context);
        alertDialog.getWindow().setContentView(R.layout.dlg_choose_date_or_num);
        TextView tv_title = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_title);
        TextView tv_sure = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_sure);
        lists.addAll(list);
        tv_title.setText(title);
        item = list.get(0);
        final AbstractWheel wv_view = (AbstractWheel) alertDialog.findViewById(R.id.wv_view);
        wv_view.setVisibleItems(3);
        ChooseAdapetr chooseAdapetr = new ChooseAdapetr(context, lists);
        wv_view.setViewAdapter(chooseAdapetr);
        wv_view.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                item = lists.get(newValue);
            }
        });
//        tv_cancel.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                alertDialog.cancel();
//            }
//        });
        tv_sure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                textView.setText(item);
                listener.GetCityId(item, "", "");
                alertDialog.cancel();
            }
        });
    }

    private List<GuigeBean> guigeList = new ArrayList<GuigeBean>();

    public String getQuality(String flag) {
        String result = "";
        if (flag.equals("0")) {
            result = "全新";
        } else if (flag.equals("1")) {
            result = "九成新";
        } else if (flag.equals("2")) {
            result = "八成新";
        } else if (flag.equals("3")) {
            result = "七成新";
        } else if (flag.equals("4")) {
            result = "七成以下";
        }
        return result;
    }

    /**
     * 规格弹出对话框
     *
     * @param context
     * @param textView
     * @param list
     * @param flag     0买  1租
     * @param listener
     */
    public void SelectGuigeDialog(final Activity context, final TextView textView, final List<GuigeBean> list, int flag,
                                  final GetCityIdListener listener) {
        guigeList.clear();
        showDialog(context);
        alertDialog.getWindow().setContentView(R.layout.dlg_choose_guige);
        final TextView tv_price = (TextView) alertDialog.getWindow().findViewById(R.id.tv_price);
        final TextView tv_price_old = (TextView) alertDialog.getWindow().findViewById(R.id.tv_price_old);
        final TextView tv_yajin = (TextView) alertDialog.getWindow().findViewById(R.id.tv_yajin);
        final TextView tv_stock = (TextView) alertDialog.getWindow().findViewById(R.id.tv_stock);
        final TextView tv_quality = (TextView) alertDialog.getWindow().findViewById(R.id.tv_quality);
        TextView tv_sure = (TextView) alertDialog.getWindow().findViewById(R.id.tv_sure);
        if (flag == 0) {
            tv_price_old.setVisibility(View.VISIBLE);
            tv_yajin.setVisibility(View.GONE);
        } else {
            tv_price_old.setVisibility(View.GONE);
            tv_yajin.setVisibility(View.VISIBLE);
        }
        guigeList.addAll(list);
        guigeItem = list.get(0).name;
        tv_price.setText("￥" + list.get(0).price_two);
        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_price_old.setText("￥" + list.get(0).price_one);
        tv_yajin.setText("押金:" + list.get(0).price_two + "元");
        tv_stock.setText("库存" + list.get(0).stock + "件");
        tv_quality.setText("新旧程度:" + getQuality(list.get(0).quality));
        final AbstractWheel wv_view = (AbstractWheel) alertDialog.findViewById(R.id.wv_view);
        wv_view.setVisibleItems(3);
        GuigeAdapetr guigeAdapetr = new GuigeAdapetr(context, guigeList);
        wv_view.setViewAdapter(guigeAdapetr);
        wv_view.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(AbstractWheel wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                guigeItem = guigeList.get(newValue).name;
                tv_price.setText("￥" + guigeList.get(newValue).price_two);
                tv_price_old.setText("￥" + list.get(newValue).price_one);
                tv_yajin.setText("押金:" + list.get(newValue).price_two + "元");
                tv_stock.setText("库存" + guigeList.get(newValue).stock + "件");
                tv_quality.setText("新旧程度:" + getQuality(guigeList.get(newValue).quality));
            }
        });
//        tv_cancel.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                alertDialog.cancel();
//            }
//        });
        tv_sure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                textView.setText(guigeItem);
                listener.GetCityId(guigeItem, "", "");
                alertDialog.cancel();
            }
        });
    }

    /**
     * 类型选择弹出框的适配器
     */
    public class IdentifyAdapter extends AbstractWheelTextAdapter {

        List<CityBean> listcity;
        List<AreaBean> listarea;
        boolean isProvince;
        boolean isCity;

        /**
         * Constructor
         */
        protected IdentifyAdapter(Context context, List<CityBean> listcity, List<AreaBean> listarea, boolean isProvince, boolean isCity) {
            super(context, R.layout.wheel_item, DEFAULT_TEXT_COLOR);
            this.listcity = listcity;
            this.listarea = listarea;
            this.isCity = isCity;
            this.isProvince = isProvince;
            setItemTextResource(R.id.tv_identify_item);
        }


        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            if (isProvince) {
                return list.size();
            } else if (isCity) {
                return listcity.size();
            } else {
                return listarea.size();
            }
        }

        @Override
        protected CharSequence getItemText(int index) {
            if (isProvince) {
                return list.get(index).name;
            } else if (isCity) {
                return listcity.get(index).name;
            } else {
                return listarea.get(index).name;
            }
        }

        @Override
        public int getItemResource() {

            return super.getItemResource();
        }

    }

    private class ChooseAdapetr extends AbstractWheelTextAdapter {
        List<String> lists;

        protected ChooseAdapetr(Context context, List<String> lists) {
            super(context, R.layout.wheel_item, DEFAULT_TEXT_COLOR);
            this.lists = lists;
            setItemTextResource(R.id.tv_identify_item);
        }

        @Override
        protected CharSequence getItemText(int index) {
            return lists.get(index);
        }

        @Override
        public int getItemsCount() {
            return lists.size();
        }

        @Override
        public int getItemResource() {

            return super.getItemResource();
        }


    }

    private class GuigeAdapetr extends AbstractWheelTextAdapter {
        List<GuigeBean> lists;

        protected GuigeAdapetr(Context context, List<GuigeBean> lists) {
            super(context, R.layout.wheel_item, DEFAULT_TEXT_COLOR);
            this.lists = lists;
            setItemTextResource(R.id.tv_identify_item);
        }

        @Override
        protected CharSequence getItemText(int index) {
            return lists.get(index).name;
        }

        @Override
        public int getItemsCount() {
            return lists.size();
        }

        @Override
        public int getItemResource() {

            return super.getItemResource();
        }
    }

    public interface GetCityIdListener {
        void GetCityId(String provinceId, String cityId, String areaId);
    }

}
