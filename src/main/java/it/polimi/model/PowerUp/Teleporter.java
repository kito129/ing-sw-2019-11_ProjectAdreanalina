package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The type Teleporter.
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
     * @param rd        the rd
     * @param cd        the cd
     */
    public void effect(GameModel gameModel, int rd, int cd) {

        Square destSquare = gameModel.getMap().searchSquare(rd,cd);
        gameModel.getMap().movePlayer(gameModel.getActualPlayer(),destSquare);
        }
    
    /**
     * Effect.
     *
     * @param gameModel  the game model
     * @param destSquare the dest square
     */
    public void effect(GameModel gameModel, Square destSquare) {

        gameModel.getMap().movePlayer(gameModel.getActualPlayer(),destSquare);
    }

}

