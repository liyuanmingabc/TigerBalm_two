package com.cnpay.tigerbalm.utils.task;

/**
 * 名称：TaskObjectListener.java
 * 描述：数据执行的接口.
 *
 * 包            名:      com.cnpay.tigerbalm.utils.task
 * 类            名:      TaskObjectListener
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 */
public abstract class TaskObjectListener extends TaskListener{
    /**
     * Gets the object.
     *
     * @param <T> the generic type
     * @return 返回的结果对象
     */
    public abstract <T> T getObject();

    /**
     * 描述：执行开始后调用.
     *
     * @param <T> the generic type
     * @param obj the obj
     */
    public abstract <T> void update(T obj);
}
