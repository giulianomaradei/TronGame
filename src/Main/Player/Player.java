package Main.Player;

import Main.Game.Game;
import Main.TraceableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public abstract class Player extends TraceableObject {

    private double skillCooldown;
    private int currentHorizontalSpeed = 0;
    private int currentVerticalSpeed = -1;

    private int nextHorizontalSpeed = 0;
    private int nextVerticalSpeed = -1;
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
            String traceUrl = "resources/straightTrace.png";
            if(i == traces.length-1){
                traceUrl = "resources/lastTrace.png";
            }
            traces[i] = new Trace(traceUrl, this.getX(), this.getY(), previousObject, i);
            previousObject = traces[i];

            if(i > 0){
                traces[i-1].setNextObject(traces[i]);
            }
        }
    }

    public void moveUp(){
        if(this.currentVerticalSpeed == 1 || this.currentVerticalSpeed == -1){
            return;
        }

        this.setAngle(90);
        this.nextHorizontalSpeed = 0;
        this.nextVerticalSpeed = -1;
    }

    public void moveDown(){
        if(this.currentVerticalSpeed == -1 || this.currentVerticalSpeed == 1){
            return;
        }

        this.setAngle(270);
        this.nextHorizontalSpeed = 0;
        this.nextVerticalSpeed = 1;
    }

    public void moveLeft(){
        if(this.currentHorizontalSpeed == 1 || this.currentHorizontalSpeed == -1){
            return;
        }

        this.setAngle(180);
        this.nextHorizontalSpeed = -1;
        this.nextVerticalSpeed = 0;
    }

    public void moveRight(){
        if(this.currentHorizontalSpeed == -1 || this.currentHorizontalSpeed == 1){
            return;
        }

        this.setAngle(0);
        this.nextHorizontalSpeed = 1;
        this.nextVerticalSpeed = 0;
    }

    public void move(){

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y);

        this.setX(x + (nextHorizontalSpeed * Game.cellSize));
        this.setY(y + (nextVerticalSpeed * Game.cellSize));

        this.currentHorizontalSpeed = nextHorizontalSpeed;
        this.currentVerticalSpeed = nextVerticalSpeed;

        checkCollisionWall();
    }

    public void moveTraces(){
        traces[0].move();
    }

    public void renderTraces(Graphics g){
        for (int i = traces.length-1; i >= 0; i--) {
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
            currentHorizontalSpeed = 0;
            currentVerticalSpeed = 0;
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

        double rotationAngle = Math.toDegrees(Math.atan2(currentVerticalSpeed, currentHorizontalSpeed));

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
