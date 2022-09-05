package mk.ukim.finki.aps.labs.lab9.lavirintvehb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


class Graph {
    int numerOfNodes;
    int[][] adjMat;

    Graph(int numerOfNodes) {
        this.adjMat = new int[numerOfNodes][numerOfNodes];
        this.numerOfNodes = numerOfNodes;
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[0].length; j++) {
                this.adjMat[i][j] = 0;
            }
        }
    }

    Graph(int numerOfNodes, int[][] adjMat) {
        this.numerOfNodes = numerOfNodes;
        this.adjMat = adjMat;
    }

    void addEdge(int x, int y) {
        adjMat[x][y] = 1;
        adjMat[y][x] = 1;
    }

    void deleteEdge(int x, int y) {
        adjMat[x][y] = 0;
        adjMat[y][x] = 0;
    }

    int adjacent(int x, int y) {
        return adjMat[x][y] == 1 ? 1 : 0;
    }

    int getNumerOfNodes() {
        return numerOfNodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < numerOfNodes; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < numerOfNodes; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < numerOfNodes; j++) {
                sb.append(adjMat[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

class Maze {
    int start;
    int end;
    Graph g;
    Map<String, Integer> map;

    Maze() {
        map = new HashMap<>();
    }


    public void generateGraph(int rows, int columns, String[] in) {
        int numNodes = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (in[i].charAt(j) != '#') {
                    String x = i + "," + j;
                    map.put(x, numNodes);

                    if (in[i].charAt(j) == 'S') start = numNodes;
                    if (in[i].charAt(j) == 'E') end = numNodes;

                    numNodes++;
                }
            }
        }

        g = new Graph(numNodes);
//        System.out.println(g);

        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(in[i].charAt(j) != '#'){
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

    public void findPath() {
        boolean [] visited = new boolean[g.getNumerOfNodes()];
        visited[start] = true;
        Stack<Integer> s = new Stack<>();

        s.push(start);

        while (!s.isEmpty() && s.peek() != end){
            int pom = s.peek();
            int pom2 = pom;
            for(int i=0; i<g.getNumerOfNodes(); i++){
                if(g.adjacent(pom,i) == 1){
                    pom2 = i;
                    if(!visited[i]) break;
                }
            }
            if(!visited[pom2]){
                visited[pom2] = true;
                s.push(pom2);
            } else {
                s.pop();
            }
        }

        Stack<Integer> printPath = new Stack<>();
        while(!s.isEmpty()) printPath.push(s.pop());

        while(!printPath.isEmpty()) System.out.println(printPath.pop());

    }
}

public class Lavirint {


    /*
        6,6
        ######
        #S# E#
        # # ##
        #   ##
        ######
        ######
         */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(",");
        int rows = Integer.parseInt(parts[0]);
        int columns = Integer.parseInt(parts[1]);

        String[] lines = new String[rows];
        for (int i = 0; i < rows; i++) {
            lines[i] = br.readLine();
        }
        Maze maze = new Maze();
        maze.generateGraph(rows, columns, lines);
        maze.findPath();

    }


}
