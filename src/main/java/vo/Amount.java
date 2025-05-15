package vo;

import java.util.Objects;

public class Amount {

    public static final int LOTTO_PRICES_UNIT = 1_000;
    private final int value;

    public Amount(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("금액은 0 또는 음수가 될 수 없습니다.");
        }
        if (value % 1000 != 0) {
            throw new InvalidAmountValueUnitExcpetion("로또는 1000원 단위로만 구매할 수 있습니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount amount)) return false;
        return getValue() == amount.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    static public class InvalidAmountValueUnitExcpetion extends IllegalArgumentException {
        public InvalidAmountValueUnitExcpetion(String s) {
            super(s);
        }
    }

}
