package Game.Bonus;

import Main.Player.Player;

public class invincibilityBonus extends Bonus{

    private float invincibilityTime;

    public invincibilityBonus(String spriteUrl, int x, int y, float invincibilityTime) {
        super(spriteUrl, x, y);
        this.invincibilityTime = invincibilityTime;
    }

    @Override
    public void reaction(Player player) {
        if(collisionDetected(player)){
            System.out.println("Colide");
        }
    }
}
