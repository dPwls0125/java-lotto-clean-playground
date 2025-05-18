package util;

import java.util.List;

public class ManualNumberGenerator implements NumbersGenerator{

    private final List<Integer> manuallyGeneratedNumbers;

    public ManualNumberGenerator(List<Integer> manuallyGeneratedNumbers) {
        this.manuallyGeneratedNumbers = manuallyGeneratedNumbers;
    }

    @Override
    public List<Integer> generateSixLottoNumbers() {
        return manuallyGeneratedNumbers;
    }
}
