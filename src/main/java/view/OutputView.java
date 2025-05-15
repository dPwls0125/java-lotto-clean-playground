package view;

import domain.UserLottos;
import vo.TicketCount;

public class OutputView {

    private static final String NOTICE_TICKET_COUNTS = "%d개를 구매했습니다.";

    public static void printNumberOfLottoes(TicketCount ticketCount){
        System.out.printf( "\n"+ (NOTICE_TICKET_COUNTS) + "%n", ticketCount.getValue());
    }

    public static void printIssuedLottoTickets(UserLottos userLottos){
        userLottos.getUserLottos().forEach(System.out::println);
    }
}
