package mk.ukim.finki.np.vezhbi.labs.generici.listaCeliBroevi;

import java.util.*;
import java.util.stream.Collectors;


class IntegerList{
    private List<Integer> integerList;

    public IntegerList() {
        integerList = new ArrayList<>();
    }

    public IntegerList(Integer... numbers) {
        this.integerList.addAll(Arrays.asList(numbers));
    }

    public void add(int element, int index) {
        if(index > this.integerList.size()) {
            for(int i=this.integerList.size(); i<index; i++) {
                this.integerList.add(0);
            }
        }
        this.integerList.add(index,element);
    }

    public boolean validIndex(int index){
        return index >= 0 || index < this.integerList.size();
    }

    public int remove(int index) {
        if(!validIndex(index)) throw new ArrayIndexOutOfBoundsException();
        return this.integerList.remove(index);
    }

    public void set(int element, int index) {
        if(!validIndex(index)) throw new ArrayIndexOutOfBoundsException();
        this.integerList.add(index,element);
    }

    public int get(int index) {
        if(!validIndex(index)) throw new ArrayIndexOutOfBoundsException();
        return this.integerList.get(index);
    }

    public int size() {
        return this.integerList.size();
    }

    public int count(int element) {
        return (int)this.integerList.stream()
                .filter(i -> i.equals(element))
                .count();
    }

    public void removeDuplicates(){
        this.integerList = integerList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public int sumFirst(int k) {
        return this.integerList.stream()
                .limit(k)
                .mapToInt(i -> i)
                .sum();
    }

    public int sumLast(int k){
        return this.integerList.stream()
                .skip(k)
                .mapToInt(i -> i)
                .sum();
    }

    public IntegerList addValue(int value) {
        IntegerList newList = new IntegerList();
        this.integerList.stream()
                .forEach(i -> newList.add(this.integerList.get(i)+value,this.integerList.indexOf(i)));
        return newList;
    }

    public void shiftLeft(int ind, int k) {
        if(!validIndex(k)) throw new ArrayIndexOutOfBoundsException();
        if(!validIndex(ind)) throw new ArrayIndexOutOfBoundsException();

        // 1 2 3 4, (2,3) 3 % 4 = 3
        //1 3 4 2

        int newInd = ind - (k % size());
        if( newInd < 0){
            newInd += size();
        }
        int element = this.integerList.remove(ind);

        this.integerList.add(newInd,element);


    }

    public void shiftRight(int ind, int k) {
        if(!validIndex(k)) throw new ArrayIndexOutOfBoundsException();
        if(!validIndex(ind)) throw new ArrayIndexOutOfBoundsException();

        // 1 2 3 4, (1,2) 3 % 4 = 3
        //1 3 4

        int newInd = (ind + k) % size();
        int element = this.integerList.remove(ind);
        this.integerList.add(newInd,element);

    }
}

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}
