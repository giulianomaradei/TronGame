package Game.Bonus;

import Main.Player.Player;

import java.awt.*;

public class SpeedBonus extends Bonus{
    private float speedBonus;
    private float bonusTime;

    public SpeedBonus(String spriteUrl, int x, int y, float speedBonus, float bonusTime) {
        super(spriteUrl, x, y);
        this.speedBonus = speedBonus;
        this.bonusTime = bonusTime;
    }

    @Override
    public void reaction(Player player) {
        //player.improveSpeed(bonusTime);
    }
}
