package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type GameModel model.
 */
public class GameModel implements Serializable {

    //capire quali classi vanno serializzate
    
    //observable pattern
    private ArrayList<RemoteView> remoteViews = new ArrayList<>();
    private Map map;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private Player actualPlayer;
    private State state;
    private WeaponState weaponState;
    private WeaponsEffect weaponsEffect;
    private String errorMessage;
    private String messageToCurrentView;
    private String messsageToAllView;
    private ArrayList<EnumColorPlayer> gameColor = new ArrayList<>(5);
    
    public GameModel(){
        
        state=State.LOBBY;
        //create object of the gam
        this.killShotTrack= new KillShotTrack();
        this.ammoDeck = new AmmoDeck();
        this.powerUpDeck=new PowerUpDeck();
        this.weaponDeck=new WeaponDeck();
        players=new ArrayList<>();
        //create map and poulate list of color for the player
        this.map = new Map(MapCreator.createA(),"mapp a");
        populateColor();
        
    }
    
    public EnumColorPlayer getRandomColor(){
    
        Collections.shuffle(gameColor);
        EnumColorPlayer randomColor = gameColor.get(0);
        gameColor.remove(0);
        return randomColor;
        
    }
    
    public void populateColor(){
        
        this.gameColor.add(EnumColorPlayer.BLU);
        this.gameColor.add(EnumColorPlayer.GREEN);
        this.gameColor.add(EnumColorPlayer.GREY);
        this.gameColor.add(EnumColorPlayer.PINK);
        this.gameColor.add(EnumColorPlayer.YELLOW);
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

    public ArrayList<Player> getPlayers(boolean wantCurrent) {
        
        if (wantCurrent) {

            return players;        
        } else
        {
            ArrayList<Player> temp =new ArrayList<Player>(players);
            temp.remove(getActualPlayer());
            return temp;
        }

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
    
    public String getMesssageToAllView () {
        
        return messsageToAllView;
    }
    
    public void setPlayers(Player player){
       
        this.players.add(player);
        actualPlayer = this.players.get(0);
    }
    

    public void setState (State state)  {
        
        this.state=state;
        notifyObserver(this);
    }
    
    public WeaponState getWeaponState () {
        
        return weaponState;
    }
    
    public WeaponsEffect getWeaponsEffect () {
        
        return weaponsEffect;
    }
    
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    /**
     * Sets actual player.
     *
     * @param actualPlayer the actual player
     */
    public void setActualPlayer(Player actualPlayer) {
        
        this.actualPlayer = actualPlayer;
    }
    
    
    public ArrayList<Player> getDeadPlayers(){
        ArrayList<Player> tempPLayers = new ArrayList<>();
        for (Player a:players){
            if(!a.isAlive()){
                tempPLayers.add(a);
            }
        }
        return tempPLayers;
    }
    
    public void setMessageToCurrentView (String messageToCurrentView) {
        
        this.messageToCurrentView = messageToCurrentView;
    }
    
    public void setMesssageToAllView (String messsageToAllView) {
        
        this.messsageToAllView = messsageToAllView;
    }
    
    public void setWeaponState (WeaponState weaponState) {
        
        this.weaponState = weaponState;
    }
    
    public void setWeaponsEffect (WeaponsEffect weaponsEffect) {
        
        this.weaponsEffect = weaponsEffect;
    }
    
    public ArrayList<EnumColorPlayer> getPlayerColor(){
        
        ArrayList<EnumColorPlayer> playerColor = new ArrayList<>();
        
        for (Player a: getPlayers(true)){
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


    public List<RemoteView> getRemoteView(){

        return remoteViews;
    }
    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     */

    public void addObserver(RemoteView observer){
        
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

        //todo da rifare
        int index = 0;
        for(Player a : players){
            if((a.getName()).equals(observer.getUser())) {
                index = players.indexOf(a);
                break;
            }
        }
        remoteViews.set(index, observer);
    }

    public void notifyObserver (GameModel gameModel){

        //todo da fare

        for(RemoteView observer: getRemoteView()){

            try {
                observer.update(this);

            } catch (RemoteException e) {

            }
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
