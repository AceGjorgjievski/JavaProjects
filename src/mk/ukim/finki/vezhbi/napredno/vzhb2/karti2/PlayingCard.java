package mk.ukim.finki.vezhbi.napredno.vzhb2.karti2;

public class PlayingCard {
    private int range;
    private PlayingCardType type;

    public PlayingCard(PlayingCardType type, int range) {
        this.range = range;
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public PlayingCardType getType() {
        return type;
    }

    public void setType(PlayingCardType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ""+range+" "+type;
    }
}
