package BinaryTree;

public class deleteLeaves
{
    static class Node{
        int data;
        Node left,right;
    
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
         }
    }
    public static Node deleteLeave(Node root,int x){
        if(root==null){
            return null;
        }
        root.left=deleteLeave(root.left,x);
        root.right=deleteLeave(root.right,x);

        if(root.data==x&&root.left==null&&root.right==null){
            return null;
        }
        return root;
    }
    public static void inorder(Node root ){
        if(root==null){
            return;
        }
        System.out.println(root.data +" ");
        inorder(root.left);
      
        inorder(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(3);
        root.left.right = new Node(1);
        root.right.right = new Node(3);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(3);
        deleteLeave(root,3);
        inorder(root);

    }
}
