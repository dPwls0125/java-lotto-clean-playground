package domain;

import exception.lotto.InvalidLottoLengthException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static constant.LottoConstants.*;


public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        validateLottoNumbersCount(lottoNumbers);
        validateLottoNumberIncludesDuplicateNumber(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toList()));
    }

    public int countMatchedNumbers(WinningNumbers winningNumbers) {
        int countMatchedNumber = 0;
        for (LottoNumber number : winningNumbers.getWinningNumbers()) {
            if (lottoNumbers.contains(number)) {
                ++countMatchedNumber;
            }
        }
        return countMatchedNumber;
    }

    public boolean isContainBonusBallNumber(WinningNumbers winningNumbers) {
        return lottoNumbers.contains(winningNumbers.getBonusNumber());
    }

    private void validateLottoNumbersCount(final List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new InvalidLottoLengthException("로또가 가질 수 있는 숫자의 갯수는 6개입니다.");
        }
    }

    private void validateLottoNumberIncludesDuplicateNumber(final List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또에 중복된 번호가 포함되어 있습니다.");
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
