package Game.Bonus;

import Main.Collidable;
import Main.Player.Player;
import Main.Point;

public abstract class Bonus extends Point implements Collidable {
    private String spriteUrl;
    public Bonus(int x, int y) {
        setXY(x,y);
    }

    public abstract void reaction(Player player);

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    //public void render(Graphics g);
}
