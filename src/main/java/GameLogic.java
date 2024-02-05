import java.util.Scanner;

public class GameLogic {

    private final Scanner scanner;
    private final char[] board;
    private static final int BOARD_SIZE = 9;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final int MIN_BOX_NUMBER = 1;
    private static final int MAX_BOX_NUMBER = 9;

    public GameLogic(Scanner scanner) {
        this.scanner = scanner;
        this.board = initializeBoard();
    }

    public void playGame() {
        boolean isBoardEmpty = false;
        int gameResult = 0;

        while (true) {
            displayBoard();

            if (isGameOver()) {
                displayResultMessage(gameResult);
                break;
            }

            if (!isBoardEmpty) {
                clearBoard();
                isBoardEmpty = true;
            }

            playerMove();

            if (isWinner(PLAYER_X)) {
                gameResult = 1;
                break;
            }

            if (!isBoxAvailable()) {
                gameResult = 3;
                break;
            }

            computerMove();

            if (isWinner(PLAYER_O)) {
                gameResult = 2;
                break;
            }
        }
    }

    private void displayBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private char[] initializeBoard() {
        char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        return board;
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = ' ';
        }
    }

    private void playerMove() {
        int input;
        while (true) {
            System.out.println("Enter your move (1-9): ");
            input = scanner.nextInt();
            if (isValidMove(input)) {
                board[input - 1] = PLAYER_X;
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private boolean isValidMove(int input) {
        return input >= MIN_BOX_NUMBER && input <= MAX_BOX_NUMBER && board[input - 1] == ' ';
    }

    private void computerMove() {
        int randomBox;
        while (true) {
            randomBox = (int) (Math.random() * BOARD_SIZE) + MIN_BOX_NUMBER;
            if (board[randomBox - 1] == ' ') {
                board[randomBox - 1] = PLAYER_O;
                break;
            }
        }
    }

    private boolean isWinner(char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private boolean isBoxAvailable() {
        for (char box : board) {
            if (box == ' ') {
                return true;
            }
        }
        return false;
    }

    private boolean isGameOver() {
        return isWinner(PLAYER_X) || isWinner(PLAYER_O) || !isBoxAvailable();
    }

    private void displayResultMessage(int result) {
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