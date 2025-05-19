package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class WinningResultTest {

    @Test
    @DisplayName("getCountof는 각 랭크에 대한 로또 일치 갯수를 반환한다.")
    void getCountOf_ReturnCorrespondNumberPerRank() {
        WinningResult result = new WinningResult();
        result.addResult(LottoRank.FIRST);
        result.addResult(LottoRank.SECOND);
        result.addResult(LottoRank.SECOND);

        assertAll(
                () -> assertThat(result.getCountOf(LottoRank.FIRST)).isEqualTo(1),
                () -> assertThat(result.getCountOf(LottoRank.SECOND)).isEqualTo(2),
                () -> assertThat(result.getCountOf(LottoRank.THIRD)).isEqualTo(0),
                () -> assertThat(result.getCountOf(LottoRank.FOURTH)).isEqualTo(0),
                () -> assertThat(result.getCountOf(LottoRank.FIFTH)).isEqualTo(0),
                () -> assertThat(result.getCountOf(LottoRank.NONE)).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("자신(winningResult)의 결과를 기반으로 yield 값을 반환한다.")
    void getYield_ReturnCorrectYieldValue() {
        WinningResult result = new WinningResult();
        result.addResult(LottoRank.FIRST);

        Amount amount = new Amount(1000);
        double yield = result.getYield(amount);

        assertThat(yield).isEqualTo(2_000_000);
    }

    @Test
    @DisplayName("수익률에 대한 손익 여부를 반환한다.")
    void getIsItLoss_thenReturnCorrectLossOrProfit_whenGivenYield() {
        WinningResult expectedLossResult = new WinningResult();
        expectedLossResult.addResult(LottoRank.NONE);

        WinningResult expectedProfitResult = new WinningResult();
        expectedProfitResult.addResult(LottoRank.FIRST);

        double yield1 = expectedLossResult.getYield(new Amount(1000));
        double yield2 = expectedProfitResult.getYield(new Amount(1000));

        assertAll(
                () -> assertThat(expectedLossResult.getIsItLoss(yield1)).isTrue(),
                () -> assertThat(expectedProfitResult.getIsItLoss(yield2)).isFalse()
        );
    }
}
