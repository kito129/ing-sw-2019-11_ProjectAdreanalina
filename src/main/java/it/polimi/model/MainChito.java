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
    
    
    
    }
}
