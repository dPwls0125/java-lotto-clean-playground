package domain;

public class YieldCalculator {

    public static double calculateYield(final WinningResult winningResult, final Amount purchaseAmount) {
        int totalProfit = calculateProfit(winningResult);
        return (double) totalProfit / purchaseAmount.getValue();
    }

    private static int calculateProfit(final WinningResult winningResult) {
        return winningResult.getValue().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public static boolean isLoss(final double calculatedYield) {
        return calculatedYield < 1.0;
    }
}
