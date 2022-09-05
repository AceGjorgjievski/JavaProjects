package mk.ukim.finki.np.labs.lab5.IntegerList;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class IntegerList {
    private List<Integer> list;

    public IntegerList() {
        this.list = new ArrayList<>();
    }

    public IntegerList(Integer... numbers) {
        this.list = new ArrayList<>(Arrays.asList(numbers));
    }
    public boolean validIndex(int ind){
        if(ind < 0 || ind > list.size()) throw new ArrayIndexOutOfBoundsException();
        else return true;
    }

    public void add(int element, int ind) {
        if(ind > list.size()){
            IntStream.range(list.size(), ind)
                    .forEach(i -> list.add(0));
        }
        
        list.add(ind,element);
    }

    public int remove(int ind) {
        validIndex(ind);
        return list.remove(ind);
    }

    public int count(int element) {//////
        return (int) list.stream()
                .filter(i -> i.equals(element))
                .count();
    }

    public void removeDuplicates() {

//        for(int i=0; i< list.size()-1; i++){
//            Integer integer = list.get(i);
//            for(int j=i+1; j< list.size(); j++){
//                if(integer.equals(list.get(j))){
//                    list.remove(j);
//                }
//            }
//        }

        list = list.stream()
                .distinct()
                .collect(Collectors.toList());


    }

    public IntegerList addValue(int value) {
        validIndex(value);
        IntegerList newList = new IntegerList();
        this.list.stream()
                .forEach(i -> newList.add(i.intValue() + value,list.indexOf(i)));
        return newList;
    }

    public void shiftLeft(int ind, int k) {
        validIndex(ind);
        validIndex(k);

        // 1 2 3 4, (2,3) 3 % 4 = 3
        //1 3 4 2

        int newInd = ind - (k % size());
        if( newInd < 0){
            newInd += size();
        }
        int element = list.remove(ind);

        list.add(newInd,element);


    }

    public void shiftRight(int ind, int k) {

        validIndex(ind);
        validIndex(k);

        // 1 2 3 4, (1,2) 3 % 4 = 3
        //1 3 4

        int newInd = (ind + k) % size();
        int element = list.remove(ind);
        list.add(newInd,element);

    }

    public int sumFirst(int k) {
        validIndex(k);
        return list.subList(0,k)
                .stream()
                .mapToInt(i -> i.intValue())
                .sum();
    }

    public int sumLast(int k) {
        validIndex(k);
        return list.subList(size() - k, size())
                .stream()
                .mapToInt(i -> i.intValue())
                .sum();
    }

    public int size() {
        return list.size();
    }

    public int get(int ind) {
        validIndex(ind);
        return list.get(ind);
    }

}

public class IntegerListTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //testCases standard methods
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
        if ( k == 1 ) { //testCases count,remove duplicates, addValue
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
        if ( k == 2 ) { //testCases shiftRight, shiftLeft, sumFirst , sumLast
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
