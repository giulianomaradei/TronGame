package Main.Game.Panels;

import Main.Game.Game;
import Main.Game.GameFrame;
import Main.Game.SceneManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.black;

public class SelectCharScene extends Scene {

    private GameFrame gameFrame;
    private int player1Select = 1;
    private int player2Select = 1;
    boolean player1Done = false;
    boolean player2Done = false;
    private int quantMaxCharacters = 3;

    public SelectCharScene(SceneManager sceneManager, GameFrame gameFrame) {
        super(sceneManager);
        this.gameFrame = gameFrame;
        setBackground(black);
        setLayout(null);
        characterImages();
        selectBackground();
        descriptionCharacters();
        selectImages();
    }

    private void selectBackground() {

    }

    private void characterImages() {
        try {
            BufferedImage safiraBImg = ImageIO.read(new File("resources/GUI/Moto1.png"));
            BufferedImage rubyBImg = ImageIO.read(new File("resources/GUI/Moto1.png"));
            BufferedImage esmeraldaBImg = ImageIO.read(new File("resources/GUI/Moto1.png"));

            int newWidth = 128; // Coloco a largura que eu quero da imagem
            int newHeight = 128;  //Coloco altura

            Image scaledImage1 = safiraBImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            Image scaledImage2 = rubyBImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            Image scaledImage3 = esmeraldaBImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão

            ImageIcon safiraIcon = new ImageIcon(scaledImage1);
            ImageIcon rubyIcon = new ImageIcon(scaledImage2);
            ImageIcon esmeraldaIcon = new ImageIcon(scaledImage3);

            JLabel safiraImg = new JLabel(safiraIcon);
            JLabel rubyImg = new JLabel(rubyIcon);
            JLabel esmeraldaImg = new JLabel(esmeraldaIcon);

            add(safiraImg);
            add(rubyImg);
            add(esmeraldaImg);

            Insets insets = this.getInsets();
            Dimension size = safiraImg.getPreferredSize();
            safiraImg.setBounds(25 + insets.left, 50 + insets.top,
                    size.width, size.height);
            rubyImg.setBounds(25 + insets.left, 228 + insets.top,
                    size.width, size.height);
            esmeraldaImg.setBounds(25 + insets.left, 406 + insets.top,
                    size.width, size.height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void descriptionCharacters() {
        try {
            BufferedImage safiraTitImg = ImageIO.read(new File("resources/GUI/SafiraaTit.png"));
            BufferedImage rubyTitImg = ImageIO.read(new File("resources/GUI/RubyTit.png"));
            BufferedImage esmeraldaTitImg = ImageIO.read(new File("resources/GUI/EsmeraldaTit.png"));


            int newWidth = 243; // Coloco a largura que eu quero da imagem
            int newHeight = 37;  //Coloco altura

            Image scaledImage1 = safiraTitImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            Image scaledImage2 = rubyTitImg.getScaledInstance(156, 41, Image.SCALE_SMOOTH);
            Image scaledImage3 = esmeraldaTitImg.getScaledInstance(350, newHeight, Image.SCALE_SMOOTH);

            ImageIcon safiraTitIcon = new ImageIcon(scaledImage1);
            ImageIcon rubyTitIcon = new ImageIcon(scaledImage2);
            ImageIcon esmeraldaTitIcon = new ImageIcon(scaledImage3);

            JLabel safiraTittleImg = new JLabel(safiraTitIcon);
            JLabel rubyTittleImg = new JLabel(rubyTitIcon);
            JLabel esmeraldaTittleImg = new JLabel(esmeraldaTitIcon);

            add(safiraTittleImg);
            add(rubyTittleImg);
            add(esmeraldaTittleImg);


            Insets insets = this.getInsets();
            Dimension size = safiraTittleImg.getPreferredSize();
            safiraTittleImg.setBounds(285 + insets.left, 90 + insets.top,
                    size.width, size.height);
            rubyTittleImg.setBounds(285 + insets.left, 270 + insets.top,
                    size.width, size.height);
            esmeraldaTittleImg.setBounds(240 + insets.left, 450 + insets.top,
                    350, size.height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectImages() {
        try {
            BufferedImage selectBImagesP1 = ImageIO.read(new File("resources/GUI/player1.png"));
            BufferedImage selectBImagesP2 = ImageIO.read(new File("resources/GUI/player2.png"));
            Image selectImgScale1 = selectBImagesP1.getScaledInstance(64, 64, Image.SCALE_SMOOTH); //Modifico a escala do botão
            Image selectImgScale2 = selectBImagesP2.getScaledInstance(64, 64, Image.SCALE_SMOOTH); //Modifico a escala do botão
            ImageIcon selectImgP1 = new ImageIcon(selectImgScale1);
            ImageIcon selectImgP2 = new ImageIcon(selectImgScale2);
            JLabel selectP1 = new JLabel(selectImgP1);
            JLabel selectP2 = new JLabel(selectImgP2);

            add(selectP1);
            add(selectP2);

            Insets insets = this.getInsets();
            Dimension size = selectP1.getPreferredSize();
            selectP1.setBounds(150 + insets.left, 55 + insets.top,
                    size.width, size.height);
            selectP2.setBounds(150 + insets.left, 115 + insets.top,
                    size.width, size.height);
            setInputListeners(selectP1, selectP2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInputListeners(JLabel selectP1, JLabel selectP2) {
        Insets insets = this.getInsets();
        Dimension size = selectP1.getPreferredSize();
        BufferedImage selectBImagesP1 = null;



        gameFrame.addKeyListener(new java.awt.event.KeyAdapter() {

                public void keyPressed(java.awt.event.KeyEvent evt) {
                    switch (evt.getKeyCode()) {
                        case java.awt.event.KeyEvent.VK_UP:
                            if (player1Select > 1) player1Select--;
                            if (player1Select == 1) selectP1.setBounds(150 + insets.left, 55 + insets.top,
                                    size.width, size.height);
                            else if (player1Select == 2) selectP1.setBounds(150 + insets.left, 235 + insets.top,
                                    size.width, size.height);
                            System.out.println("P1: " + player1Select);
                            break;

                        case java.awt.event.KeyEvent.VK_DOWN:
                            if (player1Select < quantMaxCharacters) player1Select++;
                            if (player1Select == 2) selectP1.setBounds(150 + insets.left, 235 + insets.top,
                                    size.width, size.height);
                            else if (player1Select == 3) selectP1.setBounds(150 + insets.left, 420 + insets.top,
                                    size.width, size.height);
                            System.out.println("P1: " + player1Select);
                            break;

                        case java.awt.event.KeyEvent.VK_W:
                            if (player2Select > 1) player2Select--;
                            if (player2Select == 1) selectP2.setBounds(150 + insets.left, 115 + insets.top,
                                    size.width, size.height);
                            else if (player2Select == 2) selectP2.setBounds(150 + insets.left, 295 + insets.top,
                                    size.width, size.height);
                            System.out.println("P2: " + player2Select);
                            break;

                        case KeyEvent.VK_S:
                            if (player2Select < quantMaxCharacters) player2Select++;
                            if (player2Select == 2) selectP2.setBounds(150 + insets.left, 295 + insets.top,
                                    size.width, size.height);
                            else if (player2Select == 3) selectP2.setBounds(150 + insets.left, 480 + insets.top,
                                    size.width, size.height);
                            System.out.println("P2: " + player2Select);
                            break;

                        case KeyEvent.VK_SPACE:
                            if (!player1Done) {
                                player1Done = true;
                                System.out.println(true);
                            }
                            else {
                                player1Done = false;
                                System.out.println(player1Done);
                            }

                        case KeyEvent.VK_ENTER:
                    }
                }
            });
    }

    @Override
    public void render() {

    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {

    }
}
