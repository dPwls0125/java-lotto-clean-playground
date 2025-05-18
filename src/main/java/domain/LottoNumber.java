package domain;

import exception.lotto.InvalidLottoNumberRangeException;

import java.util.Objects;

import static constant.LottoConstants.LOTTO_LOWER_BOUND;
import static constant.LottoConstants.LOTTO_UPPER_BOUND;

public class LottoNumber {

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateNumberRange(int number) {
        if(!(number >= LOTTO_LOWER_BOUND && number <= LOTTO_UPPER_BOUND)){
            throw new InvalidLottoNumberRangeException(String.format("%d는 허용되지 않는 범위의 숫자입니다.",number));
        }
    }

    public int getValue() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber that)) return false;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(lottoNumber);
    }
}
