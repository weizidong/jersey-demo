package com.wzd.utils;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步工具类
 * 
 * @author WeiZiDong
 * 
 */
public class ThreadPoolUtils {

	// 创建可缓存的线程池
	private static ExecutorService cachedPool = Executors.newCachedThreadPool();

	// 创建定时以及周期性执行任务的线程池（1个线程）
	private static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);

	/**
	 * 执行异步方法
	 */
	public static void excuteCachedThreadPool(Runnable runnable) {
		if (cachedPool.isShutdown()) {
			cachedPool = Executors.newCachedThreadPool();
		}
		cachedPool.execute(runnable);
	}

	/**
	 * 每隔period秒执行定时以及周期性任务的线程
	 */
	public static void excuteScheduledThreadPool(Runnable runnable, long period) {
		if (scheduledPool.isShutdown()) {
			scheduledPool = Executors.newScheduledThreadPool(1);
		}
		scheduledPool.scheduleAtFixedRate(runnable, 0, period, TimeUnit.SECONDS);
	}

	/**
	 * 定时任务
	 */
	public static void schedule(Runnable runnable, Date date) {
		if (scheduledPool.isShutdown()) {
			scheduledPool = Executors.newScheduledThreadPool(1);
		}
		scheduledPool.schedule(runnable, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

}
