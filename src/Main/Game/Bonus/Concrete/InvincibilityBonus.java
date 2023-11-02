package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.TimedBonus;
import Main.Player.Player;

public class InvincibilityBonus extends TimedBonus {

    public InvincibilityBonus(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y, 2);
    }

    @Override
    public void reaction(Player player) {
        super.reaction(player);
    }
}
