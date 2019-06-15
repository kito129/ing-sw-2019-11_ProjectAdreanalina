package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;

import java.rmi.RemoteException;
import java.util.TimerTask;

public class TaskPingServer extends TimerTask {

    RemoteGameController gameController;

    public TaskPingServer(RemoteGameController gameController){

        this.gameController=gameController;
    }

    public void run() {

        try{

            gameController.pingToServer();

        }catch (RemoteException remoteExcpetion){

            System.out.println("SERVER IS DOWN");
            System.exit(0);
        }




    }
}
