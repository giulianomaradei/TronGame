package Main.Game.Panels;

import Main.Game.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {

    

    public GameScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Chama o método da classe pai

        // Desenhe elementos gráficos personalizados aqui
        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100);

        g.setColor(Color.RED);
        g.drawString("Olá, Mundo!", 150, 200);


    }

    public void render(){

    }

    public void update(){

    }

    public void handleInput(){

    }
}
