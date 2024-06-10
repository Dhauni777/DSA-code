package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class cheapestFlight {
    static class Edge {
        int src;
        int dest;
        int wt;
        
        public Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
    }
    public static void crateGraph(int flights[][],ArrayList<Edge> graph[] ){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<flights.length;i++){
            int src=flights[i][0];
            int dest= flights[i][1];
            int wt= flights[i][2];
            
            Edge e= new Edge(src, dest, wt);
            graph[src].add(e);
        }
    }
    static class Info{
            int v;
            int cost;
            int stops;

        public Info(int v, int cost, int s){
            this.v=v;
            this.cost=cost;
            this.stops=s;
        }
    }

    public static int  cheapestFlights(int n,  int flights[][],int src,int dest,int k){
        ArrayList<Edge> graph[]= new ArrayList[n];
        crateGraph(flights, graph);
        int dist[]= new int[n];
        for(int i=0;i<n;i++){
            if(i!=src){
                dist[i]= Integer.MAX_VALUE;
            }
        }
        Queue<Info> q= new LinkedList<>();
        q.add(new Info(0, 0, 0));

        while (!q.isEmpty()) {
            Info curr= q.remove();
            if(curr.stops>k){
                break;
            }
            for(int i=0;i<graph[curr.v].size();i++){ 
               Edge e= graph[curr.v].get(i);
               int u=e.src;
               int v=e.dest;
               int wt= e.wt;
               if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v] && curr.stops<=k){
                dist[v]= dist[u]+wt;
                q.add(new Info(v, dist[v], curr.stops+1));
               }
            }
        }
        if(dist[dest]==Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[dest];
        }
    }


    public static void main(String[] args) {
        int n=4; 
        int flights[][]={{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src=0, dest=3,k=1;
        int cheapestflight=cheapestFlights(n, flights, src, dest, k);
        System.out.println(cheapestflight);
       
    }
}
