package challenge;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan Diego Villegas
 * 
 */

public class TennisGame {

    /**
     * @return the player1
     */
    public String getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    /**
     * @return the player1Score
     */

    /**
     * @param player1Score the player1Score to set
     */


    /**
     * @return the player2
     */
    public String getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }




    private String player1;
    private int player1Score = 0;
    private String player2;
    private int player2Score = 0;

    public TennisGame(){

    }

    public TennisGame(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    
    //Metodo que devuelve el jugador que tiene mas puntos en la partida
    private String playerWithMoreScore(){
        if (player1Score > player2Score)
                return player1;
            else
                return player2;
    }
    
    //Metodo que permite saber si ya hay un ganador en la partida
    private boolean thereIsAWinner(){
        if (this.player1Score >= 4 && 
                this.player1Score-this.player2Score >= 2) 
            return true;
        else return this.player2Score >= 4 && 
                this.player2Score-this.player1Score >= 2;
    }
    
    //Metodo que permite saber si algun jugador tiene la ventaja sobre otro
    private boolean thereIsAnAdvantage(){
        if (this.player1Score >= 3 && this.player2Score >= 3)
            return Math.abs(player1Score-player2Score) == 1;       
        else return false;
    }
    
    
    //Metodo que permite saber si ambos estan en empate
    private boolean thereIsADeuce(){
        return this.player1Score >= 3 && this.player2Score >= 3 &&
                this.player1Score == this.player2Score;
    }
    
    //Metodo que permite saber el marcador si es que los jugadores tienen
    //menos de 4 puntos
    private String scoreFromPlayer(int score){
        switch (score){
        case 0:
            return "Love";
        case 1:
            return "Fifteen";
        case 2:
            return "Thirty";
        case 3:
            return "Forty";
        default: //No deberia poder llegar
            throw new IllegalArgumentException(Integer.toString(score));
        }
    }
    
    //Método que se encargara de mostrar el score actual
    public String score(){
        if (thereIsAWinner())
            return String.format(Constants.HASWON, playerWithMoreScore());
        else if (thereIsAnAdvantage())
            return String.format(Constants.HASADVANTAGE, playerWithMoreScore());
        else if (thereIsADeuce())
            return Constants.DEUCE;    
        else
            return String.format(Constants.SCOREUNDERFOURPOINTS,
                    player1, scoreFromPlayer(player1Score), player2, scoreFromPlayer(player2Score));
    }
    
    //Este metodo se llama cuando el jugador 1 anota un punto en la partida
    public void player1Scores(){
        this.player1Score+=1;
    }
    
    //Este metodo se llama cuando el jugador 2 anota un punto en la partida
    public void player2Scores(){
        this.player2Score+=1;
    }
    
    
    //Metodo para reiniciar puntuaciones, sirve para los tests
    public void resetScores(){
        this.player1Score = 0;
        this.player2Score = 0;
    }
}
