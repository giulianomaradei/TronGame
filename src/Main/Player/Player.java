package Main.Player;

import Main.Game.Game;
import Main.Point;
import Main.TraceableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Player extends TraceableObject {

    public Player(int x, int y){
        super(x, y);
        setStepActions();
    }
    private double skillCooldown;
    private int currentHorizontalSpeed = 0;
    private int currentVerticalSpeed = -1;

    private int nextHorizontalSpeed = 0;
    private int nextVerticalSpeed = -1;

    private int nextAngle = 270;
    private ArrayList<Trace> traces = new ArrayList<>();

    private int totalTraces = 20;
    private boolean canMove = true;

    private Timer timer;
    private int interval = 120;

    private void setStepActions(){
        int delay = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                move();
                moveTraces();
            }
        }, delay, interval);
    };

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

        checkCollisionWall(new_x, new_y);
        checkCollision(new_x, new_y);

        this.setPositionInGrid(new_x, new_y);
        this.removePositionInGrid(x, y);

        this.currentHorizontalSpeed = nextHorizontalSpeed;
        this.currentVerticalSpeed = nextVerticalSpeed;
        this.setCurrentAngle(this.nextAngle);

        if(traces.size() < totalTraces){
            addTrace();
        }
    }

    private void addTrace() {
        TraceableObject nextObject = traces.size() == 0 ? this : traces.getLast();

        Point lastPosition = nextObject.getLastPosition();

        traces.add(new Trace(this.getSpriteName(), lastPosition.getX(), lastPosition.getY(), nextObject, traces.size()-1, this));

        if(traces.size() > 1){
            traces.get(traces.size()-2).setPreviousObject(traces.getLast());
        }
    }

    private void checkCollision(int x, int y) {
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(i >= Game.gridWidth || j >= Game.gridHeight || i < 0 || j < 0){
            return;
        }

        if(Game.grid[i][j] != null){
            Game.grid[i][j].reaction(this);
        }
    }

    private void moveTraces(){
        traces.getFirst().move();
    }

    private void renderTraces(Graphics g){
        for (Trace trace : traces) {
            trace.render(g);
        }
    }

    private void checkCollisionWall(int new_x, int new_y) {
        int width = Game.cellSize;
        int height = Game.cellSize;

        int rightX = new_x + width;
        int bottomY = new_y + height;

        if (new_x < 0 || rightX > Game.gridWidth || new_y < 0 || bottomY > Game.gridHeight) {
           //Game.lose(this);
            canMove = false;
        }
    }


    //////// BONUS REACTIONS ////////

    public void trailBonusReaction(){
        this.totalTraces += 2;
    }

    public void speedBonusReaction(){
        setStepRate(80);

        Timer timer = new Timer();
        int delay = 2000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setStepRate(120);
            }
        }, delay);
    }

    private void setStepRate(int rate){
        if (timer != null) {
            timer.cancel(); // Interrompe o Timer atual
        }

        move();
        moveTraces();

        interval = rate; // Atualiza o intervalo

        // Cria um novo Timer com o novo intervalo
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                move();
                moveTraces();
            }
        }, 0, interval);
    }



    @Override
    public void render(Graphics g) {
        this.renderTraces(g);

        BufferedImage playerSprite = this.getSprite(this.getSpriteName() + this.getCurrentAngle());

        int x = this.getX();
        int y = this.getY();

        // Desenhar o BufferedImage girado no JPanel
        g.drawImage(playerSprite, x, y, null);
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
