package com.mmall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.mmall.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

@NotThreadSafe
public class VectorExample3 {

	// java.util.ConcurrentModificationException
	public static void test1(Vector<Integer> v) {
		for (Integer i : v) {
			if (i.equals(3)) {
				v.remove(i);
			}
		}
	}

	public static void test2(Vector<Integer> v) {
		Iterator<Integer> iterator = v.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
				v.remove(i);
			}
		}
	}

	// java.util.ConcurrentModificationException
	public static void test3(Vector<Integer> v) {
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).equals(3)) {
				v.remove(i);
			}
		}
	}

	// success
	public static void main(String[] args) throws Exception {
		Vector<Integer> vector = new Vector<>();

		vector.add(1);
		vector.add(2);
		vector.add(3);

		test2(vector);

	}

}
