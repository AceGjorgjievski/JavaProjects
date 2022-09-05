package mk.ukim.finki.np.vezhbi.auds.generici;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.addElement("bottom",20);
        priorityQueue.addElement("mid1",70);
        priorityQueue.addElement("mid3",40);
        priorityQueue.addElement("mid2",50);
        priorityQueue.addElement("top",100);

        String element;
        while ((element = priorityQueue.remove()) != null) {
            System.out.println(element);
        }
    }
}
