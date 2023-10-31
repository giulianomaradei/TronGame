package Main.Game.Panels;

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
        menuBackground();
        startButton();
        SoundHandler.RunMusic("src/Res/AdagioForTRON.wav");
    }

    public void menuBackground(){
        setBackground(black);
        try {

            BufferedImage backgroundImg = null;
            backgroundImg = ImageIO.read(new File("src/Res/menu_background.jpg"));
            ImageIcon menuBackgroundImg = new ImageIcon(backgroundImg);
            JLabel backgroundMenu = new JLabel(menuBackgroundImg);
            backgroundMenu.setIcon(menuBackgroundImg);
            add(backgroundMenu);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.title();
    }

    private void startButton(){
        BufferedImage buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File("src/Res/play_button.png"));
            int newWidth = 180; // Coloco a largura que eu quero da imagem no botão
            int newHeight = 85;  //Coloco altura
            Image scaledImage = buttonIcon.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //Modifico a escala do botão
            ImageIcon startIcon = new ImageIcon(scaledImage); //Transformo em Icon
            JButton startButton = new JButton(startIcon); // Inicio o botão com o Icon
            startButton.setPreferredSize(new Dimension(200, 100)); //Tamanho do botão
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setContentAreaFilled(false);
            add(startButton, BorderLayout.SOUTH);

            add(startButton);

            startButton.addActionListener(e -> {
                this.sceneManager.showGameplay();
                SoundHandler.StopMusic();
                SoundHandler.RunMusic("src/Res/EndOfLine.wav");
            });

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public static class SoundHandler{
        private static Clip clip;
        public static void RunMusic(String path){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        public static void StopMusic(){
            clip.stop();
        }
    }

    private void title(){
/*        JLabel titleLabel = new JLabel("Tron");
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(titleLabel, BorderLayout.EAST);
*/    }

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
