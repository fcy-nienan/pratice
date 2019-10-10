package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class abstractSort {
    private int sortCount=10000;
    private int arrayLen=10000;
    protected List<String> disOrderList=new ArrayList();
    protected List<String> sequenceList=new ArrayList();
    protected List<String> reserveList=new ArrayList();
    protected List<int[]> sortedList=new ArrayList<>();
    public abstractSort sortCount(int sortCount){
        this.sortCount=sortCount;
        return this;
    }
    public int getArrayLen(){
        return this.arrayLen;
    }
    public abstractSort arrayLen(int arrayLen){
        this.arrayLen=arrayLen;
        return this;
    }
    protected abstract void sort(int[] array);
    public void execute(){
        long start=System.nanoTime();
        long mstart=System.currentTimeMillis();
        int sequenceOrder=0,reserveOrder=0,disOrder=0,sortCount=this.sortCount,arrayLen=this.arrayLen;
        for(int i=0;i<sortCount;i++) {
            int[] array = sortUtil.produceArray(arrayLen);
            sort(array);
            sortedList.add(array);
        }
        long end=System.nanoTime();
        long mend=System.currentTimeMillis();
        for(int[] array:sortedList) {
            int orderType = sortUtil.checkArray2(array);
            if (orderType == 0) {
                disOrder++;
                disOrderList.add(Arrays.toString(array));
            } else if (orderType == 1) {
                sequenceOrder++;
                sequenceList.add(Arrays.toString(array));
            } else {
                reserveOrder++;
                reserveList.add(Arrays.toString(array));
            }
        }
        System.out.printf("array len: %s --  total count: %s --  sequence order: %s --  reserve order: %s  --  disorder: %s  \r\n",arrayLen,sortCount,sequenceOrder,reserveOrder,disOrder);
        System.out.printf("cost time: %s nano  %s  million \r\n",(end-start),(mend-mstart));
    }
}
