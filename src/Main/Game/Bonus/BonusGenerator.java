package Main.Game.Bonus;

import Main.Game.Bonus.Concrete.ShortTraceBonus;
import Main.Game.Bonus.Concrete.SpeedBonus;
import Main.Game.Bonus.Concrete.TrailBonus;
import Main.Game.Bonus.Contracts.Bonus;
import Main.Game.Game;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BonusGenerator{
    private Bonus activeBonus;
    private int maxRespawnCooldown = 2000; //
    private int minRespawnCooldown = 8000;

    private TrailBonus trailBonus;
    private SpeedBonus speedBonus;
    private ShortTraceBonus shortTraceBonus;

    public BonusGenerator() {
        scheduleBonusSpawner();
        bonusesInit();
    }

    private void scheduleBonusSpawner() {
        Timer timer = new Timer();
        int delay = ((int)(Math.random() * minRespawnCooldown) + maxRespawnCooldown); // calcula tempo aleatorio entre 2 e 8 segundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnRandomBonus();
            }
        }, delay); // cria o bonus depois do delay
    }

    private void bonusesInit(){
        trailBonus = new TrailBonus(0, 0);
        speedBonus = new SpeedBonus( 0, 0 );
        shortTraceBonus = new ShortTraceBonus(0, 0);
    }

    private void setBonusRandomPosition(Bonus bonus){
        int i = (int) ((Math.random() * Game.gridWidth) / Game.cellSize);
        int j = (int) ((Math.random() * Game.gridHeight) / Game.cellSize);

        int x = i*Game.cellSize;
        int y = j*Game.cellSize;

        bonus.setPositionInGrid(x, y);

        bonus.setX(x);
        bonus.setY(y);
    }

    private void spawnRandomBonus(){
        int rouletteValue = new Random().nextInt(100);

        if(rouletteValue < 50){ // 50% de chance de spawnar um bonus de trilha
            setActiveBonus(trailBonus);
        } else if (rouletteValue < 75){ // 25% de chance de spawnar um bonus de velocidade
            setActiveBonus(speedBonus);
        }else{
            setActiveBonus(shortTraceBonus); // 25% de chance de spawnar um bonus de rastro curto
        }
    }

    private void setActiveBonus(Bonus bonus){
        activeBonus = bonus;
        setBonusRandomPosition(activeBonus);
    }

    public void renderActiveBonus(Graphics g){
        if(activeBonus != null) {
            activeBonus.render(g);
        }
    }

    public void activeBonusDeactivation(){ // desativa o bonus ativo e "marca" para criar outro
        if(activeBonus != null){
            activeBonus = null;
            scheduleBonusSpawner();
        }
    }
}
