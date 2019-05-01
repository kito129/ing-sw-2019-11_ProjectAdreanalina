package it.polimi.model.Weapon;

import it.polimi.model.*;

import java.util.ArrayList;

public class LockFire extends WeaponCard {
    
    
    public void effect1(GameModel gameModel, Player target){
        
        //2 danni temporanei
        ArrayList<EnumColorPlayer> damage = new ArrayList<>();
        damage.add(gameModel.getActualPlayer().getColor());
        damage.add(gameModel.getActualPlayer().getColor());
        
        //1 marchio temoporaneo
        ArrayList<EnumColorPlayer> mark = new ArrayList<>();
        mark.add(gameModel.getActualPlayer().getColor());
        try{
            //setta danni e marchi a target se vedi
            if(gameModel.getMap().isVisible(gameModel.getActualPlayer(),target)){
                target.getPlayerBoard().increaseDamages(damage);
                target.getPlayerBoard().increaseMarks(mark);
            }
        }
        catch (Exception e){
            System.out.println("target no visibile");
            e.printStackTrace();
        }
    }
    
    public void effect2(GameModel gameModel, Player target2){
        
        //verifico il costo per l'uso di effetto 2
        if(gameModel.getActualPlayer().getPlayerBoard().getAmmoR()==1 || gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(0).getColorCard()==EnumColorCard.RED){
        
        } else {
            //non farlo
        }
        
        
        //1 marchio temoporaneo
        ArrayList<EnumColorPlayer> mark = new ArrayList<>();
        mark.add(gameModel.getActualPlayer().getColor());
        
        try{
            //setta danni e marchi a target se vedi
            if(gameModel.getMap().isVisible(gameModel.getActualPlayer(),target2)){
                target2.getPlayerBoard().increaseMarks(mark);
            }
        }
        catch (Exception e){
            System.out.println("target no visibile");
            e.printStackTrace();
        }
    }
    
}
