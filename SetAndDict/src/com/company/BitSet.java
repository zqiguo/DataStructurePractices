package com.company;

public class BitSet {
    private int setSize; // setSize is the element number of set
    private int vectorSize; // vectorSize is the storage that needed by the set
    private int[] bitVector;

    public BitSet(){
        this.setSize = 1000;
        this.vectorSize = (this.setSize + 31) >> 5;
        this.bitVector = new int[this.vectorSize];
    }

    /**
     * set the volume of the set
     * @param setSize the volume of the set
     */
    public BitSet(int setSize){
        this.setSize = setSize;
        this.vectorSize = (setSize + 31) >> 5;
        this.bitVector = new int[this.vectorSize];
    }

    /**
     * set member value.Note that the min member should be 1, not 0.
     * @param element the element of set
     * @param value the value to set; only 0 or 1
     * @return true or false
     */
    public boolean setMemberValue(int element, int value){
        if (element < 1 || element > setSize){
            System.err.println("Out of Range!");
            return false;
        }
        element -= 1;        // begin with 0 instead of 1.
        int id = element / 32;
        int ad = element % 32;
        int tmp = bitVector[id];
        int last = tmp >>> (31 - ad);
        tmp = tmp << (ad + 1);
        if (last % 2 == 0 && value == 1){
            last = last + 1;
        }
        if (last % 2 == 1 && value == 0){
            last -= 1;
        }
        bitVector[id] = (last << (31 - ad)) | (tmp >>> (ad + 1));
        return  true;
    }

    /**
     * get the value of the element. Note that the min element is 1, not 0;
     * @param element
     * @return if the element in the set, return 1; else 0.
     */
    public int getMemberValue(int element){
        if (element < 1 || element > setSize){
            System.err.println("Out Of Range!");
            return -1;
        }
        element -= 1;
        int id = element / 32;
        int ad = element % 32;
        int result = bitVector[id] >>> (31-ad);
        result %= 2;
        return result;
    }

    /**
     * add the element to the set
     * @param element
     * @return
     */
    public boolean addMember(int element){
        if (getMemberValue(element) == 1){
            System.err.println("Already In!");
            return false;
        }
        return setMemberValue(element,1);
    }

    /**
     * delete the element of the set
     * @param element
     * @return
     */
    public boolean delMember(int element){
        if (getMemberValue(element) == 0){
            System.err.println("Not have it!");
            return false;
        }
        return setMemberValue(element,0);
    }

    /**
     * print an int in binary string, from the first bit that not 0.
     * @param value
     */
    public void printBinaryValue(int value){
        System.out.println(Integer.toBinaryString(value));
    }

    public static void main(String[] args) {
	// write your code here
        BitSet bitSet = new BitSet(5000);
        bitSet.addMember(1);
        bitSet.addMember(31);
        bitSet.addMember(20);
        bitSet.addMember(32);
        bitSet.addMember(2);
        bitSet.printBinaryValue(bitSet.bitVector[0]);
        bitSet.addMember(21);
        bitSet.printBinaryValue(bitSet.bitVector[0]);
        bitSet.delMember(21);
        bitSet.printBinaryValue(bitSet.bitVector[0]);
        System.out.println(bitSet.getMemberValue(1));
        System.out.println(bitSet.getMemberValue(20));
    }
}
