package mk.ukim.finki.np.vezhbi.za_juni.list_whole_nums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerList {
    private List<Integer> integers;

    public IntegerList() {
        this.integers = new ArrayList<>();
    }

    public IntegerList(Integer ...numbers) {
        this.integers = new ArrayList<>();
        Collections.addAll(this.integers,numbers);
    }

    public void add(int el, int ind) {
        if (ind > this.integers.size()) {
            int length = ind - this.integers.size();
            for (int i = 0; i < length; i++) {
                this.integers.add(0);
            }
            this.integers.add(el);
        } else {
            this.integers.add(ind, el);
        }
    }

    public int remove(int ind) {
        return this.integers.remove(ind);
    }

    public void set(int el, int ind) {
        this.integers.set(ind, el);
    }

    public int get(int ind) {
        return this.integers.get(ind);
    }

    public int size() {
        return this.integers.size();
    }

    public int count(int el) {
        return (int)this.integers.stream()
                .filter(i -> i.equals(el))
                .count();
    }

    public void removeDuplicates() {
        this.integers.stream()
                .distinct().collect(Collectors.toList());
    }

    public int sumFirst(int k) {
        return this.integers.stream()
                .limit(k)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int sumLast(int k) {
        return this.integers.stream()
                .mapToInt(Integer::intValue)
                .skip(this.integers.size()-k)
                .sum();
    }

    public void shiftRight(int idx, int k) {

        int newInd = (idx + k) % size();
        int element = this.integers.remove(idx);
        this.integers.add(newInd,element);
    }

    public void shiftLeft(int idx, int k) {
        int newInd = idx - (k % size());
        if( newInd < 0){
            newInd += size();
        }
        int element = this.integers.remove(idx);

        this.integers.add(newInd,element);
    }

    public IntegerList addValue(int value) {
        return new IntegerList(this.integers
                .stream()
                .map(i -> i + value)
                .toArray(Integer[]::new));
    }
}
