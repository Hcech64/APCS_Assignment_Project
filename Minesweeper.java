import java.util.*;

public class Minesweeper{

    //choose a box to click and see the reaction
    /*public static int[][] click(int x, int y, String[][] visible, int[][] hidden) {
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("Where would you like to go? Eg. 4, 5");
        String stringCords = keyboard.nextLine();
        String value = stringCords.replaceAll("[^0-9]", "");
        int[] cords = {0, 0};
        String stringX = value.substring(0, 1);
        String stringY = value.substring(value.length() - 2, value.length() - 1);
        int X = Integer.parseInt(stringX);
        int Y = Integer.parseInt(stringY);

        //visible[X][Y] = 
    }*/

    public static void main(String[] args) {
        // Welome to this AP Computer science assignment. In this assignment, you will
        // use you kowledge of arrays and 2d arrays in order to constuct a minesweeper
        // game
        // import keyboard handler

        Scanner k = new Scanner(System.in);

        int size = 0;

        while (size == 0) {
            System.out.println("What size field do you want? Small, Medium, or Large?\n");
            String temp = k.nextLine();

            if ( temp.equalsIgnoreCase("small")) {
                size = 3;
            } else if ( temp.equalsIgnoreCase("medium")) {
                size = 6;
            } else if ( temp.equalsIgnoreCase("large")) {
                size = 9;
            }
        }

        double difficulty = 0.0;

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
        }

        String[][] visible = new String[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                visible[x][y] = "X";
            }
        }
        PrintBoard(visible);
        

        int[][] hidden = new int[size][size];
        bombPlacer(hidden, size, difficulty);
        PrintBoard(hidden);
        
        k.close();
    }

    public static void bombPlacer(int[][] grid, int size, double difficulty) {
        
        //set all to zero
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                grid[x][y] = 0;
            }
        }
        
        int bombCounter = 0;
        bombCounter = (int)(size * size * difficulty);

        for(int i = 0; i < bombCounter; i++){
            grid[(int) (Math.random()*size)][(int)( Math.random()*size)] = -1;
        }
    }


    //print out the current board
    public static void PrintBoard(int[][] grid){
        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[0].length; y++){
                System.out.print( grid[x][y] + "  ");
            }
            System.out.println();
        }
        System.out.println("\n \n");
    }
    public static void PrintBoard(String[][] grid){
        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[0].length; y++){
                System.out.print( grid[x][y] + "  ");
            }
            System.out.println();
        }
        System.out.println("\n \n");
    }

}
