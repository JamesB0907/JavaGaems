import java.util.Random;
import java.util.Scanner;

public class JavaGame {

    public static void main(String[] args) {
        char[][] map = generateMap(20, 10); // Create a 20x10 map
        int playerX = 1;
        int playerY = 1;
        boolean gameOver = false;

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printMap(map);
            System.out.print("Enter a direction (w/a/s/d): ");
            char input = scanner.next().charAt(0);

            switch (input) {
                case 'w':
                    if (isValidMove(map, playerX, playerY - 1)) {
                        playerY--;
                    }
                    break;
                case 'a':
                    if (isValidMove(map, playerX - 1, playerY)) {
                        playerX--;
                    }
                    break;
                case 's':
                    if (isValidMove(map, playerX, playerY + 1)) {
                        playerY++;
                    }
                    break;
                case 'd':
                    if (isValidMove(map, playerX + 1, playerY)) {
                        playerX++;
                    }
                    break;
                default:
                    System.out.println("Invalid input. Use 'w', 'a', 's', or 'd' to move.");
            }

            if (map[playerY][playerX] == 'E') {
                System.out.println("You found the exit! You win!");
                gameOver = true;
            }
        }

        scanner.close();
    }

    // Generate a random map with walls (X), an exit (E), and an empty space (.)
    private static char[][] generateMap(int width, int height) {
        char[][] map = new char[height][width];
        Random random = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    map[y][x] = 'X'; // Create walls around the map
                } else {
                    map[y][x] = random.nextDouble() < 0.1 ? 'X' : '.'; // 10% chance of a wall
                }
            }
        }

        // Place an exit randomly
        int exitX, exitY;
        do {
            exitX = random.nextInt(width);
            exitY = random.nextInt(height);
        } while (map[exitY][exitX] == 'X');

        map[exitY][exitX] = 'E';

        return map;
    }

    // Print the map to the console
    private static void printMap(char[][] map) {
        for (char[] row : map) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    // Check if a move is valid (within the map boundaries and not a wall)
    private static boolean isValidMove(char[][] map, int x, int y) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length && map[y][x] != 'X';
    }
}
