package Game.Bonus;

import Main.Player.Player;

public class BonusGenerator {
    private float respawnCooldown;
    private int windowSizeX;
    private int windowSizeY;
    private int gridCellSize;
    private boolean isActiveBonus;
    private Bonus bonus;
    public BonusGenerator(int windowSizeX, int windowSizeY, int gridCellSize) {
        this.respawnCooldown = 2.f;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.gridCellSize = gridCellSize;
    }

    public void setRespawnCooldown(float respawnCooldown) {
        this.respawnCooldown = respawnCooldown;
    }

    public void setBonusRandomPosition(){
        int x = (int) ((Math.random() * (windowSizeX - 0)) + 0) / gridCellSize;
        int y = (int) ((Math.random() * (windowSizeY - 0)) + 0) / gridCellSize;
        bonus.setXY(x,y);
    }
    boolean isActiveBonus(){
        return isActiveBonus;
    }
}
