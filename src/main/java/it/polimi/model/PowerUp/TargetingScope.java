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
     * Gives to the target one damage of the color of current player.
     *
     * @param currentPlayer the player who gives the damage
     * @param target the player who gets the damage
     */
    public void effect(Player currentPlayer, Player target){

        target.SingleDamage(currentPlayer.getColor());
    }

    //TOdo da eliminare funzione sotto
    /*
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param target    the target

    public void effect(GameModel gameModel, Player target){

        EnumColorPlayer damage = gameModel.getActualPlayer().getColor();
        target.getPlayerBoard().increaseDamages(damage);
    }

     */


}
