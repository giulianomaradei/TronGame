package Main.Game.Panels;

import Main.Game.SceneManager;

import javax.swing.*;

public abstract class Scene extends JPanel {

    public SceneManager sceneManager;

    public Scene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }
    public abstract void render();
    public abstract void update();
    public abstract void handleInput();
}
