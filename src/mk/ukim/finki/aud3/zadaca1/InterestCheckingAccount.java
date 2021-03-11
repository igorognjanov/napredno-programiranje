package mk.ukim.finki.aud3.zadaca1;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {
    public static double INTEREST;

    public InterestCheckingAccount(String name, int id, double amount) {
        super (name, id, amount);
    }

    public double getAmount(){
        return this.amount;
    }

    public static void changeInterest(double interest){
        INTEREST = interest;
    }

    @Override
    public void addInterest() {
        this.addAmount (getAmount ()*INTEREST);
    }
}
