package domain;

import org.junit.jupiter.api.Test;
import vo.Amount;
import vo.WinningResult;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class YieldCalculatorTest {

    @Test
    void testCalculatingYield(){
        Map<LottoRank,Integer> WinningResultMap = Map.of(
                LottoRank.FIRST, 1,
                LottoRank.SECOND, 1,
                LottoRank.THIRD, 1,
                LottoRank.FOURTH, 1,
                LottoRank.NONE, 2
        );

        Amount amount = new Amount(6000);
        WinningResult winningResult = new WinningResult(WinningResultMap);

        double expected = 333_592.5;
        assertThat(YieldCalculator.calculateYield(winningResult,amount))
                .isEqualTo(expected);

    }


}
