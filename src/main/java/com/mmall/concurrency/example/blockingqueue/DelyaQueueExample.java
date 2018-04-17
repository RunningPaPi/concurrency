package com.mmall.concurrency.example.blockingqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelyaQueueExample implements Delayed {
	private long expire;
	private long delay;
	private String name;

	public long getExpire() {
		return expire;
	}

	public DelyaQueueExample(long delay, String name) {
		super();
		this.expire = delay + System.currentTimeMillis();
		this.delay = delay;
		this.name = name;
	}

	@Override
	public int compareTo(Delayed o) {
		DelyaQueueExample cached = (DelyaQueueExample) o;
		return cached.getExpire() > expire ? 1 : -1;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return (expire - System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "DelyaQueueExample [delay=" + delay + ", name=" + name + "]";
	}

	static final int STUDENT_SIZE = 30;

	public static void main(String[] args) throws InterruptedException {
		// DelayQueue<DelyaQueueExample> queue = new DelayQueue<>();
		// DelyaQueueExample ele1 = new DelyaQueueExample(2000, "cache 2 seconds");
		// DelyaQueueExample ele = new DelyaQueueExample(4000, "cache 3 seconds");
		// queue.put(ele1);
		// queue.put(ele);
		// while (!queue.isEmpty()) {
		// System.out.println(queue.take());
		// }
		// System.out.println(queue.take());

		Random r = new Random();
		// 把所有学生看做一个延迟队列
		DelayQueue<Student> students = new DelayQueue<Student>();
		// 构造一个线程池用来让学生们“做作业”
		ExecutorService exec = Executors.newFixedThreadPool(STUDENT_SIZE);
		for (int i = 0; i < STUDENT_SIZE; i++) {
			// 初始化学生的姓名和做题时间
			students.put(new Student("学生" + (i + 1), 3000 + r.nextInt(10000)));
		}
		// 开始做题
		while (!students.isEmpty()) {
			exec.execute(students.take());
		}
		exec.shutdown();
	}

}

// 把所有考试的学生看做是一个DelayQueue，谁先做完题目释放谁
class Student implements Runnable, Delayed {
	private String name; // 姓名
	private long costTime;// 做试题的时间
	private long finishedTime;// 完成时间

	public Student(String name, long costTime) {
		this.name = name;
		this.costTime = costTime;
		finishedTime = costTime + System.currentTimeMillis();
	}

	@Override
	public void run() {
		System.out.println(name + " 交卷,用时" + costTime / 1000 + "秒");
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return (finishedTime - System.currentTimeMillis());
	}

	@Override
	public int compareTo(Delayed o) {
		Student other = (Student) o;
		return costTime >= other.costTime ? 1 : -1;
	}

}
