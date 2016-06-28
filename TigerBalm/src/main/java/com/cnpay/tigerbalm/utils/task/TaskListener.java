package com.cnpay.tigerbalm.utils.task;

/**
 * 名称：TaskListener.java
 * 描述：任务执行的控制父类.
 *
 * 包            名:      com.cnpay.tigerbalm.utils.task
 * 类            名:      TaskListener
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 */
public class TaskListener {
    /**
     * Gets the.
     * 返回的结果对象
     */
    public void get(){};

    /**
     * 描述：执行开始后调用.
     * */
    public void update(){};

    /**
     * 监听进度变化.
     *
     * @param values the values
     */
    public void onProgressUpdate(Integer... values){};

}
