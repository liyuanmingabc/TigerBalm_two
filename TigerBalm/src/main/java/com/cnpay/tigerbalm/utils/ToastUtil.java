package com.cnpay.tigerbalm.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpay.tigerbalm.R;

/**
 * 名称：ToastUtil.java
 * 描述：Toast工具类.
 *
 * @version V1.0
 */
public class ToastUtil {
    /** 上下文. */
    //private static Context mContext = null;

    /**
     * 显示Toast.
     */
    public static final int SHOW_TOAST = 0;

    /**
     * 主要Handler类，在线程中可用
     * what：0.提示文本信息
     */
    /*private static Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TOAST:
                    showToast(mContext,msg.getData().getString("TEXT"));
                    break;
                default:
                    break;
            }
        }
    };*/


    /**
     * 描述：Toast提示文本.
     *
     * @param context context
     * @param text    文本
     */
    public static void showToast(Context context, String text) {
        //mContext = context;
        if (!StrUtil.isEmpty(text) && !text.contains("check the network") && !text.contains("java.lang.String")) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 描述：Toast提示文本.
     *
     * @param context context
     * @param resId   文本的资源ID
     */
    public static void showToast(Context context, int resId) {
        //mContext = context;
        Toast.makeText(context, "" + context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
    }


    /**
     * 自定义中间吐司
     *
     * @param context context
     * @param text    信息
     */
    public static void showCenterToast(Context context, String text) {
        if (!StrUtil.isEmpty(text) && !text.contains("check the network") && !text.contains("java.lang.String")) {
            View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
            TextView infoView = (TextView) view.findViewById(R.id.view_Toast_text);
            infoView.setText(text);
            Toast toast = SingleToast.getInstance(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
    }


    /**
     * 描述：Toast提示文本.
     * @param text  文本
     */
    /*public static void showToastLong(Context context,String text) {
        mContext = context;
        if(!StrUtil.isEmpty(text)){
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }

    }*/
    /**
     * 描述：在线程中提示文本信息.
     * @param resId 要提示的字符串资源ID，消息what值为0,
     */
    /*public static void showToastInThread(Context context,int resId) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", context.getResources().getString(resId));
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }*/

    /**
     * 描述：在线程中提示文本信息.
     * @param
     */
    /*public static void showToastInThread(Context context,String text) {
        mContext = context;
        Message msg = baseHandler.obtainMessage(SHOW_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", text);
        msg.setData(bundle);
        baseHandler.sendMessage(msg);
    }*/
}

class SingleToast {
    private static Toast mToast;

    /**
     * 双重锁定，使用同一个Toast实例
     */
    public static Toast getInstance(Context context) {
        if (mToast == null) {
            synchronized (SingleToast.class) {
                if (mToast == null) {
                    mToast = new Toast(context);
                }
            }
        }
        return mToast;
    }
}
