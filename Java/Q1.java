//Build a tree for the expression (x+(y*z)-w) + (u-v)


// Node class
class Node {
    Node left;
    Node right;
    String value;
  
    // Constructor
    public Node(String value) {
       this.value = value;
       left = null;
       right = null;
    }
  
    // Set left child
    public void setLeft(Node left) {
       this.left = left;
    }
  
    // Set right child
    public void setRight(Node right) {
       this.right = right;
    }
  
    // print tree in inorder
    public void printInorder() {
       if(left != null) {
          left.printInorder();
       }
       System.out.print(value + " ");
       if(right != null) {
          right.printInorder();
       }
    }
  }
  
  // Tree class
  class Tree {
    Node root;
  
    // Constructor
    public Tree(String value) {
       root = new Node(value);
    }
  
    // Set root
    public void setRoot(Node root) {
       this.root = root;
    }
  
  
    public void printInorder() {
       root.printInorder();
    }
  }
  
  // Main class
  public class Q1 {
    public static void main(String[] args) {
       Tree tree = new Tree("0");
       Node root = tree.root;
  
       // Set root
       tree.setRoot(new Node("+"));
       root = tree.root;
  
       // Set left child
       root.setLeft(new Node("-"));
  
       // Set right child
       root.setRight(new Node("-"));
  
       // Set left child of left child
       root.left.setLeft(new Node("+"));
  
       // Set right child of left child
       root.left.setRight(new Node("w"));
  
       // Set left child of right child
       root.right.setLeft(new Node("u"));
  
       // Set right child of right child
       root.right.setRight(new Node("v"));
  
       // Set left child of left child of left child
       root.left.left.setLeft(new Node("x"));
  
       // Set right child of left child of left child
       root.left.left.setRight(new Node("*"));
  
       // Set left child of right child of left child of left child
       root.left.left.right.setLeft(new Node("y"));
  
       // Set right child of right child of left child of left child
       root.left.left.right.setRight(new Node("z"));
  
       // Print tree
       tree.printInorder();
  
  
    }
  }
  