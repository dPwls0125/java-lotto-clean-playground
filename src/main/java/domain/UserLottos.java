package domain;

import vo.Amount;
import vo.WinningNumbers;
import vo.WinningResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.LottoConstants.BONUS_MATCH_REQUIRED_COUNT;

public class UserLottos {

    private final List<Lotto> userLottos;
    private final Amount purchaseAmount;
    public UserLottos(Amount purchaseAmount){
        this.userLottos = new ArrayList<>();
        this.purchaseAmount = purchaseAmount;
    }

    public void addUserLotto(Lotto lotto){
        userLottos.add(lotto);
    }

    public void addUserLottos(List<Lotto> lottos){
        userLottos.addAll(lottos);
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
        boolean bonusMatched = isBonusMatched(matchCount, lotto, winningNumbers);
        return LottoRank.match(matchCount, bonusMatched);
    }

    private boolean isBonusMatched(int matchCount, Lotto lotto, WinningNumbers winningNumbers) {
        if (matchCount != BONUS_MATCH_REQUIRED_COUNT) {
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
