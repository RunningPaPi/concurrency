package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ImnutableExample1 {
	
	private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3);
	
	private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);
	
	private final static ImmutableMap<Integer,Integer> map=ImmutableMap.<Integer,Integer>builder()
			.put(1,2).put(2,5).put(3,6).put(4,7).build();
	
	public static void main(String[] args) {
		//list.add(12);
		//set.add(12);
		System.out.println(map.get(3));
	}
}
