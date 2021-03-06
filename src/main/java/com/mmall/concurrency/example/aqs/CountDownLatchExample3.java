package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchExample3 {

	private final static int threadCount = 200;

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		final CountDownLatch countDownLatch = new CountDownLatch(190);
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			Thread.sleep(1);
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
	}
}
