package Main.Player;

import Main.GameObject;
import Main.Point;

public class Trace extends GameObject {

    public Trace(String spriteUrl, int x, int y, int index, Trace previousTrace) {
        super(spriteUrl, x, y);
        this.index = index;
        this.previousTrace = previousTrace;
    }

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


    @Override
    public void reaction(Player player) {

    }
}
