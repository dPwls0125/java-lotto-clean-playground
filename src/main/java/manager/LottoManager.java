package manager;

import domain.Lotto;
import domain.LottoFactory;
import domain.UserLottos;
import view.InputView;
import view.OutputView;
import vo.Amount;
import vo.TicketCount;

import java.util.ArrayList;
import java.util.List;

import static vo.Amount.LOTTO_PRICES_UNIT;

public class LottoManager {

    private final LottoFactory lottoFactory;

    public LottoManager(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public void IssuingLottoTickets() {
        TicketCount ticketCount = getTicketCountFromInput();
        UserLottos userLottos = generateLottos(ticketCount);
        printUserLottos(ticketCount,userLottos);
    }

    private TicketCount getTicketCountFromInput() {
        Amount amount = InputView.induceTheAmountToBeEntered();
        return new TicketCount(amount.getValue() / LOTTO_PRICES_UNIT);
    }

    private UserLottos generateLottos(TicketCount ticketCount){
        List<Lotto> issuedLottos = new ArrayList<>();
        for(int i=0; i< ticketCount.getValue(); i++){
            issuedLottos.add(lottoFactory.generateLotto());
        }
        return new UserLottos(issuedLottos);
    }

    private void printUserLottos(TicketCount ticketCount, UserLottos userLottos){
        OutputView.printNumberOfLottoes(ticketCount);
        OutputView.printIssuedLottoTickets(userLottos);
    }
}
