import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

// todo make sure location coordinates x and y are not inverted

public class MainGame {

    static String textPrint() {
       return "this is what i really wanted to test out anyways to see if this would work \n " +
               "and this is the second line of the entire thing";

    }

    static void locationCreate(String locationName, int x, int y, Grid grid) { //method to create locations by setting parameters,
        Locations locations = new Locations(locationName); //constructs a location object
        grid.setGridNames(x, y, locations); //stores location object in 2d grid array at x and y coordinates
    }

    static void mobCreate(String name, int hp, int maxHp, int atk, int x, int y, Grid grid) { //method to create mobs by setting parameters
        Enemy mob = new Enemy(name, hp, maxHp, atk);
        grid.getGridName(x,y).addEnemy(mob);
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

        locationCreate("Start", 2,2, grid);
        mobCreate("jeff",1,1,1,2, 2, grid);

//        locations = new Locations("Start"); //creates a new naming location object
//        mob = new Enemy("mobOne", 100, 100, 10); //makes mobOne enemy object
//        locations.addEnemy(mob); //adds enemy object to array in location class
//        mob = new Enemy("mobTwoStarter",1,1,1);
//        locations.addEnemy(mob);
//
//        grid.setGridNames(2,2, locations);//adds location to 2d grid array

//        mob = new Enemy("mobTwo", 100,1000,10);
//        locations = new Locations("Second");
//        locations.addEnemy(mob);
//        grid.setGridNames(2,1, locations);
//
//
//        mob = new Enemy("mobThree", 1,1,10);
//        locations = new Locations("Under Start");
//        locations.addEnemy(mob);
//        grid.setGridNames(2,3, locations);
//



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
            Locations gridLocation = grid.getGridName(grid.getX(), grid.getY());//retrieves location object using players x and y coordinates
            boolean enemyExists = true;
            Enemy opponent = null;
            if (gridLocation.getEnemy() == null) {
                enemyExists = false;
            } else {
                opponent = gridLocation.getEnemy();
            }

            if(enemyExists == true) { //encounters enemy at location "second" via player x and y coordinates

                while(opponent.getHp() > 0) { // loop to ensure player stays in battle with enemy
                    System.out.println("You have encountered: " + opponent.getName()); // shows encounter message of what mob via player x and y coordinates
                    System.out.println("Enemy Name: " + opponent.getName());
                    System.out.println("Enemy HP: " + opponent.getHp()); // prints enemy hp
                    System.out.println("Your HP: " + mainChar.getHp()); // prints player hp
                    System.out.println("\n What would you like to do? \n Attack \n Strong Attack \n Defend \n"); // menu selection
                    String trigger = input.nextLine().toLowerCase(); // menu selection

                    int enemyAtk = opponent.getAtk(); //attack variable for mobTwo
                    switch (trigger) {
                        case "attack" -> {
                            opponent.takeDamage(playerAtk);// does damage = to player attack to enemy
                            System.out.println("You hit the enemy for " + (playerAtk) + " damage!");
                            TimeUnit.SECONDS.sleep(1);
                            mainChar.takeDamage(enemyAtk - playerDef);// takes damage = to mob attack to player - defence
                            System.out.println("You took " + (enemyAtk - playerDef) + " damage!");
                            input.nextLine();
                        }
                        case "strong attack" -> {
                            int strongAttack = (int)(Math.random()*(2-1+1)+1);
                            opponent.takeDamage((playerAtk * strongAttack)); // 50% chance of doing double damage
                            if (strongAttack == 2) {
                                System.out.println("Critical Strike!");
                            }
                            System.out.println("You swing and hit the enemy for " + (playerAtk * strongAttack) + " damage!");
                            TimeUnit.SECONDS.sleep(1);
                            mainChar.takeDamage(enemyAtk + (playerDef / 2)); // lose half defensive stats when strong attacking
                            System.out.println("You risky attack dropped your defences! You took " + (enemyAtk + (playerDef / 2)) + " damage!");
                            input.nextLine();
                        }
                        case "defend" -> {
                            mainChar.takeDamage(enemyAtk - (playerDef * 2));
                            System.out.println("You steel your defences against the enemy. You took " + (enemyAtk - playerDef) + " damage!");
                            input.nextLine();
                        }
                    }
                    if(opponent.getHp() <= 0) {
                        System.out.println("Congratulations on killing " + opponent.getName() + " \n You have now obtained: " + itemOne.getItemName());
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