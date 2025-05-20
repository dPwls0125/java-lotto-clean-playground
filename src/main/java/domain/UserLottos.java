package domain;

import constant.LottoConstants;

import java.util.*;


public class UserLottos {

    private final List<Lotto> userLottos;
    private final Amount purchaseAmount;

    private UserLottos(List<Lotto> lottos, Amount purchaseAmount) {
        this.userLottos = new ArrayList<>(lottos);
        this.purchaseAmount = purchaseAmount;
    }

    public static UserLottos of(List<Lotto> manualLottos, List<Lotto> autoLottos, Amount purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        lottos.addAll(autoLottos);
        return new UserLottos(lottos, purchaseAmount);
    }


    public WinningResult getWinningResult(WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> result = new HashMap<>();
        for (Lotto lotto : userLottos) {
            LottoRank rank = evaluateRank(lotto, winningNumbers);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        return new WinningResult(result);
    }

    private LottoRank evaluateRank(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = lotto.countMatchedNumbers(winningNumbers);
        boolean bonusMatched = isFiveNumbersMatchedWithBonus(matchCount, lotto, winningNumbers);
        return LottoRank.match(matchCount, bonusMatched);
    }

    private boolean isFiveNumbersMatchedWithBonus(int matchCount, Lotto lotto, WinningNumbers winningNumbers) {
        if (matchCount != LottoConstants.BONUS_MATCH_REQUIRED_COUNT) {
            return false;
        }
        return lotto.isContainBonusBallNumber(winningNumbers);
    }

    public List<Lotto> getUserLottos() {
        return Collections.unmodifiableList(userLottos);
    }

    public Amount getPurchaseAmount() {
        return purchaseAmount;
    }
    
    public static class Builder {
        private List<Lotto> manualLottos;
        private List<Lotto> autoLottos;
        private Amount purchaseAmount;

        public Builder manualLottos(List<Lotto> manualLottos) {
            this.manualLottos = manualLottos;
            return this;
        }

        public Builder autoLottos(List<Lotto> autoLottos) {
            this.autoLottos = autoLottos;
            return this;
        }

        public Builder purchaseAmount(Amount amount) {
            this.purchaseAmount = amount;
            return this;
        }

        public UserLottos build() {
            List<Lotto> mergedLottoList = new ArrayList<>();
            mergedLottoList.addAll(this.manualLottos);
            mergedLottoList.addAll(this.autoLottos);
            return new UserLottos(mergedLottoList, this.purchaseAmount);
        }

        public static Builder builder() {
            return new Builder();
        }

    }
}
