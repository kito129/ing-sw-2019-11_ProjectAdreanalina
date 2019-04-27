package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * Teh power Up newton
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
    
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param rd        the row destination
     * @param cd        the column designation
     * @param target    the target
     */
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
     */
    public void effect(GameModel gameModel, Square destSquare, Player target){

        Square actualSquare = gameModel.getMap().getSquare(target.getRow(),target.getColumn());
        if((gameModel.getMap().sameDirection(destSquare,actualSquare)) && (3>gameModel.getMap().distance(destSquare,actualSquare,1))){

            gameModel.getMap().movePlayer(target,destSquare);
        }
    }
}
