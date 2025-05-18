package vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vo.Amount;
import vo.TicketCount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketCountTest {
    @ParameterizedTest(name = "value가 {0}일때 ticketCount 생성")
    @DisplayName("유효한 값이 들어오면 ticketCount를 생성한다.")
    @ValueSource(ints = {1,100,1000,123})
    void whenValidInputCome_thenCreateAmount(int value){
        TicketCount ticketCount = new TicketCount(value);
        assertThat(ticketCount.getValue()).isEqualTo(value);
    }

    @ParameterizedTest(name = "value가 {0} => throw IllegalArgumentException")
    @DisplayName("음수 또는 0의 값의 경우 예외를 던진다.")
    @ValueSource(ints = {0,-1,-100})
    void whenValueIsNotPositive_thenThrowsException(int value){
        assertThatThrownBy(() -> new Amount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
