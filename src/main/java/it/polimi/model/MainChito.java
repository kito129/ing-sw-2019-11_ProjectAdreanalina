package it.polimi.model;

public class MainChito {
    public static void main(String[] args){
        
        MapCreator mapCreator =new MapCreator();
        Map mappa = new Map(mapCreator.createC());
        
        int a,b,c,d=0;
        


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
        
        //cerco funzioni
        a=0;
        b=2;
        c=2;
        d=2;
        
        Square squareA=new Square(a,b,EnumColorSquare.RED);
        Square squareB=new Square(c,d,EnumColorSquare.RED);
        
        Player playerA=new Player(1,"marco",EnumColorPlayer.GREEN);
        Player playerB=new Player(2,"andre",EnumColorPlayer.BLU);
        
        mappa.addPlayerOnSquare(mappa.getSquare(a,b),playerA);
        mappa.getSquare(c,d).addPlayer(playerB);
    
        
        System.out.println(mappa.findPlayer(playerA));
        System.out.println(mappa.findPlayer(playerB));
        System.out.println(mappa.isInMySquare(playerA,playerB));
        
        mappa.movePlayer(playerA,mappa.getSquare(c,d));
    
        System.out.println("\n"+mappa.findPlayer(playerA));
        System.out.println(mappa.findPlayer(playerB));
        System.out.println(mappa.isInMySquare(playerA,playerB));
        
        System.out.println(mappa.distancePlayer(playerA,playerB));
    
        mappa.movePlayer(playerA,mappa.getSquare(2,1));
        System.out.println(playerA.toString());
        System.out.println(playerB.toString());
        
        System.out.println(mappa.distancePlayer(playerA,playerB));
        System.out.println(mappa.isInMySquare(playerA,playerB));
        System.out.println(mappa.sameDirection(playerA,playerB));
        System.out.println(mappa.playerOnMyRow(playerA).get(0).toString());
        mappa.movePlayer(playerB,mappa.getSquare(2,2));
      
        System.out.println(mappa.playerInRoom(EnumColorSquare.WHITE));
    
        System.out.println(mappa.isVisibleRoom(playerA,EnumColorSquare.BLU));
        
        //MAIN ARMI
        
        
    
    
    }
}
