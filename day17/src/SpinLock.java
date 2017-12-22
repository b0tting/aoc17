import java.util.LinkedList;

public class SpinLock {
    LinkedList<Integer> spinLock = new LinkedList<>();
    int steps;
    int position = 0;

    public static void main(String[] args) {
        SpinLock sp = new SpinLock(376);
        sp.spinLock.add(0);
        int i = 1;
        while(i < 50000000) {
            sp.spinMyLock(i++);
            if(i % 100000 == 0) {
                System.out.println("NOW AT " + i);
            }
        }
        sp.printSolution();

    }

    public void printSolution() {
        int location = spinLock.indexOf(0);
        System.out.println(spinLock.get(location +1));
    }

    public SpinLock(int steps) {
        this.steps = steps;
    }

    public void spinMyLock(int value) {
        int newPos = ((steps + position) % spinLock.size()) + 1;
        spinLock.add(newPos, value);
        position = newPos;
    }


}
