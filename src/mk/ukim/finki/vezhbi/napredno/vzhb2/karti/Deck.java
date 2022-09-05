package mk.ukim.finki.vezhbi.napredno.vzhb2.karti;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private PlayingCard [] cards;
    private int counter;
    private boolean [] dealtCards;


    public Deck(){
        cards = new PlayingCard[52];
        counter = 0;
        dealtCards = new boolean[52];
        for(int i=0; i<PlayingCardType.values().length; i++){
            for(int j=0; j<13; j++){
                cards[(13*i) + j] = new PlayingCard(PlayingCardType.values()[i],j+2);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(PlayingCard card : cards){
            stringBuilder.append(card);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public PlayingCard dealCard(){
        if(counter == 52) return null;
        int num = (int)(Math.random()*52);
        if(!dealtCards[num]){
            counter++;
            dealtCards[num] = true;
            return cards[num];
        }
        return dealCard();
    }

    public PlayingCard [] shuffle(){
        List<PlayingCard> lista = Arrays.asList(cards);
        Collections.shuffle(lista);
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Arrays.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cards);
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println("\n\n");

        deck.shuffle();
        System.out.println("\n\n" + deck);

    }
}
