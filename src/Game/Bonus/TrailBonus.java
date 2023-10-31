package Game.Bonus;

import Main.Player.Player;

public class TrailBonus extends Bonus{
    private int trailBonusAmount;
    public TrailBonus(int x, int y, int trailBonusAmount) {
        super(x, y);
        this.trailBonusAmount = trailBonusAmount;
    }

    @Override
    public void reaction(Player player) {
        //player.improveTrail(trailBonusAmount);
    }
}
