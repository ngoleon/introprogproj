import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;


// todo make sure to create algorithms
// todo comment everything
//todo change stats of everything

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

        //starting menu (player name and sibling name)
        System.out.println("What is your name: ");
        String name = input.nextLine();
        System.out.println();
        Player mainChar = new Player(name, 250, 250, 10, 10);


        System.out.println("What is your sibling's name: ");
        String siblingName = input.nextLine();
        System.out.println();
        Sibling sibling = new Sibling(siblingName);

        System.out.println(intro() + "\n" + pause());
        input.nextLine();
        //starting menu end

        //location creation
        locationCreate("Fantasy - Forest", "", 0, 0, grid);
        locationCreate("Fantasy - Kitchen", fantasyEntrance(), 0, 1, grid);
        locationCreate("Entrance FK & SA", leftEntrance(), 0, 2, grid);
        locationCreate("Space Adventure - Bedroom", spaceEntrance(), 0, 3, grid);
        locationCreate("Space Adventure - Cargo Hold", "", 0, 4, grid);
        locationCreate("Fantasy - Mountain", "", 1, 0, grid);
        locationCreate("Dead Space", "", 1, 1, grid);
        locationCreate("Corridor West", corridor(), 1, 2, grid);
        locationCreate("Dead Space", "", 1, 3, grid);
        locationCreate("Space Adventure - Control Room", "", 1, 4, grid);
        locationCreate("Backdoor", "", 2, 0, grid);
        locationCreate("Corridor North", corridor(), 2, 1, grid);
        locationCreate("Start", "", 2, 2, grid);
        locationCreate("Corridor South", corridor(), 2, 3, grid);
        locationCreate("Front Door", frontDoor(), 2, 4, grid);
        locationCreate("Remix - Plateau", "", 3, 0, grid);
        locationCreate("Dead Space", "", 3, 1, grid);
        locationCreate("Corridor East", corridor(), 3, 2, grid);
        locationCreate("Dead Space", "", 3, 3, grid);
        locationCreate("Wild West - Mineshaft", "", 3, 4, grid);
        locationCreate("Remix - Nest", "", 4, 0, grid);
        locationCreate("Remix - Bathroom", remixEntrance(), 4, 1, grid);
        locationCreate("Entrance R & WW", rightEntrance(), 4, 2, grid);
        locationCreate("Wild West - Lounge Room", wwEntrance(), 4, 3, grid);
        locationCreate("Wild West - Saloon", "", 4, 4, grid);
        //location creation end

        //mob creation
        mobCreate("Prancealot", forestIntro(), forestOutro(), 200, 200, 20, 0, 0, grid);
        mobCreate("Barathrum", fantasyIntro(), fantasyOutro(), 400, 400, 40, 1, 0, grid);

        mobCreate("Fuzz Aldrin", cargoHoldIntro(), cargoHoldOutro(), 300, 300, 30, 0, 4, grid);
        mobCreate("Neil Legstrong", controlRoomIntro(), controlRoomOutro(), 600, 600, 50, 1, 4, grid);

        mobCreate("Billy The Skid", saloonIntro(), saloonOutro(), 400, 400, 40, 4, 4, grid);
        mobCreate("Mild Bill", mildBillIntro(), mildBillOutro(), 800, 800, 60, 3, 4, grid);

        mobCreate("Xenodwarf", nestIntro(), nestOutro(), 500, 500, 50, 4, 0, grid);
        mobCreate("Cyber Bill 3000", remixIntro(), remixOutro(), 1000, 1000, 60, 3, 0, grid);
        //mob creation end

        //item creation
        itemCreate("JA", "First Piece", 1, 0, 0, grid);
        itemCreate("VA", "Second Piece", 1, 4, 0, grid);
        itemCreate("MA", "Third Piece", 3, 4, 0, grid);
        itemCreate("N!", "Fourth Piece", 3, 0, 0, grid);

        itemCreate("Bounty", "A Bounty for Mild Bill", 4, 4, 0, grid);
        itemCreate("ID Card", "Just an ID card", 0, 4, 0, grid);
        itemCreate("Cylinder", "A Cylinder... that's it...", 4, 0, 0, grid);
        itemCreate("Silver Sword", "Big sword, silver in colour", 0, 0, 0, grid);

        Itemcreate item = new Itemcreate("Lucky Charm", "A lucky charm you picked up.");
        Itemcreate key = new Itemcreate("Back Door Key", "Used to access the back door.");
        //item creation end


        String command = ""; //string that controls the overall game loop
        while (!command.equals("exit")) { //overall game loop


            System.out.println(grid.getGridName(grid.getX(), grid.getY()).getLocationDesc()); //prints location description using x and y coordinates

            if (grid.checkXY(2, 4)) {
                mainChar.setHp(mainChar.getMaxHp()); //method that sets players hp to their maximum
                System.out.print(" You heal to: " + mainChar.getHp() + "HP \n\n"); //shows hp healed
            } //if statement checking coordinate location to be at the front door

            boolean lock = true; //boolean for backdoor lock
            if (grid.checkXY(2, 0)) { //checks x and y coordinates for back door
                if (mainChar.containsItemName("JA") && mainChar.containsItemName("VA") && //checks for whether all req
                        mainChar.containsItemName("MA") && mainChar.containsItemName("N!")) {//items are contained in inventory
                    System.out.println("You feel a strong presence behind the back door. Would you like insert the strange shapes into the door? \n 1 - yes \n 2 - no"); //prints out selection menu
                    String trigger = input.nextLine(); //string variable to store input for further statements
                    if (trigger.equals("1")) {//if String trigger input is "yes"
                        mainChar.removeItem(mainChar.getItemIndex("JA")); //removes item from inv
                        mainChar.removeItem(mainChar.getItemIndex("VA")); // " " " " " " " " " "
                        mainChar.removeItem(mainChar.getItemIndex("MA")); // " " " " " " " " " "
                        mainChar.removeItem(mainChar.getItemIndex("N!")); // " " " " " " " " " "
                        mainChar.addItem(key);
                        System.out.println("The shapes magically disappear and you are given a 'Back Door Key'");
                        System.out.println(pause()); //flavour text to continue
                        input.nextLine(); //requires user input to continue
                        lock = false; //unlocks backdoor
                    } else if (trigger.equals("2")) { //if String trigger input is "no"
                        System.out.println("You follow your instincts and retreat."); //print statement and return
                    }
                } else if (mainChar.containsItemName("JA") || mainChar.containsItemName("VA") || //checks for whether
                        mainChar.containsItemName("MA") || mainChar.containsItemName("N!")) {//some items are contained in inventory
                    System.out.println("You feel a slight warmth in your pockets from the shapes. " +
                            "You hear your sister's voice echo across the corridor, \"four pieces are needed to unlock the backdoor!\"\n"); //print statement for when some items are present in inventory
                } else if (!mainChar.containsItemName("Back Door Key")) { //if no items are present in inventory
                    int i = 0; //counter for door breaking
                    System.out.println("\n You try to open the door to the backyard and find that it won’t budge. " +
                            "Upon closer inspection you see four shapes emblazoned on the door. What are they used for?\n"); //print statement for when no relevant items are in inventory
                    while (i <= 11) { //begin door breaking loop
                        String trigger; //String variable to store trigger
                        if (i == 0) {  //if counter is at 0
                            System.out.println("Attempt to forcibly break through? \n 1 - yes \n 2 - no"); //print this text
                        } else { //if counter is not at 0
                            System.out.println("Attempt to forcibly break through again? \n 1 - yes \n 2 - no"); //print this text
                        }
                        trigger = input.nextLine(); //ask user to store trigger variable

                        if (trigger.equals("2")) { //if user inputs "no" from trigger variable
                            break; //break the loop
                        } else if (trigger.equals("1")) { //if user inputs "yes" from trigger variable
                            i++; //adds 1 to the door breaking counter
                            System.out.println("\nAs you hit the door in frustration, your sister gives you a disapproving look.\n"); //prints out flavour text
                        }

                        if (i == 10) { //if door is broken into 10 times
                            System.out.println(doorBreak()); //prints out flavour text
                            grid.sendEnd(); //method to send player to the end coordinates which end the game
                            break; //break the loop
                        } else if (i > 5) { //else if door is attempted to be broken into over 5 times
                            System.out.println("You feel like something bad is going to happen."); //prints out warning
                        }
                    }
                }
                if (!lock) { //if lock boolean is false
                    mobCreate("Javaman", javamanEncounter(), javamanDeath(), 10000, 10000, 200, 2, 0, grid); //creates javaman boss mob, stores name, intro, outro and other variables
                    itemCreate("The True Ending!", "Niiiiice", 2, 0, 0, grid); //creates item called "Nice." for mob on coordinates 2,0 (javaman)
                }
            }

            int playerDef = mainChar.getDef(); //stores player defence in an int
            int playerAtk = mainChar.getAtk(); //store player attack in an int
            Locations gridLocation = grid.getGridName(grid.getX(), grid.getY());//retrieves location object using players x and y coordinates
            boolean enemyExists = true; //boolean to confirm whether an enemy exists or not
            Enemy opponent = null; //handling exceptions without try catch
            if (gridLocation.getEnemy() == null) {//if statement that calls a method to check if enemies from a current x and y coordinates do not exist
                enemyExists = false; //if enemy does not exist, then boolean enemyExists is changed to false
            } else { //if enemy does not return a null value and returns a valid enemy
                opponent = gridLocation.getEnemy(); //the enemy is retrieved and stored as the object variable "opponent"
            }

            if (enemyExists) { //encounters enemy at location via player x and y coordinates
                if (opponent.isAlive()) { //calls method to check whether the enemy is alive or not
                    System.out.println(opponent.getIntro()); //prints out enemy intro
                    System.out.println(pause()); //flavour text to press to continue
                    input.nextLine(); //requires user to input to continue
                } //encounters enemy at location via player x and y coordinates

                while (opponent.isAlive()) { // loop to ensure player stays in battle with enemy
                    System.out.println("You have encountered: " + opponent.getName()); // shows encounter message of what mob via player x and y coordinates
                    System.out.println("Enemy Name: " + opponent.getName());
                    System.out.println("Enemy HP: " + opponent.getHp()); // prints enemy hp
                    System.out.println("Your HP: " + mainChar.getHp()); // prints player hp
                    System.out.println("\n What would you like to do? \n 1 - Attack \n 2 - Strong Attack \n 3 - Defend \n"); // menu selection
                    String trigger = input.nextLine().toLowerCase(); // menu selection

                    int enemyAtk = opponent.getAtk(); //attack variable for mobTwo
                    switch (trigger) {
                        case "1" -> { //"attack" from menu selection
                            opponent.takeDamage(playerAtk);// does damage = to player attack to enemy
                            System.out.println("You hit the enemy for " + (playerAtk) + " damage!"); //prints out damage given
                            TimeUnit.MILLISECONDS.sleep(500); //pause between lines
                            mainChar.takeDamage(enemyAtk - playerDef);// takes damage = to mob attack to player - defence
                            System.out.println("You took " + (enemyAtk - playerDef) + " damage!\n"); //prints out damage taken
                            TimeUnit.MILLISECONDS.sleep(500); //pause between lines
                        }
                        case "2" -> {//"strong attack" from menu selection
                            int strongAttack = 0;
                            if (!mainChar.containsItemName("Lucky Charm")) {
                                strongAttack = (int) (Math.random() * (2 - 1 + 1) + 1); //utilises random to give a 50% chance of rolling double damage (max - min + 1) + min
                            } else {
                                strongAttack = (int) (Math.random() * (3 - 1 + 1) + 1); //33% chance of rolling double 33% triple damage (max - min + 1) + min
                            }
                            opponent.takeDamage((playerAtk * strongAttack)); // deals damage utilising above random calculation
                            if (strongAttack == 2) { //if double damage is rolled
                                System.out.println(criticalStrikeDouble()); //flavour text for double damage
                            } else if (strongAttack == 3) {
                                System.out.println(criticalStrikeTriple()); //flavour text for double damage
                            }
                            System.out.println("You swing and hit the enemy for " + (playerAtk * strongAttack) + " damage!"); //prints damage dealt
                            TimeUnit.MILLISECONDS.sleep(500); //pause between lines
                            mainChar.takeDamage(enemyAtk + (playerDef / 2)); // lose half defensive stats when strong attacking
                            System.out.println("You risky attack dropped your defences! You took " + (enemyAtk + (playerDef / 2)) + " damage!\n"); //prints damage taken
                            TimeUnit.MILLISECONDS.sleep(500); //pause between lines
                        }
                        case "3" -> {//"defend" from menu selection
                            mainChar.takeDamage(enemyAtk);

                            if (mainChar.getHp() < mainChar.getMaxHp()) {
                                mainChar.heal(enemyAtk + (playerDef * 2)); //calculation for health healed taken, enemy atk + double player def
                                System.out.println("You steel your defences against the enemy. You heal " + (playerDef * 2) + "!\n"); //prints health healed
                            } else if (mainChar.getHp() >= mainChar.getMaxHp()) {
                                System.out.println("You steel your defences against the enemy. You take " + enemyAtk + " damage.\n");
                            }
                            TimeUnit.MILLISECONDS.sleep(500); //pauses between lines
                        }
                    }
                    if (!opponent.isAlive()) { //if statement that calls method that returns boolean for whether enemy is dead
                        System.out.println(opponent.getOutro()); //prints out a method that returns enemy outro message
                        System.out.println(pause()); //prints out pause text method
                        input.nextLine(); //requires any user input to continue

                        System.out.println("Congratulations on killing " + opponent.getName() +
                                " \n You have now obtained: " + opponent.getItemName(0)); //prints out text with methods calling enemy name and item name
                        mainChar.addItem(opponent.getItem()); //method to add item from enemy killed to inventory
                        System.out.println(pause());
                        input.nextLine();

                        if (grid.checkXY(2, 0)) { //checks grid location to be 2,0
                            boolean javamanLock = true;
                            while (javamanLock) {
                                System.out.println(" 1 - Free Roam \n 2 - End Game"); //prints "you died"
                                trigger = input.nextLine();
                                if (trigger.equals("1")) {
                                    grid.sendStart(); //method to set players x and y coordinates to starting location 2,2
                                    mainChar.setHp(mainChar.getMaxHp()); //sets player hp back to maximum
                                    javamanLock = false; //breaks battle loop
                                } else if (trigger.equals("2")) {
                                    System.out.println("Are you sure? \n type 'CONFIRM'");
                                    trigger = input.nextLine();
                                    if (trigger.equals("CONFIRM")) {
                                        grid.sendEnd();
                                        javamanLock = false;
                                    }
                                }
                            }
                        } else if (grid.checkXY(1, 0) || grid.checkXY(3, 0) || grid.checkXY(1, 4) || grid.checkXY(3, 4)) { //checks grid location to be on boss quadrants
                            grid.sendStart(); //method to set players x and y coordinates to starting location 2,2
                            System.out.println(sendStartText()); //prints out message stating that player returned to start
                        }
                    }

                    if (mainChar.isDead()) { //method to check if player is dead
                        boolean deathLock = true;
                        while (deathLock) {
                            System.out.println(mainCharDeath()); //prints "you died"
                            trigger = input.nextLine();
                            if (trigger.equals("1")) {
                                grid.sendStart(); //method to set players x and y coordinates to starting location 2,2
                                mainChar.setHp(mainChar.getMaxHp()); //sets player hp back to maximum
                                opponent.setHp(opponent.getMaxHp()); //sets enemy hp back to maximum
                                deathLock = false; //breaks battle loop
                            } else if (trigger.equals("2")) {
                                System.out.println("Are you sure? \n type 'CONFIRM'");
                                trigger = input.nextLine();
                                if (trigger.equals("CONFIRM")) {
                                    grid.sendEnd();
                                    deathLock = false;
                                }
                            }
                        }
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

            System.out.println(mainMenu()); //menu selection
            if (grid.checkXY(2, 2)) { //checks grid location to be 2,2
                System.out.println(" 3 - Talk to sister"); //prints npc interaction option
                if (!mainChar.containsItemName("Lucky Charm")) { //checks to see if player has item
                    System.out.println(" 4 - Investigate Area"); //prints investigate lucky charm item option
                }
                if (mainChar.containsItemName("The True Ending!")) {
                    System.out.println(" 0 - Mob Builder");
                }
            }
            command = String.valueOf(input.nextLine()); //variable for menu switch statement

            switch (command) {

                case "w" -> {
                    grid.up();
                    System.out.println();
                } //moves up in the grid

                case "s" -> {
                    grid.down();
                    System.out.println();
                } //moves down in the grid

                case "a" -> {
                    grid.left();
                    System.out.println();
                } //moves left in the grid

                case "d" -> {
                    grid.right();
                    System.out.println();
                } //moves right in the grid
                case "1" -> {
                    System.out.println(mainChar.getName() + "\n\n" + "Your Stats: " + "\n" + mainChar.getHpStat() + "\n" + mainChar.getAtkStat() + "\n");
                    System.out.println(pause());
                    input.nextLine();
                } //returns player stats
                case "2" -> {
                    input = new Scanner(System.in);
                    boolean invLock = true;
                    while (invLock) {
                        System.out.println(inventoryMenu() + mainChar.getSize());//prints inventory menu selection
                        for (int i = 0; i < mainChar.getSize(); i++) {
                            System.out.println("\nItem " + (i + 1) + ": " + mainChar.getItem(i));
                        }//loop to print out all items in array/inventory

                        String invTrigger = input.nextLine();
                        if (invTrigger.equals("1")) { //if statement for description process
                            System.out.println("Type in item number:"); //prints out prompt for user interaction
                            int trigger = Integer.parseInt(input.nextLine()); // int variable to be used in item selection
                            if (mainChar.containsItemIndex(trigger) && trigger > 0) { //if statement checking if item can exist
                                int indexTrigger = trigger - 1; //int variable to select index of item
                                System.out.println(mainChar.getItem(indexTrigger) + "\n- " + mainChar.getDesc(indexTrigger)); //prints out item description
                            } else {
                                System.out.println("Item does not exist."); //prints out invalid item response
                            }
                            input.nextLine();
                        } else if (invTrigger.equals("2")) { //if statement to exit
                            invLock = false; //breaks the inventory loop
                        }
                    }
                } //inventory system
                case "3" -> {
                    if (grid.checkXY(2, 2)) { //checks player grid location is 2,2
                        boolean npcLock = true;
                        while (npcLock) {
                            System.out.println(sibling.getName() + ": Hey " + mainChar.getName() + npcMenu()); //prints out npc interaction menu
                            String trigger = input.nextLine(); //stores String trigger for if statements/menu selection
                            if (trigger.equals("1")) { //if option 1 (help) is selected
                                boolean npcHelp = true;
                                while (npcHelp) { //starts help loop
                                    System.out.println(sibling.getName() + npcHelp());//prints out npc help interaction menu
                                    trigger = input.nextLine();
                                    if (trigger.equals("1")) {
                                        if (mainChar.containsItemName("JA") && mainChar.containsItemName("VA") &&
                                                mainChar.containsItemName("MA") && mainChar.containsItemName("N!")) { //checks whether items are in inventory/array
                                            System.out.println(sibling.getName() + npcJavaman());
                                        } else if (mainChar.containsItemName("JA") && mainChar.containsItemName("VA")
                                                && mainChar.containsItemName("MA")) { //checks whether items are in inventory/array
                                            System.out.println(sibling.getName() + npcBathroom()); //prints out npc guide text
                                            if (!mainChar.containsItemName("Cylinder")) { //checks whether item is in inventory/array
                                                System.out.println(" - Go investigate the bathroom!");
                                            } else {
                                                System.out.println(" - Cyber Bill awaits in the Remix...");
                                            }
                                        } else if (mainChar.containsItemName("JA") && mainChar.containsItemName("VA")) { //checks whether items are in inventory/array
                                            System.out.println(sibling.getName() + npcLounge());
                                            if (!mainChar.containsItemName("Bounty")) { //checks whether item is in inventory/array
                                                System.out.println(" - Go investigate the lounge!"); //prints guide text
                                            } else {
                                                System.out.println(" - Mild Bill awaits in the Wild West...");//prints guide text
                                            }
                                        } else if (mainChar.containsItemName("JA")) { //checks whether item is inventory/array
                                            System.out.println(sibling.getName() + npcBedroom());
                                            if (!mainChar.containsItemName("ID Card")) { //checks whether item is in inventory/array
                                                System.out.println(" - Go investigate the bedroom!"); //prints guide text
                                            } else {
                                                System.out.println(" - Neil Legstrong awaits in Space..."); //prints guide text
                                            }
                                        } else if (!mainChar.containsItemName("JA")) { //checks whether item is not in inventory/array
                                            System.out.println(sibling.getName() + npcKitchen());
                                            if (!mainChar.containsItemName("Silver Sword")) { //checks whether item is in inventory/array
                                                System.out.println(" - Go investigate the kitchen!"); //prints guide text
                                            } else {
                                                System.out.println(" - Barathrum awaits in the Forest..."); //prints guide text
                                            }
                                        }
                                    } else if (trigger.equals("2")) {
                                        System.out.println(sibling.getName() + ": Head to the front door!"); //prints guide text
                                    } else if (trigger.equals("3")) {
                                        System.out.println(sibling.getName() + ": There are three endings."); //prints guide text
                                    } else if (trigger.equals("4")) {
                                        npcHelp = false;
                                    }
                                    System.out.println(pause());
                                    input.nextLine();
                                }
                            } else if (trigger.equals("2")) { //menu selection to show inventory to get buffs
                                boolean npcBuff = true;
                                while (npcBuff) {
                                    System.out.println(sibling.getName() + ": Find me some cool stuff and I'll grant you some power ups! \n Show her your inventory? \n 1 - yes \n 2 - no");
                                    String invTrigger = input.nextLine();
                                    if (invTrigger.equals("1")) {
                                        if (mainChar.containsItemName("Cylinder")) { //checks for item in player inventory
                                            int bonusHealth = mainChar.getDef() + 50; //integer for bonus def
                                            mainChar.setDef(bonusHealth); //method to set bonus def
                                            System.out.println("You show " + sibling.getName() + " the Cylinder and she raises your def to " + bonusHealth);
                                            mainChar.returnItemName("Cylinder").setItemNameDesc("Cylinder (u)", "Attack cylinder...?, +50 atk");
                                        }
                                        if (mainChar.containsItemName("ID Card")) { //checks for item in player inventory
                                            int bonusAtk = mainChar.getAtk() + 50; //integer for bonus atk
                                            mainChar.setAtk(bonusAtk); //method to set bonus atk
                                            System.out.println("You show " + sibling.getName() + " the ID Card and she raises your attack to " + bonusAtk);
                                            mainChar.returnItemName("ID Card").setItemNameDesc("ID Card (u)", "Not just an ID Card, +50 atk");
                                        }
                                        if (mainChar.containsItemName("Bounty")) { //checks for item in player inventory
                                            int bonusHealth = mainChar.getMaxHp() + 500; //integer for bonus health
                                            mainChar.setMaxHp(bonusHealth); //method to set max hp
                                            mainChar.setHp(bonusHealth); //method to set hp to max
                                            System.out.println("You show " + sibling.getName() + " the Bounty and she raises your health to " + bonusHealth);
                                            mainChar.returnItemName("Bounty").setItemNameDesc("Bounty (u)", "A healthy Bounty for Mild Bill, +500 hp");
                                        }
                                        if (mainChar.containsItemName("Silver Sword")) { //checks for item in player inventory
                                            int bonusAtk = mainChar.getAtk() + 50; //integer for bonus atk
                                            mainChar.setAtk(bonusAtk); //method to set bonus atk
                                            System.out.println("You show " + sibling.getName() + " the Silver Sword and she raises your attack to " + bonusAtk);
                                            mainChar.returnItemName("Silver Sword").setItemNameDesc("Silver Sword (u)", "Bigger sword, silver in colour, +50 atk");
                                        }
                                        npcBuff = false;
                                    } else if (invTrigger.equals("2")) {
                                        npcBuff = false;
                                    }
                                }
                            } else if (trigger.equals("3")) {
                                npcLock = false;
                            }
                        }
                    } else {
                        System.out.println("You are too far away from " + sibling.getName() + " to hear her.");
                        System.out.println(pause());
                        input.nextLine();
                    }
                }
                case "4" -> {
                    if (grid.checkXY(2, 2)) { //method to check grid location is 2,2
                        boolean charmLock = true;
                        while (charmLock) {
                            if (!mainChar.containsItemName("Lucky Charm")) { //checks if item is in player inventory/arraylist
                                System.out.println("You found a lucky charm on the floor, would you like to pick it up? \n 1 - yes \n 2 - no");
                                String trigger;
                                trigger = input.nextLine();
                                if (trigger.equals("1")) {
                                    mainChar.addItem(item); //adds item to inventory
                                    System.out.println("You pick up the charm.");
                                    charmLock = false;
                                } else if (trigger.equals("2")) {
                                    System.out.println("You leave the charm on the floor.");
                                    charmLock = false;
                                }
                                System.out.println(pause());
                                input.nextLine();
                            }
                        }
                    }
                }
                case "0" -> {
                    if (grid.checkXY(2, 2)) { //method to check grid location is 2,2
                        boolean mobLock = true;
                        System.out.println("Welcome to mob creator. \n\nEnemy Name:");
                        String mobName = input.nextLine();
                        System.out.println("Intro Flavour Text:");
                        String intro = input.nextLine();
                        System.out.println("Outro Flavour Text:");
                        String outro = input.nextLine();
                        System.out.println("HP:");
                        int hp = Integer.parseInt(input.nextLine());
                        System.out.println("Max HP:");
                        int maxHp = Integer.parseInt(input.nextLine());
                        System.out.println("Atk:");
                        int atk = Integer.parseInt(input.nextLine());
                        System.out.println("X Coordinate:");
                        int x = Integer.parseInt(input.nextLine());
                        System.out.println("Y Coordinate:");
                        int y = Integer.parseInt(input.nextLine());
                        mobCreate(mobName, intro, outro, hp, maxHp, atk, x, y, grid);
                        System.out.println("Item Creator\n Item Name:");
                        String itemName = input.nextLine();
                        System.out.println("Item Desc:");
                        String itemDesc = input.nextLine();
                        int enemyNum = grid.getGridName(x,y).numEnemy() - 1;
                        itemCreate(itemName, itemDesc, x, y, enemyNum, grid);
                    }
                }
                case "take damage" -> {
                    mainChar.takeDamage(500);
                    System.out.println("You took 500 damage! \n" + mainChar.getHpStat() + "\n" + pause());
                    input.nextLine();
                }
                case "cheathp" -> {
                    mainChar.setAtk(10000);
                    mainChar.setHp(100000);
                    mainChar.addItem(grid.getGridName(1, 0).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(1, 4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3, 4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3, 0).getEnemy().getItem());
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
                "\n abrupt silence fall onto the lively room. \"Settle down, y'all...\" said the moustached man behind the " +
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
                "\n somehow still as lively as it was before your fight. On the main road, you look up the road and see " +
                "\n the rough thug from before, could that be Mild Bill?";
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
                "\n pod opens up. Inside is a shining shape that seems to shimmer in the dull space of the broken " +
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
                "\n presents you with the item. You hold onto it as everything fades to the white.";
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
                "\n glimmer inside of it which you pull out and inspect it, it looks like a smooth cylinder with a gem " +
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
                "\n as range limiters gave out. Your sister hands you the item as the world fades to white.";
    }

    static String forestIntro() {
        return "The forest before you chirps with life, a path is carved through the dense grass and trees, leading " +
                "\n further into the wilderness. In front of you lies a bridge, guarded by a solitary knight in silver " +
                "\n armor. The large knight approaches you on the opposite side of the bridge. \"Hold\" the steel knight " +
                "\n bellowed \"State thy names...\" You apprehensively mumble your name \"What tragic " +
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
                "\n respond \"ME!\" the dragon's head raises and it blasts \"THEN MEET YOUR DOOM!\".";
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
        return "\n \"You enter through the door, and find yourself in a dusty old west town. To your south you spy an " +
                "\n unsuspecting saloon, filled with the roughest of the rough. A tumbleweed slides gracefully across " +
                "\n the main road in south of you, drawing your eyes to a rough thug with a chip on his shoulder, and " +
                "\n his hand on his holster...\"\n";
    }

    static String fantasyEntrance() {
        return "\n \"As you walk through the kitchen door, the world around you brightens, faeries flutter across the " +
                "\n path leading to a sprawling vista of hills and grassy fields. To the north of you, there's an opening " +
                "\n to a forest path that seems to lead up towards an imposing mountain...\"\n";
    }

    static String spaceEntrance() {
        return "\n \"As the bedroom door slowly creaks open, you feel your body drift up from the floor. As you enter, " +
                "\n you let out a small gasp as you lose your breath realising the air is still breathable. The sky above " +
                "\n you opens up to an alien moon gently floating in a sea of stars and a beautiful stream of lights that " +
                "\n seem to carry every colour on the spectrum within. You float over to an abandoned space station taking " +
                "\n a peek inside. You see a sign pointing south that reads cargo hold.\"\n";
    }

    static String remixEntrance() {
        return "\n \"You feel a strange pulse pass through you as your hand touches the bathroom door, it slides open " +
                "\n and you see a chaotic hodge-podge of pure imagination. Up north you see a nest of some kind, yet " +
                "\n adorned with the bold characteristics of an ancient race of men?\"\n";
    }

    static String npcKitchen() {
        return ": I heard something weird in the kitchen, I don’t know what it was but it sounded scary! \n";
    }

    static String npcLounge() {
        return ": I think I heard a loud bang in the lounge room? We should go take a look! \n";
    }

    static String npcBedroom() {
        return ": My hair starts to float when I go near the bedroom… It’s pretty funny! \n";
    }

    static String npcBathroom() {
        return ": Something weird is happening in the bathroom, I don’t know how to describe it! \n";
    }

    static String npcJavaman() {
        return ": Let's take a look at the back door again! \n";
    }

    static String doorBreak() {
        return "\n The door breaks down, you feel heat rise from your pock- \n\n" +
                " Did you really think it was going to be that easy? Try playing the game properly.\n\n" +
                " GAME OVER. \n";
    }

    static String sendStartText() {
        return "After defeating the boss, the world comes back into focus as you look " +
                "around, \nyou’re back at the junction of the hallways, all four directions spanning outwards.\n";
    }

    static String criticalStrikeDouble() {
        return "Critical Strike! \nYour sister cheers from the sidelines: " +
                "\n “Yeah! That looked like it hurt him! A few more of those should beat them!\n";
    }

    static String criticalStrikeTriple() {
        return "Critical Strike! \nYour sister cheers from the sidelines: " +
                "\n “Triple damage?! That Lucky Charm must be coming in handy!\n";
    }

    static String mainMenu() {
        return "What would you like to do? \n Move: w - north | a - west | s - south | d - east " +
                "\n 1 - Check your stats \n 2 - Opens your inventory";
    }

    static String inventoryMenu() {
        return "Inventory \n Options: \n 1 - show description \n 2 - exit \n Total number of items: ";
    }

    static String npcMenu() {
        return "! What do you need? \n 1 - help \n 2 - power up \n 3 - exit";
    }

    static String npcHelp() {
        return ": What do you need help with? \n 1 - What do I need to do? \n 2 - How do I heal up? " +
                "\n 3 - How many endings are there? \n 4 - Go back.";
    }

    static String mainCharDeath() {
        return "You died. Talk to your sister and see if you can power up! \n 1 - Restart from last checkpoint? " +
                "\n 2 - End Game";
    }
}



