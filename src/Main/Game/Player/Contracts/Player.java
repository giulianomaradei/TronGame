package Main.Game.Player.Contracts;

import Main.Game.Game;
import Main.Game.Player.Trace;
import Main.Point;
import Main.TraceableObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;


public abstract class Player extends TraceableObject {

    public Player(int x, int y){
        super(x, y);
        setStepRate(120);
    }
    private boolean isDead = false;
    private double skillCooldown;
    private int currentHorizontalSpeed = 0;
    private int currentVerticalSpeed = -1;

    private int nextHorizontalSpeed = 0;
    private int nextVerticalSpeed = -1;

    private int nextAngle = 270;
    private final ArrayList<Trace> traces = new ArrayList<>();

    private int totalTraces = 20;
    private boolean canMove = true;

    private Timer timer;
    private int interval = 120;

    private final Semaphore moveRenderSemaphore = new Semaphore(1);

    private boolean isInvencible = false;
    private boolean isShorted = false;


    public void moveUp(){
        if(this.currentVerticalSpeed == 1 || this.currentVerticalSpeed == -1){ //se ele estiver indo para baixo ou para cima
            return; //não faz nada, pois se está indo pra cima e apertou pra cima, não precisa fazer nada e se está indo pra baixo e apertou pra cima, não pode fazer nada
        }

        this.nextAngle = 270;
        this.nextHorizontalSpeed = 0; // 0 é parado
        this.nextVerticalSpeed = -1; // -1 é para cima
    }

    public void moveDown(){
        if(this.currentVerticalSpeed == -1 || this.currentVerticalSpeed == 1){ //se ele estiver indo para cima ou para baixo
            return;
        }

        this.nextAngle = 90;
        this.nextHorizontalSpeed = 0; // 0 é parado
        this.nextVerticalSpeed = 1; // 1 é para baixo
    }

    public void moveLeft(){
        if(this.currentHorizontalSpeed == 1 || this.currentHorizontalSpeed == -1){ //se ele estiver indo para direita ou para esquerda
            return;
        }

        this.nextAngle = 180;
        this.nextHorizontalSpeed = -1; // -1 é para esquerda
        this.nextVerticalSpeed = 0; // 0 é parado
    }

    public void moveRight(){
        if(this.currentHorizontalSpeed == -1 || this.currentHorizontalSpeed == 1){ //se ele estiver indo para esquerda ou para direita
            return;
        }

        this.nextAngle = 0;
        this.nextHorizontalSpeed = 1; // 1 é para direita
        this.nextVerticalSpeed = 0; // 0 é parado
    }

