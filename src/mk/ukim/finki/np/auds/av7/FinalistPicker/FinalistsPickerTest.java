package mk.ukim.finki.np.auds.av7.FinalistPicker;

import java.util.*;

class InvalidArgumentException extends Exception {
    public InvalidArgumentException(int numOfpRizes) {
        super(String.format("The following number of prizes %d is not within the constrains",numOfpRizes));
    }
}

class FinalistPicker {
    int numFinalists;
    static Random RANDOM = new Random();

    public FinalistPicker(int numFinalists) {
        this.numFinalists = numFinalists;
    }

    public Collection<Integer> pickFinalist(int numOfPrizes) throws InvalidArgumentException {
        if(numOfPrizes <= 0 || numOfPrizes > numFinalists) throw new InvalidArgumentException(numOfPrizes);

//        List<Integer> list = new ArrayList<>();
//
//        while(list.size() != numOfPrizes){
//            int generateFinalist = RANDOM.nextInt(numFinalists) + 1;
//            if(!list.contains(generateFinalist)){
//                list.add(generateFinalist);
//            }
//        }

        Set<Integer> set = new HashSet<>();
        while(set.size() != numOfPrizes){
            set.add(RANDOM.nextInt(numFinalists) + 1);
        }
        // List & Set are inherited from Collection class
        return set;
    }
}

public class FinalistsPickerTest {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfFinalists = input.nextInt();
        int numOfPrizes = input.nextInt();

        FinalistPicker finalistPicker = new FinalistPicker(numOfFinalists);
        try {
            System.out.println(finalistPicker.pickFinalist(numOfPrizes));
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

    }
}
