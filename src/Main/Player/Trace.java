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

    public Trace(String spriteUrl, int x, int y, TraceableObject nextObject, int index, Player player){
        super(spriteUrl, x, y);

        try {
            this.curvedSprite = ImageIO.read(new File("resources/curvedTrace.png"));
            this.straightSprite = ImageIO.read(new File("resources/straightTrace.png"));
            this.lastSprite = ImageIO.read(new File("resources/lastTrace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.nextObject = nextObject;
        this.index = index;
        this.player = player;
    }


    private TraceableObject previousObject = null;
    private TraceableObject nextObject;
    private int index = 0;

    BufferedImage curvedSprite = null;
    BufferedImage straightSprite = null;
    BufferedImage lastSprite = null;

    public void setPreviousObject(TraceableObject previousObject){
        this.previousObject = previousObject;
    }

    public void move() {
        Point nextObjectLastPosition = nextObject.getLastPosition();
        int new_x = nextObjectLastPosition.getX();
        int new_y = nextObjectLastPosition.getY();

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y);

        this.setX(new_x);
        this.setY(new_y);

        this.updatePositionInGrid(x, y , new_x, new_y);

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

        BufferedImage traceSprite;

        int x = this.getX();
        int y = this.getY();

        int nextObjectLastAngle = nextObject.getLastAngle(); // Ultimo angulo do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        int nextObjectCurrentAngle = nextObject.getCurrentAngle();
        int displayAngle = nextObjectLastAngle;
        //int currentAngle = this.getCurrentAngle();

        if(nextObjectLastAngle != nextObjectCurrentAngle){
            traceSprite = curvedSprite;
            if ((nextObjectLastAngle == 0 && nextObjectCurrentAngle == 270) || (nextObjectLastAngle == 270 && nextObjectCurrentAngle == 180) ||
                    (nextObjectLastAngle == 180 && nextObjectCurrentAngle == 90) || (nextObjectLastAngle == 90 && nextObjectCurrentAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.

                if(nextObjectCurrentAngle  == 180 || nextObjectCurrentAngle == 0 || nextObjectLastAngle == 180 || nextObjectLastAngle == 0) {
                    traceSprite = mirrorVertical(traceSprite);
                }else{
                    traceSprite = mirrorHorizontal(traceSprite);
                }
            }
        }else{
            traceSprite = straightSprite;
        }

        if(this.previousObject == null){ // Se for o ultimo trace (ponta)
            traceSprite = lastSprite;
            // se ele estiver na vertical inverte o angulo
            displayAngle = nextObjectCurrentAngle;
        }

        this.setCurrentAngle(nextObjectLastAngle);
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
