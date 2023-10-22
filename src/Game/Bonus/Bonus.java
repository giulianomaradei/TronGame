package Game.Bonus;

import Main.Collidable;
import Main.Player.Player;
import Main.Point;

public abstract class Bonus extends Point implements Collidable {
    public Bonus(int x, int y) {
        setXY(x,y);
    }

    public abstract void reaction(Player player);
}
