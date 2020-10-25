import java.util.ArrayList;

public class Player {

    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int numPotions;
    private int x;
    private int y;

    //Inventory System
    ArrayList<Itemcreate> inventory = new ArrayList<Itemcreate>();

    //Setter
    public void addItem(Itemcreate itemName) { //method to add created item to arraylist
        inventory.add(itemName);
    }

    public void removeItem(int index) {
        inventory.remove(index);
    }

    //Getter
    public String getItem(int index) { //method to return item name at index number in arraylist
        return inventory.get(index).getItemName();
    }

    public String getDesc(int index) { //method to return item name at index number in arraylist
        return inventory.get(index).getItemDesc();
    }

    public int getSize() {
        return inventory.size();
    }

    public boolean containsItem(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            String itemName = inventory.get(i).getItemName();
            if (itemName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getJava(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            String itemName = inventory.get(i).getItemName();
            if (itemName.equals(name)) {
                return i;
            }
        }
        return 0;
    }

//Player Creation
    public Player(String name, int hp, int maxHp, int atk, int def) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
    }

    // Getters Values

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {return maxHp;}

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    // Stats Getters

    public String getHpStat() {
        return "Health: " + getHp();
    }

    public String getAtkStat() {
        return "Attack: " + getAtk();
    }

    // Setters Stats
    public void setHp(int setHp) {
        hp = setHp;
    }

    public void setAtk(int setAtk) {
        atk = setAtk;
    }

    public void setDef(int setDef) {
        def = setDef;
    }

    public void setMaxHp(int setMaxHp) {
        maxHp = setMaxHp;
    }

    public void killPlayer() {
        hp = 0;
    }

    public void takeDamage(int damage) {
        hp = hp - damage;
    }
    public void heal() {
        if (numPotions > 0) {
            numPotions--;
            hp = maxHp;
        }
    }

    //Location
}