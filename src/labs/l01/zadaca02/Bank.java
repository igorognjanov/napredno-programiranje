package labs.l01.zadaca02;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account[] niza;
    private Double vkupnoTransferi;
    private Double vkupnoProvizija;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Bank bank = (Bank) o;
        if(this.niza.length != bank.niza.length) return false;
        for(int i=0; i<this.niza.length; i++) if(!this.niza[i].equals(bank.niza[i])) return false;
        return Objects.equals (name, bank.name) &&
                Objects.equals (vkupnoTransferi, bank.vkupnoTransferi) &&
                Objects.equals (vkupnoProvizija, bank.vkupnoProvizija);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash (name, vkupnoTransferi, vkupnoProvizija);
        result = 31 * result + Arrays.hashCode (niza);
        return result;
    }
}
