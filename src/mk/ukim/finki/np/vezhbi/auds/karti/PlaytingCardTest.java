package mk.ukim.finki.np.vezhbi.auds.karti;


import java.util.*;
import java.util.stream.Collectors;

enum TYPE {
    HEARTS,DIAMONDS,SPADES,CLUBS;
}

class PlayingCard {
    private TYPE type;
    private int range;


    public PlayingCard(int range, TYPE type) {
        this.type = type;
        this.range = range;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(range).append(" ").append(type).append("\n");
        return sb.toString();
    }
}

class Deck {
    private PlayingCard [] cards;
    private boolean [] isDealt;
    private int total;
    private Random random;

    public Deck() {
        cards = new PlayingCard[52];
        isDealt = new boolean[52];
        random = new Random();
        total = 0;

        for(int i = 0; i< TYPE.values().length; i++) {
            for(int j=0; j<13; j++) {
                cards[(13*i)+j] = new PlayingCard(j+1,TYPE.values()[i]);
            }
        }
    }

    public boolean hasCardsLeft(){
        return (cards.length - total) > 0;
    }

    public void shuffle(){
        Collections.shuffle(Arrays.asList(cards));
    }

    public PlayingCard dealCard(){
        int number = (int)(Math.random() * 52);
        int number2 = random.nextInt(52);

        if(!hasCardsLeft()) return null;

        if(!isDealt[number2]) {
            isDealt[number2] = true;
            if(total %10 == 0) System.out.println();
            total++;
            return cards[number2];
        }

        return dealCard();
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(PlayingCard card : cards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }
}

class MultipleDeck {
    private Deck [] decks;

    public MultipleDeck(int size) {
        decks = new Deck[size];
        for(int i=0; i<size; i++) {
            decks[i] = new Deck();
        }
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for(Deck deck : decks) {
//            sb.append(deck.toString()).append("\n");
//        }
//        return sb.toString();
        return Arrays.stream(decks)
                .map(Deck::toString)
                .collect(Collectors.joining("\n\n"));
    }
}

public class PlaytingCardTest {
    public static void main(String[] args) {

        PlayingCard playingCard;
        Deck deck = new Deck();
        System.out.print(deck);
        System.out.println("==============");

//        while ((playingCard = deck.dealCard()) != null) {
//            System.out.print(playingCard);
//        }

        MultipleDeck multipleDeck = new MultipleDeck(3);

        System.out.println(multipleDeck);
    }
}
