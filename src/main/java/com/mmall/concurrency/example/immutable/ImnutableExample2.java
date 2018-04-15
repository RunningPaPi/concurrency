package com.mmall.concurrency.example.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public class ImnutableExample2 {
	private static final Integer a = 1;
	private final static String b = "2";
	private static Map<Integer, Integer> map = Maps.newHashMap();

	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
		map = Collections.unmodifiableMap(map);
	}
	
	public static void main(String[] args) {
		// Exception in thread "main" java.lang.UnsupportedOperationException
		// at java.util.Collections$UnmodifiableMap.put(Collections.java:1457)
		// at
		// com.mmall.concurrency.example.immutable.ImnutableExample1.main(ImnutableExample1.java:23)

		map.put(1, 3);
	}
}
