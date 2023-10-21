package Main.Player;

import Main.Collidable;
import Main.Game.Game;
import Main.GameObject;


public abstract class Player extends GameObject {




    private double skillCooldown;
    private int vx;
    private int vy;
    private Trace[] traces;

    public Player(String spriteUrl, int x, int y, int vx, int vy){
        super(spriteUrl, x, y);
        this.vx = vx;
        this.vy = vy;
        this.skillCooldown = skillCooldown;
    }

    private void movement(){

        int x = this.getX();
        int y = this.getY();

        x += Game.cellSize * vx;
        y += Game.cellSize * vy;

        this.setXY(x, y);

        traces[0].movement();
    }

}
