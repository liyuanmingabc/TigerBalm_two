package com.cnpay.tigerbalm.app;

import android.app.Application;
import android.content.Context;

import com.yolanda.nohttp.NoHttp;

/**
 * 包            名:      com.cnpay.tigerbalm.app
 * 类            名:      TbApplication
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/1/4
 */
public class TbApplication extends Application {

    public Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();

        //NoHttp初始化
        NoHttp.init(this);
//        Logger.setDebug(true);
//        Logger.setTag("TbLib");
    }


}
