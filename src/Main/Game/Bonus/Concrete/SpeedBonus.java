package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Player.Player;

public class SpeedBonus extends TimedBonus {
    private float speedBonus = 1.2f;

    public SpeedBonus(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y, 3);
    }

    @Override
    public void reaction(Player player) {
        //player.improveSpeed(bonusTime);
    }
}
