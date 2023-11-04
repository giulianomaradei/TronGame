package Main.Game.Player;

import Main.Game.Game;
import Main.Game.Player.Contracts.Player;
import Main.Point;
import Main.TraceableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trace extends TraceableObject {

    private Player player;
    private String playerSpriteName;

    public Trace(String playerSpriteName, int x, int y, TraceableObject nextObject, int index, Player player){
        super(x, y);

        this.playerSpriteName = playerSpriteName;
        this.nextObject = nextObject;
        this.index = index;
        this.player = player;
    }


    private TraceableObject previousObject = null;
    private TraceableObject nextObject;
    private int index = 0;

    public void setPreviousObject(TraceableObject previousObject){
        this.previousObject = previousObject;
    }

    public void move() {
        Point nextObjectLastPosition = nextObject.getLastPosition();
        int new_x = nextObjectLastPosition.getX();
        int new_y = nextObjectLastPosition.getY();

        int new_angle = nextObject.getLastAngle();
        this.setCurrentAngle(new_angle);

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y);

        this.setX(new_x);
        this.setY(new_y);

        this.setPositionInGrid(new_x, new_y);
        this.removePositionInGrid(x, y);

        if(previousObject != null){
            ((Trace) previousObject).move();
        }
    }


    @Override
    public void reaction(Player player) {
        Game.lose(player);
    }

    @Override
    public void render(Graphics g) {

        int x = this.getX();
        int y = this.getY();

        int nextObjectLastAngle = nextObject.getLastAngle(); // Ultimo angulo do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        int nextObjectCurrentAngle = nextObject.getCurrentAngle();

        BufferedImage traceSprite;

        if(nextObjectLastAngle != nextObjectCurrentAngle){
            traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + nextObjectLastAngle );

            if ((nextObjectLastAngle == 0 && nextObjectCurrentAngle == 270) || (nextObjectLastAngle == 270 && nextObjectCurrentAngle == 180) ||
                    (nextObjectLastAngle == 180 && nextObjectCurrentAngle == 90) || (nextObjectLastAngle == 90 && nextObjectCurrentAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.

                if(nextObjectCurrentAngle  == 180 || nextObjectCurrentAngle == 0 || nextObjectLastAngle == 180 || nextObjectLastAngle == 0) {
                    int displayAngle = Math.abs(nextObjectLastAngle + 90) == 360 ? 0 : Math.abs(nextObjectLastAngle + 90);
                    traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + displayAngle );
                }else{
                    int displayAngle = Math.abs(nextObjectLastAngle + 270) == 360 ? 0 : Math.abs(nextObjectLastAngle + 270);
                    traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + displayAngle );
                }
            }
        }else{
            traceSprite = this.getSprite(playerSpriteName + "StraightTrace" + nextObjectLastAngle );
        }

        if(this.previousObject == null){ // Se for o ultimo trace (ponta)
            traceSprite = this.getSprite(playerSpriteName + "LastTrace" + nextObjectCurrentAngle );
        }

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(traceSprite, x, y, null);

        if(previousObject != null){
            ((Trace) previousObject).render(g);
        }
    }

}
