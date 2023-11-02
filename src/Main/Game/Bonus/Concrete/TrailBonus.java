package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.Bonus;
import Main.Game.Game;
import Main.Player.Player;

public class TrailBonus extends Bonus {
    private String spriteUrl;

    public TrailBonus(String spriteUrl, int x, int y) {
        super(spriteUrl, x, y);
    }

    @Override
    public void reaction(Player player) {
        super.reaction(player);
    }
}
