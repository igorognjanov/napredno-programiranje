package labs.l01.zadaca02;

import java.util.Objects;

public abstract class Transaction {
    protected long fromId;
    protected long toId;
    protected String amount;
    protected String description;

    public Transaction(){
    }

    public Transaction(long fromId, long toId, String amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public Transaction(long fromId, long toId, String description, String amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Transaction that = (Transaction) o;
        return fromId == that.fromId &&
                toId == that.toId &&
                Objects.equals (amount, that.amount) &&
                Objects.equals (description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash (fromId, toId, amount, description);
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getAmount() {
        return amount;
    }

}
