public class Maze
{
    private final String WALL = "\u2B1B";
    private final String FACE = "\uD83E\uDD21";
    private String[][] maze;

    public Maze(int size)
    {
        maze = new String[size][size];
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

    public void move(String direction)
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j].equals(FACE))
                {
                    maze[i][j] = " ";
                    if (direction.equals("right") && !maze[i][j+1].equals(WALL))
                    {
                        maze[i][j+1] = FACE;
                    }
                    else if (direction.equals("left") && !maze[i][j-1].equals(WALL))
                    {
                        maze[i][j-1] = FACE;
                    }
                    else if (direction.equals("up") && !maze[i-1][j].equals(WALL))
                    {
                        maze[i-1][j] = FACE;
                    }
                    else if (direction.equals("down") && !maze[i+1][j].equals(WALL))
                    {
                        maze[i+1][j] = FACE;
                    }
                }
            }
        }
    }

    public String[][] getMaze()
    {
        return maze;
    }
}
