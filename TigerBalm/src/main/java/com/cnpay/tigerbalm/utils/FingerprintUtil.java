package com.cnpay.tigerbalm.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;

/**
 * Created by xiaoyao on 2017/9/14.
 * 指纹识别工具类
 */
public class FingerprintUtil {

    /**
     * 指纹验证类
     */
    private static CancellationSignal cancellationSignal;

    /**
     * 指纹验证
     *
     * @param context  上下文
     * @param listener 监听
     */
    public static void callFingerPrint(Context context, final OnCallBackListener listener) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 默认为 不支持该设备
            if (listener != null)
                listener.onSupportFailed();
            return;
        }
        FingerprintManagerCompat managerCompat = FingerprintManagerCompat.from(context);
        if (!managerCompat.isHardwareDetected()) { //判断设备是否支持
            if (listener != null)
                listener.onSupportFailed();
            return;
        }

        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (!keyguardManager.isKeyguardSecure()) { //判断设备是否处于安全保护中
            if (listener != null)
                listener.onInsecurity();
            return;
        }

        if (!managerCompat.hasEnrolledFingerprints()) { //判断设备是否已经注册过指纹
            if (listener != null)
                listener.onEnrollFailed(); //未注册
            return;
        }

        if (listener != null)
            listener.onAuthenticationStart(); //开始指纹识别

        cancellationSignal = new CancellationSignal(); //必须重新实例化，否则cancel 过一次就不能再使用了

        managerCompat.authenticate(null, 0, cancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
            // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息，比如华为的提示就是：尝试次数过多，请稍后再试。
            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                if (listener != null)
                    listener.onAuthenticationError(errMsgId, errString);
            }

            // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
            @Override
            public void onAuthenticationFailed() {
                if (listener != null)
                    listener.onAuthenticationFailed();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                if (listener != null)
                    listener.onAuthenticationHelp(helpMsgId, helpString);
            }

            // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                if (listener != null)
                    listener.onAuthenticationSucceeded(result);
            }
        }, null);

    }

    /**
     * 监听接口
     */
    public interface OnCallBackListener {
        /**
         * 当前设备不支持指纹
         */
        void onSupportFailed();

        /**
         * 当前设备未处于安全保护中
         */
        void onInsecurity();

        /**
         * 请到设置中设置指纹
         */
        void onEnrollFailed();

        /**
         * 验证开始
         */
        void onAuthenticationStart();

        /**
         * 验证错误
         *
         * @param errMsgId  错误ID
         * @param errString 错误信息
         */
        void onAuthenticationError(int errMsgId, CharSequence errString);

        /**
         * 解锁失败
         */
        void onAuthenticationFailed();

        /**
         * 验证帮助
         *
         * @param helpMsgId  帮助ID
         * @param helpString 帮助信息
         */
        void onAuthenticationHelp(int helpMsgId, CharSequence helpString);

        /**
         * 解锁成功
         *
         * @param result result
         */
        void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result);
    }

    /**
     * 取消验证
     */
    public static void cancel() {
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
            cancellationSignal = null;
        }
    }
}
