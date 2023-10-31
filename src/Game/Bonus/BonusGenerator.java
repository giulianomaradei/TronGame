package Game.Bonus;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BonusGenerator{
    private float respawnCooldown;
    private final int windowSizeX;
    private final int windowSizeY;
    private final int gridCellSize;
    private boolean isActiveBonus = false;
    private Bonus activeBonus;
    private int maxRespawnCooldown;
    private int respawnCooldownCounter = 0;

    private ArrayList<Bonus> bonuses = new ArrayList<>();
    public BonusGenerator(int windowSizeX, int windowSizeY, int gridCellSize) {
        this.respawnCooldown = 2.f;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.gridCellSize = gridCellSize;
        this.maxRespawnCooldown = 1000;
        bonusesInit();
    }

    private void bonusesInit(){
        bonuses.add(new invincibilityBonus("resources/Bonus1.png", 0, 0, 3.f));
        bonuses.add(new invincibilityBonus("resources/Bonus2.png", 0, 0, 3.f));
    }

    public void setRespawnCooldown(float respawnCooldown) {
        this.respawnCooldown = respawnCooldown;
    }

    public void setBonusRandomPosition(Bonus bonus){
        int x = (int) ((Math.random() * (windowSizeX)) + 0) / gridCellSize;
        int y = (int) ((Math.random() * (windowSizeY)) + 0) / gridCellSize;
        bonus.setX(x * gridCellSize);
        bonus.setY(y * gridCellSize);
    }
    public boolean isActiveBonus(){
        return isActiveBonus;
    }

    public void spawnRandomBonus(int time){
        if(respawnCooldownCounter >= maxRespawnCooldown){
            if(!isActiveBonus) {
                //Aleatoriza um indice do tamanho do Array bonus
                int index = new Random().nextInt(bonuses.size());

                //Define uma posição aleatoria para o bonus
                setBonusRandomPosition(bonuses.get(index));
                activeBonus = bonuses.get(index);

                respawnCooldownCounter = 0;
                isActiveBonus = true;
            }
        }

        respawnCooldownCounter += time;
        //System.out.println(respawnCooldownCounter);
    }

    public void renderActiveBonus(Graphics g){
        if(activeBonus != null) {
            activeBonus.render(g);
        }
    }
}
