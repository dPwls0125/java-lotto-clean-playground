import domain.LottoFactory;
import domain.UserLottos;
import manager.LottoManager;
import util.RandomNumbersGenerator;

public class Main {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(new LottoFactory(new RandomNumbersGenerator()));
        UserLottos userLottos = lottoManager.IssuingLottoTickets();
        lottoManager.calculateAndShowWinningStatistics(userLottos);
    }
}
