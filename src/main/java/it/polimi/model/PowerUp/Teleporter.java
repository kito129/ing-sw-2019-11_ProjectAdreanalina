package it.polimi.model.PowerUp;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;

/**
 * The poweup Teleporter.
 */
public class Teleporter extends PowerUpCard {

    /**
     * Instantiates a new Teleporter.
     *
     * @param colorCard the color card
     */
    public Teleporter(EnumColorCardAndAmmo colorCard) {

        super("TELEPORTER", colorCard);
        setDescription("Puoi giocare questa carta nel tuo turno prima o dopo aver svolto qualsiasi azione.\n" +
                "Prendi la tua miniatura e piazzala in un qualsiasi quadrato sulla plancia.\n" +
                "(Non puoi usare questo potenziamento dopo che hai visto dove un altro giocatore si rigenera alla fine del tuo turno, è troppo tardi.)");
    }

    /**
     * Moves the current player to any square of the map
     *
     * @param currentPlayer the current player that uses this power up.
     * @param map           the map of the game.
     * @param destSquare    the square where the player wants to go.
     */
    public void effect(Player currentPlayer, Map map, Square destSquare) throws  MapException {

        map.movePlayer(currentPlayer, destSquare);
    }
}

