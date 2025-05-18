package manager;

import domain.Lotto;
import domain.LottoFactory;
import domain.UserLottos;
import view.InputView;
import view.OutputView;
import vo.Amount;
import vo.TicketCount;
import vo.WinningNumbers;
import vo.WinningResult;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {

    private final LottoFactory lottoFactory;

    public LottoManager(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public UserLottos IssuingLottoTickets() {
        Amount amount = getTicketCountFromInput();
        TicketCount ticketCount = TicketCount.from(amount);
        UserLottos userLottos = generateLottos(ticketCount ,amount);
        printUserLottos(ticketCount,userLottos);
        return userLottos;
    }

    public void calculateAndShowWinningStatistics(UserLottos userLottos){
        WinningNumbers winningNumbers = InputView.induceTheWinningNumberToBeEntered();
        WinningResult winningResult = userLottos.getWinningResult(winningNumbers);
        OutputView.printWinningResultAndStatistics(winningResult,userLottos.getPurchaseAmount());
    }

    private Amount getTicketCountFromInput() {
        return InputView.induceTheAmountToBeEntered();
    }

    private UserLottos generateLottos(TicketCount ticketCount, Amount amount){
        List<Lotto> issuedLottos = new ArrayList<>();
        for(int i=0; i< ticketCount.getValue(); i++){
            issuedLottos.add(lottoFactory.generateLotto());
        }
        return new UserLottos(issuedLottos,amount);
    }

    private void printUserLottos(TicketCount ticketCount, UserLottos userLottos){
        OutputView.printNumberOfLottoes(ticketCount);
        OutputView.printIssuedLottoTickets(userLottos);
    }
}
