package domain;

import exception.lotto.InvalidLottoLengthException;
import exception.lotto.InvalidLottoNumberRangeException;
import vo.WinningNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstants.*;


public class Lotto {

    private final List<Integer> lottoNumbers;

    public Lotto(final List<Integer> lottoNumbers) {
        validateLottoNumbersCount(lottoNumbers);
        validateNumberNumberRange(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }
    public int countMatchedNumbers(WinningNumbers winningNumbers ){
        int countMatchedNumber = 0;
        for(Integer number : winningNumbers.getWinningNumbers()){
            if(lottoNumbers.contains(number)){
                ++countMatchedNumber;
            }
        }
        return countMatchedNumber;
    }
    private void validateNumberNumberRange(final List<Integer> lottoNumbers) {
        lottoNumbers
                .forEach(number -> {
                    if(!(number >= LOTTO_LOWER_BOUND && number <= LOTTO_UPPER_BOUND)){
                        throw new InvalidLottoNumberRangeException(String.format("%d는 허용되지 않는 범위의 숫자입니다.",number));
                    }
                });
    }

    private void validateLottoNumbersCount(final List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE){
            throw new InvalidLottoLengthException("로또가 가질 수 있는 숫자의 갯수는 6개입니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString(){
        return lottoNumbers.toString();
    }
}
