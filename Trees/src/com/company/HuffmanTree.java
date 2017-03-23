package com.company;

/**
 * Created by zqiguo on 2017/3/12.
 */
public class HuffmanTree{
    private HuffmanNode root;

    public HuffmanTree(){
        root = null;
    }
    public HuffmanTree(int[] array){
        if (array == null || array.length == 0){
            root = null;
            return;
        }
        HuffmanNode[] huffmanNodes = new HuffmanNode[array.length];
        for (int i = 0; i < array.length; i++) {
            huffmanNodes[i] = new HuffmanNode(array[i]);
        }
        Heap heap = new Heap(huffmanNodes);
        HuffmanNode tmp;
        while (heap.getCurrentSize() > 1){
            HuffmanNode least1 = heap.delete();
            HuffmanNode least2 = heap.delete();
            tmp = new HuffmanNode(least1.weight+least2.weight);
            tmp.left = least1;
            tmp.right = least2;
            heap.insert(tmp);
        }
        tmp = heap.getTop();
        root = tmp;
    }


    public int getHuffmanWeight(){
        return getHuffmanWeight(this.root, 0);
    }

    /**
     *
     * @param root the root of huffman tree.
     * @param level the level of tree, should be initialized by 0;
     * @return the weight of huffman tree.
     */
    private int getHuffmanWeight(HuffmanNode root, int level){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.weight * level;
        return getHuffmanWeight(root.left, level+1) + getHuffmanWeight(root.right, level+1);
    }

    public static void main(String[] args){
        int[] array = new int[]{5, 25,3,6,10,11,36,4};
        HuffmanTree huffmanTree = new HuffmanTree(array);
        System.out.println(huffmanTree.getHuffmanWeight());
    }

}