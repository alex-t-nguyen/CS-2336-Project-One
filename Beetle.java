public class Beetle extends Creature {
    static final int NUM_TURNS_BREED = 8;
    static final int NUM_TURNS_STARVE = 5;
    static final int MAX_GRID_SIZE  = 10;

    
    private boolean hungry = true;

    public void move(Creature[][] grid, int col, int row)
    {
        
    }

    public void breed(Creature[][] grid, int col, int row, int numTurns)
    {
        if(numTurns == NUM_TURNS_BREED)
        {
            if(grid[col][row-1] == null)
                grid[col][row-1] = new Beetle();
            else if(grid[col+1][row] == null)
                grid[col+1][row] = new Beetle();
            else if(grid[col][row+1] == null)
                grid[col][row+1] = new Beetle();
            else if(grid[col-1][row] == null)
                grid[col-1][row] = new Beetle();
        }
    }

    public void starve(Creature[][] grid, int col, int row, int numTurns)
    {
        if(numTurns == 5 && hungry == true)
            grid[col][row] = null;
    }
    
    public void setHunger(boolean hunger)
    {
        hungry = hunger;
    }

    public boolean getHunger()
    {
        return hungry;
    }

    public int getNearestOrthogonalAnt(Creature [][] grid, int col, int row)
    {
        int [] numSpacesArray = new int[4];
        int index = 0;
        int numSpacesToAnt = 0, numEmptySpaces = 0;
        for(int r = row - 1; r >= 0; r--)    // Checks spaces to ant and empty spaces in NORTH direction
        {
            if(grid[col][r] == null)
            {
                numEmptySpaces++;
            }
            if(grid[col][r] instanceof Ant)
                numSpacesArray[index] = numSpacesToAnt;
            else
                numSpacesToAnt++;
        }
        index++;
        numSpacesToAnt = 0;
        numEmptySpaces = 0;

        for(int c = col + 1; c <= MAX_GRID_SIZE; c++)    // Checks spaces to ant and empty spaces in EAST direction
        {
            if(grid[c][row] == null)
            {
                numEmptySpaces++;
            }
            if(grid[c][row] instanceof Ant)
                numSpacesArray[index] = numSpacesToAnt;
            else
                numSpacesToAnt++;
        }
        index++;
        numSpacesToAnt = 0;
        numEmptySpaces = 0;

        for(int r = row + 1; r <= MAX_GRID_SIZE; r++)    // Checks spaces to ant and empty spaces in SOUTH direction
        {
            if(grid[col][r] == null)
            {
                numEmptySpaces++;
            }
            if(grid[col][r] instanceof Ant)
                numSpacesArray[index] = numSpacesToAnt;
            else
                numSpacesToAnt++;
        }
        index++;
        numSpacesToAnt = 0;
        numEmptySpaces = 0;

        for(int c = col - 1; c >= 0; c--)    // Checks spaces to ant and empty spaces in SOUTH direction
        {
            if(grid[c][row] == null)
            {
                numEmptySpaces++;
            }
            if(grid[c][row] instanceof Ant)
                numSpacesArray[index] = numSpacesToAnt;
            else
                numSpacesToAnt++;
        }

        int min = numSpacesArray[0], numClosestAnt = 0;
        for(int i = 1; i < numSpacesArray.length; i++)
        {
            if(min > numSpacesArray[i])
            {
                min = numSpacesArray[i];
            }
            if(min == numSpacesArray[i])
                numClosestAnt++;
        }

        int direction = 0;
        if(numClosestAnt > 0) // if multiple closests ants
        {

        }
        else // if only a single closest ant
        {
            for(int i = 0; i < numSpacesArray.length; i++)
            {
                if(numSpacesArray[i] == min)
                    direction = i;
            }
        }
    }
}