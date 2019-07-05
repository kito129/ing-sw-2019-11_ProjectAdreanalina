package it.polimi.model;

import it.polimi.controller.FunctionController;
import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.Serializable;
import java.lang.management.PlatformLoggingMXBean;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type GameModel model.
 */
public class GameModel implements Serializable {

    //observable pattern
    private ArrayList<RemoteView> remoteViews = new ArrayList<>();
    //object for game model
    private State state;
    private State beforeError;
    private Map map;
    private Player actualPlayer;
    private KillShotTrack killShotTrack= new KillShotTrack();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> alivePlayer = new ArrayList<>();
    //action count
    int actionCount;
    //first spawn
    Player spawnPlayer;
    private int spawnedPlayer;
    //deck
    private AmmoDeck ammoDeck = new AmmoDeck();
    private PowerUpDeck powerUpDeck = new PowerUpDeck();
    private WeaponDeck weaponDeck = new WeaponDeck();
    //color for new player
    private ArrayList<EnumColorPlayer> gameColor = new ArrayList<>(5);
    //weapon necessary
    private WeaponCard weaponSelected; //current weapon for current Player
    private WeaponState weaponState;
    private WeaponsEffect actualWeaponEffect;
    private ArrayList<WeaponsEffect> availableEffect = new ArrayList<>();
    private String weaponName;
    private WeaponsEffect beforeEffect;
    private ArrayList<Player> playerDamaged= new ArrayList<>();
    private ArrayList<Player> playerMarked = new ArrayList<>();
    //end turn
    private ArrayList<Player> deadPlayer= new ArrayList<>();
    private Player actualDeadPLayer;
    private boolean endTurn;
    //weapon to charge
    private ArrayList<WeaponCard> weaponToCharge = new ArrayList<>();
    //grenade
    private Player userGrenade;
    private ArrayList<Player> playerDamagedWithGrenade = new ArrayList<>();
    private ArrayList<Boolean> playerDamagedWithGrenadeVisibility = new ArrayList<>();
    private int userGrenadeCount;
    private  ArrayList<Player> usedGrenade = new ArrayList<>();
    //respawn
    
    //powerup
    private PowerUpCard powerUpSelected; //current weapon effect for current Player
    //message
    private String errorMessage;
    private String messageToCurrentView;
    private String messageToAllView;
    
    public GameModel() {
        
        state=State.LOBBY;
        //create map
        this.map = new Map(MapCreator.createB(),"MAPB");
        //populate list of color for the player
        populateColor();
        
    }
    
    public EnumColorPlayer getRandomColor(){

        if(gameColor.size()!=0) {

            Collections.shuffle(gameColor);
            EnumColorPlayer randomColor = gameColor.get(0);
            gameColor.remove(0);
            return randomColor;
        }
        return null;
    }
    
    public ArrayList<Player> getUsedGrenade () {
        
        return usedGrenade;
    }
    
    public Player getActualDeadPLayer () {
        
        return actualDeadPLayer;
    }
    
    public void setActualDeadPLayer (Player actualDeadPLayer) {
        
        this.actualDeadPLayer = actualDeadPLayer;
    }
    
    public ArrayList<Boolean> getPlayerDamagedWithGrenadeVisibility () {
        
        return playerDamagedWithGrenadeVisibility;
    }
    
    public ArrayList<Player> getPlayerDamagedWithGrenade () {
        
        return playerDamagedWithGrenade;
    }
    
    public Player getUserGrenade () {
        
        return userGrenade;
    }
    
    public int getUserGrenadeCount () {
        
        return userGrenadeCount;
    }
    
    public void incremanetGrenade(){
        
        this.userGrenadeCount++;
    }
    
    public void resetUserGrenadeCount(){
        
        this.userGrenadeCount=0;
    }
    
    public void setUserGrenade (Player userGrenade) {
        
        this.userGrenade = userGrenade;
    }
    
    public int getActionCount () {
        
        return actionCount;
    }
    
    public void incrementActionCount(){
        
        this.actionCount++;
    }
    
    public void resetActionCount(){
        
        this.actionCount=0;
    }
    
