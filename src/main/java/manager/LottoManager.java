package manager;

import domain.Lotto;
import domain.LottoFactory;
import domain.UserLottos;
import util.ManualNumberGenerator;
import util.NumbersGenerator;
import util.RandomNumbersGenerator;
import view.InputView;
import view.OutputView;
import vo.*;

import java.util.List;

public class LottoManager {

    private LottoFactory lottoFactory;
    private NumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

    public LottoManager(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public UserLottos IssuingLottoTickets() {
        Amount amount = getTicketCountFromInput();
        TicketCount ticketCount = TicketCount.from(amount);
        UserLottos userLottos = new UserLottos(amount);
        // manual issuing
        ManualLottoCount manualLottoCount = InputView.induceManulLottoCountToBeEntered(ticketCount);
        generateManualLottosAndAddToUserLottos(manualLottoCount,userLottos);
        // auto issuing
        generateAutoLottosAndAddToUserLottos(ticketCount,userLottos);
        printUserLottos(manualLottoCount,ticketCount,userLottos);
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

    private void generateManualLottosAndAddToUserLottos( ManualLottoCount manualLottoCount, UserLottos userLottos){
        List<List<Integer>> manualLottosNumbers = InputView.readManualNumbers(manualLottoCount);

        for(List<Integer> lottoNumbers : manualLottosNumbers){
            lottoFactory.changeNumberGenerateStrategy(new ManualNumberGenerator(lottoNumbers));
            userLottos.addUserLotto(lottoFactory.generateLotto());
        }
    }


    private void generateAutoLottosAndAddToUserLottos(TicketCount ticketCount,UserLottos userLottos){
        lottoFactory.changeNumberGenerateStrategy(randomNumbersGenerator);

        for(int i=0; i< ticketCount.getValue(); i++){
            userLottos.addUserLotto(lottoFactory.generateLotto());
        }
    }

    private void printUserLottos(ManualLottoCount manualLottoCount, TicketCount ticketCount, UserLottos userLottos){
        OutputView.printNumberOfLottoes(manualLottoCount,ticketCount);
        OutputView.printIssuedLottoTickets(userLottos);
    }
}
