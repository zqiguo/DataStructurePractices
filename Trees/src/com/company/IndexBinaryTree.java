package com.company;

/**
 * 根据中序及前序建立二叉树
 * 根据后序及前序建立二叉树
 * 二叉树中序索引化
 * Created by zqiguo on 2017/3/8.
 */
public class IndexBinaryTree {
    private static class IndexNode{
        char value;
        IndexNode left;
        IndexNode right;
        boolean flagLeft;
        boolean flagRight;
    }

    /**
     * 建立中序索引二叉树的公用接口，注意手动赋值最后一节点。
     * @param root
     */
    public static void creatMidIndexTree(IndexNode root){
        if (root == null) {
            return;
        }
        IndexNode[] pre = new IndexNode[1];
        creatMidIndexTree(root, pre);
        pre[0].right = null;
        pre[0].flagRight = true;
    }
    /**
     * 建立中序索引，之所以pre 为[]类型，为了在递归中传递值
     * pre保存前一中序节点，这样就足够，注意要手动将最后一个节点右子树赋值。
     * 左节点走到底，脑中模拟一遍，就出结果了。
     * @param root 根节点
     * @param pre IndexNode[], 数组容量为1，用于在递归中保存中序前一节点
     *
     */
    private static void creatMidIndexTree(IndexNode root, IndexNode[] pre){
        if (root == null){
            return;
        }
        creatMidIndexTree(root.left, pre);
        if (root.left == null){
            root.left = pre[0];
            root.flagLeft = true;
        }
        if (pre[0] != null && pre[0].right == null){
            pre[0].right = root;
            pre[0].flagRight = true;
        }
        pre[0] = root;
        creatMidIndexTree(root.right, pre);
    }


    /**
     * 根据中序和前序建立二叉树
     * @param pre  String 前序序列
     * @param mid  String 中序序列
     * @return  根节点
     */
    public static IndexNode creatTreeByPreAndMid(String pre, String mid){
        IndexNode indexNode = null;
        if(pre.length() > 0){
            indexNode = new IndexNode();
            indexNode.value = pre.charAt(0);
            String left = mid.substring(0, mid.indexOf(indexNode.value));
            int i = 1;
            while (i < pre.length()){
                if (left.contains(pre.charAt(i) + "")){
                    i++;
                }else {
                    break;
                }
            }
            String preLeft = pre.substring(1,i);
            String preRight = pre.substring(i,pre.length());
            String right = mid.substring(mid.indexOf(indexNode.value)+1, mid.length());
            indexNode.left = creatTreeByPreAndMid(preLeft,left);
            indexNode.right = creatTreeByPreAndMid(preRight,right);
        }
        return indexNode;
    }

    /**
     * 根据后序和中序建立二叉树
     * @param mid
     * @param suf
     * @return
     */
    public static IndexNode createTreebyMidAndSuf(String mid, String suf){
        IndexNode root = null;
        if (suf.length() > 0){
            root = new IndexNode();
            root.value = suf.charAt(suf.length()-1);
            String midLeft = mid.substring(0,mid.indexOf(suf.charAt(suf.length()-1)));
            String midRight = mid.substring(mid.indexOf(suf.charAt(suf.length()-1))+1, mid.length());
            String sufLeft = suf.substring(0,midLeft.length());
            String sufRight = suf.substring(midLeft.length(),suf.length()-1);
            root.left = createTreebyMidAndSuf(midLeft,sufLeft);
            root.right = createTreebyMidAndSuf(midRight,sufRight);
        }
        return root;
    }

    /**
     * 中序非递归遍历
     * @param root
     */
    public static void printMidNonStack(IndexNode root){
        IndexNode[] stack = new IndexNode[20];
        int top = -1;
        IndexNode tmp = root;
        while (tmp != null || top != -1){
            while (tmp != null){
                stack[++top] = tmp;
                tmp = tmp.left;
            }
            if (top != -1){
                tmp = stack[top--];
                System.out.printf(tmp.value + " ");
            }
            tmp = tmp.right;
        }
        System.out.println();
    }

