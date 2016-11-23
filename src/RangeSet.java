import java.util.*;

public class RangeSet implements Set<Integer> {


    private static Set<Range> ranges = new HashSet<>();

    public void addRange(int from, int to) {
        Range newRange = new Range(from, to);
        if (ranges.size() == 0) {
            ranges.add(newRange);
        } else {
            for (Range range : ranges) {

                if (range.contains(from) & !(range.contains(to))) {
                    range.setEnd(to);
                }
                if (range.getStart() == to) {
                    range.setStart(from);
                }
                if (range.getEnd() == from) {
                    range.setEnd(to);
                }
                if (!(range.contains(from)) & (range.contains(to))) {
                    range.setStart(from);
                }
            }
            int i = 0;
            for (Range range : ranges) {

                if (!(range.contains(from) & range.contains(to))) {
                    i++;
                }
            }
            if (i == ranges.size()) {
                ranges.add(newRange);
            }
        }
    }

    @Override
    public int size() {
        int i = 0;
        for (Range range : ranges) {
            i++;
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (Range range : ranges) {
            if (range.contains((int) o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        addRange(integer, integer);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Range range : ranges) {
            if (range.contains((int) o)) {
                if((int)o == range.getStart() & (int)o == range.getEnd()){
                    ranges.remove(range);
                    break;
                }
                if((int)o == range.getStart()){
                    range.setStart((int)o+1);
                }
                if((int)o == range.getEnd()){
                    range.setEnd((int)o - 1);
                }
                ranges.add(new Range((int) o + 1, range.getEnd()));
                range.setEnd((int) o - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

}