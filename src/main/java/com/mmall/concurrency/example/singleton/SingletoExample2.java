package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉式
 * 类被加载时实例化
 * @author Caesar
 *
 */

@ThreadSafe
public class SingletoExample2 {

	private SingletoExample2() {
	}

	private static final SingletoExample2 instance = new SingletoExample2();

	public static SingletoExample2 getInstance() {
		return instance;
	}
}
