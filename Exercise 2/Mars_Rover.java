import java.util.*;

// Interface for all commands
interface Command {
    void execute(Rover rover);
}

// Concrete command for moving forward
class MoveCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.move();
    }
}

// Concrete command for turning left
class TurnLeftCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

// Concrete command for turning right
class TurnRightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}

// Abstract class for grid components
abstract class GridComponent {
    protected int x;
    protected int y;

    public GridComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean isObstacle();
}

// Concrete class for empty grid space
class EmptySpace extends GridComponent {
    public EmptySpace(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isObstacle() {
        return false;
    }
}

// Concrete class for obstacles
class Obstacle extends GridComponent {
    public Obstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }
}

// Composite class for the grid
class Grid {
    private GridComponent[][] grid;
    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new GridComponent[width][height];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new EmptySpace(x, y);
            }
        }
    }

    public void addObstacle(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = new Obstacle(x, y);
        }
    }

    public boolean isObstacle(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y].isObstacle();
        }
        return true; // Treat out of bounds as an obstacle
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

// Enum for directions
enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }
}

// Rover class
class Rover {
    private int x;
    private int y;
    private Direction direction;
    private Grid grid;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
        int newX = x;
        int newY = y;

        switch (direction) {
            case NORTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH:
                newY--;
                break;
            case WEST:
                newX--;
                break;
        }

        if (!grid.isObstacle(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public String getStatus() {
        return String.format("Rover is at (%d, %d) facing %s", x, y, direction);
    }
}

// Main class to run the simulation
public class Mars_Rover {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Grid grid = createGrid();
            Rover rover = createRover(grid);
            addObstacles(grid);
            String commandSequence = getCommands();

            Map<Character, Command> commands = new HashMap<>();
            commands.put('M', new MoveCommand());
            commands.put('L', new TurnLeftCommand());
            commands.put('R', new TurnRightCommand());

            System.out.println("Initial " + rover.getStatus());

            for (char c : commandSequence.toCharArray()) {
                try {
                    Command command = Optional.ofNullable(commands.get(c))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + c));
                    command.execute(rover);
                    System.out.println("After '" + c + "': " + rover.getStatus());
                } catch (IllegalArgumentException e) {
                    System.out.println("Warning: " + e.getMessage());
                }
            }

            System.out.println("Final " + rover.getStatus());
        } catch (Exception e) {
            System.out.println("An error occurred during simulation: " + e.getMessage());
        }
    }

    private static Grid createGrid() {
        int width = getValidInput("Enter grid width: ", 1, 100);
        int height = getValidInput("Enter grid height: ", 1, 100);
        return new Grid(width, height);
    }

    private static Rover createRover(Grid grid) {
        int x = getValidInput("Enter rover's starting X coordinate: ", 0, grid.getWidth() - 1);
        int y = getValidInput("Enter rover's starting Y coordinate: ", 0, grid.getHeight() - 1);
        Direction direction = getValidDirection();
        return new Rover(x, y, direction, grid);
    }

    private static void addObstacles(Grid grid) {
        int numObstacles = getValidInput("Enter number of obstacles: ", 0, grid.getWidth() * grid.getHeight());
        for (int i = 0; i < numObstacles; i++) {
            int x = getValidInput("Enter obstacle " + (i + 1) + " X coordinate: ", 0, grid.getWidth() - 1);
            int y = getValidInput("Enter obstacle " + (i + 1) + " Y coordinate: ", 0, grid.getHeight() - 1);
            grid.addObstacle(x, y);
        }
    }

    private static String getCommands() {
        System.out.print("Enter command sequence (M for move, L for left, R for right): ");
        String commands = scanner.next().toUpperCase();
        if (!commands.matches("[MLR]+")) {
            throw new IllegalArgumentException("Invalid command sequence. Use only M, L, or R.");
        }
        return commands;
    }

    private static int getValidInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.next());
                if (value < min || value > max) {
                    throw new IllegalArgumentException("Input must be between " + min + " and " + max);
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Direction getValidDirection() {
        while (true) {
            try {
                System.out.print("Enter rover's starting direction (N/E/S/W): ");
                return Direction.valueOf(scanner.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid direction. Please enter N, E, S, or W.");
            }
        }
    }
}