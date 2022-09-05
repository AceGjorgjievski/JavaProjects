package mk.ukim.finki.vezhbi.napredno.vzhb2.karti;

import java.util.Objects;

import static mk.ukim.finki.vezhbi.napredno.vzhb2.karti.PlayingCardType.HEARTS;

public class PlayingCard {
    private PlayingCardType cardType;
    private int range;

    public PlayingCard(PlayingCardType cardType, int range) {
        this.cardType = cardType;
        this.range = range;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cardType + " " + range);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return range == that.range && cardType == that.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, range);
    }

    public PlayingCardType getCardType() {
        return cardType;
    }

    public void setCardType(PlayingCardType cardType) {
        this.cardType = cardType;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public static void main(String[] args) {
        PlayingCard card = new PlayingCard(HEARTS,2);
        System.out.println(card);
    }
}
