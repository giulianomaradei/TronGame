package Main.Game.Panels;

import Main.Game.GameFrame;
import Main.Game.SceneManager;

import javax.swing.*;
import java.awt.*;

public class MenuScene extends Scene {

    public MenuScene(SceneManager sceneManager) {
        super(sceneManager);

        setLayout(new BorderLayout());

        this.startButton();
        this.title();
    }

    private void startButton(){
        JButton startButton = new JButton("Start Game");
        add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            this.sceneManager.showGameplay();
        });
    }

    private void title(){

        JLabel titleLabel = new JLabel("Tron");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the superclass method
    }

    public void render(){

    }

    public void update(){

    }

    public void handleInput(){

    }
}
