package it.polimi.model;



import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.RunActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvaible;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Action model.
 */
public class ActionModel {

    private GameModel gameModel;
    private int action = 0;

    Map map = gameModel.getMap();
    Player actualPlayer = gameModel.getActualPlayer();

    /**
     * Gets action number.
     *
     * @return the action number
     */
    public int getAction() {

        return action;
    }

    /**
     * Gets game model.
     *
     * @return the game model
     */
    public GameModel getGameModel() {

        return gameModel;
    }

    /**
     * Run Action.
     *
     * @param targetSquare the target square to move
     * @throws RunActionMaxDistLimitException the run action max dist limit exception
     */
    public void runActionModel(Square targetSquare) throws RunActionMaxDistLimitException {

        if (map.distance(map.findPlayer(actualPlayer), targetSquare) < 4) {

            map.movePlayer(actualPlayer, targetSquare);
            action++;
        } else {

            //run action not valid
            throw new RunActionMaxDistLimitException();
        }
    }

    /**
     * Grab Action.
     *
     * @param targetSquare the target square where grab
     * @param weaponIndex  the weapon index position in generation square
     * @throws CatchActionMaxDistLimitException the catch action max dist limit exception
     * @throws CatchActionFullObjException      the catch action full obj exception
     */
    public void grabActionModel(Square targetSquare, int weaponIndex) throws CatchActionMaxDistLimitException, CatchActionFullObjException {

        //adrenalinic distance
        int maxDist;

        if (actualPlayer.getPlayerBoard().getDamages().size() < 2) {

            maxDist = 1;
        } else {

            maxDist = 2;
        }
        if (map.distance(map.findPlayer(actualPlayer), targetSquare) < maxDist) {

            map.movePlayer(actualPlayer, targetSquare);
            if (!map.isGenerationSquare(targetSquare) && actualPlayer.getPlayerBoard().getPlayerPowerUps().size() < 4) {

                actualPlayer.catchAmmoCard(((NormalSquare) map.findPlayer(actualPlayer)).catchAmmoCard());
                action++;
            } else if (map.isGenerationSquare(targetSquare) && actualPlayer.getPlayerBoard().getPlayerWeapons().size() < 4) {

                actualPlayer.getPlayerBoard().addWeapon(((GenerationSquare) map.findPlayer(actualPlayer)).catchWeapon(weaponIndex));
                action++;
            } else {

                throw new CatchActionFullObjException();
            }
        } else {

            throw new CatchActionMaxDistLimitException();
        }
    }

    /**
     * Check the number in the turn.
     *
     * @return true if can do action, else otherwise
     */
    public boolean checkActionCount() {
        if (action == 1) {

            //gameModel.setState(State.ACTION1);//todo commento perchè non mi compila
            return true;
        } else if (action == 2) {

            //gameModel.setState(State.ACTION2);todo commento perchè non mi compila
            return true;
        } else {
            return false;
        }
    }



    /**
     * Use power up Newton.
     *
     * @param newton       the newton powerUp
     * @param targetPlayer the target player
     * @param targetSquare the target square
     * @throws NoPowerUpAvaible   No power up avaible
     * @throws NotInSameDirection Not in same direction
     * @throws NotValidDistance   Not valid distance
     */
    public void usePowerUpNewton(Newton newton, Player targetPlayer, Square targetSquare) throws NoPowerUpAvaible, NotInSameDirection, NotValidDistance {

        gameModel.setState(State.POWERUP);
        newton.effect(gameModel.getMap(), targetSquare, targetPlayer);

    }

    /**
     * Use power up Teleporter.
     *
     * @param teleporter   the teleporter PowerUp
     * @param targetSquare the target square
     * @throws NoPowerUpAvaible the no power up avaible
     */
    public void usePowerUpTeleporter(Teleporter teleporter, Square targetSquare) throws NoPowerUpAvaible {

        gameModel.setState(State.POWERUP);
        teleporter.effect(gameModel.getActualPlayer(), gameModel.getMap(), targetSquare);

    }

    /**
     * Use power up Targeting Scope.
     *
     * @param targetingScope the targeting scope PowerUp
     * @param targetPlayer   the target player
     * @throws NoPowerUpAvaible NO power up available
     */
    public void usePowerUpTargetingScope(TargetingScope targetingScope, Player targetPlayer) throws NoPowerUpAvaible {

        gameModel.setState(State.POWERUP);
        targetingScope.effect(gameModel.getActualPlayer(), targetPlayer);

    }

    /**
     * Use power up Tag Back Grenade.
     *
     * @param tagBackGrenade the tag back grenade powerUp
     * @param targetPlayer   the target player
     * @throws NoPowerUpAvaible No power up avaible
     * @throws NotVisibleTarget Not visible target
     */
    public void usePowerUpTagBackGrenade(TagBackGrenade tagBackGrenade, Player targetPlayer) throws NoPowerUpAvaible, NotVisibleTarget {

        gameModel.setState(State.POWERUP);
        tagBackGrenade.effect(gameModel.getMap(), gameModel.getActualPlayer(), targetPlayer);
    }

    /**
     * Respawn player.
     *
     * @param player           the player to respawn
     * @param generationSquare the generation square
     * @param powerUpCard      the power up card to add to player
     */
    public void respawnPlayer(Player player, Square generationSquare, PowerUpCard powerUpCard) {

        player.getPlayerBoard().resetDamage();
        player.setAlive(true);
        gameModel.getMap().addPlayerOnSquare(generationSquare, player);
        player.getPlayerBoard().addPowerUp(powerUpCard);
    }


    public void scoringPlayerBoard(Player player) {

        PlayerBoard playerBoard = player.getPlayerBoard();
        HashMap<EnumColorPlayer, Integer> colorDamageHashMap = new HashMap<>();

        for (Player a : gameModel.getPlayers()) {
            colorDamageHashMap.put(a.getColor(), player.getPlayerBoard().colorOccurrenceInDamages(a.getColor()));
        }

        //first blood
        playerBoard.getDamages();
    }


}
        
    
        


