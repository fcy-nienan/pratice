package sort;

import java.io.*;
import java.util.Random;
import java.util.logging.Logger;

public class outerSort extends abstractSort{
    private static final String file="E:\\data";
    private static final long NUM_COUNT=Long.MAX_VALUE/20/20/20;
    private static final long flush_threold=1000000;
    private static Logger logger = Logger.getLogger(outerSort.class.getName());

    public static void main(String args[]) throws Exception {
        prepareFile();
    }
    private static void prepareFile() throws IOException {
        Random random=new Random();
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        DataOutputStream outputStream=new DataOutputStream(fileOutputStream);
        for(int i=1;i<NUM_COUNT;i++){
            int x=random.nextInt(Integer.MAX_VALUE);
            outputStream.write(x);
            if (i%flush_threold==0){
                outputStream.flush();
//                fileOutputStream.getFD().sync();
                System.out.println(fileOutputStream.getFD().valid());
                System.out.println("add count:"+flush_threold);
            }
        }
        outputStream.close();
    }
    @Override
    protected void sort(int[] array) {

    }
}
