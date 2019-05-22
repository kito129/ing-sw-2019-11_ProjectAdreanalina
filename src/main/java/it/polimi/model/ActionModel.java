package it.polimi.model;

import it.polimi.model.Exception.*;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;

import java.util.*;

/**
 * The type Action model.
 */
public class ActionModel {

    private GameModel gameModel;
    private int action = 0;
    private Map map;
    private Player actualPlayer;

    public ActionModel(GameModel gameModel){

        this.gameModel=gameModel;
        this.map=gameModel.getMap();
        this.actualPlayer=gameModel.getActualPlayer();
    }

    
    /**
     * Instantiates a new Action model.
     */
    public ActionModel(){
    
    }
    
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
    public void runActionModel(Square targetSquare) throws RunActionMaxDistLimitException, NotValidInput, NotValidSquareException, MapException {

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
    public void grabActionModel(Square targetSquare, int weaponIndex) throws CatchActionMaxDistLimitException, CatchActionFullObjException, NotValidInput, NotValidSquareException, MapException {

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
     * @throws NoPowerUpAvailable   No power up avaible
     * @throws NotInSameDirection Not in same direction
     * @throws NotValidDistance   Not valid distance
     */
    public void usePowerUpNewton(Newton newton, Player targetPlayer, Square targetSquare) throws NoPowerUpAvailable, NotInSameDirection, NotValidDistance, NotValidInput, MapException {

        gameModel.setState(State.USEPOWERUP);
        newton.effect(gameModel.getMap(), targetSquare, targetPlayer);

    }
    
    /**
     * Use power up Teleporter.
     *
     * @param teleporter   the teleporter PowerUp
     * @param targetSquare the target square
     * @throws NoPowerUpAvailable the no power up avaible
     */
    public void usePowerUpTeleporter(Teleporter teleporter, Square targetSquare) throws  NotValidInput, MapException {

        gameModel.setState(State.USEPOWERUP);
        teleporter.effect(gameModel.getActualPlayer(), gameModel.getMap(), targetSquare);

    }
    
    /**
     * Use power up Targeting Scope.
     *
     * @param targetingScope the targeting scope PowerUp
     * @param targetPlayer   the target player
     * @throws NoPowerUpAvailable NO power up available
     */
    public void usePowerUpTargetingScope(TargetingScope targetingScope, Player targetPlayer)  {

        gameModel.setState(State.USEPOWERUP);
        System.out.println(gameModel.getActualPlayer().toString());
        targetingScope.effect(gameModel.getActualPlayer(),targetPlayer);

    }
    
    /**
     * Use power up Tag Back Grenade.
     *
     * @param tagBackGrenade the tag back grenade powerUp
     * @param targetPlayer   the target player
     * @throws NoPowerUpAvailable No power up avaible
     * @throws NotVisibleTarget Not visible target
     */
    public void usePowerUpTagBackGrenade(TagBackGrenade tagBackGrenade, Player targetPlayer) throws NotVisibleTarget   {

        gameModel.setState(State.USEPOWERUP);
        tagBackGrenade.effect(gameModel.getMap(), gameModel.getActualPlayer(), targetPlayer);
    }
    
    /**
     * Respawn player.
     *
     * @param player           the player to respawn
     * @param generationSquare the generation square
     * @param powerUpCard      the power up card to add to player
     */
    public void respawnPlayer(Player player, Square generationSquare, PowerUpCard powerUpCard) throws  MapException {

        player.getPlayerBoard().resetDamage();
        player.setAlive(true);
        gameModel.getMap().addPlayerOnSquare(generationSquare, player);
        player.getPlayerBoard().addPowerUp(powerUpCard);
    }
    
    // TODO 18/05
    
    /**
     * The type Player score, useful for scoring computation.
     */
    public class PlayerScore implements Comparable<PlayerScore>{
        
        private EnumColorPlayer color;
        private int value;
        
        @Override
        public int compareTo(PlayerScore o) {
            
            return (this.getValue()-o.getValue());
        }
    
        @Override
        public String toString () {
        
            return (this.getValue() + this.getColor().toString());
        }
    
        /**
         * Instantiates a new Player score.
         *
         * @param color the color of the player
         * @param value color value in point.
         */
        public PlayerScore(EnumColorPlayer color, int value){
            
            this.color=color;
            this.value=value;
        }
    
        /**
         * Gets color of the player.
         *
         * @return the color
         */
        public EnumColorPlayer getColor () {
        
            return color;
        }
    
        /**
         * Gets value of the color player.
         *
         * @return the value
         */
        public int getValue () {
        
            return value;
        }
    
        /**
         * Sets value.
         *
         * @param value the value
         */
        public void setValue (int value) {
        
            this.value = value;
        }
        
    }
    
    /**
     * Calculate damages point, valuating occurrence and tie
     *
     * @param player the player that have PlayerBoard to scoring
     * @return an Array list in order by the point to add to single player with same color.
     */
    public ArrayList<EnumColorPlayer> damagesOrderColor (Player player) {
        
        //create temp variables
        PlayerBoard playerBoard = player.getPlayerBoard();
        ArrayList<PlayerScore> playerScores = new ArrayList<PlayerScore>();
        
        ArrayList<EnumColorPlayer> playerColor = new ArrayList<EnumColorPlayer>(gameModel.getPlayerColor());
        
        //get color occurrence for all player in game
        for (EnumColorPlayer a : playerColor) {
            
            if(playerBoard.colorOccurrenceInDamages(a)>0) {
                playerScores.add(new PlayerScore(a, playerBoard.colorOccurrenceInDamages(a)));
            }
        }
        
        //order in descending
        Collections.sort(playerScores);
        Collections.reverse(playerScores);
        //System.out.println(playerScores);
        
        //calculate the tie
        for (int i = 0; i < playerScores.size(); i++) {
            if(i<playerScores.size()-1) {
                PlayerScore cur = playerScores.get(i);
                PlayerScore next = playerScores.get(i + 1);
        
                if (cur.getValue() == next.getValue()) {
                    if (playerBoard.getFirstOccurrenceInDamage(cur.getColor()) > playerBoard.getFirstOccurrenceInDamage(next.getColor())) {
                        Collections.swap(playerScores, i, i + 1);
                    }
                }
            }
        }
        
        //create array of color in order of occurrence, calculated tie
        ArrayList<EnumColorPlayer> playerOrder = new ArrayList<>();
        
        for (PlayerScore a:playerScores){
            
            playerOrder.add(a.getColor());
        }
        return playerOrder;
    }
    
    /**
     * Scoring player board.
     *
     * @param player the player that have PlayerBoard to Score.
     */
    public void scoringPlayerBoard(Player player){
        
        /*
        if(player.isAlive()){
            return;
        }
        
         */
        PlayerBoard playerBoard = player.getPlayerBoard();
        
        
        //player color in order by occurrence (tie calculated)
        ArrayList<EnumColorPlayer> playerOrderDamage = new ArrayList<>(damagesOrderColor(player));
        //fist blood
        EnumColorPlayer firstBlood=playerBoard.getDamages().get(0);
        //death
        EnumColorPlayer death = playerBoard.getDamages().get(10);
        //overkill
        EnumColorPlayer overkill = null;
        if(playerBoard.getDamages().size()==12) {
            overkill = playerBoard.getDamages().get(11);
        }
        //TODO double kill
        
        //player point
        ArrayList<PlayerScore> playerPoint = new ArrayList<PlayerScore>();
        
        System.out.println(playerPoint);
        
        int temp=0;
        int pointTo=0;
        //create danno
        switch (playerBoard.getBoardValue()){
        case 8:
           pointTo =10;
           temp=pointTo;
            for (EnumColorPlayer a : playerOrderDamage) {
        
                //decrement point to ad to the a player
                temp-=2;
                if (temp < 2) {
    
                    temp = 1;
                }
                //add point if first blood
                if (a == firstBlood) {
                    
                    temp++;
                }
                playerPoint.add(new PlayerScore(a, temp));
            }
           
           break;
        case 6:
            pointTo =8;
            for (EnumColorPlayer a : playerOrderDamage) {
    
                temp-=2;
                if (temp < 2) {
            
                    pointTo = 1;
                    playerPoint.add(new PlayerScore(a, pointTo));
                }
            }
            break;
        case 4:
            pointTo =6;
            for (EnumColorPlayer a : playerOrderDamage) {
    
                temp-=2;
                if (temp < 2) {
            
                    pointTo = 1;
                    playerPoint.add(new PlayerScore(a, pointTo));
                }
            }
            break;
        case 2:
            pointTo =4;
            for (EnumColorPlayer a : playerOrderDamage) {
    
                temp-=2;
                if (temp < 2) {
            
                    pointTo = 1;
                    playerPoint.add(new PlayerScore(a, pointTo));
                }
            }
           break;
        case 1:
            pointTo =2;
            for (EnumColorPlayer a : playerOrderDamage) {
        
                temp-=2;
                if (temp < 2) {
            
                    pointTo = 1;
                    playerPoint.add(new PlayerScore(a, pointTo));
                }
            }
            break;
           
        }
        for (PlayerScore a:playerPoint){
            a.toString();
        }
        
        //share point to player in player point
        for (PlayerScore a:playerPoint){
            
            gameModel.getPlayerByColor(a.getColor()).increaseScore(a.value);
        }
        
        //put color in killshot track
        if(gameModel.getKillShotTrack().skullNumber()>1){
            
            ArrayList<EnumColorPlayer> toKillShot = new ArrayList<>();
            
            if(overkill!=null){
                
                toKillShot.add(death);
                toKillShot.add(overkill);
                gameModel.getPlayerByColor(overkill).singleMark(player.getColor());
            } else {
                
                toKillShot.add(death);
            }
            
            //update the killshot track point
            gameModel.getKillShotTrack().updateTrack(toKillShot);
            playerBoard.resetDamage();
            playerBoard.decreaseBoardValue();
            
            
        }
    }
    
    // TODO FINE 18/05


}
        
    
        


