package Game.Bonus;

import Main.Player.Player;

public class SpeedBonus extends Bonus{
    private float speedBonus;
    private float bonusTime;

    public SpeedBonus(int x, int y, float speedBonus, float bonusTime) {
        super(x, y);
        this.speedBonus = speedBonus;
        this.bonusTime = bonusTime;
    }

    @Override
    public void reaction(Player player) {
        //player.improveSpeed(bonusTime);
    }
}
