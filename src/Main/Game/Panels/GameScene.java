package Main.Game.Panels;

import Main.Game.Game;
import Main.Game.GameFrame;
import Main.Game.SceneManager;
import Main.Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GameScene extends Scene {

    private GameFrame gameFrame;

    public GameScene(SceneManager sceneManager, GameFrame gameFrame) {
        super(sceneManager);
        this.gameFrame = gameFrame;


        setStepActions();
        setInputListeners();

    }

    private void setInputListeners() {
        gameFrame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP:
                        Game.player1.moveUp();
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        Game.player1.moveDown();
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        Game.player1.moveLeft();
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        Game.player1.moveRight();
                        break;
                    case java.awt.event.KeyEvent.VK_W:
                        Game.player2.moveUp();
                        break;
                    case java.awt.event.KeyEvent.VK_S:
                        Game.player2.moveDown();
                        break;
                    case java.awt.event.KeyEvent.VK_A:
                        Game.player2.moveLeft();
                        break;
                    case java.awt.event.KeyEvent.VK_D:
                        Game.player2.moveRight();
                        break;
                }
            }
        });
    }

    private void setStepActions(){
        int delay = 0;
        int interval = 100;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                gameFrame.repaint();
                gameFrame.revalidate();
            }
        }, delay, interval);
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método da classe pai

        int GameFrameWidth = gameFrame.getWidth();
        //for(int i=Game.cellSize;i<GameFrameWidth;i+=Game.cellSize){
            //g.drawLine(i,0, i, GameFrameWidth);
            //g.drawLine(0,i, GameFrameWidth, i);

        //}

        Game.player1.render(g);
        Game.player2.render(g);
    }

    public void render(){

    }

    public void update(){

    }

    public void handleInput(){

    }


}
