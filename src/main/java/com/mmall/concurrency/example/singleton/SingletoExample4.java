package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉式 在第一次使用时实例化
 * 
 * @author Caesar
 *
 */
@NotThreadSafe
public class SingletoExample4 {

	// 私有构造函数
	private SingletoExample4() {
	}

	// 单例对象
	public static SingletoExample4 instance = null;

	// 1、memory= allocate() 分配对象的内存空间
	// 2、ctorInstance()初始化对象
	// 3、instance=menmory设置instance指向刚分配的内存

	// JVM和CPU优化，发生了指令重排

	// 1、memory= allocate() 分配对象的内存空间
	// 3、instance=menmory设置instance指向刚分配的内存
	// 2、ctorInstance()初始化对象

	// 结果：线程B检测到instance不为空，返回了一个没有初始化的对象
	// 静态工厂方法
	public static SingletoExample4 getInstance() {
		if (instance == null) {// 双重检验机制 			//线程B
			synchronized (SingletoExample4.class) { //同步锁
				if (instance == null) {
					instance = new SingletoExample4();//线程A->3
				}
			}
		}
		return instance;
	}
}
