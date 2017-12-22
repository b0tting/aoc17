import java.util.ArrayList;

/**
 * Created by Mark on 12-12-2017.
 */
public class Program {
    private ArrayList<Program> connections = new ArrayList<>();
    private int pid;

    public Program(int pid) {
        this.pid = pid;
    }

    public void addConnection(Program connection) {
        this.connections.add(connection);
    }

    public ArrayList<Program> getConnections() {
        return this.connections;
    }

    public int getPid() {
        return this.pid;
    }

    @Override
    public String toString() {
        return "PID " + pid + " and " + this.connections.size() + " connections";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Program && ((Program)obj).getPid() == this.pid);
    }
}
