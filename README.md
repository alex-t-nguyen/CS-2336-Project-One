# CS-2336-Project-One
Ants Vs Beetle Game
CS 2336 – PROJECT 1 – Ant Wars

Objective: Implement object-oriented programming in Java with inheritance and polymorphism.
Problem: Mumbo Jumbo (a developer located in Dallas) has begun working on a new online game called Ant
Wars. The game is a simple, casual game where players try to avoid their ant colony being destroyed by an
invading colony of beetles. The player will have an opportunity to play against an AI or against another player.
You have been assigned to help develop the AI part of the game.

Pseudocode: Your pseudocode should describe the following items
• For each function, identify the following
o Determine the parameters
o Determine the return type
o Detail the step-by-step logic that the function will perform
• Functions
o Ant class
  ▪ Breed
  ▪ Move
o Beetle class
  ▪ Breed
  ▪ Move
  ▪ Starve
o Main class
  ▪ Main
  ▪ Any additional functions that you plan to create

Classes
• Base class
  o Name file Creature.java
  o Creature
▪ Abstract class (-5 points if not)
▪ Methods
• Move (abstract) (-5 points if not)
• Breed (abstract) (-5 points if not)
• Derived Classes
o Ant
  ▪ Name file Ant.java
  ▪ Methods
  • Move
  • Breed
o Beetle
  ▪ Name file Beetle.java
  ▪ Methods
  • Move
  • Breed
  • Starve
• You can add any member variables necessary for each class
• The main function must be in a file named Main.java

Details:
• The game will be played on a 10 x 10 grid
  o You decide how the grid will be stored in memory
• Beetles will move before the ants
• A file will be used to populate the grid
  o See sample file
  o a = ant
  o B = beetle
• Grid traversal should be by column first then row
  o Creatures to the left perform actions before creatures to the right
• User will specify number of turns to watch
  o There will not be any user interaction in the game
  o The goal is to test the AI, so the game will play against itself
• All movement is orthogonal (N, S, E, W)
  o Movement is limited to one space per turn
• Movement happens before breeding and starving
• A beetle will eat an ant if it moves into the same space as the ant

Turn Order:
1. Beetles move
2. Ants move
3. Beetles starve
4. Ants breed
5. Beetles breed

Ant Details:
• Move
  o Each ant will move in the opposite direction of the nearest orthogonal beetle
  o If no orthogonal beetle, ant stands still
  o If multiple beetles are nearest, prioritize movement
▪ Move ant in direction of no beetle if possible
▪ If beetles in all directions, move toward farthest beetle
▪ If there are multiple possible directions to move (either multiple clear pathways or
multiple beetles furthest away) use the following movement priority: N, E, S, W
  o Ants cannot move to an occupied space
▪ If space moving to is occupied, no movement happens
  o Cannot move off grid
• Breed
  o If ant survives 3 turns, it breeds
  o Add ant in adjacent orthogonal space
▪ Start with north space and check clockwise around space until empty orthogonal space
found
▪ If no empty spaces, no breeding
  o Ant may not breed again unless it survives another 3 turns
  
Beetle Details:
• Move
  o Move toward nearest orthogonal ant
  o If multiple ants are nearest prioritize movement
▪ Move toward ant with most adjacent ant neighbors (orthogonal and diagonal)
▪ If still tied, move toward ant with most ant neighbors using the following priority: N, E, S, W
  o If no ant, move toward farthest edge
▪ If there is a tie for farthest edge, use the following priority: N, E, S W
• Breed
  o If beetle survives for 8 turns, it breeds
  o Use breeding algorithm for ants
  o Beetle cannot breed again unless it survives another 8 turns
• Starve
  o If a beetle does not eat an ant in 5 turns, it dies
  
User Interface: The user will be prompted for the following information in the order listed
• Initial grid filename
• Character to represent ant in output
• Character to represent beetle in output
• Number of turns

Input:
• The initial grid will be populated from file input.
• Prompt the user for the input filename
  o This would normally be hardcoded in an application, but zyLabs requires a filename for multiple
  test files
• There will be no need for input validation.
• Input sequence
  o Input file
  o Ant character
  o Beetle character
  o Number of turns
  
Output:
• All output will be sent to the console.
• Print the current state of the grid to the console window for each turn.
  o Display the turn header followed by a blank line
▪ TURN<space><turn number>
  o Display each row of the grid followed by a newline
▪ Each ant and beetle is represented by the user-defined character
▪ An empty cell in the grid is represented with a space
  o Display a blank line after the last row of the grid
