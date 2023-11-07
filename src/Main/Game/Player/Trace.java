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
        Point nextObjectLastPosition = nextObject.getLastPosition(); // Ultima posição do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        int new_x = nextObjectLastPosition.getX(); // Nova posição do trace atual
        int new_y = nextObjectLastPosition.getY(); // Nova posição do trace atual

        int new_angle = nextObject.getLastAngle(); // Ultimo angulo do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        this.setCurrentAngle(new_angle); // Define o angulo atual do trace atual

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y); // Define a ultima posição do trace atual

        this.setX(new_x); // Define a nova posição do trace atual
        this.setY(new_y); // Define a nova posição do trace atual

        this.setPositionInGrid(new_x, new_y);
        this.removePositionInGrid(x, y);

        if(previousObject != null){ // Se não for o ultimo trace (ponta)
            ((Trace) previousObject).move(); // Move o trace anterior
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
        int nextObjectCurrentAngle = nextObject.getCurrentAngle(); // Angulo atual do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));

        BufferedImage traceSprite;

        if(nextObjectLastAngle != nextObjectCurrentAngle){ // Se o angulo do proximo objeto for diferente do angulo atual do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente)), isso significa que o trace atual terá de ser curvado;
            traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + nextObjectLastAngle ); // Pega o sprite do trace curvado para o angulo certo

            if ((nextObjectLastAngle == 0 && nextObjectCurrentAngle == 270) || (nextObjectLastAngle == 270 && nextObjectCurrentAngle == 180) ||
                    (nextObjectLastAngle == 180 && nextObjectCurrentAngle == 90) || (nextObjectLastAngle == 90 && nextObjectCurrentAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.

                if(nextObjectCurrentAngle  == 180 || nextObjectCurrentAngle == 0 || nextObjectLastAngle == 180 || nextObjectLastAngle == 0) {
                    int displayAngle = Math.abs(nextObjectLastAngle + 90) == 360 ? 0 : Math.abs(nextObjectLastAngle + 90); // Gira o sprite até ele estar espelhado
                    traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + displayAngle );
                }else{
                    int displayAngle = Math.abs(nextObjectLastAngle + 270) == 360 ? 0 : Math.abs(nextObjectLastAngle + 270); // Gira o sprite até ele estar espelhado
                    traceSprite = this.getSprite(playerSpriteName + "CurvedTrace" + displayAngle );
                }
            }
        }else{
            traceSprite = this.getSprite(playerSpriteName + "StraightTrace" + nextObjectLastAngle ); // Pega o sprite do trace reto para o angulo certo
        }

        if(this.previousObject == null){ // Se for o ultimo trace (ponta)
            traceSprite = this.getSprite(playerSpriteName + "LastTrace" + nextObjectCurrentAngle ); // Pega o sprite do trace da ponta para o angulo certo
        }

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(traceSprite, x, y, null); // Desenha o sprite do trace

        if(previousObject != null){
            ((Trace) previousObject).render(g); // Renderiza o trace anterior
        }
    }

}
