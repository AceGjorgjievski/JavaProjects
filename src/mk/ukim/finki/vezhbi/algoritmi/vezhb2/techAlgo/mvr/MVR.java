package mk.ukim.finki.vezhbi.algoritmi.vezhb2.techAlgo.mvr;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

class ArrayQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

class Gragjanin {
    private String nameSurname;
    private int idCard;
    private int passport;
    private int drivingLicense;

    public Gragjanin(String nameSurname, int idCard, int passport, int drivingLicense) {
        this.nameSurname = nameSurname;
        this.idCard = idCard;
        this.passport = passport;
        this.drivingLicense = drivingLicense;
    }

    public int getIdCard() {
        return idCard;
    }

    public int getPassport() {
        return passport;
    }

    public int getDrivingLicense() {
        return drivingLicense;
    }

    @Override
    public String toString() {
        return String.format("%s", nameSurname);
    }
}

public class MVR {


    public static void priorityQueue(Gragjanin[] gragjanins) {
        Queue<Gragjanin> idCardQueue = new ArrayQueue<>(100);
        Queue<Gragjanin> passportQueue = new ArrayQueue<>(100);
        Queue<Gragjanin> drivingLicenseQueue = new ArrayQueue<>(100);

        for (int i = 0; i < gragjanins.length; i++) {
            if (gragjanins[i].getIdCard() == 1) {
                idCardQueue.enqueue(gragjanins[i]);
            } else if (gragjanins[i].getPassport() == 1) {
                passportQueue.enqueue(gragjanins[i]);
            } else {
                drivingLicenseQueue.enqueue(gragjanins[i]);
            }
        }

        while (!idCardQueue.isEmpty()) {
            Gragjanin g = idCardQueue.dequeue();
            if (g.getPassport() == 0 && g.getDrivingLicense() == 0) {
                System.out.println(g.toString());
            } else if (g.getPassport() == 1) {
                passportQueue.enqueue(g);
            } else {
                drivingLicenseQueue.enqueue(g);
            }
        }

        while (!passportQueue.isEmpty()) {
            Gragjanin g = passportQueue.dequeue();
            if (g.getDrivingLicense() == 0) {
                System.out.println(g.toString());
            } else {
                drivingLicenseQueue.enqueue(g);
            }
        }

        while (!drivingLicenseQueue.isEmpty()) {
            Gragjanin g = drivingLicenseQueue.dequeue();
            System.out.println(g.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        Gragjanin[] gragjanins = new Gragjanin[N];

        for (int i = 0; i < N; i++) {
            String nameSurname = bf.readLine();
            int idCard = Integer.parseInt(bf.readLine());
            int passport = Integer.parseInt(bf.readLine());
            int drivingLicense = Integer.parseInt(bf.readLine());
            gragjanins[i] = new Gragjanin(nameSurname, idCard, passport, drivingLicense);
        }
        priorityQueue(gragjanins);
    }
}
