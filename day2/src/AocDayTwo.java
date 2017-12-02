import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AocDayTwo {
    private List<int[]> ints;

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        AocDayTwo aoc = new AocDayTwo("E:\\drive\\intellij\\aoc17\\day2\\input.txt");
        System.out.println(aoc.getCheckSum());
        System.out.println(aoc.getCheckSumTwo());
    }

    public AocDayTwo(String filename) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<int[]> ints = new ArrayList<int[]>();
        for (String line : lines) {
            int[] numbers = Arrays.asList(line.split("\\s+")).stream().mapToInt(Integer::parseInt).toArray();
            ints.add(numbers);
        }
        this.ints = ints;
    }

    private int getCheckSum() {
        int sum = 0;
        for (int[] numbers : this.ints) {
            int lowest = numbers[0];
            int highest = lowest;
            for (int number : numbers) {
                if (number < lowest) {
                    lowest = number;
                }

                if (number > highest) {
                    highest = number;
                }
            }
            sum += (highest - lowest);
        }
        return sum;
    }

    private int getCheckSumTwo() {
        int sum = 0;
        for (int[] numbers : this.ints) {
            for(int i = 0; i < numbers.length; i++) {
                int number = numbers[i];
                for(int j = 0; j < numbers.length; j++) {
                    int divisor = numbers[j];
                    if (i != j && number % divisor == 0) {
                        sum += number / divisor;
                    }
                }
            }
        }
        return sum;
    }
}