import java.lang.reflect.Array;
import java.util.ArrayList;

public class Maze
{
    private final String WALL = "\u2B1B";
    private final String FACE = "O";
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
                maze[row][col] = WALL;
//                if (row == 0 || col == 0 || row == maze.length - 1 || col == maze[0].length - 1)
//                {
//                    maze[row][col] = WALL;
//                }
//                else
//                {
//                    maze[row][col] = " ";
//                }
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
        int row = coord.getRow();
        int col = coord.getCol();
        visitedCells.add(coord);
        maze[row][col] = " ";
        String direction = "";
        boolean up = notVisited(new Coordinate(row-1, col));
        boolean down = notVisited(new Coordinate(row+1, col));
        boolean left = notVisited(new Coordinate(row, col-1));
        boolean right = notVisited(new Coordinate(row, col+1));
        int count = boolToInt(up) + boolToInt(down) + boolToInt(left) + boolToInt(right);
        if (count == 0)
        {
            for (int i = 1; i < size-1; i++)
            {
                for (int j = 1; j < size-1; j++)
                {
                    Coordinate tempCoord = new Coordinate(i, j);
                    if (notVisited(tempCoord))
                    {
                        makeMaze(tempCoord);
                    }
                }
            }
        }
        int temp = 0;
        int random = (int) (Math.random() * count) + 1;
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        list.add(up);
        list.add(down);
        list.add(left);
        list.add(right);
        for (int i = 0; i < 4; i++)
        {
            if (list.get(i) == true)
            {
                temp++;
            }
            if (temp == random)
            {
                if (i == 0)
                    makeMaze(new Coordinate(row-1, col));
                else if (i == 1)
                    makeMaze(new Coordinate(row+1, col));
                else if (i == 2)
                    makeMaze(new Coordinate(row, col-1));
                else if (i == 3)
                    makeMaze(new Coordinate(row, col+1));
                break;
            }
        }

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

    public int boolToInt(boolean tf)
    {
        return tf ? 1 : 0;
    }

    public boolean notVisited(Coordinate coord)
    {
        int row = coord.getRow();
        int col = coord.getCol();
        for (Coordinate c : visitedCells)
        {
            if (c.getRow() == row && c.getCol() == col)
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkSpace(Coordinate coord, String directionFrom)
    {
        int row = coord.getRow();
        int col = coord.getCol();
        if (directionFrom.equals("up"))
        {
            return !(maze[row+1][col].equals(" ") || maze[row][col-1].equals(" ") || maze[row][col+1].equals(" "));
        }
        else if (directionFrom.equals("down"))
        {

        }
        else if (directionFrom.equals("left"))
        {

        }
        else if (directionFrom.equals("right"))
        {

        }
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
