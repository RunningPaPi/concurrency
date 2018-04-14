package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式，最安全的
 * 
 * @author Caesar
 *
 */

@ThreadSafe
@Recommend
public class SingletoExample7 {

	private SingletoExample7() {
	}

	public static SingletoExample7 getInstance() {
		return Singleton.INSTANCE.getInstance();
	}
	
	private enum Singleton{
		INSTANCE;
		
		private SingletoExample7 instance;
		
		Singleton(){
			instance = new SingletoExample7();
		}
		
		public SingletoExample7 getInstance() {
			return instance;
		}
	}
}
