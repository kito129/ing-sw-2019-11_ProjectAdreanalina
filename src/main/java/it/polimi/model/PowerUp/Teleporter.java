package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidInput;

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
    }

    /**
     * Moves the current player to any square of the map
     *
     * @param currentPlayer the current player that uses this power up.
     * @param map           the map of the game.
     * @param destSquare    the square where the player wants to go.
     */
    public void effect(Player currentPlayer, Map map, Square destSquare) throws NotValidInput, MapException {

        map.movePlayer(currentPlayer, destSquare);
    }
}

