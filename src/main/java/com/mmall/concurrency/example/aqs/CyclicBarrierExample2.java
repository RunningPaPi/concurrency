package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyclicBarrierExample2 {
	private static long sum = 0;
	
	private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
		System.out.println(sum);
	}) ;

	
	public static void add(int i) {
		sum +=i;
	}
	public static void main(String[] args) throws InterruptedException {

		ExecutorService exec = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int threadNum = i;
			//Thread.sleep(1000);
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
		add(threadNum);
		log.info("{} is ready", threadNum);
		barrier.await();
		log.info("{} is continue", threadNum);
	}
}
