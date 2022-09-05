package mk.ukim.finki.np.labs.lab5.ComplexNumber;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


class ComplexNumber<T extends Number,U extends Number> implements Comparable<ComplexNumber<?,?>>{
    private T real;
    private U imag;

    public ComplexNumber(T real, U imag) {
        this.real = real;
        this.imag = imag;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imag;
    }

    public double modul() {
        // z = sqrt(x^2 + y^2)
        return Math.sqrt(Math.pow(real.doubleValue(),2) + Math.pow(imag.doubleValue(),2));
    }

    @Override
    public int compareTo(ComplexNumber<? , ?> other) {
        return Double.compare(this.modul(),other.modul());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%.2f",real.doubleValue()));

        if(imag.doubleValue() >= 0){
            stringBuilder.append(String.format("+%.2fi",imag.doubleValue()));
        } else {
            stringBuilder.append(String.format("-%.2fi",imag.doubleValue()));
        }

        return stringBuilder.toString();
    }
}

public class ComplexNumberTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //testCases simple functions int
            int r = jin.nextInt();
            int i = jin.nextInt();
            ComplexNumber<Integer, Integer> c = new ComplexNumber<Integer, Integer>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if ( k == 1 ) { //testCases simple functions float
            float r = jin.nextFloat();
            float i = jin.nextFloat();
            ComplexNumber<Float, Float> c = new ComplexNumber<Float, Float>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if ( k == 2 ) { //compareTo int
            LinkedList<ComplexNumber<Integer,Integer>> complex = new LinkedList<ComplexNumber<Integer,Integer>>();
            while ( jin.hasNextInt() ) {
                int r = jin.nextInt(); int i = jin.nextInt();
                complex.add(new ComplexNumber<Integer, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if ( k == 3 ) { //compareTo double
            LinkedList<ComplexNumber<Double,Double>> complex = new LinkedList<ComplexNumber<Double,Double>>();
            while ( jin.hasNextDouble() ) {
                double r = jin.nextDouble(); double i = jin.nextDouble();
                complex.add(new ComplexNumber<Double, Double>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if ( k == 4 ) { //compareTo mixed
            LinkedList<ComplexNumber<Double,Integer>> complex = new LinkedList<ComplexNumber<Double,Integer>>();
            while ( jin.hasNextDouble() ) {
                double r = jin.nextDouble(); int i = jin.nextInt();
                complex.add(new ComplexNumber<Double, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
    }
}