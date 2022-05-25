import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI implements KeyListener, ActionListener
{
    private Maze maze;
    private JFrame frame;
    private JPanel sliderPanel;
    private JPanel mazePanel;
    private JSlider slider;

    public GUI()
    {
    }

    public void initialize()
    {
        frame = new JFrame("MAZE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.addKeyListener(this);
        frame.setSize(1000, 1000);

        slider = new JSlider(30, 70);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        sliderPanel = new JPanel();
        JButton sliderButton = new JButton("Submit");
        JLabel sliderText = new JLabel("What size do you want your maze to be?");
        sliderPanel.add(sliderText);
        sliderPanel.add(slider);
        sliderPanel.add(sliderButton);
        sliderButton.addActionListener(this);
        frame.add(sliderPanel);
        frame.setVisible(true);

    }

    public void reload()
    {
        frame.remove(mazePanel);
        mazePanel = new JPanel(new GridLayout(maze.getSize(), maze.getSize(), 0, -5));
        for (int i = 0; i < maze.getMaze().length; i++)
        {
            for (int j = 0; j < maze.getMaze()[0].length; j++)
            {
                JLabel l = new JLabel(maze.getMaze()[i][j]);
                mazePanel.add(l);
            }
        }
        frame.add(mazePanel);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        boolean win = false;
        if (keyCode == KeyEvent.VK_W) {
            win = maze.move("up");
        } else if (keyCode == KeyEvent.VK_S) {
            win = maze.move("down");
        } else if (keyCode == KeyEvent.VK_A) {
            win = maze.move("left");
        } else if (keyCode == KeyEvent.VK_D) {
            win = maze.move("right");
        }
        reload();
        if (win)
        {
            System.out.println("you win");
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void actionPerformed(ActionEvent e)
    {
        int size = slider.getValue();
        maze = new Maze(size);
        maze.makeMaze(new Coordinate(1, 1));
        mazePanel = new JPanel(new GridLayout(maze.getSize(), maze.getSize(), 0, -5));
        sliderPanel.setVisible(false);
        frame.remove(sliderPanel);
        frame.add(mazePanel);
        frame.setSize(size*10+20, size*10+20);
        reload();
    }
}
