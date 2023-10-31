package Game.Bonus;

import java.util.ArrayList;
import java.util.Random;

public class BonusGenerator {
    private float respawnCooldown;
    private final int windowSizeX;
    private final int windowSizeY;
    private final int gridCellSize;
    private boolean isActiveBonus;
    private float maxRespawnCooldown;

    private ArrayList<Bonus> bonuses = new ArrayList<>();
    public BonusGenerator(int windowSizeX, int windowSizeY, int gridCellSize) {
        this.respawnCooldown = 2.f;
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.gridCellSize = gridCellSize;
    }

    private void bonusesInit(){
        bonuses.add(new SpeedBonus(2000, 2000, 1.5f, 3.f));
        bonuses.add(new TrailBonus(2000,2000,3));
        bonuses.add(new invincibilityBonus(2000,2000, 3.f));
    }

    public void setRespawnCooldown(float respawnCooldown) {
        this.respawnCooldown = respawnCooldown;
    }

    public void setBonusRandomPosition(Bonus bonus){
        int x = (int) ((Math.random() * (windowSizeX)) + 0) / gridCellSize;
        int y = (int) ((Math.random() * (windowSizeY)) + 0) / gridCellSize;
        bonus.setXY(x,y);
    }
    public boolean isActiveBonus(){
        return isActiveBonus;
    }

    public void spawnRandomBonus(float respawnCooldownCounter){
        //Aleatoriza um indice do tamanho do Array bonus
        int index = new Random().nextInt(bonuses.size());

        //Quando o contador de tempo for maior que o tempo de respawn, entra na função.
        if(respawnCooldownCounter >= maxRespawnCooldown){
            //Define uma posição aleatoria para o bonus
            setBonusRandomPosition(bonuses.get(index));
        }
    }
}
