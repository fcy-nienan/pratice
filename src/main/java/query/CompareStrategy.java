package query;

public interface CompareStrategy {
    boolean gt(Object o1,Object o2);
    boolean lt(Object o1,Object o2);
    boolean equal(Object o1,Object o2);
}
