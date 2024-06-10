package Graph;

import java.util.*;
class Graph {
    // BFS 
    public int orangesRotting(int[][] grid) {
        int freshOranges = 0;
        Queue<Integer> que = new LinkedList<>();

        int n = grid.length;
        int m = grid[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    freshOranges++;
                } else if(grid[i][j] == 2){
                    que.add(i*m+j);
                }
            }
        }

        if(freshOranges == 0) return 0;

        int level = 0;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        while(que.size()>0){
            int size = que.size();

            while(size > 0){
                int rottenOrangeCell = que.remove();
                int i = rottenOrangeCell / m;
                int j = rottenOrangeCell % m;

                for(int[] dir: dirs){
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];

                    if(newRow >=0 && newCol>=0 && newRow<n && newCol<m && grid[newRow][newCol] == 1){
                        grid[newRow][newCol] = 2;
                        freshOranges--;
                        que.add(newRow*m + newCol);
                    }
                }
                size--;
            }
            level++;
        }

        if(freshOranges == 0){
            return level - 1;
        } 
        return -1;
    }

    public static void BFS(int src, ArrayList<Integer>[] graph){
        int N = graph.length;
        boolean[] vis = new boolean[N];
        Queue<Integer> que= new LinkedList<>();

        que.add(src);
        vis[src] = true;

        while(que.size()>0){
            int u = que.remove();

            System.out.println(u);
            ArrayList<Integer> nbrs = graph[u];
            for(int i=0; i<nbrs.size(); i++){
                int nbr = nbrs.get(i);
                if(vis[nbr] == false){
                    vis[nbr] = true;
                    que.add(nbr);
                }
            }   
        }
    }

    public static void BFS_cycle(int src, ArrayList<Integer>[] graph){
        int N = graph.length;
        boolean[] vis = new boolean[N];
        Queue<Integer> que= new LinkedList<>();

        que.add(src);

        while(que.size()>0){
            int u = que.remove();
            if(vis[u] == true){
                System.out.println("We have a cycle at " + u);
            }
            vis[u] = true; // marking visited at removal time

            // System.out.println(u);
            ArrayList<Integer> nbrs = graph[u];
            for(int i=0; i<nbrs.size(); i++){
                int nbr = nbrs.get(i);
                if(vis[nbr] == false){
                    que.add(nbr);
                }
            }   
        }
    }

    public static void BFS_level(int src, ArrayList<Integer>[] graph){
        int N = graph.length;
        boolean[] vis = new boolean[N];
        Queue<Integer> que= new LinkedList<>();

        que.add(src);
        vis[src] = true;
        int level = 0;

        while(que.size()>0){
            int size = que.size();
            System.out.println("Level is "+level+" --->");
            while(size > 0){
                int u = que.remove();
                ArrayList<Integer> nbrs = graph[u];
                for(int i=0; i<nbrs.size(); i++){
                    int nbr = nbrs.get(i);
                    if(vis[nbr] == false){
                        vis[nbr] = true;
                        System.out.print(nbr+", ");
                        que.add(nbr);
                    }
                } 
                size--;  
            }
            level++;
            System.out.println();     
        }
    }




    // construction ===============
    public static void addEdge(ArrayList<Integer>[] graph,int u, int v){
        graph[u].add(v);
        graph[v].add(u);
    }

    public static void main(String[] args){
        int N = 7;
        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1);
        addEdge(graph,0,3);
        addEdge(graph,1,2);
        addEdge(graph,1,4);
        addEdge(graph,5,4);
        addEdge(graph,4,6);
        addEdge(graph,5,6);
        addEdge(graph,3,2);

        int src = 0;
        // BFS_level(src,graph);
        BFS_cycle(src, graph);
        // BFS(src, graph);
    }
}