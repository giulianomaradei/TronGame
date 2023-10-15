package Game.Player;

import Game.Coordinate;

public class Trace {

    private int index;
    private Coordinate nextCoordinate;

    public int getIndex() {
        return index;
    }

    public void movement() {
        Coordinate coordinate = this.nextCoordinate;

        int x = coordinate.getX();
        int y = coordinate.getY();

        x += vx;
        y += vy;

        coordinate.setXY(x, y);

        traces[0].movement();
    }
}