    private void populateColor(){
        
        this.gameColor.add(EnumColorPlayer.BLU);
        this.gameColor.add(EnumColorPlayer.GREEN);
        this.gameColor.add(EnumColorPlayer.GREY);
        this.gameColor.add(EnumColorPlayer.PINK);
        this.gameColor.add(EnumColorPlayer.YELLOW);
    }
    
    
    public ArrayList<Player> getPlayerDamaged () {
        
        return playerDamaged;
    }
    

    public State getBeforeError () {
        
        return beforeError;
    }
    
    public void setBeforeError (State beforeError) {
        
        this.beforeError = beforeError;
    }
    
    public int getSpawnedPlayer () {
        
        return spawnedPlayer;
    }
    
   public void incrementSpawnedPlayer (){
        
        spawnedPlayer++;
   }
   
   public void resetSpawnedPLayer(){
        
        spawnedPlayer=0;
   }
    
    public PowerUpCard getPowerUpSelected () {
        
        return powerUpSelected;
    }
    
    public String getWeaponName () {
        
        return weaponName;
    }
    
    
    public void setWeaponName (String weaponName) {
        
        this.weaponName = weaponName;
    }
    
    public WeaponsEffect getBeforeEffect () {
        
        return beforeEffect;
    }
    
    public Player getSpawnPlayer () {
        
        return spawnPlayer;
    }
    
    public boolean isEndTurn () {
        
        return endTurn;
    }
    
    public void setEndTurn (boolean endTurn) {
        
        this.endTurn = endTurn;
    }
    
    public void setSpawnPlayer (Player spawnPlayer) {
        
        this.spawnPlayer = spawnPlayer;
    }
    
    public void setBeforeEffect (WeaponsEffect beforeEffect) {

        this.beforeEffect = beforeEffect;
    }
    
    public void setPowerUpSelected (PowerUpCard powerUpSelected) {
        
        this.powerUpSelected = powerUpSelected;
    }

    //todo da eliminare
    public void setWeaponSelected (WeaponCard weaponSelected) {
        
        this.weaponSelected = weaponSelected;
    }
    
