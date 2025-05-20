package domain;

import java.util.List;
import java.util.Objects;


public class WinningNumbers {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        this.bonusNumber = new LottoNumber(bonusNumber);
        this.winningLotto = new Lotto(winningNumbers);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningNumbers that)) return false;
        return Objects.equals(winningLotto, that.winningLotto) && Objects.equals(getBonusNumber(), that.getBonusNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, getBonusNumber());
    }
}
