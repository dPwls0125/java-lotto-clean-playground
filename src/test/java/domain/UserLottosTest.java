package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLottosTest {
    private static UserLottos userLottos;
    private static WinningNumbers winningNumbers;

    @BeforeAll
    static void setUserLottos(){
        Amount amount = new Amount(6000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1,2,3,4,5,6)),
                new Lotto(List.of(2,3,4,5,6,7)),
                new Lotto(List.of(3,4,5,6,7,8)),
                new Lotto(List.of(4,5,6,7,8,9)),
                new Lotto(List.of(5,6,7,8,9,10)),
                new Lotto(List.of(6,7,8,9,10,11))
        );
        userLottos = new UserLottos(lottos,amount);
        winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6), 7);
    }


    @Test
    @DisplayName("userLottos의 getWinningResult() 메서드는, 유저가 소유한 로또의 당첨 결과를 반환한다.")
    void whenGivenFixedNumbersLotto_thenGetCorrespondingWinningResult(){
        Map<LottoRank,Integer> expectedResult = Map.of(
                LottoRank.FIRST, 1,
                LottoRank.SECOND, 1,
                LottoRank.FOURTH, 1,
                LottoRank.FIFTH, 1,
                LottoRank.NONE, 2
        );

        assertThat(userLottos.getWinningResult(winningNumbers).getValue()).isEqualTo(expectedResult);
    }



}
