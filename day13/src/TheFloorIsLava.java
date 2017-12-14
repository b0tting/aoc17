import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class TheFloorIsLava {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        Firewall fw = new Firewall();
        fw.fill("e:\\drive\\intellij\\aoc17\\day13\\input.txt");

        int delay = 10;
        int severity = 1;
        while(severity != 0) {
            if(delay % 2 == 1) {
                delay++;
                continue;
            }
            fw.reset();
            fw.setDelay(delay);
            while(fw.gotNext()) {
                fw.move();
            }
            severity = fw.getTotalSeverity();
            if(severity != 0) {
                System.out.println(" AT DELAY " + delay + " GOT SEV " + severity);
                delay++;
            } else {
                System.out.println("GOT OUT AT " + delay);
            }
        }
    }
}
