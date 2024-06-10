package BST;
import java.util.*;
public class BST {
    static class Node{
        int data;
        Node left, right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    static Node insert(Node root, int val){
        if(root==null){
            return new Node(val);
        }
        if(root.data>val){
            //left subtree
            root.left=insert(root.left, val);
        }else{
            root.right=insert(root.right, val);
        }
        return root;
    } 
    static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);

    }

//Search
    public static boolean search(Node root, int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(root.data>key){
            return search(root.left, key); 
        }else{
            return search(root.right, key);
        }
    }


//delete a node
    //case1 : No child(leaf node)
    //case2 : One child
    //case3 : two children
    public static Node delete(Node root, int val){
        if(root==null){
            return null;
        }

        if(root.data>val){
            root.left=delete(root.left, val);
        }else if(root.data<val){
            root.right=delete(root.right, val);
        }else{
            //case 1- leaf node
            if(root.left==null && root.right==null){
                return null;
            }
            //case 2 - single child
            if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }
            //case 3 - two child
            Node IS= findInOrderSuccessor(root.right);
            root.data=IS.data;
            root.right=delete(root.right, IS.data);
        }
        return root;
    }

    public static Node findInOrderSuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }   

//Print in range
    public static void printInRange(Node root, int k1, int k2){
        if(root==null){
            return;
        }
        if(root.data>=k1 && root.data<=k2){
            printInRange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printInRange(root.right, k1, k2);
        }
        else if(root.data<k1){
            printInRange(root.right, k1, k2);
        }
        else{
            printInRange(root.left, k1, k2);
        }
    }

//Root to leaf path
    public static void printPath(ArrayList<Integer> path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+"->");
        }
        System.out.println("Null");
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path){
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.left==null && root.right==null){
            printPath(path);
        }
        printRootToLeaf(root.left, path);
        printRootToLeaf(root.right, path);
        path.remove(path.size()-1);
    }

//Valid BST
    public static boolean isValidBST(Node root, Node min, Node max){
        if(root==null){
            return true;
        }
        if((min!=null && root.data<=min.data) || (max!=null && root.data>=max.data)){
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }


//Sorted array to Balanced BST
    public static void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);  
        preOrder(root.right);
    }    

    public static Node createBST(int arr[], int st, int end){
        if(st>end){
        return null;
    }
        int mid=(st+end)/2;
        Node root= new Node(arr[mid]);
        root.left=createBST(arr, st, mid-1);
        root.right=createBST(arr, mid+1, end);
        return root;
    }


//Convert BST to balanced BST
    public static void getInOrder(ArrayList<Integer> inorder,Node root){
        if(root==null){
            return;
        }
        getInOrder(inorder, root.left);
        inorder.add(root.data);
        getInOrder(inorder, root.right);
    }   
    public static Node createBST2(ArrayList<Integer> inorder, int st,int end){
        if(st>end){
            return null;
        }
        int mid=(st+end)/2;
        Node root= new Node(inorder.get(mid));
        root.left= createBST2(inorder, st, mid-1);
        root.right= createBST2(inorder, mid+1, end);
        return root;
    }

    public static Node balanceBST(Node root){
        //SOrted arr
        ArrayList<Integer> inorder = new ArrayList<>();
        getInOrder(inorder, root);
        //sorted inorder-> balanced BST
        root= createBST2(inorder, 0, inorder.size()-1);
        return root;

    }
    
