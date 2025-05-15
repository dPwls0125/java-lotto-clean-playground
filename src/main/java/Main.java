import domain.LottoFactory;
import manager.LottoManager;
import utils.RandomNumbersGenerator;

public class Main {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(new LottoFactory(new RandomNumbersGenerator()));
        lottoManager.IssuingLottoTickets();
    }
}
