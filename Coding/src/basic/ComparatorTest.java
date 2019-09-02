package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * 参考：https://www.cnblogs.com/maozp/p/11153403.html
 */
public class ComparatorTest {
	public static void main(String[] args) {
		//测试实例1：对list进行降序排序
		System.out.println("测试实例1：对list进行降序排序：");
		//数组转list
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,7,4,5,9,6,3));
		System.out.print("排序前:");
		System.out.println(list);//[1, 2, 7, 4, 5, 9, 6, 3]
		System.out.print("排序后:");
		sortDesc(list);//降序排序
//		Collections.sort(list);//默认升序
		System.out.println(list);//[9, 7, 6, 5, 4, 3, 2, 1]
		
		//实例2：对二维数组进行排序：定义规则(x,y):从小到大排序，如果x相同，y从小到大排序
		int[][] arr = {{2,6},{3,4},{2,3},{4,5}};
		System.out.println("排序前：");
		for(int i = 0; i <arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		/*
		 * 排序前：
		 * [2, 6]
		 * [3, 4]
		 * [2, 3]
		 * [4, 5]
		 */
		arrAsc(arr);
		System.out.println("排序后：");
		for(int i = 0; i <arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		/*
		 * 排序后：
		 * [2, 3]
		 * [2, 6]
		 * [3, 4]
		 * [4, 5]
		 */
		
		
		//实例3：对一个一维数组进行排序，排序规则是：偶数优先奇数，如果都是偶数或者奇数，值大的优先；取前k个数（优先队列）
		int[] nums = {5,2,1,3,4,7,0};
		System.out.println("原数组："+Arrays.toString(nums));
		int k = 6;
		ArrayList<Integer> res = kNum(nums,k);
		//从小顶堆堆顶输出的顺序是从小到大。所以最终结果我们要逆序输出
		System.out.println("前k个优先元素：");
		for(int i = res.size() - 1; i >= 0; i--) {
			System.out.print(res.get(i)+" ");
		}
		
	}
	
	
	//实例1：对list进行降序排序
	public static void sortDesc(ArrayList<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			//源码中第一个入参（o1）是数组靠后面的数，第二个入参（o2）是数组靠前面的数（比如这里：o1=2，b=1）
			@Override
			public int compare(Integer o1, Integer o2) {
				
				if (o1 <= o2) {
//					System.out.println(o1+" "+o2+" 1");
					return 1;//后面的数小于等于前面的数，返回1表示顺序不调整
				}else {
//					System.out.println(o1+" "+o2+" -1");
					return -1;//后面的数大于前面的数，返回-1表示需要调整，逆序，这样就可以判断出这个排序是降序排序。
				}
				//如果改成升序，只要改变1和-1的位置即可。或者直接用默认的return o1.compareTo(o2);//稍后解释
				
			}
		});
	}
	/**
	 * 小结：1.重写Comparator中的compare()方法，自定义的比较规则：
	 *     1.1注意点：
	 *        (1)重写的compare(o1,o2)，第一个入参o1表示集合元素中相邻元素靠后的一个。第二个入参o2表示靠前的一个。
	 *        (2)不需要调整就返回1，需要调整就返回-1；
	 *     1.2判断条件：
	 *        (1)升序：期望的是后一个元素比前一个元素大。如果已经满足就不需要调整顺序（即，o1 >= o2,返回1），如果不满足，就调换一下顺序（即，o1 < o2,返回-1）
	 *        (2)降序：期望的是后一个元素比前一个元素小。如果已经满足就不需要调整顺序（即，o1 <= o2,返回1），如果不满足，就调换一下顺序（即，o1 > o2,返回-1）。
	 * 
	 *     1.3举例：元素集合[1, 2, 7, 4, 5, 9, 6, 3]
	 *        当比较顺序时，o1=2，o2=1，if(o1>o2)返回的是-1，表示我期望的是降序，而实际情况是升序，所以就要调整。
	 *        至于后面的数还要不要做调整就要继续判断。
	 *        注意：可以将上方System.out.println(o1+" "+o2+" 1");取消注释，可以看出后面的元素是用二分法插入到已排好序的元素中的。
	 *     
	 *     2.原理：先将集合中的部分元素排好顺序，然后将剩下的元素用二分法插入到已排好序(二分法的使用是建立在已排好序的前提下)的元素中。最终得到排好序的集合。
	 *     3.return o1.compareTo(o2);返回值为负数意味着o1比o2小，否则返回为零意味着o1等于o2，返回为正数意味着o1大于o2
	 *        分析：o1<o2返回-1，表示后面的数比前面的数小就调整，所以是升序。
	 *        等价于：
	 *        if (o1 < o2) {
	 *        		return -1;
	 *        }else if (o1 > o2) {
	 *              return 1;
	 *        }else {
	 *          	return 0;
	 *        }
	 */
	
	
	//实例2：对二维数组进行排序：定义规则(x,y):从小到大排序，如果x相同，y从小到大排序
	public static void arrAsc(int[][] arr) {
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0]) {
					//从小到大排序:o1[0]比o2[0]大的话就不需要调整，即返回正数1(也可以是其他正数)
					return o1[0]-o2[0];
				}else {//o1[0] == o2[0],就比较o1[1]和o2[1],从小到大
					return o1[1]-o2[1];
					
				}
			}
		});
	}
	
	//实例3：对一个一维数组进行排序，排序规则是：偶数优先奇数，如果都是偶数或者奇数，值大的优先；取前k个数
	public static ArrayList<Integer> kNum(int[] arr, int k){
		//思路：自定义排序+小顶堆
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(k,new Comparator<Integer>() {
			//注意这边的写法：很容易绕进去。关键点：了解小根堆的入队操作
			@Override
			public int compare(Integer o1, Integer o2) {
				if ((o1 & 1) == 0) {
					if ((o2 & 1) == 0) {//都是偶数，因为是小顶堆，如果o1小于o2,就往上调整，返回-1；
						return o1.compareTo(o2);
					}else {//前面的数o2是奇数，后面的数o1是偶数，不调整，返回1
						return 1;
					}
				}else {
					if ((o2 & 1) == 0) {//前面的数o2是偶数，后面的数o1是奇数，调整,返回-1
						return -1;
					}else {//都是奇数，因为是小顶堆，如果o1小于o2,就往上调整，返回-1；
						return o1.compareTo(o2);
					}
				}
			}
		});
		
		for(int i = 0; i < arr.length; i++) {
			int val = arr[i];
			if (minHeap.size() < k) {
				minHeap.offer(val);
			}else {
				int min = minHeap.peek();
				//什么情况要删除堆顶，插入新值：min是奇数：val奇数且比min大，或者val是偶数；min是偶数，val是偶数且比min大
				if (((min & 1) == 1 && (((val & 1) == 1 && val > min) || (val & 1) == 0) ) || (((min & 1) == 0 && (val & 1) == 0 && val > min))) {
					minHeap.remove();
					minHeap.offer(val);
				}
			}
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		//小顶堆：上面的最小，下面的最大。如果将一个数组从小到大排序，构建大顶堆，请看堆排序的过程
		while(!minHeap.isEmpty()) {
			res.add(minHeap.poll());//从堆顶输出的顺序是从小到大。所以最终结果我们要逆序输出
		}
		return res;
		
	}
	

}
