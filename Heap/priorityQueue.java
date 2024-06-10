package Heap;
import java.util.PriorityQueue;

public class priorityQueue {
    static class Student implements Comparable<Student>{
        String name;
        int rank;

        public Student(String name, int rank){
            this.name=name;
            this.rank=rank;
        }
        @Override
        public int compareTo(Student s2){
            return this.rank- s2.rank;
        }
        
    }
    public static void main(String[] args) {
        PriorityQueue<Student> pq= new PriorityQueue<>();     //for reverse PriorityQueue<>(Comparator.reverseOrder())
            pq.add(new Student("A", 2));      //O(logn)
            pq.add(new Student("B", 4));
            pq.add(new Student("C", 6));  
            pq.add(new Student("D", 1));

            while(!pq.isEmpty()){
                System.out.println(pq.peek().name+ "--> "+pq.peek().rank);  //O(1)
                pq.remove();      //O(logn)
            }
     }
}

    // public static void main(String[] args) {
    //     PriorityQueue<Integer> pq= new PriorityQueue<>();     //for reverse PriorityQueue<>(Comparator.reverseOrder())
    //         pq.add(4);      //O(logn)
    //         pq.add(3);
    //         pq.add(6);
    //         pq.add(1);

    //         while(!pq.isEmpty()){
    //             System.out.println(pq.peek());  //O(1)
    //             pq.remove();      //O(logn)
    //         }
    //     }