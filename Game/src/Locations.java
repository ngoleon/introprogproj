import java.util.ArrayList;

public class Locations {

    public String locationName;

    //constructor
    public Locations(String locationName) {
        this.locationName = locationName;
    }
    ArrayList<Locations> locationsArrayList = new ArrayList<Locations>(); //array for enemy mobs
    ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>(); //array for enemy mobs

    public Enemy getEnemy(int index) {
        return enemyArrayList.get(index);
    }

    public Locations getLocations(int index) {
        return locationsArrayList.get(index);
    }

    public String getEnemyName(int index) { //retrieves enemy name from array list
        return enemyArrayList.get(index).getName();
    }

    public void addEnemy(Enemy enemyObject) { //adds enemy object to arraylist from enemy class
        enemyArrayList.add(enemyObject);
    }

    public void addLocation(Locations locationObject) { //adds enemy object to arraylist from enemy class
        locationsArrayList.add(locationObject);
    }
    public int numEnemy() { //retrieves number of enemies in the arraylist
        return enemyArrayList.size();
    }

    //getter
    public String getLocationName() { //retrieves location name
        return locationName;
    }
}
