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

        int delay = 5000;
        int interval = 1000;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                render();
            }
        }, delay, interval);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método da classe pai

        int GameFrameWidth = gameFrame.getWidth();
        for(int i=Game.cellSize;i<GameFrameWidth;i+=Game.cellSize){
            g.drawLine(i,0, i, GameFrameWidth);
            g.drawLine(0,i, GameFrameWidth, i);

        }

        renderPlayer(Game.player1, g);
        renderPlayer(Game.player2, g);
    }

    public void render(){

    }

    public void update(){

    }

    public void handleInput(){

    }

    private void renderPlayer(Player player, Graphics g){
        int x = player.getX() * Game.cellSize;
        int y = player.getY() * Game.cellSize;
        BufferedImage playerSprite = player.getSprite();

        g.drawImage(playerSprite, x, y, null);
    }
}
