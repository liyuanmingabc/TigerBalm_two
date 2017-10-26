package com.cnpay.tigerbalm.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * GsonUtils 工具类
 */
public class GsonUtils {
    /**
     * 构造器
     *
     * @return Gson
     */
    public static Gson createGson() {
        return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
    }

    /**
     * 返回一个gson, 指定日期的格式
     *
     * @param dateFormate dateFormate
     * @return Gson
     */
    public static Gson createGson(String dateFormate) {
        return new GsonBuilder().serializeNulls().setDateFormat(dateFormate).create();
    }

    /**
     * 创建无空
     * createWithoutNulls
     *
     * @return Gson
     */
    public static Gson createWithoutNulls() {
        return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    /**
     * 创建无Nulls禁用Html转义
     * createWithoutNullsDisableHtmlEscaping
     *
     * @return Gson
     */
    public static Gson createWithoutNullsDisableHtmlEscaping() {
        return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
                .disableHtmlEscaping().create();
    }

    /**
     * 把列出的属性加入到生成的json中
     *
     * @param o          obj
     * @param properties properties
     * @return String
     */
    public static String toJsonProperties(Object o, String... properties) {
        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setExclusionStrategies(new PropertiesInclude(properties));
        gsonBuilder.setDateFormat("yyyy-MM-dd");
        return gsonBuilder.create().toJson(o);
    }


    /**
     * 把列出的属性加入到生成的json中
     *
     * @param o          obj
     * @param properties properties
     * @return String
     */
    public static String toJsonPropertiesDes(Object o, String... properties) {
        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setExclusionStrategies(new PropertiesInclude(properties));
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return gsonBuilder.create().toJson(o);
    }

    /**
     * PropertiesInclude
     */
    static class PropertiesInclude implements ExclusionStrategy {
        /**
         * includeProSet
         */
        private HashSet<String> includeProSet = null;

        /**
         * 构造区
         *
         * @param pros pros
         */
        PropertiesInclude(String[] pros) {
            includeProSet = new HashSet<>(pros.length);
            Collections.addAll(includeProSet, pros);
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            String name = f.getName();
            return !includeProSet.contains(name);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }

    /*************************************************************
     * 转换
     *
     * @param jsonString jsonString
     * @param cls cls
     * @return cls
     ************************************************************/
    public static <T> T getPerson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = createGson().fromJson(jsonString, cls);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * @param jsonString jsonString
     * @param cls        cls
     */
    public static <T> List<T> getPersons(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = createGson().fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param jsonString jsonString
     * @return List
     */
    public static List<String> getList(String jsonString) {
        List<String> list = new ArrayList<>();
        try {
            list = createGson().fromJson(jsonString, new TypeToken<List<String>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * @param jsonString jsonString
     * @return List
     */
    public static List<Map<String, Object>> listKeyMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = createGson().fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
