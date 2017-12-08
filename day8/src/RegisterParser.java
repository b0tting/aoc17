import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mark on 8-12-2017.
 */
public class RegisterParser {
    public Registers registers = null;
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        try {
            RegisterParser rp = new RegisterParser();
            List<String> lines = rp.readInput("d:\\drive\\intellij\\aoc17\\day8\\input.txt");
            rp.parseInstructions(lines);
            System.out.println("Max in system: " + rp.registers.getMaxRegister());
            System.out.println("Max ever in system: " + rp.registers.getMaxEverRegister());
            //rp.registers.printAllRegister();
        } catch (IllegalStateException ie) {
            ie.printStackTrace();
        }
    }



    public List<String> readInput(String filename) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }
        return lines;
    }

    public void parseInstructions(List<String> unparsed) {
        this.registers = new Registers();
        for(String line : unparsed) {
            registers.handleLine(line);
        }
    }
}
