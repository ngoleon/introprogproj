import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;


// todo make sure to create algorithms
// todo comment everything

public class MainGame {

    static void locationCreate(String locationName, String locationDesc, int x, int y, Grid grid) { //method to create locations by setting parameters,
        Locations locations = new Locations(locationName, locationDesc); //constructs a location object
        grid.setGridNames(x, y, locations); //stores location object in 2d grid array at x and y coordinates
    }

    static void mobCreate(String name, String intro, String outro, int hp, int maxHp, int atk, int x, int y, Grid grid) { //method to create mobs by setting parameters
        Enemy mob = new Enemy(name, intro, outro, hp, maxHp, atk);
        grid.getGridName(x, y).addEnemy(mob);
    }

    static void itemCreate(String name, String desc, int x, int y, int enemyNum, Grid grid) {
        Itemcreate item = new Itemcreate(name, desc);
        grid.getGridName(x, y).getEnemyNumber(enemyNum).addItem(item);
    }

    static String pause() {
        return "\n Press enter to continue";
    }

    public static void main(String[] args) throws InterruptedException {
        //initialisation and variables
        Scanner input = new Scanner(System.in);
        Grid grid = new Grid(2, 2); //creates an instance of the Grid class, sets starting location

        //starting menu
        System.out.println("What is your name: ");
        String name = input.nextLine();
        System.out.println();
        Player mainChar = new Player(name, 1000, 100, 10, 10);
        System.out.println("What is your sibling's name: ");
        String siblingName = input.nextLine();
        System.out.println();
        Sibling sibling = new Sibling(siblingName);

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(intro() + "\n" + pause());
        input.nextLine();
        //mob creation
//        if (grid.checkXY(0,2)) {
//            System.out.println("\n You have reached the end of the corridor. " +
//                    "To the north is the door to the kitchen and to the south is the door to the bedrooms.\n");
//        }else if (grid.checkXY(4,2)) {
//            System.out.println("\n You have reached the end of the corridor. " +
//                    "To the north is the door to the bathroom and to the south is the door to the lounge room.\n");
//        }else if (grid.checkXY(2,1) || grid.checkXY(2,3) || grid.checkXY(1,2) || grid.checkXY(3,2)) {
//            System.out.println("\n You step out into the corridor. There isn’t much going on. \n");
//        }else if (grid.checkXY(2,4)) {
//            mainChar.setHp(mainChar.getMaxHp());
//            System.out.println("\n You reach the safe spot and call out “SAFE”. " +
//                    "Knowing the world is just a little less dangerous fills you with the strength to move forward.\n" +
//                    "\n Your HP fully heals to: ");
//        }


        locationCreate("Fantasy - Forest", "", 0, 0, grid);
        locationCreate("Fantasy - Kitchen", fantasyEntrance(), 0, 1, grid);
        locationCreate("Entrance FK & SA", leftEntrance(), 0, 2, grid);
        locationCreate("Space Adventure - Bedroom", spaceEntrance(), 0, 3, grid);
        locationCreate("Space Adventure - Cargo Hold", "", 0, 4, grid);
        locationCreate("Fantasy - Mountain", "", 1, 0, grid);
        locationCreate("Dead Space", "", 1, 1, grid);
        locationCreate("Corridor Left", corridor(), 1, 2, grid);
        locationCreate("Dead Space", "", 1, 3, grid);
        locationCreate("Space Adventure - Control Room", "", 1, 4, grid);
        locationCreate("Backdoor", "", 2, 0, grid);
        locationCreate("Corridor Top", corridor(), 2, 1, grid);
        locationCreate("Start", "", 2, 2, grid);
        locationCreate("Corridor Bottom", corridor(), 2, 3, grid);
        locationCreate("Front Door", frontDoor(), 2, 4, grid);
        locationCreate("Remix - Plateau", "", 3, 0, grid);
        locationCreate("Dead Space", "", 3, 1, grid);
        locationCreate("Corridor Right", corridor(), 3, 2, grid);
        locationCreate("Dead Space", "", 3, 3, grid);
        locationCreate("Wild West - Mineshaft", "", 3, 4, grid);
        locationCreate("Remix - Nest", "", 4, 0, grid);
        locationCreate("Remix - Bathroom", remixEntrance(), 4, 1, grid);
        locationCreate("Entrance R & WW", rightEntrance(), 4, 2, grid);
        locationCreate("Wild West - Lounge Room", wwEntrance(), 4, 3, grid);
        locationCreate("Wild West - Saloon", "", 4, 4, grid);

        mobCreate("Prancealot", forestIntro(), forestOutro(), 10, 100, 100, 0, 0, grid);
        mobCreate("Barathrum", fantasyIntro(), fantasyOutro(), 10, 100, 100, 1, 0, grid);

        mobCreate("Fuzz Aldrin", cargoHoldIntro(), cargoHoldOutro(), 10, 100, 100, 0, 4, grid);
        mobCreate("Neil Legstrong", controlRoomIntro(), controlRoomOutro(), 10, 100, 100, 1, 4, grid);

        mobCreate("Billy The Skid", saloonIntro(), saloonOutro(), 10, 100, 100, 4, 4, grid);
        mobCreate("Mild Bill", mildBillIntro(), mildBillOutro(), 10, 100, 100, 3, 4, grid);

        mobCreate("Xenodwarf", nestIntro(), nestOutro(), 10, 100, 100, 4, 0, grid);
        mobCreate("Cyber Bill 3000", remixIntro(), remixOutro(), 10, 100, 100, 3, 0, grid);


        itemCreate("JA", "First Piece", 1, 0, 0, grid);
        itemCreate("VA", "Second Piece", 1, 4, 0, grid);
        itemCreate("MA", "Third Piece", 3, 4, 0, grid);
        itemCreate("N!", "Fourth Piece", 3, 0, 0, grid);

        itemCreate("Bounty", "WANTED - Mild Bill (last seen in Mineshaft)", 4, 4, 0, grid);
        itemCreate("ID Card", "Just an ID card", 0, 4, 0, grid);
        itemCreate("Cylinder", "Cylinder", 4, 0, 0, grid);
        itemCreate("Silver Sword", "A silver sword", 0, 0, 0, grid);

        Itemcreate item = new Itemcreate("Lucky Charm", "A lucky charm you picked up.");
//        itemCreate("Really Big Sword 2", 2,0,0, grid);
//        itemCreate("Really Big Sword 3", 2,0,0, grid);
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
//        Itemcreate reallyBigSword = new Itemcreate("Really Big Sword"); //creating an object for "this is the item" called itemOne
//        mainChar.addItem(itemOne);//adds itemOne to the array list
//        Itemcreate itemTwo = new Itemcreate("this is the second item");// " " item two
//        mainChar.addItem(itemTwo);//" " item two
//        System.out.println(mainChar.getItem(0));//prints out item one from arraylist index position zero
//        System.out.println(mainChar.getItem(1));//" " position one
//        System.out.println(mainChar.getSize());//prints out number of items in the arraylist


        String command = "";
        while (!command.equals("exit")) {

            //flavour text
            System.out.println(grid.getGridName(grid.getX(), grid.getY()).getLocationDesc());

            if (grid.checkXY(2, 4)) {
                mainChar.setHp(mainChar.getMaxHp());
                System.out.print(" You heal to: " + mainChar.getHp() + "HP \n\n");
            }

            boolean lock = true;
            if (grid.checkXY(2, 0)) {
                if (mainChar.containsItemName("JA") && mainChar.containsItemName("VA") && mainChar.containsItemName("MA") && mainChar.containsItemName("N!")) {
                    System.out.println("You feel a strong presence behind the back door. Would you like insert the strange shapes into the door? \n yes \n no");
                    String trigger = input.nextLine();
                    if (trigger.equals("yes")) {
                        mainChar.removeItem(mainChar.getItemIndex("JA"));
                        mainChar.removeItem(mainChar.getItemIndex("VA"));
                        mainChar.removeItem(mainChar.getItemIndex("MA"));
                        mainChar.removeItem(mainChar.getItemIndex("N!"));
                        System.out.println(pause());
                        input.nextLine();
                        lock = false;
                    } else if (trigger.equals("no")) {
                        System.out.println("You follow your instincts and retreat.");
                    }
                } else if (mainChar.containsItemName("JA") || mainChar.containsItemName("VA") || mainChar.containsItemName("MA") || mainChar.containsItemName("N!")) {
                    System.out.println("You feel a slight warmth in your pockets from the shapes. " +
                            "You hear your sister's voice echo across the corridor, \"four pieces are needed to unlock the backdoor!\"\n");
                } else {
                    int i = 0;
                    System.out.println("\n You try to open the door to the backyard and find that it won’t budge. " +
                            "Upon closer inspection you see four shapes emblazoned on the door. What are they used for?\n");
                    while (i <= 11) {
                        String trigger;
                        if (i == 0) {
                            System.out.println("Attempt to forcibly break through? \n yes \n no");
                        } else {
                            System.out.println("Attempt to forcibly break through again? \n yes \n no");
                        }
                        trigger = input.nextLine();

                        if (trigger.equals("no")) {
                            break;
                        } else if (trigger.equals("yes")) {
                            i++;
                            System.out.println("\nAs you hit the door in frustration, your sister gives you a disapproving look.\n");
                        }
// todo make sure to write the flavour text for the alternate ending
                        if (i == 10) {
                            System.out.println("\nDeath.\n");
                            grid.sendEnd();
                            break;
                        } else if (i > 5) {
                            System.out.println("You feel like something bad is going to happen.");
                        }
                    }
                }
                if (!lock) {
                    mobCreate("javaman", javamanEncounter(), javamanDeath(), 10, 100, 100, 2, 0, grid);
                    itemCreate("Nice.", "Niiiiice", 2, 0, 0, grid);
                }
            }

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

            if (enemyExists) { //encounters enemy at location "second" via player x and y coordinates
                if (opponent.isAlive()) {
                    System.out.println(opponent.getIntro());
                    System.out.println(pause());
                    input.nextLine();
                }

                while (opponent.isAlive()) { // loop to ensure player stays in battle with enemy
                    System.out.println("You have encountered: " + opponent.getName()); // shows encounter message of what mob via player x and y coordinates
                    System.out.println("Enemy Name: " + opponent.getName());
                    System.out.println("Enemy HP: " + opponent.getHp()); // prints enemy hp
                    System.out.println("Your HP: " + mainChar.getHp()); // prints player hp
                    System.out.println("\n What would you like to do? \n 1 - Attack \n 2 - Strong Attack \n 3 - Defend \n"); // menu selection
                    String trigger = input.nextLine().toLowerCase(); // menu selection

                    int enemyAtk = opponent.getAtk(); //attack variable for mobTwo
                    switch (trigger) {
                        case "1" -> {
                            opponent.takeDamage(playerAtk);// does damage = to player attack to enemy
                            System.out.println("You hit the enemy for " + (playerAtk) + " damage!");
                            TimeUnit.MILLISECONDS.sleep(500);
                            mainChar.takeDamage(enemyAtk - playerDef);// takes damage = to mob attack to player - defence
                            System.out.println("You took " + (enemyAtk - playerDef) + " damage!\n");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        case "2" -> {
                            int strongAttack = (int) (Math.random() * (2 - 1 + 1) + 1);
                            opponent.takeDamage((playerAtk * strongAttack)); // 50% chance of doing double damage
                            if (strongAttack == 2) {
                                System.out.println("Critical Strike! \nYour sister cheers from the sidelines: " +
                                        "\n “Yeah! That looked like it hurt him! A few more of those should beat them!\n");
                            }
                            System.out.println("You swing and hit the enemy for " + (playerAtk * strongAttack) + " damage!");
                            TimeUnit.MILLISECONDS.sleep(500);
                            mainChar.takeDamage(enemyAtk + (playerDef / 2)); // lose half defensive stats when strong attacking
                            System.out.println("You risky attack dropped your defences! You took " + (enemyAtk + (playerDef / 2)) + " damage!\n");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        case "3" -> {
                            mainChar.takeDamage(enemyAtk - (playerDef * 2));
                            System.out.println("You steel your defences against the enemy. You took " + (enemyAtk - (playerDef * 2)) + " damage!\n");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                    }
                    if (!opponent.isAlive()) {
                        System.out.println(opponent.getOutro());
                        System.out.println(pause());
                        input.nextLine();

                        System.out.println("Congratulations on killing " + opponent.getName() + " \n You have now obtained: " + opponent.getItemName(0));
                        mainChar.addItem(opponent.getItem());
                        System.out.println(pause());
                        input.nextLine();

                        if (grid.checkXY(2, 0)) {
                            grid.sendEnd();
                            break;
                        } else if (grid.checkXY(1, 0) || grid.checkXY(3, 0) || grid.checkXY(1, 4) || grid.checkXY(3, 4)) {
                            grid.sendStart();
                            System.out.println("After defeating the boss, the world comes back into focus as you look " +
                                    "around, \nyou’re back at the junction of the hallways, all four directions spanning outwards.\n");
                        }
                    }

                    if (mainChar.isDead()) {
                        System.out.println("You died.");
                        grid.sendStart();
                        mainChar.setHp(mainChar.getMaxHp());
                        break;
                    }
                }
            }

            if (grid.checkXY(1, 1)) {
                break;
            } //condition to end the game after defeating the final boss

            System.out.println("Your Location: " + grid.getGridName(grid.getX(), grid.getY()).getLocationName());//prints out Grid name in array

            if (mainChar.isDead()) {
                System.out.println("You died.");
                grid.sendStart();
            }

            System.out.println("What would you like to do? \n Move: w - north | a - east | s - south | d - west \n 1 - Check your stats \n 2 - Opens your inventory"); //menu selection
            if (grid.checkXY(2, 2)) {
                System.out.println(" 3 - Talk to sister");
                if (!mainChar.containsItemName("Lucky Charm")) {
                    System.out.println(" 4 - Investigate Area");
                }
            }
            command = String.valueOf(input.nextLine());

            switch (command) {
//moves up in the grid
                case "w" -> {
                    grid.up();
                    System.out.println();
                }
//moves down in the grid
                case "s" -> {
                    grid.down();
                    System.out.println();
                }
//moves left in the grid
                case "a" -> {
                    grid.left();
                    System.out.println();
                }
//moves right in the grid
                case "d" -> {
                    grid.right();
                    System.out.println();
                }
                case "1" -> {
                    System.out.println(mainChar.getName() + "\n\n" + "Your Stats: " + "\n" + mainChar.getHpStat() + "\n" + mainChar.getAtkStat() + "\n");
                    System.out.println(pause());
                    input.nextLine();
                }
                case "2" -> {
                    input = new Scanner(System.in);
                    boolean invLock = true;
                    while (invLock) {
                        System.out.println("Inventory \n Options: \n 1 - description \n 2 - exit \n Total number of items: " + mainChar.getSize());
                        for (int i = 0; i < mainChar.getSize(); i++) {
                            System.out.println("\nItem " + (i + 1) + ": " + mainChar.getItem(i));
                        }

                        String invTrigger = input.nextLine();
                        if (invTrigger.equals("1")) {
                            System.out.println("Type in item number:");
                            int trigger = Integer.parseInt(input.nextLine());
                            if (mainChar.containsItemIndex(trigger) && trigger > 0) {
                                int indexTrigger = trigger - 1;
                                System.out.println(mainChar.getItem(indexTrigger) + "\n- " + mainChar.getDesc(indexTrigger));
                            } else {
                                System.out.println("Item does not exist.");
                            }
                            input.nextLine();
                        } else if (invTrigger.equals("2")) {
                            invLock = false;
                        }
                    }
                }
                // todo make sure to change npc advice (sibling)
                case "3" -> {
                    System.out.println("This is the help menu! \n " + "");
                    if (grid.checkXY(2, 2)) {
                        System.out.println(sibling.getName() + ": Would you like some help? \n yes \n no");
                        String trigger = input.nextLine();
                        if (trigger.equals("yes")) {
                            boolean siblingLock = true;
                            while (siblingLock) {
                                System.out.println(sibling.getName() + ": What do you need help with? \n 1 - What do I need to do? \n 2 - How do I heal up? \n 3 - How many endings are there? \n 4 - Go back.");
                                trigger = input.nextLine();
                                if (trigger.equals("1")) {
                                    if (!mainChar.containsItemName("JA")) {
                                        if (!mainChar.containsItemName("Silver Sword")) {
                                            System.out.println("Go kill 1,0");
                                        } else {
                                            System.out.println("Kill 0,0");
                                        }
                                    }
                                    if (!mainChar.containsItemName("VA")) {
                                        if (!mainChar.containsItemName("ID Card")) {
                                            System.out.println("Go kill 0,4");
                                        } else {
                                            System.out.println("Kill 1,4");
                                        }
                                    }
                                    if (!mainChar.containsItemName("MA")) {
                                        if (!mainChar.containsItemName("Bounty")) {
                                            System.out.println("Go kill 4,4");
                                        } else {
                                            System.out.println("Kill 3,4");
                                        }
                                    }
                                    if (!mainChar.containsItemName("N!")) {
                                        if (!mainChar.containsItemName("Cylinder")) {
                                            System.out.println("Go kill 4,0");
                                        } else {
                                            System.out.println("Kill 3,0");
                                        }
                                    }
                                } else if (trigger.equals("2")) {
                                    System.out.println();
                                } else if (trigger.equals("3")) {
                                    System.out.println("There are three endings.");
                                } else if (trigger.equals("4")) {
                                    siblingLock = false;
                                }
                                System.out.println(pause());
                                input.nextLine();
                            }
                        }
                    } else {
                        System.out.println("You are too far away from " + sibling.getName() + " to hear her.");
                    }
                    System.out.println(pause());
                    input.nextLine();
                }
                case "4" -> {
                    if (!mainChar.containsItemName("Lucky Charm")) {
                        System.out.println("You found a lucky charm on the floor, would you like to pick it up? \n yes \n no");
                        String trigger;
                        trigger = input.nextLine();
                        if (trigger.equals("yes")) {
                            mainChar.addItem(item);
                        }
                    }
                }

                case "take damage" -> {
                    mainChar.takeDamage(500);
                    System.out.println("You took 500 damage! \n" + mainChar.getHpStat() + "\n" + pause());
                    input.nextLine();
                }
                case "cheat" -> {
                    mainChar.addItem(grid.getGridName(1, 0).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(1, 4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3, 4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3, 0).getEnemy().getItem());
                }
            }
        }
    }

    static String javamanDeath() {
        return "\n With one final swing you slog Javaman across the jawbone and watch him " +
                "\n slump to the ground. Breathing heavily you turn to your younger sibling to " +
                "\n see if they’re okay. \n\n The front door opens revealing your tired and bedraggled" +
                " mum who looks \n at you both before shifting over to settle on Javaman laying flat " +
                "on the floor. \n\n She gasps, \"It’s you!\" she exclaims pointing at the figure on the" +
                " floor. You and your \n sibling exchange looks of confusion as Javaman seems to break \n" +
                "down and disappear. \n\n Your mum looks at you both bewilderment in her eyes, \"Why " +
                "did you let him \nout?!\"";
    }

    static String javamanEncounter() {
        return "\nYou feel heat rise from your pocket as all of the symbols light up at once " +
                "\nilluminating the shapes emblazoned on the door. The door slides open smoothly but not by " +
                "\nyour influence. Slowly, at first a figure comes into view walking through the doorway. " +
                "\nYou hear a small, sharp intake of breath from your sibling you now hides behind you before " +
                "\nthey whisper “Java-man…”. You stare in awe as the figure rises to his full height towering " +
                "\nover you. But you don’t get long to process what’s happening before he attacks.";
    }

    static String intro() {
        return " The front door lurches open as your mum hurriedly pulls her shoes on. She curses for a second" +
                "\n and turns to you a glimmer of desperation in her eye \"Can you please watch your sister please?\". " +
                "\n Her messy hair and tired eyes, an unsubtle reminder of the situation your family has been in for a " +
                "\n while, you walk over and hold the door open to free up her other hand so she can tie her shoes properly." +
                "\n\n \"Sure mum\" you say smiling. She thanks you as she races out the door. You shut the door gently before " +
                "\n turning around being greeted by your younger sibling." +
                "\n\n \"Hey, you wanna play?\" you ask, leading her down the corridor.";
    }

    static String saloonIntro() {
        return " The doors to the saloon swing open, and as the floorboards squeak with your first step you notice an " +
                "\n abrupt silence fall onto the lively room. \"Settle down, yall...\" said the moustached man behind the " +
                "\n bar \"It ain’t Bill.\" a fairly drunk prospector piped up and spoke \"Couple ‘a heads too short on the " +
                "\n both of ‘em to be Bill!\" The jolly tone that rang out from before sprung back to life \"Don’t just " +
                "\n stand there! Get in or get lost!\" shouted the bartender. The two of you approach the bar, you take a " +
                "\n seat however your little sister’s head could only just a few centimeters over the bar \"Now what’d you " +
                "\n like, missy?\" The bartender says cheerfully. As she begins to answer, a hand grabs your shoulder and " +
                "\n you turn to see a skinny drunk man, probably only three years your elder. \"You don’t seem that tough!\" " +
                "\n The skinny thug slurs \"y’know what they call me? I’m Billy the Skid! I can best them all!\" he looks " +
                "\n away for a few seconds and utters \"except Mild Bill...\" he stares at you intensely and screams \"BAR FIGHT!\"";
    }

    static String saloonOutro() {
        return "Billy takes one last hit across his cheek and stumbles backwards onto an occupied table, splitting it in" +
                "\n  half and spilling the Prospectors drink across his long and unkempt beard. \"YOU SPILT MY ROOT BEER, YOU" +
                "\n  YELLOW BELLY HOODLUM!\" the old prospector screeched. The bar suddenly all erupted and piled on the poor" +
                "\n  thug. You return to the bar and find two freshly poured glasses of milk on the bar. \"You’ve got some " +
                "\n moves, kid!\" The bartender  \"Why not check the bounty board out down the hall, sure a tenacious young " +
                "\n lad like you could make yourself a living of a few more hoodlums like young Billy there.\" You and your " +
                "\n sister take a protracted sip from your glasses of Milk. Your sister looks up to the kind moustached " +
                "\n gentleman and says \"Thank you, kindly.\" The two of you walk down the hallway and find a board covered in" +
                "\n  aged and torn up papers, each with a name and a bounty attributed to them. One catches your eye, the " +
                "\n aforementioned \"Mild Bill\" with a bounty of $50 ($5000 adjusted for inflation). You grasp the bounty " +
                "\n and rip it from the board. Without a single word, the two of you leave the now trashed saloon that was " +
                "\n somehow still as lively as it was before your fight. On the main road, you look north and see the rough " +
                "\n thug from before, something tells you that the mine to the left might be a better place to look for now.";
    }

    static String mildBillIntro() {
        return "You walk towards the thug in the middle of the street. He looks up and mumbles \"You the deputy?\" You nod " +
                "\n and he asks \"Y’know who I am, boy?\" you study his face and recognise it from the bounty \"You’re Mild Bill\" " +
                "\n he cracks a crass smile \"And I shot the sheriff, guess I’ll now have to shoot the deputy! DRAW!\"";
    }

    static String mildBillOutro() {
        return "Bill lets out a faint grunt and falls flat on his back. You realise that this whole time, the patrons of " +
                "\n the saloon and the cowardly sheriff had been watching. They all erupt in celebration. The prospector, " +
                "\n with one arm around the neck of Billy the Skid and the other raising a fresh glass of root beer in the air " +
                "\n yells \"HOORAY FOR THE YOUNGSTERS! MILD BILL’S IN THE PROVERBIAL DUMPSTER!\". Your sister fumbles through " +
                "\n the pockets of the TKO’d Bill and finds a strange letter. She hands it to you and the world fades to white.";
    }

    static String cargoHoldIntro() {
        return "You follow the corridor out into a very wide open space, shipping containers strewn through the air, " +
                "\n a few of them burst open in their sides or in other seemingly random locations. \n" +
                "\n \"Hold it\" you hear someone call out from amongst the containers. Your sister, not heeding the warning" +
                "\n  steps forward and between a flash of light and your quick reflexes you manage to save her from a laser " +
                "\n shot that now stands. You hear the voice gasp in shock as a hairy man slowly comes into view. \"Y-you’re" +
                "\n  one of them\" he says \"This is useless on you\". He throws his gun off to the side and pulls into a " +
                "\n weird fighting stance, even in zero gravity he looked completely at ease until he kicked off of a nearby" +
                "\n  heavy object and hurtled at you.";
    }

    static String cargoHoldOutro() {
        return "You both trade some blows until you finally manage to land a clean hit to the sternum making him wheeze " +
                "\n before grabbing him by his scruffy hair and slamming his head into a nearby container knocking him out. " +
                "\n You catch a glimpse of his ID card and take it from him while he’s knocked out. \n" +
                "\n You pull your sister in close double checking to make sure she’s okay. \n" +
                "\n You look around. To the north you saw the door you used to enter the cargo hold and to the east a " +
                "\n sign marked Control Room.";
    }

    static String controlRoomIntro() {
        return "You walk into the room, there are loose wires sparking everywhere with a giant console in the middle of" +
                "\n  the room and what looks like a giant tube at the back of the space sitting adjacent to the computer. " +
                "\n You walk up and inspect the display it reads [Please verify peer authentication] with a card reader " +
                "\n illuminated by a strip of lights. It’s worth a shot you figure as you slide the card into the reader. " +
                "\n The screen flashes green telling you it has been verified and you hear a hiss from behind you as the " +
                "\n pod opens up. Inside is a shining letter ‘J’ that seems to shimmer in the dull space of the broken " +
                "\n control room. You go over to take a look when you hear a shot whizz past your ear. \n" +
                "\n \"Hand it over kid\" He warns, \"I know a fella who pays pretty good for that stuff\". You size him " +
                "\n up, after the last two encounters you’ve had here you’re not even close to surprised, even a little " +
                "\n tired of the repetition. You raise your hands in surrender when you see a small flicker of movement " +
                "\n from the corner of your eye as your sister bursts out of hiding and winds up a punch very similar to " +
                "\n the one you made in the cafeteria but, due to her stunning lack of height manages to land an incredibly" +
                "\n  clean hit to the stomach forcing him to drop his gun which she scampers away with.";


    }

    static String controlRoomOutro() {
        return "You land the final blow as he slumps to the ground. After this, it’s not hard to imagine that you’re " +
                "\n kind of over space adventures. You sister runs up and hugs you and you ruffle her hair a little and she " +
                "\n presents you with the letter. You hold onto it as everything fades to the white.";
    }

    static String nestIntro() {
        return "You tread carefully through the field of eggs as smooth and dark as ebony, making sure you are very " +
                "\n mindful of where you put your hands. That is until you feel something push you from behind and you " +
                "\n stumble into a bunch of eggs. Eventually, you hear a crunch followed by a quiet \"uh oh\". You turn " +
                "\n around and find your little sister standing in an egg.  Almost immediately a small figure rises, a " +
                "\n gross oblong head and ribbed spiny physique but what really stood out was the featureless face, the " +
                "\n rows upon rows of teeth and finally the wispy remains of a blood spattered beard. You look at it for " +
                "\n a couple seconds before it strikes.";
    }

    static String nestOutro() {
        return "The battered husk of the creature lays curled up beneath you like a dead spider. You see something " +
                "\n glimmer inside of it which you pull out anD inspect it, it looks like a smooth cylinder with a gem " +
                "\n inlay in the middle of one of the cylinder’s bases.  It could be useful later you think to yourself. \n" +
                "\n You look around, you see the path you took that leads down to the general area of the cantina to the " +
                "\n south and a path off to the west that seems to lead up a mountain.";
    }

    static String remixIntro() {
        return "You walk out into an empty plateau with sheers drop offs surrounding all sides. The wind sweeps over " +
                "\n the surface kicking up dust. In the middle of the open space sits a pedestal with an indent in it, " +
                "\n in the shape of a rectangle with a curved bottom.  \n" +
                "\n \"Hey! That's the same shape as the items we picked up!\" your sister exclaims. You look it over and " +
                "\n notice that she’s right. Not really knowing what else to do you insert it into the slot on the " +
                "\n pedestal. The pedestal drops into the ground as the floor next to it opens up revealing the fourth " +
                "\n letter, the letter ‘A’. Which rises slowly out of the ground glowing and shimmering. You reach out " +
                "\n to grab it when a tinny voice calls out from behind you. \n" +
                "\n \"Well well well, thank you for doing the work for me boy, especially with how our last meeting " +
                "\n ended\". Frown, turning around to see Mild Bill. However, limbs seem to have been replaced with metal " +
                "\n augments and wild magic glowing in his eye. \"Now, hand it over\" \n" +
                "\n \"Mild Bill?\" your voice betraying your disbelief. \n" +
                "\n \"Close, I’m Cyber_Bill_3000 now\" he declares before launching at you.";
    }

    static String remixOutro() {
        return "Cyber bill slumps to the ground, eyes sputtering the mechanical limbs twitching twisted at odd angles " +
                "\n as range limiters gave out. Your sister hands you the letter as the world fades to white.";
    }

    static String forestIntro() {
        return "The forest before you chirps with life, a path is carved through the dense grass and trees, leading " +
                "\n further into the wilderness. In front of you lies a bridge, guarded by a solitary knight in silver " +
                "\n armor. The large knight approaches you on the opposite side of the bridge. \"Hold\" the steel knight " +
                "\n bellowed \"State thy names...\" You apprehensively mumble your name \"*name* is it? What tragic " +
                "\n irony...\" you shoot him a look, one eyebrow raised. \"Tragic as it’s the name the prophecies " +
                "\n foretold shalt wield my family blade and slay the black dragon, Barathrum.\" he pauses for dramatic " +
                "\n flare \"However I don’t hold myself to the whims of old faerie tales and I, Sir Prancealot, will slay " +
                "\n the beast myself... if you wish to fulfill this foolish belief held only by naves and peasantry: Thy " +
                "\n must pry my blade and my charge from my dead hands!\" he unsheathes his silver sword, its spine is " +
                "\n engraved with ancient runes and its ornate gold hilt shines in the beams of light that pass through " +
                "\n the canopy leaves. \"Let this be done, peasant.\"";
    }

    static String forestOutro() {
        return "The knight slumps to one knee, with a single hand grasped to his sword. \"You have bested me, now " +
                "\n finish your job.\" you push him onto his back and pry the sword from his hands. \"What are you doing?" +
                "\n  Finish me!\" he protests \"release my blade thief, I said you could have it after I die!\" you look " +
                "\n down at him and ask \"what are you gonna do about it down there? Grasp onto my ankles?\". You walk " +
                "\n back across the bridge and leave the knight to his fate. Before you lies an opening to a forest to " +
                "\n your right and a mountain towering beyond it.";
    }

    static String fantasyIntro() {
        return "You approach the base of the dark mountain and spot a small cave, inside it is an ancient elven door " +
                "\n that you assume leads inward. You pull out the elves key and move to place it in the door. The key " +
                "\n slips in perfectly as you twist, the door moves backwards then slides to the right. Before you lies " +
                "\n what could be an infinitely dark tunnel until small, blue flames appear in dark metallic braziers " +
                "\n that progressively lead you down towards the abyss. Suddenly the cave comes to a large opening, light " +
                "\n fills the room through cracks in the surface of the mountain and reflects off of the purple speckled " +
                "\n black scales of a great dragon. You make your way down to its nest, lined with gold and silver jewels. " +
                "\n The dragon awakens and spreads its massive wings, it bellows \"WHO COMETH?\" you step forward and " +
                "\n respond \"*player name*!\" the dragon's head raises and it blasts \"THEN MEET YOUR DOOM!\".";
    }

    static String fantasyOutro() {
        return "The dragon screeches its death cry and falls, unleashing a final blast of air that made the assorted " +
                "\n gold and silver around it rattle violently. A single scale falls off of the abyssal dragon, your " +
                "\n sister picks it up and shows it to you \"Looks like the letter ‘A’, right?\" You nod in agreement " +
                "\n and the world around you fades to white.";
    }

    static String corridor() {
        return "\n You step out into the corridor. There isn’t much going on. \n";
    }

    static String frontDoor() {
        return "\n You reach the safe spot and call out “SAFE”. " +
                "Knowing the world is just a little less dangerous fills you with the strength to move forward.";
    }

    static String rightEntrance() {
        return "\n You have reached the end of the corridor. " +
                "To the north is the door to the bathroom and to the south is the door to the lounge room.\n";
    }

    static String leftEntrance() {
        return "\n You have reached the end of the corridor. " +
                "To the north is the door to the kitchen and to the south is the door to the bedrooms.\n";
    }

    static String wwEntrance() {
        return "\n \"You enter through the door, and find yourself in a dusty old west town. To your left you spy an " +
                "\n unsuspecting saloon, filled with the roughest of the rough and to your right you notice the opulent" +
                "\n  opening of the town’s mineshaft, labeled \"GOLD BE HERE!\". A tumbleweed slides gracefully across " +
                "\n the main road in front of you, drawing your eyes to a rough thug with a chip on his shoulder, and " +
                "\n his hand on his holster...\"\n";
    }

    static String fantasyEntrance() {
        return "\n \"As you walk through the kitchen door, the world around you brightens, faeries flutter across the " +
                "\n path leading to a sprawling vista of hills and grassy fields. To the right of you, there's an opening " +
                "\n to a forest path that seems to lead up towards an imposing mountain...\"\n";
    }

    static String spaceEntrance() {
        return "\n \"As the bedroom door slowly creaks open, you feel your body drift up from the floor. As you enter, " +
                "\n you let out a small gasp as you lose your breath realising the air is still breathable. The sky above " +
                "\n you opens up to an alien moon gently floating in a sea of stars and a beautiful stream of lights that " +
                "\n seem to carry every colour on the spectrum within. You float over to an abandoned space station taking " +
                "\n a peek inside. You see a sign pointing south that reads cargo hold\"\n";
    }

    static String remixEntrance() {
        return "\n \"You feel a strange pulse pass through you as your hand touches the bathroom door, it slides open " +
                "\n and you see a chaotic hodge-podge of pure imagination. Up north you see a nest of some kind, yet " +
                "\n adorned with the bold characteristics of an ancient race of men?\"\n";
    }
}



