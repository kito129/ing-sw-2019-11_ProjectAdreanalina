package it.polimi.view.gui;

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

public class StartController {

    @FXML
    protected AnchorPane container;

    @FXML
    protected Button startButton;

    @FXML
    protected TextField username;

    @FXML
    private Label error, lobby, message;

    private int state = 0;
    private boolean wrongIP = false;
    private ViewGUI viewGUI;


    /**
     * sets if the ip address inserted is wrong
     * @param wrongIP the wrongIP
     */
    public void setWrongIP(boolean wrongIP) {

        this.wrongIP = wrongIP;
    }

    /**
     * sets the viewGUI reference
     * @param viewGUI the viewGUI reference
     */
    void setViewGUI(ViewGUI viewGUI){

        this.viewGUI = viewGUI;
    }

    /**
     * sets the client username
     * @param s username
     */
    private void setUser(String s){

        viewGUI.setUser(s);
        container.getChildren().removeAll(startButton, username, error);
        message.setVisible(true);
        message.setText("YOU HAVE BEEN ADDED TO THIS GAME!\nIT WILL START IN A FEW MOMENTS");
    }

    /**
     * hides all if the game is already started
     */
    private void gameStarted(){

        container.getChildren().removeAll(message, lobby, startButton, username);
        error.setVisible(true);
        error.setText("OPS! THE GAME IS ALREADY STARTED!\nCOME BACK LATER!");
    }

    /**
     * enables to insert the username
     */
    private void insertUsername(){

        startButton.setVisible(false);
        message.setVisible(false);
        username.clear();
        username.setVisible(true);
        username.setPromptText("username");
    }

    /**
     * initializes the first GUI screen
     */
    void init(){

        error.managedProperty().bind(error.visibleProperty());
        username.managedProperty().bind(username.visibleProperty());
        lobby.managedProperty().bind(lobby.visibleProperty());
        startButton.managedProperty().bind(startButton.visibleProperty());
    }

    /**
     * show a text in the lobby
     */
    void printLobby(){

        lobby.setText("GAMERS IN THE LOBBY:\n");
    }

    /**
     * show the player username
     * @param s player username
     */
    void addPrint(String s){

        lobby.setText(lobby.getText() + s + "\n");
    }

    /**
     * show the string error
     * @param s string error
     */
    void printError(String s){

        container.getChildren().removeAll(startButton, message, lobby, username);
        error.setVisible(true);
        error.setText(s);
    }

    /**
     * changes the screen scene
     * @param mainStage where the schene must be loaded
     * @throws IOException
     */
    void changeScene(Stage mainStage) throws IOException{

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


}
