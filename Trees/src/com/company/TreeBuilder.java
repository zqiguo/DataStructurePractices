package com.company;

/**
 * Build a binary tree from a generalized list.
 * input is like this:A(B(D,E(G,)),C(,F))#
 */
public class TreeBuilder {

    private static class Node{
        char value;
        Node left;
        Node right;
    }

    /**
     * Build a binary tree from a generalized list.
     * @param glist glist is a generalized list, for example: A(B(D,E(G,)),C(,F))#
     * @return the root node of tree
     */
    public static Node creatTree(String glist){
       if (glist.isEmpty()){
           return null;
       }
       Node[] stack = new Node[glist.length()];
       int pos = 0; int top = -1;
       Node root = new Node();
       if ((glist.charAt(pos) + "").matches("[A-Z]")){ //if is upper character, push in stack
            root.value = glist.charAt(pos);
            stack[++top] = root;
        }else {
           return null;
       }
       while (glist.charAt(pos) != '#'){
           char ch = glist.charAt(pos);
           if (ch == '('){
               pos++;
               if ((glist.charAt(pos) + "").matches("[A-Z]")){
                   Node tmp = new Node();
                   tmp.value = glist.charAt(pos);
                   stack[top].left = tmp;
                   if (glist.charAt(pos+1) == '('){ // if has sub-tree, push in stack
                       stack[++top] = tmp;
                   }
                   pos++;
               }else if (glist.charAt(pos) == ','){
                   stack[top].left = null;
               }
           }else if (glist.charAt(pos) == ','){ // after ',' is right sub-tree.
               pos++;
               if ((glist.charAt(pos) + "").matches("[A-Z]")){
                   Node tmp = new Node();
                   tmp.value = glist.charAt(pos);
                   stack[top--].right = tmp; // pop stack
                   if (glist.charAt(pos+1) == '('){ // if has sub-tree, push in stack
                       stack[++top] = tmp;
                   }
                   pos++;
               }else {
                   stack[top--].right = null;
               }
           }else {
               pos++;
           }
       }
       if (top == -1){ // test whether the result is true.
           System.out.println(true);
       }
       return root;
    }


    /**
     * Build a binary tree from a generalized list.
     * version 2, use switch grammar
     * @param glist glist is a generalized list, for example: A(B(D,E(G,)),C(,F))#
     * @return the root node of tree
     */
    public static Node creatTree2(String glist){
        if (glist.isEmpty()){
            return null;
        }
        Node[] stack = new Node[glist.length()];
        int top = -1;
        int flag = 0; // right sub-tree or left sub-tree
        int pos = 0;  // char position
        Node root = null;
        Node tmp = null;
        while (glist.charAt(pos) != '#'){
            char ch = glist.charAt(pos);
            switch (ch){
                case '(':
                    stack[++top] = tmp;// push in
                    pos++;
                    flag = 1;  // left sub-tree
                    break;
                case ',':
                    pos++;
                    flag = 2; // right sub-tree
                    break;
                case ')':
                    pos++;
                    top--;  // pop out
                    break;
                default:
                    tmp = new Node();
                    tmp.value = ch;
                    if (root == null){  // link the root
                        root = tmp;
                    }
                    if (flag == 1){
                        stack[top].left = tmp;
                    }else if (flag == 2){
                        stack[top].right = tmp;
                    }
                    pos++;
            }
        }
        return root;
    }

    public static void printPreTree(Node root){
        if (root != null){
            System.out.print(root.value + " ");
            printPreTree(root.left);
            printPreTree(root.right);
        }
    }

    /**
     * the non-recursion pre traversal of tree
     * @param root
     */
    public static void printPreTreeNonRecursion(Node root){
        Node tmp = root;
        Node[] stack = new Node[20];
        int top = -1;
        while (tmp != null || top != -1){
            if (tmp != null){
                System.out.printf(tmp.value + " ");
                if (tmp.right != null){
                    stack[++top] = tmp.right;
                }
                tmp = tmp.left;
            }else {
                tmp = stack[top--];
            }
        }
        System.out.println();
    }

