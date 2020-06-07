package com.example.connor.cornholecalculator;

import java.util.ArrayList;

/**
 * Represents a game of bags with 2 players/teams Created by Connor on 5/6/2016.
 */
public class BagsGame
{
    private int playTo;
    private int resetTo;
    private boolean isOver;
    private ArrayList<Player> players;


    public final int DEFAULT_MAX = 21;
    public final int DEFAULT_RESET = 15;

    // possibly add in a point history to see who got how many points in recent turns.
    //    private HashMap<Player, Integer> pointHistory;

    /**
     * Constructs a new game of bags with a Default score limit and a default score to reset to if
     * the max score is exceeded.
     */
    public BagsGame ()
    {
        playTo = DEFAULT_MAX;
        resetTo = DEFAULT_RESET;
        isOver = false;
        players = new ArrayList<> ();
    }

    /**
     * Constructs a new game of bags with a score limit and a reset to. If the score limit is
     * reached, the game is won, if the score limit is passed, the player that passed goes back to
     * <code>resetTo</code>.
     *
     * @param playTo  score needed to Win
     * @param resetTo score that a <code>Player</code> goes back to if they pass the maximum score
     */
    public BagsGame (int playTo, int resetTo)
    {
        this.playTo = playTo;
        this.resetTo = resetTo;
        isOver = false;
        players = new ArrayList<> ();
    }

    /**
     * Returns a list of all the players in this game
     *
     * @return list of all the players / teams in this game
     */
    public ArrayList<Player> getPlayers ()
    {
        return players;
    }

    public void addPlayer (Player p)
    {
        players.add (p);
    }

    public void addAllPlayers (ArrayList<String> players)
    {
        for (String pName : players)
        {
            Player p = new Player (pName);
            addPlayer (p);
        }
    }

    /**
     * Returns true if this game is over, false otherwise.
     *
     * @return if this game is over or not
     */
    public boolean isOver ()
    {
        return isOver;
    }

    /**
     * Simulates one 'turn' in the game, where a turn is finished after each player has thrown all
     * of their bags. This method counts up the amount of bags for each player, subtracting them
     * from each other and the player with the most points gets the points for that round.
     */
    public void calculateScore ()
    {
        if (! isOver ())
        {
            Player p1 = players.get (0);
            Player p2 = players.get (1);
            int playerOnePoints = p1.calculateTurnPoints ();
            int playerTwoPoints = p2.calculateTurnPoints ();

            if (playerOnePoints > playerTwoPoints)
            {
                p1.setScore (p1.getScore () + playerOnePoints - playerTwoPoints);
                checkScore (p1);
            }
            else if (playerTwoPoints > playerOnePoints)
            {
                p2.setScore (p2.getScore () + playerTwoPoints - playerOnePoints);
                checkScore (p2);
            }
            // if neither of these it is a wash and neither player gets points.
        }
    }

    /**
     * Checks to see if the current players score went over the max score or if the player is at the
     * max score, if at the max score, the game is over and that player wins, if over the max score,
     * the players score is reset to <code>resetTo</code>.
     *
     * @param p given player to check the score of
     */
    private void checkScore (Player p)
    {
        if (p.getScore () > playTo)
            p.setScore (resetTo);
        else if (p.getScore () == playTo)
            isOver = true;
    }
}
