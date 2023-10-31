package Main.Game.Panels;

import Main.Game.Game;
import Main.Game.SceneManager;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {

    public SceneManager sceneManager;

    public Scene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    public Dimension getPreferredSize() {
        return new Dimension(Game.gridWidth, Game.gridHeight);
    }
    public abstract void render();
    public abstract void update();
    public abstract void handleInput();
}