    public void move(){

        if(!canMove){
            return;
        }

        try {
            moveRenderSemaphore.acquire(); // Adquire o semáforo para poder mover antes de renderizar

            int x = this.getX();
            int y = this.getY();

            this.setLastPosition(x, y);

            int new_x = this.setX(x + (nextHorizontalSpeed * Game.cellSize)); // Calcula a nova posição
            int new_y = this.setY(y + (nextVerticalSpeed * Game.cellSize)); // Calcula a nova posição

            checkCollisionWall(new_x, new_y); // Verifica se bateu na parede
            checkCollision(new_x, new_y); // Verifica se bateu em outro objeto

            this.setPositionInGrid(new_x, new_y); // Atualiza a posição na matriz utilizada para verificar colisoes
            this.removePositionInGrid(x, y); // Remove a posição anterior da matriz

            this.currentHorizontalSpeed = nextHorizontalSpeed;
            this.currentVerticalSpeed = nextVerticalSpeed;
            this.setCurrentAngle(this.nextAngle);

            if(traces.size() < totalTraces && !isShorted){ //se o tamanho da lista de traços for menor que o tamanho total de traços e o jogador não estiver com o bonus de shortTrace
                addTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            moveRenderSemaphore.release(); // Libera o semáforo para poder renderizar
        }

    }

    private void addTrace() {
        TraceableObject nextObject = traces.size() == 0 ? this : traces.getLast(); //se não tiver nenhum traço, o próximo objeto é o próprio jogador, se não, o próximo objeto é o último traço adicionado

        Point lastPosition = nextObject.getLastPosition(); //pega a última posição do objeto

        traces.add(new Trace(this.getSpriteName(), lastPosition.getX(), lastPosition.getY(), nextObject, traces.size()-1, this)); //adiciona um novo traço na lista de traços

        if(traces.size() > 1){ //se tiver mais de um traço na lista de traços
            traces.get(traces.size()-2).setPreviousObject(traces.getLast());  //seta o objeto anterior do penúltimo traço como o último traço adicionado
        }
    }

    private void checkCollision(int x, int y) {
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(i >= Game.gridWidth || j >= Game.gridHeight || i < 0 || j < 0){ //se o jogador estiver fora da tela
            return;
        }

        if(Game.grid[i][j] != null){ //se o jogador estiver colidindo com algum objeto
            Game.grid[i][j].reaction(this); //chama a reação do objeto
        }
    }

    private void moveTraces(){
        traces.getFirst().move(); //move o primeiro traço da lista de traços
    }

    private void renderTraces(Graphics g){
        traces.getFirst().render(g); //renderiza o primeiro traço da lista de traços
    }

    private void checkCollisionWall(int new_x, int new_y) {
        int width = Game.cellSize;
        int height = Game.cellSize;

        int rightX = new_x + width; //calcula o offset da direita do jogador
        int bottomY = new_y + height; //calcula o offset de baixo do jogador

        if (new_x < 0 || rightX > Game.gridWidth || new_y < 0 || bottomY > Game.gridHeight) { //se o jogador estiver fora da tela
            Game.lose(this);
            canMove = false;
        }
    }

    private void setStepRate(int rate){
        if (timer != null) {
            timer.cancel(); // Interrompe o Timer atual
        }

        interval = rate; // Atualiza o intervalo

        // Cria um novo Timer com o novo intervalo
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!isDead) { //se o jogador não estiver morto
                    move();
                    moveTraces();
                }
            }
        }, 0, interval); // Inicia o Timer para executar a cada intervalo
    }


    //////// BONUS REACTIONS ////////

    public void trailBonusReaction(){
        this.totalTraces += 5; //aumenta o tamanho total de traços
    }

    public void speedBonusReaction(int speedBonus, int durationTime){
        setStepRate(speedBonus); //aumenta a velocidade do jogador aumentando a frequência do timer

        Timer timer = new Timer();
        int delay = durationTime;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setStepRate(120); //volta a velocidade do jogador ao normal
            }
        }, delay);
    }

    public void shortTraceBonusReaction(int traceSize, int durationTime){
        isShorted = true; //seta a flag de shortTrace como true

        while(traces.size() != traceSize){ //enquanto o tamanho da lista de traços for diferente do tamanho do traço do bonus
            Trace trace = traces.getLast(); //pega o último traço da lista de traços
            trace.removePositionInGrid(trace.getX(),trace.getY()); //remove a posição do traço da matriz
            traces.removeLast(); //remove o último traço da lista de traços
        }
        traces.getLast().setPreviousObject(null); //seta o objeto anterior do último traço como null pois excluimos todos os outros

        Timer timer = new Timer();
        int delay = durationTime;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isShorted = false; // voltamos ao normal e todos os traços voltam a ser adicionados até chegar no tamanho total_traces
            }
        }, delay);
    }

    @Override
    public void render(Graphics g) {

        try {
            moveRenderSemaphore.acquire(); // Adquire o semáforo para poder renderizar
            BufferedImage playerSprite = this.getSprite(this.getSpriteName() + this.getCurrentAngle()); // Pega o sprite do jogador utilizando o angulo atual

            int x = this.getX();
            int y = this.getY();

            // Desenhar o BufferedImage girado no JPanel
            g.drawImage(playerSprite, x, y, null); // Desenha o sprite do jogador

            this.renderTraces(g); // Renderiza os traços do jogador
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            moveRenderSemaphore.release();
        }
    }

    public void reaction(Player player){
        int this_angle = this.getCurrentAngle(); //pega o angulo atual do jogador
        int other_angle = player.getCurrentAngle(); //pega o angulo atual do outro jogador

        int difference = Math.abs(this_angle - other_angle); //pega a diferença entre os angulos

        if(isInvencible) return;

        if(difference == 180){ //eles estão batendo de frente um para o outro
            Game.draw(); //empate
        }else{
            this.isDead = true;
            Game.win(this); //o jogador que não bateu de frente ganha
        }
    }
}
