package mk.ukim.finki.aps.labs.lab6.Pharmacy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            temp.append(i).append(":");
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp.append(curr.element.toString()).append(" ");
            }
            temp.append("\n");
        }
        return temp.toString();
    }

}

class CureName {
    String cureName;

    public CureName(String cureName) {
        this.cureName = cureName.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CureName cureName1 = (CureName) o;
        return Objects.equals(cureName, cureName1.cureName);
    }

    public int ASCII(char input) {
        return (int)input;
    }

    @Override
    public int hashCode() {
        return (29*(29*(29*0 + ASCII(cureName.charAt(0)) + ASCII(cureName.charAt(1)) + ASCII(cureName.charAt(2))))) % 102780;
    }

    @Override
    public String toString() {
        return ""+cureName;
    }
}

class Cure {
    String name;
    int isPositive;
    int price;
    int quantity;

    public Cure(String name, int isPositive, int price, int quantity) {
        this.name = name.toUpperCase();
        this.isPositive = isPositive;
        this.price = price;
        this.quantity = quantity;
    }

    public String isPositive(){
        return isPositive == 1 ? "POZ" : "NEG";
    }

    public boolean makeOrder(int inputQuanity) {
        if(inputQuanity <= quantity) {
            quantity -= inputQuanity;
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s\n%d\n%d",
                name,
                isPositive(),
                price,
                quantity);
    }
}

public class PharmacyTest {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        Map<CureName,Cure> map = new HashMap<>();

        for(int i=0; i<N; i++) {
            String [] parts = bf.readLine().split("\\s++");

            String name = parts[0];
            int isPositive = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            int quantity = Integer.parseInt(parts[3]);

            CureName cureName = new CureName(name);
            Cure cure = new Cure(name,isPositive,price,quantity);

            map.put(cureName,cure);
        }

        while (true) {
            String line = bf.readLine();
            if(line.equals("KRAJ")) return;

            int quantity = Integer.parseInt(bf.readLine());

            CureName cureName = new CureName(line);

            if(map.containsKey(cureName)) {
                Cure cure = map.get(cureName);
                if(cure.makeOrder(quantity)) {
                    System.out.println(cure);
                    System.out.println("Napravena naprachka");
                } else {
                    System.out.println("Nema dovolno lekovi");
                }
            } else {
                System.out.println("Nema takov lek");
            }

        }

    }
}
