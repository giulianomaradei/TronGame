package Main.Game;

import Main.GameObject;
import Main.Player.ConcretePlayers.DashPlayer;
import Main.Player.Player;

import javax.swing.*;

public class Game {
    static public Player player1 = new DashPlayer("resources/Player.png", 0, 0, 1, 0);
    static public Player player2 = new DashPlayer("resources/Player.png", 10, 10, 1, 0);;

    static private GameObject[][] grid;
    static public int cellSize = 20;
    static public int boardSize = 10;

    public Game(){
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }


    public void render(){

    }

}
