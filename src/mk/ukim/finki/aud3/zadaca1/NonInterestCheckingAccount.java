package mk.ukim.finki.aud3.zadaca1;

public class NonInterestCheckingAccount extends Account {

    public NonInterestCheckingAccount(String name, int id, double amount) {
        super (name, id, amount);
    }

    public double getAmount(){
        return this.amount;
    }

}
