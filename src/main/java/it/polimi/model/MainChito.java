package it.polimi.model;

public class MainChito {
    public static void main(String[] args){
        
        
        Map mappa = new Map(MapCreator.createC());
        
        int a,b,c,d=0;
        

/*
        //cerco disatnza
        a=1;
        b=1;
        c=0;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
        
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
    
        //cerco disatnza
        a=1;
        b=2;
        c=1;
        d=1;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=1;
        b=1;
        c=1;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=0;
        b=0;
        c=2;
        d=3;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=1;
        b=0;
        c=1;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=0;
        b=2;
        c=2;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=1;
        b=0;
        c=2;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    
        //cerco disatnza
        a=1;
        b=1;
        c=2;
        d=2;
        System.out.println("cerco : ("+ a+","+b+") e ("+c+","+d+")");
    
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(a, b, c, d, 1));
        //cerco visibilità
        if (mappa.isVisible(a, b, c, d)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        
        mappa.refreshMap();
        */
        //cerco funzioni
        a=0;
        b=2;
        c=2;
        d=2;
        
        Square squareA=new Square(a,b,EnumColorSquare.RED);
        Square squareB=new Square(c,d,EnumColorSquare.RED);
        
        Player playerA=new Player(1,"marco",EnumColorPlayer.GREEN);
        Player playerB=new Player(2,"andre",EnumColorPlayer.BLU);
        Player playerC=new Player(2,"simo",EnumColorPlayer.BLU);
        
        mappa.addPlayerOnSquare(mappa.getSquare(a,b),playerA);
        mappa.getSquare(c,d).addPlayer(playerB);
    
        
        System.out.println(mappa.findPlayer(playerA));
        System.out.println(mappa.findPlayer(playerB));
        System.out.println(mappa.isInMySquare(playerA,playerB));
        
        mappa.movePlayer(playerA,mappa.getSquare(c,d));
    
        System.out.println("\n"+mappa.findPlayer(playerA));
        System.out.println(mappa.findPlayer(playerB));
        System.out.println(mappa.isInMySquare(playerA,playerB));
        
        System.out.println(mappa.distance(playerA,playerB));
    
        mappa.movePlayer(playerA,mappa.getSquare(2,1));
        System.out.println(playerA.toString());
        System.out.println(playerB.toString());
        
        System.out.println("distance: " + mappa.distance(playerA,playerB));
        System.out.println("my square: " + mappa.isInMySquare(playerA,playerB));
        
        System.out.println("same direction: " + mappa.sameDirection(playerA,playerB,playerC));
        
        System.out.println("south: " + mappa.playerOnMySouth(playerA));
        System.out.println("north: " + mappa.playerOnMyNorth(playerA));
        System.out.println("est: " + mappa.playerOnMyEst(playerA));
        System.out.println("west: " + mappa.playerOnMyWest(playerA));
        mappa.movePlayer(playerB,mappa.getSquare(2,2));
      
        System.out.println("my room: " + mappa.playerInRoom(EnumColorSquare.WHITE));
    
        System.out.println("is visible room: " + mappa.isVisibleRoom(playerA,EnumColorSquare.BLU));
        System.out.println("is visible room: " + mappa.isVisible(playerA,playerB));
    
        System.out.println("is generation player A: " + mappa.isGenerationSquare(mappa.findPlayer(playerA)));
        System.out.println("is generation player B: " + mappa.isGenerationSquare(mappa.findPlayer(playerB)));
    
        mappa.movePlayer(playerA,mappa.getSquare(2,3));
    
        System.out.println("is generation player A: " + mappa.isGenerationSquare(mappa.findPlayer(playerA)));
        //MAIN ARMI
        
        
    
    
    }
}
