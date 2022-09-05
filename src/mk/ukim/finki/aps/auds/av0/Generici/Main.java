package mk.ukim.finki.aps.auds.av0.Generici;

public class Main {
    public static void main(String[] args) {
       /* System.out.println("hello");

        Scanner input = new Scanner(System.in); //scanf
        System.out.println("Vnesi cel broj: ");

        int broj = input.nextInt();
        //double broj2 = input.nextDouble();

        System.out.println("Vneseniot broj e: "+ broj + " so potochna preciznost: ");

        if(broj > 10){
            System.out.println("Brojot e pogolem od 10");
        } else {
            System.out.println("Brojot e 10 ili ne epogolem od 10");
        }*/
        //System.out.println("hello");

        Kutija<Integer> kutija1 = new Kutija<Integer>(12);
        System.out.println(kutija1.toString());
        kutija1.setObj(10);
        System.out.println(kutija1.toString());

        Kutija<String> kutija2 = new Kutija<String>("Zhaba");
        System.out.println(kutija2.toString());
        System.out.println(kutija2.toString());

        Kutija<Televizor> kutijaSoTelevizor = new Kutija<Televizor>();
        Televizor t1 = new Televizor("LG",40,true);
        kutijaSoTelevizor.setObj(t1);

        System.out.println(t1);
        System.out.println(kutijaSoTelevizor);

        Kutija<Televizor> kutijaSoTelevizor2 = new Kutija<Televizor>();
        Televizor t2 = new Televizor("Philips",42,true);
        kutijaSoTelevizor2.setObj(t2);
        System.out.println("Kutijata so televizor 2 sodrzhi " + kutijaSoTelevizor2);




    }

}
