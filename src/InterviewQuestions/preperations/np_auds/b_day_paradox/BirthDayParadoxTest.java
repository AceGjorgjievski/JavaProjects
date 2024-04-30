package InterviewQuestions.preperations.np_auds.b_day_paradox;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class BirthDayParadox {
    private int numberOfPeople;
    private static Random RANDOM = new Random();

    public BirthDayParadox() {
        this.numberOfPeople = 0;
    }

    public BirthDayParadox(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void conductExperiment() {
        Set<Integer> bdays = new HashSet<>();
        int counterOfSamePeopleBirthday = 0;
        for(int i=2; i<=numberOfPeople; i++) {
            counterOfSamePeopleBirthday = 0;
            for(int j=0; j<5000; j++) {
                bdays.clear();
                for(int k=0; k<i; k++) {
                    int randomNumber = RANDOM.nextInt(365)+1;
                    if(!bdays.contains(randomNumber)) {
                        bdays.add(randomNumber);
                    } else {
                        counterOfSamePeopleBirthday++;
                        break;
                    }
                }
            }
            System.out.printf("%d ---> %.10f\n",i, counterOfSamePeopleBirthday*1.0/5000);
        }

//        System.out.println(counterOfSamePeopleBirthday);
//        System.out.println(counterOfSamePeopleBirthday/5000*1.0);

    }
}

public class BirthDayParadoxTest {
    public static void main(String[] args) {
        BirthDayParadox birthDayParadox = new BirthDayParadox(100);
        birthDayParadox.conductExperiment();
    }
}
