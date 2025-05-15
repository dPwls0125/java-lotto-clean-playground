package utils;

import java.util.*;

import static domain.constant.LottoConstants.*;

public class RandomNumbersGenerator implements NumbersGenerator{
    @Override
    public List<Integer> generateSixLottoNumbers() {
        List<Integer> randomNumbers = generateShuffledNumbers().subList(0,LOTTO_SIZE);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = generateListForShuffle();
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> generateListForShuffle(){
        List<Integer> numbers = new ArrayList<>();
        for(int i=LOTTO_LOWER_BOUND; i<=LOTTO_UPPER_BOUND; i++){
            numbers.add(i);
        }
        return numbers;
    }
}
