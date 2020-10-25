import java.util.ArrayList;
import java.util.Random;

public class Locations {

    public String locationName;

    //constructor
    public Locations(String locationName) {
        this.locationName = locationName;
    }
    ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>(); //array for enemy mobs

    public Enemy getEnemy() {
        if(enemyArrayList.size() > 0) {
            int rand = new Random().nextInt(enemyArrayList.size());
            return enemyArrayList.get(rand);
        } else {
            return null;
        }
    }

    public Enemy getEnemyNumber(int number) {
        return enemyArrayList.get(number);
    }
    public String getEnemyName(int index) { //retrieves enemy name from array list
        return enemyArrayList.get(index).getName();
    }

    public void addEnemy(Enemy enemyObject) { //adds enemy object to arraylist from enemy class
        enemyArrayList.add(enemyObject);
    }

    public int numEnemy() { //retrieves number of enemies in the arraylist
        return enemyArrayList.size();
    }

    //getter
    public String getLocationName() { //retrieves location name
        return locationName;
    }
}
