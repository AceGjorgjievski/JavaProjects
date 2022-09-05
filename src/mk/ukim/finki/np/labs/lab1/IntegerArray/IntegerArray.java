package mk.ukim.finki.np.labs.lab1.IntegerArray;

import java.util.Arrays;

public class IntegerArray {
    private int [] a;
    private IntegerArray(int [] a, boolean to_copy){
        if(to_copy){
            this.a = a;
        } else {
            this.a = Arrays.copyOf(a,a.length);
        }
    }

    public IntegerArray(int[] a) {
        this.a = Arrays.copyOf(a,a.length);
    }

    public int length(){
        return a.length;
    }

    public int getElementAt(int i){
        return a[i];
    }

    public int sum(){
        int suma = 0;
        for(int i=0; i<a.length; i++){
            suma+=a[i];
        }
        return suma;
    }

    public double average(){
        return (sum() *1.0/length());
    }

    public IntegerArray getSorted(){
        int [] sortedArray = Arrays.copyOf(a,length());
        Arrays.sort(sortedArray);
        return new IntegerArray(sortedArray);
    }

    public IntegerArray concat(IntegerArray ia){
        int [] finalArray = new int[a.length + ia.a.length];
        System.arraycopy(a,0,finalArray,0,a.length);
        System.arraycopy(ia.a,0,finalArray,a.length,ia.a.length);
        return new IntegerArray(finalArray,true);
    }

    @Override
    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
//        for(int i=0;i<a.length;++i){
//            stringBuilder.append(a[i]);
//            stringBuilder.append(", ");
//        }
//        stringBuilder.append("]");
//        return stringBuilder.toString();
        return Arrays.toString(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerArray that = (IntegerArray) o;
        return Arrays.equals(a, that.a);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + Arrays.hashCode(a);
        return result;
    }
}
