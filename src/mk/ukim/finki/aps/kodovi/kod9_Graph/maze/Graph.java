package mk.ukim.finki.aps.kodovi.kod9_Graph.maze;

public class Graph {
	//matrica nenasochen
	int num_nodes; // broj na jazli
    int adjMat[][];  // matrica na sosednost
    
	public Graph(int num_nodes) {
		this.num_nodes = num_nodes;
		adjMat = new int[num_nodes][num_nodes];
		
		for(int i=0;i<this.num_nodes;i++)
			for(int j=0;j<this.num_nodes;j++)
				adjMat[i][j]=0;
	}

	public Graph(int num_nodes, int[][] adjMat) {
		this.num_nodes = num_nodes;
		this.adjMat = adjMat;
	}


	int adjacent(int x,int y)
	{  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
	   return (adjMat[x][y]!=0)?1:0;
	}
	
	void addEdge(int x,int y)
	{  // dodava vrska megu jazlite so indeksi x i y
	   adjMat[x][y]=1;
	   adjMat[y][x]=1;
	}
	
	void deleteEdge(int x,int y)
	{
	   // ja brise vrskata megu jazlite so indeksi x i y
	   adjMat[x][y]=0;
	   adjMat[y][x]=0;
	}
	
	public int getNum_nodes() {
		return num_nodes;
	}

	public void setNum_nodes(int num_nodes) {
		this.num_nodes = num_nodes;
	}



	@Override
	public String toString() {
		StringBuilder ret= new StringBuilder("  ");
		for(int i=0;i<num_nodes;i++)
			ret.append(i).append(" ");
		ret.append("\n");
		for(int i=0;i<num_nodes;i++){
			ret.append(i).append(" ");
			for(int j=0;j<num_nodes;j++)
				ret.append(adjMat[i][j]).append(" ");
			ret.append("\n");
		}
		return ret.toString();
	}
	
	
}
