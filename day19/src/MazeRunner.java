import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Mark on 22-12-2017.
 */
public class MazeRunner {
    public char[][] maze;
    int last_x = 0;
    int last_y = 0;
    String solution = "";
    long steps = 0;

    public static void main(String[] args) {
        MazeRunner mazeRunner = new MazeRunner();
        mazeRunner.readInput("d:\\drive\\intellij\\aoc17\\day19\\input.txt");
        mazeRunner.last_x = 183;
        mazeRunner.last_y = -1;
        int x = 183;
        int y = 0;
        while(true) {
            int[] curr = mazeRunner.run(x, y);
            x = curr[0];
            y = curr[1];
        }
    }

    public void readInput(String fileName) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            List<String> parsed = Files.readAllLines(Paths.get(fileName), charset);
            maze = new char[parsed.size() + 1][parsed.size() + 15];
            int j = 0;
            for(String line : parsed) {
                for(int i = 0; i < line.length(); i++ ) {
                    maze[j][i] = line.charAt(i);
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public int[] run(int x, int  y) {
        steps++;
        char currentChar = maze[y][x];
        int next_x = x;
        int next_y = y;
        if(currentChar == 'K') {
            System.out.println(solution + "K and steps " + steps);
            System.exit(0);
        } else if(currentChar == '+') {
            // We komen over de Y as, dus kunnen we alleen naar boven of beneden
            if(last_x == x) {
                next_x = maze[y][x + 1] == '-' ? next_x + 1 : next_x - 1;
            } else {
                next_y = maze[y+1][x] == '|' ? next_y + 1 : next_y - 1;
            }
        } else {
            if(currentChar != '-' && currentChar != '|') {
                solution += currentChar;
            }

            if(last_x != x) {
                next_x = last_x < x ? x + 1 : x - 1;
            } else {
                next_y = last_y < y ? y + 1 : y - 1;
            }
        }
        last_x = x;
        last_y = y;
        int[] returns = new int[2];
        returns[0] = next_x;
        returns[1] = next_y;
        return returns;
    }
}
