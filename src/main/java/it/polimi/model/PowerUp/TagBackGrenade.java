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
     * Gives to the current player who did damage, one mark of the damaged player's color.
     *
     * @param map the map of the game.
     * @param currentPlayer the player who did damage.
     * @param targetPlayer the player who made the mark.
     * @throws NotVisibleTarget if the damaged player can't see the current player who did the damage
     */
    public void effect(Map map, Player currentPlayer, Player targetPlayer) throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, targetPlayer)) {

            currentPlayer.singleDamage(targetPlayer.getColor());
            System.out.println("danneggio adnre con mrchio di ");
            currentPlayer.stampa();
        } else {

            throw new NotVisibleTarget();
        }
    }


}