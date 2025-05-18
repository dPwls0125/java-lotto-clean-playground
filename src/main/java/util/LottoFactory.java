package util;

import domain.Lotto;
import util.NumbersGenerator;

public class LottoFactory {
    private NumbersGenerator numbersGenerator;

    public LottoFactory(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public void changeNumberGenerateStrategy(NumbersGenerator numbersGenerator){
        this.numbersGenerator = numbersGenerator;
    }
    public Lotto generateLotto(){
        return new Lotto(numbersGenerator.generateSixLottoNumbers());
    }
}
