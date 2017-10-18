package com.cnpay.tigerbalm.activity;

import android.os.Bundle;
import android.view.View;

/**
 * 父视图操作
 *
 * @author Neil.zh.
 * @version 1.0
 */
public interface TbIBaseViewListener {

    /**
     * 初始化控件
     *
     * @param savedInstanceState savedInstanceState
     */
    void initWidget(Bundle savedInstanceState);

    /**
     * 点击事件
     *
     * @param v view
     */
    void onWidgetClick(View v);
}
