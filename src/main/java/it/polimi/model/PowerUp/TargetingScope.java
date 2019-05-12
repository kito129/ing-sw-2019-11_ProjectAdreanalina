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
    public TargetingScope(EnumColorCardAndAmmo colorCard) {

        super("TARGETING SCOPE", colorCard);
    }

    /**
     * Gives to the target one damage of the color of current player.
     *
     * @param currentPlayer the player who gives the damage and uses this power up.
     * @param target the player who gets one damage.
     */
    public void effect(Player currentPlayer, Player target){

        target.singleDamage(currentPlayer.getColor());
    }


}
