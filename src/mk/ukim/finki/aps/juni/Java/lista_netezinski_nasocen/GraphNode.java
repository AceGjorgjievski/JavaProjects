//package lista_netezinski_nasocen;
//
//import java.util.LinkedList;
//
//public class GraphNode<E> {
//	private int index;//index (reden broj) na temeto vo grafot
//	private E info;
//	private LinkedList<mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E>> neighbors;
//
//	public GraphNode(int index, E info) {
//		this.index = index;
//		this.info = info;
//		neighbors = new LinkedList<mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E>>();
//	}
//
//	boolean containsNeighbor(mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E> o){
//		return neighbors.contains(o);
//	}
//
//	void addNeighbor(mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E> o){
//		neighbors.add(o);
//	}
//
//
//	void removeNeighbor(mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E> o){
//		if(neighbors.contains(o))
//			neighbors.remove(o);
//	}
//
//
//	@Override
//	public String toString() {
//		String ret= "INFO:"+info+" SOSEDI:";
//		for(int i=0;i<neighbors.size();i++)
//		ret+=neighbors.get(i).info+" ";
//		return ret;
//
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		@SuppressWarnings("unchecked")
//		mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E> pom = (mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E>)obj;
//		return (pom.info.equals(this.info));
//	}
//
//	public int getIndex() {
//		return index;
//	}
//
//	public void setIndex(int index) {
//		this.index = index;
//	}
//
//	public E getInfo() {
//		return info;
//	}
//
//	public void setInfo(E info) {
//		this.info = info;
//	}
//
//	public LinkedList<mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E>> getNeighbors() {
//		return neighbors;
//	}
//
//	public void setNeighbors(LinkedList<mk.ukim.finki.aps.juni.Ispiti.Zagaduvanje.other.GraphNode<E>> neighbors) {
//		this.neighbors = neighbors;
//	}
//
//
//
//}
