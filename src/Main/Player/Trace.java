package Main.Player;

import Main.Point;

public class Trace extends Point{

    private int index;
    private Trace previousTrace;

    public int getIndex() {
        return index;
    }

    public void movement() {
        int x = previousTrace.getX();
        int y = previousTrace.getY();

        this.setXY(x, y);

        previousTrace.movement();
    }
}
