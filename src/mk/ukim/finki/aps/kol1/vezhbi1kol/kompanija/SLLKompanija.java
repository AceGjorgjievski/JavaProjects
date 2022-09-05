package mk.ukim.finki.aps.kol1.vezhbi1kol.kompanija;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class SLLNode {
    protected int id;
    protected int plata;
    protected SLLNode succ;

    public SLLNode(int id,int plata, SLLNode succ) {
        this.id = id;
        this.plata=plata;
        this.succ = succ;
    }


}

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }


    public void insertFirst(int id, int plata) {
        SLLNode ins = new SLLNode(id,plata, first);
        first = ins;
    }

    public void insertLast(int id,int plata) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, plata, null);
            tmp.succ = ins;
        } else {
            insertFirst(id,plata);
        }
    }

    public SLLNode getFirst() {
        return first;
    }

    public SLLNode deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
            return tmp;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }


    public SLL brisi_pomali_od(int iznos) {
        // Vasiot kod tuka
        SLLNode node = this.getFirst();
        while (node != null) {

            if(node.plata < iznos) {
                this.delete(node);
            }
            node = node.succ;
        }
        if(length() < 1) return null;
        return this;
    }

    public SLL sortiraj_opagacki() {
        // Vasiot kod tuka
        SLLNode node = this.getFirst();
        SLLNode node2=null;
        SLLNode temp = new SLLNode(-1,-1,null);
        while (node != null) {
            node2 = node.succ;
            while (node2 != null) {

                if(node.id < node2.id) {
                    temp.id = node.id;
                    temp.plata = node.plata;
                    node.id = node2.id;
                    node.plata = node2.plata;
                    node2.id = temp.id;
                    node2.plata = temp.plata;
                }
                node2 = node2.succ;
            }

            node = node.succ;
        }

        return this;
    }
    public void pecati (SLL lista)
    {
        SLLNode p=lista.first;
        while(p!=null)
        {
            System.out.println(p.id+" "+p.plata);
            p=p.succ;
        }
    }

}
public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s=stdin.readLine();
            String s1=stdin.readLine();
            lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1=lista1.brisi_pomali_od(Integer.parseInt(s));
        if(lista1!=null)
        {
            lista1=lista1.sortiraj_opagacki();
            lista1.pecati(lista1);
        } else {
            System.out.println("nema");
        }

    }
}
