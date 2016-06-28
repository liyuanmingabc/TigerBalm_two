package com.cnpay.tigerbalm.utils.dialog;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.cnpay.tigerbalm.utils.dialog.fragment.AlertDialogFragment;
import com.cnpay.tigerbalm.utils.dialog.fragment.LoadDialogFragment;
import com.cnpay.tigerbalm.utils.dialog.fragment.ProgressDialogFragment;
import com.cnpay.tigerbalm.utils.dialog.fragment.RefreshDialogFragment;
import com.cnpay.tigerbalm.utils.dialog.fragment.SampleDialogFragment;

// TODO: Auto-generated Javadoc

/**
 * 名称：DialogUtil.java 描述：Dialog工具类.
 */

public class DialogUtil {

    /**
     * dialog tag
     */
    public static String mDialogTag = "dialog";

    /**
     * 全屏显示一个Fragment
     *
     * @param view
     * @return
     */
    public static SampleDialogFragment showFragment(View view) {

        removeDialog(view);

        FragmentActivity activity = (FragmentActivity) view.getContext();
        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        // 作为全屏显示,使用“content”作为fragment容器的基本视图,这始终是Activity的基本视图
        ft.add(android.R.id.content, newFragment, mDialogTag).addToBackStack(null).commitAllowingStateLoss();

        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     */
    public static SampleDialogFragment showDialog(View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @return
     */
    public static SampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setContentView(view);

        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     * @param onCancelListener
     * @return
     */
    public static SampleDialogFragment showDialog(View view, DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.setOnCancelListener(onCancelListener);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(有背景层)
     *
     * @param view
     * @param animEnter
     * @param animExit
     * @param animPopEnter
     * @param animPopExit
     * @param onCancelListener
     * @return
     */
    public static SampleDialogFragment showDialog(View view, int animEnter, int animExit, int animPopEnter, int animPopExit,
                                                  DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.setOnCancelListener(onCancelListener);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(无背景层)
     *
     * @param view
     */
    public static SampleDialogFragment showPanel(View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Light_Panel);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个自定义的对话框(无背景层)
     *
     * @param view
     * @param onCancelListener
     * @return
     */
    public static SampleDialogFragment showPanel(View view, DialogInterface.OnCancelListener onCancelListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);

        // Create and show the dialog.
        SampleDialogFragment newFragment = SampleDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Light_Panel);
        newFragment.setContentView(view);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.setOnCancelListener(onCancelListener);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：对话框dialog （图标，标题，String内容）.
     *
     * @param context
     * @param icon
     * @param title   对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(Context context, int icon, String title, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（图标，标题，string内容，确认，取消）.
     *
     * @param context
     * @param icon
     * @param title           对话框标题内容
     * @param message         对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(Context context, int icon, String title, String message,
                                                      AlertDialogFragment.DialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, message, null, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（标题，String内容，确认，取消）.
     *
     * @param context
     * @param title           对话框标题内容
     * @param message         对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(Context context, String title, String message,
                                                      AlertDialogFragment.DialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, message, null, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（View内容）.
     *
     * @param view 对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(View view) {
        if (null == view) return null;
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, null, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（String内容）.
     *
     * @param context
     * @param message 对话框标题内容
     */
    public static AlertDialogFragment showAlertDialog(Context context, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, null, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：对话框dialog （图标，标题，View内容）.
     *
     * @param icon
     * @param title 对话框标题内容
     * @param view  对话框提示内容
     */
    public static AlertDialogFragment showAlertDialog(int icon, String title, View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（图标，标题，View内容，确认，取消）.
     *
     * @param icon
     * @param title           对话框标题内容
     * @param view            对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(int icon, String title, View view, AlertDialogFragment.DialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon, title, null, view, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：对话框dialog （标题，View内容）.
     *
     * @param title 对话框标题内容
     * @param view  对话框提示内容
     */
    public static AlertDialogFragment showAlertDialog(String title, View view) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, null, view, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 显示一个一般的对话框（标题，View内容，确认，取消）.
     *
     * @param title           对话框标题内容
     * @param view            对话框提示内容
     * @param onClickListener 点击确认按钮的事件监听
     */
    public static AlertDialogFragment showAlertDialog(String title, View view, AlertDialogFragment.DialogOnClickListener onClickListener) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, null, view, onClickListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：对话框dialog （标题，String内容）.
     *
     * @param context
     * @param title   对话框标题内容
     * @param message 对话框提示内容
     */
    public static AlertDialogFragment showAlertDialog(Context context, String title, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0, title, message, null, null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示进度框.
     *
     * @param context               the context
     * @param indeterminateDrawable 用默认请写0
     * @param message               the message
     */
    public static ProgressDialogFragment showProgressDialog(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        ProgressDialogFragment newFragment = ProgressDialogFragment.newInstance(indeterminateDrawable, message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static LoadDialogFragment showLoadDialog(Context context, int indeterminateDrawable, int theme, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        // android.R.style.Theme_Holo_Light_Dialog
        if (0 == theme) {
            theme = android.R.style.Theme_Holo_Light_Dialog;
        }
        LoadDialogFragment newFragment = LoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, theme);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static LoadDialogFragment showLoadDialog(Context context, int indeterminateDrawable, int theme, String message,
                                                    com.cnpay.tigerbalm.utils.dialog.fragment.DialogFragment.DialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        // android.R.style.Theme_Holo_Light_Dialog
        if (0 == theme) {
            theme = android.R.style.Theme_Holo_Light_Dialog;
        }
        LoadDialogFragment newFragment = LoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, theme);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     * @author liyuanming
     */
    public static LoadDialogFragment showLoadPanel(Context context, int indeterminateDrawable, int theme, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        if (0 == theme) {
            theme = android.R.style.Theme_Light_Panel;
        }
        LoadDialogFragment newFragment = LoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE, theme);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示加载框.
     *
     * @param context                   the context
     * @param indeterminateDrawable
     * @param message                   the message
     */
    public static LoadDialogFragment showLoadPanel(Context context, int indeterminateDrawable, String message,
                                                   com.cnpay.tigerbalm.utils.dialog.fragment.DialogFragment.DialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        LoadDialogFragment newFragment = LoadDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context                   the context
     * @param indeterminateDrawable
     * @param message                   the message
     */
    public static RefreshDialogFragment showRefreshDialog(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        RefreshDialogFragment newFragment = RefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context
     * @param indeterminateDrawable
     * @param message
     * @param abDialogOnLoadListener
     * @return
     */
    public static RefreshDialogFragment showRefreshDialog(Context context, int indeterminateDrawable, String message,
                                                          com.cnpay.tigerbalm.utils.dialog.fragment.DialogFragment.DialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        RefreshDialogFragment newFragment = RefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Holo_Light_Dialog);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context               the context
     * @param indeterminateDrawable
     * @param message               the message
     */
    public static RefreshDialogFragment showRefreshPanel(Context context, int indeterminateDrawable, String message) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        RefreshDialogFragment newFragment = RefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(null);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：显示刷新框.
     *
     * @param context
     * @param indeterminateDrawable
     * @param message
     * @param abDialogOnLoadListener
     * @return
     */
    public static RefreshDialogFragment showRefreshPanel(Context context, int indeterminateDrawable, String message,
                                                         com.cnpay.tigerbalm.utils.dialog.fragment.DialogFragment.DialogOnLoadListener abDialogOnLoadListener) {
        FragmentActivity activity = (FragmentActivity) context;
        removeDialog(activity);
        RefreshDialogFragment newFragment = RefreshDialogFragment.newInstance(DialogFragment.STYLE_NO_TITLE,
                android.R.style.Theme_Light_Panel);
        newFragment.setIndeterminateDrawable(indeterminateDrawable);
        newFragment.setMessage(message);
        newFragment.setDialogOnLoadListener(abDialogOnLoadListener);
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个过渡动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        newFragment.show(ft, mDialogTag);
        ft.add(newFragment,mDialogTag).commitAllowingStateLoss();
        return newFragment;
    }

    /**
     * 描述：移除Fragment.
     *
     * @param context the context
     */
    public static void removeDialog(Context context) {
        try {
            FragmentActivity activity = (FragmentActivity) context;
            if (null == activity) return;
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            // 指定一个过渡动画
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            Fragment prev = activity.getFragmentManager().findFragmentByTag(mDialogTag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            // 可能有Activity已经被销毁的异常
//			e.printStackTrace();
        }
    }

    /**
     * 描述：移除Fragment和View
     *
     * @param view
     */
    public static void removeDialog(View view) {
        if (null == view) return;
        removeDialog(view.getContext());
        removeSelfFromParent(view);
    }

    /**
     * 从父亲布局中移除自己
     *
     * @param v
     */
    private static void removeSelfFromParent(View v) {
        ViewParent parent = v.getParent();
        if (parent != null) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(v);
            }
        }
    }
}
