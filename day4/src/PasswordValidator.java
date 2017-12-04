import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark on 4-12-2017.
 */
public class PasswordValidator {
    private List<List<String>> passwords = new ArrayList<List<String>>();

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        PasswordValidator pv = new PasswordValidator("e:\\drive\\intellij\\aoc17\\day4\\passwordlist.txt");
        System.out.println(pv.getCorrectPasswords());
        System.out.println(pv.getCorrectPasswordsAnagrammed());
    }

    private PasswordValidator(String filename) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }

        for (String line : lines) {
            this.passwords.add(Arrays.asList(line.split("\\s+")));
        }
    }

    /// Oh! De juiste! Zeg dat dan!
    private int getCorrectPasswords() {
        return this.passwords.size() - getFaultyPasswords();
    }

    private int getFaultyPasswords() {
        int numInvalids = 0;
        for(List<String> password : passwords) {
            List<String> current = new ArrayList<String>();
            for(String word : password) {
                if(current.contains(word)) {
                    numInvalids++;
                    break;
                } else {
                    current.add(word);
                }
            }
        }
        return numInvalids;
    }

    private int getCorrectPasswordsAnagrammed() {
        int numInvalids = 0;
        for(List<String> password : passwords) {
            List<String> current = new ArrayList<String>();
            for(String word : password) {
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                word = new String(chars);
                if(current.contains(word)) {
                    numInvalids++;
                    break;
                } else {
                    current.add(word);
                }
            }
        }
        return this.passwords.size() - numInvalids;

    }

}
