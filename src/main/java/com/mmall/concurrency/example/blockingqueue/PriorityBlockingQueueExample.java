package com.mmall.concurrency.example.blockingqueue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample implements Comparable<PriorityBlockingQueueExample> {
	private int priority;

	public PriorityBlockingQueueExample(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(PriorityBlockingQueueExample o) {
		// TODO Auto-generated method stub
		return priority >= o.getPriority() ? 1 : -1;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "PriorityBlockingQueueExample [priority=" + priority + "]";
	}

	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<PriorityBlockingQueueExample> queue = new PriorityBlockingQueue<>();
		for (int i = 0; i < 5; i++) {
			Random random=new Random();
			PriorityBlockingQueueExample ele = new PriorityBlockingQueueExample(random.nextInt(10));
	        queue.put(ele);
		}
		while(!queue.isEmpty()){
	        System.out.println(queue.take());
	    }
	}
}
