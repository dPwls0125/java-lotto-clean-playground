package domain;

import constant.LottoConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserLottos {

    private final List<Lotto> userLottos;
    private final Amount purchaseAmount;

    public UserLottos(List<Lotto> lottos, Amount purchaseAmount) {
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
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : userLottos) {
            LottoRank rank = evaluateRank(lotto, winningNumbers);
            winningResult.addResult(rank);
        }
        return winningResult;
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
}
