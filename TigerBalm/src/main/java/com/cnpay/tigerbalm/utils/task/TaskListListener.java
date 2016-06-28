package com.cnpay.tigerbalm.utils.task;

import java.util.List;

/**
 * 名称：TaskListListener.java
 * 描述：数据执行的接口.
 *
 * 包            名:      com.cnpay.tigerbalm.utils.task
 * 类            名:      TaskListListener
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 * @date 2016/1/19 0019
 */
public abstract class TaskListListener extends TaskListener{
    /**
     * Gets the list.
     *
     * @return 返回的结果列表
     */
    public abstract List<?> getList();

    /**
     * 描述：执行完成后回调.
     * 不管成功与否都会执行
     * @param paramList 返回的List
     */
    public abstract void update(List<?> paramList);
}
