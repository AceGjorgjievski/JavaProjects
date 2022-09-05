package mk.ukim.finki.aps.labs.kolokviumski1.Kompanija2;

public class DLLNode {
    protected int id;
    protected int plata;
    protected DLLNode succ,pred;

    DLLNode(int id, int plata, DLLNode pred, DLLNode succ){
        this.id = id;
        this.plata = plata;
        this.succ = succ;
        this.pred = pred;
    }
}
