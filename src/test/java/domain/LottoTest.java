package domain;

import exception.lotto.InvalidLottoLengthException;
import exception.lotto.InvalidLottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.RandomNumbersGenerator;
import vo.WinningNumbers;

import java.util.List;
import java.util.stream.Stream;

import static constant.LottoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private LottoFactory lottoFactory;
    @Nested
    @DisplayName("자동 밝브 랜덤 로또 테스트")
    static class RandomGeneratedLottoTest{
        private LottoFactory lottoFactory = new LottoFactory(new RandomNumbersGenerator());
        @Test
        @DisplayName("랜덤으로 발급받은 로또의 범위는 로또의 upper_bount, lower_bound를 만족한다.")
        void whenLottoGeneratedByRandomNumbersGenerator_thenEachNumberBetweenUpperAndLowerBounds(){
            lottoFactory = new LottoFactory(new RandomNumbersGenerator());
            Lotto lotto = lottoFactory.generateLotto();
            lotto.getLottoNumbers()
                    .forEach(number -> assertThat(number).isBetween(LOTTO_LOWER_BOUND,LOTTO_UPPER_BOUND));
        }

        @Test
        @DisplayName("랜덤으로 발급받은 로또의 숫자 갯수는 6개이다.")
        void whenLottoGeneratedByRandomNumbersGenerator_thenHasSixNmbers(){
            lottoFactory = new LottoFactory(new RandomNumbersGenerator());
            Lotto lotto = lottoFactory.generateLotto();
            assertThat(lotto.getLottoNumbers().size()).isEqualTo(LOTTO_SIZE);

        }
    }

    @ParameterizedTest(name = "발급 받은 로또에 {1} 포함이면 throws InvalideLottoNumberRangeException")
    @MethodSource("invalidNumberIncludedLottos")
    void whenNumberOutOfBounds_thenThrowRuntimeException(List<Integer> invalidLottoNumbers, int cause){
        lottoFactory = new LottoFactory(()->invalidLottoNumbers);
        assertThatThrownBy(lottoFactory::generateLotto)
                .isInstanceOf(InvalidLottoNumberRangeException.class)
                .hasMessage(String.format("%d는 허용되지 않는 범위의 숫자입니다.",cause));

    }

    private static Stream<Arguments> invalidNumberIncludedLottos(){
        return Stream.of(
                Arguments.arguments(List.of(-1,2,3,4,5,6),-1),
                Arguments.arguments(List.of(1,2,3,4,5,46),46),
                Arguments.arguments(List.of(0,2,3,4,5,45),0)
        );
    }

    @ParameterizedTest(name = "발급 받은 로또가 {0}이면 사이즈를 벗어난다.")
    @MethodSource("invalidLengthLottos")
    void whenLottoSizeOutOfBound_thenThrowRuntimeException(List<Integer> invalidLottoNumbers) {
        lottoFactory = new LottoFactory(()->invalidLottoNumbers);
        assertThatThrownBy(lottoFactory::generateLotto)
                .isInstanceOf(InvalidLottoLengthException.class);
    }

    private static Stream<Arguments> invalidLengthLottos() {
        return Stream.of(
                Arguments.arguments(List.of(1,2,3,4,5)),
                Arguments.arguments(List.of(1,2,3,4,5,6,7)),
                Arguments.arguments(List.of())
        );
    }

    @ParameterizedTest(name = "발급 받은 로또의 번호가 {0}이면 맞춘 번호의 갯수는 {1}이다.")
    @MethodSource("lottoNumbersAndExpectedMatchCountWithWinningNumbers")
    void whenFixedNumberIncludedLottoGenerated_thenCountMatchedNumberExactly(WinningNumbers winningNumbers, LottoFactory lottoFactory, int expectedMatchCount){
        Lotto lotto = lottoFactory.generateLotto();
        assertThat(lotto.countMatchedNumbers(winningNumbers)).isEqualTo(expectedMatchCount);
    }

    private static Stream<Arguments> lottoNumbersAndExpectedMatchCountWithWinningNumbers(){
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6));
        return Stream.of(
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(1,2,3,4,5,6)),6),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(2,3,4,5,6,7)),5),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(3,4,5,6,7,8)),4),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(4,5,6,7,8,9)),3),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(5,6,7,8,9,10)),2),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(6,7,8,9,10,11)),1),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(7,8,9,10,11,12)),0),
                Arguments.arguments(winningNumbers,new LottoFactory(()->List.of(8,9,10,11,12,13)),0)
        );
    }

}
