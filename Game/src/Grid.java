public class Grid {

    private int x;
    private int y;
    private final int dimension = 5;

    private int encounterRate;

    //Grid 2d array
    Locations[][] gridNames = new Locations[dimension][dimension];

    //Grid constructor
    public Grid(int y, int x) {
        if (x >= 0 && x < dimension) {
            this.x = x;
        }
        if (y >= 0 && y < dimension) {
            this.y = y;
        }
    }


    public void setGridNames(int horizontal, int vertical, Locations locations) { //sets location name object in array x and y position
        gridNames[vertical][horizontal] = locations;
    }

    public Locations getGridName(int horizontal, int vertical) { //returns location name object in array x and y position
        return gridNames[vertical][horizontal];
    }
    //end of 2d array components



    //Grid movement
    public void left() {
        if (checkGrid(x - 1)) {
            x -= 1;
        }
    }

    public void right() {
        if (checkGrid(x + 1)) {
            x += 1;
        }
    }

    public void up() {
        if (checkGrid(y - 1)) {
            y -= 1;
        }
    }

    public void down() {
        if (checkGrid(y + 1)) {
            y += 1;
        }
    }

    public boolean checkGrid(int number) { //checks whether coordinates are valid within 5x5 grid
        if (number >= 0 && number < dimension) {
            return true;
        }
        System.out.println("scuffed");
        return false;
    }

    public void death() {
        x = 3;
        y = 3;
    }

    //Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
