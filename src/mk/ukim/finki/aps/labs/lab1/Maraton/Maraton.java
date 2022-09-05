package mk.ukim.finki.aps.labs.lab1.Maraton;

import java.util.Objects;

public class Maraton implements IMaraton{
    private String place;
    private int year;
    private Atleticar [] atleticari;

    public Maraton() {
    }

    public Maraton(String place, int year, Atleticar[] atleticari) {
        this.place = place;
        this.year = year;
        this.atleticari = atleticari;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Atleticar[] getAtleticari() {
        return atleticari;
    }

    public void setAtleticari(Atleticar[] atleticari) {
        this.atleticari = atleticari;
    }

    @Override
    public String toString() {
         StringBuilder s = new StringBuilder();
         s.append(place);
         s.append("\n");
         s.append(year);
         s.append("\n");
         for(int i=0; i<atleticari.length;  i++){
             s.append(atleticari[i].toString());
             s.append("\n");
         }
         return s.toString();
    }

    @Override
    public Atleticar najdobroVreme() {
        if(atleticari.length == 1)
            return atleticari[0];
        double minVreme = atleticari[0].getTimeRunning();
        int ind = -1;

        for(int i=1; i<atleticari.length; i++){
            if(minVreme > atleticari[i].getTimeRunning()){
                minVreme = atleticari[i].getTimeRunning();
                ind = i;
            }
        }
        return atleticari[ind];
    }

    @Override
    public int atleticariOd(String s) {
        int count = 0;
        for(int i=0; i<atleticari.length; i++){
            if(Objects.equals(atleticari[i].getCountry(), s)){
                count++;
            }
        }
        return count;
    }
}
