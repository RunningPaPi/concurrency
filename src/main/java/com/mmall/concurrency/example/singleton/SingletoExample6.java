package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉式 类被加载时实例化
 * 
 * @author Caesar
 *
 */

@ThreadSafe
public class SingletoExample6 {

	private SingletoExample6() {
	}

	private static SingletoExample6 instance = null;

	static {
		instance = new SingletoExample6();
	}

	public static SingletoExample6 getInstance() {
		return instance;
	}
}
