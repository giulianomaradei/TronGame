package Main.Player;

import Main.Game.Game;
import Main.Point;
import Main.TraceableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Player extends TraceableObject {

    private double skillCooldown;
    private int currentHorizontalSpeed = 0;
    private int currentVerticalSpeed = -1;

    private int nextHorizontalSpeed = 0;
    private int nextVerticalSpeed = -1;

    private int nextAngle = 270;
    private ArrayList<Trace> traces = new ArrayList<>();

    private int totalTraces = 20;
    private boolean canMove = true;

    public Player(String spriteUrl, int x, int y){
        super(spriteUrl, x, y);
    }

    public void moveUp(){
        if(this.currentVerticalSpeed == 1 || this.currentVerticalSpeed == -1){
            return;
        }

        this.nextAngle = 270;
        this.nextHorizontalSpeed = 0;
        this.nextVerticalSpeed = -1;
    }

    public void moveDown(){
        if(this.currentVerticalSpeed == -1 || this.currentVerticalSpeed == 1){
            return;
        }

        this.nextAngle = 90;
        this.nextHorizontalSpeed = 0;
        this.nextVerticalSpeed = 1;
    }

    public void moveLeft(){
        if(this.currentHorizontalSpeed == 1 || this.currentHorizontalSpeed == -1){
            return;
        }

        this.nextAngle = 180;
        this.nextHorizontalSpeed = -1;
        this.nextVerticalSpeed = 0;
    }

    public void moveRight(){
        if(this.currentHorizontalSpeed == -1 || this.currentHorizontalSpeed == 1){
            return;
        }

        this.nextAngle = 0;
        this.nextHorizontalSpeed = 1;
        this.nextVerticalSpeed = 0;
    }

    public void move(){

        if(!canMove){
            return;
        }

        int x = this.getX();
        int y = this.getY();

        this.setLastPosition(x, y);

        int new_x = this.setX(x + (nextHorizontalSpeed * Game.cellSize));
        int new_y = this.setY(y + (nextVerticalSpeed * Game.cellSize));

        checkCollision(new_x, new_y);

        this.setPositionInGrid(new_x, new_y);
        this.removePositionInGrid(x, y);

        this.currentHorizontalSpeed = nextHorizontalSpeed;
        this.currentVerticalSpeed = nextVerticalSpeed;
        this.setCurrentAngle(this.nextAngle);

        checkCollisionWall();

        if(traces.size() < totalTraces){
            addTrace();
        }
    }

    private void addTrace() {
        TraceableObject nextObject = traces.size() == 0 ? this : traces.getLast();

        String lastTraceUrl = "resources/lastTrace.png";

        Point lastPosition = nextObject.getLastPosition();

        traces.add(new Trace(lastTraceUrl, lastPosition.getX(), lastPosition.getY(), nextObject, traces.size()-1, this));

        if(traces.size() > 1){
            traces.get(traces.size()-2).setPreviousObject(traces.getLast());
        }
    }

    private void checkCollision(int x, int y) {
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(Game.grid[i][j] != null){
            Game.grid[i][j].reaction(this);
        }
    }

    public void moveTraces(){
        traces.getFirst().move();
    }

    public void renderTraces(Graphics g){
        for (Trace trace : traces) {
            trace.render(g);
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

        if (x <= 0 || rightX >= Game.gridWidth || y <= 0 || bottomY >= Game.gridHeight) {
            //Game.lose(this);
            canMove = false;
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

        double rotationAngle = this.getCurrentAngle();

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

    public void reaction(Player player){
        int this_angle = this.getCurrentAngle();
        int other_angle = player.getCurrentAngle();

        int difference = Math.abs(this_angle - other_angle);

        if(difference == 180){ //eles estão batendo de frente um para o outro
            Game.draw();
        }else{
            Game.win(this);
        }
    }

}
