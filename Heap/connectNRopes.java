package Heap;

import java.util.PriorityQueue;

public class connectNRopes {
    public static void main(String[] args) {
        int ropes[]={2,4,3,6,3};

        PriorityQueue<Integer> pq= new PriorityQueue<>();
        for(int i=0;i<ropes.length;i++){
            pq.add(ropes[i]);
        }
        int cost=0;
        while(pq.size()>1){
            int min= pq.remove();
            int min2=pq.remove();
            cost+=min+min2;
            pq.add(min+min2);
        }
        System.out.println("cost of connecting the ropes="+cost);
    }

}
