package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample {

	/**
	 * CyclicBarrier还提供一个更高级的
	 * 构造函数CyclicBarrier(int parties, Runnable barrierAction)，
	 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景。
	 */
	private static CyclicBarrier barrier = new CyclicBarrier(5);

	public static void main(String[] args) throws InterruptedException {

		/**
		 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。
		 * 它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
		 * 直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
		 * CyclicBarrier默认的构造方法是CyclicBarrier(int parties)，
		 * 其参数表示屏障拦截的线程数量
		 */
		ExecutorService exec = Executors.newCachedThreadPool();

		/**
		 * 如果把new CyclicBarrier(5)修改成new CyclicBarrier(11)
		 * 或把for循环条件修改成i<11则主线程和子线程会永远等待，
		 * 因为没有第11个线程执行await方法，即没有第11个线程到达屏障，
		 * 所以之前到达屏障的两个线程都不会继续执行
		 */
		for (int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			exec.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		exec.shutdown();
	}

	private static void race(int threadNum) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready", threadNum);
		System.out.println(barrier.getNumberWaiting());
		barrier.await();//每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞
		log.info("{} is continue", threadNum);
	}
}
