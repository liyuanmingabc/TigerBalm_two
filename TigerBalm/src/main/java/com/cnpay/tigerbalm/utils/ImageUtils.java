package com.cnpay.tigerbalm.utils;

import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * 包            名:      com.cnpay.tigerbalm.utils
 * 类            名:      ImageUtils
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 */
public class ImageUtils {

    private static Context mContext;

    private static ImageUtils imageUtils;
    private static RequestManager manager;

    public static synchronized ImageUtils getInstance(Context mContext) {
        if (imageUtils == null || null == mContext) {
            synchronized (ImageUtils.class) {
                imageUtils = new ImageUtils();
                getInstanceRequest(mContext);
            }
        }
        return imageUtils;
    }

    /**
     * 图片加载器
     *
     * @param mContext 上下文
     * @return
     */
    public static synchronized RequestManager getInstanceRequest(Context mContext) {
        if (manager == null || null == ImageUtils.mContext) {
            synchronized (ImageUtils.class) {
                manager = Glide.with(mContext);
                ImageUtils.mContext = mContext;
            }
        }
        return manager;
    }

    /**
     * 图片加载
     *
     * @param url             图片地址
     * @param defaultResource 默认加载图
     * @return
     */
    public DrawableRequestBuilder getImage(String url, int defaultResource) {
        return manager.load(url).crossFade()
                .placeholder(defaultResource)
                .fallback(defaultResource)
                .error(defaultResource);
    }

}
