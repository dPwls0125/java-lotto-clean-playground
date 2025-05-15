package view;

import vo.Amount;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    private static final String ENTER_THE_AMOUNT = "구입금액을 입력해 주세요.";

    public static Amount induceTheAmountToBeEntered(){
        Optional<Amount> optionalAmount = Optional.empty();
        while(optionalAmount.isEmpty()){
            System.out.println(ENTER_THE_AMOUNT);
            optionalAmount = readAndReturnAmount();
        }

        return optionalAmount.get();
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
