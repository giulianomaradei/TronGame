package Main.Game.Scenes.Concrete;

import Main.Game.Scenes.Contracts.Scene;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.Color.black;

public class MenuScene extends Scene {
    private BufferedImage backgroundImg;
    private JLabel titleText = new JLabel();

    public MenuScene(SceneManager sceneManager) {
        super(sceneManager);
        startButton();
        menuBackground();
        title();
        SceneManager.SoundHandler.RunMusic("src/Res/AdagioForTRON.wav");
    }

    private void menuBackground(){
        setBackground(black);
        setLayout(new BorderLayout());

        try {
            backgroundImg = ImageIO.read(new File("resources/GUI/menu_background.jpg"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        ImageIcon menuBackgroundImg = new ImageIcon(backgroundImg);
        JLabel backgroundMenu = new JLabel(menuBackgroundImg);
        add(backgroundMenu);
    }

    private void title(){
        titleText.setLayout(new BorderLayout());

        try {
            BufferedImage titleImage = ImageIO.read(new File("src/Res/titleGame.png"));
            ImageIcon titleIcon = new ImageIcon(titleImage);
            titleText = new JLabel(titleIcon);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        titleText.setSize(50,50);
        titleText.setBorder(new EmptyBorder(50,0,0,0));
        add(titleText, BorderLayout.NORTH);
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
            startButton.setBounds(320 + insets.left, 500 + insets.top,
                    size.width, size.height);

            startButton.addActionListener(e -> {
                SceneManager.SoundHandler.RunTimerSound();
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
