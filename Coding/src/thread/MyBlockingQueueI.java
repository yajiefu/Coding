package thread;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/*
 * 题目：使用linkedList、synchronized、wait、notify实现一个阻塞队列.包含put(),take()
 * 
 * 
 * 【使用wait和notify的注意点】
 * 1.wait和notify必须配合synchronized关键字使用
 * 2.wait方法释放锁，而notify不会释放锁
 * 
 * 【BlockingQueue】
 * 阻塞队列，支持阻塞的机制，阻塞地放入和得到数据。我们来自行实现LinkedBlockingQueue下面的两个简单的方法put()和take()。
 * [ put ]
 * 把一个Object加到BlockingQueue里，如果BlockingQueue没有空间，则调用此方法的线程被阻塞，直到BlockingQueue里面有空间再继续。
 * [ take ]
 * 取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的数据被加入。
 */
public class MyBlockingQueueI<E> {
	// 1.需要一个集合封装数据
	private LinkedList<E> list = new LinkedList<>();
	// 2.需要一个计数器，该类具备原子性
	private AtomicInteger count = new AtomicInteger(0);
	// 3.需要指定队列数据的上限以及下限
	private final int minSize = 0;
	private final int maxSize;

	// 4.在构造方法中给定队列数据的上限
	public MyBlockingQueueI(int size) {
		this.maxSize = size;

	}

	// 5.初始化一个对象，用于加锁
	private Object lock = new Object();

	// 6.put(element):把element加到BlockingQueue里，如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续
	public void put(E element) {
		synchronized (lock) {
			// 6.1集合是否满了
			while (count.get() == this.maxSize) {
				try {
					// 6.2集合满了，进入阻塞状态（释放锁）
					lock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 6.3添加元素
			list.add(element);
			// 6.4计数器累加
			count.incrementAndGet();
			// 6.5通知另一个线程可以取数据了（唤醒功能）
			lock.notify();
			System.out.println("新加入的元素为：" + element);
		}
	}

	// 7.take():
	// 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入
	public Object take() {
		Object ret = null;
		synchronized (lock) {
			// 7.1集合中没有元素了
			while (count.get() == minSize) {
				try {
					// 7.2进入阻塞状态
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// 7.3移除元素
			ret = list.removeFirst();
			// 7.4计数器递减
			count.decrementAndGet();
			// 7.5通知另一个线程可以放数据了
			lock.notify();

		}
		return ret;
	}

	// 8.得到队列的长度
	public int getSize() {
		return this.count.get();
	}

	public static void main(String[] args) {
		MyBlockingQueueI<Integer> blockingQueue = new MyBlockingQueueI<Integer>(5);
		blockingQueue.put(1);
		blockingQueue.put(2);
		blockingQueue.put(3);
		blockingQueue.put(4);
		blockingQueue.put(5);
		System.out.println("当前队列的长度：" + blockingQueue.getSize());

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				blockingQueue.put(6);
				blockingQueue.put(7);
			}
		}, "t1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				Object o1 = blockingQueue.take();
				System.out.println("移除的元素是：" + o1);
				Object o2 = blockingQueue.take();
				System.out.println("移除的元素是：" + o2);
			}
		}, "t2");
		try {
			TimeUnit.SECONDS.sleep(2);// 睡眠2s才启动线程2
		} catch (Exception e) {
			e.printStackTrace();
		}
		t2.start();

	}

}
