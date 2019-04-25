package it.polimi.model.PowerUp;
import it.polimi.model.*;

public class Teleporter extends PowerUpCard {

    public Teleporter(EnumColorCard colorCard) {

        super("TELOPORTER", colorCard);

    }

    public void effect(GameModel gameModel, int rd, int cd) {
        Square destSquare = gameModel.getMap().searchSquare(rd,cd);
        gameModel.getMap().movePlayer(gameModel.getActualPlayer(),destSquare);
        }


}

