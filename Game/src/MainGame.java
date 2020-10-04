import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class MainGame {

    static String textPrint() {
       return "this is what i really wanted to test out anyways to see if this would work \n " +
               "and this is the second line of the entire thing";

    }


    static String pause() {
        return "\n Press enter to continue";
    }
    public static void main(String[] args) throws InterruptedException{
        //initialisation and variables
        Scanner input = new Scanner(System.in);
        Grid grid = new Grid(2, 2); //creates an instance of the Grid class, sets starting location

        //starting menu
        System.out.println("What is your name: ");
        String name = input.nextLine();
        System.out.println();
        Player mainChar = new Player(name, 1000, 10,5);

        //mob creation
        Enemy mob;
        Locations locations;
        mob = new Enemy("mobOne", 100, 100, 10); //makes mobOne enemy object
        locations = new Locations("Start"); //creates a new naming location object
        locations.addEnemy(mob); //adds enemy object to array in location class
        grid.setGridNames(2,2, locations);//adds location to 2d grid array

        mob = new Enemy("mobTwo", 100,1000,10);
        locations = new Locations("Second");
        grid.setGridNames(2,1, locations);
        locations.addEnemy(mob);

        mob = new Enemy("mobThree", 1,1,10);
        locations = new Locations("Under Start");
        grid.setGridNames(2,3, locations);
        locations.addEnemy(mob);

//        System.out.println(locations.numEnemy());
//        System.out.println(locations.getLocationName());
//        Locations start = grid.getGridName(2,2);// initialise the Locations class in an instance called start, find the object in the array in the grid class via the getName method
//        System.out.println(start.getEnemyName(0));// gets enemy name
//        System.out.println(grid.getGridName(2,2).getEnemyName(0)); // shorter way of doing the above two lines
//


/*        Grid start = new Grid("start", 3, 3); //
        System.out.println(start.getName(3, 3));
        start.setGridNames(3, 3 , "start"); //sets name in Grid array for positions x and y
        System.out.println(start.getName(3, 3)); //prints out name in Grid array for positions x and y     */

       //adding items
        Itemcreate itemOne = new Itemcreate("Really Big Sword"); //creating an object for "this is the item" called itemOne
//        mainChar.addItem(itemOne);//adds itemOne to the array list
//        Itemcreate itemTwo = new Itemcreate("this is the second item");// " " item two
//        mainChar.addItem(itemTwo);//" " item two
//        System.out.println(mainChar.getItem(0));//prints out item one from arraylist index position zero
//        System.out.println(mainChar.getItem(1));//" " position one
//        System.out.println(mainChar.getSize());//prints out number of items in the arraylist


        String command = "";
        while (!command.equals("exit")) {
            int playerDef = mainChar.getDef();
            int playerAtk = mainChar.getAtk();
            if((grid.getGridName(grid.getX(), grid.getY()).getLocationName()).equals("Second")) { //encounters enemy at location "second" via player x and y coordinates
                System.out.println("You have encountered: " + grid.getGridName(grid.getX(), grid.getY()).getEnemyName(0)); // shows encounter message of what mob via player x and y coordinates

                while(grid.getGridName(grid.getX(), grid.getY()).getEnemy(0).getHp() > 0) { // loop to ensure player stays in battle with enemy
                    System.out.println("Enemy Name: " + locations.getEnemyName(0));
                    System.out.println("Enemy HP: " + locations.getEnemy(0).getHp()); // prints enemy hp
                    System.out.println("Your HP: " + mainChar.getHp()); // prints player hp
                    System.out.println("\n What would you like to do? \n attack \n strong attack \n defend \n"); // menu selection
                    String mobOne = input.nextLine(); // menu selection

                    int twoAtk = locations.getEnemy(0).getAtk(); //attack variable for mobTwo
                    switch (mobOne) {
                        case "attack" -> {
                            locations.getEnemy(0).takeDamage(playerAtk);// does damage = to player attack to enemy
                            System.out.println("You hit the enemy for " + (playerAtk) + " damage!");
                            input.nextLine();
                            mainChar.takeDamage(twoAtk - playerDef);// takes damage = to mob attack to player - defence
                            System.out.println("You took " + (twoAtk - playerDef) + " damage!");
                            input.nextLine();
                        }
                        case "strong attack" -> {
                            int strongAttack = (int)(Math.random()*(2-1+1)+1);
                            locations.getEnemy(0).takeDamage((playerAtk * strongAttack)); // 50% chance of doing double damage
                            if (strongAttack == 2) {
                                System.out.println("Critical Strike!");
                            }
                            System.out.println("You swing and hit the enemy for " + (playerAtk * strongAttack) + " damage!");
                            TimeUnit.SECONDS.sleep(1);
                            mainChar.takeDamage(twoAtk + (playerDef / 2)); // lose half defensive stats when strong attacking
                            System.out.println("You risky attack dropped your defences! You took " + (twoAtk + (playerDef / 2)) + " damage!");
                            input.nextLine();
                        }
                        case "defend" -> {
                            mainChar.takeDamage(twoAtk - (playerDef * 2));
                            System.out.println("You steel your defences against the enemy. You took " + (twoAtk - playerDef) + " damage!");
                            input.nextLine();
                        }
                    }
                    if(locations.getEnemy(0).getHp() <= 0) {
                        System.out.println("Congratulations on killing mobOne! \n You have now obtained: " + itemOne.getItemName());
                        mainChar.addItem(itemOne);
                        System.out.println(pause());
                        input.nextLine();
                    }
                }
            }


            System.out.println("Your Location: " + grid.getGridName(grid.getX(), grid.getY()).getLocationName());//prints out Grid name in array
            System.out.println("What would you like to do? \n Move: up | down | left | right \n take damage \n help \n stats \n inventory \n"); //menu selection
            command = String.valueOf(input.nextLine());



            if (command.equals("up")) { //moves up in the grid
                grid.up();
                System.out.println();
            }

            if (command.equals("down")) { //moves down in the grid
                grid.down();
                System.out.println();
            }

            if (command.equals("left")) { //moves left in the grid
                grid.left();
                System.out.println();
            }

            if (command.equals("right")) { //moves right in the grid
                grid.right();
                System.out.println();
            }

            if (command.equals("stats")) {
                System.out.println("Your Stats: " + "\n" + mainChar.getHpStat() + "\n" + mainChar.getAtkStat() + "\n");
                System.out.println(pause());
                input.nextLine();
            }

            if (command.equals("help")) {
                System.out.println("This is the help menu! \n " + "");
                System.out.println(pause());
                input.nextLine();
            }

            if (command.equals("inventory")) {
                System.out.println("This is your inventory! \n Total number of items: " + mainChar.getSize());
                for (int i = 0; i < mainChar.getSize(); i++)
                System.out.println(mainChar.getItem(i));
                System.out.println(pause());
                input.nextLine();
            }

            if (command.equals("take damage")) {
                mainChar.takeDamage(50);
                System.out.println("You took 50 damage! \n" + mainChar.getHpStat() + "\n");
                if (mainChar.isDead()) {
                    System.out.println("You died.");
                    command = "exit";
                }
            }
        }
    }
}