package domain;

import vo.Amount;
import vo.WinningNumbers;
import vo.WinningResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserLottos {

    private final List<Lotto> userLottos;
    private final Amount purchaseAmount;
    public UserLottos(List<Lotto> userLottos, Amount purchaseAmount){
        this.userLottos = new ArrayList<>(userLottos);
        this.purchaseAmount = purchaseAmount;
    }

    public WinningResult getWinningResult(WinningNumbers winningNumbers){
        WinningResult winningResult = new WinningResult();
        userLottos.forEach(lotto -> winningResult
                .addResult(LottoRank.match(lotto.countMatchedNumbers(winningNumbers))));
        return winningResult;
    }

    public List<Lotto> getUserLottos() {
        return Collections.unmodifiableList(userLottos);
    }

    public Amount getPurchaseAmount() {
        return purchaseAmount;
    }
}
