package mk.ukim.finki.aps.kodovi.kod9_Graph.maze2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;



//vezhbanje


class Graph {
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


public class Maze {

     private int startNode;
     private int endNode;
     private Map<String, Integer> map;
     private Graph g;

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    Maze(){
        map = new HashMap<>();
    }

    public  void generateGraph(int rows, int columns, String [] in){
        int numNodes = 0;

        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(in[i].charAt(j) != '#') {
                    String x = i + "," + j;
                    map.put(x,numNodes);

                    if(in[i].charAt(j) == 'S') startNode = numNodes;
                    if(in[i].charAt(j) == 'E') endNode = numNodes;

                    numNodes++;
                }
            }
        }

        g = new Graph(numNodes);

//        System.out.println(g);
        /*
        6,6
        ######
        #S# E#
        # # ##
        #   ##
        ######
        ######
         */

        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(in[i].charAt(j) != '#') {
                    if(in[i].charAt(j-1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j-1);
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(in[i].charAt(j+1) != '#') {
                        String x = i + "," + j;
                        String y = i + "," + (j+1);
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(in[i-1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i-1) + "," + j;
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(in[i+1].charAt(j) != '#') {
                        String x = i + "," + j;
                        String y = (i+1) + "," + j;
                        g.addEdge(map.get(x),map.get(y));
                    }
                }
            }
        }

//        System.out.println(g);

    }

    public void findPath(){
        boolean [] visited = new boolean[g.getNum_nodes()];

        for(int i=0; i<g.getNum_nodes(); i++){
            visited[i] = false;
        }

        visited[startNode] = true;
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty() && stack.peek() != endNode) {
            int pom = stack.peek();
            int pom2 = pom;
            for(int i=0; i<g.getNum_nodes(); i++) {
                if(g.adjacent(pom,i) == 1){
                    pom2 = i;
                    if(!visited[i]) break;
                }
            }

            if(!visited[pom2]) {
                visited[pom2] = true;
                stack.push(pom2);
            } else {
                stack.pop();
            }
        }
        Stack<Integer> tempStack = new Stack<>();
        while(!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        while(!tempStack.isEmpty()) {
            System.out.println(tempStack.pop());
        }

    }

    public static void main(String[] args) {
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
