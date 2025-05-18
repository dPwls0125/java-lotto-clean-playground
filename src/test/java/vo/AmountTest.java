package vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AmountTest {

    @Test
    @DisplayName("유효한 값이 들어오면 Amount를 생성한다.")
    void whenValidInputCome_thenCreateAmount(){
        Amount amount = new Amount(Amount.LOTTO_PRICES_UNIT * 5);
        assertThat(amount.getValue()).isEqualTo(Amount.LOTTO_PRICES_UNIT * 5);
    }

    @Test
    @DisplayName("1000원 단위가 아닌 경우 예외를 던진다.")
    void whenValueIsNotInThousandUnit_thenThrowsException() {
        assertThatThrownBy(() -> new Amount(Amount.LOTTO_PRICES_UNIT - 1))
                .isInstanceOf(Amount.InvalidAmountValueUnitExcpetion.class);
    }

    @DisplayName("금액에 음수 또는 0의 값이 들어온 경우 예외를 던진다.")
    @ParameterizedTest(name = "value가 {0} => throw IllegalArgumentException")
    @ValueSource(ints = {0,-1 * Amount.LOTTO_PRICES_UNIT,-2 * Amount.LOTTO_PRICES_UNIT})
    void whenValueIsNotPositive_thenThrowsException(int value){
        assertThatThrownBy(() -> new Amount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
