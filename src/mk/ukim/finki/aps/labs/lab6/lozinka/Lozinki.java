package mk.ukim.finki.aps.labs.lab6.lozinka;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
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

class NamePassword implements Comparable<NamePassword> {
    private String name;

//    public NamePassword(String lines) {
//        String [] parts = lines.split(" ");
//        this.name = parts[0];
//        this.password = parts[1];
//    }

    public NamePassword(String name){
        this.name = name;
    }

    public NamePassword() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return  "" + name;
    }


    @Override
    public int compareTo(NamePassword other) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamePassword that = (NamePassword) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return (name.charAt(0) - 'a');
    }
}

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
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



public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        Map<String,String> map = new HashMap<String,String>();

        for(int i=0; i<N; i++) {
            String [] parts = br.readLine().split("\\s++");
            map.put(parts[0],parts[1]);
        }

        System.out.println(map);

        while (true) {
            String [] parts = br.readLine().split("\\s++");
            if(parts[0].equals("KRAJ")) return;

            if(map.containsKey(parts[0])) {
                if(map.containsValue(parts[1])) {
                    System.out.println("Najaven");
                    return;
                } else {
                    System.out.println("Nenajaven");
                }
            } else {
                System.out.println("Nenajaven");
            }
        }

      /*  //MOZHE I MAPIRANJE SO <STRING,STRING>
        CBHT<NamePassword,String> table = new CBHT<NamePassword,String>(31);
        for(int i=1;i<=N;i++){
            String imelozinka = br.readLine();
            String[] pom = imelozinka.split(" ");
            table.insert(new NamePassword(pom[0]),pom[1]);
        }

        for(int i=1; i<Integer.MAX_VALUE; i++){
            String imelozinka = br.readLine();
            String [] parts = imelozinka.split(" ");
            if(parts[0].equals("KRAJ")){
                //System.out.println("KRAJ");
                break;
            }
            SLLNode<MapEntry<NamePassword, String>> result = table.search(new NamePassword(parts[0]));
            if(result != null) {// ima ana banana i ana bannana
//                 System.out.println(result.element.key);
//                 System.out.println(result.element.value);
                 if(result.element.value.equals(parts[1])){
                     System.out.println("Najaven");
                     break;
                 } else {
                     System.out.println("Nenajaven");
                 }
            }
            else {
                System.out.println("Nenajaven");
            }

        }
      //  System.out.println(table);
*/
    }
}

/*
0:<ana,bannana>
1:
2:
3:<darko,marko>
4:
5:
6:
7:
8:
9:
10:
11:
12:<mitre,mitre> <marko,darko> <mara,shara>
13:
14:
15:<petre,petle> <pero,zdero>
16:
17:
18:
19:<trpe,trpi>
20:
21:
22:
23:
24:
25:
26:
27:
28:
29:
30:

 */
