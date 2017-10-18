package com.cnpay.tigerbalm.net;

import org.json.JSONObject;

import java.util.List;

/**
 * 数据格式-同步请求
 * 包   名:     com.mh.patentnanny.net
 * 类   名:     SyncMsg
 * 时   间:     2016/10/20 15:17
 * 作   者:     liyuanming
 */
public class SyncMsg<T> {

    /**
     * 错误信息
     */
    public String errorMsg;
    /**
     * 是否成功
     */
    public boolean isSuccess;
    /**
     * 返回的JSON字符
     */
    public JSONObject json;

    /**
     * 数据列
     */
    public List<Class<T>> lists;

    public SyncMsg(boolean isSuccess, String errorMsg) {
        this.isSuccess = isSuccess;
        this.errorMsg = errorMsg;
    }

    public SyncMsg(boolean isSuccess, JSONObject json) {
        this.isSuccess = isSuccess;
        this.json = json;

    }

    public SyncMsg(boolean isSuccess, List<Class<T>> lists) {
        this.isSuccess = isSuccess;
        this.lists = lists;
    }
}
