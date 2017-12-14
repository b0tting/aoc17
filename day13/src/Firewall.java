import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Firewall {
    int packetLocation = -1;
    int severity = 0;
    int delay = 0;
    int last = 0;
    ArrayList<Layer> lazylayers = new ArrayList<>();

    public void fill(String filename) {
        ArrayList<Integer> numbers = IntStream.range(1, 100).boxed().collect(Collectors.toCollection(ArrayList::new));
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }

        for(String line : lines) {
            String[] current = line.split(":\\s");
            Layer layer = new Layer(Integer.parseInt(current[0]), Integer.parseInt(current[1]));
            if(layer.depth > last) {
                last = layer.depth;
            }
            lazylayers.add(layer);
        }
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void reset() {
        for(Layer layer : lazylayers) {
            layer.reset();
        }
        packetLocation = -1;
        delay = 0;
        severity = 0;
    }

    public void move() {
        if(this.delay > 0) {
            delay--;
        } else {
            packetLocation++;
        }

        for(Layer layer : lazylayers) {
            if(layer.depth == packetLocation && layer.scannerIndex == 0) {
                severity += layer.getSeverity();
            }
            layer.moveScanner();
        }
    }

    public int getTotalSeverity() {
        return severity;
    }

    public boolean gotNext() {
        return packetLocation <= last;
    }


}
