package Main.Game;

import Main.Game.Bonus.BonusGenerator;
import Main.GameObject;
import Main.Player.ConcretePlayers.DashPlayer;
import Main.Player.ConcretePlayers.JumpPlayer;
import Main.Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
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

            BufferedImage DashPlayer = ImageIO.read(new File("resources/Player/DashPlayer/Bike.png"));
            BufferedImage DashPlayerStraightTrace = ImageIO.read(new File("resources/Player/DashPlayer/StraightTrace.png"));
            BufferedImage DashPlayerCurvedTrace = ImageIO.read(new File("resources/Player/DashPlayer/CurvedTrace.png"));
            BufferedImage DashPlayerLastTrace = ImageIO.read(new File("resources/Player/DashPlayer/LastTrace.png"));

            BufferedImage JumpPlayer = ImageIO.read(new File("resources/Player/JumpPlayer/Bike.png"));
            BufferedImage JumpPlayerStraightTrace = ImageIO.read(new File("resources/Player/JumpPlayer/StraightTrace.png"));
            BufferedImage JumpPlayerCurvedTrace = ImageIO.read(new File("resources/Player/JumpPlayer/CurvedTrace.png"));
            BufferedImage JumpPlayerLastTrace = ImageIO.read(new File("resources/Player/JumpPlayer/LastTrace.png"));

            BufferedImage TeleportPlayer = ImageIO.read(new File("resources/Player/TeleportPlayer/Bike.png"));
            BufferedImage TeleportPlayerStraightTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/StraightTrace.png"));
            BufferedImage TeleportPlayerCurvedTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/CurvedTrace.png"));
            BufferedImage TeleportPlayerLastTrace = ImageIO.read(new File("resources/Player/TeleportPlayer/LastTrace.png"));

            BufferedImage TrailBonus = ImageIO.read(new File("resources/Bonus/TrailBonus.png"));
            BufferedImage SpeedBonus = ImageIO.read(new File("resources/Bonus/SpeedBonus.png"));
            BufferedImage InvincibleBonus = ImageIO.read(new File("resources/Bonus/InvincibleBonus.png"));

            imageCache.put("DashPlayer", DashPlayer);
            imageCache.put("DashPlayerStraightTrace", DashPlayerStraightTrace);
            imageCache.put("DashPlayerCurvedTrace", DashPlayerCurvedTrace);
            imageCache.put("DashPlayerLastTrace", DashPlayerLastTrace);

            imageCache.put("JumpPlayer", JumpPlayer);
            imageCache.put("JumpPlayerStraightTrace", JumpPlayerStraightTrace);
            imageCache.put("JumpPlayerCurvedTrace", JumpPlayerCurvedTrace);
            imageCache.put("JumpPlayerLastTrace", JumpPlayerLastTrace);

            imageCache.put("TeleportPlayer", TeleportPlayer);
            imageCache.put("TeleportPlayerStraightTrace", TeleportPlayerStraightTrace);
            imageCache.put("TeleportPlayerCurvedTrace", TeleportPlayerCurvedTrace);
            imageCache.put("TeleportPlayerLastTrace", TeleportPlayerLastTrace);

            imageCache.put("TrailBonus", TrailBonus);
            imageCache.put("SpeedBonus", SpeedBonus);
            imageCache.put("InvincibleBonus", InvincibleBonus);

        }catch (Exception e) {
            e.printStackTrace();
        }
        grid = new GameObject[gridWidth / cellSize][gridHeight / cellSize];


        player1 = new DashPlayer(400, 400);
        player2 = new JumpPlayer(300, 300);
        bonusGenerator = new BonusGenerator();

        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
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
}
