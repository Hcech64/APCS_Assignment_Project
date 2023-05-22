import java.util.*;


public class Example{
    public static void main(String[] args) {
        // Welome to this AP Computer science assignment. In this assignment, you will use you kowledge of arrays and 2d arrays in order to constuct a minesweeper game
        // import keyboard handler
        
        Scanner k = new Scanner(System.in);

        System.out.println("What size field do you want? Small, Medium, or Large?\n");
        String tempNextLine = k.nextLine();
        while (!tempNextLine.equalsIgnoreCase("small") && !tempNextLine.equalsIgnoreCase("medium") && !tempNextLine.equalsIgnoreCase("large")) {
            System.out.println("What size field do you want? Small, Medium, or Large?\n");
        }
        int size = 0;
        if (tempNextLine.equalsIgnoreCase("small")) {
            size = 3;
        } else if (tempNextLine.equalsIgnoreCase("medium"))  {
            size = 6;
        } else if (tempNextLine.equalsIgnoreCase("large")) {
            size = 9;
        }

    

        double difficulty = 0.0;
        
        while(difficulty == 0.0){
            System.out.println("what difficulty do you want? easy, medium, or hard?");
            String temp = k.nextLine();

            if(temp.equalsIgnoreCase("easy")){
                difficulty = 0.1;
            }
            if(temp.equalsIgnoreCase("medium")){
                difficulty = 0.5;
            }
            if(temp.equalsIgnoreCase("hard")){
                difficulty = 0.7;
            }
        }
        
        int[][] grid = new int[size][size];
        int[][] visible = new int[size][size];
        
    }


}

