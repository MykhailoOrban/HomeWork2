import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameLogic game = new GameLogic(scanner);

        System.out.println("Enter box number to select. Enjoy!\n");
        game.playGame();
    }
}
