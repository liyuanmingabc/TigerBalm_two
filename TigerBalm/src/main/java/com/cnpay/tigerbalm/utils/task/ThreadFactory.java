package com.cnpay.tigerbalm.utils.task;


import android.os.Process;

import com.cnpay.tigerbalm.utils.AppUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 名称：ThreadFactory.java
 * 描述：线程工厂.
 *
 * 包            名:      com.cnpay.tigerbalm.utils.task
 * 类            名:      ThreadFactory
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 */
public class ThreadFactory {
    /** 任务执行器. */
    public static Executor mExecutorService = null;

    /** 保存线程数量 . */
    private static final int CORE_POOL_SIZE = 5;

    /** 最大线程数量 . */
    private static final int MAXIMUM_POOL_SIZE = 64;

    /** 活动线程数量 . */
    private static final int KEEP_ALIVE = 5;

    /** 线程工厂 . */
    private static final java.util.concurrent.ThreadFactory mThreadFactory = new java.util.concurrent.ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AbThread #" + mCount.getAndIncrement());
        }
    };

    /** 队列. */
    private static final BlockingQueue<Runnable> mPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(10);

    /**
     * 获取执行器.
     *
     * @return the executor service
     */
    public static Executor getExecutorService() {
        if (mExecutorService == null) {
            int numCores = AppUtil.getNumCores();
            mExecutorService
                    = new ThreadPoolExecutor(numCores * CORE_POOL_SIZE,numCores * MAXIMUM_POOL_SIZE,numCores * KEEP_ALIVE,
                    TimeUnit.SECONDS, mPoolWorkQueue, mThreadFactory);
        }
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        return mExecutorService;
    }
}
