package mk.ukim.finki.np.auds.av9;

import java.util.*;

public class SetIntro {
    public static void main(String[] args) {

        /*
            Komplksnost :
            https://simplenotions.wordpress.com/2009/05/13/java-standard-data-structures-big-o-notation/
         */

        /*
            SITE SETOVI SE KORISTAT ZA IZBEGNUVANJE DUPLIKATI
         */

        //unikatni(da nema duplikati) i plus sortirani
        //se koristi za da se sortiraat elementite spored nekoj komparator
        //Integer sama po sebe go nasleduva Number i go implementira interface Comparable
        //a ako e za custom klasa (Set<OtherClass>) togash mora da se implementira komparator i
        //da se prati kako eden edinstven argument kaj konstruktorot na TreeSet
        Set<Integer> treeIntSet = new TreeSet<>(Comparator.reverseOrder());
        for(int i=10; i>=1; i--){
            treeIntSet.add(i);
            treeIntSet.add(i);
        }
        System.out.println(treeIntSet);

        //najednostavna vremenska kompleksnost i bez duplikati i redosledot se izmestuva spored hashCode
        //elementite vo kofickata se stavaat spored hash vrednost
        Set<Integer> hasIntSet = new HashSet<>();
        for(int i=1; i<=10; i++){
            hasIntSet.add(i);
            hasIntSet.add(i);
        }

        Set<String> hasStringSet = new HashSet<>();
        hasStringSet.add("FINKI");
        hasStringSet.add("NP");
        hasStringSet.add("napredno");
        hasStringSet.add("ukim");

        System.out.println(hasStringSet);

        //ednostavna kompleksnost, no redosledot tochno se zachuvuva na elementite

        Set<String> linkedStringSet = new LinkedHashSet<>();
        linkedStringSet.add("FINKI");
        linkedStringSet.add("NP");
        linkedStringSet.add("napredno");
        linkedStringSet.add("ukim");

        //kako dodavame taka se zachuvuvaat
        System.out.println(linkedStringSet);
    }
}
