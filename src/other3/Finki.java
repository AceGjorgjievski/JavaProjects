package other3;

public class Finki<T> {
    private T element;


    public void print() {
        System.out.println(element);
    }


    public static void main(String[] args) {
        Finki<Integer> integerFinki = new Finki<Integer>();

    }

}
