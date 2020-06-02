package sort;

import CommonUtil.IOUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class BitSetSort {
    private static Logger logger = Logger.getLogger(BitSetSort.class.getName());

    public static void main(String args[]) throws Exception {
//        computeNeedSize(1750000000);
        int[] x=new int[10];
        System.arraycopy(x,0,x,0,111);
//        long[] x=new long[50000000];
//        BitSetSortDemo();
    }
    public static void fillBitSet(BitSet bitSet,HashMap<Integer,Integer> map,int[] array) throws InterruptedException {
        try {
            for (int i = 0; i < array.length; i++) {
                int value = array[i];
                if (bitSet.get(value)) {
                    if (map.get(value) != null) {
                        map.put(value, map.get(value) + 1);
                    } else {
                        map.put(value, 1);
                    }
                } else {
                    bitSet.set(value);
                }
            }
        }catch (Throwable t){
            System.out.println(bitSet.size());
            System.out.println(bitSet.length());
            t.printStackTrace();
            Thread.sleep(1000000);
            System.exit(0);
        }
    }
    public static void computeNeedSize(long maxValue){
        System.out.println("最大数字:"+maxValue);
        System.out.println("long数组所需大小:"+maxValue/64);
        System.out.println("long数组大小:"+maxValue/8/1024+"KB");
        System.out.println("long数组大小:"+maxValue/8/1024/1024+"MB");
        System.out.println("long数组大小:"+maxValue/8/1024/1024/1024+"GB");

    }
    static String  base = "string";
    public static void BitSetSortDemo() throws IOException, InterruptedException {
        BitSet bitSet=new BitSet();
        HashMap<Integer,Integer> map=new HashMap();
        String path="E:\\outerSort\\data";
        long maxValue=maxValue(path);
        System.out.println(maxValue);
        computeNeedSize(maxValue);

        BufferedReader reader= IOUtil.BufferedReader(path);
        String msg=null;
        while ((msg= reader.readLine())!=null){
            if (!"".equals(msg)){
                int[] ints = transferToIntArray(msg);
                msg=null;
                fillBitSet(bitSet,map,ints);
            }
        }

        System.out.println(bitSet.size());
        forBitSet1(bitSet,map);
    }
    public static long maxValue(String path) throws IOException {
        BufferedReader reader= IOUtil.BufferedReader(path);
        String msg=null;
        long maxValue=Long.MIN_VALUE;
        while ((msg=reader.readLine())!=null){
            long[] ints=transferToLongArray(msg);
            msg=null;
            for (long anInt : ints) {
                if (maxValue<anInt){
                    maxValue=anInt;
                }
            }
        }
        return maxValue;
    }
    public static void forBitSet1(BitSet bitSet,HashMap<Integer,Integer> map){
        StringBuilder builder=new StringBuilder();
        int i=bitSet.nextSetBit(0);
        if (i==-1)return;
        builder.append(i);
        int sum=1;
        while (true){
            i++;
            i= bitSet.nextSetBit(i);
            if (i<0)break;
            if (map.containsKey(i)){
                int count=map.get(i);
                for (int j=0;j<count;j++){
                    builder.append(",").append(i);
                    sum++;
                }
            }
            builder.append(",").append(i);
            sum++;
        }
        System.out.println(sum);
        System.out.println(builder.length());
    }
    public static void forBitSet(BitSet bitSet){

        StringBuilder builder=new StringBuilder();
        builder.append('{');

        int i = bitSet.nextSetBit(0);
        int sum=0;
        if (i != -1) {
            int len=10;
            builder.append(i);
            while (true) {
                if (++i < 0) break;
                if ((i = bitSet.nextSetBit(i)) < 0) break;
                int endOfRun = bitSet.nextClearBit(i);
                do {
                    builder.append(", ").append(i);
                    sum++;
                } while (++i != endOfRun);
                len--;
//                if (len<0)break;
            }
        }
        System.out.println(sum);
//        System.out.println(builder.toString());
    }
    //将一个字符串转化为一个int数组
    private static int[] transferToIntArray(String msg){
        String[] split = msg.split(",");
        int[] intArr=new int[split.length];
        for (int i = 0; i < split.length; i++) {
            intArr[i]=Integer.parseInt(split[i]);
        }
        return intArr;
    }
    private static long[] transferToLongArray(String msg){
        String[] split=msg.split(",");
        long[] longArr=new long[split.length];
        for (int i=0;i<longArr.length;i++){
            longArr[i]=Long.parseLong(split[i]);
        }
        return longArr;
    }
}
