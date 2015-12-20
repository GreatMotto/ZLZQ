package com.bm.zlzq.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * SharedPreferences帮助类
 */
@SuppressLint("CommitPrefEdits")
public class SharedPreferencesHelper {

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @SuppressWarnings("deprecation")
    public SharedPreferencesHelper(Context context, String filename) {
        sp = context.getSharedPreferences(filename, Context.MODE_WORLD_WRITEABLE);
    }

    public void remove(String KEY) {
        editor = sp.edit();
        editor.remove(KEY);
        editor.commit();
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public void putValue(String key, String value) {
        editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getValue(String key) {
        return sp.getString(key, "");
    }

    public void putBooleanValue(String key, boolean value) {
        editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBooleanValue(String key) {
        return sp.getBoolean(key, false);
    }

    public int getIntValue(String key) {
        return sp.getInt(key, 1);
    }

    public void putIntValue(String key, int value) {
        editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putListValue(String key, List value) {
        editor = sp.edit();
        try {
            String liststr = List2String(value);
            editor.putString(key, liststr);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getListValue(String key) {
        String liststr = sp.getString(key, "");
        List<String> list = null;
        try {
            list = String2List(liststr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String List2String(List list) throws IOException {
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(list);
        // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
        String SceneListString = new String(Base64.encode(
                byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭objectOutputStream
        objectOutputStream.close();
        return SceneListString;
    }

    public static List String2List(String ListString) throws IOException, ClassNotFoundException {
        byte[] mobileBytes = Base64.decode(ListString.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                mobileBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        List SceneList = (List) objectInputStream
                .readObject();
        objectInputStream.close();
        return SceneList;
    }


}
