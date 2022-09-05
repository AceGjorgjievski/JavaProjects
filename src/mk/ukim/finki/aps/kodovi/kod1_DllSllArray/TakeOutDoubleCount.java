package mk.ukim.finki.aps.kodovi.kod1_DllSllArray;

public class TakeOutDoubleCount<E extends Comparable<E>> {
    //1<->2<->3<->1<->5<->2 ==>  1<->2<->3<->5

    public DLL<E> removeSameElements(DLL<E> lista){
        DLLNode<E> temp1 = lista.getFirst();

        while(temp1.succ != null){
            DLLNode<E> temp2 = temp1.succ;
            while(temp2 != null){
                if(temp1.element.compareTo(temp2.element) == 0){
                    if(temp2.succ != null){
                        temp2 = temp2.succ;
                        lista.delete(temp2.pred);
                    } else {
                        lista.delete(temp2);
                        temp2 = null;//ova se pravi za da izleze od ciklusot
                    }
                } else {
                    temp2 = temp2.succ;
                }
            }
            temp1 = temp1.succ;
        }
        return lista;
    }

    public static void main(String[] args) {
        DLL<Integer> lista = new DLL<Integer>();
        for(int i=1; i<=8; i++){
            if(i%2 == 1){
                lista.insertLast(i);
            }
        }
        lista.insertLast(1);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(1);


        System.out.println(lista.toString());

        TakeOutDoubleCount<Integer> takeOutDoubleCount = new TakeOutDoubleCount<Integer>();

        System.out.println(takeOutDoubleCount.removeSameElements(lista));
    }


}