    public static void printMid(IndexNode root){
        if (root != null){
            printMid(root.left);
            System.out.printf(root.value + " ");
            printMid(root.right);
        }
    }

    /**
     * 前序非递归遍历
     * @param root
     */
    public static void printPreNonStack(IndexNode root){
        IndexNode tmp = root;
        IndexNode[] stack = new IndexNode[20];
        int top = -1;
        while (top != -1 || tmp != null){
            if (tmp == null){
                tmp = stack[top--];
            }else {
                System.out.printf(tmp.value + " ");
                if (tmp.right != null){
                    stack[++top] = tmp.right;
                }
                tmp = tmp.left;
            }
        }
        System.out.println();
    }

    public static void printPre(IndexNode root){
        if (root != null){
            System.out.printf(root.value + " ");
            printPre(root.left);
            printPre(root.right);
        }
    }

    public static void printSuf(IndexNode root){
        if (root != null){
            printSuf(root.left);
            printSuf(root.right);
            System.out.printf(root.value + " ");
        }
    }

    /**
     * 后序非递归遍历
     * @param root
     */
    public static void printSufNonStack(IndexNode root){
        class FlagIndexNode{
            IndexNode indexNode;
            boolean right;
        }
        FlagIndexNode[] stack = new FlagIndexNode[20];
        int top = -1;
        IndexNode tmp = root;
        while (top != -1 || tmp != null){
            while (tmp != null){
                FlagIndexNode flagIndexNode = new FlagIndexNode();
                flagIndexNode.indexNode = tmp;
                stack[++top] = flagIndexNode;
                tmp = tmp.left;
            }
            if (stack[top].right == false){
                stack[top].right = true;
                tmp = stack[top].indexNode.right;
            }else {
                System.out.printf(stack[top--].indexNode.value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 使用中序索引二叉树来实现前序遍历。不用栈。
     * 方法要点在于找出节点的前序后继。节点的前序后继就是节点中序后继标志不为TRUE的第一个节点的右子树。
     * 画一个树图示意一下，就明白了。
     * @param root
     */
    public static void printPreOrderUseIndex(IndexNode root){
        IndexNode tmp = root;
        while (tmp != null){
            while (tmp.flagLeft != true) {
                System.out.printf(tmp.value + " ");
                tmp = tmp.left;
            }
            System.out.printf(tmp.value + " ");
            if (tmp.flagRight != true){
                tmp = tmp.right;
            }else {
                while (tmp.flagRight == true && tmp.right != null){
                    tmp = tmp.right;
                }
                tmp = tmp.right;
            }
        }
        System.out.println();
    }

    /**
     * Use middle indexed binary tree to print middle traversal order.
     * @param root the root of tree
     */
    public static void printMidOrderUseIndex(IndexNode root){
        IndexNode tmp = root;
        while (tmp != null){
            while (tmp.flagLeft != true){
                tmp = tmp.left;
            }
            System.out.printf(tmp.value + " ");
            while (tmp.flagRight == true && tmp.right != null){
                tmp = tmp.right;
                System.out.printf(tmp.value + " ");
            }
            tmp = tmp.right;
        }
    }

    public static void main(String[] args){
        String pre = "ABDGCEF";
        String mid = "BGDACEF";
        String suf = "GDBFECA";
        IndexNode root = creatTreeByPreAndMid(pre,mid);
        IndexNode root2 = createTreebyMidAndSuf(mid,suf);

        printMid(root);
        System.out.println();
        printMidNonStack(root);
        printMid(root2);
        System.out.println();
        printMidNonStack(root2);
        System.out.println();
        printPre(root);
        System.out.println();
        printPreNonStack(root2);
        printPre(root2);
        System.out.println();
        System.out.println();

        printSuf(root);
        System.out.println();
        printSufNonStack(root);

        System.out.println();
        System.out.println();
        printPreNonStack(root);
        printMidNonStack(root);
        creatMidIndexTree(root);
        printPreOrderUseIndex(root);
        printMidOrderUseIndex(root);
        System.out.println();
        int a = -1024;
        System.out.println(Integer.toBinaryString(a));
        int b = 0x1001;
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(b | ~a));
    }
}