//Size of largest BST
    static class Info{
        boolean isBST;
        int size;
        int min;
        int max;

        public Info(boolean isBST, int size, int min , int max){
            this.isBST=isBST;
            this.size=size;
            this.min=min;
            this.max=max;
        }
    }
    public static int maxBST=0;
    public static Info largestBST(Node root){
        if(root==null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo=largestBST(root.left);
        Info rightInfo= largestBST(root.right);
        int size= leftInfo.size+rightInfo.size+1;
        int min=Math.min(root.data,Math.min(leftInfo.min,rightInfo.min));
        int max=Math.max(root.data,Math.max(leftInfo.max,rightInfo.max));

        if(root.data<=leftInfo.max || root.data>=rightInfo.min){
            return new Info(false,size, min, max);
        }
        if(leftInfo.isBST && rightInfo.isBST){
            maxBST=Math.max(maxBST, size);
            return new Info(true,size,min,max);
        } 
        return new Info(false, size, min, max);

    }   

//Merge 2 BSTs

    public static Node createBST3(ArrayList<Integer> arr,int st, int end){
        if(st>end){
            return null;
        }
        int mid=(st+end)/2;
        Node root= new Node(arr.get(mid));
        root.left= createBST3(arr, st, mid-1);
        root.right= createBST3(arr, mid+1, end);
        return root;
    }

    public static Node mergeBSTs(Node root1, Node root2){
        //step1
        ArrayList<Integer> arr1= new ArrayList<>();
        getInOrder(arr1, root1);
        //step2
        ArrayList<Integer> arr2= new ArrayList<>();
        getInOrder(arr2, root2);
        //merge
        int i=0,j=0;
        ArrayList<Integer> finalArr= new ArrayList<>();
        while(i<arr1.size() && j<arr2.size()){
            if(arr1.get(i)<=arr2.get(j)){
                finalArr.add(arr1.get(i));
                i++;
            }else{
                finalArr.add(arr2.get(j));
                j++;
            }
        }
        while(i<arr1.size()){
            finalArr.add(arr1.get(i));
            i++;
        }
        while(j<arr2.size()){
            finalArr.add(arr2.get(j));
            j++;
        }

        //sorted Al-> balanced BST
        System.out.println(finalArr);
        return createBST3(finalArr, 0, finalArr.size()-1);
    }



    public static void main(String[] args) {
        // int values[]={5,1,3,4,2,7};
        // int values[]={8,5,9,3,1,4,6,10,11,14};
        
     //Sorted array to Balanced BST   
        // int arr[]={3,5,6,8,10,11,12};
        // Node root= createBST(arr, 0, arr.length-1);
        // preOrder(root);


        // Node root= null;

        // for(int i=0;i<values.length;i++){
        //     root=insert(root, values[i]);
        // }
        // inorder(root);
        // System.out.println();

        // if(search(root, 8)){
        //     System.out.println("found");
        // }else{
        //     System.out.println("not found");
        // }

        // delete(root, 5);
        // inorder(root);
        // printInRange(root, 1, 7);
        // printRootToLeaf(root, new ArrayList<>());
        // if(isValidBST(root, null, null)){
        //     System.out.print("isValid");
        // }else{
        //     System.out.print("notValid");
        // }


    //Convert BST to balanced BST
    // Node root= new Node(8);
    // root.left= new Node(6);
    // root.left.left= new Node(5);
    // root.left.left.left= new Node(3);

    // root.right= new Node(10);
    // root.right.right= new Node(11);
    // root.right.right.right= new Node(12);

    // root=balanceBST(root);
    // preOrder(root);
    
//size of largest BST
    // Node root= new Node(50);
    // root.left= new Node(30);
    // root.left.left= new Node(5);
    // root.left.right= new Node(20);
    // root.right= new Node(60);
    // root.right.left= new Node(45);
    // root.right.right= new Node(70);
    // root.right.right.left= new Node(65);
    // root.right.right.right= new Node(80);
    // Info info=largestBST(root);
    // System.out.print("largest BST is "+maxBST);


//Merge 2 BSTs
    Node root1= new Node(2);
    root1.left=new Node(1);
    root1.right= new Node(4);

    Node root2= new Node(9);
    root2.left=new Node(3);
    root2.right= new Node(12);
    Node root= mergeBSTs(root1, root2);
    preOrder(root);
}
}

