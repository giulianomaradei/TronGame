package Game.Bonus;

import Main.Player.Player;

public class invincibilityBonus extends Bonus{

    private float invincibilityTime;

    public invincibilityBonus(int x, int y, float invincibilityTime) {
        super(x, y);
        this.invincibilityTime = invincibilityTime;
    }

    @Override
    public void reaction(Player player) {
        //player.improveSpeed(invincibilityTime);
    }
}
