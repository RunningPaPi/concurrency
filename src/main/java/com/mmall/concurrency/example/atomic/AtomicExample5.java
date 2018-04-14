package com.mmall.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.mmall.concurrency.annotations.NotThreadSafe;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class AtomicExample5 {
	
	private static AtomicIntegerFieldUpdater<AtomicExample5> updater=
			AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
	
	@Getter
	private volatile int count = 100;
	
	
	public static void main(String[] args) {
		AtomicExample5 exp5 = new AtomicExample5();
		if(updater.compareAndSet(exp5, 100, 200)) {
			log.info("update success1: {}",exp5.getCount());
		}
		
		
		if(updater.compareAndSet(exp5, 100, 200)) {
			log.info("update success2: {}",exp5.getCount());
		}else {
			log.info("update failed: {}",exp5.getCount());
		}
	}
}
