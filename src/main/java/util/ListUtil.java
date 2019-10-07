package util;

import java.util.List;

public class ListUtil {
    public static void main(String args[]) {

    }
    public static void disList(List list){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public static void disListLen(List list,int len){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            if (i>=len){
                return;
            }
        }
    }

}
