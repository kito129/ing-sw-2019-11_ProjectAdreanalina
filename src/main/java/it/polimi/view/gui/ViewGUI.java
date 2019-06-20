

package it.polimi.view.gui;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.GameModel;
import it.polimi.model.Player;
import it.polimi.model.State;
import it.polimi.view.RemoteView;
import javafx.applicationls
        .Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViewGUI{

    private transient Stage mainStage;

    private boolean multiPlayer;
    private boolean returnOnline;
    private boolean online;
    private State state;
    private String user;
    private int choose1;
    private int choose2;
    private boolean endGame;
    private ArrayList<Integer> choices;
    private boolean restart;

    private RemoteGameController network;
    private RemoteView remoteView;
    private GameModel gameModel;

    private transient MatchController matchController;
    private transient StartController startController;


    /**
     * gets if a player has to be set online
     * @return true if the player has to be set online
     */

    public boolean getReturnOnline(){

        return returnOnline;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */

    public void start(Stage primaryStage) throws Exception{

        returnOnline = false;
        setOnline(true);
        choices = new ArrayList<>();
        choices.add(-1);
        endGame = false;
        restart = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/start.fxml"));
        Parent root = loader.load();

        startController = loader.getController();
        startController.setViewGUI(this);
        startController.init();

        primaryStage.setOnCloseRequest(event -> {

            Platform.exit();
            System.exit(0);
        });
        primaryStage.setTitle("ADRENALINA");
        primaryStage.setScene(new Scene(root, 896, 627));
        primaryStage.setResizable(false);
        mainStage = primaryStage;
        primaryStage.show();
        root.requestFocus();
    }

    /**
     * updates each view in the game
     * @param gameModel the gamemodel of the match
     */

    public void update(GameModel gameModel) {

        this.gameModel = gameModel;
        try {

            this.run();
        }catch (IOException e){

            //do nothing
        }
    }

    /**
     * modifies the view based on the current state
     * @throws IOException any exception thrown by the underlying OutputStream
     */

    private void run() throws IOException{

        returnOnline = false;
        state = gameModel.getState();

        switch (state){

            case LOBBY:
                viewLobby();
                break;
            default:
                assert false;
        }
    }

    // VIEW -------------------------------------------------------------------------------------------------
    /**
     * shows the players in the lobby
     */

    private void viewLobby(){

        Platform.runLater(()->{

            startController.printLobby();
            for (Player p : gameModel.getPlayers(true)){

                startController.addPrint("- " + p.getName());
            }
        });
    }

    // END VIEW ---------------------------------------------------------------------------------------------

    // GET SET ----------------------------------------------------------------------------------------------

    /**
     * gets if player is online or not
     * @return true if the player is online, false otherwise
     */

    public synchronized boolean getOnline(){

        return online;
    }

    /**
     * gets the client's username
     * @return the client's username
     */

    public String getUser() {

        return user;
    }

    /**
     * gets choose1
     * @return first choice of the client
     */

    public int getChoose1() {

        return choose1;
    }

    /**
     * gets choose2
     * @return second choice of the client
     */

    public int getChoose2() {

        return choose2;
    }

    /**
     * gets the list of inputs of the client
     * @return an arraylist of client's inputs
     */

    public ArrayList<Integer> getChoices(){

        return choices;
    }

    /**
     * gets if this client wants to restart the game
     * @return true if the client wants to restart the game, false otherwise
     */

    public boolean getRestart() {

        return restart;
    }

    /**
     * gets if has started a multiplayer match
     * @return true if the game is in multiplayer mode
     */

    public boolean getMultiPlayer(){

        return multiPlayer;
    }

    /**
     * gets the gamecontroller of the match
     * @return the gamecontroller of the match
     */

    RemoteGameController getNetwork(){

        return this.network;
    }

    /**
     * sets if the client is online or not
     */

    public synchronized void setOnline(boolean online){

        this.online = online;
        if(!online){

            Platform.runLater(()-> matchController.setInactive());
        }
    }

    /**
     * sets if the client wants to restart the game
     * @param restart the boolean to be set
     */

    public void setRestart(boolean restart) {

        this.restart = restart;
    }

    /**
     * sets first choice of the client
     * @param i the choice of the client
     */


    void setChoose1(int i){

        this.choose1 = i;
    }

    /**
     * sets the second choice of the client
     * @param i the choice of the client
     */

    void setChoose2(int i){

        this.choose2 = i;
    }

    /**
     * sets the username of this client's view
     * @param s the name to be set
     */

    void setUser(String s) {

        this.user = s;
    }

    /**
     * sets if has started a multiplayer match
     * @param multiPlayer the boolean to be set
     */

    void setMultiPlayer(boolean multiPlayer){

        this.multiPlayer = multiPlayer;
    }

    /**
     * sets the matchController of the game
     * @param matchController the matchController to be set
     */

    void setMatchController(MatchController matchController){

        this.matchController = matchController;
    }

    /**
     * establishes a RMI connection
     * @param ipAddress the IPaddress to connects with
     */

    void setRMIConnection(String ipAddress){

        try {

            Registry registry = LocateRegistry.getRegistry(ipAddress);
            network = (RemoteGameController) registry.lookup("network");
            UnicastRemoteObject.exportObject((Remote) this,0);
            verifyServerConnection();
        } catch (RemoteException e) {

            startController.printError("THIS IP ADDRESS DOES NOT EXIST");
            startController.setWrongIP(true);
        } catch (NotBoundException e){

            startController.printError("OPS... AN ERROR OCCURRED. PLEASE RESTART THE GAME.");
            startController.setWrongIP(true);
        }
    }

    //END GET SET------------------------------------------------------------------------------------------

    /**
     * prints an error message
     * @param error the error message to be printed
     * @throws RemoteException if the reference could not be accessed
     */

    public void printError(String error) throws RemoteException {

        Platform.runLater(() -> matchController.answerOrMessageError.setText(error));
    }

    /**
     * checks if the username inserted already exists
     * @param s the username inserted
     * @return true if doesn't exist the same username, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */

    boolean verifyUsername(String s) throws RemoteException{

        for(int i=0; i<gameModel.getPlayers(true).size(); i++){

            if(s.equals(gameModel.getPlayers(true).get(i).getName()))
                return false;
        }
        return true;
    }

    /**
     * checks if the actual state is LOBBY
     * @return true if the actual state is LOBBY, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */
    boolean checkLobby() throws RemoteException {

        return gameModel.getState().equals(State.LOBBY);
    }

    /**
     * Only for RMI clients.
     * Every 2 seconds verifies if the Server is up.
     * If not, shuts down the client.
     */
    private void verifyServerConnection(){

        /*Timer t = new Timer();
        t.schedule(new TimerTask() {

            @Override
            public void run() {

                try {

                    network.getMultiPlayerStarted(); //metodo dal RemoteGameController per MultiPlayerMatch
                    verifyServerConnection();
                }catch (RemoteException e){

                    Platform.runLater(()->{

                        matchController.serverDown();
                    });
                }
            }
        },2000);*/
    }

    /**
     * checks if this client is the actual player or not
     * @return true if this client is the actual player, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */
    boolean actualPlayer() throws RemoteException {

        return user.equals(gameModel.getActualPlayer().getName());
    }

    /**
     * creates a new match
     */
    private void showMatch(){
        Platform.runLater(() -> {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/match.fxml"));
                Parent match = loader.load();

                matchController = loader.getController();
                matchController.setViewGUI(this);
                matchController.init();
               // matchController.waitTurn();
                if (actualPlayer()) {

                    playTimer();
                   // matchController.selectMove1View();
                }

                Scene startScene;
                startScene = new Scene(match, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
                mainStage.setScene(startScene);
                mainStage.setMaximized(true);
                mainStage.setFullScreen(true);
                mainStage.show();
                match.requestFocus();
            } catch (IOException e) {

                //do nothing
            }
        });
    }

    /**
     * based on the type of connection, starts a new timer on the server
     * @throws IOException Any exception thrown by the underlying OutputStream.
     */
    void playTimer() throws IOException {

        // network.startTimer(this, null); //al posto di startTimer ci sarà il metodo che farà partire il Timer nel RemoteGameController

    }

    /**
     * allow the player to rejoin the match setting him online again
     * @throws IOException Any exception thrown by the underlying OutputStream.
     */
    void matchRejoined() throws IOException {

        //network.setPlayerOnline(user, true);
        this.setOnline(true);
    }

    /**
     * creates a multiplayer match
     * @throws RemoteException if the reference could not be accessed
     */
    void createMultiPlayerMatch() throws RemoteException {

        if(network.isGameStarted()){

            gameModel = network.getGameModel();
        }
    }

    /**
     * checks if the user is trying to reconnecting or not
     * @return true if the user is reconnecting, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */
    boolean reconnecting() throws RemoteException {

        //TODO
        if(multiPlayer){

            //return (!gameModel.getObservers().contains(null));
            return true; //da eliminare questo return, il true sarà quello alla riga sopra
        }
        else {

            /*for (int i = 0; i < gameModel.getObservers().size(); i++) {

                if ((gameModel.getObservers() == null || gameModel.getObservers().get(i) == null) &&
                        (gameModel.getObserverSocket() == null || gameModel.getObserverSocket().get(i) == null))
                    return true;
            }*/
            return false;
        }
    }


    /*
    boolean verifyUserCrashed(String s) throws RemoteException {

        for(Player x : gameModel.getPlayers(true)){

            if(x.getName().equals(s)){

                if(x.getOnline())
                    return false;
                else{

                    if(multiPlayer){

                        for(RemoteView y : gameModel.getObservers()){

                            if(y!=null && y.getUser().equals(s))
                                return false;
                        }
                        return true;
                    }
                    else {

                        for (int i = 0; i < gameModel.getObservers().size(); i++) {

                            if (gameModel.getObservers() != null && gameModel.getObservers().get(i) != null && gameModel.getObservers().get(i).getUser().equals(s))
                                return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * adds again an RMI observer after he has lost connection
     * @throws IOException Any exception thrown by the underlying OutputStream.
     */
    void reAddPlayer() throws IOException {

        network.reAddObserver(remoteView);
        //network.setPlayerOnline(user, true);
    }

    /**
     * based on the type of connection, it calls an update to the Server
     * @throws IOException if an I/O error occurs while reading stream header
     */
    void notifyNetwork() throws IOException {

        network.update(remoteView);
    }

    /**
     * finds the correspondence between the name of this view and the player in the model
     * @param s the name to be searched
     * @return the player whose name is equal to the string 's'
     * @throws RemoteException if the reference could not be accessed
     */
    public Player searchPlayer(String s) throws RemoteException {

        for(int i=0; i<gameModel.getPlayers(true).size(); i++) {

            Player p = gameModel.getPlayers(true).get(i);
            if(p.getName().equals(s)) {

                return p;
            }
        }
        return gameModel.getPlayers(true).get(0);
    }

    /**
     * gets the username of the player at index i
     * @param i the index of the player
     * @return the username of the player
     * @throws RemoteException if the reference could not be accessed
     */
    String getPlayerUsername(int i) throws RemoteException {

        if(gameModel.getPlayers(true).get(i).getName().equals(user))
            return "next";
        else
            return gameModel.getPlayers(true).get(i).getName();
    }

    /**
     * gets the actual state of the gamemodel
     * @return the actual state of the gamemodel
     * @throws RemoteException if the reference could not be accessed
     */
    State getGameState() throws RemoteException {

        return gameModel.getState();
    }

    /**
     * checks if the player 's' is online or not
     * @param s the username to be searched
     * @return true if 's' is online, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */
    boolean checkOtherPlayerOnline(String s) throws RemoteException{

        Player player = searchPlayer(s);
        return player.getOnline();
    }

    /**
     * checks if the player 's' is the actual player or not
     * @param s the username to be searched
     * @return true if 's' is the actual player, false otherwise
     * @throws RemoteException if the reference could not be accessed
     */
    boolean checkOtherPlayerActual(String s) throws RemoteException{

        return (gameModel.getActualPlayer().getName().equals(s));
    }

    /**
     * gets the final score of the player 's'
     * @param s the username to be searched
     * @return the final score of the player found
     * @throws RemoteException if the reference could not be accessed
     */
    int getPlayerScore(String s) throws RemoteException {

        Player player = searchPlayer(s);
        return player.getScore();
    }
}