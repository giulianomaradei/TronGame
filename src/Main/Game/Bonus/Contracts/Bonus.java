package Main.Game.Bonus.Contracts;

import Main.GameObject;
import Main.Player.Player;

public abstract class Bonus extends GameObject {

    public Bonus(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y);
    }
}
