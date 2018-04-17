package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchExample {

	private final static int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		/**
		 * 当我们调用一次CountDownLatch的countDown方法时，N就会减1，
		 * CountDownLatch的await会阻塞当前线程，直到N变成零。
		 * 由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，
		 * 也可以是1个线程里的N个执行步骤。
		 * 用在多个线程时，你只需要把这个CountDownLatch的引用传递到线程里。
		 */
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.submit(() -> {
				try {
					test(threadNum);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		
		countDownLatch.await();
		log.info("finished");
		exec.shutdown();
	}

	private static void test(int i) throws Exception {
		Thread.sleep(100);
		log.info("{}", i);
		Thread.sleep(100);
	}
}
