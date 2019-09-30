// Alex Nguyen
// atn170001

abstract class Creature {

    private int turn;   // turn number of creature
    private boolean moved; // moved status of creature

    /**
     * Default Constructor
     */
    public Creature()
    {
        turn = 0;
        moved = false;
    }

    /**
     * Overloaded Constructor
     * @param t turn of creature
     * @param m boolean to determine if creature moved
     */
    public Creature(int t, boolean m)
    {
        turn = t;
        moved = m;
    }

    /**
     * Gets the number of turns creature has gone through
     * @return number of turns
     */
    public int getTurn()
    {
        return turn;
    }

    /**
     * Sets the number of turns creature has gone through
     * @param t turn of creature
     */
    public void setTurn(int t)
    {
        turn = t;
    }

    /**
     * Gets boolean if creature has moved or not
     * @return boolean if creature moved
     */
    public boolean getMoved()
    {
        return moved;
    }

    /**
     * Sets the moved status of creature
     * @param move moved status of creature
     */
    public void setMoved(boolean move)
    {
        moved = move;
    }

    /**
     * Abstract move method of creature
     * @param grid game area
     * @param col column position of creature
     * @param row row position of creature
     */
    abstract boolean move(Creature[][] grid, int col, int row);

    /**
     * Abstract breed method of creature
     * @param grid 2d array of game area
     * @param col column position of creature
     * @param row row position of creature
     * @param numTurns number of turns of creature
     */
    abstract void breed(Creature [][] grid, int col, int row, int numTurns);
}