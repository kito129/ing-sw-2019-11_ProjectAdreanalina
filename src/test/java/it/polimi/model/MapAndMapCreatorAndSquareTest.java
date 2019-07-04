package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapAndMapCreatorAndSquareTest {

    Map mapD;
    Map mapB;
    Map mapC;
    Map mapA;
    Player player1;
    Player player2;
    Player player3;
    GameModel gameModel;

    @Before

    public void setUp() {

        mapD = new Map(MapCreator.createD(), "D");
        mapA = new Map(MapCreator.createA(),"A");
        mapB = new Map(MapCreator.createB(),"B");
        mapC = new Map(MapCreator.createC(),"C");
        gameModel=new GameModel();
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3= new Player(5,"NIKO",EnumColorPlayer.BLU,gameModel);
    }

    @Test

    public void getNameTest(){

        assertEquals("D", mapD.getName());
    }

    @Test

    public void getSquaresTest(){

        assertEquals(12, mapD.getSquares().size());
        assertEquals(11, mapA.getSquares().size());
        assertEquals(11, mapB.getSquares().size());
        assertEquals(10, mapC.getSquares().size());

    }

    @Test

    public void getSquareTest() throws MapException {

        assertEquals(0, mapD.getSquare(0,2).getRow());
        assertEquals(2, mapD.getSquare(0,2).getColumn());
        assertThrows(MapException.class, () -> {
            mapD.getSquare(5,5);
        });
    }

    @Test

    public void findPlayerTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(1, 2), player1);
        assertEquals(1, mapD.findPlayer(player1).getRow());
        assertEquals(2, mapD.findPlayer(player1).getColumn());
        assertThrows(MapException.class, () -> {
            mapD.findPlayer(player2);
        });

    }

    @Test

    public void playersOnSquareTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(1, 2), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(1, 2), player2);
        ArrayList<Player>players=new ArrayList<>();
        players= mapD.playersOnSquare(mapD.getSquare(1,2));
        assertEquals(2,players.size());
        assertTrue(players.contains(player1)&&players.contains(player2));
        ArrayList<Player>players2=new ArrayList<>();
        Square square=new Square(1,1,EnumColorSquare.BLU);
        players2= mapD.playersOnSquare(square);
        assertEquals(0,players2.size());
    }

    @Test

    public void distanceTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(0, 0), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(2, 3), player2);
        assertEquals(5, mapD.distance(player1,player2));
        mapD.movePlayer(player2, mapD.getSquare(0,0));
        assertEquals(0, mapD.distance(player1,player2));

        mapA.addPlayerOnSquare(mapA.getSquare(0, 0), player1);
        mapA.addPlayerOnSquare(mapA.getSquare(1, 2), player2);
        assertEquals(3, mapA.distance(player1,player2));

        mapB.addPlayerOnSquare(mapB.getSquare(1, 0), player1);
        mapB.addPlayerOnSquare(mapB.getSquare(2, 2), player2);
        assertEquals(3, mapB.distance(player1,player2));

        mapC.addPlayerOnSquare(mapC.getSquare(0, 0), player1);
        mapC.addPlayerOnSquare(mapC.getSquare(2, 3), player2);
        assertEquals(5, mapC.distance(player1,player2));


    }

    @Test

    public void distanceTest1() throws MapException {

        assertEquals(3,mapA.distance(mapA.getSquare(0,0),mapA.getSquare(0,3)));
        assertEquals(1,mapB.distance(mapB.getSquare(1,2),mapB.getSquare(1,3)));
        assertEquals(3,mapC.distance(mapC.getSquare(1,2),mapC.getSquare(2,2)));
        assertEquals(2,mapD.distance(mapD.getSquare(0,3),mapD.getSquare(2,3)));
        assertEquals(0,mapA.distance(mapA.getSquare(0,0),mapA.getSquare(0,0)));
        assertEquals(0,mapB.distance(mapB.getSquare(1,3),mapB.getSquare(1,3)));
        assertEquals(0,mapC.distance(mapC.getSquare(1,2),mapC.getSquare(1,2)));
        assertEquals(0,mapD.distance(mapD.getSquare(0,3),mapD.getSquare(0,3)));
        Square square=new Square(5,5,EnumColorSquare.BLU);
        assertEquals(-1,mapA.distance(square,mapA.getSquare(0,3)));
    }

    @Test

    public void IsNotVisibleTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(0, 0), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(2, 3), player2);
        assertTrue(mapD.isNotVisible(player1,player2));
        mapA.addPlayerOnSquare(mapA.getSquare(0, 0), player1);
        mapA.addPlayerOnSquare(mapA.getSquare(2, 3), player2);
        assertTrue(mapD.isNotVisible(player1,player2));
        mapB.addPlayerOnSquare(mapB.getSquare(0, 0), player1);
        mapB.addPlayerOnSquare(mapB.getSquare(2, 3), player2);
        assertTrue(mapB.isNotVisible(player1,player2));
        mapC.addPlayerOnSquare(mapC.getSquare(0, 0), player1);
        mapC.addPlayerOnSquare(mapC.getSquare(2, 3), player2);
        assertTrue(mapD.isNotVisible(player1,player2));
        assertFalse(mapD.isNotVisible(player1,player3));

    }

    @Test

    public void isVisibleRoom() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(0, 0), player1);
        mapA.addPlayerOnSquare(mapA.getSquare(0, 0), player1);
        mapB.addPlayerOnSquare(mapB.getSquare(0, 0), player1);
        mapC.addPlayerOnSquare(mapC.getSquare(0, 0), player1);
        assertTrue(mapD.isVisibleRoom(player1,EnumColorSquare.BLU));
        assertFalse(mapA.isVisibleRoom(player1,EnumColorSquare.YELLOW));
        assertTrue(mapB.isVisibleRoom(player1,EnumColorSquare.RED));
        assertTrue(mapC.isVisibleRoom(player1,EnumColorSquare.RED));
        assertFalse(mapC.isVisibleRoom(player2,EnumColorSquare.BLU));

    }

    @Test

    public void isVisibleTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(0, 0), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(0, 1), player2);
        assertTrue(mapD.isVisible(player1,player2));
        assertFalse(mapD.isVisible(player1,player3));
        assertTrue(mapD.isVisible(mapD.getSquare(0,0), mapA.getSquare(0,1)));
        assertTrue(mapD.isVisible(0,0,0,0));
        assertFalse(mapD.isVisible(-1,-1,2,0));
    }

    @Test

    public void sameDirectionTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(0, 0), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(0, 1), player2);
        mapD.addPlayerOnSquare(mapD.getSquare(0,2),player3);
        assertTrue(mapD.sameDirection(player1,player2,player3));
        mapD.removePlayerFromSquare(player3);
        assertFalse(mapA.sameDirection(player1,player2,player3));
        mapD.sameDirection(mapD.getSquare(0,0),mapD.getSquare(0,1),mapD.getSquare(0,3));
        mapD.sameDirection(mapD.getSquare(0,0),mapD.getSquare(0,1));

    }

    @Test

    public void playerInRoomTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(1, 2), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(1, 3), player2);
        mapD.addPlayerOnSquare(mapD.getSquare(2,3),player3);
        ArrayList<Player> players= mapD.playerInRoom(EnumColorSquare.YELLOW);
        assertTrue(players.size()==3&&players.contains(player2)&&players.contains(player1)&&
                players.contains(player3));
        assertEquals(0,mapD.playerInRoom(EnumColorSquare.BLU).size());
    }

    @Test

    public void playerOnMyNSEWTest() throws MapException {

        mapD.addPlayerOnSquare(mapD.getSquare(2, 1), player1);
        mapD.addPlayerOnSquare(mapD.getSquare(1, 1), player2);
        mapD.addPlayerOnSquare(mapD.getSquare(0,1),player3);
        ArrayList<Player> players= mapD.playerOnMyNorth(player1);
        assertTrue(players.size()==2&&players.contains(player2)&&players.contains(player3));
        assertEquals(0,mapD.playerOnMyEst(player1).size());
        mapD.movePlayer(player2, mapD.getSquare(1,0));
        mapD.movePlayer(player1,mapD.getSquare(1,2));
        assertTrue(mapD.playerOnMyWest(player1).size()==1&&mapD.playerOnMyWest(player1).contains(player2));
        mapD.movePlayer(player2,mapD.getSquare(2,2));
        assertTrue(mapD.playerOnMySouth(player1).size()==1&&mapD.playerOnMySouth(player1).contains(player2));
    }

    @Test

    public void existInMapTest() throws MapException {

        Square square=new Square(5,5,EnumColorSquare.BLU);
        assertTrue(mapC.existInMap(mapC.getSquare(0,2)));
        assertFalse(mapC.existInMap(square));
        assertTrue(mapC.existInMap(1,1));
        assertFalse(mapC.existInMap(-1,-1));
    }

    @Test

    public void isGenerationSquareTest() throws MapException {

        assertTrue(mapC.isGenerationSquare(mapC.getSquare(0,2)));
        assertFalse(mapC.isGenerationSquare(mapC.getSquare(0,0)));
        assertFalse(mapC.isGenerationSquare(null));

    }

    @Test

    public void getGenerationSquareTest() throws MapException {

        Square square=mapB.getGenerationSquare(EnumColorSquare.YELLOW);
        mapB.isGenerationSquare(square);
        assertEquals(2,square.getRow());
        assertEquals(3,square.getColumn());
        assertThrows(MapException.class, () -> {
            mapB.getGenerationSquare(EnumColorSquare.GREEN);
        });
    }

    @Test

    public void isPortTest() throws MapException {

        assertTrue(mapB.isPort(mapB.getSquare(0,1),mapB.getSquare(1,1)));
        assertFalse(mapB.isPort(mapB.getSquare(1,2),mapB.getSquare(2,2)));
        assertTrue(mapB.isPort(0,1,1,1));
        assertFalse(mapB.isPort(1,2,2,2));
        assertFalse( mapB.isPort(-1,-1,0,0));



    }

    @Test

    public void getRoomColorTest(){

        ArrayList<EnumColorSquare>colorSquares=mapB.getRoomColor();
        assertEquals(5,colorSquares.size());
        assertTrue(colorSquares.contains(EnumColorSquare.RED)&&colorSquares.contains(EnumColorSquare.BLU)&&
                colorSquares.contains(EnumColorSquare.WHITE)&&colorSquares.contains(EnumColorSquare.YELLOW)&&
                colorSquares.contains(EnumColorSquare.PINK));

    }



}