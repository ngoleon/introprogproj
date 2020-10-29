public class Grid {

    private int x;
    private int y;
    private final int dimension = 5;


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
        if (x == 2 && y == 0) {
        } else if (x == 2 && y == 1) { //movement constraints
        } else if (x == 2 && y == 3) {
        } else if (x == 2 && y == 4) {
        } else if (x == 4 && y == 1) {
        } else if (x == 4 && y == 3) {
        } else if (checkGrid(x - 1)) { //method to check whether movement in grid is valid
            x -= 1;
        }
    }

    public void right() {
        if (x == 2 && y == 0) {
        } else if (x == 2 && y == 1) { //movement constraints
        } else if (x == 2 && y == 3) {
        } else if (x == 2 && y == 4) {
        } else if (x == 0 && y == 1) {
        } else if (x == 0 && y == 3) {
        } else if (checkGrid(x + 1)) { //method to check whether movement in grid is valid
            x += 1;
        }
    }

    public void up() {
        if (x == 1 && y == 2) {
        } else if (x == 3 && y == 2) { //movement constraints
        } else if (x == 1 && y == 4) {
        } else if (x == 3 && y == 4) {
        } else if (checkGrid(y - 1)) { //method to check whether movement in grid is valid
            y -= 1;
        }
    }

    public void down() {
        if (x == 1 && y == 2) {
        } else if (x == 3 && y == 2) { //movement constraints
        } else if (x == 1 && y == 0) {
        } else if (x == 3 && y == 0) {
        } else if (checkGrid(y + 1)) { //method to check whether movement in grid is valid
            y += 1;
        }
    }

    public boolean checkGrid(int number) { //checks whether coordinates are valid within 5x5 grid
        if (number >= 0 && number < dimension) {
            return true;
        }
        System.out.println("You hit the wall of your house.");

        return false;
    }

    public void sendStart() { //sends player to starting grid location 2,2
        x = 2;
        y = 2;
    }

    public void sendEnd() { //sends player to ending grid location 1,1
        x = 1;
        y = 1;
    }


    //Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean checkXY(int x, int y) {
        int checkX = this.x;
        int checkY = this.y;
        if (x == checkX && y == checkY) {
            return true;
        }
        return false;
    }
}
