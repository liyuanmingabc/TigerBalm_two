package com.cnpay.tigerbalm.utils.dialog.fragment;

import android.content.DialogInterface;
import android.view.View;

import com.cnpay.tigerbalm.utils.dialog.DialogUtil;


/**
 * 名称：DialogFragment.java
 * 描述：弹出框的父类
 */
public class DialogFragment extends android.app.DialogFragment {

    private View mIndeterminateView = null;
    public String mMessage;
    private DialogInterface.OnCancelListener mOnCancelListener = null;
    private DialogInterface.OnDismissListener mOnDismissListener = null;
    private DialogOnLoadListener mAbDialogOnLoadListener = null;

    public DialogFragment() {
        super();
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        // 用户中断
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }

        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        // 用户隐藏
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
        super.onDismiss(dialog);
    }

    public DialogInterface.OnCancelListener getOnCancelListener() {
        return mOnCancelListener;
    }

    public void setOnCancelListener(
            DialogInterface.OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    public void setOnDismissListener(
            DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }


    /**
     * 加载调用
     */
    public void load(View v) {
        if (mAbDialogOnLoadListener != null) {
            mAbDialogOnLoadListener.onLoad();
        }
        mIndeterminateView = v;
        /*AnimationUtil.playRotateAnimation(mIndeterminateView, 300, Animation.INFINITE,
				Animation.RESTART);*/
    }

    /**
     * 加载成功调用
     */
    public void loadFinish() {
        //停止动画
        loadStop();
        DialogUtil.removeDialog(this.getActivity());
    }

    /**
     * 加载结束
     */
    public void loadStop() {
        //停止动画
        mIndeterminateView.postDelayed(new Runnable() {

            @Override
            public void run() {
                mIndeterminateView.clearAnimation();
            }

        }, 200);

    }

    public DialogOnLoadListener getDialogOnLoadListener() {
        return mAbDialogOnLoadListener;
    }

    public void setDialogOnLoadListener(DialogOnLoadListener abDialogOnLoadListener) {
        this.mAbDialogOnLoadListener = abDialogOnLoadListener;
    }

    public String getMessage() {
        return mMessage;
    }


    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }


    /**
     * 加载事件的接口.
     */
    public interface DialogOnLoadListener {

        /**
         * 加载
         */
        public void onLoad();

    }

}
