package mk.ukim.finki.vezhbi.napredno.vzhb2.karti2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private PlayingCard [] cards;
    private boolean [] dealtCards;
    private int counter;

    public Deck() {
        cards = new PlayingCard[52];
        dealtCards = new boolean[52];
        counter = 0;

        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                cards[j+(13*i)] = new PlayingCard(PlayingCardType.values()[i],j+2);
            }
        }
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

    public void shuffle(){
        List<PlayingCard> lista = Arrays.asList(cards);
        Collections.shuffle(lista);
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println("1");
        deck.shuffle();
        System.out.println(deck);
    }
}
