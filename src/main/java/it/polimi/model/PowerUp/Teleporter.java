package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The poweup Teleporter.
 */
public class Teleporter extends PowerUpCard {
    
    /**
     * Instantiates a new Teleporter.
     *
     * @param colorCard the color card
     */
    public Teleporter(EnumColorCard colorCard) {

        super("TELOPORTER", colorCard);

    }
    
    /**
     * Effect.
     *
     * @param gameModel the game model
     * @param rd        the row destination
     * @param cd        the column destination
     */
    public void effect(GameModel gameModel, int rd, int cd) {

        Square destSquare = gameModel.getMap().getSquare(rd,cd);
        gameModel.getMap().movePlayer(gameModel.getActualPlayer(),destSquare);
        }
    
    /**
     * Effect.
     *
     * @param gameModel  the game model
     * @param destSquare the destination square
     */
    public void effect(GameModel gameModel, Square destSquare) {

        gameModel.getMap().movePlayer(gameModel.getActualPlayer(),destSquare);
    }

}

