package Main.Game.Panels;

import Main.Game.GameFrame;
import Main.Game.SceneManager;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.Color.black;

public class MenuScene extends Scene {

    public MenuScene(SceneManager sceneManager) {
        super(sceneManager);
        startButton();
        menuBackground();
        SceneManager.SoundHandler.RunMusic("src/Res/AdagioForTRON.wav");
    }

    public void menuBackground(){
        setBackground(black);
        setLayout(new BorderLayout());
        try {
            BufferedImage backgroundImg = ImageIO.read(new File("resources/GUI/menu_background.jpg"));
            ImageIcon menuBackgroundImg = new ImageIcon(backgroundImg);
            JLabel backgroundMenu = new JLabel(menuBackgroundImg);
            backgroundMenu.setIcon(menuBackgroundImg);
            add(backgroundMenu, BorderLayout.CENTER);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void startButton(){
        try {
            BufferedImage buttonIcon = ImageIO.read(new File("src/Res/play_button.png"));
            int newWidth = 160; // Coloco a largura que eu quero da imagem no botão
            int newHeight = 75;  //Coloco altura
            Image scaledImage = buttonIcon.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            ImageIcon startIcon = new ImageIcon(scaledImage); //Transformo em Icon
            JButton startButton = new JButton(startIcon); // Inicio o botão com o Icon
            startButton.setPreferredSize(new Dimension(160, 80)); //Tamanho do botão
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setContentAreaFilled(false);
            add(startButton);

            Insets insets = this.getInsets();
            Dimension size = startButton.getPreferredSize();
            startButton.setBounds(325 + insets.left, 500 + insets.top,
                    size.width, size.height);

            startButton.addActionListener(e -> {
                //this.sceneManager.showGameplay();
                this.sceneManager.showSelectionScene();
            });

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void exitButton(){

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
