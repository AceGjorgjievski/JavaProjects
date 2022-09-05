package mk.ukim.finki.aps.auds.av1.zad2;

class SLLNode2<E>{
    E element;//vrednost i refenrenca kje sodrzhi , tip generic bidejkji mozhe se da sodrzhi string,int, vraboten
    SLLNode2<E> succ;//od ist tip bidejkji pokazhuva kon ist tip i zatoa kje bide SLL node od tip generic

    public SLLNode2(E element, SLLNode2<E> succ) {
        this.element = element;
        this.succ = succ;
    }


}

class SLL2<E>{
    SLLNode2<E> first;// element koj pokazhuva na prviot node

    public SLL2(){
        this.first = null;
    }

    public void insertFirst(E o){//insterFirst kje zadava prv element na listata
        SLLNode2<E> pom = new SLLNode2<E>(o,first);
        first = pom;
    }
    // 1->2->3->4
    // 5->1->2->3->4
}




public class Main {



}
