package mk.ukim.finki.vezhbi.napredno.vzhb1;

import java.util.Scanner;

abstract class Assignment{
    private boolean isTrue;

    public Assignment(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public abstract void printResult();
    public abstract double returnValue();
    public abstract int returnNumber();
}

class Task extends Assignment{
    private String testName;
    private double aValue;
    private int aNumber;

    public Task(boolean isTrue) {
        super(isTrue);
    }

    public void printResult(){
        System.out.println("hello");
    }
    public double returnValue(){
        return aValue;
    }
    public int returnNumber(){
        return 2;
    }
}

interface X
{
public void myMethod();
}
interface Y
{
    public void myMethod();
}
class Main implements X, Y
{
    public void myMethod()
    {
        System.out.println("Implementing more than one interfaces");
    }
    public static void main(String args[]){
//        mk.ukim.finki.aps.juni.Ispiti.zad2Zagaduvanje.Main obj = new mk.ukim.finki.aps.juni.Ispiti.zad2Zagaduvanje.Main();
//        //obj.myMethod();
//
//        Scanner s = new Scanner(System.in);
//        int num = s.nextInt();
//        System.out.println(num);

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] niza = new int[n];
        for(int i=0; i<n; i++){
            niza[i] = sc.nextInt();
        }

        int sum = 0;
        for(int i=0; i<n; i++){
            sum += niza[i] * niza[i];
        }

    }
}
