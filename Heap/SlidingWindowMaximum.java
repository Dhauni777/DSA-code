package Heap;
 
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
 
public class SlidingWindowMaximum {
 
    static class Pair implements Comparable<Pair>{
        int data;
        int idx;
    
        Pair(int data, int idx)
        {
            this.data = data;
            this.idx = idx;
        }
 
        @Override
        public int compareTo(Pair n2) {
            return n2.data - this.data;
        } 
    }
 
    public static List<Integer> maxSlidingWindow(int[] arr,
                                                int k)
    {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair> heap = new PriorityQueue<>();
 
        // Initialize the heap with the first k elements
        for (int i = 0; i < k; i++) {
            heap.add(new Pair(arr[i], i));
        }
 
        // The maximum element in the first window
        ans.add(heap.peek().data);
 
        // Process the remaining elements
        for (int i = k; i < arr.length; i++) {
 
            // Remove elements that are outside the current
            // window
            while (heap.peek().idx <= i - k) {
                heap.poll();
            }
 
            heap.add(new Pair(arr[i], i));
 
            // The maximum element in the current window
            ans.add(heap.peek().data);
        }
 
        return ans;
    }
 
    public static void main(String[] args)
    {
        int[] arr = { 2, 3, 7, 9, 5, 1, 6, 4, 3 };
        int k = 3;
 
        // Find the maximum element in each sliding window
        // of size k
        List<Integer> result = maxSlidingWindow(arr, k);
 
        // Print the results
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}