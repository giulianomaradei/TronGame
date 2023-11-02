package Main.Game;

import Main.Game.Bonus.BonusGenerator;
import Main.GameObject;
import Main.Player.ConcretePlayers.DashPlayer;
import Main.Player.Player;

import javax.swing.*;

public class Game {
    static public Player player1 = new DashPlayer("resources/Moto1.png", 0, 0);
    static public Player player2 = new DashPlayer("resources/Moto1.png", 0, 5);;

    static private GameObject[][] grid;
    static public int cellSize = 20;
    static public int gridWidth = 800;
    static public int gridHeight = 600;
    static public BonusGenerator bonusGenerator = new BonusGenerator();

    public Game(){
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }

    public void render(){

    }
}
