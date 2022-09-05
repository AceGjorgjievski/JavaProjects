package mk.ukim.finki.aps.labs.lab9.lavirint;




import mk.ukim.finki.aps.kodovi.kod4_StackQueue.ArrayStack;
import mk.ukim.finki.aps.kodovi.kod4_StackQueue.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

class Maze {
    Graph g;
    int startNode;
    int endNode;

    Map<String,Integer> map;

    public Maze() {
        map = new HashMap<>();
    }
            /*
6,6
######
# # ##
# # S#
# # ##
# E ##
######
         */


    public void generateGraph(int rows, int columns, String [] lines) {
        int numberOfNodes = 0;

        for(int i=1; i<rows-1; i++) {
            for(int j=1; j<columns-1; j++) {
                if(lines[i].charAt(j) != '#') {
                    String x = i+","+j;
                    map.put(x,numberOfNodes);

                    if(lines[i].charAt(j) == 'S') startNode = numberOfNodes;
                    if(lines[i].charAt(j) == 'E') endNode = numberOfNodes;

                    numberOfNodes++;
                }
            }
        }
        g = new Graph(numberOfNodes);
//        System.out.println(g);

        for(int i=1; i<rows-1; i++) {
            for(int j=1; j<columns-1; j++) {
                if(lines[i].charAt(j) != '#') {
                    if(lines[i].charAt(j-1) != '#') {
                        String x = i+","+j;
                        String y = i + ","+(j-1);
                        g.addEdge(map.get(x),map.get(y));
                    } else if(lines[i].charAt(j+1) != '#') {
                        String x = i+","+j;
                        String y = i+","+(j+1);
                        g.addEdge(map.get(x),map.get(y));
                    } else if(lines[i-1].charAt(j) != '#') {
                        String x = i+","+j;
                        String y = (i-1)+","+j;
                        g.addEdge(map.get(x),map.get(y));
                    } else if(lines[i+1].charAt(j) != '#') {
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
        //BFS
        Stack<Integer> integerStack = new ArrayStack<Integer>(g.getNum_nodes());
        boolean [] visited = new boolean[g.getNum_nodes()];

        visited[startNode] = true;
        integerStack.push(startNode);

        while (!integerStack.isEmpty() && integerStack.peek() != endNode) {
            int x = integerStack.peek();
            int x2 = x;
            for(int i=0; i<g.getNum_nodes(); i++) {
                if(g.adjacent(x,i) == 1) {
                    x2 = i;
                    if(!visited[i]) break;
                }
            }
            if(!visited[x2]) {
                visited[x2] = true;
                integerStack.push(x2);
            } else {
                integerStack.pop();
            }
        }

        Stack<Integer> tempStack = new ArrayStack<>(g.num_nodes);
        while (!integerStack.isEmpty()) tempStack.push(integerStack.pop());

        System.out.println("tuka radi");
        while (!tempStack.isEmpty()) System.out.print(tempStack.pop() + " ");


    }
}

public class Lavirint {

    public static void main(String[] args) throws IOException {
        /*
6,6
######
#S# E#
# # ##
#   ##
######
######
         */

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String [] parts = bf.readLine().split(",");

        int rows = Integer.parseInt(parts[0]);
        int columns = Integer.parseInt(parts[1]);

        String [] lines = new String[rows];

        for(int i=0; i<rows; i++) {
            lines[i] = bf.readLine();
        }

        Maze maze = new Maze();
        maze.generateGraph(rows,columns,lines);
        maze.findPath();

    }
}
