package constant;

public class LottoConstants {
    public static final int LOTTO_UPPER_BOUND = 45;
    public static final int LOTTO_LOWER_BOUND = 1;
    public static final int LOTTO_SIZE = 6;

    private LottoConstants() throws Exception{
        throw new Exception("LottoConstants 클래스는 인스턴스화할 수 없습니다.");
    }
}

