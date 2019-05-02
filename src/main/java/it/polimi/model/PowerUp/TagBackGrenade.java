package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.InvalidActionForThisCard;

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

    public void effect(Map map, Player currentPlayer, Player damagedPlayer) throws InvalidActionForThisCard {

        if(map.isVisible(currentPlayer,damagedPlayer)){

            currentPlayer.receiveSingledamage(damagedPlayer.getColor());
        }else {

            throw new InvalidActionForThisCard();
        }
    }



    /*
    /**
     * Effect.
     *
     * @param gameModel     the game model
     * @param damagedPlayer the damaged player

    public void effect(GameModel gameModel,Player damagedPlayer){

        if(gameModel.getMap().isVisible(gameModel.getActualPlayer().getRow(),gameModel.getActualPlayer().getColumn(),damagedPlayer.getRow(),damagedPlayer.getColumn())){

            EnumColorPlayer colorOfDamagedPlayer = damagedPlayer.getColor();
            Player actualPlayer=gameModel.getActualPlayer();
            actualPlayer.getPlayerBoard().increaseDamages(colorOfDamagedPlayer);
        }else{


        }
    }

     */

}

