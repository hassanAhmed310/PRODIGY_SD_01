package sample;

public class Value <K, V> {
    private K unit;
    private V amount;

    public Value(V amount, K unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public V getAmount() {
        return amount;
    }

    public void setAmount(V amount) {
        this.amount = amount;
    }

    public K getUnit() {
        return unit;
    }

    public void setUnit(K unit) {
        this.unit = unit;
    }

}
