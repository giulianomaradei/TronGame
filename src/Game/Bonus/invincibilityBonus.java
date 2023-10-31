package Game.Bonus;

import Main.Player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class invincibilityBonus extends Bonus{

    private float invincibilityTime;

    public invincibilityBonus(String spriteUrl, int x, int y, float invincibilityTime) {
        super(spriteUrl, x, y);
        this.invincibilityTime = invincibilityTime;
    }

    @Override
    public void reaction(Player player) {
        //player.improveSpeed(invincibilityTime);
    }
}
