package Main.Player;

import Main.Collidable;
import Main.Game.Game;
import Main.Point;

public abstract class Player extends Point implements Collidable {

    private double skillCooldown;
    private int vx;
    private int vy;
    private Trace[] traces;

    private void movement(){

        int x = this.getX();
        int y = this.getY();

        x += Game.cellSize * vx;
        y += Game.cellSize * vy;

        this.setXY(x, y);

        traces[0].movement();
    }

}
