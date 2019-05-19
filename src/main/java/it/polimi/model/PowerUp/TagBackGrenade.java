package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;

/**
 * The type Tag back grenade.
 */
public class TagBackGrenade extends PowerUpCard {

    /**
     * Instantiates a new Tag back grenade.
     *
     * @param colorCard the color card
     */
    public TagBackGrenade(EnumColorCardAndAmmo colorCard) {

        super("TAGBACK GRENADE", colorCard);
    }

    /**
     * Gives to the current player who did damage, one mark of the damaged player's color.
     *
     * @param map the map of the game.
     * @param currentPlayer the player who did damage.
     * @param damagedPlayer the player who made the mark.
     * @throws NotVisibleTarget if the damaged player can't see the current player who did the damage
     */
    public void effect(Map map, Player currentPlayer, Player damagedPlayer) throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, damagedPlayer)) {

            currentPlayer.singleMark(damagedPlayer.getColor());
        } else {

            throw new NotVisibleTarget();
        }
    }


}