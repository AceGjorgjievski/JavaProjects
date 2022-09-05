package mk.ukim.finki.aps.kodovi.kod1_DllSllArray;

public class DLLNode<E extends Comparable<E>> {
	protected E element;
	protected int countOccurences;
	protected DLLNode<E> pred, succ;

	public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
		this.element = elem;
		this.pred = pred;
		this.succ = succ;
		this.countOccurences = 1;
	}

	@Override
	public String toString() {
		return element.toString() + "(" + countOccurences + ")";
	}
}
