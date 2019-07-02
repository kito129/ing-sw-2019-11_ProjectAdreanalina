package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Map creator.
 */
public class MapCreator {

    /**
     * Create a array list.
     *
     * @return the array list
     */
    public static ArrayList<Square> createA() {
    
        NormalSquare s1 = new NormalSquare(0, 0, EnumColorSquare.BLU);
        NormalSquare s2 = new NormalSquare(0, 1, EnumColorSquare.BLU);
        GenerationSquare s3 = new GenerationSquare(0, 2, EnumColorSquare.BLU);
        NormalSquare s4 = new NormalSquare(0, 3, EnumColorSquare.GREEN);
        GenerationSquare s5 = new GenerationSquare(1, 0, EnumColorSquare.RED);
        NormalSquare s6 = new NormalSquare(1, 1, EnumColorSquare.RED);
        NormalSquare s7 = new NormalSquare(1, 2, EnumColorSquare.YELLOW);
        NormalSquare s8 = new NormalSquare(1, 3, EnumColorSquare.YELLOW);
        NormalSquare s9 = new NormalSquare(2, 1, EnumColorSquare.WHITE);
        NormalSquare s10 = new NormalSquare(2, 2, EnumColorSquare.YELLOW);
        GenerationSquare s11 = new GenerationSquare(2, 3, EnumColorSquare.YELLOW);
    
    
        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        linkS1.add(s5);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s3);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s4);
        linkS3.add(s7);
        linkS3.add(s2);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s8);
        linkS4.add(s3);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s1);
        linkS5.add(s6);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s9);
        linkS6.add(s5);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s10);
        linkS7.add(s3);
        linkS7.add(s8);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s4);
        linkS8.add(s11);
        linkS8.add(s7);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s6);
        linkS9.add(s10);
        s9.setLink(linkS9);
        ArrayList<Square> linkS10 = new ArrayList<>();
        linkS10.add(s9);
        linkS10.add(s7);
        linkS10.add(s11);
        s10.setLink(linkS10);
        ArrayList<Square> linkS11 = new ArrayList<>();
        linkS11.add(s8);
        linkS11.add(s10);
        s11.setLink(linkS11);
    
        ArrayList<Square> temMap = new ArrayList<Square>();
    
        temMap.add(s1);
        temMap.add(s2);
        temMap.add(s3);
        temMap.add(s4);
        temMap.add(s5);
        temMap.add(s6);
        temMap.add(s7);
        temMap.add(s8);
        temMap.add(s9);
        temMap.add(s10);
        temMap.add(s11);
       return temMap;
    }
    
    /**
     * Create b array list.
     *
     * @return the array list
     */
    public static ArrayList<Square> createB() {
    
        NormalSquare s1 = new NormalSquare(0, 0, EnumColorSquare.RED);
        NormalSquare s2 = new NormalSquare(0, 1, EnumColorSquare.BLU);
        GenerationSquare s3 = new GenerationSquare(0, 2, EnumColorSquare.BLU);
        GenerationSquare s4 = new GenerationSquare(1, 0, EnumColorSquare.RED);
        NormalSquare s5 = new NormalSquare(1, 1, EnumColorSquare.PINK);
        NormalSquare s6 = new NormalSquare(1, 2, EnumColorSquare.PINK);
        NormalSquare s7 = new NormalSquare(1, 3, EnumColorSquare.YELLOW);
        NormalSquare s8 = new NormalSquare(2, 0, EnumColorSquare.WHITE);
        NormalSquare s9 = new NormalSquare(2, 1, EnumColorSquare.WHITE);
        NormalSquare s10 = new NormalSquare(2, 2, EnumColorSquare.WHITE);
        GenerationSquare s11 = new GenerationSquare(2, 3, EnumColorSquare.YELLOW);
        
        
        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        linkS1.add(s4);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s3);
        linkS2.add(s5);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s6);
        linkS3.add(s2);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s8);
        linkS4.add(s1);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s2);
        linkS5.add(s6);
        linkS5.add(s9);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s7);
        linkS6.add(s5);
        linkS6.add(s3);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s11);
        linkS7.add(s6);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s4);
        linkS8.add(s9);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s8);
        linkS9.add(s5);
        linkS9.add(s10);
        s9.setLink(linkS9);
        ArrayList<Square> linkS10 = new ArrayList<>();
        linkS10.add(s9);
        linkS10.add(s11);
        s10.setLink(linkS10);
        ArrayList<Square> linkS11 = new ArrayList<>();
        linkS11.add(s7);
        linkS11.add(s10);
        s11.setLink(linkS11);
        
        ArrayList<Square> temMap = new ArrayList<Square>();
        
        temMap.add(s1);
        temMap.add(s2);
        temMap.add(s3);
        temMap.add(s4);
        temMap.add(s5);
        temMap.add(s6);
        temMap.add(s7);
        temMap.add(s8);
        temMap.add(s9);
        temMap.add(s10);
        temMap.add(s11);
        return temMap;
    }
    
    /**
     * Create c array list.
     *
     * @return the array list
     */
    public static ArrayList<Square> createC() {
        
        NormalSquare s1 = new NormalSquare(0, 0, EnumColorSquare.BLU);
        NormalSquare s2 = new NormalSquare(0, 1, EnumColorSquare.BLU);
        GenerationSquare s3 = new GenerationSquare(0, 2, EnumColorSquare.BLU);
        GenerationSquare s4 = new GenerationSquare(1, 0, EnumColorSquare.RED);
        NormalSquare s5 = new NormalSquare(1, 1, EnumColorSquare.RED);
        NormalSquare s6 = new NormalSquare(1, 2, EnumColorSquare.RED);
        NormalSquare s7 = new NormalSquare(1, 3, EnumColorSquare.YELLOW);
        NormalSquare s8 = new NormalSquare(2, 1, EnumColorSquare.WHITE);
        NormalSquare s9 = new NormalSquare(2, 2, EnumColorSquare.WHITE);
        GenerationSquare s10 = new GenerationSquare(2, 3, EnumColorSquare.YELLOW);
 
        
        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        linkS1.add(s4);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s3);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s6);
        linkS3.add(s2);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s5);
        linkS4.add(s1);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s4);
        linkS5.add(s6);
        linkS5.add(s8);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s7);
        linkS6.add(s5);
        linkS6.add(s3);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s6);
        linkS7.add(s10);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s5);
        linkS8.add(s9);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s8);
        linkS9.add(s10);
        s9.setLink(linkS9);
        ArrayList<Square> linkS10 = new ArrayList<>();
        linkS10.add(s7);
        linkS10.add(s9);
        s10.setLink(linkS10);
  
        
        ArrayList<Square> temMap = new ArrayList<Square>();
        
        temMap.add(s1);
        temMap.add(s2);
        temMap.add(s3);
        temMap.add(s4);
        temMap.add(s5);
        temMap.add(s6);
        temMap.add(s7);
        temMap.add(s8);
        temMap.add(s9);
        temMap.add(s10);
        return temMap;
    }
    
    /**
     * Create d array list.
     *
     * @return the array list
     */
    public static ArrayList<Square> createD() {
        
        NormalSquare s1 = new NormalSquare(0, 0, EnumColorSquare.RED);
        NormalSquare s2 = new NormalSquare(0, 1, EnumColorSquare.BLU);
        GenerationSquare s3 = new GenerationSquare(0, 2, EnumColorSquare.BLU);
        NormalSquare s4 = new NormalSquare(0, 3, EnumColorSquare.GREEN);
        GenerationSquare s5 = new GenerationSquare(1, 0, EnumColorSquare.RED);
        NormalSquare s6 = new NormalSquare(1, 1, EnumColorSquare.PINK);
        NormalSquare s7 = new NormalSquare(1, 2, EnumColorSquare.YELLOW);
        NormalSquare s8 = new NormalSquare(1, 3, EnumColorSquare.YELLOW);
        NormalSquare s9 = new NormalSquare(2, 0, EnumColorSquare.WHITE);
        NormalSquare s10 = new NormalSquare(2, 1, EnumColorSquare.WHITE);
        NormalSquare s11 = new NormalSquare(2, 2, EnumColorSquare.YELLOW);
        GenerationSquare s12 = new GenerationSquare(2, 3, EnumColorSquare.YELLOW);
        
        
        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        linkS1.add(s5);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s3);
        linkS2.add(s6);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s4);
        linkS3.add(s2);
        linkS3.add(s7);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s3);
        linkS4.add(s8);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s1);
        linkS5.add(s9);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s2);
        linkS6.add(s10);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s3);
        linkS7.add(s11);
        linkS7.add(s8);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s4);
        linkS8.add(s7);
        linkS8.add(s12);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s5);
        linkS9.add(s10);
        s9.setLink(linkS9);
        ArrayList<Square> linkS10 = new ArrayList<>();
        linkS10.add(s9);
        linkS10.add(s6);
        linkS10.add(s11);
        s10.setLink(linkS10);
        ArrayList<Square> linkS11 = new ArrayList<>();
        linkS11.add(s10);
        linkS11.add(s7);
        linkS11.add(s12);
        s11.setLink(linkS11);
        ArrayList<Square> linkS12 = new ArrayList<>();
        linkS12.add(s8);
        linkS12.add(s11);
        s12.setLink(linkS12);
        
        
        ArrayList<Square> temMap = new ArrayList<Square>();
        
        temMap.add(s1);
        temMap.add(s2);
        temMap.add(s3);
        temMap.add(s4);
        temMap.add(s5);
        temMap.add(s6);
        temMap.add(s7);
        temMap.add(s8);
        temMap.add(s9);
        temMap.add(s10);
        temMap.add(s11);
        temMap.add(s12);
        return temMap;
    }
    
}
