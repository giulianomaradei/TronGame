package Main.Game.Scenes.Concrete;

import Main.Game.Game;
import Main.Game.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private SceneManager sceneManager;

    public GameFrame() {
        setTitle("Meu Jogo");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Pega o tamanho da tela

        // Define o tamanho da janela
        int windowWidth = Game.gridWidth;
        int windowHeight = Game.gridHeight;

        // Calcula a posição para centralizar a janela
        int x = (screenSize.width - windowWidth) / 2;
        int y = (screenSize.height - windowHeight) / 2;

        // Define a posição da janela
        setLocation(x, y);
        //setSize(windowWidth, windowHeight);

        sceneManager = new SceneManager(this);
        showMenu(); // Defina a cena inicial
    }

    public void showMenu() {
        sceneManager.showMenu();
    }

    public void showGameplay() {
        sceneManager.showGameplay();
    }

    public void showEndScreen() {
        sceneManager.showEndScreen();
    }
}
