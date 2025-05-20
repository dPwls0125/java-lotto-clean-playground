package util;

import domain.Lotto;

import java.util.List;

public class LottoFactory {

    public static Lotto generateAutoLotto() {
        return new Lotto(RandomNumbersGenerator.generateSixLottoNumbers());
    }

    public static Lotto generateManualLotto(final List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }
}
