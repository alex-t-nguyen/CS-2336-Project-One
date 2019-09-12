public class Ant extends Creature {

    static final int NUM_TURNS_BREED = 3;

    public void move(Creature[][] grid, int col, int row)
    {

    }

    public void breed(Creature[][] grid, int col, int row, int numTurns)
    {
        if(numTurns == NUM_TURNS_BREED)
        {
            if(grid[col][row-1] == null)
                grid[col][row-1] = new Ant();
            else if(grid[col+1][row] == null)
                grid[col+1][row] = new Ant();
            else if(grid[col][row+1] == null)
                grid[col][row+1] = new Ant();
            else if(grid[col-1][row] == null)
                grid[col-1][row] = new Ant();
        }     
    }
}