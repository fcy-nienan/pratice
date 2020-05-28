package sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class outerSort extends AbstractSort {
    private static final String file="E:\\data";
    private static final String outputPath="E:\\split\\";
    private static final String orderPath="E:\\split\\ordered\\";
    private static final String mergePath="E:\\split\\merge\\";
    private static final long NUM_COUNT=1111111;
    private static final long flush_threold=10000;
    static {
        File file=new File(outputPath);
        file.mkdirs();
        file=new File(orderPath);
        file.mkdirs();
        file=new File(mergePath);
        file.mkdirs();
    }
    public static void main(String args[]) throws Exception {
//        tail(10);
//        prepareFile();
//        splitFile(file,outputPath,10);
        sortAllFile(outputPath);
    }
    private static int[] transferToIntArray(String msg){
        String[] split = msg.split(",");
        int[] intArr=new int[split.length];
        for (int i = 0; i < split.length; i++) {
            intArr[i]=Integer.parseInt(split[i]);
        }
        return intArr;
    }
    private static String transferToString(int[] ints){
        StringBuilder builder=new StringBuilder();
        for (int anInt : ints) {
            builder.append(anInt).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    public static int[] mergeTwoArray(int[] ints1,int[] ints2){
        int[] temp=new int[ints1.length+ints2.length];
        int i=0,j=0,k=0;
        while (i<ints1.length&&j<ints2.length){
            if (ints1[i]<ints2[j]){
                temp[k++]=ints1[i++];
            }else{
                temp[k++]=ints2[j++];
            }
        }
        while (i<ints1.length){
            temp[k++]=ints1[i++];
        }
        while (j<ints2.length){
            temp[k++]=ints2[j++];
        }
        return temp;
    }
    public static void mergeFile(String onePath,String twoPath)throws Exception{
        File file1=new File(onePath);
        File file2=new File(twoPath);
        BufferedReader one=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf-8"));
        BufferedReader two=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"utf-8"));
        File mergeFile=new File(mergePath+file1.getName()+"--merge--"+file2.getName());
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mergeFile),"utf-8"));
        while (true){
            String msg1=one.readLine();
            String msg2=two.readLine();
            if (msg1==null||msg2==null){
                break;
            }
            int[] ints1 = transferToIntArray(msg1);
            int[] ints2 = transferToIntArray(msg2);
            int[] ints = mergeTwoArray(ints1, ints2);
            String s = transferToString(ints);
            writer.write(s);
            writer.newLine();
            writer.flush();
        }
        one.close();
        two.close();
        writer.close();
    }
    public static void sortAllFile(String path) throws Exception {
        File file=new File(path);
        File[] files = file.listFiles();
        for (int i=0;i<files.length;i++) {
            File file1 = files[i];
            if (file1.isFile()){
                sortOneFile(file1.getAbsolutePath(),orderPath,"ordered"+i);
            }
        }
    }
    public static void sortOneFile(String path,String orderPath,String fileName)throws Exception{
        int[] oneFileArray = getOneFileArray(path);
        quickSort(oneFileArray,0,oneFileArray.length-1);
        writeToFile(oneFileArray,orderPath+fileName);
    }
    public static void writeToFile(int[] array,String outPath)throws Exception{
        File file=new File(outPath);
        if (!file.exists())file.createNewFile();
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
        StringBuilder builder=new StringBuilder();
        for (int i : array) {
            builder.append(i).append(",");
            if (builder.length()>flush_threold){
                builder.deleteCharAt(builder.length()-1);
                writer.write(builder.toString());
                writer.write("\r\n");
                writer.flush();
                builder.delete(0,builder.length());
            }
        }
        writer.flush();
        writer.close();

    }
    public static void quickSort(int[] array,int start,int end){
        int low=start,high=end,key=array[start];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            while (low<high&&array[low]<key){
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (low!=start)quickSort(array,start,low);//必须low不能low-1?
        if (high!=end)quickSort(array,low+1,end);
    }
    private static int[] getOneFileArray(String inPath)throws Exception{
        File file=new File(inPath);
        FileInputStream inputStream=new FileInputStream(file);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String msg=null;
        StringBuilder builder=new StringBuilder();
        while ((msg=reader.readLine())!=null){
            builder.append(msg);
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        String[] split = builder.toString().split(",");
        int[] data=new int[split.length];
        for (int i=0;i<split.length;i++){
            data[i]=Integer.parseInt(split[i]);
        }
        return data;
    }
    /*
    * path:原始数据文件路径
    * outputPath:输出路径 E:\\split\\
    * count:拆分行数
    * */
    private static void splitFile(String path,String outputPath,int count)throws Exception{
        File file=new File(path);
        FileInputStream fileInputStream=new FileInputStream(file);
        BufferedReader reader=new BufferedReader(new InputStreamReader(fileInputStream,"utf-8"));
        int inc=0;
        BufferedWriter writer=null;
        String newFileName=file.getName();
        int fileIncre=0;
        String msg=null;
        while ((msg=reader.readLine())!=null){
            if (writer==null||inc==count){
                String newPath=outputPath+newFileName+fileIncre;
                if (writer!=null){
                    writer.flush();
                    writer.close();
                    writer=null;
                    System.out.println("写入文件:"+newPath);
                }
                File newFile=new File(newPath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newPath), "utf-8"));
                }
                inc=0;
                fileIncre++;
            }
            writer.write(msg);
            writer.newLine();
            inc++;
        }
        writer.flush();
        writer.close();
        writer=null;
    }
    private static void prepareFile() throws IOException {
        Random random=new Random();
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fileOutputStream,"utf-8"));
        StringBuilder builder=new StringBuilder();
        for (int i=1;i<NUM_COUNT;i++){
            int x=random.nextInt(Integer.MAX_VALUE);
            builder.append(x+",");
            if (builder.length()>flush_threold){
                builder.deleteCharAt(builder.length()-1);
                writer.write(builder.toString());
                writer.write("\r\n");
                writer.flush();
                builder.delete(0,builder.length());
            }
        }
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
    @Override
    protected void sort(int[] array) {

    }
}
