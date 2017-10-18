package com.cnpay.tigerbalm.net;


import org.json.JSONObject;

/**
 * 结果回调接口
 */
public abstract class HttpListener {

    /**
     * 启动
     *
     * @param what 表示id
     */
    public void onStart(int what) {

    }

    /**
     * 请求成功
     *
     * @param what     表示id
     * @param response 返回的JSON字符
     * @throws Exception 错误信息
     */
    public abstract void onSuccess(int what, JSONObject response) throws Exception;

    /**
     * 请求失败
     *
     * @param what 表示id
     * @param msg  错误信息
     */
    public abstract void onFailed(int what, String msg);

    /**
     * 关闭
     *
     * @param what 表示id
     */
    public void onFinish(int what) {

    }
}
