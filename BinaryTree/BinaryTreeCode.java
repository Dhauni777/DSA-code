package BinaryTree;
import java.util.*;
public class BinaryTreeCode
{
    static class Node{
        int data;
        Node left;
        Node right;
        
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static class BinaryTree{
        static int idx=-1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode= new Node(nodes[idx]);
            newNode.left= buildTree(nodes);
            newNode.right= buildTree(nodes);
            return newNode;
        }
        public static void preorder(Node root){
            if(root==null){
                return;
            }
            System.out.print(root.data +" ");
            preorder(root.left);
            preorder(root.right);
            
        }
         public static void inorder(Node root){
            if(root==null){
                return;
            }
            inorder(root.left);
            System.out.print(root.data +" ");
            inorder(root.right);
            
        }
         public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data +" ");
        }
        public static void levelOrder(Node root){
            if(root== null){
                return;
            }
            Queue<Node> q= new LinkedList<>();
            q.add(root);
            q.add(null);
            
            while(!q.isEmpty()){
                Node currNode=q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    } 
                    if(!q.isEmpty()){
                        q.add(null);
                    }
                }else{
                    System.out.print(currNode.data +" ");
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        q.add(currNode.right);
                    }
                }
                
            }
        }
    }
    
    
// 	public static void main(String[] args) {
// 		int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
// 		// BinaryTree tree= new BinaryTree();                //no need
// 		Node root=BinaryTreeCode.BinaryTree.buildTree(nodes);
// // 		System.out.print(root.data);
//         BinaryTreeCode.BinaryTree.preorder(root);
//         System.out.println();
//         BinaryTreeCode.BinaryTree.inorder(root);
//         System.out.println();
//         BinaryTreeCode.BinaryTree.postorder(root);
//         System.out.println();
//         BinaryTreeCode.BinaryTree.levelOrder(root);
// 		}
// }

public static void KLevel(Node root, int level, int k){
    if(root== null){
        return;
    }
    if(level==k){
        System.out.print(root.data+" ");
        return;
    }
    KLevel(root.left, level+1, k);
    KLevel(root.right, level+1, k);
}

// Lowest common ancestor 
// Approach 1
public static boolean getPath(Node root, int n, ArrayList<Node> path){
    if(root==null){
        return false;
    }
    path.add(root);

    if(root.data==n){
        return true;
    }
    boolean foundLeft= getPath(root.left, n, path);
    boolean foundRight= getPath(root.right, n, path);
    if(foundLeft||foundRight){
        return true;
    }else{
    path.remove(path.size()-1);
        return false;
    }
}


public static Node lca(Node root, int n1, int n2){
    ArrayList<Node> path1= new ArrayList<>();
    ArrayList<Node> path2= new ArrayList<>(); 

    getPath(root, n1, path1);
    getPath(root, n2, path2);

    //lase common ancestor
    int i=0; 
    for(;i<path1.size() && i<path2.size(); i++){
        if(path1.get(i)!=path2.get(i)){
            break;
        }
    }
    Node lca= path1.get(i-1);
    return lca;
}

// Lowest common ancestor 
//Aprrroach 2
public static Node lca2(Node root, int n1, int n2){
    if(root==null||root.data==n1||root.data==n2){
        return root;
    }
    Node leftlca= lca2(root.left, n1, n2);
    Node rightlca=lca2(root.right, n1, n2);
    //leftlca=valid n rightlca=null
    if(rightlca==null){
        return leftlca;
    }
    if(leftlca==null){
        return rightlca;
    }
    return root;
}


//Min distance
public static int lcaDist(Node root, int n){
    if(root==null){
        return -1;
    }
    if(root.data==n){
        return 0;
    }
    int leftDist= lcaDist(root.left, n);
    int rightDist= lcaDist(root.right, n);

    if(leftDist==-1 && rightDist==-1){
        return -1;
    }else if(leftDist==-1){
        return rightDist+1;
    }else{
        return leftDist+1;
    }
}

public static int minDist(Node root, int n1, int n2){
    Node lca= lca2(root, n1, n2);
    int dist1= lcaDist(lca,n1);
    int dist2= lcaDist(lca,n2);

    return dist1+dist2;
}

//Kth ancestor
public static int KAncestor(Node root, int n,int k){
    if(root==null){
        return -1;
    }
    if(root.data==n){
        return 0;
    }
    int leftDist= KAncestor(root.left, n, k);
    int rightDist= KAncestor(root.right, n, k);

    if(leftDist==-1 && rightDist==-1){
        return -1;
    }
    int max= Math.max(leftDist,rightDist);
    if(max+1==k){
        System.out.println(root.data);
    }
    return max+1;
}

//Transform to sum tree
public static int transform(Node root){
    if(root==null){
        return 0;
    }
    int leftchild= transform(root.left);
    int rightchild= transform(root.right);

    int data= root.data;

    int newLeft= root.left == null ? 0 : root.left.data;
    int newRight= root.right == null ? 0 : root.right.data;

    root.data= newLeft + leftchild + newRight + rightchild;
    return data;
}

public static void preorder(Node root){
    if(root== null){
        return;
    }
    System.out.print(root.data+" ");
    preorder(root.left);
    preorder(root.right);
}
public static void main(String[] args) {
    Node root = new Node(1);
    root.left= new Node(2);
    root.right= new Node(3);
    root.left.left= new Node(4);
    root.left.right= new Node(5);
    root.right.left= new Node(6);
    root.right.right= new Node(7);

//  int k=2;
// KLevel(root, 1, k);

//   System.out.println(minDist(root, 4, 6));
   // int n=4,k= 1;
   // KAncestor(root, n, k);
  transform(root);
   preorder(root);
    }
}
