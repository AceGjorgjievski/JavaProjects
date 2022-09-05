package mk.ukim.finki.np.vezhbi.labs.generici.GenerichkiKontejner;

public class IntegerArray  extends ResizableArray<Integer>{

    public int sum() {
        int sum = 0;
        for(int i=0;i<count(); i++) {
            sum += elementAt(i);
        }
        return sum;
    }

    public double mean() {
        return sum()*1.0/count();
    }

    public int countNonZero() {
        int counter=0;
        for(int i=0; i<count(); i++) {
            if(elementAt(i) != 0) counter++;
        }
        return counter;
    }

    public IntegerArray distinct() {
        IntegerArray integerArray = new IntegerArray();
        for(int i=0; i<count(); i++) {
            integerArray.addElement(elementAt(i));
        }
        return integerArray;
    }

    public IntegerArray increment(int offset) {
        IntegerArray integerArray = new IntegerArray();
        for(int i=0; i<count(); i++) {
            integerArray.addElement(elementAt(i)+offset);
        }
        return integerArray;
    }
}
