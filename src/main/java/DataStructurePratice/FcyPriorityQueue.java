package DataStructurePratice;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

public class FcyPriorityQueue {
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY=16;
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
    public FcyPriorityQueue(){
        this(DEFAULT_CAPACITY);
    }
    public FcyPriorityQueue(int capacity){
        if (capacity>=0){
            this.array=new int[capacity];
        }else{
            throw new IllegalArgumentException("capacity can not be zero!");
        }
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
            if (minCapacity<0)
                throw new OutOfMemoryError();
            newCapacity=minCapacity<MAX_ARRAY_SIZE?MAX_ARRAY_SIZE:Integer.MAX_VALUE;
        }
        array=Arrays.copyOf(array,newCapacity);
    }
    public int poll(){
        int result=array[0];
        int lastValue=array[size-1];
        array[size-1]=Integer.MIN_VALUE;
        size--;
        int i=0,min=i*2+1;
        while (min<size){
            if (min+1<size&&array[min]>array[min+1]){
                min=min+1;
            }
            array[i]=array[min];
            i=min;
            min=i*2+1;
        }
        array[i]=lastValue;
        return result;
    }
    public void add(int value){
        int i=size;
        ensureCapacity(i+1);
        size++;
        if (i==0){
            array[0]=value;
        }else{
            while (i>0){
                int parent=(i-1)>>>1;
                if (value<array[parent]){
                    array[i]=array[parent];
                    i=parent;
                }else{
                    break;
                }
            }
            array[i]=value;
        }
    }
    public void display(){
        for(int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
    }
    public static void main(String args[]) throws Exception {
        FcyPriorityQueue queue=new FcyPriorityQueue();
        Random random=new Random();
        for(int i=0;i<100;i++){
            int k=random.nextInt(1000);
            queue.add(k);
        }
        for(int i=0;i<100;i++){
            System.out.print(queue.poll()+"\t");
            if (i%8==0){
                System.out.println();
            }
        }
        System.out.println();
        queue.display();

    }
}
