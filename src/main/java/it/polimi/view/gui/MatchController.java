package it.polimi.view.gui;

import it.polimi.model.Player;
import it.polimi.model.WeaponCard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image ;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MatchController {

    @FXML
    protected AnchorPane APtop, APbottom;

    @FXML
    protected GridPane gridActualplayerPlayerboard, InternalgridActualplayerPlayerboar, gridAmmo;

    @FXML
    protected Label answerOrMessageError;

    static final String PNG = ".png";
    static final String JPG = ".jpg";
    static final String AMMOPATH = "images/ammo/";
    static final String AMMOCARDSPATH = "images/ammoCards/";
    static final String WEAPONPATH = "images/weapon/";
    static final String POWERUPPATH = "images/powerup/";
    static final String MAPSPATH = "images/maps/";
    static final String PLAYERBOARDPATH = "images/playerboards/";
    static final String POINTSPATH = "images/points/";
    static final String TEARSPATH = "images/tears/";

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

    void refreshDamagePlayerboard(Player player) throws RemoteException{

        int damageIndex;
        int markIndex;
        int valueIndex;

        for(Node damageImage : InternalgridActualplayerPlayerboar.getChildren()){

            damageIndex = GridPane.getColumnIndex(damageImage);
            if(damageIndex==0){ //devo verificare il colore dei danni di volta in volta quindi servirebbe un metodo per vedere quali danni ho subito in quel momento e non solo i danni totali

            }
        }

        for (Node markImage : InternalgridActualplayerPlayerboar.getChildren()){

            markIndex = GridPane.getColumnIndex(markImage);
        }

        for (Node valueImage : InternalgridActualplayerPlayerboar.getChildren()){

            valueIndex = GridPane.getColumnIndex(valueImage);
        }
    }

    // END YOUR PLAYERBOARD -----------------------------------------------------------------------------------------

    // YOUR WEAPON --------------------------------------------------------------------------------------------------

    void refreshYourWeapon(Player player, ArrayList<WeaponCard> weaponCards) throws RemoteException{

        String nameWeapon = "";
        ImageView weaponImage = new ImageView();

        for (WeaponCard w : weaponCards){

            nameWeapon = w.getNameWeaponCard();
            path = WEAPONPATH + nameWeapon + PNG;
            loadImage(path, 101, 181, weaponImage, 0);
        }
    }

    // END YOUR WEAPON ----------------------------------------------------------------------------------------------

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
