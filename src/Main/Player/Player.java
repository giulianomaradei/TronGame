package Main.Player;

import Main.Collidable;
import Main.Game.Game;
import Main.GameObject;
import Main.TraceableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public abstract class Player extends TraceableObject {

    private double skillCooldown;
    private int vx = 0;
    private int vy = -1;
    private Trace[] traces;

    public Player(String spriteUrl, int x, int y){
        super(spriteUrl, x, y);
        setTraces();
    }

    public void setTraces(){
        this.traces = new Trace[10];
        TraceableObject previousObject = this;
        TraceableObject nextObject = null;

        for (int i = 0; i < traces.length; i++) {
            traces[i] = new Trace("resources/Trace.png", this.getX(), this.getY(), previousObject);
            previousObject = traces[i];

            if(i > 0){
                traces[i-1].setNextObject(traces[i]);
            }
        }
    }

    public void moveUp(){
        if(this.vy == 1){
            return;
        }

        this.vx = 0;
        this.vy = -1;
    }

    public void moveDown(){
        if(this.vy == -1){
            return;
        }
        this.vx = 0;
        this.vy = 1;
    }

    public void moveLeft(){
        if(this.vx == 1){
            return;
        }
        this.vx = -1;
        this.vy = 0;
    }

    public void moveRight(){
        if(this.vx == -1){
            return;
        }
        this.vx = 1;
        this.vy = 0;
    }

    public void move(){

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y);

        this.setX(x + (vx * Game.cellSize));
        this.setY(y + (vy * Game.cellSize));

        checkCollisionWall();
    }

    public void moveTraces(){
        traces[0].move();
    }

    public void renderTraces(Graphics g){
        for (int i = 0; i < traces.length; i++) {
            traces[i].render(g);
        }
    }

    public void checkCollisionWall() {
        int x = this.getX();
        int y = this.getY();
        BufferedImage sprite = this.getSprite();
        int width = sprite.getWidth();
        int height = sprite.getHeight();

        int rightX = x + width;
        int bottomY = y + height;

        if (x < 0) {
            this.setX(0);
        }
        if (rightX > Game.gridWidth) {
            this.setX(Game.gridWidth - width);
        }
        if (y < 0) {
            this.setY(0);
        }
        if (bottomY > Game.gridHeight) {
            this.setY(Game.gridHeight - height);
        }

        // Verificar se o objeto está colidindo após o ajuste
        x = this.getX();
        y = this.getY();
        rightX = x + width;
        bottomY = y + height;

        if (x < 0 || rightX > Game.gridWidth || y < 0 || bottomY > Game.gridHeight) {
            vx = 0;
            vy = 0;
        }
    }



    @Override
    public void render(Graphics g) {
        this.move();
        this.moveTraces();
        this.renderTraces(g);

        BufferedImage playerSprite = this.getSprite();

        int x = this.getX();
        int y = this.getY();

        double rotationAngle = Math.toDegrees(Math.atan2(vy, vx));

        // Criar um novo BufferedImage para a imagem girada
        BufferedImage rotatedImage = new BufferedImage(playerSprite.getWidth(), playerSprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Definir a transformação de rotação com base no ângulo calculado
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle), playerSprite.getWidth() / 2, playerSprite.getHeight() / 2);
        g2d.setTransform(at);

        // Desenhar a imagem no novo BufferedImage girado
        g2d.drawImage(playerSprite, 0, 0, null);
        g2d.dispose();

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(rotatedImage, x, y, null);
    }

}