    /**
     * the hierarchical traversal of tree
     * @param root
     */
    public static void printHierarchical(Node root){
        Node[] queen = new Node[20];
        int head = 0;
        int tail = 0;
        Node tmp = root;
        queen[tail++%20] = tmp;
        while (tail != head){
            tmp = queen[head++%20];
            System.out.printf(tmp.value + " ");
            if (tmp.left != null){
                queen[tail++%20] = tmp.left;
            }
            if(tmp.right != null){
                queen[tail++%20] = tmp.right;
            }
        }
        System.out.println();
    }

    /**
     * 树的中序非递归遍历
     * the middle non-recursion traversal of tree
     * @param root
     */
    public static void printMidTreeNonRecursion(Node root){
        Node[] stack = new Node[20];
        int top = -1;
        Node tmp = root;
        while (tmp != null || top != -1){
            while (tmp != null){
                stack[++top] = tmp;
                tmp = tmp.left;
            }
            if (top != -1){
                tmp = stack[top--];
                System.out.printf(tmp.value + " ");
                tmp = tmp.right;
            }
        }
        System.out.println();
    }

    /**
     * 树的中序递归遍历
     * the middle recursion traversal of tree
     * @param root
     */
    public static void printMidTree(Node root){
        if (root != null){
            printMidTree(root.left);
            System.out.printf(root.value + " ");
            printMidTree(root.right);
        }
    }

    /**
     * 树的后序递归遍历
     * the suffix recursion of traversal of tree
     */
    public static void printSufTree(Node root){
        if (root != null){
            printSufTree(root.left);
            printSufTree(root.right);
            System.out.printf(root.value + " ");
        }
    }

    /**
     * the suffix non-recursion traversal of tree
     */
    public static void printSufTreeNonRecursion(Node root){
        class FlagNode{
            Node node;
            int flag;
        }
        FlagNode[] stack = new FlagNode[20];
        int top = -1;
        Node tmp = root;
        while (top != -1 || tmp != null){
            while (tmp != null){
                FlagNode flagNode = new FlagNode();
                flagNode.node = tmp;
                flagNode.flag = 0;
                stack[++top] = flagNode;
                tmp = tmp.left;
            }
            if (top != -1){
                if (stack[top].flag == 0){
                    stack[top].flag = 1;
                    tmp = stack[top].node.right;
                }else {
                    System.out.printf(stack[top--].node.value + " ");
                }
            }
        }
        System.out.println();
    }
    /**
     * 以广义表的形式把树打印出来
     * @param root
     */
    public static void printGTree(Node root){
        if(root != null){
            System.out.printf(root.value + "");
            if (root.left != null || root.right != null) {
                System.out.printf("(");
                printGTree(root.left);
                System.out.printf(",");
                printGTree(root.right);
                System.out.printf(")");
            }
        }
    }

    /**
     * count the number of leaves in a tree.
     * @param root Node root of tree
     * @return int  leaves number
     */
    public static int numOfLeaves(Node root){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return numOfLeaves(root.left) + numOfLeaves(root.right);
    }

