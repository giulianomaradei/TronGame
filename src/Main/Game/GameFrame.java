package Main.Game;

import javax.swing.*;

public class GameFrame extends JFrame {
    private SceneManager sceneManager;

    public GameFrame() {
        setTitle("Meu Jogo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
