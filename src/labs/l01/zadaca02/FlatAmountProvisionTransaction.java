package labs.l01.zadaca02;

import java.text.DecimalFormat;
import java.util.Objects;

public class FlatAmountProvisionTransaction extends Transaction {
    public String flatProvision;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        if (!super.equals (o)) return false;
        FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
        return flatProvision == that.flatProvision;
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode (), flatProvision);
    }

    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision){
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    public String getFlatAmount(){
        return flatProvision;
    }

    public static void f(Transaction fa, Transaction fp){
        System.out.println (fa.getClass ());
    }

    public static void main(String[] args) {
        FlatAmountProvisionTransaction fa =
                new FlatAmountProvisionTransaction (12312312, 32132132, "100.00$", "10.00%");
        FlatPercentProvisionTransaction fp =
                new FlatPercentProvisionTransaction (45645645, 65465465, "20.00%", 8);
        f(fa, fp);
        Double d = 2.00;
        DecimalFormat decimalFormat = new DecimalFormat ("#.00");
        System.out.println (d);
        String str = decimalFormat.format (d);
        System.out.println (str);

    }

}
