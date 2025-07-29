package staffs.common.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money extends ValueObject {
    private BigDecimal amount;

    public Money(BigDecimal amount) {
        assertArgumentNotEmpty(amount.toString(),"Money cannot be empty");
        assertValueIsGreaterThan(amount, BigDecimal.ZERO,"Money cannot be greater than zero");
        this.amount = amount;
    }

    public Money(String s) {
        this.amount = new BigDecimal(s);
    }

    public Money(int i) {
        this.amount = new BigDecimal(i);
    }

    public Money(double d){
        this.amount = new BigDecimal(d);
    }

    //Shallow copy
    public Money(Money money){
        this(money.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;
        return this.amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.format("amount %s", amount);
    }

    public Money add(Money delta) {
        return new Money(amount.add(delta.amount));
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public BigDecimal asBigDecimal() {return amount;}

    public Money multiply(int x) {
        return new Money(amount.multiply(new BigDecimal(x)));
    }
}

