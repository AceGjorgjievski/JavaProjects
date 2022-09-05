package mk.ukim.finki.aps.kodovi.kod9_Graph.maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Maze {
	
	//Kje se koristi nenasocen graf implementiran so martica na sosednost
	//za pretstavuvanje na lavirintot
	//Teminjata vo grafot se samo indeksi
	//Iminjata na teminjata se preveduvaat vo broevi preku hash tabela
	//T.e. na pr. 1,1 kje se prevede vo 1 (1,1 kje se zacuva kako string)
	Graph g;
	Map<String,Integer> map;
	int start;
	int end;

	public Maze (){
		map = new HashMap<>();
	}

	private void generateGraph(int rows, int columns, String[] in) {
		int totalNodes = 0;

		for(int i=1; i<rows-1; i++){
			for(int j=1; j<columns-1; j++){
				if(in[i].charAt(j) != '#') {
					String key = i+","+j;
					map.put(key,totalNodes);

					if(in[i].charAt(j) == 'S') start = totalNodes;
					if(in[i].charAt(j) == 'E') end = totalNodes;

					totalNodes++;
				}
			}
		}

		g = new Graph(totalNodes);



		for(int i=1; i<rows-1; i++){
			for(int j=1; j<columns-1; j++){
				if(in[i].charAt(j) != '#') {
					if(in[i].charAt(j-1) != '#'){
						String x = i+","+j;
						String y = i+","+(j-1);
						g.addEdge(map.get(x),map.get(y));
					}
					if(in[i].charAt(j+1) != '#'){
						String x = i+","+j;
						String y = i+","+(j+1);
						g.addEdge(map.get(x),map.get(y));
					}
					if(in[i-1].charAt(j) != '#'){
						String x = i+","+j;
						String y = (i-1)+","+j;
						g.addEdge(map.get(x),map.get(y));
					}
					if(in[i+1].charAt(j) != '#'){
						String x = i+","+j;
						String y = (i+1)+","+j;
						g.addEdge(map.get(x),map.get(y));
					}
				}
			}
		}

		System.out.println(g);
	}

	private void findPath() {
		boolean visited[] = new boolean[g.num_nodes];
		for (int i = 0; i < g.num_nodes; i++)
			visited[i] = false;

		visited[start] = true;
//		System.out.println(start);

		Stack<Integer> s = new Stack<Integer>();
		s.push(start);

		int pom;
		while (!s.isEmpty() && s.peek() != end) {
			pom = s.peek();
			int pom1 = pom;
			for (int i = 0; i < g.num_nodes; i++) {
				if (g.adjacent(pom, i) == 1) {
					pom1 = i;
					if (!visited[i])
						break;
				}
			}
			if (!visited[pom1]) {
				visited[pom1] = true;
//				System.out.println(pom1);
				s.push(pom1);
			} else {
				s.pop();
			}
		}

		Stack<Integer> pomoshen = new Stack<>();
		while (!s.isEmpty())
			pomoshen.push(s.pop());
		while (!pomoshen.isEmpty())
			System.out.println(pomoshen.pop());
	}
	
	public static void main(String args[]){
		Maze m = new Maze();
		int rows = 6;
		int columns = 6;
		String[] in = new String[rows];
		
		in[0] = "######";
		in[1] = "#E# ##";
		in[2] = "# # S#";
		in[3] = "#   ##";
		in[4] = "######";
		in[5] = "######";
		
		m.generateGraph(rows, columns, in);
		System.out.println("Pateka:");
		m.findPath();
	}

}
