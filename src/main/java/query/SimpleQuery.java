package query;

public class SimpleQuery {
    public static void main(String args[]) {
        int[] intArray=new int[]{12,8,3,59,1};
        int queryValue=0;
        boolean exist = query(intArray, queryValue, new AbstractCompare() {
            @Override
            public boolean equal(Object o1, Object o2) {
                if ((int) o1 == (int) o2) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(exist);
    }
    public static boolean query(int[] objects,int o,CompareStrategy strategy){
        for(Object t:objects){
            if (strategy.equal(t,o)){
                return true;
            }
        }
        return false;
    }
}
