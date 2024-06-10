package Heap;

import java.util.ArrayList;

public class heap {
    static class Heap{
        ArrayList<Integer> arr= new ArrayList<>();
        public void add(int data){
        //add to last idx
        arr.add(data);

        int x= arr.size()-1;  //x is child idx
        int par=(x-1)/2;    //par idx
            while(arr.get(x)<arr.get(par)){  //O(logn)
                //swap
                int temp=arr.get(par);
                arr.set(par, arr.get(x));
                arr.set(x, temp);

                x=par;
                par=(x-1)/2;
            }
        }
        public int peek(){
            return arr.get(0);
        }
        private void heapify(int i){
            int left=2*i+1;
            int right= 2*i+2;
            int minIDX=i;

            if(left<arr.size() && arr.get(minIDX) > arr.get(left)){
                minIDX=left;
            }
            if(right<arr.size() && arr.get(minIDX) > arr.get(right)){
                minIDX=right;
            }
            if(minIDX!=i){
                //swap
                int temp= arr.get(i);
                arr.set(i, arr.get(minIDX));
                arr.set(minIDX, temp);
                heapify(minIDX);
            } 
        } 

        public int remove(){
            int data= arr.get(0);

            //step1- swap first and last
            int temp=arr.get(0);
            arr.set(0, arr.get(arr.size()-1));
            arr.set(arr.size()-1, temp);

            //step2
            arr.remove(arr.size()-1);

            //step3
            heapify(0);
            return data;

        }
        public boolean isEmpty(){
            return arr.size()==0;
        }
    }

     //heapSort
    public static void heapify(int arr[],int i, int size){
            int left=2*i+1;
            int right=2*i+2;
            int maxIDX=i;

            if(left<size && arr[left] > arr[maxIDX]){
                maxIDX=left;
            }
            if(right<size && arr[right] > arr[maxIDX]){
                maxIDX=right;
            }
            if(maxIDX!=i){
                //swap
                int temp= arr[i];
                arr[i]=arr[maxIDX];
                arr[maxIDX]=temp;
                heapify(arr, maxIDX, size);
            } 
    }
    //heapSort
    public static void heapSort(int arr[]){
        //step1- build maxHeap
        int n=arr.length;
        for(int i=n/2;i>=0;i--){
            heapify(arr,i,n);
        }
        //step2- push largest to the end
        for(int i=n-1;i>0;i--){
            //swap largest with first
            int temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;

            heapify(arr, 0,i);
        }
    }

    public static void main(String[] args) {
        // Heap pq =new Heap();
        // pq.add(3);
        // pq.add(4);
        // pq.add(1);
        // pq.add(5);

        // while(!pq.isEmpty()){
        //     System.out.println(pq.peek());
        //     pq.remove();
        // }
        int arr[]={1,2,4,5,3};
        heapSort(arr);

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
        
    }
    
}
