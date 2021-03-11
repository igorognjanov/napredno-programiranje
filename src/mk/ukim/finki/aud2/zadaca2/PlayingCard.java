package mk.ukim.finki.aud2.zadaca2;

import java.util.Objects;

public class PlayingCard {
    public enum type{srce, baklava, detelina, list}

    public type cardType;
    public int rank;

    public PlayingCard(int rank, type cardType){
        this.cardType = cardType;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        PlayingCard that = (PlayingCard) o;
        return rank == that.rank &&
                cardType == that.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash (cardType, rank);
    }

    @Override
    public String toString(){
        return rank + " " + cardType.toString ();
    }


}
