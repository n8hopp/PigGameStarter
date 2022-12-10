package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {
    PigGameState gameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        gameState = new PigGameState();

    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getTurn();
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction)
        {
            int run = gameState.getRunningTotal();
            int scor = gameState.getPlayerScore(gameState.getTurn());
            gameState.setPlayerScore(scor+run, gameState.getTurn());
            gameState.setRunningTotal(0);
            gameState.setTurn((gameState.getTurn() + 1) % 2);
            return true;
        }
        if(action instanceof PigRollAction)
        {
            Random random = new Random();
            int dieRoll = random.nextInt(5);
            dieRoll++;
            gameState.setDie(dieRoll);
            if(dieRoll != 1)
            {
                gameState.setRunningTotal(gameState.getRunningTotal() + dieRoll);
            }
            else
            {
                gameState.setRunningTotal(0);
                gameState.setTurn((gameState.getTurn() + 1) % 2);
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copy = new PigGameState(gameState);
        p.sendInfo(copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        for(int i = 0; i < 2; i++)
        {
            int score = gameState.getPlayerScore(i);
            if (score >= 50)
            {
                return "Player " + i + " won with " + score + " points!";
            }
        }
        return null;
    }

}// class PigLocalGame
