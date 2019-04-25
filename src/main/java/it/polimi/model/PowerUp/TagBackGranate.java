package it.polimi.model.PowerUp;
import it.polimi.model.*;
import java.util.ArrayList;

public class TagBackGranate extends PowerUpCard {

    public TagBackGranate(EnumColorCard colorCard) {

        super("TAGBACK GRANATE", colorCard);

    }

    public void effect(GameModel gameModel,Player damagedPlayer){

        if(gameModel.getMap().isVisible(gameModel.getActualPlayer().getRow(),gameModel.getActualPlayer().getColumn(),damagedPlayer.getRow(),damagedPlayer.getColumn())){

            EnumColorPlayer colorOfDamagedPlayer = damagedPlayer.getColor();
            Player actualPlayer=gameModel.getActualPlayer();
            actualPlayer.getPlayerBoard().increaseDamage(colorOfDamagedPlayer);
        }else{

            //TOdo vedere come gestire il fatto che, non lo vedo.
        }
    }

}

