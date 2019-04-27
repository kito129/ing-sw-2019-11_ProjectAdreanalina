package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The type Newton.
 */
public class Newton extends PowerUpCard {
    
    /**
     * Instantiates a new Newton.
     *
     * @param colorCard the color card
     */
    public Newton(EnumColorCard colorCard) {

        super("NEWTON", colorCard);

    }
    
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param rd        the rd
     * @param cd        the cd
     * @param target    the target
     */
    public void effect(GameModel gameModel, int rd, int cd, Player target){

        if((gameModel.getMap().sameDirection(gameModel.getMap().findPlayer(target),gameModel.getMap().searchSquare(rd,cd))) && (3>gameModel.getMap().distance(target.getRow(),target.getColumn(),rd,cd,1))){

            gameModel.getMap().movePlayer(target,gameModel.getMap().searchSquare(rd,cd));
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

        Square actualSquare = gameModel.getMap().searchSquare(target.getRow(),target.getColumn());
        if((gameModel.getMap().sameDirection(destSquare,actualSquare)) && (3>gameModel.getMap().distance(destSquare,actualSquare,1))){

            gameModel.getMap().movePlayer(target,destSquare);
        }
    }
}
