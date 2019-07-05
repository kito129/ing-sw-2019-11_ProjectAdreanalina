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
        setDescription("You may play this card when you receive damage from a player you can see. Give that player 1 mark.");
    }

    /**
     * Give one mark to the player who shot before.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player who shot before and receive the mark.
     * @param targetPlayer the player who received the damage.
     * @throws NotVisibleTarget
     */
    public void effect(Map map, Player currentPlayer, Player targetPlayer) throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, targetPlayer)) {

            currentPlayer.singleMark(targetPlayer.getColor());
        } else {

            throw new NotVisibleTarget();
        }
    }


}