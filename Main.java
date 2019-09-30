// Alex Nguyen
// atn170001

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static final int MAX_GRID_SIZE = 10;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();
        char antChar = scan.next().charAt(0);
        char beetleChar = scan.next().charAt(0);
        int numTurns = scan.nextInt();
        
        // Create game area
        Creature[][] grid = new Creature[MAX_GRID_SIZE][MAX_GRID_SIZE];
        copyArray(readFile(filename), grid);    // Put all ants and beetles into game from file
        
        for(int row = 0; row < MAX_GRID_SIZE; row++)
        {
            for(int col = 0; col < MAX_GRID_SIZE; col++)
            {
                if(grid[row][col] instanceof Beetle || grid[row][col] instanceof Ant)   // Sets the starting turn of the beetle or ant to 0
                {
                    (grid[row][col]).setTurn(0);
                }
            }
        }
        for(int turnCounter = 1; turnCounter <= numTurns; turnCounter++) // Loop through grid to move beetles
        {
        
            for(int col = 0; col < MAX_GRID_SIZE; col++)
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {
                    if(grid[row][col] instanceof Beetle && (!(grid[row][col].getMoved())))    // Beetles move
                    {
                        grid[row][col].move(grid, col, row);
                    }                 
                }
            }

            for(int col = 0; col < MAX_GRID_SIZE; col++) // Loop through grid to move ants
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {
                    if(grid[row][col] instanceof Ant && (!(grid[row][col].getMoved())))  // Ants move
                    {
                        grid[row][col].move(grid, col, row);
                    }
                }
            }
            
            // CHANGE TURNS
            for(int col = 0; col < MAX_GRID_SIZE; col++)
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {
                    if(grid[row][col] instanceof Beetle || grid[row][col] instanceof Ant)
                    {
                        if(grid[row][col].getTurn() != 0)
                            (grid[row][col]).setTurn((grid[row][col].getTurn()) + 1); // Increment turn of creature in game afer moving
                        else if(grid[row][col].getTurn() == 0)
                            (grid[row][col]).setTurn(1);
                        grid[row][col].setMoved(false);
                    }
                    if(grid[row][col] instanceof Beetle)
                    {
                        if(((Beetle)grid[row][col]).getAte()) // Check if beetle ate an ant
                        {
                            ((Beetle)grid[row][col]).setHunger(0); // Reset beetle hunger counter
                            ((Beetle)grid[row][col]).setAte(false); // Reset beetle ate status
                           //((Beetle)grid[row][col]).setAteCounter(0);
                        }
                        else
                        {
                            int h = ((Beetle)grid[row][col]).getHunger() + 1;
                            //System.out.println("Test: " + h);
                            ((Beetle)grid[row][col]).setHunger(h);  // Increase turn beetle hasn't eaten by 1 every turn
                           // ((Beetle)grid[row][col]).setAteCounter(((Beetle)grid[row][col]).getAteCounter() + 1);
                        }

                        /*Starve Beetle - 
                        1) Counter that goes up to 5, increment each turn
                        2) If Space beetle moves to was an Ant, reset counter to 0
                        3) If Counter reaches 5, Beetle starves, change element at Beetle's position to null
                        */
                    }
                }
            }
            for(int col = 0; col < MAX_GRID_SIZE; col++) // Loop through grid to starve beetles
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {
                    if(grid[row][col] instanceof Beetle && ((Beetle)grid[row][col]).getHunger() == 5)    // Beetles starve
                        ((Beetle)grid[row][col]).starve(grid, col, row, ((Beetle)grid[row][col]).getHunger());
                }
            }

            for(int col = 0; col < MAX_GRID_SIZE; col++) // Loop through grid to breed ants
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {       
                    if(grid[row][col] instanceof Ant && (grid[row][col]).getTurn() != 0 && (grid[row][col]).getTurn() % 3 == 0) // Ants breed
                        ((Ant)grid[row][col]).breed(grid, col, row, ((Ant)grid[row][col]).getTurn());
                }
            }

            for(int col = 0; col < MAX_GRID_SIZE; col++) // Loop through grid to breed beetles
            {
                for(int row = 0; row < MAX_GRID_SIZE; row++)
                {
                    if(grid[row][col] instanceof Beetle)    // Beetles breed
                        ((Beetle)grid[row][col]).breed(grid, col, row, ((Beetle)grid[row][col]).getTurn());
                }
            }
            
            displayGrid(grid, turnCounter, antChar, beetleChar);           
        }
        
        scan.close();
    }

    /**
     * Displays game
     * @param grid game area
     * @param turn turn number in game
     * @param ant character to represent ants
     * @param beetle character to represent beetles
     */
    public static void displayGrid(Creature[][] grid, int turn, char ant, char beetle)
    {
        System.out.println("TURN " + turn);
        for(int row = 0; row < MAX_GRID_SIZE; row++)
        {
            for(int col = 0; col < MAX_GRID_SIZE; col++)
            {
                if(col == MAX_GRID_SIZE - 1)
                {
                    if(grid[row][col] == null)
                        System.out.println(" ");
                    else if(grid[row][col] instanceof Ant)
                        System.out.println(ant);
                    else if(grid[row][col] instanceof Beetle)
                        System.out.println(beetle);
                }
                else
                {
                    if(grid[row][col] == null)
                        System.out.print(" ");
                    else if(grid[row][col] instanceof Ant)
                        System.out.print(ant);
                    else if(grid[row][col] instanceof Beetle)
                        System.out.print(beetle);
                }
            }
        }
        System.out.println();
    }

    /**
     * Reads file
     * @param filename name of file with game starting positions
     */
    public static Creature[][] readFile(String filename) throws FileNotFoundException, IOException
    {
        Scanner sc =  new Scanner(new BufferedReader(new FileReader(filename)));
        //ArrayList<String> fileLines = new ArrayList<String>();
        char[][] myArray = new char[MAX_GRID_SIZE][MAX_GRID_SIZE];
        String line = null;
        while(sc.hasNextLine())
        {
            for(int row = 0; row < myArray.length; row++)
            {
                line = sc.nextLine();
                //fileLines.add(line);
                char [] lines = line.toCharArray(); // Put read in line into char array
                //lines = fileLines.toArray(lines); 
                /*for(int i = 0; i < lines.length; i++)
                    System.out.print(lines[i] + "/"); 
                */           
                for(int col = 0; col < MAX_GRID_SIZE; col++)
                {
                    if(lines[col] == ' ')   // Check for space
                        myArray[row][col] = 0;
                    else if(lines[col] == 'a') // Check if read in creature is an ant
                        myArray[row][col] = 'a';
                    else if(lines[col] == 'B')  // Check if read in creature is a beetle
                        myArray[row][col] = 'B';
                }
            }
        }
        sc.close();
        
        // Create game area
        Creature [][] gameGrid = new Creature[MAX_GRID_SIZE][MAX_GRID_SIZE];
        for(int r = 0; r < MAX_GRID_SIZE; r++)
        {
            for(int c = 0; c < MAX_GRID_SIZE; c++)
            {
                if(myArray[r][c] == 0)
                    gameGrid[r][c] = null;
                else if(myArray[r][c] == 'a')
                    gameGrid[r][c] = new Ant();
                else if(myArray[r][c] == 'B')
                    gameGrid[r][c] = new Beetle();
            }
        }
        return gameGrid;
    }

    /**
     * copies game starting positions from file to game area
     * @param original
     * @param copy
     */
    public static void copyArray(Creature[][] original, Creature[][] copy)
    {
        for(int i = 0; i < original.length; i++)
        {
            for(int j = 0; j < original[i].length; j++)
            {
                copy[i][j] = original[i][j];
            }
        }
    }
}