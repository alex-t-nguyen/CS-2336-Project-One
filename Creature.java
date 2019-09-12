abstract class Creature {

    abstract void move(Creature[][] grid, int col, int row);

    abstract void breed(Creature [][] grid, int col, int row, int numTurns);
}