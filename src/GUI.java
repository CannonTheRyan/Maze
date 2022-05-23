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
    private int size;

    public GUI()
    {
    }

    public void initialize()
    {
        frame = new JFrame("MAZE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        slider = new JSlider(20, 50);
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

    public void load()
    {
        frame.add(mazePanel);
        reload();

    }

    public void reload()
    {
        for (int i = 0; i < maze.getMaze().length; i++)
        {
            for (int j = 0; j < maze.getMaze()[0].length; j++)
            {
               mazePanel.add(new JLabel(maze.getMaze()[i][j]));
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            maze.move("up");
        } else if (keyCode == KeyEvent.VK_DOWN) {
            maze.move("down");
        } else if (keyCode == KeyEvent.VK_LEFT) {
            maze.move("left");
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            maze.move("right");
        }
        reload();
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
        size = slider.getValue();
        maze = new Maze(size);
        mazePanel = new JPanel(new GridLayout(size, size, 0, -5));
        mazePanel.setFocusable(true);
        mazePanel.addKeyListener(this);
        sliderPanel.setVisible(false);
        frame.remove(sliderPanel);
        load();
    }
}
