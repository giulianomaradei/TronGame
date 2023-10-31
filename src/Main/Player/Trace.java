package Main.Player;

import Main.Game.Game;
import Main.GameObject;
import Main.Point;
import Main.TraceableObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Trace extends TraceableObject {

    public Trace(String spriteUrl, int x, int y, TraceableObject nextObject, int index) {
        super(spriteUrl, x, y);

        try {
            this.curvedSprite = ImageIO.read(new File("resources/curvedTrace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.nextObject = nextObject;
        this.index = index;
    }


    private TraceableObject previousObject = null;
    private TraceableObject nextObject;
    private int index = 0;

    BufferedImage curvedSprite = null;

    public void setPreviousObject(TraceableObject previousObject){
        this.previousObject = previousObject;
    }

    public void move() {
        Point nextObjectLastPosition = nextObject.getLastPosition();
        int x = nextObjectLastPosition.getX();
        int y = nextObjectLastPosition.getY();

        this.setLastPosition(this.getX(), this.getY());

        this.setX(x);
        this.setY(y);

        if(previousObject != null){
            ((Trace) previousObject).move();
        }
    }


    @Override
    public void reaction(Player player) {

    }

    @Override
    public void render(Graphics g) {

        BufferedImage traceSprite = this.getSprite();

        int x = this.getX();
        int y = this.getY();

        int nextObjectLastAngle = nextObject.getLastAngle(); // Ultimo angulo do proximo objeto (objeto para qual vamos para a antiga posição dele (trace da frente));
        int nextObjectCurrentAngle = nextObject.getCurrentAngle();
        //int currentAngle = this.getCurrentAngle();



        if(nextObjectLastAngle != nextObjectCurrentAngle){
            traceSprite = curvedSprite;
            if ((nextObjectLastAngle == 0 && nextObjectCurrentAngle == 90) || (nextObjectLastAngle == 90 && nextObjectCurrentAngle == 180) ||
                    (nextObjectLastAngle == 180 && nextObjectCurrentAngle == 270) || (nextObjectLastAngle == 270 && nextObjectCurrentAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.

                if(nextObjectLastAngle  == 180 || nextObjectLastAngle == 0) {
                    traceSprite = flipSpriteVertically(traceSprite);
                }else{
                    traceSprite = flipSpriteHorizontally(traceSprite);
                }
            }
        }

        this.setCurrentAngle(nextObjectLastAngle);


        if(this.previousObject == null){ // Se for o ultimo trace (ponta)
            // se ele estiver na vertical inverte o angulo
            if(nextObjectLastAngle == 90 || nextObjectLastAngle == 270){
                nextObjectLastAngle = Math.abs(nextObjectLastAngle + 180); // invertemos o angulo afinal o ultimo trace sendo aponta para o lado inverso de onde o corpo está andando
            }
        }

        // Criar um novo BufferedImage para a imagem girada
        BufferedImage rotatedImage = new BufferedImage(traceSprite .getWidth(), traceSprite .getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Definir a transformação de rotação com base no ângulo calculado
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(nextObjectLastAngle), traceSprite .getWidth() / 2, traceSprite .getHeight() / 2);
        g2d.setTransform(at);

        // Desenhar a imagem no novo BufferedImage girado
        g2d.drawImage(traceSprite , 0, 0, null);
        g2d.dispose();

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(rotatedImage, x, y, null);
    }

    private BufferedImage flipSpriteHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g2d.dispose();
        return flippedImage;
    }

    private BufferedImage flipSpriteVertically(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, 0, height, width, 0, null);
        g2d.dispose();
        return flippedImage;
    }
}
