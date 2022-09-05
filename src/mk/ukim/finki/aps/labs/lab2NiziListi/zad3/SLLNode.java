package mk.ukim.finki.aps.labs.lab2NiziListi.zad3;

public class SLLNode<E> {
    protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}
