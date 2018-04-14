package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉式
 * 在第一次使用时实例化
 * @author Caesar
 *
 */
@NotThreadSafe
public class SingletoExample {

	private SingletoExample() {
	}

	public static SingletoExample instance = null;

	public static synchronized SingletoExample getInstance() {
		if (instance == null) {
			instance = new SingletoExample();
		}
		return instance;
	}
}
