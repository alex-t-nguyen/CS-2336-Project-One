// Alex Nguyen
// atn170001
import java.util.*;

public class Beetle extends Creature {
    static final int NUM_TURNS_BREED = 8; // number of turns for beetle to breed
    static final int NUM_TURNS_STARVE = 5; // number of turns for beetle to starve
    static final int MAX_GRID_SIZE  = 10; // Dimensions of game area

    
    private int hungry; // hunger counter
    private int ateCounter; // counter if beetle eats ant
    private boolean ate;    // ate status of beetle

    /**
     * Deafult Beetle constructor
     */
    Beetle() 
    {
        super();
        hungry = 0;
        ate = false; 
        ateCounter = 0; 
    }

    /**
     * Overloaded Beetle constructor
     */
    Beetle(int t, boolean m, int hunger, boolean a)
    {
        super(t, m);
        hungry = hunger;
        ate = a;
        ateCounter = 0;
    }

    /**
     * Moves Beetle
     * @param grid game area
     * @param col column position of beetle
     * @param row row position of beetle
     */
    public boolean move(Creature[][] grid, int col, int row)
    {
        Beetle temp = (Beetle)grid[row][col];
        
        int direction = getNearestOrthogonalAnt(grid, col, row);
        if(direction == 0) // Move NORTH
        {
            if(!(grid[row - 1][col] instanceof Beetle)) // If position to move to is not a beetle
            {
                grid[row][col] = null;
                if(grid[row - 1][col] instanceof Ant) // If position to move to is an ant
                {    
                    temp.ate = true;    // Eat ant
                    grid[row - 1][col] = null;
                }
                grid[row - 1][col] = temp;  // Move beetle
                grid[row - 1][col].setMoved(true); // Make sure beetle can't move again in same turn
                return true;
            }
        }
        else if(direction == 1) // EAST
        {
            if(!(grid[row][col + 1] instanceof Beetle)) // If position to move to is not a beetle
            {
                grid[row][col] = null;
                if(grid[row][col + 1] instanceof Ant)   // If position to move to is an ant
                {    
                    temp.ate = true;
                    grid[row][col + 1] = null;
                }
                grid[row][col + 1] = temp;  // Move beetle
                grid[row][col + 1].setMoved(true);  // Make sure beetle can't move again in same turn
                return true;
            }
        }
        else if(direction == 2) // SOUTH
        {
            if(!(grid[row + 1][col] instanceof Beetle)) // If position to move to is not a beetle
            {
                grid[row][col] = null;
                if(grid[row + 1][col] instanceof Ant)   // If position to move to is an ant
                {    
                    temp.ate = true;
                    grid[row + 1][col] = null;
                }
                grid[row + 1][col] = temp;  // Move beetle
                grid[row + 1][col].setMoved(true);  // Make sure beetle can't move again in same turn
                return true;
            }
        }
        else if(direction == 3) // WEST
        {
            if(!(grid[row][col - 1] instanceof Beetle)) // If position to move to is not a beetle
            {
                grid[row][col] = null;
                if(grid[row][col - 1] instanceof Ant)   // If position to move to is an ant
                {    
                    temp.ate = true;
                    grid[row][col - 1] = null;
                }
                grid[row][col - 1] = temp;  // Move beetle
                grid[row][col - 1].setMoved(true);  // Make sure beetle can't move again in same turn
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
        if(numTurns != 0 && numTurns % NUM_TURNS_BREED == 0)
        {
            if(row != 0 && grid[row - 1][col] == null)
                grid[row - 1][col] = new Beetle();
            else if(col != 9 && grid[row][col + 1] == null)
                grid[row][col + 1] = new Beetle();
            else if(row != 9 && grid[row + 1][col] == null)
                grid[row + 1][col] = new Beetle();
            else if(col != 0 && grid[row][col - 1] == null)
                grid[row][col - 1] = new Beetle();
        }
    }

    /**
     * Starves beetle
     * @param grid game area
     * @param col column position of beetle
     * @param row row position of beetle
     * @param turn turn number of beetle 
     */
    public void starve(Creature[][] grid, int col, int row, int turn)
    {
        if(turn % NUM_TURNS_STARVE == 0 && turn != 0)
        {
            grid[row][col] = null;
        }
    }
    
    /**
     * Sets hunger of beetle
     * @param hunger hunger status of beetle
     */
    public void setHunger(int hunger)
    {
        hungry = hunger;
    }

    /**
     * Gets hunger status of beetle
     * @return hunger status of beetle
     */
    public int getHunger()
    {
        return hungry;
    }

    /**
     * Sets status of beetle if it ate an ant or not
     * @param a ate status of beetle
     */
    public void setAte(boolean a)
    {
        ate = a;
    }

    /**
     * Gets status of beetle if it ate an ant or not
     * @return ate status of ant
     */
    public boolean getAte()
    {
        return ate;
    }

    /**
     * Sets ate status of beetle
     * @param a ate status of beetle
     */
    public void setAteCounter(int a)
    {
        ateCounter = a;
    }

    /**
     * Gets counter to see if beetle ate anything
     * @return number of turns since beetle ate ant
     */
    public int getAteCounter()
    {
        return ateCounter;
    }

    /**
     * Gets the direction of the nearest orthogonal ant
     * @param grid game area
     * @param col column position of beetle
     * @param row row position of beetle
     * @return direction of nearest orthogonal ant
     */
    public int getNearestOrthogonalAnt(Creature [][] grid, int col, int row)
    {
        int [] numSpacesArray = new int[4];

        int index = 0;
        int numSpacesToAnt = 1;

        if(row == 0)
            numSpacesArray[index] = -1;
        else
        {
            for(int r = row - 1; r > -1; r--)    // Checks spaces to ant and empty spaces in NORTH direction
            {
                
                if(grid[r][col] instanceof Ant)
                {
                    numSpacesArray[index] = numSpacesToAnt;
                    break;
                }
                else
                {
                    numSpacesArray[index] = -1;
                    numSpacesToAnt++;
                }
            }
        }
        
        index++;
        numSpacesToAnt = 1;

        if(col == MAX_GRID_SIZE - 1)
            numSpacesArray[index] = -1;
        else
        {
            for(int c = col + 1; c < MAX_GRID_SIZE; c++)    // Checks spaces to ant and empty spaces in EAST direction
            {
                
                if(grid[row][c] instanceof Ant)
                { 
                    numSpacesArray[index] = numSpacesToAnt;
                    break;
                }
                else
                {
                    numSpacesArray[index] = -1;
                    numSpacesToAnt++;
                }
            }
        }
        //multipleClosestAntPositions[index] = numSpacesToAnt;
        index++;
        numSpacesToAnt = 1;
     

        if(row == MAX_GRID_SIZE - 1)
            numSpacesArray[index] = -1;
        else
        {
            for(int r = row + 1; r < MAX_GRID_SIZE; r++)    // Checks spaces to ant and empty spaces in SOUTH direction
            {
               
                if(grid[r][col] instanceof Ant)
                {
                    numSpacesArray[index] = numSpacesToAnt;                   
                    break;
                }
                else
                {
                    numSpacesArray[index] = -1;
                    numSpacesToAnt++;
                }
            }
        }
        index++;
        numSpacesToAnt = 1;
        

        if(col == 0)
            numSpacesArray[index] = -1;
        else
        {
            for(int c = col - 1; c > -1; c--)    // Checks spaces to ant and empty spaces in WEST direction
            {
                
                if(grid[row][c] instanceof Ant)
                {
                    numSpacesArray[index] = numSpacesToAnt;
                    break;
                }
                else
                {
                    numSpacesArray[index] = -1;
                    numSpacesToAnt++;
                }

            }
        }
        int direction = 0;
        int numClosestAnt = 0;
        int noAntCounter = 0;
        int [] closestAntIndexes = {-1, -1, -1, -1}; // default values for direcions of closest ants
        for(int i = 0; i < numSpacesArray.length; i++)
        {
            if(numSpacesArray[i] == -1)
                noAntCounter++;
            else
                direction = i;
        }
        if(noAntCounter != 3) // If there are multiple orthogonal ants
        {
            int arrIndex = 0;
            while(arrIndex < 3 && numSpacesArray[arrIndex] == -1) // gets direction of first ant
            {
                arrIndex++;
            }
            int min = numSpacesArray[arrIndex];
            direction = arrIndex;
            for(int i = arrIndex + 1; i < numSpacesArray.length; i++)
            {
                if(numSpacesArray[i] == -1)
                    continue;
                else
                {
                    if(numSpacesArray[i] < min) // finds the closest number of spaces to orthogonal ant
                    {
                        min = numSpacesArray[i];
                        direction = i;
                    }
                }
            }
            closestAntIndexes[direction] = direction; // gets direction of closest ant
            
            for(int i = direction + 1; i < numSpacesArray.length; i++)
            {
                if(numSpacesArray[i] == min) // Checks for multiple closest ants
                {
                    closestAntIndexes[i] = i;
                    numClosestAnt++;    
                }
            }
        }
        // If no ants move towards farthest edge
        int x = 0;
        for(int i = 0; i < numSpacesArray.length; i++)
        {
            if(numSpacesArray[i] == -1)
            {
                x++;
                if(x == 4)  // If not orthogonal ants
                {
                    // Get direction of farthest edge
                    int [] spacesToEdge = {row, 9 - col, 9 - row, col};
                    
                    // CHECK FOR TIES OF FARTHEST EDGE
                    ArrayList<Integer> indexOfTies = new ArrayList<Integer>();
                    for(int y = 0; x < spacesToEdge.length; x++)
                    {
                        for(int z = 1; z < spacesToEdge.length; z++)
                        {
                            if(spacesToEdge[y] == spacesToEdge[z] && y != z)
                            {
                                indexOfTies.add(y);
                                break;
                            }
                        }
                    }
                    
                        int largest = 0;
                        for(int a = 1; a < spacesToEdge.length; a++) // looks for furthest number of spaces to edge
                        {
                            if(spacesToEdge[a] > spacesToEdge[largest])
                                largest = a;
                        }
                        for(int l = largest, p = largest + 1; p < spacesToEdge.length; p++) // checks for multiple furthest edges
                        {
                            if(spacesToEdge[l] == spacesToEdge[p])
                                break;
                            else if(p == spacesToEdge.length - 1)
                                return l;
                        }
                        
                        for(int b = 0; b < spacesToEdge.length; b++)
                        {
                            if(spacesToEdge[largest] == spacesToEdge[b]) // gets direction of furthest edges
                            {
                                spacesToEdge[b] = b;
                            }
                            else
                                spacesToEdge[b] = -1;
                        }
                        for(int c = 0; c < spacesToEdge.length; c++)
                        {
                            if(spacesToEdge[c] != -1)
                                return c;
                        }
                   
                }
            }
            else
                break;
        }
        if(numClosestAnt > 0) // if multiple closest ants
        {
            // Default value for if there is no orthogonal ant in direction
            int antNeighborsNorth = -1;
            int antNeighborsEast = -1;
            int antNeighborsSouth = -1;
            int antNeighborsWest = -1;

            if(closestAntIndexes[0] != -1) // If there is an orthogonal ant North
                antNeighborsNorth = getNumAdjacentAntNeighbors(grid, col, row - numSpacesArray[0]); // Gets number of ant neighbors or North orthogonal ant
            if(closestAntIndexes[1] != -1)  // If there is an orthogonal ant East
                antNeighborsEast = getNumAdjacentAntNeighbors(grid, col + numSpacesArray[1], row);  // Gets number of ant neighbors or East orthogonal ant
            if(closestAntIndexes[2] != -1)  // If there is an orthogonal ant South
                antNeighborsSouth = getNumAdjacentAntNeighbors(grid, col, row + numSpacesArray[2]); // Gets number of ant neighbors or South orthogonal ant
            if(closestAntIndexes[3] != -1)  // If there is an orthogonal ant West
                antNeighborsWest = getNumAdjacentAntNeighbors(grid, col - numSpacesArray[3], row);  // Gets number of ant neighbors or West orthogonal ant

            // Puts number of ant neighbors of each ant in each direction into array
            int [] antNeighbors = new int[4];
            antNeighbors[0] = antNeighborsNorth;
            antNeighbors[1] = antNeighborsEast;
            antNeighbors[2] = antNeighborsSouth;
            antNeighbors[3] = antNeighborsWest;
            
            int maxIndex = 0;
            boolean multiCloseAnts = false;   
            for(int i = 1; i < antNeighbors.length; i++) // Gets direction of ant with most neighbors
            {
                if(antNeighbors[i] == -1)
                    continue;
                else if(antNeighbors[i] > antNeighbors[maxIndex])
                    maxIndex = i;
            }
            for(int n = maxIndex + 1; n < antNeighbors.length; n++) // Checks if multiple ants have most number of neighbors
            {
                if(antNeighbors[maxIndex] == antNeighbors[n])
                {
                    multiCloseAnts = true;
                    break;
                }
            }    
            if(multiCloseAnts) // If there are multiple ants with most neighbors
            {
                if(antNeighbors[0] != -1) // Move priority North first
                {
                    if(antNeighborsNorth == antNeighborsEast || antNeighborsNorth == antNeighborsSouth || antNeighborsNorth == antNeighborsWest)
                    {                        
                        return 0; // NORTH
                    }
                }
                if(antNeighbors[1] != -1) // Move priority East second
                {
                    if(antNeighborsEast == antNeighborsSouth || antNeighborsEast == antNeighborsWest)
                    {                  
                        return 1; // EAST                    
                    }
                }
                if(antNeighbors[2] != -1) // Move priority South third
                {                   
                    if(antNeighborsSouth == antNeighborsWest)
                    {                     
                        return 2; // SOUTH                     
                    }
                }     
            }
            else    // If no tie for ant with most neighbors/ ant West has most neighbors
                return maxIndex;
        }
        else // if only a single closest ant
        {
            return direction;
        }
        return direction;
    }

    /**
     * Gets the number of adjacent ant neighbors of a desginated ant
     * @param grid game area
     * @param col column position of ant
     * @param row row position of ant  
     */
    public int getNumAdjacentAntNeighbors(Creature[][] grid, int col, int row)
    {
        int numAdjacentAnt = 0;
        for(int r = row - 1; r < row + 2; r++)  // Loops through adjacent rows
        {
            for(int c = col - 1; c < col + 2; c++)  // Loops through adjacent columns
            {
                if(c == col && r == row)    // Skips space at center
                    continue;
                else if(c < 0  || c >= MAX_GRID_SIZE)    // Checks for adjacent column out of bounds
                    continue;
                else if(r < 0 || r >= MAX_GRID_SIZE) // Checks for adjacent row out of bounds
                    continue;
                else if(grid[r][c] instanceof Ant)  // Checks if adjacent space has ant
                {
                    numAdjacentAnt++;             
                }
            }
        }
        
        return numAdjacentAnt;
    }
}