package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Player.Player;

public class SpeedBonus extends TimedBonus {
    private float speedBonus = 1.2f;

    public SpeedBonus(int x, int y) {
        super(x, y, 3);
        setSprite("SpeedBonus");
    }

    @Override
    public void reaction(Player player) {
        super.reaction();
    }
}
