package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 懒汉式
 * 在第一次使用时实例化
 * @author Caesar
 *
 */
@ThreadSafe
@NotRecommend
public class SingletoExample3 {

	//私有构造函数
	private SingletoExample3() {
	}

	//单例对象
	public static SingletoExample3 instance = null;

	//静态工厂方法
	public static SingletoExample3 getInstance() {
		if (instance == null) {
			instance = new SingletoExample3();
		}
		return instance;
	}
}
