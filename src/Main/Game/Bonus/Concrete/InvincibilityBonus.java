package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Game.Player.Contracts.Player;

public class InvincibilityBonus extends TimedBonus {

    public InvincibilityBonus(int x, int y) {
        super(x, y, 5000);
        setSprite("InvincibilityBonus");
    }

    @Override
    public void reaction(Player player) {
        super.reaction();
        player.invincibilityBonusReaction(durationTime);
    }
}
