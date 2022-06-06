import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.SubmissionPublisher;

public class GUI implements KeyListener, ActionListener
{
    private Maze maze;
    private JFrame frame;
    private JPanel sliderPanel;
    private JPanel textFieldPanel;
    private JPanel submitPanel;
    private JPanel mazePanel;
    private JTextField characterSelector;
    private JSlider slider;
    private static int numTries = 1;

    public GUI()
    {
    }

    public void initialize()
    {
        if (numTries == 1)
        {
            frame = new JFrame("MAZE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setFocusable(true);
            frame.addKeyListener(this);
        }

        frame.setSize(500, 150);

        slider = new JSlider(30, 70);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);

        sliderPanel = new JPanel();
        JLabel sliderText = new JLabel("What size do you want your maze to be?");
        sliderPanel.add(sliderText);
        sliderPanel.add(slider);
        frame.add(sliderPanel, BorderLayout.NORTH);

        characterSelector = new JTextField(1);
        JLabel textFieldText = new JLabel("What letter/number do you want to be? ");

        textFieldPanel = new JPanel();
        textFieldPanel.add(textFieldText, BorderLayout.WEST);
        textFieldPanel.add(characterSelector, BorderLayout.EAST);
        frame.add(textFieldPanel, BorderLayout.CENTER);

        submitPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitPanel.add(submitButton, BorderLayout.CENTER);
        frame.add(submitPanel, BorderLayout.SOUTH);

        frame.pack();
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

    private void showWinningScreen()
    {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout());
        JLabel label = new JLabel("<html> Y   Y  OOO  U   U     W     W III N   N<br>&nbsp;&nbsp;Y Y  O   O U   U     W     W  I  NN  N</html>");
        textPanel.add(label);
        frame.add(textPanel);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //System.out.println(frame.getHeight() + " " + frame.getWidth());
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
        } else if (keyCode == KeyEvent.VK_SPACE) {
            maze.moveFace(new Coordinate(1, 0));
        } else if (keyCode == KeyEvent.VK_P) {
            maze.moveFace(new Coordinate(maze.getSize()-2, maze.getSize()-2));
        }
        reload();
        if (win)
        {
            frame.remove(mazePanel);
            showWinningScreen();
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
        maze.setFace(characterSelector.getText());
        maze.makeMaze(new Coordinate(1, 1));
        maze.makeMaze(new Coordinate(size-2, size-2));
        maze.cleanUpMaze();
        mazePanel = new JPanel(new GridLayout(maze.getSize(), maze.getSize(), 0, -5));
        frame.remove(sliderPanel);
        frame.remove(textFieldPanel);
        frame.remove(submitPanel);
        frame.add(mazePanel);
        frame.setSize(size*10+20, size*10+20);
        reload();
    }
}
