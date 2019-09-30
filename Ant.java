// Alex Nguyen
// atn170001
public class Ant extends Creature {

    static final int NUM_ROWS = 10, NUM_COLUMNS = 10; // Dimensions of game area
    static final int NUM_TURNS_BREED = 3; // number of turns for ants to breed

    /**
     * Default constructor of Ant
     */
    Ant()
    {
        super();
    }

    /**
     * Overloaded constructor of Ant
     * @param t turn number of ant
     * @param m moved status of ant
     */
    Ant(int t, boolean m)
    {
        super(t, m);
    }

    /**
     * Moves Ant
     * @param grid 2d array of game area
     * @param col column position of ant
     * @param row row position of ant
     */
    public boolean move(Creature[][] grid, int col, int row) {
        //Check north direction
        int numEmptySpace = 0, numSpaces = 0;
        int [] emptySpaces = new int[4];
        boolean isBeetle = false;
        int [] beetleSpaces = new int[4];
        boolean [] orthoBeetles = new boolean[4];
        if(row == 0)
        {
            isBeetle = false;
            numSpaces = -1;
        }
        else
        {
            for(int b = row - 1; b >= 0; b--)   // Checks if beetle in north direction
            {
                if(!(grid[b][col] instanceof Beetle))
                {
                    numSpaces++;
                    isBeetle = false;
                    if(b == 0 && !isBeetle)
                        numSpaces = -1;
                }
                else
                {
                    isBeetle = true;
                    break;
                }
            }
        }
        beetleSpaces[0] = numSpaces;    // Number of spaces until beetle NORTH direction
        orthoBeetles[0] = isBeetle;
        for(int i = row - 1; i >= 0; i--)
        {
            if(!(grid[i][col] instanceof Ant) && (!(grid[i][col] instanceof Beetle)))
            {
                numEmptySpace++;       
            }  
            else
                break;
        }
        emptySpaces[0] = numEmptySpace;
        numEmptySpace = 0;

        if(col == NUM_COLUMNS - 1)
        {
            isBeetle = false;
            numSpaces = -1;
        }
        else
        {
            numSpaces = 0;
            for(int b = col + 1; b < NUM_COLUMNS; b++)   // Checks if beetle in east direction
            {
                if(!(grid[row][b] instanceof Beetle))
                {
                    numSpaces++;
                    isBeetle = false;
                    if(b == NUM_COLUMNS - 1 && !isBeetle)
                        numSpaces = -1;
                }
                else
                {
                    isBeetle = true;
                    break;
                }
            }
        }
        beetleSpaces[1] = numSpaces;    // Number of spaces until beetle EAST direction
        orthoBeetles[1] = isBeetle;
        for(int i = col + 1; i < NUM_COLUMNS; i++)
        {
            if(!(grid[row][i] instanceof Ant) && (!(grid[row][i] instanceof Beetle)))
            {
                numEmptySpace++;         
            }
            else
                break;
        }
        emptySpaces[1] = numEmptySpace;
        numEmptySpace = 0;

        if(row == NUM_ROWS - 1)
        {
            isBeetle = false;
            numSpaces = -1;
        }
        else
        {
            numSpaces = 0;
            for(int b = row + 1; b < NUM_ROWS; b++)   // Checks if beetle in south direction
            {
                if(!(grid[b][col] instanceof Beetle))
                {
                    numSpaces++;
                    isBeetle = false;
                    if(b == NUM_ROWS - 1 && !isBeetle)
                        numSpaces = -1;
                }
                else
                {
                    isBeetle = true;
                    break;
                }
            }
        }
        beetleSpaces[2] = numSpaces;    // Number of spaces until beetle SOUTH direction
        orthoBeetles[2] = isBeetle;
        for(int i = row + 1; i < NUM_ROWS; i++)
        {
            if(!(grid[i][col] instanceof Ant) && (!(grid[i][col] instanceof Beetle)))
            {
                numEmptySpace++;         
            }
            else
                break;
        }
        emptySpaces[2] = numEmptySpace;
        numEmptySpace = 0;

        if(col == 0)
        {
            isBeetle = false;
            numSpaces = -1;
        }
        else
        {
            numSpaces = 0;
            for(int b = col - 1; b >= 0; b--)   // Checks if beetle in west direction
            {
                if(!(grid[row][b] instanceof Beetle))
                {
                    numSpaces++;
                    isBeetle = false;
                    if(b == 0 && !isBeetle)
                        numSpaces = -1;
                }
                else
                {
                    isBeetle = true;
                    break;
                }
            }
        }
        //System.out.println("numSpaces: " + numSpaces);
        beetleSpaces[3] = numSpaces;    // Number of spaces until beetle WEST direction
        orthoBeetles[3] = isBeetle;
        for(int i = col - 1; i >= 0; i--)
        {
            if(!(grid[row][i] instanceof Ant) && (!(grid[row][i] instanceof Beetle)))
            {
                numEmptySpace++;     
            }  
            else
                break;  
        }
        emptySpaces[3] = numEmptySpace;

        for(int i = 0; i < 4; i++) // Check for orthogonal beetles
        {
            if(orthoBeetles[i])
                break;
            if(i == 3)  // If no orthogonal beetles, ant stands still
                return false;
        }
        int closestBeetleDirection = getClosestBeetle(orthoBeetles, beetleSpaces); // Gets direction of closest beetle
        Ant temp = (Ant)grid[row][col];
        if(closestBeetleDirection == 0) // Move ant North
        {
            if(emptySpaces[0] == 0) // If space to move is occupied, no movement happens
                return false;
            else if(row - 1 >= 0)
            {
                grid[row][col] = null;
                grid[row - 1][col] = temp;
                grid[row - 1][col].setMoved(true);
                return true;
            }
        }
        else if(closestBeetleDirection == 1) // Move ant East 
        {
            if(emptySpaces[1] == 0)
                return false;
            else if(col + 1 < 10)
            {
                grid[row][col] = null;
                grid[row][col + 1] = temp;
                grid[row][col + 1].setMoved(true);
                return true;
            }
        }
        else if(closestBeetleDirection == 2)    // Move ant South
        {
            if(emptySpaces[2] == 0)
                return false;
            else if(row + 1 < 10)
            {
                grid[row][col] = null;
                grid[row + 1][col] = temp;
                grid[row + 1][col].setMoved(true);
                return true;
            }
        }
        else if(closestBeetleDirection == 3)    // Move ant West
        {
            if(emptySpaces[3] == 0)
                return false;
            else if(col - 1 >= 0)
            {
                grid[row][col] = null;
                grid[row][col - 1] = temp;
                grid[row][col - 1].setMoved(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Breeds beetle
     * @param grid game area
     * @param col column position of beetle
     * @param row row position of beetle
     * @param numTurns turn number of beetle
     */
    public void breed(Creature[][] grid, int col, int row, int numTurns)
    {
        if(numTurns % NUM_TURNS_BREED == 0) // Breeds beetle every 5 turns
        {
            if(row != 0 && grid[row-1][col] == null) // Breed North if possible
                grid[row-1][col] = new Ant();
            else if(col != 9 && grid[row][col+1] == null)   // Breed East if possible
                grid[row][col+1] = new Ant();
            else if (row != 9 && grid[row+1][col] == null)  // Breed South if possible
                grid[row+1][col] = new Ant();
            else if(col != 0 && grid[row][col-1] == null)   // Breed West if possible
                grid[row][col-1] = new Ant();
            else
                return;
        }     
    }

    /**
     * Gets direction to move from closest beetle
     * @param orthoBeetles array determining if there are beetles in each direction
     * @param beetleSpaces array of number of spaces to beetle in each direction
     * @return direction for ant to move
     */
    public int getClosestBeetle(boolean [] orthoBeetles, int [] beetleSpaces)
    {
        int index = 0;
        while(beetleSpaces[index] == -1)
        {
            index++;
        }
        if(index == 4)
            index--;
        int minSpaces = beetleSpaces[index];
        int direction = index;
        for(int i = index + 1; i < beetleSpaces.length; i++)
        {
            if(beetleSpaces[i] == -1)
                continue;
            else
            {
                if(beetleSpaces[i] < minSpaces)
                {
                    minSpaces = beetleSpaces[i];
                    direction = i;
                }
            }
        }
        for(int i = direction + 1; i < beetleSpaces.length; i++)
        {
            if(minSpaces == beetleSpaces[i])
            {
                // Call function for multiple nearest beetles
                return ifMultipleNearestBeetle(orthoBeetles, beetleSpaces);
            }
        }
        
        if(direction == 0) // If closest beetle is North move South
            return 2;
        else if(direction == 1) // If closest beetle is East move West
            return 3;
        else if(direction == 2) // If closest beetle is South move North
            return 0;
        else// if(direction == 3) //If closest beetle is West move East
            return 1;
        
    }

    /**
     * Gets direction to move to if there are multiple closest beetles
     * @param orthoBeetles array determining if there are beetles in each direction
     * @param beetleSpaces array of number of spaces to beetle in each direction
     * @return direction for ant to move
     */
    public int ifMultipleNearestBeetle(boolean [] orthoBeetles, int [] beetleSpaces)
    {
        int multiplePathsCounter = 0;
        for(int i = 0; i < orthoBeetles.length; i++)
        {
            if(!orthoBeetles[i])    // Checks for clear pathways (no beetles)
                multiplePathsCounter++;
        }
        int[] clearPaths = new int [multiplePathsCounter];
        int clearPathsIndex = 0;
        for(int i = 0; i < orthoBeetles.length; i++)
        {
            if(!orthoBeetles[i])    // Checks for clear pathways (no beetles)
            {  
                clearPaths[clearPathsIndex] = i;
                clearPathsIndex++;
            }
        }

        int max = beetleSpaces[0];
        int furthestIndex = 0;
        for(int i = 1; i < beetleSpaces.length; i++)
        {
            if(max < beetleSpaces[i])
            {
                max = beetleSpaces[i];
                furthestIndex = i;
            }
        }
        int counter = 0;
        for(int i = furthestIndex; i < beetleSpaces.length; i++)
        {
            if(max == beetleSpaces[i])
                counter++;
        }
        int [] furthestDirections = new int [counter];
        int index = 0;
        for(int i = 0; i < beetleSpaces.length; i++) // Gets indexes of furthest beetles of equal distance
        {
            if(max == beetleSpaces[i])
            {   
                furthestDirections[index] = i;
                index++;
            }
        }

        if(multiplePathsCounter >= 1) // If there is a single clear pathway or multiple clear pathways
        {
            return clearPaths[0];
        }
        if(multiplePathsCounter < 1) // If ant is surrounded with unequal distances for each beetle move towards furthest beetle
        {
            return furthestIndex;
        }
        else// if(multiplePathsCounter < 1)// If multiple beetles furthest away
        {  
            return furthestDirections[0];
        }
    }
}
