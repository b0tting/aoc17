public class Layer {
    int scannerIndex = 0;
    boolean movingUp = true;
    int depth;
    int range;

    public Layer(int depth, int range) {
        this.depth = depth;
        this.range = range;
    }

    public void moveScanner() {
        if(movingUp) {
            if(++scannerIndex >= (range - 1)) {
                movingUp = false;
            }
        } else {
            if(--scannerIndex <= 0) {
                movingUp = true;
            }
        }
    }

    public void reset() {
        scannerIndex = 0;
        movingUp = true;
    }

    public int getSeverity() {
        return depth * range;
    }

}
