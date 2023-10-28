package Main.Player;

import Main.Game.Game;
import Main.GameObject;
import Main.Point;
import Main.TraceableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trace extends TraceableObject {

    public Trace(String spriteUrl, int x, int y, TraceableObject previousObject) {
        super(spriteUrl, x, y);
        this.previousObject = previousObject;
    }


    private TraceableObject previousObject;
    private TraceableObject nextObject = null;

    public void setNextObject(TraceableObject nextObject){
        this.nextObject = nextObject;
    }

    public void move() {
        Point previousObjectLastPosition = previousObject.getLastPosition();
        int x = previousObjectLastPosition.getX();
        int y = previousObjectLastPosition.getY();

        this.setLastPosition(this.getX(), this.getY());

        this.setX(x);
        this.setY(y);

        if(nextObject != null){
            ((Trace) nextObject).move();
        }
    }


    @Override
    public void reaction(Player player) {

    }

    @Override
    public void render(Graphics g) {

        BufferedImage playerSprite = this.getSprite();

        int x = this.getX();
        int y = this.getY();

        g.drawImage(playerSprite, x, y, null);
    }
}
