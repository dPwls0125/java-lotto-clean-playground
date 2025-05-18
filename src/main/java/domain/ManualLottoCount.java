package domain;

public class ManualLottoCount {
    private final int value;

    public ManualLottoCount(int value, TicketCount ticketCount) {
        validate(value, ticketCount);
        this.value = value;
    }

    private void validate(int value, TicketCount ticketCount) {
        if (value < 0) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        }
        if (value > ticketCount.getValue()) {
            throw new IllegalArgumentException("구입 금액을 초과한 수동 구매 수입니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
