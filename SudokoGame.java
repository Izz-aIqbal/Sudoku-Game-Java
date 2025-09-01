import java.util.Scanner;

class Sudoku {
    private int[][] board;
    private static final int SIZE = 9;

    // Constructor with a sample Sudoku puzzle
    public Sudoku() {
        board = new int[][] {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
    }

    // Display the Sudoku board
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println("------+-------+------");

            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0)
                    System.out.print("| ");
                if (board[i][j] == 0)
                    System.out.print(". ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if placing num is valid
    private boolean isValid(int row, int col, int num) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num)
                return false;
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num)
                return false;
        }

        // Check 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    // Insert number into board if valid
    public boolean placeNumber(int row, int col, int num) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            if (board[row][col] == 0 && isValid(row, col, num)) {
                board[row][col] = num;
                return true;
            }
        }
        return false;
    }

    // Check if the Sudoku is completely filled
    public boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}

public class SudokuGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();

        System.out.println("=== Sudoku Game ===");
        System.out.println("Fill the Sudoku by entering row, column, and number.");
        System.out.println("Use indexes (0-8) for rows and columns.\n");

        while (!sudoku.isSolved()) {
            sudoku.displayBoard();

            System.out.print("Enter row (0-8): ");
            int row = sc.nextInt();

            System.out.print("Enter column (0-8): ");
            int col = sc.nextInt();

            System.out.print("Enter number (1-9): ");
            int num = sc.nextInt();

            if (sudoku.placeNumber(row, col, num)) {
                System.out.println("Number placed successfully!\n");
            } else {
                System.out.println("Invalid move! Try again.\n");
            }
        }

        sudoku.displayBoard();
        System.out.println("🎉 Congratulations! You solved the Sudoku!");
        sc.close();
    }
}
