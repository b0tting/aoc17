import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mark on 12-12-2017.
 */
public class ProgramParser {

    public HashMap<Integer, Program> programs = new HashMap<>();

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        ProgramParser rp = new ProgramParser("d:\\drive\\intellij\\aoc17\\day12\\input.txt");
    }

    public ProgramParser(String filename) {
        int groupCount = 0;
        List<String> lines = readInput(filename);
        parseInstructions(lines);
        while(programs.keySet().size() > 0) {
            int current = ((Integer)programs.keySet().toArray()[0]);
            ArrayList<Program> skiplist = findConnections(current);
            programs.values().removeAll(skiplist);
            groupCount++;
        }
        System.out.println(groupCount);


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
        for(String line : unparsed) {
            String[] splitted = line.split("[\\s<\\->,]");
            Integer pid = Integer.parseInt(splitted[0]);
            Program prog = new Program(pid);
            programs.put(pid, prog);
            for(int i = 1; i < splitted.length; i++) {
                if(splitted[i].length() > 0) {
                    Integer connectionPid = Integer.parseInt(splitted[i]);
                    if (programs.containsKey(connectionPid)) {
                        Program connectionProg = programs.get(connectionPid);
                        if (connectionProg != prog) {
                            prog.addConnection(connectionProg);
                            programs.get(connectionPid).addConnection(prog);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Program> findConnections(Integer pid) {
        ArrayList<Program> skiplist = new ArrayList<Program>();
        findConnections(pid, skiplist);
        return skiplist;
    }

    public void findConnections(Integer pid, ArrayList<Program> skiplist) {
        Program prog = programs.get(pid);
        if(!skiplist.contains(prog)) {
            skiplist.add(prog);
        }
        for (Program connection : prog.getConnections()) {
            if(!skiplist.contains(connection)) {
                findConnections(connection.getPid(), skiplist);
            }
        }
    }
}
