package Main.Game.Bonus.Contracts;

import Main.Game.Game;
import Main.GameObject;
import Main.Player.Player;

public abstract class Bonus extends GameObject {

    public Bonus( int x, int y) {
        super( x, y);
    }

    public void reaction() {
        Game.bonusGenerator.activeBonusDeactivation();
        this.removePositionInGrid(getX(), getY());
    }
}
