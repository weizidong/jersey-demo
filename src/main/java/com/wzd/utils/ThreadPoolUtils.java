package com.wzd.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步工具类
 * 
 * @author lyb
 * 
 */
public class ThreadPoolUtils {

	// 创建可缓存的线程池
	private static ExecutorService cachedPool = Executors.newCachedThreadPool();
	
	// 创建定时以及周期性执行任务的线程池（1个线程）
	private static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);

	/**
	 * 执行异步方法
	 * 
	 * @param runnable
	 */
	public static void excuteCachedThreadPool(Runnable runnable) {
		if(cachedPool.isShutdown()){
			cachedPool = Executors.newCachedThreadPool();
		}
		cachedPool.execute(runnable);
	}
	
	/**
	 * 每隔period秒执行定时以及周期性任务的线程
	 * @param runnable
	 * @param period
	 */
	public static void excuteScheduledThreadPool(Runnable runnable, long period) {
		if(scheduledPool.isShutdown()){
			scheduledPool = Executors.newScheduledThreadPool(1);
		}
		scheduledPool.scheduleAtFixedRate(runnable, 0, period, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {

		ThreadPoolUtils.excuteCachedThreadPool(new Runnable() {

			public void run() {
//				System.out.println("1111");
			}

		});
		ThreadPoolUtils.excuteCachedThreadPool(new Runnable() {

			public void run() {
//				System.out.println("1111");
			}

		});
	}

}
