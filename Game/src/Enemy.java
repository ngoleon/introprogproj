public class Enemy {
    private String name;
    private int hp;
    private int maxHp;
    private int atk;

    //constructor
    public Enemy(String name, int hp, int maxHp, int atk) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
    }

    //getters
    public boolean isAlive() {
        return hp > 1;
    }
    public String getName() {
        return name;
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
