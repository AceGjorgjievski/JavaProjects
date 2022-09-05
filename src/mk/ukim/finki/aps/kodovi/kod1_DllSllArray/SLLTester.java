package mk.ukim.finki.aps.kodovi.kod1_DllSllArray;


public class SLLTester {

	public static void main(String[] args) throws Throwable {
		/*SLL<Integer> lista = new SLL<Integer>();


		lista.insertLast(5);
		System.out.print("Listata po vmetnuvanje na 5 kako posleden element: ");
		System.out.println(lista.toString());
		
		lista.insertFirst(3);
		System.out.print("Listata po vmetnuvanje na 3 kako prv element: ");
		System.out.println(lista.toString());
		
		lista.insertLast(1);
		System.out.print("Listata po vmetnuvanje na 1 kako posleden element: ");
		System.out.println(lista.toString());
		
		lista.deleteFirst();
		System.out.print("Listata po brishenje na prviot element: ");
		System.out.println(lista.toString());
		
		SLLNode<Integer> pom = lista.find(5);
		lista.insertBefore(2, pom);
		System.out.print("Listata po vmetnuvanje na elementot 2 pred elementot 5: ");
		System.out.println(lista.toString());
		
		pom = lista.find(1);
		lista.insertAfter(3, pom);
		System.out.print("Listata po vmetnuvanje na elementot 3 posle elementot 1: ");
		System.out.println(lista.toString());
		
		Iterator<Integer> i = lista.iterator();
		System.out.print("Izminuvanje so iterator: ");
		while(i.hasNext())
			System.out.print(i.next()+" ");
		System.out.println();
		System.out.println("Momentalna dolzina na listata: "+lista.length());
		
		System.out.print("Listata po prevrtuvanje: ");
		lista.mirror();
		System.out.println(lista.toString());
		
		pom = lista.find(2);
		lista.delete(pom);
		System.out.print("Listata po brishenje na elementot 2: ");
		System.out.println(lista.toString());
		
		System.out.println("Momentalna dolzina na listata: "+lista.length());
		
		lista.deleteList();
		System.out.print("Pecatenje na listata po nejzino brishenje: ");
		System.out.println(lista.toString());
		System.out.println("Momentalna dolzina na listata: "+lista.length());*/

		SLL<Integer> lista = new SLL<Integer>();


		for(int i=1; i<=10; i++){
			lista.insertLast(i);
		}
		System.out.println(lista.toString());
		SLLNode<Integer> node = lista.find(5);
		lista.delete(node);
		System.out.println(lista.toString());
//		Iterator<Integer> iterator = lista.iterator();
//
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
//		lista.insertFirst(50);
//		lista.insertAfter(11,lista.getFirst());
//		lista.insertAfter(12,lista.getFirst().succ);
//		lista.insertAfter(13,lista.getFirst().succ.succ);
//		lista.insertAfter(14,lista.getFirst().succ.succ.succ);
//		for(int i=0; i<10; i++){
//			lista.insertLast(i);
//		}
//		System.out.println(lista.toString());
//		Iterator<Integer> i = lista.iterator();
//		System.out.print("Izminuvanje so iterator: ");
//		while(i.hasNext())
//			System.out.print(i.next()+" ");
//		System.out.println();
//		System.out.println("Momentalna dolzina na listata: "+lista.length());


		/*

		10 -> 8 ->

		 */

		
	}

}
