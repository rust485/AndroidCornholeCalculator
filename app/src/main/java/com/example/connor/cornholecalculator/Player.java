package com.example.connor.cornholecalculator;

/**
 * Represents a player in a game of bags
 * Created by Connor on 5/6/2016.
 */
public class Player
{
    private int score;
    private String name;
    private int inHole;
    private int onBoard;

    /**
     * Constructs a new player with a given name, a score of 0, and 0 bags on the board and in
     * the hole.
     * @param name
     */
    public Player (String name)
    {
        this.name = name;
        score = 0;
        inHole = 0;
        onBoard = 0;
    }

    public String getName()
    {
        return name;
    }

    /**
     * set the number of bags that are in the hole to the given value.
     * @param inHole the given value to be set to
     */
    public void setInHole (int inHole)
    {
        this.inHole = inHole;
    }

    /**
     * set the number of bags that are on the board to the given value.
     * @param onBoard the given value to be set to
     */
    public void setOnBoard (int onBoard)
    {
        this.onBoard = onBoard;
    }

    /**
     * Set this player's current score to the given value.
     * @param score given value
     */
    public void setScore (int score)
    {
        this.score = score;
    }

    /**
     * Returns the score of this player.
     * @return score
     */
    public int getScore ()
    {
        return score;
    }

    /**
     * Calculate the number of points that this player could receive in this turn. In the hole is
     * worth 3 points and on the board is worth 1 point. Caller should ensure that inHole and
     * onBoard both add up to 4.
     * @return number of points that this player could receive in this turn.
     */
    public int calculateTurnPoints ()
    {
        if (inHole + onBoard != 4)
            throw new IllegalStateException();
        int ret = inHole*3 + onBoard;
        inHole = 0;
        onBoard = 0;

        return ret;
    }
}
