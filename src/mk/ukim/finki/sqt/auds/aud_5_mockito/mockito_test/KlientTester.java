package mk.ukim.finki.sqt.auds.aud_5_mockito.mockito_test;

//import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class KlientTester {
	Klient klient;
	ServisTroshoci servisTroshoci;
	
	public static void main(String[] args){
		KlientTester tester=new KlientTester();
		tester.setUp();
		System.out.println(tester.testirajVrednostCena()?"pass":"fail");
	}

	public void setUp(){
		//Kreirame objekt Klient koj ke se koristi za testiranje
		klient=new Klient();
		//Kreirame mock objekt od servisot za troshci
//		servisTroshoci= mock(ServisTroshoci.class);
		//go postavuvame servisot za troshoci za dadeniot klient
		klient.setServisTroshoci(servisTroshoci);
	}

	public boolean testirajVrednostCena(){
		//Kreiraj lista na proizvodi koi sakame da gi dodademe kaj daden klient
		//t.e. koi klientot gi kupil
		List<Proizvod> proizvodi=new ArrayList<Proizvod>();
		Proizvod jabolka=new Proizvod("1","Jabolko",5);
		Proizvod leb=new Proizvod("2","leb",2);
		proizvodi.add(jabolka);
		proizvodi.add(leb);



		//gi dodavame proizvodite vo kosnicata na dadeniot klient
		klient.setProizvodi(proizvodi);
		//go mockirame odnesuvanje na servisot za troshoci da ja vrati vrednosta na razlicniot proizvod
//		when(servisTroshoci.getCena(jabolka)).thenReturn(25.00);
//		when(servisTroshoci.getCena(leb)).thenReturn(20.00);
		double cena=klient.getCenaProizvod();
		return cena==165.0;
	}
}
