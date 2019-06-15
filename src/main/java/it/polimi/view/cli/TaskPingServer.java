package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class TaskPingServer extends TimerTask {

    RemoteGameController gameController;
    Timer timer;

    public TaskPingServer(RemoteGameController gameController,Timer timer){

        this.gameController=gameController;
        this.timer=timer;
    }

    public void run() {

        try{

            gameController.pingToServer();

        }catch (RemoteException remoteExcpetion){

            System.out.println("SERVER IS DOWN");
            timer.cancel();
            System.exit(0);
        }




    }
}
