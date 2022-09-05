package mk.ukim.finki.os.labs.lab_3.zad1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class TancSoStudentite {
    //TODO: Definicija na globalni promenlivi i semafori

    public static Semaphore enterPresoblekuvalna;
    public static Semaphore mEnter;
    public static Semaphore wEnter;

    public static Semaphore lockW;
    public static Semaphore lockM;

    public static int mCount;
    public static int wCount;

    public static Semaphore wHere;
    public static Semaphore dancingDone;

    public static Semaphore enterDancingRoomLock;
    public static Semaphore enterDancingRoom;

    public void init() {
        //TODO: da se implementira
        enterPresoblekuvalna = new Semaphore(1);
        mEnter = new Semaphore(10);
        wEnter = new Semaphore(10);

        lockM = new Semaphore(1);
        lockW = new Semaphore(1);

        mCount = 0;
        wCount = 0;

        wHere = new Semaphore(0);
        dancingDone = new Semaphore(0);

        enterDancingRoomLock = new Semaphore(1);
        enterDancingRoom = new Semaphore(3);

    }

    class Masko extends Thread {
        //TODO: Definicija  na promenlivi za sostojbata

        public void ucestvo() throws InterruptedException {
            //TODO: da se implementira

            //crtez -> https://prnt.sc/ZcsugXh8mQ4Z


            lockM.acquire();
            if(mCount == 0) { //vidi dali ima nekoe drugo mashko vo soblekuvalna
                enterPresoblekuvalna.acquire();//ako ne, togash mozhebi ima zhenski
            }
            mCount++; //maski ++
            mEnter.acquire();// acq na semefor
            show.presobleci();
            lockM.release();


            lockM.acquire();
            mEnter.release();//otkako si zavrshil, update na brojot
            mCount--;
            if(mCount == 0) {//ako si posleden
                enterPresoblekuvalna.release();//mozhebi zenski chekaat, vikni gi
            }
            lockM.release();

            wHere.acquire();//chekaj informacija dali zhenskoto e spremno za tanc, dali e tuka/womanHere

            enterDancingRoomLock.acquire();
            enterDancingRoom.acquire();//probajte da vlezete vo slata za tac
            enterDancingRoomLock.release();

            // samo maskoto go povikuva metodot tancuvaj
            show.tancuvaj();//ako uspesno pominavte togash tancuvajte
            dancingDone.release();//isprati poraka deka e zavrsheno tancuvanjeto

            enterDancingRoomLock.acquire();
            enterDancingRoom.release();//update na semefoot za total parovi na scena za tanc
            enterDancingRoomLock.release();
        }

        @Override
        public void run() {
            try {
                ucestvo();
            } catch (InterruptedException e) {
                // Do nothing
            } catch (Exception e) {
                exception = e;
                hasException = true;
            }
        }

        @Override
        public String toString() {
            return String.format("m\t%d", getId());
        }
        public Exception exception = null;
    }

    class Zensko extends Thread {
        //TODO: Definicija  na promenlivi za sostojbata

        public void ucestvo() throws InterruptedException {
            //TODO: da se implementira
            lockW.acquire();
            if(wCount == 0) {//ako si prva i nema drugi zhenski vo soblekuvalna,
                enterPresoblekuvalna.acquire();//vidi mozhebi maski ima, chekaj
            }
            wCount++;//br zhenski ++
            wEnter.acquire();//vidi dali br na zhenski od semeforot e < 10
            show.presobleci();//ako e togash presoblechi se
            lockW.release();

            lockW.acquire();
            wEnter.release();//koga si gotova, update count na zhenski
            wCount--;
            if(wCount==0) {//ako si posledna
                enterPresoblekuvalna.release();//prati infomarcija deka mashki mozhe da vlezat
            }
            lockW.release();

            wHere.release();//prati info na mashkoto deka si spremna za tanc
            dancingDone.acquire();//chekaj da ti kazhe dali da zavrshite :)
        }

        @Override
        public void run() {
            try {
                ucestvo();
            } catch (InterruptedException e) {
                // Do nothing
            } catch (Exception e) {
                exception = e;
                hasException = true;
            }
        }

        @Override
        public String toString() {
            return String.format("z\t%d", getId());
        }
        public Exception exception = null;
    }

    public static void main(String[] args) {
        try {
            TancSoStudentite environment = new TancSoStudentite();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {
        show = new Show();
        init();
        HashSet<Thread> threads = new HashSet<Thread>();
        for (int i = 0; i < BROJ_INSTANCI; i++) {
            Zensko z = new Zensko();
            Masko m = new Masko();
            threads.add(z);
            threads.add(m);
        }

        for (Thread t : threads) {
            t.start();
        }

        boolean valid = true;
        for (Thread t : threads) {
            if (!hasException) {
                t.join();
            } else {
                t.interrupt();
            }
        }
        show.printStatus();

    }

    public class Show {

        public static final int BROJ_GARDEROBA = 10;
        public static final int BROJ_TEREN = 3;
        public static final int TYPE_MASKO = 1;
        public static final int TYPE_ZENSKO = 2;
        public static final int TYPE_UNKNOWN = -1;
        public static Random RANDOM;

        public Show() {
        }
        public int brojMaskiGarderoba = 0;
        public int brojZenskiGarderoba = 0;
        public int brojTancuvanja = 0;
        public int maxMaskiGarderoba = 0;
        public int maxZenskiGarderoba = 0;
        public int maxTancuvanja = 0;

        public void presobleci() throws RuntimeException {
            log(null, "presobleci start");
            Thread t = Thread.currentThread();
            if (t instanceof Masko) {
                synchronized (RANDOM) {
                    brojMaskiGarderoba++;
                    if (brojMaskiGarderoba > 10) {
                        exception("Ne moze da ima poveke od 10 maski vo maskata garderoba.");
                    }
                    if (brojMaskiGarderoba > maxMaskiGarderoba) {
                        maxMaskiGarderoba = brojMaskiGarderoba;
                    }
                }
                waitRandom();
                synchronized (RANDOM) {
                    brojMaskiGarderoba--;
                }
            } else {
                synchronized (RANDOM) {
                    brojZenskiGarderoba++;
                    if (brojZenskiGarderoba > 10) {
                        exception("Ne moze da ima poveke od 10 zenski vo zenskata garderoba.");
                    }
                    if (brojZenskiGarderoba > maxZenskiGarderoba) {
                        maxZenskiGarderoba = brojZenskiGarderoba;
                    }
                }
                waitRandom();
                synchronized (RANDOM) {
                    brojZenskiGarderoba--;
                }
            }
            log(null, "presobleci kraj");
        }

        public void tancuvaj() throws RuntimeException {
            log(null, "tancuvaj start");
            synchronized (RANDOM) {
                brojTancuvanja++;
                if (brojTancuvanja > BROJ_TEREN) {
                    exception("Ne moze paralelno da tancuvaat poveke od 3 para.");
                }

                if (brojTancuvanja > maxTancuvanja) {
                    maxTancuvanja = brojTancuvanja;
                }
            }
            waitRandom();
            synchronized (RANDOM) {
                brojTancuvanja--;
            }
            log(null, "tancuvaj kraj");
        }

        private void waitRandom() {
            try {
                int r;
                synchronized (RANDOM) {
                    r = RANDOM.nextInt(RANDOM_RANGE);
                }
                Thread.sleep(r);
            } catch (Exception e) {
                //do nothing
            }
        }

        private void exception(String message) {
            RuntimeException e = new RuntimeException(message);
            log(e, null);
            hasError = true;
            throw e;
        }

        public int getType() {
            Thread t = Thread.currentThread();
            if (t instanceof Masko) {
                return TYPE_MASKO;
            } else if (t instanceof Zensko) {
                return TYPE_ZENSKO;
            } else {
                return TYPE_UNKNOWN;
            }
        }

        private synchronized void log(RuntimeException e, String action) {
            Thread t = Thread.currentThread();
            if (e == null) {
                actions.add(t.toString() + "\t(a): " + action);
            } else {
                actions.add(t.toString() + "\t(e): " + e.getMessage());
            }
        }

        public synchronized void printLog() {
            System.out.println("Poradi konkurentnosta za pristap za pecatenje, mozno e nekoja od porakite da ne e na soodvetnoto mesto.");
            System.out.println("Log na izvrsuvanje na akciite:");
            System.out.println("=========================");
            System.out.println("(tip m<=>Masko, tip z<=>Zensko)");
            System.out.println("tip\tid\takcija/error");
            System.out.println("=========================");
            for (String l : actions) {
                System.out.println(l);
            }
        }

        public void printStatus() {
            if (!hasError) {
                int poeni = 25;
                System.out.println("Procesot e uspesno sinhroniziran");
                if (show.maxMaskiGarderoba == 1 || show.maxZenskiGarderoba == 1) {
                    System.out.println("\t-no ima maksimum eden ucesnik vo garderobata.");
                    poeni -= 5;
                }
                if (show.maxTancuvanja == 1) {
                    System.out.println("\t-no ima maksimum edna proverka vo eden moment.");
                    poeni -= 5;
                }

                System.out.println("Osvoeni poeni: " + poeni);

            } else {
                System.out.println("Procesot ne e sinhroniziran spored uslovite na zadacata");
                show.printLog();
                System.out.println("Maksimum mozni poeni: 15");
            }

        }
        private List<String> actions = new ArrayList<String>();
        private boolean hasError = false;
    }
    // Konstanti
    public static int BROJ_INSTANCI = 1000;
    public static final Random RANDOM = new Random();
    public static final int RANDOM_RANGE = 3;
    // Instanca od bafferot
    public Show show;
    public boolean hasException = false;
}
