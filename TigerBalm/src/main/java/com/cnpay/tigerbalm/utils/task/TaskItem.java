package com.cnpay.tigerbalm.utils.task;

/**
 * 名称：TaskItem.java
 * 描述：数据执行单位.
 *
 * 包            名:      com.cnpay.tigerbalm.utils.task
 * 类            名:      TaskItem
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 * @date 2016/1/19 0019
 */
public class TaskItem {
    /** 记录的当前索引. */
    private int position;

    /** 执行完成的回调接口. */
    private TaskListener listener;

    /**
     * Instantiates a new ab task item.
     */
    public TaskItem() {
        super();
    }

    /**
     * Instantiates a new ab task item.
     *
     * @param listener the listener
     */
    public TaskItem(TaskListener listener) {
        super();
        this.listener = listener;
    }

    /**
     * Gets the position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets the listener.
     *
     * @return the listener
     */
    public TaskListener getListener() {
        return listener;
    }

    /**
     * Sets the listener.
     *
     * @param listener the new listener
     */
    public void setListener(TaskListener listener) {
        this.listener = listener;
    }
}
