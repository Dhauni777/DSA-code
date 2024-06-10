package Hash;

import java.util.ArrayList;
import java.util.LinkedList;

public class hashMapCode {
    static class HashMap<K,V>{
        private class Node{
            K key;
            V value;

            public Node(K key, V value){
                this.key=key;
                this.value=value;
            }
        }
        private int n;  //n
        private int N;
        private LinkedList<Node> buckets[];   //N= bucket.length

        @SuppressWarnings("unchecked")
        public HashMap(){
            this.N=4;
            this.buckets= new LinkedList[4];
            for(int i=0;i<4;i++){
                this.buckets[i]= new LinkedList<>();
            }
        }
        private int hashFunction(K key){
            int hc=key.hashCode();
            return Math.abs(hc) % N;
        }
        public int SearchInLL(K key,int bi){
            LinkedList <Node> ll= buckets[bi];
            int di=0;
            
            for(int i=0; i<ll.size();i++){
                Node node=ll.get(i);
                if(node.key==key){
                    return di;
                }
                di++;
            }
            return -1;
        }
        @SuppressWarnings("unchecked")
        public void rehash(){
            LinkedList<Node> oldBuck[]= buckets;
            buckets= new LinkedList[N*2];
            N=N*2;
            for(int i=0; i<buckets.length;i++){
                LinkedList<Node> ll=oldBuck[i];
                for(int j=0; j<ll.size();j++){
                    Node node=ll.remove();
                    put(node.key,node.value);
                }
            }
        }

        public void put(K key, V value){
            int bi=hashFunction(key);  //0 to 3
            int di= SearchInLL(key,bi);    //valid,-1

            if(di!=-1){
                Node node=buckets[bi].get(di);
                node.value=value;
            }else{
                buckets[bi].add(new Node(key, value));
                n++;
            }
            double lambda=(double)n/N;
            if(lambda>2.0){
                rehash();
            }
        }
        public boolean containsKey(K key){
            int bi=hashFunction(key);  //0 to 3
            int di= SearchInLL(key,bi);    //valid,-1

            if(di!=-1){
                return true;
            }else{
                return false; 
            }
        }
        public V remove(K key){
            int bi=hashFunction(key);  //0 to 3
            int di= SearchInLL(key,bi);    //valid,-1

            if(di!=-1){
                Node node=buckets[bi].remove(di);
                n--;
                return node.value;
            }else{
                return null;
            }
        }

        public V get(K key){
            int bi=hashFunction(key);  //0 to 3
            int di= SearchInLL(key,bi);    //valid,-1

            if(di!=-1){
                Node node=buckets[bi].get(di);
                return node.value;
            }else{
                return null;
            }
        }
        public ArrayList<K> keySet(){
            ArrayList<K> keys= new ArrayList<>();
            for(int i=0;i<buckets.length;i++){
                LinkedList<Node> ll=buckets[i];
                for(Node node:ll){
                    keys.add(node.key);
                }
            }
            return keys;
        }
        public boolean idEmpty(){
            return n==0;
        }
        public int getOrDefault(int sum, int i) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getOrDefault'");
        }

    }
        public static void main(String[] args) {
            HashMap<String,Integer> hm= new HashMap<>();
            hm.put("India",100);
            hm.put("china",50);
            hm.put("US",30);
            hm.put("Russia",40);

            ArrayList<String> keys=hm.keySet();
            for(String key:keys){
                System.err.println(key);
            }
            System.out.print(hm.get("India"));
        }

    }

