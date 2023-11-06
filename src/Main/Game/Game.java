package Main.Game;

import Main.Game.Bonus.BonusGenerator;
import Main.Game.Panels.SelectCharScene;
import Main.Game.SceneManager;
import Main.Game.Scenes.Concrete.GameFrame;
import Main.Game.Scenes.Contracts.Scene;
import Main.GameObject;
import Main.Game.Player.Concrete.DashPlayer;
import Main.Game.Player.Concrete.JumpPlayer;
import Main.Game.Player.Contracts.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Game {
    static public GameObject[][] grid;
    static public int cellSize = 20;
    static public int gridWidth = 800;
    static public int gridHeight = 600;
    static public Player player1;
    static public Player player2;

    static public HashMap<String, BufferedImage> imageCache = new HashMap<>();

    static public BonusGenerator bonusGenerator;

    public Game(){

        try{
            BufferedImage image;

            BufferedImage DashPlayer = ImageIO.read(new File("resources/Player/DashPlayer/greenMoto.png"));
            BufferedImage DashPlayerStraightTrace = ImageIO.read(new File("resources/Player/DashPlayer/StraightTrace.png"));
            BufferedImage DashPlayerCurvedTrace = ImageIO.read(new File("resources/Player/DashPlayer/CurvedTrace.png"));
            BufferedImage DashPlayerLastTrace = ImageIO.read(new File("resources/Player/DashPlayer/LastTrace.png"));

            BufferedImage JumpPlayer = ImageIO.read(new File("resources/Player/JumpPlayer/blueMoto.png"));
            BufferedImage JumpPlayerStraightTrace = ImageIO.read(new File("resources/Player/JumpPlayer/StraightTrace.png"));
            BufferedImage JumpPlayerCurvedTrace = ImageIO.read(new File("resources/Player/JumpPlayer/CurvedTrace.png"));
            BufferedImage JumpPlayerLastTrace = ImageIO.read(new File("resources/Player/JumpPlayer/LastTrace.png"));

            BufferedImage TeleportPlayer = ImageIO.read(new File("resources/Player/TeleportPlayer/redMoto.png"));
            BufferedImage TeleportPlayerStraightTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/StraightTrace.png"));
            BufferedImage TeleportPlayerCurvedTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/CurvedTrace.png"));
            BufferedImage TeleportPlayerLastTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/LastTrace.png"));

            BufferedImage TrailBonus = ImageIO.read(new File("resources/Bonus/TrailBonus.png"));
            BufferedImage SpeedBonus = ImageIO.read(new File("resources/Bonus/SpeedBonus.png"));
            BufferedImage InvincibleBonus = ImageIO.read(new File("resources/Bonus/InvincibleBonus.png"));
            BufferedImage ShortTraceBonus = ImageIO.read(new File("resources/Bonus/ShortTraceBonus.png"));

            setSpriteAngles(DashPlayer, "DashPlayer");
            setSpriteAngles(DashPlayerStraightTrace, "DashPlayerStraightTrace");
            setSpriteAngles(DashPlayerCurvedTrace, "DashPlayerCurvedTrace");
            setSpriteAngles(DashPlayerLastTrace, "DashPlayerLastTrace");

            setSpriteAngles(JumpPlayer, "JumpPlayer");
            setSpriteAngles(JumpPlayerStraightTrace, "JumpPlayerStraightTrace");
            setSpriteAngles(JumpPlayerCurvedTrace, "JumpPlayerCurvedTrace");
            setSpriteAngles(JumpPlayerLastTrace, "JumpPlayerLastTrace");

            setSpriteAngles(TeleportPlayer, "TeleportPlayer");
            setSpriteAngles(TeleportPlayerStraightTrace, "TeleportPlayerStraightTrace");
            setSpriteAngles(TeleportPlayerCurvedTrace, "TeleportPlayerCurvedTrace");
            setSpriteAngles(TeleportPlayerLastTrace, "TeleportPlayerLastTrace");

            imageCache.put("TrailBonus", TrailBonus);
            imageCache.put("SpeedBonus", SpeedBonus);
            imageCache.put("InvincibilityBonus", InvincibleBonus);
            imageCache.put("ShortTraceBonus", ShortTraceBonus);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            grid = new GameObject[gridWidth / cellSize][gridHeight / cellSize];

            SwingUtilities.invokeLater(() -> {
                GameFrame frame = new GameFrame();
                frame.setVisible(true);
            });
        }

    }

    public static void draw() {
    }

    public static void win(Player player) {

        if(player == player1){
            System.out.println("Player 1 ganhou!");

        } else {
            System.out.println("Player 2 ganhou!");
        }
        System.exit(0);
    }

    public static void lose(Player player) {
        if(player == player1){
            System.out.println("Player 2 Ganhou!");

        } else {
            System.out.println("Player 1 Ganhou!");
        }
        System.exit(0);
    }

    public static void start() {
        Main.Game.Panels.SelectCharScene selectCharScene = SceneManager.getSelectionScene();
        switch (selectCharScene.getPlayer1Id()){
            case 1:
                player2 = new JumpPlayer(760, 560);
                break;
            case 2:
                player2 = new JumpPlayer(760, 560);
                break;
            case 3:
                player2 = new DashPlayer(760, 560);
                break;
        }

        switch (selectCharScene.getPlayer2Id()){
            case 1:
                player1 = new JumpPlayer(20, 560);
                break;
            case 2:
                //Devera ser o vermelho
                player1 = new JumpPlayer(20, 560);
                break;
            case 3:
                player1 = new DashPlayer(20, 560);
                break;
        }
        bonusGenerator = new BonusGenerator();
    }

    public static void setSpriteAngles(BufferedImage sprite, String spriteName){
        for(int angle = 0;angle <= 270; angle+=90){
            BufferedImage rotatedImage = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rotatedImage.createGraphics();
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), sprite.getWidth() / 2, sprite.getHeight() / 2);
            g2d.setTransform(at);

            g2d.drawImage(sprite , 0, 0, null);
            g2d.dispose();

            imageCache.put(spriteName + angle, rotatedImage);
        }
    }
}
