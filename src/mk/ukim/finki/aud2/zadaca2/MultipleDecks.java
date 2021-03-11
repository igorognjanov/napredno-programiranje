package mk.ukim.finki.aud2.zadaca2;

public class MultipleDecks {
    public Deck [] decks;

    public MultipleDecks(int n){
        decks = new Deck[n];
        for(int i=0; i<n; i++){
            decks[i] = new Deck ();
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder ();
        for(Deck deck : decks) {
            sb.append (deck);
            sb.append ('\n');
        }
        return sb.toString ();
    }

    public static void main(String[] args) {
        MultipleDecks md = new MultipleDecks (3);
        System.out.println (md);
    }
}
