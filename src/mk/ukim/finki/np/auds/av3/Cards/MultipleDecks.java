package mk.ukim.finki.np.auds.av3.Cards;

public class MultipleDecks {
    private Deck [] decks;

    public MultipleDecks(int numOfDecks) {
        this.decks = new Deck[numOfDecks];
        for(int i=0; i<numOfDecks; i++){
            decks[i] = new Deck();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Deck deck : decks){
            stringBuilder.append(decks.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
