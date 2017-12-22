import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class Duet {
    private String[] instructions;
    LinkedHashMap<String, Long> registers  = new LinkedHashMap<>();
    long lastsound = 0;
    long pointer = 0;

    public static void main(String[] args) {
        Duet duet = new Duet();
        duet.readInput("e:\\drive\\intellij\\aoc17\\day18\\input.txt");
        duet.startParsing();

    }

    private Long getval(String registerOrValue) {
        if(registerOrValue.matches("[a-z]")) {
            if(!registers.containsKey(registerOrValue)) {
                registers.put(registerOrValue, 0L);
            }
            return registers.get(registerOrValue);
        } else {
            return Long.parseLong(registerOrValue);
        }
    }

    private void startParsing() {
        while(pointer < instructions.length) {
            String line = instructions[(int)pointer];
            System.out.println(pointer + ": "  + line + " - " + getStateString());
            String[] parsed = line.split("\\s");
            if(parsed[0].equals("snd")) {
                lastsound = getval(parsed[1]);
                System.out.println("I just played " + lastsound);
            } else if(parsed[0].equals("set")) {
                registers.put(parsed[1], getval(parsed[2]));
            } else if(parsed[0].equals("add")) {
                registers.put(parsed[1], getval(parsed[2]) + getval(parsed[1]));
            } else if(parsed[0].equals("mul")) {
                registers.put(parsed[1], getval(parsed[2]) * getval(parsed[1]));
            } else if(parsed[0].equals("mod")) {
                registers.put(parsed[1], getval(parsed[1]) % getval(parsed[2]));
            } else if(parsed[0].equals("rcv")) {
                if(getval(parsed[1]) != 0) {
                    System.out.println("I RECOVERED " + lastsound);
                    pointer = 100000000;
                }
            }

            if(parsed[0].equals("jgz") && getval(parsed[1]) > 0) {
                pointer = pointer + getval(parsed[2]);
                System.out.println("jumping to " + pointer);
            } else {
                pointer++;
            }
        }
    }

    private String getStateString() {
        StringBuffer sb = new StringBuffer();
        for(String key : registers.keySet()) {
            sb.append(key);
            sb.append(":");
            sb.append(registers.get(key));
            sb.append(",");
        }
        return sb.toString();
    }

    private void readInput(String filename) {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            List<String> parsed = Files.readAllLines(Paths.get(filename), charset);

            instructions =  parsed.toArray(new String[parsed.size()]);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
