package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The type Targeting scope.
 */
public class TargetingScope extends PowerUpCard {
    
    /**
     * Instantiates a new Targeting scope.
     *
     * @param colorCard the color card
     */
    public TargetingScope(EnumColorCard colorCard) {

        super("TARGETING SCOPE", colorCard);

    }
    
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param target    the target
     */
    public void effect(GameModel gameModel, Player target){

        EnumColorPlayer damage = gameModel.getActualPlayer().getColor();
        target.getPlayerBoard().increaseDamages(damage);
    }

}
