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
        maze[maze.length-2][maze[0].length-2] = " ";
        //visitedCells.add(new Coordinate(size-2, size-2));
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
//        System.out.println(row + ", " + col);
//        printArr(maze);
        visitedCells.add(coord);
        maze[row][col] = " ";
        boolean up = checkSpace(new Coordinate(row-1, col), "up");
        boolean down = checkSpace(new Coordinate(row+1, col), "down");
        boolean left = checkSpace(new Coordinate(row, col-1), "left");
        boolean right = checkSpace(new Coordinate(row, col+1), "right");
        int count = boolToInt(up) + boolToInt(down) + boolToInt(left) + boolToInt(right);
//        System.out.println(count);
//        System.out.println("nottvisited up: " + notVisited(new Coordinate(row-1, col)));
//        System.out.println(up);
//        System.out.println(down);
//        System.out.println(left);
//        System.out.println(right);
        if (count == 0)
        {
            for (int i = 1; i < size-1; i++)
            {
                for (int j = 1; j < size-1; j++)
                {
                    Coordinate tempCoord = new Coordinate(i, j);
                    if (validScan(tempCoord))
                    {
//                        System.out.println("scan");
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
                if (i == 0) {
                    //System.out.println("                                       up");
                    makeMaze(new Coordinate(row-1, col));
                } else if (i == 1) {
                    //System.out.println("                                       down");
                    makeMaze(new Coordinate(row + 1, col));
                } else if (i == 2) {
                    //System.out.println("                                       left");
                    makeMaze(new Coordinate(row, col - 1));
                } else if (i == 3) {
                    //System.out.println("                                       right");
                    makeMaze(new Coordinate(row, col + 1));
                }
                break;
            }
        }
       // if ()
    }

    public void cleanUpMaze()
    {
        //for (int )
    }

    public boolean move(String direction)
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j].equals(FACE))
                {
                    try
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
                    catch (Exception e)
                    {

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
        try
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
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean validScan(Coordinate coord)
    {
        int row = coord.getRow();
        int col = coord.getCol();

        int up = boolToInt(notVisited(new Coordinate(row-1, col)) && checkSpace(new Coordinate(row-1, col), "up"));
        int down = boolToInt(notVisited(new Coordinate(row+1, col)) && checkSpace(new Coordinate(row+1, col), "down"));
        int left = boolToInt(notVisited(new Coordinate(row, col-1)) && checkSpace(new Coordinate(row, col-1), "left"));
        int right = boolToInt(notVisited(new Coordinate(row, col+1)) && checkSpace(new Coordinate(row, col+1), "right"));
        return up + down + left + right >= 2;
    }

    public boolean checkSpace(Coordinate coord, String directionGoing)
    {
        int row = coord.getRow();
        int col = coord.getCol();
        int tempUpRow = row-1 == 0 ? row: row-1;
        int tempDownRow = row+1 == size-1 ? row : row+1;
        int tempLeftCol = col-1 == 0 ? col: col-1;
        int tempRightCol = col+1 == size-1 ? col: col+1;
        if (directionGoing.equals("up"))
        {
            if (row-1 < 0)
            {
                return false;
            }
            return  notVisited(new Coordinate(row, col)) &&
                    notVisited(new Coordinate(row, tempLeftCol)) &&
                    notVisited(new Coordinate(row, tempRightCol)) &&
                    notVisited(new Coordinate(tempUpRow, col)) &&
                    notVisited(new Coordinate(tempUpRow, tempLeftCol)) &&
                    notVisited(new Coordinate(tempUpRow, tempRightCol));

        }
        else if (directionGoing.equals("down"))
        {
            if (row+1 > size-1)
            {
                return false;
            }
            return  notVisited(new Coordinate(row, col)) &&
                    notVisited(new Coordinate(row, tempLeftCol)) &&
                    notVisited(new Coordinate(row, tempRightCol)) &&
                    notVisited(new Coordinate(tempDownRow, col)) &&
                    notVisited(new Coordinate(tempDownRow, tempLeftCol)) &&
                    notVisited(new Coordinate(tempDownRow, tempRightCol));
        }
        else if (directionGoing.equals("left"))
        {
            if (col-1 < 0)
            {
                return false;
            }
            return  notVisited(new Coordinate(row, col)) &&
                    notVisited(new Coordinate(tempUpRow, col)) &&
                    notVisited(new Coordinate(tempDownRow, col)) &&
                    notVisited(new Coordinate(row, tempLeftCol)) &&
                    notVisited(new Coordinate(tempUpRow, tempLeftCol)) &&
                    notVisited(new Coordinate(tempDownRow, tempLeftCol));
        }
        else if (directionGoing.equals("right"))
        {
            if (col+1 > size-1)
            {
                return false;
            }
            return  notVisited(new Coordinate(row, col)) &&
                    notVisited(new Coordinate(tempUpRow, col)) &&
                    notVisited(new Coordinate(tempDownRow, col)) &&
                    notVisited(new Coordinate(row, tempRightCol)) &&
                    notVisited(new Coordinate(tempUpRow, tempRightCol)) &&
                    notVisited(new Coordinate(tempDownRow, tempRightCol));
        }
        return true;
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
