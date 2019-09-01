package query;

public abstract class AbstractCompare implements CompareStrategy {
    @Override
    public boolean gt(Object o1, Object o2) {
        return false;
    }

    @Override
    public boolean lt(Object o1, Object o2) {
        return false;
    }

    @Override
    public boolean equal(Object o1, Object o2) {
        return false;
    }
}
