package com.cnpay.tigerbalm.view.list.listener;

/**
 * 加载数据 监听
 * <p/>
 * 数据回调操作
 */
public interface LoadResultCallBack {

    int SUCCESS_OK = 1001;
    int SUCCESS_NONE = 1002;
    int ERROR_NET = 1003;

    void onSuccess(int result, Object object);

    void onError(int code, String msg);
}
