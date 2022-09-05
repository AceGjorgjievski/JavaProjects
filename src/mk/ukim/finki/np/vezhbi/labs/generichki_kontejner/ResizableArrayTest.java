package mk.ukim.finki.np.vezhbi.labs.generichki_kontejner;

import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

class ResizableArray<T> {
    private T [] elements;

    public ResizableArray() {
        this.elements = (T[])new Object[0];
    }

    public ResizableArray(T [] elements) {
        this.elements = elements;
    }

    public void addElement(T element) {
        T [] temp = (T[]) new Object[this.elements.length + 1];
        int i;
        for(i=0; i<this.elements.length; i++) {
            temp[i] = this.elements[i];
        }
        temp[i++] = element;
        this.elements = temp;
    }

    public boolean removeElement(T element) {
        boolean found = false;
        T [] temp = (T[]) new Object[this.elements.length - 1];
        int i,j;
        for(i=0,j=0;i<this.elements.length; i++){
            if(this.elements[i] != element) {
                temp[j++] = this.elements[i];
            } else found = true;
        }
        this.elements = temp;

        return found;
    }

    public T[] getElements() {
        return elements;
    }

    public boolean contains(T element) {
        return IntStream.range(0, this.elements.length).anyMatch(i -> this.elements[i].equals(element));
    }

    public Object [] toArray() {
        return Arrays.copyOf(this.elements, this.elements.length);
    }

    public boolean isEmpty() {
        return this.elements.length == 0;
    }

    public int count() {
        return this.elements.length;
    }

    public T elementAt(int ind) {
        if(ind < 0 || ind > this.count()) throw new ArrayIndexOutOfBoundsException();
        return this.elements[ind];
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        IntStream.range(0, src.count()).forEach(i -> dest.addElement(src.elementAt(i)));
    }

    public ResizableArray<T> createObject(T... elements) {
        return new ResizableArray<T>(elements);
    }
}

class IntegerArray extends ResizableArray<Integer> {

    public double sum() {
        return Arrays.stream(this.getElements()).mapToDouble(i -> i.doubleValue()).sum();
    }

    public double mean() {
        return this.sum()/this.count();
    }

    public int countNonZero() {
        return (int)Arrays.stream(this.getElements())
                .filter(i -> i.intValue() != 0)
                .mapToInt(i -> i.intValue())
                .count();
    }

    public IntegerArray distinct() {
        IntegerArray result = new IntegerArray();
        for (int i = 0; i < this.count(); i++) {
            boolean alreadyHaveIt = false;
            for (int j = 0; j < result.count(); j++) {
                if (this.elementAt(i) == result.elementAt(j)) {
                    alreadyHaveIt = true;
                    break;
                }
            }
            if (!alreadyHaveIt) {
                result.addElement(this.elementAt(i));
            }
        }
        return result;
    }

    public IntegerArray increment(int offset) {
        IntegerArray result = new IntegerArray();
        for (int i = 0; i < count(); i++) {
            int current = elementAt(i).intValue();
            result.addElement(current + offset);
        }
        return result;
    }
}

public class ResizableArrayTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}
