package domain;

import java.util.Collections;
import java.util.List;

public class UserLottos {
    private List<Lotto> userLottos;

    public UserLottos(List<Lotto> userLottos){
        this.userLottos = userLottos;
    }

    public List<Lotto> getUserLottos() {
        return Collections.unmodifiableList(userLottos);
    }
}
