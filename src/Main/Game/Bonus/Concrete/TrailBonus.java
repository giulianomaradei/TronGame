package Main.Game.Bonus.Concrete;

import Main.Game.Bonus.Contracts.Bonus;
import Main.Game.Game;
import Main.Player.Player;

public class TrailBonus extends Bonus {

    public TrailBonus(int x, int y) {
        super(x, y);
        setSprite("TrailBonus");
    }

    @Override
    public void reaction(Player player) {
        super.reaction();
        player.trailBonusReaction();
    }
}
