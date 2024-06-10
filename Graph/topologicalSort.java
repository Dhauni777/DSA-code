package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import com.sun.java.swing.plaf.windows.resources.windows_zh_HK;

public class topologicalSort {
    static class Edge {
        int src;
        int dest;
        
        Edge(int s, int d){
            this.src=s;
            this.dest=d;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
      
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }
    public static void topSortDfs(ArrayList<Edge>[] graph){
        boolean vis[]= new boolean[graph.length];
        Stack<Integer> s= new Stack<>();

        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
            topSortDfsUtil(graph, i, vis, s);
        }
    }
    while(!s.isEmpty()){
        System.out.print(s.pop()+" "); 
    }
    }
    public static void topSortDfsUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> s){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                topSortDfsUtil(graph, e.dest, vis, s);;
            } 
        }
        s.push(curr);
    }

    public static void indeg(ArrayList<Edge>[] graph, int indeg[]){
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].size();j++){
            Edge e=graph[i].get(j);
            indeg[e.dest]++;
        }
        }
    }   
    public static void topSortBFS(ArrayList<Edge>[] graph){
        int indeg[]= new int[graph.length];
        indeg(graph, indeg);
        Queue<Integer> q= new LinkedList<>();

        for(int i=0;i<graph.length;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int curr=q.remove();
            System.out.print(curr+" ");
            for(int i=0;i<graph[curr].size();i++){
                Edge e=graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
        
    } 
   

    public static void main(String[] args) {
        int V=6;
        ArrayList<Edge> graph[]= new ArrayList[V];
        createGraph(graph);
        // topSortDfs(graph);
        topSortBFS(graph);
    }
}
