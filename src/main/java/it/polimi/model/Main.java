package it.polimi.model;


import it.polimi.model.Exception.NoAvaibleCard;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TargetingScope;

import java.util.ArrayList;

public class Main {


   public static void prova(ArrayList<Integer> integers){

       for(int i=integers.size();i<3;i++){

           integers.add(1);




       }
   }

    public static void main(String args[]) {

       ArrayList<Integer> integers=new ArrayList<>();
       integers.add(3);


       prova(integers);

       System.out.println(integers);



    }







































}