package Main.Player;

import Main.Game.Game;
import Main.Point;
import Main.TraceableObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

        boolean shouldMirrorVertical = false;
        boolean shouldMirrorHorizontal = false;

        int x = this.getX();
        int y = this.getY();

        int nextObjectLastAngle = nextObject.getLastAngle(); // Ultimo angulo do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        int nextObjectCurrentAngle = nextObject.getCurrentAngle();
        int displayAngle = nextObjectLastAngle;
        //int currentAngle = this.getCurrentAngle();

        if(nextObjectLastAngle != nextObjectCurrentAngle){
            this.setSprite(playerSpriteName + "CurvedTrace");

            if ((nextObjectLastAngle == 0 && nextObjectCurrentAngle == 270) || (nextObjectLastAngle == 270 && nextObjectCurrentAngle == 180) ||
                    (nextObjectLastAngle == 180 && nextObjectCurrentAngle == 90) || (nextObjectLastAngle == 90 && nextObjectCurrentAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.

                if(nextObjectCurrentAngle  == 180 || nextObjectCurrentAngle == 0 || nextObjectLastAngle == 180 || nextObjectLastAngle == 0) {
                    shouldMirrorVertical = true;
                }else{
                    shouldMirrorHorizontal = true;
                }
            }
        }else{
            this.setSprite(playerSpriteName + "StraightTrace");
        }

        if(this.previousObject == null){ // Se for o ultimo trace (ponta)
            this.setSprite(playerSpriteName + "LastTrace");
            // se ele estiver na vertical inverte o angulo
            displayAngle = nextObjectCurrentAngle;
        }

        BufferedImage traceSprite = this.getSprite();

        if(shouldMirrorVertical){
            traceSprite = mirrorVertical(traceSprite);
        } else if (shouldMirrorHorizontal){
            traceSprite = mirrorHorizontal(traceSprite);
        }

        // Criar um novo BufferedImage para a imagem girada
        BufferedImage rotatedImage = new BufferedImage(traceSprite.getWidth(), traceSprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Definir a transformação de rotação com base no ângulo calculado
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(displayAngle), traceSprite.getWidth() / 2, traceSprite.getHeight() / 2);
        g2d.setTransform(at);

        // Desenhar a imagem no novo BufferedImage girado
        g2d.drawImage(traceSprite , 0, 0, null);
        g2d.dispose();

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(rotatedImage, x, y, null);
    }

    public static BufferedImage mirrorVertical(BufferedImage originalImage) {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -originalImage.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }

    public static BufferedImage mirrorHorizontal(BufferedImage originalImage) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-originalImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(originalImage, null);
    }
}
