package com.cnpay.tigerbalm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;

/**
 * 保存到应用目录下
 */
public class PreferencesUtil {

    /**
     * 保存一个实体
     *
     * @param context  context
     * @param fileName fileName
     * @param key      key
     * @param value    value
     * @throws IOException IOException
     * @author liyuanming
     */
    public static void saveObj(Context context, String fileName, String key, Object value) throws IOException {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(value);
        String objString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        sp.edit().putString(key, objString).apply();
        objectOutputStream.close();
    }

    /**
     * 取出一个实体
     *
     * @param context  context
     * @param fileName fileName
     * @param key      key
     * @return Object
     * @throws StreamCorruptedException StreamCorruptedException
     * @throws IOException              IOException
     * @throws ClassNotFoundException   ClassNotFoundException
     * @author liyuanming
     */
    public static Object getObj(Context context, String fileName, String key) throws StreamCorruptedException, IOException,
            ClassNotFoundException {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String str = sp.getString(key, "");
        if (str.length() <= 0)
            return null;
        Object obj;
        byte[] mobileBytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        obj = objectInputStream.readObject();
        objectInputStream.close();
        return obj;
    }

    /**
     * 添加一个String到
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @param value    value
     * @return boolean
     */
    public static boolean putString(String fileName, Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 取得一个String 的数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @return String
     */
    public static String getString(String fileName, Context context, String key) {
        return getString(fileName, context, key, null);
    }

    /**
     * 带默认值的
     *
     * @param fileName     fileName
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return String
     */
    public static String getString(String fileName, Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * 存储int类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @param value    value
     * @return boolean
     */
    public static boolean putInt(String fileName, Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 获取int类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @return int
     */
    public static int getInt(String fileName, Context context, String key) {
        return getInt(fileName, context, key, -1);
    }

    /**
     * 获取int类型数据
     *
     * @param fileName     fileName
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return int
     */
    public static int getInt(String fileName, Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * 存储long类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @param value    value
     * @return boolean
     */
    public static boolean putLong(String fileName, Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获取long类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @return long
     */
    public static long getLong(String fileName, Context context, String key) {
        return getLong(fileName, context, key, -1);
    }

    /**
     * 获取long类型数据
     *
     * @param fileName     fileName
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return long
     */
    public static long getLong(String fileName, Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * 存储Float类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @param value    value
     * @return boolean
     */
    public static boolean putFloat(String fileName, Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 获取Float类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @return float
     */
    public static float getFloat(String fileName, Context context, String key) {
        return getFloat(fileName, context, key, -1);
    }

    /**
     * 获取Float类型数据 没有就默认
     *
     * @param fileName     fileName
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return float
     */
    public static float getFloat(String fileName, Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * 存储的布尔类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @param value    value
     * @return boolean
     */
    public static boolean putBoolean(String fileName, Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 获取本地存储的布尔类型数据
     *
     * @param fileName fileName
     * @param context  context
     * @param key      key
     * @return boolean
     */
    public static boolean getBoolean(String fileName, Context context, String key) {
        return getBoolean(fileName, context, key, false);
    }

    /**
     * 获取本地存储的布尔类型数据,如果没有则赋默认值
     *
     * @param fileName     fileName
     * @param context      context
     * @param key          key
     * @param defaultValue defaultValue
     * @return boolean
     */
    public static boolean getBoolean(String fileName, Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * 把集合转换成字符串
     *
     * @param weatherMap WeatherMap
     * @return String
     * @throws IOException IOException
     */
    public static String mapToString(Map<String, Object> weatherMap) throws IOException {
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(weatherMap);
        // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
        String weatherListString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
        // 关闭objectOutputStream
        objectOutputStream.close();
        return weatherListString;
    }

    /**
     * 把字符串转换成集合
     *
     * @param weatherListString WeatherListString
     * @return Map<String, Object>
     * @throws Exception Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> stringToMap(String weatherListString) throws Exception {
        byte[] mobileBytes = Base64.decode(weatherListString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Map<String, Object> weatherMap = (Map<String, Object>) objectInputStream.readObject();
        objectInputStream.close();
        return weatherMap;
    }

    /**
     * 清空本地数据文件
     *
     * @param fileName fileName
     * @param context  context
     */
    public static void clearPreferences(String fileName, Context context) {
        SharedPreferences settings = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

}
