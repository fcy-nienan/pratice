package DataStructurePratice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

public class HeapTwo {
    private Comparator comparator;
    private Object[] objects;
    private int size;
    private static final int DEFAULT_SIZE=16;
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE;
    public void add(Object o){
        int i=size;
        if (i>=objects.length){
            grow(i+1);
        }
        size++;
        if (i == 0){
            objects[0]=o;
        }else{
            while (i>0){
                int parent=(i-1)>>>1;
                Object parentObject=objects[parent];
                if (comparator.compare(o,parentObject)>=0){
                    break;
                }
                objects[i]=parentObject;
            }
            objects[i]=o;
        }
    }
    private void grow(int minCapacity){
        int oldCapacity=size;
        int newCapacity=oldCapacity+((oldCapacity<64)?oldCapacity+2:oldCapacity>>1);
        if (newCapacity>MAX_ARRAY_SIZE){
            if (minCapacity<0)
                throw new OutOfMemoryError();
            newCapacity=newCapacity>MAX_ARRAY_SIZE?Integer.MAX_VALUE:MAX_ARRAY_SIZE;
        }
        objects= Arrays.copyOf(objects,newCapacity);
    }
    public static void main(String args[]) throws Exception {
        System.out.println(Integer.MAX_VALUE+1);
        System.out.println(Integer.MAX_VALUE-8+((Integer.MAX_VALUE-8)>>1));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE-8);
        int initCapacity=11;
        int oldCapacity=initCapacity;
        for(int i=0;i<66;i++){
            int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                    (oldCapacity + 2) :
                    (oldCapacity >> 1));
            oldCapacity=newCapacity;
            int reduce=newCapacity-Integer.MAX_VALUE-8;
            System.out.println(newCapacity+":"+reduce);
        }
    }
}
