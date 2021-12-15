package story;

import utils.Thing;

import java.util.Objects;

public class Money implements Thing {
    protected int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        if (this.amount > 10000000) {
            return "значительная сумма денег";
        } else if (this.amount > 100000) {
            return "небольшая сумма денег";
        } else {
            return "ничтожная сумма денег";
        }
    }

    @Override
    public String toString() {
        if (this.amount == 1) {
            return "1 кредит";
        } else {
            return this.amount + "кредитов";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
