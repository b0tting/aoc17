import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheDance {
    LinkedList<String> dancers = new LinkedList<String>();
    List<String> input;
    ArrayList<String> familiar = new ArrayList<>();

    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<String>(Arrays.asList("abcdefghijklmnop".split("(?!^)")));
        TheDance td = new TheDance();
        td.readInput("e:\\drive\\intellij\\aoc17\\day16\\input.txt");
        td.dancers = strings;
        td.startTheDance();
        td.endTheDance();
        td.startABillion(1000000000);
        td.endTheDance();

    }

    private void readInput(String filename) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }
        String line = lines.get(0);
        input = Arrays.asList(line.split(","));
    }

    private void startTheDance() {
        for(String entry :input) {
            danceRound(entry);
        }
    }

    private void startABillion(int one_billion) {
        for(int i = 0; i < one_billion; i++) {
            startTheDance();
            String endPositiion = "".join("", dancers);
            if(familiar.contains(endPositiion)) {
                // Repeating!
                System.out.println("Now at " + i + ", found a repeating dance");
                one_billion = one_billion % i;
                i = 1;
                System.out.println("Reset dances to 1 and will end at " + one_billion);
                familiar.clear();
            } else {
                familiar.add(endPositiion);
            }

            if(i % 1000 == 0) {
                System.out.println("AT " + i);
            }
        }
    }

    private void danceRound(String entry) {
        Pattern pat = Pattern.compile("x(\\d+)/(\\d+)");
        Pattern pat2 = Pattern.compile("s(\\d+)");
        if(entry.charAt(0) == 's') {
            Matcher match = pat2.matcher(entry);
            if(match.find()) {
                spin(Integer.parseInt(match.group(1)));
            }
        } else if(entry.charAt(0) == 'x') {
            // Okay, we regexxen die shit
            Matcher match = pat.matcher(entry);
            if(match.find()) {
                int from = Integer.parseInt(match.group(1));
                int to = Integer.parseInt(match.group(2));
                exchange(from, to);
            }

        } else if(entry.charAt(0) == 'p') {
            partner(String.valueOf(entry.charAt(1)), String.valueOf(entry.charAt(3)));
        }
    }

    private void spin(int number) {
        Collections.rotate(dancers, number);
    }

    private void exchange( int pos1, int pos2) {
        Collections.swap(dancers, pos1, pos2);
    }

    private void partner(String program1, String program2) {
        int oldIndex = dancers.indexOf(program1);
        int newIndex = dancers.indexOf(program2);
        exchange(oldIndex, newIndex);
    }

    private void endTheDance() {
        System.out.println(dancers);
        System.out.println("".join("", dancers));
    }
}
