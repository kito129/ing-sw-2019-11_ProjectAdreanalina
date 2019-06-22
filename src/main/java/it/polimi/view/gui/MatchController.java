package it.polimi.view.gui;

import it.polimi.model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MatchController {

    @FXML
    private SplitPane container;

    @FXML
    private AnchorPane APtop, APbottom;

    @FXML
    private GridPane gridActualplayerPlayerboard, InternalgridActualplayerPlayerboar, gridAmmo;

    @FXML
    private GridPane esternalGridMap, internalGridMap;

    @FXML
    private GridPane weaponB1, weaponB2, weaponY1, weaponY2, weaponY3, weaponR1, weaponR2, gridPowerupDeck, gridWeaponDeck;

    @FXML
    private GridPane gridSkull1, gridSkull2, gridSkull3;

    @FXML
    private GridPane square1, square2, square3, square4, square5, square6, square7, square8, square9, square10, square11, square12;

    @FXML
    protected Label answerOrMessageError;

    @FXML
    private ImageView weapon1, weapon2, weapon3, powerUp1, powerUp2, powerUp3, weaponSelected;

    static final String PNG = ".png";
    static final String JPG = ".jpg";
    static final String AMMOPATH = "images/ammo/";
    static final String AMMOCARDSPATH = "images/ammoCards/";
    static final String WEAPONPATH = "images/weapon/";
    static final String POWERUPPATH = "images/powerup/";
    static final String MAPSPATH = "images/maps/";
    static final String PLAYERBOARDPATH = "images/playerboards/";
    static final String POINTSPATH = "images/points/";
    static final String TEARSPATH = "images/tears/";                //damage and mark
    static final String SKULLPATH = "images/RedSkull";
    static final String PLAYERSPATH = "images/players/";

    private ViewGUI viewGUI;
    String path = "";

    /**
     * sets the viewGUI reference
     * @param viewGUI the viewGUI reference
     */
    void setViewGUI(ViewGUI viewGUI){

        this.viewGUI = viewGUI;
    }

    /**
     * initializes the select window scene
     * @throws RemoteException if the reference could not be accessed
     */
    void init() throws RemoteException {

        APtop.managedProperty().bind(APtop.visibleProperty());
        APbottom.managedProperty().bind(APbottom.visibleProperty());
        gridActualplayerPlayerboard.managedProperty().bind(gridActualplayerPlayerboard.visibleProperty());
        InternalgridActualplayerPlayerboar.managedProperty().bind(InternalgridActualplayerPlayerboar.visibleProperty());
        gridAmmo.managedProperty().bind(gridAmmo.visibleProperty());
        esternalGridMap.managedProperty().bind(esternalGridMap.visibleProperty());
        internalGridMap.managedProperty().bind(internalGridMap.visibleProperty());
        weaponB1.managedProperty().bind(weaponB1.visibleProperty());
        weaponB2.managedProperty().bind(weaponB2.visibleProperty());
        weaponY1.managedProperty().bind(weaponY1.visibleProperty());
        weaponY2.managedProperty().bind(weaponY2.visibleProperty());
        weaponY3.managedProperty().bind(weaponY3.visibleProperty());
        weaponR1.managedProperty().bind(weaponR1.visibleProperty());
        weaponR2.managedProperty().bind(weaponR2.visibleProperty());
        gridPowerupDeck.managedProperty().bind(gridPowerupDeck.visibleProperty());
        gridWeaponDeck.managedProperty().bind(gridWeaponDeck.visibleProperty());
        gridSkull1.managedProperty().bind(gridSkull1.visibleProperty());
        gridSkull2.managedProperty().bind(gridSkull2.visibleProperty());
        gridSkull3.managedProperty().bind(gridSkull3.visibleProperty());
        answerOrMessageError.managedProperty().bind(answerOrMessageError.visibleProperty());

    }

    // YOUR PLAYERBOARD ---------------------------------------------------------------------------------------------

    /**
     * initializes the player's playerboard
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void addYourPlayerboard(Player player){

        for(Node YPimage : gridActualplayerPlayerboard.getChildren()){

            path = PLAYERBOARDPATH + player.getColor().toString() + PNG;
            loadImage(path,527,138, YPimage,0);
            gridActualplayerPlayerboard.add(YPimage, 0, 0); //node, column, row
        }
    }

    /**
     * return the right string corresponding to the player's color
     * @param damageAndMark arrayList of damage/mark
     * @throws RemoteException if the reference could not be accessed
     */
    void colorDamageMark(ArrayList<EnumColorPlayer> damageAndMark) throws RemoteException{

        String colorPlayer = "";

        for (EnumColorPlayer e : damageAndMark) {

            switch (e) {

                case BLU:
                    colorPlayer = "BLU";
                    break;
                case GREEN:
                    colorPlayer = "GREEN";
                    break;
                case GREY:
                    colorPlayer = "GREY";
                    break;
                case PINK:
                    colorPlayer = "PINK";
                    break;
                case YELLOW:
                    colorPlayer = "YELLOW";
                    break;
            }

            path = TEARSPATH + colorPlayer + PNG;
        }
    }

    /**
     * update the mark in player's playerboard
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshMark(Player player) throws RemoteException{

        int markIndex;

        for (Node markImage : InternalgridActualplayerPlayerboar.getChildren()){

            markIndex = GridPane.getColumnIndex(markImage);
            colorDamageMark(player.getPlayerBoard().getMarks());
            loadImage(path, 25, 47, markImage, 0);
            InternalgridActualplayerPlayerboar.add(markImage, 0, markIndex);
        }
    }

    /**
     * update the damage in player's playerboard
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshDamage(Player player) throws RemoteException{

        int damageIndex;

        for (Node damageImage : InternalgridActualplayerPlayerboar.getChildren()){

            damageIndex = GridPane.getColumnIndex(damageImage);
            colorDamageMark(player.getPlayerBoard().getDamages());
            loadImage(path, 25, 47, damageImage, 0);
            InternalgridActualplayerPlayerboar.add(damageImage, 1, damageIndex);
        }
    }

    /**
     * update the value in player's playerboard
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshValue(Player player) throws RemoteException{

        ImageView valueImage = new ImageView();
        path = SKULLPATH + PNG;

        if(player.getPlayerBoard().getBoardValue()==8){

            valueImage.setImage(null);
        }

        if (player.getPlayerBoard().getBoardValue()==6){

            loadImage(path, 25,47, valueImage, 0);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 4);
        }

        if (player.getPlayerBoard().getBoardValue()==4){

            loadImage(path, 25,47, valueImage, 0);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 5);
        }

        if (player.getPlayerBoard().getBoardValue()==2){

            loadImage(path, 25,47, valueImage, 0);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 6);
        }

        //TODO completare con && numero di morti == 5 e == 6
        if (player.getPlayerBoard().getBoardValue()==1){

            loadImage(path, 25,47, valueImage, 0);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 7);
        }
    }

    // END YOUR PLAYERBOARD -----------------------------------------------------------------------------------------

    // YOUR WEAPON --------------------------------------------------------------------------------------------------

    /**
     * update the player's weapon
     * @param weaponCards player's weaponList
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshYourWeapon(ArrayList<WeaponCard> weaponCards) throws RemoteException{

        String nameWeapon;


        for (int i = 0; i < weaponCards.size(); i++) {

            WeaponCard w = weaponCards.get(i);

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;
            Image weapon = new Image(path);

            if(i==0){

                weapon1.setImage(weapon);
                if(!w.isCharge()){

                    weapon1.setOpacity(0.7);
                }else {

                    weapon1.setOpacity(1.0);
                }
            }

            else if(i==1){

                weapon2.setImage(weapon);
                if(!w.isCharge()){

                    weapon2.setOpacity(0.7);
                }else {

                    weapon2.setOpacity(1.0);
                }
            }

            else if(i==2){

                weapon3.setImage(weapon);
                if(!w.isCharge()){

                    weapon3.setOpacity(0.7);
                }else {

                    weapon3.setOpacity(1.0);
                }
            }
        }
    }

    // END YOUR WEAPON ----------------------------------------------------------------------------------------------

    // YOUR POWER UP ------------------------------------------------------------------------------------------------

    /**
     * update the player's powerup
     * @param powerUpCards player's powerupList
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshYourPowerUp(ArrayList<PowerUpCard> powerUpCards) throws RemoteException{

        String namePowerUp;
        String color;

        for (int i = 0; i < powerUpCards.size(); i++) {

            PowerUpCard p = powerUpCards.get(i);

            namePowerUp = p.getNameCard();
            color = p.getColorPowerUpCard().toString();
            path = WEAPONPATH + namePowerUp + "_" + color + PNG;

            if(i==0){

                loadImage(path,68,114, powerUp1,0);
            }

            if(i==1){

                loadImage(path,68,114, powerUp2,0);
            }

            if(i==2){

                loadImage(path,68,114, powerUp3,0);
            }
        }
    }

    // END YOUR POWER UP---------------------------------------------------------------------------------------------

    // AMMO ---------------------------------------------------------------------------------------------------------

    /**
     * refresh blue ammo
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshAmmoB(Player player) throws RemoteException {

        int ammoIndex;

        for(Node ammoImage : gridAmmo.getChildren()){

            ammoIndex = GridPane.getColumnIndex(ammoImage);
            if(ammoIndex < player.getPlayerBoard().getAmmoB().size()) {

                path = AMMOPATH + "ammoB" + PNG;
                loadImage(path, 24, 26, ammoImage, 0);
                gridAmmo.add(ammoImage, ammoIndex, 0); //node, column, row
            }else{

                ((ImageView) ammoImage).setImage(null);
            }
        }
    }

    /**
     * refresh red ammo
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshAmmoR(Player player) throws RemoteException {

        int ammoIndex;

        for(Node ammoImage : gridAmmo.getChildren()){

            ammoIndex = GridPane.getColumnIndex(ammoImage);
            if(ammoIndex < player.getPlayerBoard().getAmmoR().size()) {

                path = AMMOPATH + "ammoR" + PNG;
                loadImage(path, 24, 26, ammoImage, 0);
                gridAmmo.add(ammoImage, ammoIndex, 1); //node, column, row
            }else{

                ((ImageView) ammoImage).setImage(null);
            }
        }
    }

    /**
     * refresh yellow ammo
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshAmmoY(Player player) throws RemoteException {

        int ammoIndex;

        for(Node ammoImage : gridAmmo.getChildren()){

            ammoIndex = GridPane.getColumnIndex(ammoImage);
            if(ammoIndex < player.getPlayerBoard().getAmmoY().size()) {

                path = AMMOPATH + "ammoY" + PNG;
                loadImage(path, 24, 26, ammoImage, 0);
                gridAmmo.add(ammoImage, ammoIndex, 2); //node, column, row
            }else{

                ((ImageView) ammoImage).setImage(null);
            }
        }
    }

    // END AMMO -----------------------------------------------------------------------------------------------------

    // MAP ----------------------------------------------------------------------------------------------------------

    void addMapImage(GameModel gameModel) throws RemoteException{

        //TODO serve il nome della mappa
    }

    void refreshKillshotTrackPoint(GameModel gameModel, ArrayList<KillShotTrackPoint> killShotTrackPoints) throws RemoteException{

        //TODO
    }

    /**
     * update the weapons on generation square blu
     * @param generationSquare a generationSquare
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshWeaponGenerationBlue(GenerationSquare generationSquare) throws RemoteException{

        String nameWeapon;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for (int i = 0; i < weaponList.size(); i++) {

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;

            if(i<2){

                loadImage(path,60,102, weaponImage, 0);
                weaponB1.add(weaponImage,i+1,0);
            }

            if(i==2){

                loadImage(path, 60,102, weaponImage,0);
                weaponB2.add(weaponImage,1,0);
            }
        }
    }

    /**
     * update the weapons on generation square yellow
     * @param generationSquare a generationSquare
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshWeaponGenerationYellow(GenerationSquare generationSquare) throws RemoteException{

        String nameWeapon;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for(int i = 0; i < weaponList.size(); i++){

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;

            if(i==0){

                loadImage(path, 71,216, weaponImage,0);
                weaponY1.add(weaponImage,1,1);
            }

            if(i==1){

                loadImage(path,71,210, weaponImage, 0);
                weaponY2.add(weaponImage,1,1);
            }

            if(i==2){

                loadImage(path, 76,215, weaponImage,0);
                weaponY3.add(weaponImage,1,0);
            }
        }
    }

    /**
     * update the weapons on generation square red
     * @param generationSquare a generationSquare
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshWeaponGenerationRed(GenerationSquare generationSquare) throws RemoteException{

        String nameWeapon;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for(int i = 0; i < weaponList.size(); i++) {

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;

            if(i==0){

                loadImage(path, 72,106, weaponImage,0);
                weaponR1.add(weaponImage,1,0);
            }

            if(i==1){

                loadImage(path, 66,112, weaponImage,0);
                weaponR1.add(weaponImage,1,2);
            }

            if(i==2){

                loadImage(path, 67,106, weaponImage,0);
                weaponR2.add(weaponImage,1,0);
            }
        }
    }

    // SINGLE SQUARE ------------------------------------------------------------------------------------------------

    /**
     * return the right string corresponding to player's name
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    String namePlayer(Player player){

        String name;
        name = player.getName();
        return name;
    }

    /**
     * return the right GridPane corresponding to the GridPane (a square) in map where is the player on
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    GridPane knowSquare(Player player) throws RemoteException{

        GridPane square = new GridPane();

        if(player.getRow()==0 && player.getColumn()==0){

            square = square1;
        }

        else if(player.getRow()==0 && player.getColumn()==1){

            square = square2;
        }

        else if(player.getRow()==0 && player.getColumn()==2){

            square = square3;
        }

        else if(player.getRow()==0 && player.getColumn()==3){

            square = square4;
        }

        else if(player.getRow()==1 && player.getColumn()==0){

            square = square5;
        }

        else if(player.getRow()==1 && player.getColumn()==1){

            square = square6;
        }

        else if(player.getRow()==1 && player.getColumn()==2){

            square = square7;
        }

        else if(player.getRow()==1 && player.getColumn()==3){

            square = square8;
        }

        else if(player.getRow()==2 && player.getColumn()==0){

            square = square9;
        }

        else if(player.getRow()==2 && player.getColumn()==1){

            square = square10;
        }

        else if(player.getRow()==2 && player.getColumn()==2){

            square = square11;
        }

        else if(player.getRow()==2 && player.getColumn()==3){

            square = square12;
        }

        return square;
    }

    /**
     * add player in right position on square in map
     * @param path the path that need to upload the image in an ImageView
     * @param s a square of the map
     * @param square the right GridPane in which the image will be loaded
     * @throws RemoteException if the reference could not be accessed
     */
    void addPlayerInRightPosition(String path, Square s, GridPane square) throws RemoteException{

        ImageView playerImage1 = new ImageView();
        ImageView playerImage2 = new ImageView();
        ImageView playerImage3 = new ImageView();
        ImageView playerImage4 = new ImageView();
        ImageView playerImage5 = new ImageView();

        if (s.getPlayers().size()==1){

            loadImage(path,36,55, playerImage1, 0);
            square.add(playerImage1, 1, 0);
        }

        if (s.getPlayers().size()==2){

            loadImage(path,36,55, playerImage1, 0);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2, 0);
            square.add(playerImage2, 2, 0);
        }

        if (s.getPlayers().size()==3){

            loadImage(path,36,55, playerImage1, 0);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2, 0);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3, 0);
            square.add(playerImage3, 0, 1);
        }

        if (s.getPlayers().size()==4){

            loadImage(path,36,55, playerImage1, 0);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2, 0);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3, 0);
            square.add(playerImage3, 0, 1);

            path = s.getPlayers().get(3).getName();
            loadImage(path,36,55, playerImage4, 0);
            square.add(playerImage4, 1, 1);
        }

        if (s.getPlayers().size()==5){

            loadImage(path,36,55, playerImage1, 0);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2, 0);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3, 0);
            square.add(playerImage3, 0, 1);

            path = s.getPlayers().get(3).getName();
            loadImage(path,36,55, playerImage4, 0);
            square.add(playerImage4, 1, 1);

            path = s.getPlayers().get(4).getName();
            loadImage(path,36,55, playerImage5, 0);
            square.add(playerImage5, 2, 1);
        }
    }

    /**
     * refresh player on the map
     * @param squares all squares of the map
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshPlayersOnSquare(ArrayList<Square> squares) throws RemoteException{

        String nameSinglePlayer;
        GridPane square;

        for (Square s : squares){

            for (int i = 0; i < s.getPlayers().size(); i++){

                square = knowSquare(s.getPlayers().get(i));
                nameSinglePlayer = namePlayer(s.getPlayers().get(i));
                path = PLAYERSPATH + nameSinglePlayer + PNG;
                addPlayerInRightPosition(path, s, square);
            }
        }
    }

    // END SINGLE SQUARE --------------------------------------------------------------------------------------------

    void addBackAmmoCardDeck() throws RemoteException{

        path = "images/backAmmo.png";
        ImageView backAmmoCardImage = new ImageView();
        loadImage(path, 70,72, backAmmoCardImage,0);
        weaponR2.add(backAmmoCardImage, 1,2);
    }

    void addBackPowerUpDeck() throws RemoteException{

        path = "images/back_powerup.png";
        ImageView backPowerUpImage = new ImageView();
        loadImage(path, 49,88, backPowerUpImage,0);
        gridPowerupDeck.add(backPowerUpImage, 1,0);
    }

    void addBackWeaponDeck() throws RemoteException{

        path = "images/back_weapon.png";
        ImageView backWeaponImage = new ImageView();
        loadImage(path, 82,129, backWeaponImage,0);
        gridWeaponDeck.add(backWeaponImage, 0,1);
    }

    // END MAP ------------------------------------------------------------------------------------------------------

    /**
     * loads the ImageView or the AnchorPane's background images
     * @param path the path of the image that has to be loaded
     * @param width the width of the image that has to be loaded
     * @param height the height of the image that has to be loaded
     * @param element the Node where the image will be loaded
     * @param type the type of Node passed
     */
    void loadImage(String path, int width, int height, Node element, int type){//type 0: imageView, type 1: anchorpane

        Image image = new Image(getClass().getResourceAsStream(path), width, height, false, true);

        if(type == 0){

            ((javafx.scene.image.ImageView)element).setImage(image);
        }else if(type == 1) {

            BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            ((AnchorPane) element).setBackground(new Background(bg));
        }
    }

    /**
     * removes all the center graphics and show a message
     */
    void serverDown(){
        //allWindows.setVisible(false);
        //rejoin.setVisible(false);
        //text.setText("SEEMS LIKE THE SERVER HAS BEEN SHUT DOWN");
    }

    /**
     * hides all the screen graphics and shows only the rejoin button
     */
    void setInactive(){
        //TODO
        /*buttons.setVisible(false);
        tokens.setVisible(false);
        windowArea.setVisible(false);
        region2.setVisible(false);
        draftArea.setVisible(false);
        region1.setVisible(false);
        roundtrackArea.setVisible(false);
        input.setVisible(false);
        errorMessage.setVisible(false);
        left.setVisible(false);
        right.setVisible(false);

        message.setText("YOU ARE NOW INACTIVE!\nTO JOIN AGAIN THE MATCH, PRESS THE BUTTON");
        rejoinButton.setVisible(true);*/
    }
}
