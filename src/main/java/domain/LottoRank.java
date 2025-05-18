package domain;

import java.util.List;

public class LottoRank {

    public static final LottoRank FIRST = new LottoRank(6,2_000_000_000);
    public static final LottoRank SECOND = new LottoRank(5,1_500_000);
    public static final LottoRank THIRD = new LottoRank(4,50_000);
    public static final LottoRank FOURTH  = new LottoRank(3,5_000);
    public static final LottoRank NONE = new LottoRank(-1,0);

    private final int matchCount;
    private final int prize;

    public LottoRank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank match(int matchCount){
        if (matchCount == 6) return FIRST;
        if (matchCount == 5) return SECOND;
        if (matchCount == 4) return THIRD;
        if (matchCount == 3) return FOURTH;
        return NONE;
    }

    public static List<LottoRank> values(){
        return List.of(FIRST,SECOND,THIRD,FOURTH,NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
