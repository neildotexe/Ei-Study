# Mars Rover Simulation

## Overview
This Java application simulates a Mars Rover navigating a grid-based terrain. The rover can move forward, turn left, and turn right based on user commands. The simulation includes obstacle detection and boundary checking.

## Features
- Interactive user input for grid size, rover starting position, obstacles, and command sequence
- Command-based movement system (Move, Turn Left, Turn Right)
- Obstacle detection and avoidance
- Grid boundary enforcement
- Step-by-step output of rover movements

## Requirements
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

## How to Run
1. Compile the Java files:
   ```
   javac *.java
   ```
2. Run the MarsRoverSimulation class:
   ```
   java Mars_Rover
   ```
3. Follow the prompts to input:
   - Grid dimensions
   - Rover's starting position and direction
   - Number and positions of obstacles
   - Command sequence for the rover

## Input Format
- Grid size: Two positive integers for width and height
- Rover position: Two non-negative integers within grid bounds
- Rover direction: Single character (N, E, S, W)
- Obstacles: Number of obstacles followed by their coordinates
- Commands: String containing only M (move), L (turn left), R (turn right)

## Example Usage
```
Enter grid width: 10
Enter grid height: 10
Enter rover's starting X coordinate: 0
Enter rover's starting Y coordinate: 0
Enter rover's starting direction (N/E/S/W): N
Enter number of obstacles: 2
Enter obstacle 1 X coordinate: 2
Enter obstacle 1 Y coordinate: 2
Enter obstacle 2 X coordinate: 3
Enter obstacle 2 Y coordinate: 5
Enter command sequence (M for move, L for left, R for right): MMRMLM
```

## Project Structure
- `MarsRoverSimulation.java`: Main class containing the simulation logic and user interface
- `Rover.java`: Represents the Mars Rover, handling movement and direction
- `Grid.java`: Represents the terrain, including obstacles
- `Direction.java`: Enum representing cardinal directions
- `Command.java`: Interface for rover commands
- `MoveCommand.java`, `TurnLeftCommand.java`, `TurnRightCommand.java`: Concrete command classes

## Design Patterns Used
- Command Pattern: For encapsulating rover actions
- Composite Pattern: For representing the grid and its components
- Enum State Pattern: For handling directions and rotations

## Future Improvements
- Graphical user interface
- Saving and loading simulation states
- More complex terrain features
- Multiple rovers

## Contributing
Contributions to improve the simulation are welcome. Please fork the repository and submit a pull request with your changes.

## License
This project is open source and available under the [MIT License](https://opensource.org/licenses/MIT).
