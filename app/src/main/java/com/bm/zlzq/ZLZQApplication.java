package com.bm.zlzq;

import android.app.Activity;
import android.app.Application;

import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.SharedPreferencesHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/1.
 */
public class ZLZQApplication extends Application {
    public static ZLZQApplication instance;
    private List<Activity> acys = new LinkedList<Activity>();
    private SharedPreferencesHelper sp;

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
        initImageLoader();
        Fresco.initialize(this);
//        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
//        ImageLoader.getInstance().init(configuration);
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

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY)
                .threadPoolSize(3)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(4 * 1024 * 1024)
                .diskCacheSize(10 * 1024 * 1024)// 10 Mb
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .discCacheExtraOptions(480, 320, null)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
