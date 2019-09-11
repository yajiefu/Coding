package thread;
/*
 * 题目：使用Lock Condition实现一个阻塞队列
 */

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueueII<E> {
	// 1.需要一个集合封装数据
	private LinkedList<E> list = new LinkedList<>();
	// 2.需要指定队列的大小
	private final int capacity;
	// 3.需要
	private final Lock lock = new ReentrantLock();
	// 4.非空
	private final Condition notEmpty = lock.newCondition();
	// 5.非满
	private final Condition notFull = lock.newCondition();

	// 6.构造函数指定队列的大小
	public MyBlockingQueueII(int capacity) {
		this.capacity = capacity;
	}

	// 6.put(element)
	public void put(E element) {
		try {
			lock.lock();
			// 6.1判断队列是否满了
			while (list.size() >= capacity) {
				// 6.2当队列满了，阻塞生产线程
				notFull.await();
			}
			// 6.3放入元素
			list.add(element);
			// 6.4 不为空，唤醒消费线程
			notEmpty.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
	}

	// 7.take()
	public E take() {
		try {
			lock.lock();
			// 7.1判断队列是否为空
			while (list.size() == 0) {
				// 7.2当队列为空，阻塞消费线程
				notEmpty.await();
			}
			// 7.3移除元素
			E element = list.removeFirst();
			// 7.4队列不是满的，唤醒生产线程
			notFull.notifyAll();
			// 7.5返回该元素
			return element;
		} catch (Exception e) {
			return null;
		} finally {
			lock.unlock();
		}
	}

}

