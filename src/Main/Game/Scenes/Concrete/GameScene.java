package Main.Game.Scenes.Concrete;

import Main.Game.Game;
import Main.Game.SceneManager;
import Main.Game.Scenes.Contracts.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;

public class GameScene extends Scene {

    private GameFrame gameFrame;
    private static boolean gameOver = false;
    private BufferedImage background;
    private static JLabel gameOverText = new JLabel();

    public GameScene(SceneManager sceneManager, GameFrame gameFrame) {
        super(sceneManager);
        this.gameFrame = gameFrame;

        try{
            background = ImageIO.read(new File("resources/Background.png"));
        }catch (Exception e){
            System.out.println(e);
        }
        SceneManager.SoundHandler.StopMusic();
        initMusic();
        //setStepActions();
        setInputListeners();
        initText();
        Game.start();

        RenderThread renderThread = new RenderThread(gameFrame);
        renderThread.start();
    }

    private void initText(){
        setBackground(Color.BLACK);

        Font fontePersonalizada = null;
        try {
            fontePersonalizada = Font.createFont(Font.TRUETYPE_FONT, new File("resources/menuFont.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fontePersonalizada = fontePersonalizada.deriveFont(24f);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(fontePersonalizada);

        gameOverText.setBorder(new EmptyBorder(280, 0,0,0));
        gameOverText.setForeground(Color.WHITE);
        gameOverText.setFont(fontePersonalizada.deriveFont(Font.PLAIN, 32));
        gameOverText.setLocation(400,600);
        gameOverText.setSize(300, 300);
        gameOverText.setEnabled(true);

        this.add(gameOverText);
    }
    public static void setGameOver(boolean flag){
        gameOver = flag;
    }
    public static void setGameOverText(String player){
        if(!gameOver) {
            gameOverText.setText(player);
            gameOverText.setVisible(true);
        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método da classe pai

        if(!gameOver) {
            g.drawImage(background, 0, 0, null);

            Game.bonusGenerator.renderActiveBonus(g);
            Game.player1.render(g);
            Game.player2.render(g);
        }
    }

    private void initMusic(){
        SceneManager.SoundHandler.RunMusic("resources/Sounds/GameMusic.wav");
    }

}
