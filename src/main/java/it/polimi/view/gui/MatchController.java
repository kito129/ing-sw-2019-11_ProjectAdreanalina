package it.polimi.view.gui;

import it.polimi.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.lang.String;

public class MatchController {

    @FXML
    private SplitPane container;

    @FXML
    private Button buttonRunAround, buttonGrubStuff, buttonShoot, buttonRecharge, buttonEndTurn, buttonRejoin, effect1, effect2, effect3;

    @FXML
    private AnchorPane APtop, APbottom;

    @FXML
    private GridPane gridActualplayerPlayerboard, InternalgridActualplayerPlayerboar, gridPlayerboardPlayer2, gridPlayerboardPlayer3, gridPlayerboardPlayer4, gridPlayerboardPlayer5;

    @FXML
    private GridPane gridAmmo;

    @FXML
    private GridPane gridOthersPlayerboard;

    @FXML
    private GridPane esternalGridMap, internalGridMap;

    @FXML
    private GridPane weaponB1, weaponB2, weaponY1, weaponY2, weaponY3, weaponR1, weaponR2, gridPowerupDeck, gridWeaponDeck;

    @FXML
    private GridPane gridSkull1, gridSkull2, gridSkull3, pointGrid;

    @FXML
    private GridPane square1, square2, square3, square4, square5, square6, square7, square8, square9, square10, square11, square12;

    @FXML
    protected Label answerOrMessageError, weaponInfo;

    @FXML
    private ImageView weapon1, weapon2, weapon3, powerUp1, powerUp2, powerUp3, weaponSelected;

    private boolean rejoined;

    static final String PNG = ".png";
    static final String JPG = ".jpg";
    static final String AMMOPATH = "images/ammo/";
    static final String AMMOCARDSPATH = "images/ammoCards/";
    static final String WEAPONPATH = "images/weapon/";
    static final String POWERUPPATH = "images/powerup/";
    static final String MAPSPATH = "images/maps/";
    static final String PLAYERBOARDPATH = "images/playerboards/";
    static final String POINTSPATH = "images/points/";
    static final String TEARSPATH = "images/tears/";                //for damages and marks
    static final String SKULLPATH = "images/RedSkull";
    static final String PLAYERSPATH = "images/players/";

    static final String targetingScope = "You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color. Choose 1 of those targets and give it an extra point of damage.   NOTE: You cannot use this to do 1 damage to a target that is receiving only marks.";
    static final String newton = "You may play this card on your turn before or after any action. Choose any other player's figure and move it 1 or 2 squares in one direction. (You can't use this to move a figure after it respawns at the end of your turn. That would be too late.)";
    static final String tagbackGrenade = "You may play this card when you receive damage from a player you can see. Give that player 1 mark.";
    static final String teleporter = "You may play this card on your turn before or after any action. Pick up your figure and set it down on any square of the board. (You can't use this after you see where someone respawns at the end of your turn. By then it is too late.)";

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
        weaponInfo.managedProperty().bind(weaponInfo.visibleProperty());
        buttonRunAround.managedProperty().bind(buttonRunAround.visibleProperty());
        buttonGrubStuff.managedProperty().bind(buttonGrubStuff.visibleProperty());
        buttonShoot.managedProperty().bind(buttonShoot.visibleProperty());
        buttonRecharge.managedProperty().bind(buttonRecharge.visibleProperty());
        buttonEndTurn.managedProperty().bind(buttonEndTurn.visibleProperty());
        effect1.managedProperty().bind(effect1.visibleProperty());
        effect2.managedProperty().bind(effect2.visibleProperty());
        effect3.managedProperty().bind(effect3.visibleProperty());

