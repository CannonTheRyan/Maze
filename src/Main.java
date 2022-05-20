import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Maze m = new Maze(50);

        JFrame frame = new JFrame("MAZE");
        frame.setSize(1000, 1000);
        JPanel panel = new JPanel(new GridLayout(20, 20, 0, -5));
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                panel.add(new JLabel(maze[i][j]));
            }
        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }



}
