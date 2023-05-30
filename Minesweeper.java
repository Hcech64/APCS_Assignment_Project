import java.util.*;

public class Minesweeper{

    //choose a box to click and see the reaction
    public static String[][] click(String[][] visible, int[][] hidden, int size) {
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("Where would you like to go? Eg. 4, 5");
        String stringCords = keyboard.nextLine(); // ERROR HERE
        String value = stringCords.replaceAll("[^0-9]", "");  // POSSIBLY HERE
        String stringX = value.substring(0, 1);
        String stringY = value.substring(value.length() - 2, value.length() - 1);
        int X = Integer.parseInt(stringX);
        int Y = Integer.parseInt(stringY);

        visible[X][Y] = "_";
        visible = revealAround(size, X, Y, visible, hidden);
        PrintBoard(visible);
        keyboard.close();
        return visible;
    }

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

        //game loop
        Boolean win = false;
        while(win == false){
            PrintBoard(click(visible, hidden, size));
        }
        
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

    public static int[][] BombAdder(int[][] hidden){
        
        int[][]hidden2 = hidden;
        for (int column = 0; column <= hidden.length -1; column++) {

            for(int row = 0; row <= hidden[column].length -1; row++){
                if(column != 0){
                    hidden2[column -1][row] = hidden2[column][row] += hidden[column-1][row];
                    if(row != 0){
                        if(hidden[column ][row-1] != -1){
                            hidden2[column ][row-1] = Integer.toString(hidden[x][y-1]);
                        }
                        if(hidden[column-1 ][row+1]!= -1){
                            hidden2[column-1 ][row-1] = Integer.toString(hidden[x][y-1]);
                        }
                    } 
                    if(row != hidden[column].length){
                        if(hidden[column ][row+1]!= -1){
                            hidden2[column ][row+1] = Integer.toString(hidden[x][y+1]);
                        }
                        if(hidden[column][row+1]!= -1){
                            hidden2[column-1 ][row+1] = Integer.toString(hidden[x][y+1]);
                        }
                    }
                }
                else if(column != hidden[column].length){
                    hidden2[column -1][row] = Integer.toString(hidden[x-1][y]);
                    if(row != 0){
                        if(hidden[column ][row-1]!= -1){
                            hidden2[column ][row-1] = Integer.toString(hidden[x][y-1]);
                        }
                        if(hidden[column+1 ][row-1]!= -1){
                            hidden2[column+1 ][row-1] = Integer.toString(hidden[x][y-1]);
                        }
                    } 
                    if(row != hidden[column].length){
                        if(hidden[column ][row-1]!= -1){
                            hidden2[column ][row-1] = Integer.toString(hidden[x][y+1]);
                        }
                        if(hidden[column+1 ][row+1]!= -1){
                            hidden2[column+1 ][row+1] = Integer.toString(hidden[x][y+1]);
                        }
                    }
                }
                if(row != 0){
                    hidden2[column -1][row] = Integer.toString(hidden[x-1][y]);
                    if(column != 0){
                        hidden2[column-1 ][row] = Integer.toString(hidden[x][y-1]);
                        hidden2[column-1 ][row-1] = Integer.toString(hidden[x][y-1]);
                    } 
                    if(column != hidden[column].length){
                        hidden2[column+1 ][row] = Integer.toString(hidden[x][y+1]);
                        hidden2[column+1 ][row-1] = Integer.toString(hidden[x][y+1]);
                    }
                }
                if(row != hidden[column].length){
                    hidden2[column -1][row] = Integer.toString(hidden[x-1][y]);
                    if(column != 0){
                        hidden2[column-1 ][row] = Integer.toString(hidden[x][y-1]);
                        hidden2[column-1 ][column+1] = Integer.toString(hidden[x][y-1]);
                    } 
                    if(column != hidden[column].length){
                        hidden2[column+1 ][row] = Integer.toString(hidden[x][y+1]);
                        hidden2[column+1 ][row+1] = Integer.toString(hidden[x][y+1]);
                    }
                }
            }
        }

        return hidden2;
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


    public static String[][] revealAround(int size, int x, int y, String[][] visible, int[][] hidden){
        if(x != 0){
            visible[x -1][y] = Integer.toString(hidden[x-1][y]);
            if(y != 0){
                if(!visible[x ][y-1].equals("-1")){
                    visible[x ][y-1] = Integer.toString(hidden[x][y-1]);
                }
                if(!visible[x-1 ][y+1].equals("-1")){
                    visible[x-1 ][y-1] = Integer.toString(hidden[x][y-1]);
                }
            } 
            if(y != size){
                if(!visible[x ][y+1].equals("-1")){
                    visible[x ][y+1] = Integer.toString(hidden[x][y+1]);
                }
                if(!visible[x][y+1].equals("-1")){
                    visible[x-1 ][y+1] = Integer.toString(hidden[x][y+1]);
                }
            }
        }
        else if(x != size){
            visible[x -1][y] = Integer.toString(hidden[x-1][y]);
            if(y != 0){
                if(!visible[x ][y-1].equals("-1")){
                    visible[x ][y-1] = Integer.toString(hidden[x][y-1]);
                }
                if(!visible[x+1 ][y-1].equals("-1")){
                    visible[x+1 ][y-1] = Integer.toString(hidden[x][y-1]);
                }
            } 
            if(y != size){
                if(!visible[x ][y-1].equals("-1")){
                    visible[x ][y-1] = Integer.toString(hidden[x][y+1]);
                }
                if(!visible[x+1 ][y+1].equals("-1")){
                    visible[x+1 ][y+1] = Integer.toString(hidden[x][y+1]);
                }
            }
        }
        if(y != 0){
            visible[x -1][y] = Integer.toString(hidden[x-1][y]);
            if(x != 0){
                visible[x-1 ][y] = Integer.toString(hidden[x][y-1]);
                visible[x-1 ][y-1] = Integer.toString(hidden[x][y-1]);
            } 
            if(x != size){
                visible[x+1 ][y] = Integer.toString(hidden[x][y+1]);
                visible[x+1 ][y-1] = Integer.toString(hidden[x][y+1]);
            }
        }
        if(y != size){
            visible[x -1][y] = Integer.toString(hidden[x-1][y]);
            if(x != 0){
                visible[x-1 ][y] = Integer.toString(hidden[x][y-1]);
                visible[x-1 ][y+1] = Integer.toString(hidden[x][y-1]);
            } 
            if(x != size){
                visible[x+1 ][y] = Integer.toString(hidden[x][y+1]);
                visible[x+1 ][y+1] = Integer.toString(hidden[x][y+1]);
            }
        }
        return visible;
        
    }
}
