package it.polimi.model.PowerUp;
import it.polimi.model.*;

/**
 * The type Targeting scope.
 */
public class TargetingScope extends PowerUpCard {

    /**
     * Instantiates a new Targeting scope.
     *
     * @param colorCard the color card
     */
    public TargetingScope(EnumColorCardAndAmmo colorCard) {

        super("TARGETING SCOPE", colorCard);
        setDescription("Puoi giocare questa carta quando stai dando danno a uno o più bersagli.\n" +
                "Paga 1 cubo munizioni di qualsiasi colore.\n" +
                "Scegli 1 dei bersagli e dagli 1 segnalino danno aggiuntivo.\n" +
                "Nota: non puoi usare questo potenziamento per dare 1 danno a un bersaglio che sta solo ricevendo marchi.");
    }

    /**
     * Gives to the target one damage of the color of current player.
     *
     * @param currentPlayer the player who gives the damage and uses this power up.
     * @param target the player who gets one damage.
     */
    public void effect(Player currentPlayer, Player target){

        target.singleDamage(currentPlayer.getColor());
        System.out.println("danneggio lui:");
        target.stampa();
    }


}
