/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;
import junit.framework.TestCase;

/**
 *
 * @author Juan Diego Villegas
 */

public class TennisGameTest extends TestCase{
    TennisGame tennisGame = new TennisGame();
    
    public TennisGameTest(String player1, String player2){
        tennisGame.setPlayer1(player1);
        tennisGame.setPlayer2(player2);
    }
    
    private void getScores(int score1, int score2){
        tennisGame.resetScores();
        for (int i = 0; i < score1; i++){
            tennisGame.player1Scores();
        }
        for (int i =0; i < score2; i++){
            tennisGame.player2Scores();
        }
    }
    
    public void testFifteenLove(){
        this.getScores(1, 0);
        String expected = String.format(Constants.SCOREUNDERFOURPOINTS, 
                tennisGame.getPlayer1(), "Fifteen", tennisGame.getPlayer2(), "Love");
        assertTrue(expected.equals(tennisGame.score()));
    }
    
    public void testFifteenThirty(){
        this.getScores(1, 2);
        String expected = String.format(Constants.SCOREUNDERFOURPOINTS, 
                tennisGame.getPlayer1(), "Fifteen", tennisGame.getPlayer2(), "Thirty");
        assertTrue(expected.equals(tennisGame.score()));
    }
    
    public void testThirtyThirty(){
        this.getScores(2,2);
        String expected = String.format(Constants.SCOREUNDERFOURPOINTS, 
                tennisGame.getPlayer1(), "Thirty", tennisGame.getPlayer2(), "Thirty");
        assertTrue(expected.equals(tennisGame.score()));
    }
    
    public void testAdvantage(){
        //Prueba 1: El jugador 1 tiene la ventaja de 1 punto
        this.getScores(4, 3);
        String expected = String.format(Constants.HASADVANTAGE, tennisGame.getPlayer1());
        assertTrue(expected.equals(tennisGame.score()));
        
        //Prueba 2: El jugador 2 tiene la ventaja de 1 punto
        this.getScores(3, 4);
        expected = String.format(Constants.HASADVANTAGE, tennisGame.getPlayer2());
        assertTrue(expected.equals(tennisGame.score()));
        
        /*Prueba 3: El jugador tiene la ventaja de 1 punto pero no tiene
                    como mínimo 4 puntos para que valga.
        */
        
        this.getScores(2, 1);
        expected = String.format(Constants.HASADVANTAGE, tennisGame.getPlayer1());
        assertFalse(expected.equals(tennisGame.score()));
    }
    
    public void testDeuce(){
        
        //Prueba 1: Empate con ambos teniendo 3 puntos
        this.getScores(3,3);
        assertTrue(Constants.DEUCE.equals(tennisGame.score()));
        
        //Prueba 2: Empate con ambos teniendo mas de 3 puntos
        this.getScores(4, 4);
        assertTrue(Constants.DEUCE.equals(tennisGame.score()));
        
        //Prueba 3: Empate con ambos teniendo menos de 3 puntos
        this.getScores(2, 2);
        assertFalse(Constants.DEUCE.equals(tennisGame.score()));
    }
    
    public void testWinner(){
        
        //Prueba 1: El primer jugador gana 4-0
        this.getScores(4,0);
        String expected = String.format(Constants.HASWON, tennisGame.getPlayer1());
        assertTrue(expected.equals(tennisGame.score()));
        
        //Prueba 2: El primer jugador gana 6-4
        this.getScores(6, 4);
        expected = String.format(Constants.HASWON, tennisGame.getPlayer1());
        assertTrue(expected.equals(tennisGame.score()));
        
        //Prueba 3: El segundo jugador gana 5-2
        this.getScores(2, 5);
        expected = String.format(Constants.HASWON, tennisGame.getPlayer2());
        assertTrue(expected.equals(tennisGame.score()));
        
        /*Prueba 4: El segundo jugador va ganando 3-1 cumpliendo con la condición
                  de que haya diferencia de minimo 2 puntos pero no la de
                 tener como mínimo 4 puntos.
        */
        
        this.getScores(1, 3);
        assertFalse(expected.equals(tennisGame.score()));
        
        /*Prueba 5: El primer jugador gana 4-3 cumpliendo con la condición
                    de victoria de tener como mínimo 4 puntos para ganar
                    pero no cumpliendo con la condición de que haya 2 puntos
                    de diferencia entre ambos jugadores.
        */
        this.getScores(4, 3);
        expected = String.format(Constants.HASWON, tennisGame.getPlayer1());
        assertFalse(expected.equals(tennisGame.score()));
    }
}
