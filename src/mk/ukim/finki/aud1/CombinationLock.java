package mk.ukim.finki.aud1;

public class CombinationLock {
    private int combination;
    private boolean isOpen;

    public CombinationLock(){
        this.combination = 0;
        isOpen = false;
    }

    public CombinationLock(int combination) {
        this.combination = combination;
        isOpen = false;
    }

    public void changeCombo(int oldCombo, int combination) {
        if(this.combination == oldCombo)
                this.combination = combination;
        if(isOpen == false) isOpen = true;
    }

    public void open(int combination){
        if(this.combination == combination)
            isOpen = true;
    }

}
