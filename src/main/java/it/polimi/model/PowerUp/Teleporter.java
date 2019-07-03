package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;

/**
 * The poweup Teleporter.
 */
public class Teleporter extends PowerUpCard {

    /**
     * Instantiates a new Teleporter.
     *
     * @param colorCard the color card
     */
    public Teleporter(EnumColorCardAndAmmo colorCard) {

        super("TELEPORTER", colorCard);
        setDescription("You may play this card on your turn before or after any action. Pick up your figure and set it down on any square of the board. (You can't use this after you see where someone respawns at the end of your turn. By then it is too late.)");
    }

    /**
     * Move where you want on the map.
     *
     * @param currentPlayer the current player.
     * @param map           the map of the game.
     * @param destSquare    the square where the player wants to go.
     */
    public void effect(Player currentPlayer, Map map, Square destSquare) throws  MapException {

        map.movePlayer(currentPlayer, destSquare);
    }
}

