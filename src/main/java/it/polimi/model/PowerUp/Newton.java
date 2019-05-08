package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;

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
        this.description="";
    }

    /**
     * Moves the target by one or two square in the same direction.
     *
     * @param map the map of the game.
     * @param destSquare the destination square of the target.
     * @param target player you want to move.
     * @throws NotInSameDirection if the destination square in not in the same direction with the actual target's square.
     * @throws NotValidDistance if the destination square is more than two squares away from the actual target's square.
     */
    public void effect(Map map, Square destSquare, Player target) throws NotInSameDirection,NotValidDistance{

        if ((map.sameDirection(destSquare, map.findPlayer(target))) && ( map.distance(destSquare, map.findPlayer(target)) < 3)
                && ( map.distance(destSquare, map.findPlayer(target)) >0)) {

            map.movePlayer(target, destSquare);
        }else if ((map.sameDirection(destSquare, map.findPlayer(target))) == false) {

            throw new NotInSameDirection();
        }else if ((map.distance(destSquare, map.findPlayer(target)) > 3)&&(map.distance(destSquare, map.findPlayer(target))==0) ){

            throw new NotValidDistance();
        }
    }

}
