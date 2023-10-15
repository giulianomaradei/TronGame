package Game.Player;

import Game.Collidable;
import Game.Coordinate;
import Game.Point;

public abstract class Player extends Point implements Collidable {
    private int vx;
    private int vy;

    private double skillCooldown;

    private Trace[] traces;

    private void movement(){
        Coordinate coordinate = this.getCoordinate();

        int x = coordinate.getX();
        int y = coordinate.getY();

        x += vx;
        y += vy;

        coordinate.setXY(x, y);

        traces[0].movement();
    }

}
