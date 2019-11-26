package it.polimi;

import it.polimi.controller.GameController;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String args[]) throws RemoteException, AlreadyBoundException {

        
        GameController gameController=new GameController(args[0]);
        Registry registry=LocateRegistry.createRegistry(1099);
        registry.bind("gameController",gameController);
        System.out.println("ready to start, waiting for players");



    }
}
