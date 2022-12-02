package edu.up.cs301.pig;

import android.content.Context;

import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {
    int turn;
    int player0Score;
    int player1Score;
    int runningTotal;
    int die;

//    enum dieFace{
//        ONE(R.drawable.face1),
//        TWO(R.drawable.face2),
//        THREE(R.drawable.face3),
//        FOUR(R.drawable.face4),
//        FIVE(R.drawable.face5),
//        SIX(R.drawable.face6);
//
//        private final int value;
//
//        dieFace(int face) {
//            value = face;
//        }
//
//        public int getFace() {return value;}
//    }
    public PigGameState(){
        turn = 0;
        player0Score = 0;
        player1Score = 0;
        runningTotal = 0;
        die = 0;
    }

    public PigGameState(PigGameState previous)
    {
        turn = previous.getTurn();
        player0Score = previous.getPlayerScore(0);
        player1Score = previous.getPlayerScore(1);
        runningTotal = previous.getRunningTotal();
        die = previous.getDie();
    }

    public int getTurn() {
        return turn;
    }

    public int getPlayerScore(int pId) {
        if (pId == 0) return player0Score;
        if (pId == 1) return player1Score;
        return -1; // If we get to here, pId isn't 0 OR 1, return -1 to indicate error
    }
    public int getRunningTotal() {
        return runningTotal;
    }

    public int getDie() {
        return die;
    }

    public void setDie(int die) {
        this.die = die;
    }

    public void setPlayerScore(int score, int pId) {
        if (pId == 0) this.player0Score = score;
        if (pId == 1) this.player1Score = score;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
