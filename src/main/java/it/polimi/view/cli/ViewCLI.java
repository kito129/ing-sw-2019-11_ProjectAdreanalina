package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.Player;
import it.polimi.model.RemoteGameModel;

import java.util.ArrayList;

public class ViewCLI {
    
    RemoteGameModel gameModel;
    RemoteGameController GameController;
    
    public ViewCLI(){
    
        Player ciao = this.gameModel.getActualPlayer();
    }
    
}
