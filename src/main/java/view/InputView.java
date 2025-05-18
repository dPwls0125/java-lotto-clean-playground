package view;

import vo.Amount;
import vo.WinningNumbers;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String ENTER_THE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String ENTER_LAST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_BALL = "보너스 볼을 입력해 주세요.";

    public static Amount induceTheAmountToBeEntered(){
        Optional<Amount> optionalAmount = Optional.empty();
        while(optionalAmount.isEmpty()){
            System.out.println(ENTER_THE_AMOUNT);
            optionalAmount = readAndReturnAmount();
        }
        return optionalAmount.get();
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

    private static void printMessageAndClearBuffer(String message){
        System.out.println(message);
        scanner.nextLine();
    }
}
