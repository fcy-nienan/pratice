package DataStructurePratice;

import java.util.*;
import java.util.logging.Logger;

public class HeapOne {
    private Object[] objects;
    private int size;
    private Comparator comparator;
    private static final int DEFAULT_CAPACITY=11;
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE-8;
    public HeapOne(Comparator comparator){
        this.comparator=comparator;
        this.objects=new Object[DEFAULT_CAPACITY];
    }
    private void grow(int minCapacity){
        int oldCapacity=objects.length;
        int newCapacity=oldCapacity+((oldCapacity<64?oldCapacity+2:oldCapacity>>1));
        if (newCapacity>MAX_ARRAY_SIZE){//新容量大于最大数组
            newCapacity=hugeCapacity(minCapacity);
        }
        objects=Arrays.copyOf(objects,newCapacity);
    }
    private int hugeCapacity(int minCapacity){
        if (minCapacity<0)
            throw new OutOfMemoryError();
        return minCapacity>MAX_ARRAY_SIZE?Integer.MAX_VALUE:MAX_ARRAY_SIZE;
    }
//    最大堆      调整,如果当前节点大于该节点的父节点,则该节点和父节点交换位置
    public void add(Object o){
        int len=objects.length;
        int oldSize=size;
        if (oldSize>=len){
            grow(oldSize+1);
        }
        size++;
        if (oldSize==0){
            objects[0]=o;
        }else {
            while(oldSize>0){
                int parent=(oldSize-1)>>>1;
                Object parentObject=objects[parent];
                if (comparator.compare(o,parentObject)<0){
                    break;
                }
                objects[oldSize]=parentObject;
                oldSize=parent;
            }
            objects[oldSize]=o;
        }
    }
    private int left(int i){
        return i<<1;
    }
    private int right(int i){
        return (i<<1)+1;
    }
    private int parent(int i){
        if (i==0){
            throw new IllegalArgumentException();
        }
        return (i-1)/2;
    }
    public void display(){
        System.out.println(Arrays.toString(objects));
    }

    public static void main(String args[]) throws Exception {
        HeapOne one=new HeapOne(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof ProcessStruct && o2 instanceof ProcessStruct){
                    int o1Priority=((ProcessStruct) o1).getPriority();
                    int o2Priority=((ProcessStruct) o2).getPriority();
                    return o1Priority-o2Priority;
                }
                return 0;
            }
        });
        Random random=new Random();
        List<ProcessStruct> list=new ArrayList();
        for(int i=0;i<10;i++){
            int x=random.nextInt(100);
            list.add(new ProcessStruct(x));
        }
        for(int i=0;i<list.size();i++){
            one.add(list.get(i));
        }
        one.display();
    }
    static class ProcessStruct{
        private int priority;
        public ProcessStruct(int priority){
            this.priority=priority;
        }
        public int getPriority(){
            return priority;
        }

        @Override
        public String toString() {
            return String.valueOf(priority);
        }
    }
}
