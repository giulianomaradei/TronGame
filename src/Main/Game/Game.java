package Main.Game;

import Main.Player.Player;

import javax.swing.*;

public class Game {
    private Player player1;
    private Player player2;

    static private Object[][] grid;
    static public int cellSize = 40;
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
