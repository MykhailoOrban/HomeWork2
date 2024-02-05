import java.util.Scanner;

public class Game {

    private static final int BOARD_SIZE = 9;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int MIN_BOX_NUMBER = 1;
    private static final int MAX_BOX_NUMBER = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = initializeBoard();
        boolean isBoardEmpty = false;
        int gameResult = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            displayBoard(board);

            if (isGameOver(board)) {
                displayResultMessage(gameResult);
                break;
            }

            if (!isBoardEmpty) {
                clearBoard(board);
                isBoardEmpty = true;
            }

            playerMove(board, scanner);

            if (isWinner(board, PLAYER_X)) {
                gameResult = 1;
                break;
            }

            if (!isBoxAvailable(board)) {
                gameResult = 3;
                break;
            }

            computerMove(board);

            if (isWinner(board, PLAYER_O)) {
                gameResult = 2;
                break;
            }
        }
    }

    private static void displayBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static char[] initializeBoard() {
        char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return board;
    }

    private static void clearBoard(char[] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = ' ';
        }
    }

    private static void playerMove(char[] board, Scanner scanner) {
        int input;
        while (true) {
            System.out.println("Enter your move (1-9): ");
            input = scanner.nextInt();
            if (isValidMove(input, board)) {
                board[input - 1] = PLAYER_X;
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private static boolean isValidMove(int input, char[] board) {
        return input >= MIN_BOX_NUMBER && input <= MAX_BOX_NUMBER && board[input - 1] == ' ';
    }

    private static void computerMove(char[] board) {
        int randomBox;
        while (true) {
            randomBox = (int) (Math.random() * BOARD_SIZE) + MIN_BOX_NUMBER;
            if (board[randomBox - 1] == ' ') {
                board[randomBox - 1] = PLAYER_O;
                break;
            }
        }
    }

    private static boolean isWinner(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private static boolean isBoxAvailable(char[] board) {
        for (char box : board) {
            if (box == ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean isGameOver(char[] board) {
        return isWinner(board, PLAYER_X) || isWinner(board, PLAYER_O) || !isBoxAvailable(board);
    }

    private static void displayResultMessage(int result) {
        switch (result) {
            case 1:
                System.out.println("You won the game!\nThanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nThanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nThanks for playing!");
                break;
            default:
                System.out.println("Unknown game result. Thanks for playing!");
                break;
        }
    }
}