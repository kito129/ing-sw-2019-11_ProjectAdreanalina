package it.polimi.view.gui;

import it.polimi.model.EnumColorPlayer;
import it.polimi.model.Player;
import it.polimi.model.PowerUpCard;
import it.polimi.model.WeaponCard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MatchController {

    @FXML
    protected SplitPane container;

    @FXML
    protected AnchorPane APtop, APbottom;

    @FXML
    protected GridPane gridActualplayerPlayerboard, InternalgridActualplayerPlayerboar, gridAmmo;

    @FXML
    protected Label answerOrMessageError;

    @FXML
    protected ImageView weapon1, weapon2, weapon3, powerUp1, powerUp2, powerUp3;

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

        //TODO
    }

    // YOUR PLAYERBOARD ---------------------------------------------------------------------------------------------

    void addYourPlayerboard(Player player){

        int YPIndex;

        for(Node YPimage : gridActualplayerPlayerboard.getChildren()){

            YPIndex = GridPane.getColumnIndex(YPimage);
            path = PLAYERBOARDPATH + player.getColor().toString() + PNG;
            loadImage(path,527,138, YPimage,0);
            gridActualplayerPlayerboard.add(YPimage, 0, 0); //node, column, row
        }
    }

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

    void refreshMark(Player player) throws RemoteException{

        int markIndex;

        for (Node markImage : InternalgridActualplayerPlayerboar.getChildren()){

            markIndex = GridPane.getColumnIndex(markImage);
            colorDamageMark(player.getPlayerBoard().getMarks());
            loadImage(path, 25, 47, markImage, 0);
            InternalgridActualplayerPlayerboar.add(markImage, 0, markIndex);
        }
    }

    void refreshDamage(Player player) throws RemoteException{

        int damageIndex;

        for (Node damageImage : InternalgridActualplayerPlayerboar.getChildren()){

            damageIndex = GridPane.getColumnIndex(damageImage);
            colorDamageMark(player.getPlayerBoard().getDamages());
            loadImage(path, 25, 47, damageImage, 0);
            InternalgridActualplayerPlayerboar.add(damageImage, 1, damageIndex);
        }
    }

    void refreshValue(Player player) throws RemoteException{

        int valueIndex;

        for (Node valueImage : InternalgridActualplayerPlayerboar.getChildren()){

            path = SKULLPATH + PNG;

            if(player.getPlayerBoard().getBoardValue()==8){

                ((ImageView) valueImage).setImage(null);
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
    }

    // END YOUR PLAYERBOARD -----------------------------------------------------------------------------------------

    // YOUR WEAPON --------------------------------------------------------------------------------------------------

    void refreshYourWeapon(ArrayList<WeaponCard> weaponCards) throws RemoteException{

        String nameWeapon;
        ImageView weapon = new ImageView();

        for (int i = 0; i < weaponCards.size(); i++) {

            WeaponCard w = weaponCards.get(i);

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;

            if (!w.isCharge()){

                weapon.setOpacity(0.7);
            }else {

                weapon.setOpacity(1.0);
            }

            if(i==0){

                weapon = weapon1;
                loadImage(path, 101, 181, weapon1, 0);
            }

            else if(i==1){

                weapon = weapon2;
                loadImage(path, 101, 181, weapon2, 0);
            }

            else if(i==2){

                weapon = weapon3;
                loadImage(path, 101, 181, weapon3, 0);
            }
        }
    }

    // END YOUR WEAPON ----------------------------------------------------------------------------------------------

    // YOUR POWER UP ------------------------------------------------------------------------------------------------

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
