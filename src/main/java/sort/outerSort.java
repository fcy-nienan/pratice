package sort;
import CommonUtil.FileUtil;
import CommonUtil.IOUtil;
import CommonUtil.StartWatch;
import com.sun.scenario.effect.Merge;

import java.io.*;
import java.util.Random;
import java.util.concurrent.*;

public class outerSort extends AbstractSort {
    private static final String root="E:\\outerSort\\";//根目录
    private static final String file="E:\\outerSort\\data";//原始文件
    private static final String splitPath="E:\\outerSort\\split\\";//拆分后文件放置的目录
    private static final String orderPath="E:\\outerSort\\ordered\\";//单个文件排序后放置的目录
    private static final String mergePath="E:\\outerSort\\merge\\";//最后归并的目录
    private static boolean init=true;//初始化并清空相关目录
    private static final int NUM_COUNT=11111111;//生成的原始文件数字个数
    private static final int LINE_COUNT=400;//每行数字个数
    private static final int splitLine=50;//拆分文件时多少行拆分为一个文件
    private static ThreadPoolExecutor executor=new ThreadPoolExecutor(30,100,100, TimeUnit.SECONDS,new ArrayBlockingQueue(100));
    static {
//        init=false;
        if (init) {
            FileUtil.delete(root);
            File file = new File(splitPath);
            file.mkdirs();
            file = new File(orderPath);
            file.mkdirs();
            file = new File(mergePath);
            file.mkdirs();
        }

    }
    public static void main(String args[]) throws Exception {
        StartWatch instance = StartWatch.instance();
        instance.init();
        PrepareFile.PrepareFile(Integer.MAX_VALUE,NUM_COUNT,LINE_COUNT);
        instance.cost("prepareFile");
        SplitFile.splitFile(file, splitPath, splitLine);
        instance.cost("splitFile");
        SortFile.sortAllFile(splitPath,LINE_COUNT,4);
        instance.cost("sortAllFile");
        Rename.cpDirAndRename(orderPath,mergePath);
        instance.cost("cpDirAndRename");
        MergeFile.mergeAllFile(mergePath,0,0);
        instance.cost("mergeAllFile");
    }
    //一些验证的方法
    private static class Verify{
        //验证排序结果是否正确
        private static void valid(String inpath) throws IOException {
            BufferedReader reader=IOUtil.BufferedReader(inpath);
            String msg="";
            int pre=0;
            long lines=0;
            while ((msg=reader.readLine())!=null){
                if (msg.equals(""))continue;
                int[] ints = OtherTool.transferToIntArray(msg);
                lines++;
                for (int i=0;i<ints.length;i++){
                    if (ints[i]<pre){
                        System.out.println("验证结果:排序错误!"+lines+"-"+ints[i]+"-"+pre);
                        return;
                    }
                    pre=ints[i];
                }
            }
            System.out.println("验证结果:排序正确!");
        }
        //统计目录下所有文件中数字总数
        private static long checkDirCount(String dir) throws IOException {
            File file=new File(dir);
            File[] files = file.listFiles();
            long count=0;
            if (files!=null){
                for (File file1 : files) {
                    count+=checkCount(file1.getAbsolutePath());
                }
            }
            return count;
        }
        //统计单个文件中数字总数
        private static long checkCount(String inPath) throws IOException {
            BufferedReader reader=IOUtil.BufferedReader(inPath);
            String msg=null;
            long count=0;
            while ((msg=reader.readLine())!=null){
                if (msg.equals(""))continue;
                count+=msg.split(",").length;
            }
            IOUtil.closeStream(reader);
            return count;
        }
    }
    //其他工具
    private static class OtherTool{
        private static String getMergePath(){
            return new File(mergePath).listFiles()[0].getAbsolutePath();
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
        //将一个int数组指定区间的数组转化为字符串
        private static String transferToString(int[] ints,int start,int end){
            StringBuilder builder=new StringBuilder();
            for (int i=start;i<end;i++){
                builder.append(ints[i]).append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            return builder.toString();
        }
        private static void writeAndFlush(BufferedWriter writer,String s) throws IOException {
            writer.write(s);
            writer.newLine();
            writer.flush();
        }
        private static void clearBuildAndFlush(StringBuilder builder,BufferedWriter writer) throws IOException {
            builder.deleteCharAt(builder.length()-1);
            writeAndFlush(writer,builder.toString());
            builder.delete(0,builder.length());
        }
    }
    //封装一个BufferedReader,通过nextInt方法读取下一个数字
    private static class MergedBufferedReader{
        private BufferedReader reader;
        private String msg;
        private int[] array;
        private int index=0;
        public static final int FINISHED=-999999999;
        public MergedBufferedReader(BufferedReader reader){
            this.reader=reader;
        }
        public int nextInt() throws IOException {
            if (array==null||index==array.length){
                index=0;
                msg=reader.readLine();
                if (msg==null)return FINISHED;
                while (msg.equals("")){
                    msg=reader.readLine();
                }
                array=OtherTool.transferToIntArray(msg);
            }
            return array[index++];
        }
        public void close() throws IOException {
            this.reader.close();
        }
    }

    private static class Rename{
        //排序后的文件命名格式
        public static String orderName(String splitName){
            return "order-"+getSplitId(splitName);
        }
        //通过排序后的文件名提取id
        public static String orderId(String orderName){
            return orderName.split("-")[1];
        }
        //拆分文件命名
        private static String splitName(int id){
            return "split-"+id;
        }
        //通过拆分文件名提取id
        private static String getSplitId(String name){
            return name.split("-")[1];
        }
        //merge文件命名格式
        private static String getMergeFileName(int level,int id){
            return "merge-"+level+"-"+id;
        }
        //将排好序的文件移动到merge目录并且重命名
        public static void cpDirAndRename(String inDir,String outDir) throws IOException {
            FileUtil.cpDir(inDir,outDir);
            File file=new File(outDir);
            File[] files = file.listFiles();
            if (files!=null){
                for (File file1 : files) {
                    String newName=file1.getParent()+"\\merge-"+Rename.orderId(file1.getName());
                    file1.renameTo(new File(newName));
                }
            }
        }
    }



    private static class MergeFile{
        //递归合并所有文件(level 和 id 是用来给文件命名的)
        public static int mergeAllFile(String mergePath,int level,int id) throws Exception {
            File file=new File(mergePath);
            File[] files = file.listFiles();
            if (files.length==1){
                System.out.println("合并完成!");
                return 0;
            }else {
                //两个两个文件合并
                int i = 0;
                for (; i < files.length; i += 2) {
                    if (i < files.length && (i + 1) < files.length) {
                        File oneFile = files[i];
                        File twoFile = files[i + 1];
                        mergeFile(oneFile.getAbsolutePath(), twoFile.getAbsolutePath(), mergePath,level,id++);
                        FileUtil.delete(oneFile);
                        FileUtil.delete(twoFile);
                    } else {
                        break;
                    }
                }
                return mergeAllFile(mergePath,level+1,0);
            }
        }

        //归并排序合并两个文件
        public static void mergeFile(String onePath,String twoPath,String mergedFilePath,int level,int id)throws Exception{
            File file1=new File(onePath);
            File file2=new File(twoPath);
            MergedBufferedReader one=new MergedBufferedReader(IOUtil.BufferedReader(file1));
            MergedBufferedReader two=new MergedBufferedReader(IOUtil.BufferedReader(file2));
            String mergeFileName=Rename.getMergeFileName(level,id);
            BufferedWriter writer=IOUtil.BufferedWriter(mergedFilePath+mergeFileName);
            int[] temp=new int[(int) LINE_COUNT];
            int k=0;
            int x=one.nextInt(),y=two.nextInt();
            while (true){
                if (x==MergedBufferedReader.FINISHED||y==MergedBufferedReader.FINISHED){
                    break;
                }
                if (k==temp.length){
                    OtherTool.writeAndFlush(writer,OtherTool.transferToString(temp,0,k));
                    k=0;
                    temp=new int[(int)LINE_COUNT];
                }
                if (x<=y){
                    temp[k++]=x;
                    x=one.nextInt();
                }else{
                    temp[k++]=y;
                    y=two.nextInt();
                }
            }
            while (x!=MergedBufferedReader.FINISHED){
                if (k==temp.length){
                    OtherTool.writeAndFlush(writer,OtherTool.transferToString(temp,0,k));
                    k=0;
                    temp=new int[(int)LINE_COUNT];
                }
                temp[k++]=x;
                x=one.nextInt();
            }
            while (y!=MergedBufferedReader.FINISHED){
                if (k==temp.length){
                    OtherTool.writeAndFlush(writer,OtherTool.transferToString(temp,0,k));
                    k=0;
                    temp=new int[(int)LINE_COUNT];
                }
                temp[k++]=y;
                y=two.nextInt();
            }
            if (k!=0){
                OtherTool.writeAndFlush(writer,OtherTool.transferToString(temp,0,k));
            }
            IOUtil.closeStream(writer);
            one.close();
            two.close();
        }

    }


    private static class SortFile{
        private static class SortTask implements Callable<Long> {
            private File[] files;
            private int lineCount;
            private int start;
            private int end;
            private CountDownLatch latch;
            public SortTask(File[] files,int start,int end,int lineCount,CountDownLatch latch){
                this.files=files;
                this.lineCount=lineCount;
                this.start=start;
                this.end=end;
                this.latch=latch;
            }
            @Override
            public Long call() {
                long count=0;
                if (files!=null){
                    for(int i=start;i<end;i++){
                        File file1=files[i];
                        if (file1.isFile()){
                            try {
                                count+=sortOneFile(file1.getAbsolutePath(),orderPath,Rename.orderName(file1.getName()),lineCount);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                latch.countDown();
                return count;
            }

        }
        //排序所有文件
        public static void sortAllFile(String path,int lineCount,int threadNum) throws Exception {
            File file=new File(path);
            File[] files = file.listFiles();
            long numCount=0;
            int everyTask=(files.length%threadNum==0)?files.length/ threadNum:files.length/threadNum+1;
            CountDownLatch latch=new CountDownLatch(threadNum);
            int start=0,end=start+everyTask;
            for (int i=0;i<threadNum;i++){

                executor.submit(new SortTask(files,start,end,lineCount,latch));
            }
            latch.await();
//            for (int i=0;i<files.length;i++) {
//                File file1 = files[i];
//                if (file1.isFile()){
//                    numCount+=sortOneFile(file1.getAbsolutePath(),orderPath,Rename.orderName(file1.getName()),lineCount);
//                }
//            }
            System.out.println("排序完成-----------总数字:"+numCount);
        }


        //通过快排排序拆分后的单个文件,并将排序后的内容写入到另一个文件(必须保证拆分后的单个文件能全部放入内存,通过拆分行数控制拆分文件大小)
        private static int sortOneFile(String path,String orderPath,String fileName,int lineCount)throws Exception{
            int[] oneFileArray = getOneFileArray(path);
            quickSort(oneFileArray,0,oneFileArray.length-1);
            writeToFile(oneFileArray,orderPath+fileName,lineCount);
            return oneFileArray.length;
        }
        //将int数组内容以都好分割拼接并写入文件,LINE_COUNT控制一行多少个数字
        private static void writeToFile(int[] array,String outPath,int lineCount)throws Exception{
            File file=new File(outPath);
            if (!file.exists())file.createNewFile();
            BufferedWriter writer=IOUtil.BufferedWriter(file,"utf-8");
            StringBuilder builder=new StringBuilder();
            int count=0;
            for (int i : array) {
                builder.append(i).append(",");
                count++;
                if (count==lineCount){
                    OtherTool.clearBuildAndFlush(builder,writer);
                    count=0;
                }
            }
            writer.write(builder.toString());
            writer.flush();
            IOUtil.closeStream(writer);
        }
        //快排
        private static void quickSort(int[] array,int start,int end){
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
            if (low!=start)quickSort(array,start,low);
            if (high!=end)quickSort(array,low+1,end);
        }
        //获取单个文件的内容
        private static int[] getOneFileArray(String inPath)throws Exception{
            BufferedReader reader=IOUtil.BufferedReader(inPath,"utf-8");
            String msg=null;
            StringBuilder builder=new StringBuilder();
            while ((msg=reader.readLine())!=null){
                if (msg.equals(""))continue;
                builder.append(msg);
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
            int[] data=OtherTool.transferToIntArray(builder.toString());
            IOUtil.closeStream(reader);
            return data;
        }
    }


    private static class SplitFile{
        /*
         * path:原始数据文件路径
         * outputPath:输出路径 E:\\split\\
         * count:多少行拆分为一个文件
         * */
        private static void splitFile(String path,String outputPath,int count)throws Exception{
            System.out.println("开始拆分文件:"+path+":每"+count+"行拆分为一个文件!");
            File file=new File(path);
            FileInputStream fileInputStream=new FileInputStream(file);
            BufferedReader reader=new BufferedReader(new InputStreamReader(fileInputStream,"utf-8"));
            int inc=0;
            BufferedWriter writer=null;
            int fileIncre=0;
            String msg=null;
            while ((msg=reader.readLine())!=null){
                if (msg.equals(""))continue;
                if (writer==null||inc==count){
                    String newName=Rename.splitName(fileIncre);
                    String newPath=outputPath+newName;
                    if (writer!=null){
                        writer.flush();
                        writer.close();
                        writer=null;
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
            System.out.println("文件拆分完毕:"+outputPath+"("+fileIncre+")");
        }
    }


    private static class PrepareFile{
        //造数据
        public static void PrepareFile(int maxValue,int numCount,int lineCount) throws IOException {
            System.out.println("开始生成文件");
            Random random=new Random();
            BufferedWriter writer = IOUtil.BufferedWriter(file, "utf-8");
            StringBuilder builder=new StringBuilder();
            int count=0,totalLines=0;
            for (int i=1;i<=numCount;i++){
                int x=random.nextInt(maxValue);
                builder.append(x+",");
                count++;
                if (count==lineCount){
                    OtherTool.clearBuildAndFlush(builder,writer);
                    totalLines++;
                    count=0;
                }
            }
            if (builder.length()>0) {
                writer.write(builder.toString());
                totalLines++;
            }
            writer.flush();
            IOUtil.closeStream(writer);
            System.out.println("文件生成完毕:"+file+":生成数字总数:"+NUM_COUNT+":总行数:"+totalLines+":每行数字个数:"+LINE_COUNT);
        }
    }
    @Override
    protected void sort(int[] array) {

    }
}
