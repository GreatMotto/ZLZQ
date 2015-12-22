package com.bm.zlzq.utils;

import android.content.Context;

import com.bm.zlzq.bean.AreaBean;
import com.bm.zlzq.bean.DataBean;
import com.bm.zlzq.bean.ProvinceBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxl01 on 2015/11/13.
 */
public class AddressUtil {
    private static List<ProvinceBean> listProvince = new ArrayList<ProvinceBean>();
    private static Context context;
    private static DataBean dataBean;

    private static AddressUtil instance;

    private AddressUtil() {
    }

    public static AddressUtil getInstance(Context con) {
        if (instance == null) {
            instance = new AddressUtil();
            context = con;
            getJson();
        }
        return instance;
    }

    // 解析JSON，获取城市列表
    private static void getJson() {
        if (dataBean == null) {
//            listProvince.clear();
            String jsonData_city = getFromAssets("city.json");
            Gson gson = new Gson();
//            listProvince = gson.fromJson(jsonData_city, new TypeToken<DataBean>() {
//            }.getType());
            dataBean = gson.fromJson(jsonData_city, new TypeToken<DataBean>() {
            }.getType());
        }
    }

    // 解析assets文件
    private static String getFromAssets(String fileName) {
        String result = "";
        InputStream in = null;
        try {
            in = context.getResources().getAssets().open(fileName);
            //InputStream in = context.getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);

            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
        }
        return result;
    }

    public static String getCityNameById(String provinceId, String cityId, String areaId) {
        String provinceName = "";
        String cityName = "";
        String areaName = "";
        if (listProvince.size() < 1) {
            getListProvince();
        }
        for (ProvinceBean provinceBean : listProvince) {
            if (provinceBean.id.equals(provinceId)) {
                provinceName = provinceBean.name;
                for (int i = 0; i < provinceBean.cityList.size(); i++) {
                    if (provinceBean.cityList.get(i).id.equals(cityId)) {
                        cityName = provinceBean.cityList.get(i).name;
                        for (AreaBean areaBean : provinceBean.cityList.get(i).areaList){
                            if (areaBean.id.equals(areaId)){
                                areaName = areaBean.name;
                            }
                        }
                    }
                }
            }
        }
        if (cityName.equals("市辖区") || cityName.equals("县")){
            cityName = "";
        }
        return provinceName + cityName + areaName;
    }

    public static List<ProvinceBean> getListProvince() {
        if (listProvince != null && listProvince.size() > 0) {
            return listProvince;
        } else {
            getJson();
            listProvince = dataBean.data.list;
            return listProvince;
        }
    }
}
