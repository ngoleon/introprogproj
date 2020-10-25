import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

// todo make sure to include alternate ending, eg no javaman fight
// todo make sure to have option to pick up item
// todo make sure to create algorithms

public class MainGame {

    static String textPrint() {
       return "this is what i really wanted to test out anyways to see if this would work \n " +
               "and this is the second line of the entire thing";
    }

    static void locationCreate(String locationName, int x, int y, Grid grid) { //method to create locations by setting parameters,
        Locations locations = new Locations(locationName); //constructs a location object
        grid.setGridNames(x, y, locations); //stores location object in 2d grid array at x and y coordinates
    }

    static void mobCreate(String name, String intro, String outro, int hp, int maxHp, int atk, int x, int y, Grid grid) { //method to create mobs by setting parameters
        Enemy mob = new Enemy(name, intro, outro, hp, maxHp, atk);
        grid.getGridName(x,y).addEnemy(mob);
    }

    static void itemCreate(String name, String desc, int x, int y, int enemyNum, Grid grid) {
        Itemcreate item = new Itemcreate (name, desc);
        grid.getGridName(x,y).getEnemyNumber(enemyNum).addItem(item);
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
        Player mainChar = new Player(name,1000,100,10,10);

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(intro() + "\n" + pause());
        input.nextLine();
        //mob creation

        locationCreate("Fantasy Kitchen B", 0,0, grid);
        locationCreate("Fantasy Kitchen A", 0,1, grid);
        locationCreate("Entrance FK & SA", 0,2, grid);
        locationCreate("Space Adventure A", 0,3, grid);
        locationCreate("Space Adventure B", 0,4, grid);
        locationCreate("Fantasy Kitchen C", 1,0, grid);
        locationCreate("Dead Space", 1,1, grid);
        locationCreate("Corridor Left", 1,2, grid);
        locationCreate("Dead Space", 1,3, grid);
        locationCreate("Space Adventure C", 1,4, grid);
        locationCreate("Backdoor", 2,0, grid);
        locationCreate("Corridor Top", 2,1, grid);
        locationCreate("Start", 2,2, grid);
        locationCreate("Corridor Bottom", 2,3, grid);
        locationCreate("Front Door", 2,4, grid);
        locationCreate("Remix C", 3,0, grid);
        locationCreate("Dead Space", 3,1, grid);
        locationCreate("Corridor Right", 3,2, grid);
        locationCreate("Dead Space", 3,3, grid);
        locationCreate("Wild West - Mineshaft", 3,4, grid);
        locationCreate("Remix B", 4,0, grid);
        locationCreate("Remix A", 4,1, grid);
        locationCreate("Entrance R & WW", 4,2, grid);
        locationCreate("Wild West - Entrance", 4,3, grid);
        locationCreate("Wild West - Saloon", 4,4, grid);

        mobCreate("FK BOSS", "intro", "outro",10,100,100,1, 0, grid);
        mobCreate("Neil Legstrong",controlRoomIntro(), controlRoomOutro(),10,100,100,1, 4, grid);
        mobCreate("Fuzz Aldrin",cargoHoldIntro(), cargoHoldOutro(),10,100,100,0, 4, grid);
        mobCreate("Mild Bill",mildBillIntro(), mildBillOutro(),10,100,100,3, 4, grid);
        mobCreate("Billy The Skid",saloonIntro(), saloonOutro(),10,100,100,4, 4, grid);
        mobCreate("REMIX BOSS","intro", "outro",10,100,100,3, 0, grid);

        itemCreate("JA", "First Piece", 1,0,0, grid);
        itemCreate("VA", "Second Piece",1,4,0, grid);
        itemCreate("MA", "Third Piece",3,4,0, grid);
        itemCreate("N!", "Fourth Piece",3,0,0, grid);
        itemCreate("Bounty", "WANTED - Mild Bill (last seen in Mineshaft)",4,4,0, grid);
        itemCreate("ID Card", "Just an ID card",0,4,0, grid);
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
            if (grid.checkXY(0,2)) {
                System.out.println("\n You have reached the end of the corridor. " +
                        "To the north is the door to the kitchen and to the south is the door to the bedrooms.\n");
            }

            if (grid.checkXY(4,2)) {
                System.out.println("\n You have reached the end of the corridor. " +
                        "To the north is the door to the bathroom and to the south is the door to the lounge room.\n");
            }

            if (grid.checkXY(2,1) || grid.checkXY(2,3) || grid.checkXY(1,2) || grid.checkXY(3,2)) {
                System.out.println("\n You step out into the corridor. There isn’t much going on. \n");
            }

            if (grid.checkXY(2,4)) {
                mainChar.setHp(mainChar.getMaxHp());
                System.out.println("\n You reach the safe spot and call out “SAFE”. " +
                        "Knowing the world is just a little less dangerous fills you with the strength to move forward.\n" +
                        "\n You fully heal to: " + mainChar.getHp() + " HP \n");
                System.out.println("\n");
            }

            boolean lock = true;
            if (grid.checkXY(2,0)) {
                if (mainChar.containsItem("JA") && mainChar.containsItem("VA")&& mainChar.containsItem("MA")&& mainChar.containsItem("N!")) {
                    System.out.println("You feel a strong presence behind the back door. Would you like insert the strange shapes into the door? \n yes \n no");
                    String trigger = input.nextLine();
                    if (trigger.equals("yes")) {
                        mainChar.removeItem(mainChar.getJava("JA"));
                        mainChar.removeItem(mainChar.getJava("VA"));
                        mainChar.removeItem(mainChar.getJava("MA"));
                        mainChar.removeItem(mainChar.getJava("N!"));
                        System.out.println(pause());
                        input.nextLine();
                    lock = false;} else if (trigger.equals("no")){
                        System.out.println("You follow your instincts and retreat.");
                    }
                } else if (mainChar.containsItem("JA") || mainChar.containsItem("VA") || mainChar.containsItem("MA") || mainChar.containsItem("N!")){
                    System.out.println("You feel a slight warmth in your pockets from the shapes. " +
                            "You hear your sister's voice echo across the corridor, \"four pieces are needed to unlock the backdoor!\"\n");
                } else {
                    System.out.println("\n You try to open the door to the backyard and find that it won’t budge. " +
                            "Upon closer inspection you see four shapes emblazoned on the door. What are they used for?\n");
                }
                if (!lock) {
                    mobCreate("javaman",javamanEncounter(), javamanDeath(),10,100,100,2, 0, grid);
                    itemCreate("Nice.", "Niiiiice",2,0,0, grid);
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

            if(enemyExists) { //encounters enemy at location "second" via player x and y coordinates
                if(opponent.isAlive()) {
                    System.out.println(opponent.getIntro());
                    System.out.println(pause());
                    input.nextLine();
                }

                while(opponent.isAlive()) { // loop to ensure player stays in battle with enemy
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
                            TimeUnit.MILLISECONDS.sleep(500);
                            mainChar.takeDamage(enemyAtk - playerDef);// takes damage = to mob attack to player - defence
                            System.out.println("You took " + (enemyAtk - playerDef) + " damage!\n");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                        case "strong attack" -> {
                            int strongAttack = (int)(Math.random()*(2-1+1)+1);
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
                        case "defend" -> {
                            mainChar.takeDamage(enemyAtk - (playerDef * 2));
                            System.out.println("You steel your defences against the enemy. You took " + (enemyAtk - playerDef) + " damage!\n");
                            TimeUnit.MILLISECONDS.sleep(500);
                        }
                    }
                    if(!opponent.isAlive()) {
                        System.out.println(opponent.getOutro());
                        System.out.println(pause());
                        input.nextLine();

                        System.out.println("Congratulations on killing " + opponent.getName() + " \n You have now obtained: " + opponent.getItemName(0));
                        mainChar.addItem(opponent.getItem());
                        System.out.println(pause());
                        input.nextLine();

                        if (grid.checkXY(2,0)){
                            grid.sendEnd();
                            break;
                        }else if(grid.checkXY(1,0) || grid.checkXY(3,0) || grid.checkXY(1,4) || grid.checkXY(3,4)) {
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

            if (grid.checkXY(1,1)) {
                break;
            } //condition to end the game after defeating the final boss

            System.out.println("Your Location: " + grid.getGridName(grid.getX(), grid.getY()).getLocationName());//prints out Grid name in array

            if (mainChar.isDead()) {
                System.out.println("You died.");
                grid.sendStart();
            }

            System.out.println("What would you like to do? \n Move: up | down | left | right \n take damage \n help \n stats \n inventory \n"); //menu selection
            command = String.valueOf(input.nextLine());

            switch (command) {
//moves up in the grid
                case "up" -> {
                    grid.up();
                    System.out.println();
                }
//moves down in the grid
                case "down" -> {
                    grid.down();
                    System.out.println();
                }
//moves left in the grid
                case "left" -> {
                    grid.left();
                    System.out.println();
                }
//moves right in the grid
                case "right" -> {
                    grid.right();
                    System.out.println();
                }
                case "stats" -> {
                    System.out.println("Your Stats: " + "\n" + mainChar.getHpStat() + "\n" + mainChar.getAtkStat() + "\n");
                    System.out.println(pause());
                    input.nextLine();
                }
                case "help" -> {
                    System.out.println("This is the help menu! \n " + "");
                    System.out.println(pause());
                    input.nextLine();
                }
                case "inventory" -> {
                    System.out.println("This is your inventory! \n Total number of items: " + mainChar.getSize());
                    for (int i = 0; i < mainChar.getSize(); i++) {
                        System.out.println("\nItem " + (i+1) + ": " + mainChar.getItem(i));
                        System.out.println("- " + mainChar.getDesc(i));
                    }
                    System.out.print(pause());
                    input.nextLine();
                }
                case "take damage" -> {
                    mainChar.takeDamage(500);
                    System.out.println("You took 500 damage! \n" + mainChar.getHpStat() + "\n" + pause());
                    input.nextLine();
                }
                case "cheat" -> {
                    mainChar.addItem(grid.getGridName(1,0).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(1,4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3,4).getEnemy().getItem());
                    mainChar.addItem(grid.getGridName(3,0).getEnemy().getItem());
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
                "did you let him \nout?!\" \n";
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
                "\n\n \"Hey, you wanna play?\" you ask, leading her down the corridor. \n";
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
        return"You walk towards the thug in the middle of the street. He looks up and mumbles \"You the deputy?\" You nod " +
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
        return"You follow the corridor out into a very wide open space, shipping containers strewn through the air, " +
                "\n a few of them burst open in their sides or in other seemingly random locations. \n" +
                "\n \"Hold it\" you hear someone call out from amongst the containers. Your sister, not heeding the warning" +
                "\n  steps forward and between a flash of light and your quick reflexes you manage to save her from a laser " +
                "\n shot that now stands. You hear the voice gasp in shock as a hairy man slowly comes into view. \"Y-you’re" +
                "\n  one of them\" he says \"This is useless on you\". He throws his gun off to the side and pulls into a " +
                "\n weird fighting stance, even in zero gravity he looked completely at ease until he kicked off of a nearby" +
                "\n  heavy object and hurtled at you. \n";
    }

    static String cargoHoldOutro() {
        return "You both trade some blows until you finally manage to land a clean hit to the sternum making him wheeze " +
                "\n before grabbing him by his scruffy hair and slamming his head into a nearby container knocking him out. " +
                "\n You catch a glimpse of his ID card and take it from him while he’s knocked out. \n" +
                "\n You pull your sister in close double checking to make sure she’s okay. \n" +
                "\n You look around. To the north you saw the door you used to enter the cargo hold and to the east a " +
                "\n sign marked Control Room. \n";
    }

    static String controlRoomIntro() {
        return "You walk into the room, there are loose wires sparking everywhere with a giant console in the middle of " +
                "\n the room and what looks like a giant tube at the back of the space sitting adjacent to the computer. " +
                "\n You walk up and inspect the display it reads [Please verify peer authentication] with two card readers " +
                "\n illuminated by a strip of lights. You look at the card that you took from the last room and realise it " +
                "\n looks very similar to the other one you picked up. It’s worth a shot you figure as you slide both cards " +
                "\n into the readers. The screen flashes green telling you it has been verified and you hear a hiss from " +
                "\n behind you as the pod opens up. Inside is a shining letter ‘J’ that seems to shimmer in the dull space " +
                "\n of the broken control room. You go over to take a look when you hear a shot whizz past your ear. You " +
                "\n turn around to see a rather handsome man, golden hair perfectly styles and teeth so white he probably " +
                "\n didn’t need a flashlight while getting around. \n" +
                "\n \"Hand it over kid\" He warns, \"I know a fella who pays pretty good for that stuff\". You size him up, " +
                "\n after the last two encounters you’ve had here you’re not even close to surprised, even a little tired " +
                "\n of the repetition. You raise your hands in surrender when you see a small flicker of movement from the " +
                "\n corner of your eye as your sister bursts out of hiding and winds up a punch very similar to the one you " +
                "\n made in the cafeteria but, due to her stunning lack of height manages to land an incredibly clean hit " +
                "\n to the stomach forcing him to drop his gun which she scampers away with. You see anger flare in his " +
                "\n eyes and you immediately close the distance ready for another scrap. \n";
    }

    static String controlRoomOutro() {
        return "You land the final blow as he slumps to the ground. After this, it’s not hard to imagine that you’re " +
                "\n kind of over space adventures. You sister runs up and hugs you and you ruffle her hair a little and she " +
                "\n presents you with the letter. You hold onto it as everything fades to the white.";
    }
    }

