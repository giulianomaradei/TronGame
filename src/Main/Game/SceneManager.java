package Main.Game;

import Main.Game.Panels.GameScene;
import Main.Game.Panels.MenuScene;
import Main.Game.Panels.Scene;

import javax.swing.*;

public class SceneManager {
    private GameFrame gameFrame;
    private Scene currentScene;

    public SceneManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void showMenu() {
        changeScene(new MenuScene(this));
    }

    public void showGameplay() {
        changeScene(new GameScene(this, gameFrame));
    }

    public void showEndScreen() {
        // Implemente a lógica para alternar para a tela final de maneira semelhante.
    }

    private void changeScene(Scene scene){
        if(currentScene != null){
            gameFrame.remove(currentScene);
        }
        currentScene = scene;
        gameFrame.add(currentScene);
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}