package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.InvalidActionForThisCard;

/**
 * The type Newton.
 */
public class Newton extends PowerUpCard {
    
    /**
     * Instantiates a new Newton.
     *
     * @param colorCard the color of the card
     */
    public Newton(EnumColorCard colorCard) {

        super("NEWTON", colorCard);
    }

    public void effect(Map map, Square destSquare, Player target) throws InvalidActionForThisCard {

        if((map.sameDirection(destSquare,map.findPlayer(target))) && (map.distance(destSquare,map.findPlayer(target),1)<3)){

            map.movePlayer(target,destSquare);
        }else{

            throw new InvalidActionForThisCard();
        }


    }

    // TOdo da eliminare funzioni qua sotto.
    /*
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param rd        the row destination
     * @param cd        the column designation
     * @param target    the target

    public void effect(GameModel gameModel, int rd, int cd, Player target){

        if((gameModel.getMap().sameDirection(gameModel.getMap().findPlayer(target),gameModel.getMap().getSquare(rd,cd))) && (3>gameModel.getMap().distance(target.getRow(),target.getColumn(),rd,cd,1))){

            gameModel.getMap().movePlayer(target,gameModel.getMap().getSquare(rd,cd));
        }
    }
    
    /**
     * Effect.
     *
     * @param gameModel  the game model
     * @param destSquare the dest square
     * @param target     the target

    public void effect(GameModel gameModel, Square destSquare, Player target) {

        Square actualSquare = gameModel.getMap().getSquare(target.getRow(), target.getColumn());
        if ((gameModel.getMap().sameDirection(destSquare, actualSquare)) && (3 > gameModel.getMap().distance(destSquare, actualSquare, 1))) {

            gameModel.getMap().movePlayer(target, destSquare);
        }

    }

     */


}
