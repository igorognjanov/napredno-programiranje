package mk.ukim.finki.aud2.zadaca2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private PlayingCard [] cards;
    private boolean [] picked;
    private int pickedTotal;

    public Deck(){
        cards = new PlayingCard[52];
        picked = new boolean[52];
        pickedTotal = 0;

        for(Integer i=0; i<PlayingCard.type.values ().length; i++){
            for(int j=0; j<13; j++){
                cards[j + 13 * i] = new PlayingCard (j+1, PlayingCard.type.values()[i]);
            }
        }
    }

    public void showCards(){
        for(int i=0; i<52; i++){
            System.out.println (cards[i]);
        }
    }
    public PlayingCard[] getCards() {
        return cards;
    }

    public void setCards(PlayingCard[] cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass () != o.getClass ()) return false;
        Deck temp = (Deck)o;
        return Arrays.equals (cards, temp.cards);
    }
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = (prime * result) + Arrays.hashCode (cards);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder ();
        for(PlayingCard card:cards){
            s.append (card);
            s.append ("\n");
        }
        return s.toString ();
    }
    public PlayingCard[] shuffle(){
        List<PlayingCard> playingCardsList = Arrays.asList (cards);
        Collections.shuffle (playingCardsList);
        return cards;
    }
    public PlayingCard deal(){
        if(pickedTotal == 52) return null;
        int card = (int)(Math.random () * 52.0);

        if(!picked[card]){
            picked[card] = true;
            pickedTotal ++;
            return cards[card];
        }
        return deal();
    }

    public boolean hasCards(){
        return pickedTotal > 0;
    }


    public static void main(String[] args) {
        Deck d = new Deck ();
        System.out.println (d);
        d.shuffle ();
        System.out.println (d.toString ());
        System.out.println ();
    }

}