    /**
     * change all the left sub-tree and right sub-tree position in the tree.
     * @param root
     */
    public static void changeLeftAndRight(Node root){
        if (root != null){
            changeLeftAndRight(root.left);
            changeLeftAndRight(root.right);
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    /**
     * count the number of node whose degree is 1
     * @param root number
     */
    public static int numOf1degree(Node root){
        if (root == null) return 0;
        int sum = numOf1degree(root.left) + numOf1degree(root.right);
        if (root.right == null && root.left != null || root.left == null && root.right != null) return sum + 1;
        return sum;
    }

    /**
     * count the number of nodes whose degree are 2.
     * @param root
     * @return number
     */
    public static int numOf2degree(Node root){
        if (root == null) return 0;
        int sum = numOf2degree(root.left) + numOf2degree(root.right);
        if (root.right != null && root.left != null) return sum + 1;
        return sum;
    }

    public static int numOf0degree(Node root){
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;
        return numOf0degree(root.left) + numOf0degree(root.right);
    }

    /**
     * return the deep of the tree.
     * @param root
     * @return  deep of tree
     */
    public static int deepOfTree(Node root){
        if (root == null) return 0;
        int left = deepOfTree(root.left);
        int right = deepOfTree(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * return width of the tree. we have a wideArray which stores every level width of the tree.
     * what we need to do is find the max width.
     * @param root root of the tree
     * @return width of tree
     */
    public static int wideOfTree(Node root){
        int[] wideArray = new int[20];
        wideOfTree(root, wideArray,0);
        int max = 0;
        for (int i: wideArray) {
            if (i > max){
                max = i;
            }
        }
        return max;
    }

    /**
     * private width of tree
     * Recursion is deep first traversal. But calculate the width of tree is hierarchical operation.
     * Without extra help, we cannot guarantee the recursion nodes are in the same level.
     * @param root root of tree
     * @param wideArray we need to store the width of every level. so we need a wideArray to help.
     * @param level level of root
     */
    public static void wideOfTree(Node root, int[] wideArray, int level){
        if (root != null){
            wideArray[level] += 1;
            wideOfTree(root.left, wideArray, level+1);
            wideOfTree(root.right, wideArray, level+1);
        }
    }


    /**
     * return the level of the specified node.
     * @param root root of the tree.
     * @param p the specified node
     * @param level the level of root
     * @return the level of the specified node
     */
    public static int levelOfP(Node root, Node p, int level){
        if (root == null) return 0;
        if (root == p) return level + 1;
        int left = levelOfP(root.left, p, level+1);
        int right = levelOfP(root.right, p, level+1);
        return left > right ? left : right;
    }

    /**
     * delete all leaf nodes of the tree
     * @param root the root of the tree
     */
    public static void deleteAllLeaves(Node root){
        if (root == null) return;
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        deepOfTree(root.left);
        deepOfTree(root.right);
    }

    /**
     * use suffix traversal to realize the function.
     * In every recursion level , return the max of the parent value and the sub-tree value.
     * return the value of the node which has the max value
     * @param root root of the tree
     * @param lastMax the max value of parent node. should be initialized by Integer.MIN_VALUE
     * @return the max node value of the tree
     */
    public static int valueOfMaxNode(Node root, int lastMax){
        if (root == null) return lastMax;
        if (root.value > lastMax) lastMax = root.value;
        int maxLeft = valueOfMaxNode(root.left, lastMax);
        int maxRight = valueOfMaxNode(root.right, lastMax);
        return maxLeft > maxRight ? maxLeft : maxRight;
    }

    public static void printNodeInfoByInfixTraversal(Node root, int level) {
        if (root != null){
            System.out.print("( "+ root.value + " " + level + ") ");
            printNodeInfoByInfixTraversal(root.left, level+1);
            printNodeInfoByInfixTraversal(root.right, level+1);
        }
    }
    public static void main(String[] args) {
	// write your code here
        Node root =creatTree("A(B(G,H(K,L)),C(D,E(F,)))#");
        System.out.println("Pre traversal: ");
        printPreTree(root);
        System.out.println();
        printPreTreeNonRecursion(root);
        System.out.println("leaf num:" + numOfLeaves(root));

        printHierarchical(root);



        Node root2 =creatTree2("A(B(G(H(K(,J(,Z)),),I),M(,N)),C(D(Y,),E(F,)))#");
        System.out.println("Mid traversal: ");
        printMidTree(root2);
        System.out.println();
        printMidTreeNonRecursion(root2);
        System.out.println("Suf traversal: ");
        printSufTree(root2);
        System.out.println();
        printSufTreeNonRecursion(root2);
        printHierarchical(root2);
        System.out.println("Leaf num: " + numOfLeaves(root2));
        System.out.println("Degree 1 num: " + numOf1degree(root2));
        System.out.println("Degree 2 num: " + numOf2degree(root2));
        System.out.println("Deep of tree: " + deepOfTree(root2));
        System.out.println("Wide of tree: " + wideOfTree(root2));
        System.out.println("level of P: " + levelOfP(root2,root2.left.left.left.left.right.right,0));
        System.out.println("Max Value of tree: " + (char)valueOfMaxNode(root2, Integer.MIN_VALUE));
        printNodeInfoByInfixTraversal(root2, 1);
        System.out.println();
    }
}
