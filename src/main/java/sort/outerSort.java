package sort;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
public class outerSort extends AbstractSort {
    private static final String root="E:\\outerSort\\";
    private static final String file="E:\\outerSort\\data";
    private static final String outputPath="E:\\outerSort\\split\\";
    private static final String orderPath="E:\\outerSort\\ordered\\";
    private static final String mergePath="E:\\outerSort\\merge\\";
    private static final long NUM_COUNT=1111111;
    private static final long flush_threold=10000;
    public static void init(){
        delete(root);
    }
    private static void deleteFile(File file){
        if (file.isFile())file.delete();
        else{
            File[] files = file.listFiles();
            if (files==null)return;
            for (File file1 : files) {
                deleteFile(file1);
            }
        }
    }
    private static void delete(String path){
        deleteFile(new File(path));
    }
    static {
        init();
        File file=new File(outputPath);
        if (file.exists()){
            file.delete();
        }
        file.mkdirs();
        file=new File(orderPath);
        if (file.exists()){
            file.delete();
        }
        file.mkdirs();
        file=new File(mergePath);
        if (file.exists()){
            file.delete();
        }
        file.mkdirs();

    }
    public static void main(String args[]) throws Exception {
//        tail(10);
        prepareFile();
        splitFile(file, outputPath, 10);
        sortAllFile(outputPath);
        mergeAllFile(orderPath,mergePath);
    }
    public static void mergeAllFile(String inPath,String mergePath) throws Exception {
        File file=new File(inPath);
        File[] files = file.listFiles();
        if (files.length==1){
            System.out.println("合并完成!");
            return;
        }
        int i=0;
        for (;i<files.length;i+=2){
            if (i<file.length()&&(i+1)<file.length()) {
                File oneFile = files[i];
                File twoFile = files[i + 1];
                mergeFile(oneFile.getAbsolutePath(), twoFile.getAbsolutePath(), mergePath);
                oneFile.delete();
                twoFile.delete();
            }else{
                break;
            }
        }
        if (i<files.length){

        }
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
    public static void mergeFile(String onePath,String twoPath,String mergedFilePath)throws Exception{
        System.out.println("开始merge文件:"+onePath+","+twoPath);
        File file1=new File(onePath);
        File file2=new File(twoPath);
        BufferedReader one=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf-8"));
        BufferedReader two=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"utf-8"));
        String mergeFileName=mergedFilePath+file1.getName()+"--merge--"+file2.getName();
        File mergeFile=new File(mergeFileName);
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mergeFile),"utf-8"));
        String msg1,msg2;
        while (true){
            msg1=one.readLine();
            msg2=two.readLine();
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
        while ((msg1=one.readLine())!=null){
            writer.write(msg1);
            writer.newLine();
            writer.flush();
        }
        while ((msg2=two.readLine())!=null){
            writer.write(msg2);
            writer.newLine();
            writer.flush();
        }
        one.close();
        two.close();
        writer.close();
        System.out.println("merge文件完成:"+mergeFileName);
    }
    public static void sortAllFile(String path) throws Exception {
        File file=new File(path);
        File[] files = file.listFiles();
        for (int i=0;i<files.length;i++) {
            File file1 = files[i];
            if (file1.isFile()){
                sortOneFile(file1.getAbsolutePath(),orderPath,file1.getName()+"--ordered--"+i);
            }
        }
    }
    public static void sortOneFile(String path,String orderPath,String fileName)throws Exception{
        System.out.println("开始排序文件:"+path);
        int[] oneFileArray = getOneFileArray(path);
        quickSort(oneFileArray,0,oneFileArray.length-1);
        writeToFile(oneFileArray,orderPath+fileName);
        System.out.println("文件排序完毕:"+orderPath+fileName);
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
        System.out.println("开始拆分文件:"+path);
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
        System.out.println("文件拆分完毕:"+outputPath+"("+fileIncre+")");
    }
    public static void prepareFile() throws IOException {
        System.out.println("开始生成文件");
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
        System.out.println("文件生成完毕:"+file);
    }
    @Override
    protected void sort(int[] array) {

    }
}
