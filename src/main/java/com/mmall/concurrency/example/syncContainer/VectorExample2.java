package com.mmall.concurrency.example.syncContainer;

import java.util.List;
import java.util.Vector;

import com.mmall.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@NotThreadSafe
public class VectorExample2 {

	public static List<Integer> vector = new Vector<>();

	public static void main(String[] args) throws Exception {

		while (true) {

			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			Thread thread1 = new Thread(() -> {
				for (int i = 0; i < 10; i++) {
					vector.remove(i);
				}
			});

			Thread thread2 = new Thread(() -> {
				for (int i = 0; i < 10; i++) {
					vector.get(i);
				}
			});

			thread1.start();
			thread2.start();
		}
	}

}
