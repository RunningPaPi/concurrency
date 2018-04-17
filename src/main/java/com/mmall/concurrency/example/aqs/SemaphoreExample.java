package com.mmall.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample {

	private final static int threadCount = 20;

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			Thread.sleep(1);
			exec.submit(() -> {
				try {
					semaphore.acquire();
					test(threadNum);
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		log.info("finished");
		exec.shutdown();
	}

	private static void test(int i) throws Exception {
		log.info("{}", i);
		Thread.sleep(1000);
	}
}
