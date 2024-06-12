package InterviewQuestions.code_chem;

import java.util.*;
import java.util.stream.Collectors;

enum Color {
    BLUE, YELLOW, GREEN
}

class Marbles {
    Map<Color, Integer> marbleMap;

    public Marbles() {
        marbleMap = new HashMap<>();
    }

    public static Marbles createMarble(String marblesPart) {
        return null;
    }
}
/*
5
Game 1: 2 blue, 4 yellow; 3 yellow, 2 green, 6 blue; 1 green
Game 2: 2 blue, 1 green; 3 green, 4 blue, 1 yellow; 1 green, 3 blue
Game 3: 3 green, 6 blue, 20 yellow; 5 blue, 6 yellow, 13 green; 5 green, 1 yellow
Game 4: 2 green, 3 yellow, 9 blue; 3 green, 9 yellow; 3 green, 15 blue, 14 yellow
Game 5: 6 yellow, 2 blue, 3 green; 2 blue, 3 yellow, 1 green
 */

class Game {
    private int id;
    private List<Marbles> marbles;

    //Game 1: 2 blue, 4 yellow; 3 yellow, 2 green, 6 blue; 1 green
    public static Game createGame(String line) {
        String [] gamePart = line.split(": ");
        int gameId = Integer.parseInt(gamePart[0].split("\\s++")[1]);
        List<Marbles> marbles = new ArrayList<>();

        Arrays.stream(gamePart).skip(1).forEach(marblesPart -> {//2 blue, 4 yellow
            String [] numberColorsPar = marblesPart.split("; ");
            int numberOfMarbles = -1;



            for(String part1 : numberColorsPar) {
                String [] roundGameColor = part1.split(",");
                for(String part2 : roundGameColor) {
                    String [] numberColor = part2.split("\\s++");
                    int number = Integer.parseInt(numberColor[0]);//2
                    String colorName = numberColor[1];//blue
                }
            }
            marbles.add(Marbles.createMarble(marblesPart));



            if(numberColorsPar[0].split("\\s++")[1].equals("blue")) {
                numberOfMarbles = Integer.parseInt(numberColorsPar[0]);
            } else if(numberColorsPar[0].split("\\s++")[1].equals("yellow")) {
                numberOfMarbles = Integer.parseInt(numberColorsPar[0]);
            } else {
                //green
                numberOfMarbles = Integer.parseInt(numberColorsPar[0]);
            }
        });
        return new Game();
    }
}


public class Program {
    private static List<Game> games;


    public static void solution(String input) {
        String [] parts = input.split("\n");

        games = Arrays.stream(parts)
                        .map(line -> Game.createGame(line))
                                .collect(Collectors.toList());

        System.out.println(input);
    }

    // do not modify the code bellow this line
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        int numberOfLines = Integer.parseInt(scanner.nextLine());

        for (int i=0; i<numberOfLines; i++) {
            input += scanner.nextLine();
            if (i < numberOfLines-1) {
                input += "\n";
            }
        }

        solution(input);
        scanner.close();
    }
}