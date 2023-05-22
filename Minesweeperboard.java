import java.awt.*;
import java.awt.Taskbar.Feature;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeperboard {



  public static void main(String[] args) {

    JFrame window = new JFrame();
    JPanel gamePanel = new JPanel();

    window.setSize(300, 300);
    gamePanel.setSize(300, 300);
    gamePanel.setLayout(new GridLayout(3, 3));
    window.add(gamePanel);

    






    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
  
  }
}
