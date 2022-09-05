package mk.ukim.finki.aps.kodovi.kod9_Graph.matrica_nasocen;


public class GraphTester {

	public static void main(String[] args) {

		Integer [] niza = {0,1,2,3,4,5,6,7};
		Graph<Integer> g = new Graph<Integer>(8,niza);



		g.addEdge(0,5);
		g.addEdge(0,3);
		g.addEdge(0,7);
		g.addEdge(1,0);
		g.addEdge(1,2);
		g.addEdge(1,4);
		g.addEdge(3,2);
		g.addEdge(4,3);
		g.addEdge(5,1);
		g.addEdge(5,2);
		g.addEdge(7,1);
		g.addEdge(7,6);

		System.out.println(g);
		System.out.println();
		g.dfsNonrecursive(5);
		System.out.println();
		g.bfs(5);
		System.out.println();
		g.bfs2Practise(5);



//		Character niza[] = new Character[10];
//		//Postavuvanje na bukvite od A do J vo niza
//		for(int i=0;i<10;i++)
//			niza[i] = (char)((int)'A'+i);
//
//		//Kreiranje na graf koj kako teminja gi sodrzi bukvite od nizata
//		Graph<Character> g = new Graph<Character>(10,niza);
//
//		//Kreiranje na 15 slucajni rebra megju teminjata
//		int x, y;
//		Random r = new Random();
//		for(int i=0;i<15;i++){
//			x = r.nextInt(10);
//			y = r.nextInt(10);
//			if(x!=y){
//				if(g.adjacent(x, y)!=1){
//					g.addEdge(x, y);
//					System.out.println(i+": Dodavam nasoceno rebro od "+niza[x]+" do "+niza[y]);
//				}
//				else
//					i--;
//			}
//			else
//				i--;
//		}
//		System.out.println(g);
//		System.out.println("Depth First Search Recursive:");
//		g.dfsSearch(0);
//		System.out.println("Depth First Search Nonrecursive:");
//		g.dfsNonrecursive(0);
//		System.out.println("Breath First Search:");
//		g.bfs(0);
	}

}
