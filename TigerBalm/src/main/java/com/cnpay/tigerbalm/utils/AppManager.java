package com.cnpay.tigerbalm.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 *
 */
public class AppManager {
    /**
     *
     */
    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * 添加Activity到堆栈
     *
     * @param activity activity
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     *
     * @return Activity
     */
    public static Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     *
     * @param activity activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 关闭除-当前-外的全部 - activity
     */
    public static void finishNoActivity() {
        Activity activity = currentActivity();
        if (activity != null) {
            TbLog.e("--当前ACTIVITY --" + activity.getClass().getName());
            for (Activity mac : activityStack) {
                if (mac != null && !mac.equals(activity)) {
                    TbLog.e("--REMOVE --" + mac.getClass().getName());
                    mac.finish();
                    activityStack.remove(mac);
                }
            }
            activityStack.clear();
            activityStack.add(activity);
        } else {
            TbLog.e("--当前ACTIVITY NULL--");
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls cls
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 关闭除了指定以外的所有
     *
     * @param cls 指定
     */
    public static void findNotActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (null != activity && !activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     *
     * @param context context
     */
    public static void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
