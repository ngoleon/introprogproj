public class Player {

    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;

    private int numPotions;

    public Player(String name, int hp, int atk) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
    }


    // Getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Stats Getters
    public String getNameStat() {
        return "Name: " + getName();
    }

    public String getHpStat() {
        return "Health: " + getHp();
    }

    public String getAtkStat() {
        return "Attack: " + getAtk();
    }

    // Setters
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

    public void heal() {
        if (numPotions > 0) {
            numPotions--;
            hp = maxHp;
        }
    }
}