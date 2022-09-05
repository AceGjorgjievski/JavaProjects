package mk.ukim.finki.vezhbi.algoritmi.vezhb2.graphVezhbi.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Graph{
    int [][]adjacentMatrix;
    int size;

    public Graph(int size) {
        this.size = size;
        adjacentMatrix = new int[size][size];
    }
    public int checkAdjacent(int x,int y){
        return adjacentMatrix[x][y] != 0 ? 1 : 0;
    }
    public void addEdge(int x, int y){
        if(adjacentMatrix[x][y] != 1) adjacentMatrix[x][y] = 1;
        if(adjacentMatrix[y][x] != 1) adjacentMatrix[y][x] = 1;
    }
    public void removeEdge(int x, int y){
        if(adjacentMatrix[x][y] != 0) adjacentMatrix[x][y] = 0;
        if(adjacentMatrix[y][x] != 0) adjacentMatrix[y][x] = 0;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for(int i=0; i<size; i++){
            sb.append(i).append(" ");
        }
        sb.append("\n");

        for(int i=0; i<size; i++){
            sb.append(i).append(" ");
            for(int j=0; j<size; j++){
                sb.append(adjacentMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

public class MazeTest {

    Map<String,Integer> map;
    int startNode;
    int endNode;
    Graph g;

    public MazeTest(){
        map = new HashMap<>();
    }


    public void generateGraph(int rows, int columns, String[] lines) {

        int numberOfNode = 0;
        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(lines[i].charAt(j) != '#'){
                    String key = i + "," + j;
                    map.put(key,numberOfNode);

                    if(lines[i].charAt(j) == 'S') startNode = numberOfNode;
                    if(lines[i].charAt(j) == 'E') endNode = numberOfNode;

                    numberOfNode++;
                }
            }
        }

        g = new Graph(numberOfNode);
        System.out.println(g);

                        /*
########
#S#    #
# # ## #
#   ## #
###### #
##   # #
#E #   #
########
         */

        for(int i=1; i<rows-1; i++){
            for(int j=1; j<columns-1; j++){
                if(lines[i].charAt(j) != '#'){
                    if(lines[i].charAt(j+1) != '#'){
                        String x = i + "," + j;
                        String y = i + "," + (j+1);
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(lines[i].charAt(j-1) != '#'){
                        String x = i + "," + j;
                        String y = i + "," + (j-1);
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(lines[i-1].charAt(j) != '#'){
                        String x = i + "," + j;
                        String y = (i-1) + "," + j;
                        g.addEdge(map.get(x),map.get(y));
                    }
                    if(lines[i+1].charAt(j) != '#'){
                        String x = i + "," + j;
                        String y = (i+1) + "," + j;
                        g.addEdge(map.get(x),map.get(y));
                    }
                }
            }
        }

        System.out.println(g);


    }

    public void findPath(){
        boolean [] visited = new boolean[g.getSize()];
        visited[startNode] = true;

        Stack<Integer> s = new Stack<>();
        s.push(startNode);

        while (!s.isEmpty() && s.peek() != endNode) {
            int temp = s.peek();
            int temp2 = temp;
            for(int i=0; i<g.getSize(); i++){
                if(g.checkAdjacent(temp2, i) == 1) {
                    temp2 = i;
                    if(!visited[i]) break;
                }
            }
            if(!visited[temp2]){
                visited[temp2] = true;
                s.push(temp2);
            } else {
                s.pop();
            }
        }

        Stack<Integer> s2 = new Stack<>();
        while(!s.isEmpty()){
            s2.push(s.pop());
        }
        while (!s2.isEmpty()){
            System.out.println(s2.pop());
        }
    }

    public static void main(String[] args) {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int columns=0,rows=0;
        String line = null;
        try {
            line = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String [] parts = line.split(",");
        //6,7
        //parts[0] = 6
        //parts[1] = 7

        rows = Integer.parseInt(parts[0]);
        columns = Integer.parseInt(parts[1]);

        String [] lines = new String[rows];
        for(int i=0; i<rows; i++){
            try {
                lines[i] = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MazeTest mazeTest = new MazeTest();
        mazeTest.generateGraph(rows,columns,lines);
        mazeTest.findPath();

    }

}
