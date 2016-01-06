package com.bm.zlzq;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.SharedPreferencesHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/1.
 */
public class ZLZQApplication extends Application {
    public static ZLZQApplication instance;
    private List<Activity> acys = new LinkedList<Activity>();
    private SharedPreferencesHelper sp;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public ZLZQApplication() {
        instance = this;
    }

    public static synchronized ZLZQApplication getInstance() {
        if (instance == null) {
            instance = new ZLZQApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(myListener);
        initImageLoader(getApplicationContext());
        initLocation();
    }

    public SharedPreferencesHelper getSp() {
        if (sp == null) {
            sp = new SharedPreferencesHelper(this, Constant.SP_NAME);
        }
        return sp;
    }

    public void setSp(SharedPreferencesHelper sp) {
        this.sp = sp;
    }

    public void addActivity(Activity acy) {
        acys.add(acy);
    }

    // 完全退出应用
    public void exit() {
        for (Activity ac : acys) {
            ac.finish();
        }
        System.exit(0);
    }

    //清除所有Activity
    public void finishActivity() {
        for (Activity ac : acys) {
            ac.finish();
        }
    }

    public void finishOneActivity(Activity acy) {
        acy.finish();
    }

    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "zlzq/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)
                .diskCacheExtraOptions(480, 320, null)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();

    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e("Longitude", String.valueOf(location.getLongitude()));
            Log.e("Latitude", String.valueOf(location.getLatitude()));
            sp.putValue(Constant.LONTITUDE, String.valueOf(location.getLongitude()));//经度
            sp.putValue(Constant.LATITUDE, String.valueOf(location.getLatitude()));//纬度
            mLocationClient.stop();
        }
    }
}
