import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private String name;
    private String intro;
    private String outro;
    private int hp;
    private int maxHp;
    private int atk;
    private boolean isAlive;

    //constructor
    public Enemy(String name, String intro, String outro, int hp, int maxHp, int atk) {
        this.name = name;
        this.intro = intro;
        this.outro = outro;
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
    }

    //start of item array
    ArrayList<Itemcreate> itemArrayList = new ArrayList<Itemcreate>(); //array for enemy mobs

    public Itemcreate getItem() {
        if(itemArrayList.size() > 0) {
            int rand = new Random().nextInt(itemArrayList.size());
            return itemArrayList.get(rand);
        } else {
            return null;
        }
    }

    public Itemcreate getItemNoRand(int index) { //retrieves enemy name from array list
        return itemArrayList.get(index);
    }

    public String getItemName(int index) { //retrieves enemy name from array list
        return itemArrayList.get(index).getItemName();
    }

    public void addItem(Itemcreate itemObject) { //adds enemy object to arraylist from enemy class
        itemArrayList.add(itemObject);
    }

    public int numItem() { //retrieves number of enemies in the arraylist
        return itemArrayList.size();
    }
    //end of item array

    //getters
    public boolean isAlive() {
        return hp > 0;
    }
    public String getName() {
        return name;
    }
    public String getIntro() {
        return intro;
    }
    public String getOutro() {
        return outro;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    //setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void takeDamage(int hp) {
        this.hp -= hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

}
