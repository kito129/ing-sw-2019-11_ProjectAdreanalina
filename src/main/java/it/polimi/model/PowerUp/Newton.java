package it.polimi.model.PowerUp;
import it.polimi.model.*;

import it.polimi.model.Exception.*;

/**
 * The type Newton.
 */
public class Newton extends PowerUpCard {

    /**
     * Instantiates a new Newton.
     *
     * @param colorCard the color of the card
     */
    public Newton(EnumColorCardAndAmmo colorCard){

        super("NEWTON", colorCard);
        setDescription("You may play this card on your turn before or after any action. Choose any other player's figure and move it 1 or 2 squares in one direction." +
                " (You can't use this to move a figure after it respawns at the end of your turn. That would be too late.)");

    }

    /**
     * Move a player by one or two square in the same direction.
     *
     * @param map the map of the game.
     * @param destSquare the destination square of the target.
     * @param target the player you want to move.
     * @throws NotInSameDirection
     * @throws NotValidDistance
     */
    public void effect(Map map, Square destSquare, Player target) throws NotInSameDirection, NotValidDistance, MapException {

        if ((map.sameDirection(destSquare, map.findPlayer(target))) && ( map.distance(destSquare, map.findPlayer(target)) < 3)
                && ( map.distance(destSquare, map.findPlayer(target)) >0)) {

            map.movePlayer(target, destSquare);
        }else if (!(map.sameDirection(destSquare, map.findPlayer(target)))) {

            throw new NotInSameDirection();
        }else if ((map.distance(destSquare, map.findPlayer(target)) > 3)||(map.distance(destSquare, map.findPlayer(target))==0) ){

            throw new NotValidDistance();
        }
    }

}
