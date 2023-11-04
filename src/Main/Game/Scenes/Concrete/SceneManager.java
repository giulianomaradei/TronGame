package Main.Game.Scenes.Concrete;

import Main.Game.Scenes.Contracts.Scene;

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
        gameFrame.pack();
        gameFrame.revalidate();
        gameFrame.repaint();
        gameFrame.requestFocus();
    }
}