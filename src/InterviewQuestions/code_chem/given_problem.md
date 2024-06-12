Game of Marbles is a simple game played by one player and one game host.
Marbles in three different colors - green, yellow & blue - are put in a bowl.
The total number of marbles is not known to the player.
The game host starts the game by grabbing random marbles and then showing them to the player.
After the player has seen the marbles, the game host puts them back in the bowl.
He repeats this several times per game. 
The player plays several games and records the information from each game (your input).


Each game is recorded with its `ID` number (eg. `Game 1:` ...) followed by a semicolon-separated
list of marble subsets that were revealed from the bowl (eg. `3 yellow, 5 green, 4 blue`).


For example, the record of a few games might look like this:
```
5
Game 1: 2 blue, 4 yellow; 3 yellow, 2 green, 6 blue; 1 green
Game 2: 2 blue, 1 green; 3 green, 4 blue, 1 yellow; 1 green, 3 blue
Game 3: 3 green, 6 blue, 20 yellow; 5 blue, 6 yellow, 13 green; 5 green, 1 yellow
Game 4: 2 green, 3 yellow, 9 blue; 3 green, 9 yellow; 3 green, 15 blue, 14 yellow
Game 5: 6 yellow, 2 blue, 3 green; 2 blue, 3 yellow, 1 green
```
This is an example input. Each line (after the first) represents one game.
The first line represents the number of games. The starter code reads the input
and prints it to the standard output.


In Game 1, the game host draws marbles from the bowl in three separate rounds.
First, he grabs 2 blue and 4 yellow marbles. Then, returns those marbles in the
bowl and for the second round he grabs 3 yellow, 2 green and 6 blue marbles.
For the final (third) round he only grabs 1 green marble.


Your task is to determine which of the games provided in the input would have
been possible if the bowl contained only 12 yellow, 13 green and 14 blue marbles!


In the example above, games 1, 2, and 5 would have been possible if the bowl
had been loaded with that configuration. However, game 3 would have been
impossible because at one point the host showed the player 20 yellow marbles
at once; similarly, game 4 would also have been impossible because the host
showed the player 15 blue marbles at once.


To get the expected output you first need to find which games are possible.
Afterwards sum their ids and print that number to the standard output.
In this example input above, games with IDs: 1, 2 and 5 are possible,
so the result would be 8. (1 + 2 + 5 = 8)


Expected output:

``8``

Happy coding!

Start code:
```java
public class Program {
    public static void solution(String input) {
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
```
