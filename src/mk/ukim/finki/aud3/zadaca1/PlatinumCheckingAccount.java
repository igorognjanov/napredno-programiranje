package mk.ukim.finki.aud3.zadaca1;

public class PlatinumCheckingAccount extends InterestCheckingAccount implements InterestBearingAccount{
    public PlatinumCheckingAccount(String name, int id, double amount) {
        super (name, id, amount);
    }

    @Override
    public void addInterest() {
        this.addAmount (this.getAmount () * 2 * INTEREST);
    }

}
