package mk.ukim.finki.np.auds.av3.Cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private PlayingCard[] cards;
    private boolean[] isDealt;
    private int dealtTotal;

    public Deck() {
        cards = new PlayingCard[52];
        isDealt = new boolean[52];
        dealtTotal = 0;
        for(int i=0; i<PlayingCardType.values().length;i++){
            for(int j=0; j<13; j++){
                cards[i*13+j] = new PlayingCard(j+2,PlayingCardType.values()[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(PlayingCard card : cards){
            stringBuilder.append(card.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
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

    public PlayingCard[] getCards() {
        return cards;
    }

    public void setCards(PlayingCard[] cards) {
        this.cards = cards;
    }

    public boolean hasCardsLeft(){
        return (cards.length - dealtTotal) > 0;
    }

    public PlayingCard[] shuffle(){
        //Arrays - operation with arrays
        // Arrays.sort(cards);
        List<PlayingCard> playingCardList = Arrays.asList(cards);
        //Collections
        Collections.shuffle(playingCardList);
        return cards;
    }

    public PlayingCard dealCard(){
        if(!hasCardsLeft())
            return null;

//           Random random = new Random();
//           random.nextInt(100);
        int card = (int)(Math.random()*52);
        if(!isDealt[card]){
            isDealt[card] = true;
            dealtTotal++;
            return cards[card];
        }
        return dealCard();
    }

    public void dealCardSecondWay(){
        shuffle();
        for (PlayingCard card : cards){
            System.out.println(card);
        }
    }
}
