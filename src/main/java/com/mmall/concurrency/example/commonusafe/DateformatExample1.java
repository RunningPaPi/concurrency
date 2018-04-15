package com.mmall.concurrency.example.commonusafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.mmall.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class DateformatExample1 {
	
	private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	// 请求总数
	public static int clientTotal = 5000;

	// 同时并发执行的线程数
	public static int threadTotal = 200;


	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update();
					semaphore.release();
				} catch (Exception e) {
					log.error("exception", e);
				}
				countDownLatch.countDown();
			});
		}

		countDownLatch.await();
		executorService.shutdown();

	}

	private static void update() {
		try {
			sdf.parse("20180415");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
