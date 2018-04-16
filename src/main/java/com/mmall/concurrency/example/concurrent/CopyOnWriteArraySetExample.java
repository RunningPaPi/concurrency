package com.mmall.concurrency.example.concurrent;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行的线程数
	public static int threadTotal = 200;

	public static Set<Integer> list = new CopyOnWriteArraySet<>();

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}

		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}", list.size());

	}

	private static void update(int i) {
		list.add(i);
	}
}
