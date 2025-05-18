package view;

import domain.Amount;
import domain.ManualLottoCount;
import domain.TicketCount;
import domain.WinningNumbers;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String ENTER_THE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String ENTER_LAST_WINNING_NUMBERS = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_BALL = "보너스 볼을 입력해 주세요.";

    private static final String ENTER_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ENTER_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요";

    public static Amount induceTheAmountToBeEntered(){
        Optional<Amount> optionalAmount = Optional.empty();
        while(optionalAmount.isEmpty()){
            System.out.println(ENTER_THE_AMOUNT);
            optionalAmount = readAndReturnAmount();
        }
        return optionalAmount.get();
    }

    public static ManualLottoCount induceManulLottoCountToBeEntered(TicketCount ticketCount){
        Optional<ManualLottoCount> optionalCount = Optional.empty();
        while(optionalCount.isEmpty()){
            System.out.println(ENTER_MANUAL_LOTTO_COUNT);
            optionalCount = readAndReturnManualLottoCount(ticketCount);
        }
        ticketCount.subtractTicketCount(optionalCount.get());
        return optionalCount.get();
    }

    public static List<List<Integer>> readManualNumbers(ManualLottoCount count) {
        System.out.println(ENTER_MANUAL_LOTTO_NUMBERS);
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count.getValue(); i++) {
            manualNumbers.add(
                    Arrays.stream(scanner.nextLine().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .toList()
            );
        }
        return manualNumbers;
    }

    public static WinningNumbers induceTheWinningNumberToBeEntered(){
        System.out.println(ENTER_LAST_WINNING_NUMBERS);
        List<Integer> winningNumbersEntered = Arrays.stream(scanner.next().split(","))
                .map(Integer::parseInt)
                .toList();
        System.out.println(ENTER_BONUS_BALL);
        int bonusBall = scanner.nextInt();
        return new WinningNumbers(winningNumbersEntered, bonusBall);
    }

    private static Optional<Amount> readAndReturnAmount(){
        try {
            int input = scanner.nextInt();
            Amount amount = new Amount(input);
            return Optional.of(amount);
        }catch (InputMismatchException e) {
            printMessageAndClearBuffer("금액을 숫자로 입력해 주세요");
        } catch (IllegalArgumentException e){
            printMessageAndClearBuffer(e.getMessage());
        }
        return Optional.empty();
    }

    private static Optional<ManualLottoCount> readAndReturnManualLottoCount(TicketCount ticketCount){
        try{
            int count = scanner.nextInt();
            scanner.nextLine();
            return Optional.of(new ManualLottoCount(count,ticketCount));
        } catch(IllegalArgumentException e){
            printMessageAndClearBuffer(e.getMessage());
        }
        return Optional.empty();
    }

    private static void printMessageAndClearBuffer(String message){
        System.out.println(message);
        scanner.nextLine();
    }
}
