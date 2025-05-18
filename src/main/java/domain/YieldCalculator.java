package domain;
import vo.Amount;
import vo.WinningResult;

public class YieldCalculator {

    public static double calculateYield(WinningResult winningResult, Amount purchaseAmount) {
        int totalProfit = calculateProfit(winningResult);
        return (double) totalProfit / purchaseAmount.getValue();
    }

    private static int calculateProfit(WinningResult winningResult) {
        return winningResult.getValue().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public static boolean isLoss(double calculatedYield) {
        return calculatedYield < 1.0;
    }
}
