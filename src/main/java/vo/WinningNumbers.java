package vo;

import domain.LottoNumber;
import exception.lotto.InvalidLottoLengthException;
import exception.lotto.InvalidLottoNumberRangeException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static constant.LottoConstants.*;

public class WinningNumbers  {

    private final List<LottoNumber> winningNumbers;

    private final LottoNumber bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        this.bonusNumber = new LottoNumber(bonusNumber);
        validateNumberNumberRange(winningNumbers);
        validateWinningNumbersCount(winningNumbers);
        this.winningNumbers = winningNumbers.stream().map(LottoNumber::new).collect(Collectors.toList());
    }

    private void validateWinningNumbersCount(final List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_SIZE){
            throw new InvalidLottoLengthException("로또가 가질 수 있는 숫자의 갯수는 6개입니다.");
        }
    }
    private void validateNumberNumberRange(final List<Integer> winningNumbers) {
        winningNumbers
                .forEach(number -> {
                    if(!(number >= LOTTO_LOWER_BOUND && number <= LOTTO_UPPER_BOUND)){
                        throw new InvalidLottoNumberRangeException(String.format("%d는 허용되지 않는 범위의 숫자입니다.",number));
                    }
                });
    }

    public List<LottoNumber> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningNumbers that)) return false;
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}
