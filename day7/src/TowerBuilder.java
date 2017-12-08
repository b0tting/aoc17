import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TowerBuilder {
    private HashMap<String, Disc> discs = new HashMap<String,Disc>();

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        try {
            TowerBuilder tb = new TowerBuilder("d:\\drive\\intellij\\aoc17\\day7\\input.txt");
            //tb.dumpDiscs();
            Disc parent = tb.findWithoutParent();
            tb.countWeights(parent);

        } catch (IllegalStateException ie) {
            ie.printStackTrace();
        }
    }

    private TowerBuilder(String filename) throws IllegalStateException {
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), charset);
        } catch (IOException e) {
            System.out.println(e);
        }

        Pattern namePattern = Pattern.compile("([a-z]+)");
        Pattern weightPattern = Pattern.compile("([\\d]+)");
        for (String line : lines) {
            Matcher matcher = namePattern.matcher(line);
            if(!matcher.find()) {
                throw new IllegalStateException("Incorrect string layout!");
            }
            String name = matcher.group(0);
            Disc newdisc = null;
            if(discs.containsKey(name)) {
                newdisc = discs.get(name);
                newdisc.setStub(false);
            } else {
                newdisc = new Disc(name);
                discs.put(newdisc.getName(), newdisc);
            }

            while (matcher.find()) {
                String child = matcher.group(1);
                Disc childDisc = null;
                if(discs.containsKey(child)) {
                    childDisc = discs.get(child);
                } else {
                    childDisc = new Disc(child);
                    discs.put(child, childDisc);
                }

                newdisc.addChild(childDisc);
                childDisc.setParent(newdisc);
            }

            matcher = weightPattern.matcher(line);
            if(!matcher.find()) {
                throw new IllegalStateException("Incorrect string layout!");
            }
            newdisc.setWeight(Integer.parseInt(matcher.group(1)));

        }
    }

    private Disc findWithoutParent() {
        Disc returnDisc = null;
        for(Disc disc : this.discs.values()) {
            if(disc.getParent() == null) {
                returnDisc = disc;
                break;
            }
        }
        return returnDisc;
    }

    private void countWeights(Disc disc) {
        HashMap<Integer, List<Disc>> countedWeights = new HashMap<Integer, List<Disc>>();
        boolean gotProb = false;
        for (Disc cdisc : disc.getChildren()) {
            Integer curTotal = cdisc.getTotalWeight();
            if(countedWeights.containsKey(curTotal)) {
                countedWeights.get(curTotal).add(cdisc);
            } else {
                List<Disc> newList = new ArrayList<Disc>();
                newList.add(cdisc);
                countedWeights.put(curTotal, newList);
            }
        }

        boolean odd = false;
        Disc iamodd = null;
        for(List<Disc> discs : countedWeights.values()) {
            if(discs.size() == 1) {
                iamodd = discs.get(0);
                odd = true;
            }
        }
        if(odd) {
            System.out.println("Got odd at "+ disc.getName());
            for (Disc cdisc : disc.getChildren()) {
                if(cdisc == iamodd) {
                    System.out.println("The odd child is "+ cdisc.getName() + " with weight " + cdisc.getWeight() + " and totalweight " + cdisc.getTotalWeight());
                } else {
                    System.out.println("My other child " + cdisc.getName() + " with weight " + cdisc.getWeight() + " and totalweight " + cdisc.getTotalWeight());
                }
            }
            countWeights(iamodd);
        }
    }


    private void dumpDiscs() {
        for(Disc disc : this.discs.values()) {
            if(disc.getParent() != null) {
                System.out.println("DISC NAME " + disc.getName() + " HAS CHILDREN " + disc.getChildren().size() + " AND PARENT " + disc.getParent().getName());
            } else {
                System.out.println("U think " + disc.getName() + " has no parent");
            }
        }
        System.out.println("TOTAL " + discs.keySet().size());
    }
}
