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
        setDescription("You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color. Choose 1 of those targets and give it an extra point of damage. " +
                "Note: You cannot use this to do 1 damage to a target that is receiving only marks.");
    }

    /**
     * Gives to the target one damage of the color of current player.
     *
     * @param currentPlayer the player who gives the damage and uses this power up.
     * @param target the player who gets one damage.
     */
    public void effect(Player currentPlayer, Player target){

        target.singleDamage(currentPlayer.getColor());
        System.out.println("danneggio lui:");
        target.stampa();
    }


}
