package it.polimi.model.PowerUp;
import it.polimi.model.*;

public class Newton extends PowerUpCard {

    public Newton(EnumColorCard colorCard) {

        super("NEWTON", colorCard);

    }

    public void effect(GameModel gameModel, int rd, int cd, Player target){

        if((gameModel.getMap().sameDirection(gameModel.getMap().findPlayer(target),gameModel.getMap().searchSquare(rd,cd))) && (3>gameModel.getMap().distance(target.getRow(),target.getColumn(),rd,cd,1))){

            gameModel.getMap().movePlayer(target,gameModel.getMap().searchSquare(rd,cd));
        }
    }

    public void effect(GameModel gameModel, Square destSquare, Player target){

        Square actualSquare = gameModel.getMap().searchSquare(target.getRow(),target.getColumn());
        if((gameModel.getMap().sameDirection(destSquare,actualSquare)) && (3>gameModel.getMap().distance(destSquare,actualSquare))){

            gameModel.getMap().movePlayer(target,destSquare);
        }
    }
}
