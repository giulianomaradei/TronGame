package Game.Bonus;

import Main.Player.Player;

import java.awt.*;

public class TrailBonus extends Bonus{
    private int trailBonusAmount;
    private String spriteUrl;

    public TrailBonus(String spriteUrl, int x, int y, int trailBonusAmount, String spriteUrl1) {
        super(spriteUrl, x, y);
        this.trailBonusAmount = trailBonusAmount;
        this.spriteUrl = spriteUrl1;
    }

    @Override
    public void reaction(Player player) {
        //player.improveTrail(trailBonusAmount);
    }
}
