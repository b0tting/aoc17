import java.util.ArrayList;
import java.util.List;

public class Disc {
    private String name;
    private int weight;
    private List<Disc> children = new ArrayList<Disc>();
    private Disc parent;
    private boolean stub = true;

    public Disc(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public int getTotalWeight() {
        int total = this.getWeight();
        for (Disc cdisc : this.getChildren()) {
            total += cdisc.getTotalWeight();
        }
        return total;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Disc> getChildren() {
        return children;
    }

    public void addChild(Disc child) {
        this.children.add(child);
    }

    public Disc getParent() {
        return parent;
    }

    public void setParent(Disc parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStub() {
        return stub;
    }

    public void setStub(boolean stub) {
        this.stub = stub;
    }
}
