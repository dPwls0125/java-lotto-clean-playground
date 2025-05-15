package domain;

import utils.NumbersGenerator;

public class LottoFactory {
    private final NumbersGenerator numbersGenerator;

    public LottoFactory(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public Lotto generateLotto(){
        return new Lotto(numbersGenerator.generateSixLottoNumbers());
    }
}
