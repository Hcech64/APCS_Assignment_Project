import java.util.*;

public class Minesweeper {

    public static void main(String[] args) {
    
        // Ask user for Board size and game difficulty

        Scanner k = new Scanner(System.in);
        int size = 0;
        while (size == 0) {
            System.out.println("What size field do you want? Small, Medium, or Large?\n");
            String temp = k.nextLine();

            if (temp.equalsIgnoreCase("small")) {
                size = 3;
            } else if (temp.equalsIgnoreCase("medium")) {
                size = 6;
            } else if (temp.equalsIgnoreCase("large")) {
                size = 9;
            }
        }

        double difficulty = 0.0;
        boolean hackSwitch = false; // reveals board for dbug perposes (or winning)

        while (difficulty == 0.0) {
            System.out.println("what difficulty do you want? easy, medium, or hard?");
            String temp = k.nextLine();

            if (temp.equalsIgnoreCase("easy")) {
                difficulty = 0.1;
            }
            if (temp.equalsIgnoreCase("medium")) {
                difficulty = 0.5;
            }
            if (temp.equalsIgnoreCase("hard")) {
                difficulty = 0.7;
            }
            if (temp.equalsIgnoreCase("ginger")) {
                difficulty = 0.1;
                hackSwitch = true;
            }
        }

        //Initialize and se up the boards
        String[][] visible = new String[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                visible[x][y] = "\u001B[31m" + "X" + "\u001B[0m"; //set all elements of the visible array to "X"
            }
        }
        int[][] hidden = new int[size][size];
        bombPlacer(hidden, size, difficulty);
        hidden = BombAdder(hidden);

        // count the total bombs
        int bomb = 0;
        for (int[] i : hidden) {
            for (int j : i) {
                if(j == -1){bomb++;}
            } 
        }

        PrintBoard(visible);//prints the board to start

        // game loop - runs until win/loose
        int win = 0;
        while (win == 0) {
            if (hackSwitch == true) {PrintBoard(hidden);} // prints hidden for dbug


            System.out.println("Where would you like to go? Eg. 4 5");
            String stringCords = k.nextLine();

            int X = Integer.parseInt(stringCords.substring(0, 1));
            int Y = Integer.parseInt(stringCords.substring(stringCords.length() - 1));

            System.out.println("Mining hole at: " + X + ", " + Y);

            visible[X][Y] = Integer.toString(hidden[X][Y]);
            PrintBoard(visible);

            //check for loose
            if (hidden[X][Y] == -1) {win = -1;}

            // check for win
            int flags =0;
            for (String[] i : visible) {
                for (String j : i) {
                    if(j.equals("\u001B[31m" + "X" + "\u001B[0m")){flags++;}
                } 
            }
            if(flags == bomb){win = 1;};
        }

        if (win == 1) {
            System.out.println("You win!");
        }
        if (win == -1) {
            System.out.println("You clicked a bomb.You loose");
        }
        k.close();
    }

    // randomly places bombs on the baord
    public static void bombPlacer(int[][] grid, int size, double difficulty) {

        // set all to zero
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = 0;
            }
        }

        // places the bombs at random coords
        int bombCounter = 0;
        bombCounter = (int) (size * size * difficulty);

        for (int i = 0; i < bombCounter; i++) {
            grid[(int) (Math.random() * size)][(int) (Math.random() * size)] = -1;
        }
    }

    // adds the number of bombs surroundig a coordinate
    public static int[][] BombAdder(int[][] hidden) {

        int[][] hidden2 = hidden;
        for (int column = 0; column <= hidden.length - 1; column++) {
            for (int row = 0; row <= hidden[column].length - 1; row++) {

                int bomb = 0;
                int[][] mini = AroundTile(hidden, column, row);
                for (int i = 0; i <= mini.length - 1; i++) {
                    for (int j = 0; j <= mini[i].length - 1; j++) {
                        if (mini[i][j] == -1) {
                            bomb++;
                        }
                    }
                }
                if (hidden2[column][row] != -1) {
                    hidden2[column][row] += bomb;
                }
            }
        }
        return hidden2;
    }

    // create an small 3x3 array of all the spaces around a coord
    public static int[][] AroundTile(int[][] hidden, int x, int y) {

        int size = hidden.length - 1;

        int[][] mini = new int[3][3];
        mini[1][1] = hidden[x][y];


        // make sure that code doesnt call outside of the array. Checks for edges and corners
        if (x != 0 && y != 0) {
            mini[0][0] = hidden[x - 1][y - 1];
        }
        if (x != 0) {
            mini[0][1] = hidden[x - 1][y];
        }
        if (x != 0 && y != size) {
            mini[0][2] = hidden[x - 1][y + 1];
        }
        if (y != 0) {
            mini[1][0] = hidden[x][y - 1];
        }
        if (y != size) {
            mini[1][2] = hidden[x][y + 1];
        }
        if (x != size && y != 0) {
            mini[2][0] = hidden[x + 1][y - 1];
        }
        if (x != size) {
            mini[2][1] = hidden[x + 1][y];
        }
        if (x != size && y != size) {
            mini[2][2] = hidden[x + 1][y + 1];
        }
        return mini;
    }

    // print out the current board - for hidden which is an Int[][]
    public static void PrintBoard(int[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == -1) {
                    System.out.print(grid[x][y] + "  ");
                } else {
                    System.out.print(" " + grid[x][y] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("\n \n");
    }
    // print out the current board - for visible which is an String[][]
    public static void PrintBoard(String[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y].equals("-1")) {
                    System.out.print(grid[x][y] + "  ");
                } else {
                    System.out.print(" " + grid[x][y] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("\n \n");
    }
}