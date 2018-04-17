package com.mmall.concurrency.example.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.mmall.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class LockExample5 {

	public static void main(String[] args) throws Exception {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		new Thread(()-> {
			try {
				lock.lock();//1
				log.info("wait signal");
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("get signal");//4
			lock.unlock();
		}).start();
		
		new Thread(()->{
			lock.lock();//2
			log.info("get lock");
			try {
				//TimeUnit.MILLISECONDS.sleep(3000);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			condition.signalAll();//3
			log.info("send signal");
			lock.unlock();
		}).start();
	}

}
