package manager;

import domain.*;
import util.LottoFactory;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoManager {
    public static UserLottos IssuingLottoTickets() {
        Amount amount = getTicketCountFromInput();
        TicketCount purchasedTicketCount = TicketCount.from(amount);
        // manual issuing
        TicketCount manualLottoCount = InputView.induceManulLottoCountToBeEntered(purchasedTicketCount);
        List<Lotto> manualLottos = generateManualLottos(manualLottoCount);
        // auto issuing
        List<Lotto> autoLottos = generateAutoLottos(purchasedTicketCount);

        UserLottos userLottos = UserLottos.Builder.builder()
                .manualLottos(manualLottos)
                .autoLottos(autoLottos)
                .purchaseAmount(amount).build();

        printUserLottos(manualLottoCount, purchasedTicketCount, userLottos);
        return userLottos;
    }

    public static void calculateAndShowWinningStatistics(final UserLottos userLottos) {
        WinningNumbers winningNumbers = InputView.induceTheWinningNumberToBeEntered();
        WinningResult winningResult = userLottos.getWinningResult(winningNumbers);
        OutputView.printWinningCorrespondResult(winningResult);

        double yield = winningResult.getYield(userLottos.getPurchaseAmount());
        boolean isItLoss = winningResult.getIsItLoss(yield);
        OutputView.printStatistics(yield, isItLoss);
    }

    private static Amount getTicketCountFromInput() {
        return InputView.induceTheAmountToBeEntered();
    }

    private static List<Lotto> generateManualLottos(final TicketCount manualLottoCount) {

        List<List<Integer>> manualLottosNumbers = InputView.readManualLottoNumbers(manualLottoCount);
        List<Lotto> manuallyGeneratedLottos = new ArrayList<>();

        for (List<Integer> lottoNumbers : manualLottosNumbers) {
            manuallyGeneratedLottos.add(LottoFactory.generateManualLotto(lottoNumbers));
        }

        return manuallyGeneratedLottos;
    }

    private static List<Lotto> generateAutoLottos(final TicketCount ticketCount) {
        List<Lotto> autoGeneratedLottos = new ArrayList<>();
        for (int i = 0; i < ticketCount.getValue(); i++) {
            autoGeneratedLottos.add(LottoFactory.generateAutoLotto());
        }
        return autoGeneratedLottos;
    }

    private static void printUserLottos(final TicketCount manualLottoCount, final TicketCount ticketCount, final UserLottos userLottos) {
        OutputView.printNumberOfLottoes(manualLottoCount, ticketCount);
        OutputView.printIssuedLottoTickets(userLottos);
    }
}
