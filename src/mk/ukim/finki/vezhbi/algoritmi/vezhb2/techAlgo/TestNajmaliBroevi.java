package mk.ukim.finki.vezhbi.algoritmi.vezhb2.techAlgo;

import mk.ukim.finki.aps.kodovi.kod1_DllSllArray.Array;

import java.util.HashMap;
import java.util.Map;

class NajmaliBroevi {
    public int a;
    public int b;

    public NajmaliBroevi() {
    }

    public NajmaliBroevi(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.format("%d %d",a,b);
    }
}

public class TestNajmaliBroevi {

    public static NajmaliBroevi findMinNumber(int [] array,int l, int r) {
        if(l == r) {
            return new NajmaliBroevi(array[l],Integer.MAX_VALUE);
        }

        int mid = (l + r) / 2;
        NajmaliBroevi r1 = findMinNumber(array,l,mid);
        NajmaliBroevi r2 = findMinNumber(array,mid+1,r);

        NajmaliBroevi result = new NajmaliBroevi();
        //a b
        //4 2
        //1 3
        //====
        //1 2

        if(r1.a < r2.a) {
            result.a = r1.a;
            if(r1.b < r2.a) {
                result.b = r1.b;
            } else result.b = r2.a;
        } else {
            result.a = r2.a;
            if(r2.b < r1.a) {
                result.b = r2.b;
            } else result.b = r1.a;
        }

        return result;

    }

    public static void main(String[] args) {
        int [] array = {7,9,0,5,6,2,4,3,1,8};

        System.out.println(findMinNumber(array,0,array.length-1));



    }
}
