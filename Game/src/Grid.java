public class Grid {

    private String name;
    private int position;
    private int encounterRate;

    public Grid(String name, int position) {
        this.name = name;
        this.position = position;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
