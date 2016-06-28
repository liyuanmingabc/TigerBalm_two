package com.cnpay.tigerbalm.utils;

/**
 * 包            名:      com.cnpay.tigerbalm.utils
 * 类            名:      TaskQueue
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 * @date 2016/1/19 0019
 */

import com.cnpay.tigerbalm.utils.task.TaskItem;
import com.cnpay.tigerbalm.utils.task.TaskListListener;
import com.cnpay.tigerbalm.utils.task.TaskObjectListener;
import com.cnpay.tigerbalm.utils.task.ThreadFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 名称：TaskQueue.java
 * 描述：线程队列.
 *
 */

public class TaskQueue extends Thread{
    /** 等待执行的任务. 用 LinkedList增删效率高*/
    private static LinkedList<TaskItem> mAbTaskItemList = null;

    /** 单例对象. */
    private static TaskQueue abTaskQueue = null;

    /** 停止的标记. */
    private boolean mQuit = false;

    /**  存放返回的任务结果. */
    private static HashMap<String,Object> result;

    /** 执行完成后的消息句柄. */
    /*private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TaskItem item = (TaskItem)msg.obj;
            if(item.getListener() instanceof TaskListListener){
                ((TaskListListener)item.getListener()).update((List<?>)result.get(item.toString()));
            }else if(item.getListener() instanceof TaskObjectListener){
                ((TaskObjectListener)item.getListener()).update(result.get(item.toString()));
            }else{
                item.getListener().update();
            }
            result.remove(item.toString());
        }
    };*/

    /**
     * 单例构造.
     *
     * @return single instance of AbTaskQueue
     */
    public static TaskQueue getInstance() {
        if (abTaskQueue == null) {
            abTaskQueue = new TaskQueue();
        }
        return abTaskQueue;
    }

    /**
     * 构造执行线程队列.
     */
    public TaskQueue() {
        mQuit = false;
        mAbTaskItemList = new LinkedList<TaskItem>();
        result = new HashMap<String,Object>();
        //从线程池中获取
        Executor mExecutorService  = ThreadFactory.getExecutorService();
        mExecutorService.execute(this);
    }

    /**
     * 开始一个执行任务.
     *
     * @param item 执行单位
     */
    public void execute(TaskItem item) {
        addTaskItem(item);
    }


    /**
     * 开始一个执行任务并清除原来队列.
     * @param item 执行单位
     * @param cancel 清空之前的任务
     */
    public void execute(TaskItem item,boolean cancel) {
        if(cancel){
            cancel(true);
        }
        addTaskItem(item);
    }

    /**
     * 描述：添加到执行线程队列.
     *
     * @param item 执行单位
     */
    private synchronized void addTaskItem(TaskItem item) {
        if (abTaskQueue == null) {
            abTaskQueue = new TaskQueue();
        }
        mAbTaskItemList.add(item);
        //添加了执行项就激活本线程
        this.notify();

    }

    /**
     * 描述：线程运行.
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while(!mQuit) {
            try {
                while(mAbTaskItemList.size() > 0){

                    TaskItem item  = mAbTaskItemList.remove(0);
                    //定义了回调
                    if (item.getListener() != null) {
                        if(item.getListener() instanceof TaskListListener){
                            result.put(item.toString(), ((TaskListListener)item.getListener()).getList());
                        }else if(item.getListener() instanceof TaskObjectListener){
                            result.put(item.toString(), ((TaskObjectListener)item.getListener()).getObject());
                        }else{
                            item.getListener().get();
                            result.put(item.toString(), null);
                        }

                        if(item.getListener() instanceof TaskListListener){
                            ((TaskListListener)item.getListener()).update((List<?>) result.get(item.toString()));
                        }else if(item.getListener() instanceof TaskObjectListener){
                            ((TaskObjectListener)item.getListener()).update(result.get(item.toString()));
                        }else{
                            item.getListener().update();
                        }
                        result.remove(item.toString());
                        //交由UI线程处理
//                        Message msg = handler.obtainMessage();
//                        msg.obj = item;
//                        handler.sendMessage(msg);
                    }

                    //停止后清空
                    if(mQuit){
                        mAbTaskItemList.clear();
                        return;
                    }
                }
                try {
                    //没有执行项时等待
                    synchronized(this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    TbLog.e("AbTaskQueue"+"收到线程中断请求");
                    e.printStackTrace();
                    //被中断的是退出就结束，否则继续
                    if (mQuit) {
                        mAbTaskItemList.clear();
                        return;
                    }
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 描述：终止队列释放线程.
     *
     * @param mayInterruptIfRunning the may interrupt if running
     */
    public void cancel(boolean mayInterruptIfRunning){
        mQuit  = true;
        if(mayInterruptIfRunning){
            interrupted();
        }
        abTaskQueue  = null;
    }

}
