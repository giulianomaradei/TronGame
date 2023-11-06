package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Game.Player.Contracts.Player;

public class SpeedBonus extends TimedBonus {
    private int speedBonus = 80;


    public SpeedBonus(int x, int y) {
        super(x, y, 3000);
        setSprite("SpeedBonus");
    }

    @Override
    public void reaction(Player player) {
        super.reaction();
        player.speedBonusReaction(speedBonus, durationTime);
    }
}
