package mk.ukim.finki.np.auds.av9;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapIntro {

    public static void main(String[] args) {

        /*
            Komplksnost :
            https://simplenotions.wordpress.com/2009/05/13/java-standard-data-structures-big-o-notation/
         */

        /*
            KOLEKCIJA KLUCH I VREDNOST
            Sekoj kluch se prebrishuva ako se dodava kako duplikat

            MAPA SO DVE KOLONI(Kluc,Vrednost) I BESKRAJ REDICI

            1.Se koristat za broenje na pojavuvanje na nekoi elementi
            2.Se koristi za grupiranje na elementite (Map<String,List<String>>) - kako closed bucket

         */

        //mora Kluchot da bide comparable ako ne, da se dodade vo
        //konstruktorot komaprator za Kluchot na mapata
        //izbegnuva duplika kluchevi, ako dodademe so ist kluchhot kje go prebrishe
        //mapata e sortirana spored kluchot vo rastechki redosled*
        //komplkesnosta bidejkji e drvo e O(logn)
        Map<String,String> treeMap = new TreeMap<>();

        treeMap.put("F","Finki");
        treeMap.put("F","Fakultet");
        treeMap.put("NP","napredno");
        treeMap.put("np","napredno programiranje");
        treeMap.put("I","informatika");

        System.out.println(treeMap);

        //Najednostavna kompleksnost O(1), O(n) iteracija
        //spored hash vrednosta na kluchot se stavaat vo kofickite
        //elementite vo tipKluch mora da imaat overrid-en hash kod
        Map<String,String> hasMap = new HashMap<>();

        hasMap.put("F","Finki");
        hasMap.put("F","Fakultet");
        hasMap.put("NP","napredno");
        hasMap.put("np","napredno programiranje");
        hasMap.put("I","informatika");

        System.out.println(hasMap);


        //isto kako hasMap samo shto redosledot na elementite se zachuvuva
        //i ima ista kompleksnost kako hashMap
        Map<String,String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("F","Finki");
        linkedHashMap.put("F","Fakultet");
        linkedHashMap.put("NP","napredno");
        linkedHashMap.put("np","napredno programiranje");
        linkedHashMap.put("I","informatika");

        System.out.println(linkedHashMap);
    }
}
