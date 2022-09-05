package mk.ukim.finki.aps.labs.lab2NiziListi.zad3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLL<E extends Comparable<E>> {
	private SLLNode<E> first;

	public SLL() {
		// Construct an empty SLL
		this.first = null;
	}

	public void deleteList() {
		first = null;
	}

	public int length() {
		int ret;
		if (first != null) {
			SLLNode<E> tmp = first;
			ret = 1;
			while (tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			return ret;
		} else
			return 0;

	}

	@Override
	public String toString() {
		String ret = new String();
		if (first != null) {
			SLLNode<E> tmp = first;
			if(tmp.succ == null) // if there is only one element in the list
				ret += tmp;
			else
				ret += tmp + "->";
				while (tmp.succ != null) {
					tmp = tmp.succ;
					if(tmp.succ == null)
						ret += tmp;
					else
						ret += tmp + "->";
				}
		} else
			ret = "Prazna lista!!!";
		return ret;
	}

	public void insertFirst(E o) {
		SLLNode<E> ins = new SLLNode<E>(o, first);
		first = ins;
	}

	public void insertAfter(E o, SLLNode<E> node) {
		if (node != null) {
			SLLNode<E> ins = new SLLNode<E>(o, node.succ);
			node.succ = ins;
		} else {
			System.out.println("Dadenot jazol e null===123");
		}
	}

	public void insertBefore(E o, SLLNode<E> before) {
		
		if (first != null) {
			SLLNode<E> tmp = first;
			if(first==before){
				this.insertFirst(o);
				return;
			}
			//ako first!=before
			while (tmp.succ != before)
				tmp = tmp.succ;
			if (tmp.succ == before) {
				SLLNode<E> ins = new SLLNode<E>(o, before);
				tmp.succ = ins;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
	}

	public void insertLast(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.succ != null)
				tmp = tmp.succ;
			SLLNode<E> ins = new SLLNode<E>(o, null);
			tmp.succ = ins;
		} else {
			insertFirst(o);
		}
	}

	public E deleteFirst() {
		if (first != null) {
			SLLNode<E> tmp = first;
			first = first.succ;
			return tmp.element;
		} else {
			System.out.println("Listata e prazna");
			return null;
		}
	}

	public E delete(SLLNode<E> node) {
		if (first != null) {
			SLLNode<E> tmp = first;
			if(first ==node){
				return this.deleteFirst();
			}
			while (tmp.succ != node && tmp.succ.succ != null)
				tmp = tmp.succ;
			if (tmp.succ == node) {
				tmp.succ = tmp.succ.succ;
				return node.element;
			} else {
				System.out.println("Elementot ne postoi vo listata");
				return null;
			}
		} else {
			System.out.println("Listata e prazna");
			return null;
		}

	}

	public SLLNode<E> getFirst() {
		return first;
	}
	
	public SLLNode<E> find(E o) {
		if (first != null) {
			SLLNode<E> tmp = first;
			while (tmp.element.equals(o) && tmp.succ != null)
				tmp = tmp.succ;
			if (tmp.element.equals(o)) {
				return tmp;
			} else {
				System.out.println("Elementot ne postoi vo listata");
			}
		} else {
			System.out.println("Listata e prazna");
		}
		return first;
	}
	
    public Iterator<E> iterator () {
    // Return an iterator that visits all elements of this list, in left-to-right order.
        return new LRIterator<E>();
    }

	// //////////Inner class ////////////

	private class LRIterator<E> implements Iterator<E> {

		private SLLNode<E> place, curr;

		private LRIterator() {
			place = (SLLNode<E>) first;
			curr = null;
		}

		public boolean hasNext() {
			return (place != null);
		}

		public E next() {
			if (place == null)
				throw new NoSuchElementException();
			E nextElem = place.element;
			curr = place;
			place = place.succ;
			return nextElem;
		}

		public void remove() {
			//Not implemented
		}
	}
	
	public void mirror(){
		if (first != null) {
			//m=nextsucc, p=tmp,q=next
			SLLNode<E> tmp = first;
			SLLNode<E> newsucc = null;
			SLLNode<E> next;
			
			while(tmp != null){
				next = tmp.succ;
				tmp.succ = newsucc;
				newsucc = tmp;
				tmp = next;
			}
			first = newsucc;
		}
		
	}
	
	public void merge (SLL<E> in){
		if (first != null) {
			SLLNode<E> tmp = first;
			while(tmp.succ != null)
				tmp = tmp.succ;
			tmp.succ = in.getFirst();
		}
		else{
			first = in.getFirst();
		}
	}

	public SLL<E> joinLists(SLL<E> in){
		SLL<E> result = new SLL<E>();
		SLLNode<E> temp1 = in.getFirst(), temp2 = this.getFirst();
		while (temp1 != null && temp2 != null){
			if(temp1.element.compareTo(temp2.element) < 0){//temp1 < temp2
				result.insertLast(temp1.element);
				temp1 = temp1.succ;
			} else { // temp1 > temp2
				result.insertLast(temp2.element);
				temp2 = temp2.succ;
			}
		}

		if(temp1 != null){
			while (temp1 != null){
				result.insertLast(temp1.element);
				temp1 = temp1.succ;
			}
		}

		if(temp2 != null){
			while (temp2 != null){
				result.insertLast(temp2.element);
				temp2 = temp2.succ;
			}
		}
		return result;
	}

	public SLL<E> specialJoin(SLL<E> in1, SLL<E> in2){//JoinListsByTwo 1 2 3 4 / 5 6 7 8 => 1 2 5 6 3 4 7 8
		SLL<E> result = new SLL<>();
		SLLNode<E> temp1 = in1.first, temp2 = in2.first;

			while (temp1.succ != null && temp2.succ != null){
				result.insertLast(temp1.element);
				result.insertLast(temp1.succ.element);
				result.insertLast(temp2.element);
				result.insertLast(temp2.succ.element);
				if(temp1.succ.succ == null){
					if(temp2.succ.succ == null){
						temp2 = null;
						temp1 = null;
						break;
					} else {
						temp1 = null;
						temp2 = temp2.succ.succ;
						break;
					}
				}
				if(temp2.succ.succ == null){
					temp2 = null;
					temp1 = temp1.succ.succ;
					break;
				}
				temp1 = temp1.succ.succ;
				temp2 = temp2.succ.succ;
			}
		//     t2
		//     t1
		// 1 2 7 8
		// 5 6
		// 1 2 5 6 7 8
		if(temp1 !=null){
			while (temp1!= null){
				result.insertLast(temp1.element);
				temp1 = temp1.succ;
			}
		}
		if(temp2 != null){
			while (temp2 != null){
				result.insertLast(temp2.element);
				temp2 = temp2.succ;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		SLL<Integer> lista1 = new SLL<>();
		SLL<Integer> lista2 = new SLL<>();
		SLL<Integer> result = new SLL<>();

		lista1.insertLast(1);
		lista1.insertLast(2);
		lista1.insertLast(7);
		lista1.insertLast(5);
		lista2.insertLast(6);

		System.out.println(result.specialJoin(lista1,lista2));
	}
}
