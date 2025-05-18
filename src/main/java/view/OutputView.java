package view;

import domain.LottoRank;
import domain.UserLottos;
import domain.YieldCalculator;
import domain.Amount;
import domain.ManualLottoCount;
import domain.TicketCount;
import domain.WinningResult;

public class OutputView {

    private static final String NOTICE_MANUAL_AUTO_TICKET_COUNTS = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String NOTICE_WINNING_STATISTICS = "\n당첨 통계";
    private static final String TITLE_AND_CONTENT_DIVIDER = "---------";
    private static final String NOTICE_CORRESPOND_LOTTO_COUNT  = "%d개 일치 (%d원)- %d개%n";
    private static final String NOTICE_CORRESPOND_LOTTO_COUNT_AND_BONUS  = "%d개 일치, 보너스 볼 일치 (%d원)- %d개%n";
    private static final String NOTICE_TOTAL_PROFIT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s(이)라는 의미임)%n";
    private static final String LOSS = "손해";
    private static final String PROFIT = "이익";

    public static void printNumberOfLottoes(ManualLottoCount manualLottoCount, TicketCount ticketCount){
        System.out.printf( (NOTICE_MANUAL_AUTO_TICKET_COUNTS), manualLottoCount.getValue(),ticketCount.getValue());
    }

    public static void printIssuedLottoTickets(UserLottos userLottos){
        userLottos.getUserLottos().forEach(System.out::println);
    }

    public static void printWinningResultAndStatistics(WinningResult winningResult, Amount purchaseAmount) {
        printWinningCorrespondResult(winningResult);
        printWinningStatistics(winningResult,purchaseAmount);
    }

    private static String getLossOrProfit(double yield){
        if (YieldCalculator.isLoss(yield)){
            return LOSS;
        }
        return PROFIT;
    }
    private static void printWinningCorrespondResult(WinningResult winningResult){
        System.out.println(NOTICE_WINNING_STATISTICS);
        System.out.println(TITLE_AND_CONTENT_DIVIDER);

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) continue;

            int prize = rank.getPrize();
            int matchCount = rank.getMatchCount();
            int count = winningResult.getCountOf(rank);

            System.out.printf(getFormattedRankLine(rank), matchCount, prize, count);
        }
    }
    private static void printWinningStatistics(WinningResult winningResult, Amount purchaseAmount) {
        double yield = YieldCalculator.calculateYield(winningResult, purchaseAmount);
        System.out.printf(NOTICE_TOTAL_PROFIT, yield, getLossOrProfit(yield));
    }

    private static String getFormattedRankLine(LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            return NOTICE_CORRESPOND_LOTTO_COUNT_AND_BONUS;
        }
        return NOTICE_CORRESPOND_LOTTO_COUNT;
    }

}
