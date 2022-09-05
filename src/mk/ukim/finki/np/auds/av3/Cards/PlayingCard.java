package mk.ukim.finki.np.auds.av3.Cards;

import java.util.Objects;

public class PlayingCard {
    private int rank;
    private PlayingCardType type;

    public PlayingCard(int rank, PlayingCardType type) {
        this.rank = rank;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%d %s", rank, type.name());//or.toString
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCard that = (PlayingCard) o;
        return rank == that.rank && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, type);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public PlayingCardType getType() {
        return type;
    }

    public void setType(PlayingCardType type) {
        this.type = type;
    }
}
