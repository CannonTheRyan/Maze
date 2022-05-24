import com.sun.jdi.ClassObjectReference;

import java.nio.charset.CoderResult;
import java.util.ArrayList;

public class Maze
{
    private final String WALL = "\u2B1B";
    private final String FACE = "\uD83E\uDD21";
    private String[][] maze;
    private int size;
    private ArrayList<Coordinate> visitedCells;

    public Maze(int size)
    {
        this.size = size;
        maze = new String[size][size];
        visitedCells = new ArrayList<Coordinate>();
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                if (row == 0 || col == 0 || row == maze.length-1 || col == maze[0].length-1)
                {
                    visitedCells.add(new Coordinate(row, col));
                }
            }
        }
        for (int row = 0; row < maze.length; row++)
        {
            for (int col = 0; col < maze[0].length; col++)
            {
                if (row == 0 || col == 0 || row == maze.length - 1 || col == maze[0].length - 1)
                {
                    maze[row][col] = WALL;
                }
                else
                {
                    maze[row][col] = " ";
                }
            }
        }
        maze[1][0] = FACE;
        maze[maze.length-2][maze[0].length-1] = " ";
    }

    public static String printArr(String[][] arr)
    {
        String returnStr = "";

        for (String[] row : arr)
        {
            for (String str : row)
            {
                returnStr += str;
            }
            returnStr += "\n";
        }
        System.out.println(returnStr);
        return returnStr;
    }

    public void makeMaze(Coordinate coord)
    {
        visitedCells.add(coord);

    }

    public boolean move(String direction)
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j].equals(FACE))
                {
                    if (direction.equals("right") && !maze[i][j+1].equals(WALL))
                    {
                        maze[i][j] = " ";
                        maze[i][j+1] = FACE;
                        i = maze.length;
                        break;
                    }
                    else if (direction.equals("left") && !maze[i][j-1].equals(WALL))
                    {
                        maze[i][j] = " ";
                        maze[i][j-1] = FACE;
                        i = maze.length;
                        break;
                    }
                    else if (direction.equals("up") && !maze[i-1][j].equals(WALL))
                    {
                        maze[i][j] = " ";
                        maze[i-1][j] = FACE;
                        i = maze.length;
                        break;
                    }
                    else if (direction.equals("down") && !maze[i+1][j].equals(WALL))
                    {
                        maze[i][j] = " ";
                        maze[i+1][j] = FACE;
                        i = maze.length;
                        break;
                    }
                }
            }
        }
        return checkWin();
    }

    public boolean checkWin()
    {
        return maze[maze.length-2][maze[0].length-1].equals(FACE);
    }

    public String[][] getMaze()
    {
        return maze;
    }

    public int getSize()
    {
        return size;
    }
}
