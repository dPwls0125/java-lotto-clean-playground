package vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ManualLottoCount 테스트")
class ManualLottoCountTest {

    @Test
    @DisplayName("수동 구매 수가 음수면 예외를 던진다.")
    void throwsExceptionWhenManualCountIsNegative() {
        TicketCount ticketCount = new TicketCount(4);
        assertThatThrownBy(() -> new ManualLottoCount(-1, ticketCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 구매 수가 구매 가능 수보다 크면 예외를 던진다.")
    void throwsExceptionWhenManualCountExceedsPurchasableCount() {
        TicketCount ticketCount = new TicketCount(4);
        assertThatThrownBy(() -> new ManualLottoCount(5, ticketCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 구매 수가 유효하면 정상 생성된다.")
    void createsManualLottoCountWhenInputIsValid() {
        TicketCount ticketCount = new TicketCount(4);
        ManualLottoCount manualCount = new ManualLottoCount(2, ticketCount);
        assertThat(manualCount.getValue()).isEqualTo(2);
    }
}