        effect1.setVisible(false);
        effect2.setVisible(false);
        effect3.setVisible(false);
        buttonRecharge.setVisible(false);
        buttonEndTurn.setVisible(false);
        buttonRejoin.setVisible(false);
    }

    // YOUR PLAYERBOARD ---------------------------------------------------------------------------------------------

    /**
     * return the right string corresponding to the player's color
     * @param damageAndMark a single damage/mark
     * @throws RemoteException if the reference could not be accessed
     */
    String colorDamageMark(EnumColorPlayer damageAndMark) throws RemoteException{

        String colorPlayer = "";

        switch (damageAndMark) {

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
        return colorPlayer;
    }

    /**
     * initializes the player's playerboard
     * @param player the player
     * @throws RemoteException if the reference could not be accessed
     */
    void addYourPlayerboardImage(Player player){

        ImageView YourPlayerboardImage = new ImageView();
        path = PLAYERBOARDPATH + player.getColor().toString() + PNG;
        loadImage(path,527,138, YourPlayerboardImage);
        gridActualplayerPlayerboard.add(YourPlayerboardImage, 0, 0); //node, column, row
    }

    /**
     * update the marks in player's playerboard
     * @param colorMark list of colors of the marks
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshMark(ArrayList<EnumColorPlayer> colorMark) throws RemoteException{

        String colorM;
        ImageView markImage = new ImageView();

        for (int i = 0; i < colorMark.size(); i++) {

            EnumColorPlayer color = colorMark.get(i);

            colorM = colorDamageMark(color);
            path = TEARSPATH + colorM + PNG;
            loadImage(path, 25, 47, markImage);
            InternalgridActualplayerPlayerboar.add(markImage, i, 0); //node element, col, row
        }
    }

    /**
     * update the damages in player's playerboard
     * @param colorDamage list of colors of the damages
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshDamage(ArrayList<EnumColorPlayer> colorDamage) throws RemoteException{

        String colorD;
        ImageView markDamage = new ImageView();

        for (int i = 0; i < colorDamage.size(); i++) {

            EnumColorPlayer color = colorDamage.get(i);
            colorD = colorDamageMark(color);
            path = TEARSPATH + colorD + PNG;
            loadImage(path,25,47, markDamage);
            InternalgridActualplayerPlayerboar.add(markDamage, i,1); //node element, col, row
        }
    }

    /**
     * update the value in player's playerboard
     * @param player the player
     * @param cont counter need to know if player has died 4 (cont=0) or 5 (cont=1) times and consequently set the right image (skull)
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshValue(Player player, int cont) throws RemoteException{

        ImageView valueImage = new ImageView();
        path = SKULLPATH + PNG;

        if(player.getPlayerBoard().getBoardValue()==8){

            valueImage.setImage(null);
        }

        if (player.getPlayerBoard().getBoardValue()==6){

            loadImage(path, 25,47, valueImage);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 4);
        }

        if (player.getPlayerBoard().getBoardValue()==4){

            loadImage(path, 25,47, valueImage);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 5);
        }

        if (player.getPlayerBoard().getBoardValue()==2){

            loadImage(path, 25,47, valueImage);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 6);
        }

        if (player.getPlayerBoard().getBoardValue()==1 && cont==0){

            loadImage(path, 25,47, valueImage);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 7);
        }

        if (player.getPlayerBoard().getBoardValue()==1 && cont==1){

            loadImage(path, 25,47, valueImage);
            InternalgridActualplayerPlayerboar.add(valueImage, 2, 8);
        }
    }

    // END YOUR PLAYERBOARD -----------------------------------------------------------------------------------------

    // OTHERS PLAYERBOARD -------------------------------------------------------------------------------------------

    /**
     * initializes others players' playerboard
     * @param gameModel the reference to the gamemodel
     * @throws RemoteException if the reference could not be accessed
     */
    void addOthersPlayerboardImage (GameModel gameModel){

        ImageView OthersPlayerboardImage = new ImageView();

        ArrayList<Player> players = gameModel.getPlayers(false);

        for (int i = 0; i < players.size(); i++) {

            Player p = players.get(i);

            if(i==0){

                path = PLAYERBOARDPATH + p.getColor().toString() + PNG;
                loadImage(path, 380, 100, OthersPlayerboardImage);
                gridOthersPlayerboard.add(OthersPlayerboardImage,0,0);
            }
            else if(i==1){

                path = PLAYERBOARDPATH + p.getColor().toString() + PNG;
                loadImage(path, 380, 100, OthersPlayerboardImage);
                gridOthersPlayerboard.add(OthersPlayerboardImage,1,0);
            }
            else if(i==2){

                path = PLAYERBOARDPATH + p.getColor().toString() + PNG;
                loadImage(path, 380, 100, OthersPlayerboardImage);
                gridOthersPlayerboard.add(OthersPlayerboardImage,0,1);
            }
            else if(i==3){

                path = PLAYERBOARDPATH + p.getColor().toString() + PNG;
                loadImage(path, 380, 100, OthersPlayerboardImage);
                gridOthersPlayerboard.add(OthersPlayerboardImage,1,1);
            }
        }
    }

    /**
     * update the marks in players' playerboard
     * @param colorMark list of colors of the marks
     * @param othersPlayer players in game except main player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshMarkOthersPlayer(ArrayList<EnumColorPlayer> colorMark, ArrayList<Player> othersPlayer) throws RemoteException{

        GridPane otherPlayerboard = new GridPane();
        String colorMarks;
        ImageView markImage = new ImageView();

        for (int i = 0; i < othersPlayer.size(); i++) {

            if (i==0){

                otherPlayerboard = gridPlayerboardPlayer2;
            }

            if (i==1){

                otherPlayerboard = gridPlayerboardPlayer3;
            }

            if (i==2){

                otherPlayerboard = gridPlayerboardPlayer3;
            }

            if (i==3){

                otherPlayerboard = gridPlayerboardPlayer4;
            }

            for (int j = 0; j < colorMark.size(); j++) {

                EnumColorPlayer color = colorMark.get(j);

                colorMarks = colorDamageMark(color);
                path = TEARSPATH + colorMarks + PNG;
                loadImage(path, 21, 34, markImage);
                otherPlayerboard.add(markImage, j, 0); //node element, col, row
            }
        }
    }

    /**
     * update the damages in players' playerboard
     * @param colorDamage list of colors of the marks
     * @param othersPlayer players in game except main player
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshDamageOthersPlayer(ArrayList<EnumColorPlayer> colorDamage, ArrayList<Player> othersPlayer) throws RemoteException{

        GridPane otherPlayerboard = new GridPane();
        String colorDamages;
        ImageView damageImage = new ImageView();

        for (int i = 0; i < othersPlayer.size(); i++) {

            if (i==0){

                otherPlayerboard = gridPlayerboardPlayer2;
            }

            if (i==1){

                otherPlayerboard = gridPlayerboardPlayer3;
            }

            if (i==2){

                otherPlayerboard = gridPlayerboardPlayer3;
            }

            if (i==3){

                otherPlayerboard = gridPlayerboardPlayer4;
            }

            for (int j = 0; j < colorDamage.size(); j++) {

                EnumColorPlayer color = colorDamage.get(j);

                colorDamages = colorDamageMark(color);
                path = TEARSPATH + colorDamages + PNG;
                loadImage(path, 21, 34, damageImage);
                otherPlayerboard.add(damageImage, j, 1); //node element, col, row
            }
        }
    }

    /**
     * update the value in player's playerboard
     * @param otherPlayer players in game except main player
     * @param cont counter need to know if player has died 4 (cont=0) or 5 (cont=1) times and consequently set the right image (skull)
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshOthersValue(ArrayList<Player> otherPlayer, int cont) throws RemoteException{

        ImageView valueImage = new ImageView();
        path = SKULLPATH + PNG;
        GridPane otherPlayerboard = new GridPane();

        for (int i = 0; i < otherPlayer.size(); i++){

            Player player = otherPlayer.get(i);

            if (i==0){

                otherPlayerboard = gridPlayerboardPlayer2;
            }else if (i==1){

                otherPlayerboard = gridPlayerboardPlayer3;
            }else if (i==2){

                otherPlayerboard = gridPlayerboardPlayer3;
            }else if (i==3){

                otherPlayerboard = gridPlayerboardPlayer4;
            }

            if(player.getPlayerBoard().getBoardValue()==8){

                valueImage.setImage(null);
            }

            if (player.getPlayerBoard().getBoardValue()==6){

                loadImage(path, 21,34, valueImage);
                otherPlayerboard.add(valueImage, 2, 4);
            }

            if (player.getPlayerBoard().getBoardValue()==4){

                loadImage(path, 21,34, valueImage);
                otherPlayerboard.add(valueImage, 2, 5);
            }

            if (player.getPlayerBoard().getBoardValue()==2){

                loadImage(path, 21,34, valueImage);
                otherPlayerboard.add(valueImage, 2, 6);
            }

            if (player.getPlayerBoard().getBoardValue()==1 && cont==0){

                loadImage(path, 21,34, valueImage);
                otherPlayerboard.add(valueImage, 2, 7);
            }

            if (player.getPlayerBoard().getBoardValue()==1 && cont==1){

                loadImage(path, 21,34, valueImage);
                otherPlayerboard.add(valueImage, 2, 8);
            }
        }
    }

    // END OTHERS PLAYERBOARD ---------------------------------------------------------------------------------------

    // YOUR WEAPON --------------------------------------------------------------------------------------------------

    /**
     * update the player's weapon
     * @param weaponCards player's weaponList
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshYourWeapon(ArrayList<WeaponCard> weaponCards) throws RemoteException{

        String nameWeapon;
        String nameWeaponNoSpace;

        for (int i = 0; i < weaponCards.size(); i++) {

            WeaponCard w = weaponCards.get(i);

            nameWeapon = w.getNameWeaponCard();
            nameWeaponNoSpace = nameWeapon.replaceAll("\\s+",""); //return string without space
            path = WEAPONPATH + nameWeaponNoSpace + PNG;
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
        String namePowerUpNoSpace;
        String color;

        for (int i = 0; i < powerUpCards.size(); i++) {

            PowerUpCard p = powerUpCards.get(i);

            namePowerUp = p.getNameCard();
            namePowerUpNoSpace = namePowerUp.replaceAll("\\s+",""); //return string without space
            color = p.getColorPowerUpCard().toString();
            path = POWERUPPATH + namePowerUpNoSpace + "_" + color + PNG;
            Image powerup = new Image(path);

            if(i==0){

                powerUp1.setImage(powerup);
            }

            if(i==1){

                powerUp2.setImage(powerup);
            }

            if(i==2){

                powerUp3.setImage(powerup);
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
                loadImage(path, 24, 26, ammoImage);
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
                loadImage(path, 24, 26, ammoImage);
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
                loadImage(path, 24, 26, ammoImage);
                gridAmmo.add(ammoImage, ammoIndex, 2); //node, column, row
            }else{

                ((ImageView) ammoImage).setImage(null);
            }
        }
    }

    // END AMMO -----------------------------------------------------------------------------------------------------

    // MAP ----------------------------------------------------------------------------------------------------------

    /**
     * add map image
     * @param gameModel the reference of gamemodel
     * @throws RemoteException if the reference could not be accessed
     */
    void addMapImage(GameModel gameModel) throws RemoteException{

        String name;
        ImageView mapImage = new ImageView();

        name = gameModel.getMap().getName();
        path = MAPSPATH + name + PNG;
        loadImage(path,636,509, mapImage);
        esternalGridMap.add(mapImage,0,0);
    }

    // KILL SHOT TRACK ----------------------------------------------------------------------------------------------

    /**
     * add red skull image on kill shot track
     * @param gameModel the reference of gamemodel
     * @throws RemoteException if the reference could not be accessed
     */
    void addSkullKillShotTrack(GameModel gameModel) throws RemoteException{


        ImageView skullImage = new ImageView();
        path = SKULLPATH + PNG;
        for (int i = gameModel.getKillShotTrack().skullNumber(); i > 0; i--){

            if(i==gameModel.getKillShotTrack().skullNumber()){                  //i=8 (al massimo, poi gli altri di conseguenza)

                loadImage(path,27,37, skullImage);
                gridSkull3.add(skullImage,1,0);
            }else if(i==gameModel.getKillShotTrack().skullNumber()-1){

                loadImage(path,27,37, skullImage);
                gridSkull3.add(skullImage,0,0);
            }else if(i==gameModel.getKillShotTrack().skullNumber()-2){

                loadImage(path,27,37, skullImage);
                gridSkull2.add(skullImage,3,0);
            }
            else if(i==gameModel.getKillShotTrack().skullNumber()-3){

                loadImage(path,27,37, skullImage);
                gridSkull2.add(skullImage,2,0);
            }
            else if(i==gameModel.getKillShotTrack().skullNumber()-4){

                loadImage(path,27,37, skullImage);
                gridSkull2.add(skullImage,1,0);
            }else if(i==gameModel.getKillShotTrack().skullNumber()-5){

                loadImage(path,27,37, skullImage);
                gridSkull2.add(skullImage,0,0);
            }else if(i==gameModel.getKillShotTrack().skullNumber()-5){

                loadImage(path,27,37, skullImage);
                gridSkull1.add(skullImage,3,0);
            }else if(i==gameModel.getKillShotTrack().skullNumber()-5){

                loadImage(path,27,37, skullImage);
                gridSkull1.add(skullImage,2,0);
            }
        }
    }

    /**
     * refresh kill shot track
     * @param killShotTrackPoints list of kill shot track point on kill shot track
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshMarkKillShotTrack(ArrayList<KillShotTrackPoint> killShotTrackPoints) throws RemoteException{

        String colorMark;
        int position;

        for (int i = 0; i < killShotTrackPoints.size(); i++) {

            KillShotTrackPoint k = killShotTrackPoints.get(i);

            if (!k.isSkull()) {

                position = killShotTrackPoints.size() - i;
                colorMark = k.getMark1().toString();
                path = TEARSPATH + colorMark + PNG;
                addMarkKillShotTrack(path, position);
            }
        }
    }

    /**
     * add right mark image on kill shot track
     * @param path the string for upload the right image
     * @param position the position where add the image
     * @throws RemoteException if the reference could not be accessed
     */
    void addMarkKillShotTrack(String path, int position) throws RemoteException{

        ImageView markImage = new ImageView();

        if(position==8){

            loadImage(path,27,37, markImage);
            gridSkull1.add(markImage,2,0);
        }else if(position==7){

            loadImage(path,27,37, markImage);
            gridSkull1.add(markImage,3,0);
        }else if(position==6){

            loadImage(path,27,37, markImage);
            gridSkull2.add(markImage,0,0);
        }else if(position==5){

            loadImage(path,27,37, markImage);
            gridSkull2.add(markImage,1,0);
        }else if(position==4){

            loadImage(path,27,37, markImage);
            gridSkull2.add(markImage,2,0);
        }else if(position==3){

            loadImage(path,27,37, markImage);
            gridSkull2.add(markImage,3,0);
        }else if(position==2){

            loadImage(path,27,37, markImage);
            gridSkull3.add(markImage,0,0);
        }else if(position==1){

            loadImage(path,27,37, markImage);
            gridSkull3.add(markImage,1,0);
        }
    }

    // END KILL SHOT TRACK ------------------------------------------------------------------------------------------

    /**
     * update the weapons on generation square blu
     * @param generationSquare a generationSquare
     * @throws RemoteException if the reference could not be accessed
     */
    void refreshWeaponGenerationBlue(GenerationSquare generationSquare) throws RemoteException{

        String nameWeapon;
        String nameWeaponNoSpace;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for (int i = 0; i < weaponList.size(); i++) {

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            nameWeaponNoSpace = nameWeapon.replaceAll("\\s+",""); //return string without space
            path = WEAPONPATH + nameWeaponNoSpace + PNG;

            if(i==0){

                loadImage(path,60,102, weaponImage);
                weaponB1.add(weaponImage,0,0);
            }

            if(i==1){

                loadImage(path,60,102, weaponImage);
                weaponB1.add(weaponImage,1,0);
            }

            if(i==2){

                loadImage(path, 60,102, weaponImage);
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
        String nameWeaponNoSpace;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for(int i = 0; i < weaponList.size(); i++){

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            nameWeaponNoSpace = nameWeapon.replaceAll("\\s+",""); //return string without space
            path = WEAPONPATH + nameWeaponNoSpace + PNG;

            if(i==0){

                loadImage(path, 71,216, weaponImage);
                weaponY1.add(weaponImage,1,1);
            }

            if(i==1){

                loadImage(path,71,210, weaponImage);
                weaponY2.add(weaponImage,1,1);
            }

            if(i==2){

                loadImage(path, 76,215, weaponImage);
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
        String nameWeaponNoSpace;
        ImageView weaponImage = new ImageView();
        ArrayList<WeaponCard> weaponList = generationSquare.getWeaponList();

        for(int i = 0; i < weaponList.size(); i++) {

            WeaponCard w = weaponList.get(i);

            nameWeapon = w.getNameWeaponCard();
            nameWeaponNoSpace = nameWeapon.replaceAll("\\s+",""); //return string without space
            path = WEAPONPATH + nameWeaponNoSpace + PNG;

            if(i==0){

                loadImage(path, 72,106, weaponImage);
                weaponR1.add(weaponImage,1,0);
            }

            if(i==1){

                loadImage(path, 66,112, weaponImage);
                weaponR1.add(weaponImage,1,2);
            }

            if(i==2){

                loadImage(path, 67,106, weaponImage);
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

            loadImage(path,36,55, playerImage1);
            square.add(playerImage1, 1, 0);
        }

        if (s.getPlayers().size()==2){

            loadImage(path,36,55, playerImage1);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2);
            square.add(playerImage2, 2, 0);
        }

        if (s.getPlayers().size()==3){

            loadImage(path,36,55, playerImage1);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3);
            square.add(playerImage3, 0, 1);
        }

        if (s.getPlayers().size()==4){

            loadImage(path,36,55, playerImage1);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3);
            square.add(playerImage3, 0, 1);

            path = s.getPlayers().get(3).getName();
            loadImage(path,36,55, playerImage4);
            square.add(playerImage4, 1, 1);
        }

        if (s.getPlayers().size()==5){

            loadImage(path,36,55, playerImage1);
            square.add(playerImage1, 1, 0);

            path = s.getPlayers().get(1).getName();
            loadImage(path,36,55, playerImage2);
            square.add(playerImage2, 2, 0);

            path = s.getPlayers().get(2).getName();
            loadImage(path,36,55, playerImage3);
            square.add(playerImage3, 0, 1);

            path = s.getPlayers().get(3).getName();
            loadImage(path,36,55, playerImage4);
            square.add(playerImage4, 1, 1);

            path = s.getPlayers().get(4).getName();
            loadImage(path,36,55, playerImage5);
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
        GridPane squareGrid;

        for (Square s : squares){

            for (int i = 0; i < s.getPlayers().size(); i++){

                squareGrid = knowSquare(s.getPlayers().get(i));
                nameSinglePlayer = namePlayer(s.getPlayers().get(i));
                path = PLAYERSPATH + nameSinglePlayer + PNG;
                addPlayerInRightPosition(path, s, squareGrid);
            }
        }
    }

    /**
     * add the right image of ammo card in the right normal square
     * @param normalSquares a list of normal square in map
     * @throws RemoteException if the reference could not be accessed
     */
    void addAmmoImageOnNormalSquare(ArrayList<NormalSquare> normalSquares) throws RemoteException{

        GridPane normalSquareGrid;
        ImageView ammoCardImage = new ImageView();
        String nameAmmoCard;

        for (NormalSquare ns : normalSquares){

            nameAmmoCard = knowAmmo(ns);
            path = AMMOCARDSPATH + nameAmmoCard + PNG;
            normalSquareGrid = knowNormalSquare(ns);
            loadImage(path, 36,55, ammoCardImage);
            normalSquareGrid.add(ammoCardImage, 0,0);
        }
    }

    /**
     * return a string corresponding to the right name of ammoCard image to add on Normal Square in map
     * @param normalSquare a single normal square in map
     * @throws RemoteException if the reference could not be accessed
     */
    String knowAmmo(NormalSquare normalSquare) throws RemoteException{

        String blu = "";
        String red = "";
        String yellow = "";
        String powerUp = "";
        String ammoCard;

        int contB = 0;
        int contR = 0;
        int contY = 0;

        for (EnumColorCardAndAmmo a : normalSquare.getAmmoCard().getAmmo()){

            switch (a){

                case BLU:
                    contB++;
                    break;
                case RED:
                    contR++;
                    break;
                case YELLOW:
                    contY++;
                    break;
            }
        }

        for (int i = 0; i < contB; i++){

            blu = "B" + blu;
        }

        for (int i = 0; i < contR; i++){

            red = "R" + red;
        }

        for (int i = 0; i < contY; i++){

            yellow = "Y" + yellow;
        }

        if (normalSquare.getAmmoCard().hasPowerUpCard()){

            powerUp = "P";
        }

        ammoCard = blu + red + yellow + powerUp;

        return ammoCard;
    }

    /**
     * return the right GridPane corresponding to the GridPane (a normal square) in map where is the ammoCard on
     * @param normalSquare a single normal square of the map
     * @throws RemoteException if the reference could not be accessed
     */
    GridPane knowNormalSquare(NormalSquare normalSquare) throws RemoteException{

        GridPane ns = new GridPane();

        if(normalSquare.getRow()==0 && normalSquare.getColumn()==0){

            ns = square1;
        }

        if(normalSquare.getRow()==0 && normalSquare.getColumn()==1){

            ns = square2;
        }

        if(normalSquare.getRow()==0 && normalSquare.getColumn()==3){

            ns = square4;
        }

        if(normalSquare.getRow()==1 && normalSquare.getColumn()==1){

            ns = square6;
        }

        if(normalSquare.getRow()==1 && normalSquare.getColumn()==2){

            ns = square7;
        }

        if(normalSquare.getRow()==1 && normalSquare.getColumn()==3){

            ns = square8;
        }

        if(normalSquare.getRow()==2 && normalSquare.getColumn()==0){

            ns = square9;
        }

        if(normalSquare.getRow()==2 && normalSquare.getColumn()==1){

            ns = square10;
        }

        if(normalSquare.getRow()==2 && normalSquare.getColumn()==2){

            ns = square11;
        }

        return ns;
    }

    // END SINGLE SQUARE --------------------------------------------------------------------------------------------

    /**
     * add the back of the card on AmmoDeck
     * @throws RemoteException if the reference could not be accessed
     */
    void addBackAmmoCardDeck() throws RemoteException{

        path = "images/backAmmo.png";
        ImageView backAmmoCardImage = new ImageView();
        loadImage(path, 70,72, backAmmoCardImage);
        weaponR2.add(backAmmoCardImage, 1,2);
    }

    /**
     * add the back of the card on PowerUpDeck
     * @throws RemoteException if the reference could not be accessed
     */
    void addBackPowerUpDeck() throws RemoteException{

        path = "images/back_powerup.png";
        ImageView backPowerUpImage = new ImageView();
        loadImage(path, 49,88, backPowerUpImage);
        gridPowerupDeck.add(backPowerUpImage, 1,0);
    }

    /**
     * add the back of the card on WeaponDeck
     * @throws RemoteException if the reference could not be accessed
     */
    void addBackWeaponDeck() throws RemoteException{

        path = "images/back_weapon.png";
        ImageView backWeaponImage = new ImageView();
        loadImage(path, 82,129, backWeaponImage);
        gridWeaponDeck.add(backWeaponImage, 0,1);
    }

    void addPoints(Player player) throws RemoteException{

        //TODO
    }

    // END MAP ------------------------------------------------------------------------------------------------------

    /**
     * loads the ImageView or the AnchorPane's background images
     * @param path the path of the image that has to be loaded
     * @param width the width of the image that has to be loaded
     * @param height the height of the image that has to be loaded
     * @param element the Node where the image will be loaded
     */
    void loadImage(String path, int width, int height, Node element){//type 0: imageView, type 1: anchorpane

        Image image = new Image(getClass().getResourceAsStream(path), width, height, false, true);

        ((javafx.scene.image.ImageView)element).setImage(image);
    }

    /**
     * add to weaponSelected (ImageView) the weapon (also power up) selected with a mouse click
     * @throws IOException any exception thrown by the underlying OutputStream
    public void weaponClick(MouseEvent e){

        String path;

        ImageView selected = (ImageView) e.getSource();
        path = selected.getImage().getUrl();
        Image weaponClicked = new Image(path);
        weaponSelected.setImage(weaponClicked);
        controlPathWeapon(path);
        
 }

    
     * control if the name of weapon is contains in the path of image and set a tha weaponInfo (Label) with the corresponding description
     * @param path the path of weapon image
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void controlPathWeapon(String path){

        if (path.contains("LOCKRIFLE")){

            weaponInfo.setText("BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.   WITH SECOND LOCK: Deal 1 mark to a different target you can see.");

        }else if (path.contains("MACHINEGUN")) {

            weaponInfo.setText("BASIC EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.   WITH FOCUS SHOT: Deal 1 additional damage to one of those targets.   WITH TURRET TRIPOD: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see.   NOTES: If you deal both additional points of damage, they must be dealt to 2 different targets. If you see only 2 targets, you deal 2 to each if you use both optional effects. If you use the basic effect on only 1 target, you can still use the the turret tripod to give it 1 additional damage.");

        }else if (path.contains("T.H.O.R")) {

            weaponInfo.setText("BASIC EFFECT: Deal 2 damage to 1 target you can see.   WITH CHAIN REACTION: Deal 1 damage to a second target that your first target can see.with high voltage: Deal 2 damage to a third target that your second target can see. You cannot use this effect unless you first use the chain reaction.   NOTES: This card constrains the order in which you can use its effects. (Most cards don't.) Also note that each target must be a different player");

        }else if (path.contains("PLASMAGUN")) {

            weaponInfo.setText("BASIC EFFECT: Deal 2 damage to 1 target you can see.   WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.   WITH CHARGED SHOT: Deal 1 additional damage to your target.   NOTES: The two moves have no ammo cost. You don't have to be able to see your target when you play the card. For example, you can move 2 squares and shoot a target you now see. You cannot use 1 move before shooting and 1 move after");

        }else if (path.contains("WHISPER")) {

            weaponInfo.setText("EFFECT: Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you.   NOTES: For example, in the 2-by-2 room, you cannot shoot a target on an adjacent square, but you can shoot a target on the diagonal. If you are beside a door, you can't shoot a target on the other side of the door, but you can shoot a target on a different square of that room");

        }else if (path.contains("ELECTOSCYTHE")) {

            weaponInfo.setText("BASIC MODE: Deal 1 damage to every other player on your square.   IN REAPER MODE: Deal 2 damage to every other player on your square.");

        }else if (path.contains("TRACTORBEAM")) {

            weaponInfo.setText("BASI MODE: Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.   IN PUNISHER MODE: Choose a target 0, 1, or 2 moves away from you. Move the target to your square and deal 3 damage to it.   NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.");

        }else if (path.contains("VORTEXCANNON")) {

            weaponInfo.setText("BASIC EFFECT: Choose a square you can see, but not your square. Call it ''the vortex''. Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage.   WITH BLACK HOLE: Choose up to 2 other targets on the vortex or 1 move away from it. Move them onto the vortex and give them each 1 damage.   NOTES: The 3 targets must be different, but some might start on the same square. It is legal to choose targets on your square, on the vortex, or even on squares you can't see. They all end up on the vortex.");

        }else if (path.contains("FURNACE")) {

            weaponInfo.setText("BASIC MODE: Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.   IN COZY FIRE MODE: Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.");

        }else if (path.contains("HEATSEEKER")) {

            weaponInfo.setText("EFFECT: Choose 1 target you cannot see and deal 3 damage to it.   NOTES: Yes, this can only hit targets you cannot see");

        }else if (path.contains("HELLION")) {

            weaponInfo.setText("BASIC MODE: Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.   IN NANO-TRACER MODE: Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.");

        }else if (path.contains("FLAMETHROWER")) {

            weaponInfo.setText("BASIC MODE: Choose a square 1 move away and possibly a second square 1 more move away in the same direction. On each square, you may choose 1 target and give it 1 damage.   IN BARBECUE MODE: Choose 2 squares as above. Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.   NOTES: This weapon cannot damage anyone in your square. However, it can sometimes damage a target you can't see – the flame won't go through walls, but it will go through doors. Think of it as a straight-line blast of flame that can travel 2 squares in a cardinal direction");

        }else if (path.contains("GRENADELAUNCHER")) {

            weaponInfo.setText("BASIC EFFECT: Deal 1 damage to 1 target you can see. Then you may move the target 1 square.   WITH EXTRA GRENADE: Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect's move.   NOTES: For example, you can shoot a target, move it onto a square with other targets, then damage everyone including the first target. Or you can deal 2 to a main target, 1 to everyone else on that square, then move the main target. Or you can deal 1 to an isolated target and 1 to everyone on a different square. If you target your own square, you will not be moved or damaged.");

        }else if (path.contains("ROCKET LAUNCHER")) {

            weaponInfo.setText("BASIC EFFECT: Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.   WHIT ROCKET JUMP: Move 1 or 2 squares. This effect can be used either before or after the basic effect.   WHIT FRAGMENTING WARHEAD: During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.   NOTES: If you use the rocket jump before the basic effect, you consider only your new square when determining if a target is legal. You can even move off a square so you can shoot someone on it. If you use the fragmenting warhead, you deal damage to everyone on the target's square before you move the target – your target will take 3 damage total.");

        }else if (path.contains("RAILGUN")) {

            weaponInfo.setText("BASIC MODE: Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.   IN PIERCING MODE: Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.   NOTES: Basically, you're shooting in a straight line and ignoring walls. You don't have to pick a target on the other side of a wall – it could even be someone on your own square – but shooting through walls sure is fun. There are only 4 cardinal directions. You imagine facing one wall or door, square-on, and firing in that direction. Anyone on a square in that direction (including yours) is a valid target. In piercing mode, the 2 targets can be on the same square or on different squares");

        }else if (path.contains("CYBERBLADE")) {

            weaponInfo.setText("BASIC EFFECT: Deal 2 damage to 1 target on your square.   WITH SHADOWSTEP: Move 1 square before or after the basic effect.   WITH SLICE AND DICE: Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.   NOTES: Combining all effects allows you to move onto a square and whack 2 people; or whack somebody, move, and whack somebody else; or whack 2 people and then move.");

        }else if (path.contains("ZX-2")) {

            weaponInfo.setText("BASIC MODE: Deal 1 damage and 2 marks to 1 target you can see.   IN SCANNER MODE: Choose up to 3 targets you can see and deal 1 mark to each.   NOTES: Remember that the 3 targets can be in 3 different rooms");

        }else if (path.contains("SHOTGUN")) {

            weaponInfo.setText("BASIC MODE: Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square.   IN LONG BARREL MODE: Deal 2 damage to 1 target on any square exactly one move away.");

        }else if (path.contains("POWERGLOVE")) {

            weaponInfo.setText("BASIC MODE: Choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks.   IN ROCKET FIST MODE: Choose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there. If you want, you may move 1 more square in that same direction (but only if it is a legal move). You may deal 2 damage to 1 target there, as well.   NOTES: In rocket fist mode, you're flying 2 squares in a straight line, punching 1 person per square.");

        }else if (path.contains("SHOCKWAVE")) {

            weaponInfo.setText("BASIC MODE: Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target.   IN TSUNAMI MODE: Deal 1 damage to all targets that are exactly 1 move away.");

        }else if (path.contains("SLADGEHAMMER")) {

            weaponInfo.setText("BASIC MODE: Deal 2 damage to 1 target on your square.   IN PULVERIZE MODE: Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.   NOTES: Remember that moves go through doors, but not walls.");

        }else if (path.contains("TARGETINGSCOPE_BLU")) {

            weaponInfo.setText(targetingScope);

        }else if (path.contains("TARGETINGSCOPE_RED")) {

            weaponInfo.setText(targetingScope);

        }else if (path.contains("TARGETINGSCOPE_YELLOW")) {

            weaponInfo.setText(targetingScope);

        }else if (path.contains("NEWTON_BLU")) {

            weaponInfo.setText(newton);

        }else if (path.contains("NEWTON_RED")) {

            weaponInfo.setText(newton);

        }else if (path.contains("NEWTON_YELLOW")) {

            weaponInfo.setText(newton);

        }else if (path.contains("TAGBACKGRENADE_BLU")) {

            weaponInfo.setText(tagbackGrenade);

        }else if (path.contains("TAGBACKGRENADE_RED")) {

            weaponInfo.setText(tagbackGrenade);

        }else if (path.contains("TAGBACKGRENADE_YELLOW")) {

            weaponInfo.setText(tagbackGrenade);

        }else if (path.contains("TELEPORTER_BLU")) {

            weaponInfo.setText(teleporter);

        }else if (path.contains("TELEPORTER_RED")) {

            weaponInfo.setText(teleporter);

        }else if (path.contains("TELEPORTER_YELLOW")) {

            weaponInfo.setText(teleporter);

        }
    }

    /**
     * control the coordinates of the square selected and set the corresponding index
     * @param e square selected event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void takeIndexSquare(MouseEvent e){

        //TODO settare row e col

        //potrei lavorare direttamente sulla ImageView con .getParent();

        int row;
        int col;

        GridPane selected = (GridPane) e.getSource();

        if (selected == square1){

            row = 0;
            col = 0;
        }else if (selected == square2){

            row = 0;
            col = 1;
        }else if (selected == square3){

            row = 0;
            col = 2;
        }else if (selected == square4){

            row = 0;
            col = 3;
        }else if (selected == square5){

            row = 1;
            col = 0;
        }else if (selected == square6){

            row = 1;
            col = 1;
        }else if (selected == square7){

            row = 1;
            col = 2;
        }else if (selected == square8){

            row = 1;
            col = 3;
        }else if (selected == square9){

            row = 2;
            col = 0;
        }else if (selected == square10){

            row = 2;
            col = 1;
        }else if (selected == square11){

            row = 2;
            col = 2;
        }else if (selected == square12){

            row = 2;
            col = 3;
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {

        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * decides what to do when the shootButton is clicked
     * @param e shoot button event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void shootButtonClicked(ActionEvent e) throws IOException {

        answerOrMessageError.setText("SELECT A WEAPON FROM YOURS");

        //TODO selezionare l'arma che si vuole usare e dopo l'immagine del giocatore
    }

    /**
     * decides what to do when the runAroundButton is clicked
     * @param e shoot button event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void runAroundButtonClicked(ActionEvent e) throws IOException {

        answerOrMessageError.setText("WHERE DO YOU WANT TO GO?");

        //TODO cliccare sull'immagine delle munizioni sulla mappa su cui ci si vuole spostare (il quadratino in alto a sinistra in ogni square)
    }

    /**
     * decides what to do when the grubStuffButton is clicked
     * @param e shoot button event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void grubStuffButtonClicked(ActionEvent e) throws IOException {

        answerOrMessageError.setText("WHERE DO YOU WANT TO GO TO GRUB A STUFF?");

        //TODO cliccare sull'immagine delle munizioni sulla mappa su cui ci si vuole spostare
    }

    /**
     * decides what to do when the grubStuffButton is clicked
     * @param e shoot button event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void rechargeButtonClicked(ActionEvent e) throws IOException {

        answerOrMessageError.setText("SELECT WEAPON FROM YOURS THAT YOU WANT TO RELOAD");

        //TODO selezionare un'arma e restituire l'arma/armi scelte
    }

    public void selectEffect() throws RemoteException{

        effect1.setVisible(true);
        effect2.setVisible(true);
        effect3.setVisible(true);
    }

    public void rechargeWeapon() throws RemoteException{

        buttonRecharge.setVisible(true);
    }

    /**
     * view a message
     */
    void serverDown(){

        buttonShoot.setVisible(false);
        buttonGrubStuff.setVisible(false);
        buttonRunAround.setVisible(false);
        buttonRecharge.setVisible(false);
        buttonEndTurn.setVisible(false);
        buttonRejoin.setVisible(false);
        answerOrMessageError.setText("SEEMS LIKE THE SERVER HAS BEEN SHUT DOWN");
    }

    /**
     * view a message and a button for rejoin the match
     */
    void setInactive(){

        answerOrMessageError.setText("TO REJOIN AGAIN THE MATCH PRESS THE BUTTON");
        buttonRejoin.setVisible(true);
    }

    /**
     * hide the rejoin button and add again the player to the match
     * @param e rejoin button event
     * @throws IOException any exception thrown by the underlying OutputStream
     */
    public void rejoinButtonClicked(ActionEvent e) throws IOException {

        viewGUI.matchRejoined();
        buttonRejoin.setVisible(false);
        answerOrMessageError.setText("JOINING AGAIN THE MATCH...\nWAIT YOUR TURN");
        rejoined = true;
    }

    /**
     * disables the interactive buttons to non-actual players
     * shows a WAIT message
     * @throws RemoteException if the reference could not be accessed
     */
    void waitTurn() throws RemoteException {

        if(!viewGUI.actualPlayer()){

            buttonRunAround.setVisible(false);
            buttonGrubStuff.setVisible(false);
            buttonShoot.setVisible(false);
            buttonRecharge.setVisible(false);
            buttonEndTurn.setVisible(false);
            effect1.setVisible(false);
            effect2.setVisible(false);
            effect3.setVisible(false);
            answerOrMessageError.setText("WAIT YOUR TURN");
        }
    }
}
