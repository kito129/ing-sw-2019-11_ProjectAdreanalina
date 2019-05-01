package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The type Tag back granate.
 */
public class TagBackGrenade extends PowerUpCard {
    
    /**
     * Instantiates a new Tag back granate.
     *
     * @param colorCard the color card
     */
    public TagBackGrenade(EnumColorCard colorCard) {

        super("TAGBACK GRANATE", colorCard);

    }
    
    /**
     * Effect.
     *
     * @param gameModel     the game model
     * @param damagedPlayer the damaged player
     */
    public void effect(GameModel gameModel,Player damagedPlayer){

        if(gameModel.getMap().isVisible(gameModel.getActualPlayer().getRow(),gameModel.getActualPlayer().getColumn(),damagedPlayer.getRow(),damagedPlayer.getColumn())){

            EnumColorPlayer colorOfDamagedPlayer = damagedPlayer.getColor();
            Player actualPlayer=gameModel.getActualPlayer();
            actualPlayer.getPlayerBoard().increaseDamages(colorOfDamagedPlayer);
        }else{

            //TOdo vedere come gestire il fatto che, non lo vedo.
        }
    }

}
