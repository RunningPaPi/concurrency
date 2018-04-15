package com.mmall.concurrency.example.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public class ImnutableExample3 {
	private static final Integer a = 1;
	private final static String b = "2";
	private final static Map<Integer, Integer> map = Maps.newHashMap();

	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
	}
	
	public static void main(String[] args) {
		//map = new HashMap<>();
	}
}