    /**
     * Gets actual player.
     *
     * @return the actual player
     */
    public Player getActualPlayer() {

        return actualPlayer;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public Map getMap() {

        return map;
    }
    
    public ArrayList<WeaponCard> getWeaponToCharge () {
        
        return weaponToCharge;
    }
    
    public ArrayList<RemoteView> getRemoteViews() {

        return remoteViews;
    }

    /**
     * Gets kill shot track .
     *
     * @return the kill shot track
     */
    public KillShotTrack getKillShotTrack() {

        return killShotTrack;
    }
    
    /**
     * Gets players.
     *
     * @return the players
     */

    public void setPlayers(Player player){

        this.players.add(player);
    }


    public ArrayList<Player> getPlayers(boolean wantCurrent, boolean wantDead) {
       
        ArrayList<Player> temp = new ArrayList<Player>(players);
        if (wantDead) {
            
            if (wantCurrent) {
        
                return players;
            } else {
               
                temp.remove(getActualPlayer());
                return temp;
            }
        } else {
            
            for (int i1 = 0; i1 < temp.size(); i1++) {
                Player a = temp.get(i1);
                
                if (!a.isAlive()) {
                    temp.remove(a);
                    i1--;
                }
            }
            if (wantCurrent) {
                
                return temp;
            } else {
                
                temp.remove(getActualPlayer());
                return temp;
            }
        }
    }

    public void setActualPlayer(Player actualPlayer) {

        this.actualPlayer = actualPlayer;
    }

    
    /**
     * Gets ammo deck.
     *
     * @return the ammo deck
     */
    public AmmoDeck getAmmoDeck() {

        return ammoDeck;
    }
    
    /**
     * Gets power up deck.
     *
     * @return the power up deck
     */
    public PowerUpDeck getPowerUpDeck() {

        return powerUpDeck;
    }
    
    /**
     * Gets weapon deck.
     *
     * @return the weapon deck
     */
    public WeaponDeck getWeaponDeck() {

        return weaponDeck;
    }
    
    public State getState () {
        
        return state;
    }
    
    public String getMessageToCurrentView () {
        
        return messageToCurrentView;
    }
    
    public String getMessageToAllView () {
        
        return messageToAllView;
    }
    
    public ArrayList<WeaponsEffect> getAvailableEffect () {
        
        return availableEffect;
    }
    
    public ArrayList<Player> getPlayerMarked () {
        
        return playerMarked;
    }
    
    


    public void setState (State state)  {
        
        if(state!= State.ERROR) {
            this.setBeforeError(getState());
        }
        this.state=state;
        notifyObserver(this);
    }
    
    public WeaponState getWeaponState () {
        
        return weaponState;
    }
    
    public WeaponsEffect getActualWeaponEffect () {
        
        return actualWeaponEffect;
    }

    public void setWeaponState (WeaponState weaponState) {

        this.weaponState = weaponState;
    }

    public void setActualWeaponEffect (WeaponsEffect actualWeaponEffect) {

        this.actualWeaponEffect = actualWeaponEffect;
    }
    
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public void checkOverElvenDamage(){
        for (Player a:players){
            if (a.isMarkToDead()){
                a.setAlive(false);
            }
        }
    }

    public void setDeadPlayer () {
        
        checkOverElvenDamage();
        ArrayList<Player> tempPLayers = new ArrayList<>();
        for (Player a:players){
            if(!a.isAlive()){
                tempPLayers.add(a);
            }
        }
        this.deadPlayer=tempPLayers;
    }
    
    public ArrayList<Player> getDeadPlayers(){
       
        return deadPlayer;
    }
    
    public void setMessageToCurrentView (String messageToCurrentView) {
        
        this.messageToCurrentView = messageToCurrentView;
    }
    
    public void setMessageToAllView (String messageToAllView) {
        
        this.messageToAllView = messageToAllView;
    }
    

    
    public ArrayList<EnumColorPlayer> getPlayerColor(){
        
        ArrayList<EnumColorPlayer> playerColor = new ArrayList<>();
        
        for (Player a: getPlayers(true,true)){
            playerColor.add(a.getColor());
        }
        return playerColor;
    }
    
    public Player getPlayerByColor(EnumColorPlayer color){
        
        for (Player a:players){
            if (a.getColor()==color){
                return a;
            }
        }
        return null;
    }

    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     */

    public void addObserver(RemoteView observer) throws RemoteException{

        setPlayers(new Player(getPlayers(true,true).size()+1, observer.getUser(), getRandomColor(),this));
        remoteViews.add(observer);
    }
    
    /**
     * removes an RMI observer from the observers's remoteViews (setting him as 'null')
     * @param observer the observer to be removed
     */

    public void removeObserver(RemoteView observer){

        int indexOfObserver=remoteViews.indexOf(observer);
        remoteViews.set(indexOfObserver, null);
    }
    
    /**
     * adds again an RMI observer after he has lost connection
     * @param observer the observer to be added
     * @throws RemoteException if the reference could not be accessed
     */

    public void reAddObserver(RemoteView observer) throws RemoteException {

        int indexToReAdd = -1;
        for (Player player : players) {

            if (player.getName().equals(observer.getUser())) {

                    indexToReAdd = players.indexOf(player);
                    break;
            }
        }
        if(indexToReAdd!=-1) {
            remoteViews.set(indexToReAdd, observer);
            players.get(indexToReAdd).setOnlineModel(true);
        }
    }

    public void notifyObserver (GameModel gameModel){

        int indexOfCurrentObserver=-1;

        try {

            for(RemoteView observer: getRemoteViews()){

                if(observer!=null) {

                    if((getPlayers(true,true).indexOf(actualPlayer)!=getRemoteViews().indexOf(observer))){

                        int count=getRemoteViews().indexOf(observer);

                        if(getPlayers(true,true).get(count).getOnline()) {

                            observer.update(this);
                        }
                    }else{

                        indexOfCurrentObserver=getRemoteViews().indexOf(observer);
                    }
                }
            }
            if(indexOfCurrentObserver!=-1) {
                getRemoteViews().get(indexOfCurrentObserver).update(gameModel);
            }
        }catch (RemoteException remoteException){

        }
    }
    

    public Player getPlayerById(int i) throws MapException {

        for (Player a : this.players){

            if(a.getId()==i){
                
                return a;
            }
        }
    throw  new MapException();
    }
    
}
