package com.cnpay.tigerbalm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.cnpay.tigerbalm.utils.AppManager;


/**
 * 包   名:     com.cnpay.tigerbalm.activity
 * 类   名:     TbBaseActivity
 * 时   间:     2016/12/2 14:07
 * 作   者:     liyuanming
 */
public abstract class TbBaseActivity extends Activity implements TbIBaseViewListener, View.OnClickListener {

    /**
     * mInflater
     */
    protected LayoutInflater mInflater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.addActivity(this);
        mInflater = LayoutInflater.from(this);
        initContentView();
//        ButterKnife.bind(this);
        initWidget(savedInstanceState);
    }

   /* @Override
    @Subscribe
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }


    @Override
    @Subscribe
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterEventBus();
//        ButterKnife.unbind(this);
        // 结束Activity从堆栈中移除
        AppManager.finishActivity(this);
    }

    /**
     * 获取布局
     *
     * @return int
     */
    protected abstract int getLayoutId();

    /**
     * 初始化主布局
     */
    protected void initContentView() {
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
    }

    @Override
    public void onClick(View v) {
        onWidgetClick(v);
    }
}
