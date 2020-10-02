import java.util.Scanner;

public class MainGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Grid start = new Grid("start", 303);
        System.out.println("What is your name: ");
        String name = input.nextLine();
        System.out.println("");
        Player mainChar = new Player(name, 100, 10);

        while (mainChar.isAlive()) {
            System.out.println("You are at: " + start.getName());
            System.out.println("What would you like to do? \n up \n down \n help \n stats \n");
            String trigger = String.valueOf(input.nextLine());

            if (trigger.equals("stats")) {
                System.out.println("Your Stats: " + "\n" + mainChar.getNameStat() + "\n" + mainChar.getHpStat() + "\n" + mainChar.getAtkStat() + "\n");
                System.out.println("Type anything to return.");
                String pause = input.nextLine();
            } else if (trigger.equals("help")) {
                System.out.println("This is the help menu! \n" );
            } else {
                System.out.println("");
                continue;
            }
        }

        Grid fourZeroThree = new Grid("fourZeroThree", 403);
    }
}