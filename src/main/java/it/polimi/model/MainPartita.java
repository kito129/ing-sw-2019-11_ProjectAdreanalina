package it.polimi.model;


import java.util.ArrayList;

public class MainPartita {

    public static void main(String args[]) {


        Player player1 = new Player(1, "andrea", EnumColorPlayer.YELLOW);
        Player player2 = new Player(2, "marco", EnumColorPlayer.BLU);
        Player player3 = new Player(3, "simone", EnumColorPlayer.GREEN);
        Player player4 = new Player(4,"niko",EnumColorPlayer.PINK);
        Player player5 = new Player(5,"teo",EnumColorPlayer.GREY);
        Map map=new Map(MapCreator.createA(),"mappa a");
        KillShotTrack killShotTrack=new KillShotTrack();
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        GameModel gameModel=new GameModel(map,killShotTrack,players);
        ActionModel actionModel= new ActionModel(gameModel);


        ArrayList<EnumColorPlayer> damages=new ArrayList<>();
        damages.add(EnumColorPlayer.YELLOW);
        damages.add(EnumColorPlayer.YELLOW);
        damages.add(EnumColorPlayer.YELLOW);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.GREEN);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.GREEN);
        damages.add(EnumColorPlayer.YELLOW);

        player1.multipleDamages(damages);

        for(Player p:players){

            p.stampa();
        }

        actionModel.scoringPlayerBoard(player1);

        for(Player p:players){

            p.stampa();
        }










    }


}
