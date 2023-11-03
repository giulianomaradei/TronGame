package Main.Game.Panels;

import Main.Game.Game;
import Main.Game.GameFrame;
import Main.Game.SceneManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.black;

public class SelectCharScene extends Scene{

    public SelectCharScene(SceneManager sceneManager){
        super(sceneManager);
        setBackground(black);
        setLayout(null);
        characterImages();
        selectBackground();
        descriptionCharacters();
        selectImages();
    }

    private void selectBackground(){

    }

    private void characterImages(){
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

            JLabel safiraImg2 = new JLabel(safiraIcon);
            JLabel rubyImg2 = new JLabel(rubyIcon);
            JLabel esmeraldaImg2 = new JLabel(esmeraldaIcon);

            add(safiraImg);
            add(rubyImg);
            add(esmeraldaImg);
            add(safiraImg2);
            add(rubyImg2);
            add(esmeraldaImg2);

            Insets insets = this.getInsets();
            Dimension size = safiraImg.getPreferredSize();
            safiraImg.setBounds(25 + insets.left, 50 + insets.top,
                    size.width, size.height);
            rubyImg.setBounds(25 + insets.left, 228 + insets.top,
                    size.width, size.height);
            esmeraldaImg.setBounds(25 + insets.left, 406 + insets.top,
                    size.width, size.height);
            safiraImg2.setBounds(647 + insets.left, 50 + insets.top,
                    size.width, size.height);
            rubyImg2.setBounds(647 + insets.left, 228 + insets.top,
                    size.width, size.height);
            esmeraldaImg2.setBounds(647 + insets.left, 406 + insets.top,
                    size.width, size.height);



        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void descriptionCharacters(){

    }

    private void selectImages(){
        try{
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
        } catch (IOException e){
            e.printStackTrace();
        }

    }
/*
    private void setInputListeners() {
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                System.out.println("Teste");
                switch (evt.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP:
                        System.out.println("Seta para Cima pressionada");
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        System.out.println("Seta para Baixo pressionada");
                        break;
                    case java.awt.event.KeyEvent.VK_W, java.awt.event.KeyEvent.VK_S:
                        System.out.println("Seta");
                        break;
                }
            }
        });
    }
*/

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
