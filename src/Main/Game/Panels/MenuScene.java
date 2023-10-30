package Main.Game.Panels;

import Main.Game.SceneManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.awt.Color.black;

public class MenuScene extends Scene {
    public MenuScene(SceneManager sceneManager) {
        super(sceneManager);
        setBackground(black);
        try {
            BufferedImage backgroundImg = null;
            backgroundImg = ImageIO.read(new File("menu_background.jpg"));
            ImageIcon menuBackgroundImg = new ImageIcon(backgroundImg);
            JLabel backgroundMenu = new JLabel(menuBackgroundImg);
            backgroundMenu.setSize(400,400);
            backgroundMenu.setIcon(menuBackgroundImg);
            add(backgroundMenu);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.startButton();
        this.title();
    }

    private void startButton(){
        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File("play_button.png"));
            int newWidth = 180; // Coloco a largura que eu quero do botão
            int newHeight = 85;  //Coloco altura
            Image scaledImage = buttonIcon.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            ImageIcon startIcon = new ImageIcon(scaledImage); //Transformo em Icon
            JButton startButton = new JButton(startIcon); // Inicio o botão com o Icon
            startButton.setPreferredSize(new Dimension(200, 100));
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setContentAreaFilled(false);
            add(startButton, BorderLayout.SOUTH);


            add(startButton);

            startButton.addActionListener(e -> {
                // Adicione aqui as ações que deseja executar quando o botão for clicado.
                this.sceneManager.showGameplay();
            });

            /*startButton.addMouseMotionListener(e-> {

            });*/
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void title(){
    /*
        JLabel titleLabel = new JLabel("Tron");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
    */
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
