package it.polimi;

import it.polimi.view.cli.ViewCLI;

import javafx.application.Application;

import java.io.IOException;


public class Client {

    public static void main(String args[]) {
    
    
    
        int choiseView;
        choiseView=1;
    
        if(choiseView==1){
            //CLI
            new ViewCLI();
        
        } else if(choiseView==2){
            //GUI
            //Application.launch(ViewGUI.class);
        
        }

       

    }


}
