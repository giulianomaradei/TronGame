package Game.Player;

import Game.Collidable;
import Game.Point;

public abstract class Player extends Point implements Collidable {
    private int vx;
    private int vy;

    private double cooldown;

    private Trace[] traces;

    private void movimento(){

    }

}
