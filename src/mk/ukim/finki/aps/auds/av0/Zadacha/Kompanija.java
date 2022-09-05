package mk.ukim.finki.aps.auds.av0.Zadacha;

public class Kompanija {
    private Vraboten [] vraboteni;
    private int brVraboteni;

    public Kompanija() {
        this.vraboteni = new Vraboten[0];
        this.brVraboteni = 0;
    }

    public Kompanija(Vraboten[] vraboteni) {
        this.vraboteni = vraboteni;
    }

    public Kompanija(Vraboten[] vraboteni, int brVraboteni) {
        this.vraboteni = vraboteni;
        this.brVraboteni = brVraboteni;
    }

    public Vraboten najangaziran(){
        int max = vraboteni[0].vkupnoChasovi();
        int ind = -1;
        for(int i=1; i<brVraboteni; i++){
            if(vraboteni[i].vkupnoChasovi() > max){
                max = vraboteni[i].vkupnoChasovi();
                ind = i;
            }
        }
        return vraboteni[ind];
    }

    public void pecatiPoUspeshnost() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < vraboteni.length - 1; j++) {
                if (vraboteni[j].vratiProcent() <
                        vraboteni[j + 1].vratiProcent()) {
                    Vraboten pom = vraboteni[j];
                    vraboteni[j] = vraboteni[j + 1];
                    vraboteni[j + 1] = pom;
                    flag = true;
                }
            }
        }
        for (int i = 0; i < vraboteni.length; i++) {
            System.out.printf("Vraboten: " + vraboteni[i].getIme() + " " +
                    vraboteni[i].getPrezime() + " Uspesnost: %.2f\n", (vraboteni[i].vratiProcent() * 100));
        }
    }

    public void pecati(){
        for(Vraboten v: vraboteni){
            v.toString();
        }
    }
}
