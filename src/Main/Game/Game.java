package Main.Game;

import Main.GameObject;
import Main.Player.ConcretePlayers.DashPlayer;
import Main.Player.Player;

import javax.swing.*;

public class Game {
    static public GameObject[][] grid;
    static public int cellSize = 20;
    static public int gridWidth = 800;
    static public int gridHeight = 600;
    static public Player player1;
    static public Player player2;

    public Game(){

        grid = new GameObject[gridWidth / cellSize][gridHeight / cellSize];
        player1 = new DashPlayer("resources/Moto1.png", 400, 400);
        player2 = new DashPlayer("resources/Player.png", 300, 300);

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
