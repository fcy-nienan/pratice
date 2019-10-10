package DataStructurePratice;

import jdk.internal.org.xml.sax.InputSource;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;

public class FcyPriorityQueue1 {
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY=64;
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
    public FcyPriorityQueue1(int initCapacity){
        if (initCapacity<0){
            throw new IllegalArgumentException("capacity can not be zero!");
        }else{
            this.array=new int[initCapacity];
        }
    }
    public FcyPriorityQueue1(){
        this(DEFAULT_CAPACITY);
    }
    public void ensureCapacity(int minCapacity){
        if (minCapacity>array.length){
            grow(minCapacity);
        }
    }
    private void grow(int minCapacity){
        int oldCapacity=array.length;
        int newCapacity=oldCapacity+oldCapacity>>1;
        if (newCapacity<minCapacity){
            newCapacity=minCapacity;
        }
        if (newCapacity>MAX_ARRAY_SIZE){
            if (minCapacity<0){
                throw new OutOfMemoryError();
            }
            newCapacity=minCapacity<MAX_ARRAY_SIZE?MAX_ARRAY_SIZE:Integer.MAX_VALUE;
        }
        array= Arrays.copyOf(array,newCapacity);
    }
    public void add(int value){
        int i=size;
        ensureCapacity(size+1);
        size++;
        if (i==0){
            array[0]=value;
        }else{
            int parent=i;
            while (parent>0){
                parent=(i-1)/2;
                if (array[parent]>value){
                    break;
                }
                array[i]=array[parent];
                i=parent;
            }
            array[i]=value;
        }
    }
    private static Logger logger = Logger.getLogger(FcyPriorityQueue1.class.getName());

    public static void main(String args[]) throws Exception {

    }
}
