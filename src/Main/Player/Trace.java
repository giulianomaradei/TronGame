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

    public Trace(String spriteUrl, int x, int y, TraceableObject previousObject, int index) {
        super(spriteUrl, x, y);

        try {
            this.curvedSprite = ImageIO.read(new File("resources/curvedTrace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.previousObject = previousObject;
        this.index = index;
    }


    private TraceableObject previousObject;
    private TraceableObject nextObject = null;
    private int index = 0;

    BufferedImage curvedSprite = null;

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

        BufferedImage traceSprite = this.getSprite();

        int x = this.getX();
        int y = this.getY();


        int previousAngle = previousObject.getAngle();
        int currentAngle = this.getAngle();

        this.setAngle(previousAngle);

        boolean isTurningLeft = false;

        if(previousAngle != currentAngle){
            traceSprite = curvedSprite;
            if ((currentAngle == 0 && previousAngle == 90) || (currentAngle == 90 && previousAngle == 180) ||
                    (currentAngle == 180 && previousAngle == 270) || (currentAngle == 270 && previousAngle == 0)) {
                // O personagem está virando para a esquerda, portanto, espelhe a sprite horizontalmente.
                isTurningLeft = true;
            }
        }else if (currentAngle % 180 != 0) {
            // O personagem está na vertical, então espelhe a sprite verticalmente.
            traceSprite = flipSpriteVertically(traceSprite);
        }

        if(this.nextObject == null){
            // se ele estiver na vertical inverte o angulo
            if(previousAngle == 90 || previousAngle == 270){
                previousAngle = Math.abs(previousAngle + 180);
            }
        }

        if (isTurningLeft) {
            traceSprite = flipSpriteHorizontally(traceSprite);
        }


        // Criar um novo BufferedImage para a imagem girada
        BufferedImage rotatedImage = new BufferedImage(traceSprite .getWidth(), traceSprite .getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Definir a transformação de rotação com base no ângulo calculado
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(previousAngle), traceSprite .getWidth() / 2, traceSprite .getHeight() / 2);
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
