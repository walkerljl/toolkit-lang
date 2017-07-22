package org.walkerljl.toolkit.lang.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author lijunlin
 */
public class ThreadPool {

    /** 线程池所使用的缓冲队列大小*/
    private static final int      WORK_QUEUE_SIZE      = 2000;
    /**  线程池维护线程所允许的空闲时间*/
    private static final int      KEEP_ALIVE_TIME      = 60;
    /** 线程池维护线程所允许的空闲时间的单位*/
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    /** 线程池维护线程的最少数量,核心线程数量*/
    private static final int      CORE_POOL_SIZE       = 100;
    /** 线程池维护线程的最大数量*/
    private static final int      MAX_POOL_SIZE        = 500;

    /** 线程池对象*/
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE),
            new NamedThreadFactory("orgwalkerljl"));

    /**
     * 私有构造函数
     */
    private ThreadPool() {}

    /**
     * 执行任务
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        THREAD_POOL.execute(runnable);
    }

    /**
     * 提交任务执行
     * @param callable
     * @return
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return THREAD_POOL.submit(callable);
    }

    /**
     * 使用当前线程池包装一个CompletionService
     * @return
     */
    public static <T> CompletionService<T> wrapCompletionService() {
        return new ExecutorCompletionService<T>(THREAD_POOL);
    }

    /**
     * 启动一个新线程来执行任务
     * @param runnable
     */
    public static void newThreadExecute(Runnable runnable) {
        new Thread(runnable).start();
    }
}