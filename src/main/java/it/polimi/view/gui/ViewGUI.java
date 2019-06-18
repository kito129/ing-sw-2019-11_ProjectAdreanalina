package it.polimi.view.gui;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.GameModel;
import it.polimi.model.Player;
import it.polimi.model.State;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
     * sets if the client is online or not
     */
    public synchronized void setOnline(boolean online){

        this.online = online;
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
     * sets the username of this client's view
     * @param s the name to be set
     */
    void setUser(String s) {

        this.user = s;
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
     * updates each view in the game
     * @param gameModel the gamemodel of the match
     */
    public void update(GameModel gameModel) {

        this.gameModel = gameModel;
    }

    private void run() throws IOException{

        returnOnline = false;
        state = gameModel.getState();

        switch (state){
            case LOBBY:
                viewLobby();
                break;
        }
    }

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
     * sets the matchController of the game
     * @param matchController the matchController to be set
     */
    void setMatchController(MatchController matchController){

        this.matchController = matchController;
    }

    /**
     * gets if this client wants to restart the game
     * @return true if the client wants to restart the game, false otherwise
     */
    public boolean getRestart() {

        return restart;
    }

    /**
     * sets if the client wants to restart the game
     * @param restart the boolean to be set
     */
    public void setRestart(boolean restart) {

        this.restart = restart;
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

        network.setPlayerOnline(user, true);
        this.setOnline(true);
    }
}