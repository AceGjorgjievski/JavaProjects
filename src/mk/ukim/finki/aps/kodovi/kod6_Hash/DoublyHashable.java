package mk.ukim.finki.aps.kodovi.kod6_Hash;

public interface DoublyHashable<K> extends Comparable<K> {

    public int hashCode ();
    // Return the (raw) hash code to use for this key.
    
    public int stepCode ();
    // Return the (raw) step size to use for this key.

}