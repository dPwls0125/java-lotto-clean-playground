package domain;

import java.util.Objects;

import static constant.LottoConstants.LOTTO_PRICES_UNIT;

public class TicketCount {
    private int value;

    public TicketCount(final int value) {
        validatePositiveNumber(value);
        this.value = value;
    }

    public void subtractTicketCount(final ManualLottoCount manualLottoCount){
        value -= manualLottoCount.getValue();
    }

    private void validatePositiveNumber(int value){
        if(value <= 0) {
            throw new IllegalArgumentException("티켓의 갯수는 0 또는 음수가 될 수 없습니다.");
        }
    }

    public static TicketCount from(Amount amount) {
        return new TicketCount(amount.getValue() / LOTTO_PRICES_UNIT);
    }

    public int getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketCount that)) return false;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
