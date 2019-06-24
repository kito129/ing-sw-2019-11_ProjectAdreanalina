package it.polimi.view.gui;

import it.polimi.view.RemoteView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;

public class StartController {

    @FXML
    private AnchorPane container;

    @FXML
    private Button startButton;

    @FXML
    private TextField username;

    @FXML
    private Label error, lobby, message;

    private ViewGUI viewGUI;
    private int state = 0;
    private boolean wrongIP = false;

    /**
     * initializes the first GUI screen
     */
    void init() {

        error.managedProperty().bind(error.visibleProperty());
        message.managedProperty().bind(message.visibleProperty());
        lobby.managedProperty().bind(lobby.visibleProperty());
        username.managedProperty().bind(username.visibleProperty());
    }

    /**
     * refreshes the window based on the actual state
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void startButtonClicked() throws IOException {

        if(state == 0)
            matchSelected();
        else if(state == 1)
            connectionSelected();
        else if(state == 2)
            ipInsertion();
        else if(state == 3)
            multiPlayerSetup();
        else if(state == 4)
            usernameInserted();
    }

    /**
     * refreshes the window based on the actual state
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void inputEnter() throws IOException {

        if(state == 2)
            ipInsertion();
        else if(state == 4)
            usernameInserted();
    }

    /**
     * sets if the ip address inserted is wrong
     *
     * @param wrongIP the wrongIP
     */
    public void setWrongIP(boolean wrongIP) {

        this.wrongIP = wrongIP;
    }

    /**
     * sets the viewGUI reference
     *
     * @param viewGUI the viewGUI reference
     */
    void setViewGUI(ViewGUI viewGUI) {

        this.viewGUI = viewGUI;
    }

    /**
     * sets the client username
     *
     * @param s username
     */
    private void setUser(String s) {

        viewGUI.setUser(s);
        container.getChildren().removeAll(startButton, username, error);
        message.setVisible(true);
        message.setText("YOU HAVE BEEN ADDED TO THIS GAME!\nIT WILL START IN A FEW MOMENTS");
    }

    /**
     * hides all if the game is already started
     */
    private void gameStarted() {

        container.getChildren().removeAll(message, lobby, startButton, username);
        error.setVisible(true);
        error.setText("OPS! THE GAME IS ALREADY STARTED!\nCOME BACK LATER!");
    }

    /**
     * verifies if the client can continue joining the match
     * @return true if the client can join the match, otherwise false
     * @throws RemoteException if the reference could not be accessed
     */
    private boolean checkState() throws RemoteException {

        if ((viewGUI.getMultiPlayer() && viewGUI.getNetwork().isGameStarted())) {

            gameStarted();
            return false;
        }
        if (!viewGUI.checkLobby()) {

            if (!viewGUI.reconnecting()) {

                gameStarted();
                return false;
            }
        }
        return true;
    }

    /**
     * changes the screen scene
     *
     * @param mainStage where the schene must be loaded
     * @throws IOException
     */
    void changeScene(Stage mainStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/match.fxml"));
        Parent match = loader.load();

        MatchController matchController = loader.getController();
        matchController.setViewGUI(viewGUI);
        viewGUI.setMatchController(matchController);
        matchController.init();

        //TODO da completare (far partire i timer)

        Scene startScene;
        startScene = new Scene(match, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        mainStage.setScene(startScene);
        mainStage.setMaximized(true);
        mainStage.setFullScreen(true);
        mainStage.show();
        match.requestFocus();
    }

    /**
     * show a text in the lobby
     */
    void printLobby() {

        lobby.setText("GAMERS IN THE LOBBY:\n");
    }

    /**
     * show the player username
     * @param s player username
     */
    void addPrint(String s) {

        lobby.setText(lobby.getText() + s + "\n");
    }

    /**
     * show the string error
     * @param s string error
     */
    void printError(String s) {

        container.getChildren().removeAll(startButton, message, lobby, username);
        error.setVisible(true);
        error.setText(s);
    }

    /**
     * enables to insert the server ip addres
     */
    private void setIpIndex() {

        username.setVisible(true);
        username.setPromptText("IP address");
        message.setText("INSERT THE IP ADDRESS");
        startButton.setText("OK");
    }

    /**
     * enables to insert the username
     */
    private void insertUsername() {

        username.clear();
        username.setVisible(true);
        username.setPromptText("username");
        message.setText("INSERT YOUR USERNAME");
        startButton.setText("PLAY");
    }

    /**
     * verifies the type of match selected
     */
    private void matchSelected() {

        viewGUI.setMultiPlayer(true);
        state = 1;
    }

    /**
     * set state = 2
     */
    private void connectionSelected() {

        state = 2;
        setIpIndex();
    }

    /**
     * check if the ip is correct and create/rejoin a match
     * @throws RemoteException if the reference could not be accessed
     */
    private void ipInsertion() throws RemoteException {

        viewGUI.setRMIConnection(username.getText());

        if (!wrongIP) {

            if (viewGUI.getMultiPlayer()) {

                if (viewGUI.getNetwork().isGameStarted()){

                    gameStarted();
                }
                state = 3;
            } else {

                if (viewGUI.getNetwork().isGameStarted())
                        gameStarted();
                    else {

                    viewGUI.createMultiPlayerMatch();
                    state = 4;
                    try {
                        if (checkState()) {

                            insertUsername();
                        }
                    } catch (RemoteException e) {

                        //do nothing
                    }
                }

            }
        }
    }

    /**
     * @throws RemoteException if the reference could not be accessed
     */
    private void multiPlayerSetup() throws RemoteException {

        viewGUI.createMultiPlayerMatch();

        if (!wrongIP) {

            state = 4;
            try {

                if (checkState())
                    insertUsername();
            } catch (RemoteException e) {

                //do nothing
            }
        }
    }

    /**
     * verifies if the username is correct.
     * verifies if the username not already exists.
     * verifies if the user is reconnecting.
     * If it's all verified start/rejoin the match, otherwise stop the client
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    private void usernameInserted() throws IOException {

        error.setVisible(false);
        String user = username.getText();

        if(!user.isEmpty()) {

            if(!viewGUI.checkLobby()) {

                if (viewGUI.reconnecting()) {

                    if (viewGUI.verifyUserCrashed(user)) {

                        setUser(user);
                        viewGUI.reAddPlayer();
                        container.getChildren().removeAll(startButton, lobby, username);
                        message.setVisible(true);
                        message.setText("JOINING AGAIN THE MATCH");
                        if (viewGUI.getMultiPlayer()) {

                            //viewGUI.getNetwork().startTimer(viewGUI);
                            viewGUI.notifyNetwork();
                        }
                    } else {

                        error.setVisible(true);
                        error.setText("INSERT A VALID NAME");
                    }
                }
            }
            else {

                if (viewGUI.verifyUsername(user)) {

                    setUser(user);
                    viewGUI.getNetwork().addObserver((RemoteView) viewGUI);
                    viewGUI.notifyNetwork();

                } else {
                    error.setVisible(true);
                    error.setText("THIS USERNAME ALREADY EXIST");
                    username.clear();
                }
            }
        }
        else {
            error.setVisible(true);
            error.setText("PLEASE INSERT A VALID USERNAME");
            username.clear();
        }
    }
}