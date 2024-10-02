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
            Command command = commands.get(c);
            if (command != null) {
                command.execute(rover);
                System.out.println("After '" + c + "': " + rover.getStatus());
            }
        }

        System.out.println("Final " + rover.getStatus());
    }

    private static Grid createGrid() {
        System.out.print("Enter grid width: ");
        int width = scanner.nextInt();
        System.out.print("Enter grid height: ");
        int height = scanner.nextInt();
        return new Grid(width, height);
    }

    private static Rover createRover(Grid grid) {
        System.out.print("Enter rover's starting X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter rover's starting Y coordinate: ");
        int y = scanner.nextInt();
        System.out.print("Enter rover's starting direction (N/E/S/W): ");
        Direction direction = Direction.valueOf(scanner.next().toUpperCase());
        return new Rover(x, y, direction, grid);
    }

    private static void addObstacles(Grid grid) {
        System.out.print("Enter number of obstacles: ");
        int numObstacles = scanner.nextInt();
        for (int i = 0; i < numObstacles; i++) {
            System.out.print("Enter obstacle " + (i + 1) + " X coordinate: ");
            int x = scanner.nextInt();
            System.out.print("Enter obstacle " + (i + 1) + " Y coordinate: ");
            int y = scanner.nextInt();
            grid.addObstacle(x, y);
        }
    }

    private static String getCommands() {
        System.out.print("Enter command sequence (M for move, L for left, R for right): ");
        return scanner.next().toUpperCase();
    }
}