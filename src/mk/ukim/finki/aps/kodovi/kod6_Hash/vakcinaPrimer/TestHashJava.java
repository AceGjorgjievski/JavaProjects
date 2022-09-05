package mk.ukim.finki.aps.kodovi.kod6_Hash.vakcinaPrimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHashJava {


    public static void main(String[] args) {
        Map<Vaccine, List<String>> map = new HashMap<>();

        Vaccine vaccine1 = new Vaccine("PHY",1);
        Vaccine vaccine2 = new Vaccine("PHY",2);
        Vaccine vaccine3 = new Vaccine("AZ",1);
        Vaccine vaccine4 = new Vaccine("AZ",2);

        map.put(vaccine1, new ArrayList<>());

        map.get(vaccine1).add("John");
    }
}
