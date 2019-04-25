package it.polimi.model.PowerUp;
import it.polimi.model.*;

import java.util.ArrayList;

public class TargetingScope extends PowerUpCard {

    public TargetingScope(EnumColorCard colorCard) {

        super("TARGETING SCOPE", colorCard);

    }

    public void effect(GameModel gameModel, Player target){

        EnumColorPlayer damage = gameModel.getActualPlayer().getColor();
        target.getPlayerBoard().increaseDamage(damage);
    }

}
