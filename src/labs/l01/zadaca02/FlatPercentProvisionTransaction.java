package labs.l01.zadaca02;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction{
    private int centsPerDollar;

    FlatPercentProvisionTransaction (long fromId, long toId, String amount, int centsPerDolar){
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDolar;
    }

    public int getPercent(){
        return centsPerDollar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return centsPerDollar == that.centsPerDollar;
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), centsPerDollar);
    }

    public static void main(String[] args) {
        FlatPercentProvisionTransaction f = new FlatPercentProvisionTransaction (1292193, 2139123, "100.00$", 10);
        Double n = 20.2;
        System.out.println (n);
    }

}
