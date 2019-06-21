package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type GameModel model.
 */
public class GameModel implements Serializable {

    //capire quali classi vanno serializzate
    
    //observable pattern
    private ArrayList<RemoteView> remoteViews = new ArrayList<>();
    private State state;
    private Map map;
    private Player actualPlayer;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private ArrayList<EnumColorPlayer> gameColor = new ArrayList<>(5);
    //weapon necessary
    private WeaponState weaponState;
    private WeaponsEffect weaponsEffect;
    public ArrayList<WeaponsEffect> availableEffect;
    //message
    private String errorMessage;
    private String messageToCurrentView;
    private String messageToAllView;
    
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
    
    public String getMessageToAllView () {
        
        return messageToAllView;
    }
    
    public ArrayList<WeaponsEffect> getAvailableEffect () {
        
        return availableEffect;
    }
    
    public void setAvailableEffect (ArrayList<WeaponsEffect> availableEffect) {
        
        this.availableEffect = availableEffect;
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
    
    public void setMessageToAllView (String messageToAllView) {
        
        this.messageToAllView = messageToAllView;
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

    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     */

    public void addObserver(RemoteView observer) throws RemoteException{

        setPlayers(new Player(getPlayers(true).size()+1, observer.getUser(), getRandomColor() ));
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
        }
    }

    public void notifyObserver (GameModel gameModel){

        int indexOfCurrentObserver=-1;

        try {

            for(RemoteView observer: getRemoteViews()){

                if(observer!=null) {

                    if((getPlayers(true).indexOf(actualPlayer)!=getRemoteViews().indexOf(observer))){

                        int count=getRemoteViews().indexOf(observer);

                        if(getPlayers(true).get(count).getOnline()) {

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
