package Main.Game.Panels;

import Main.Game.GameFrame;
import Main.Game.SceneManager;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobKOctets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Color.black;
import static java.lang.Math.abs;

public class SelectCharScene extends Scene {

    private GameFrame gameFrame;
    private int player1Id = 1;
    private int player2Id = 1;
    boolean player1Done = false;
    boolean player2Done = false;
    private int quantMaxCharacters = 3;
    private int counter = 0;
    private boolean gameStarted = false;
    private BufferedImage selectBImagesP1;
    private BufferedImage selectBImagesP2;
    private JLabel counterText = new JLabel();
    private JLabel controlsText = new JLabel();
    private JLabel selectP1 = new JLabel();
    private JLabel selectP2 = new JLabel();
    private BufferedImage safiraTitImg;
    private BufferedImage rubyTitImg;
    private BufferedImage esmeraldaTitImg;
    private BufferedImage esmeraldaBImg;
    private BufferedImage safiraBImg;
    private BufferedImage rubyBImg;
    private BufferedImage selectBImageP1Selected;
    private BufferedImage selectBImageP2Selected;
    private BufferedImage playButtonColored;
    private BufferedImage playButtonNotColored;
    private JButton playButton = new JButton();

    public SelectCharScene(SceneManager sceneManager, GameFrame gameFrame) {
        super(sceneManager);
        this.gameFrame = gameFrame;
        setBackground(black);
        setLayout(null);
        initSources();
        characterImages();
        descriptionCharacters();
        selectImages();
        setInputListeners();
        checkIfPlayerIsReady();
    }

    private void initSources(){
        try {
            selectBImagesP1 = ImageIO.read(new File("resources/GUI/player1.png"));
            selectBImagesP2 = ImageIO.read(new File("resources/GUI/player2.png"));
            safiraTitImg = ImageIO.read(new File("resources/GUI/SafiraaTit.png"));
            rubyTitImg = ImageIO.read(new File("resources/GUI/RubyTit.png"));
            esmeraldaTitImg = ImageIO.read(new File("resources/GUI/EsmeraldaTit.png"));
            safiraBImg = ImageIO.read(new File("resources/GUI/Moto2.png"));
            rubyBImg = ImageIO.read(new File("resources/GUI/Moto1.png"));
            esmeraldaBImg = ImageIO.read(new File("resources/GUI/Moto3.png"));
            selectBImageP1Selected = ImageIO.read(new File("resources/GUI/player1Select.png"));
            selectBImageP2Selected = ImageIO.read(new File("resources/GUI/player2Select.png"));
            playButtonNotColored = ImageIO.read(new File("resources/GUI/playButton_notColored.png"));
            playButtonColored = ImageIO.read(new File("src/Res/play_button.png"));

            Font fontePersonalizada = Font.createFont(Font.TRUETYPE_FONT, new File("resources/menuFont.ttf"));
            fontePersonalizada = fontePersonalizada.deriveFont(24f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontePersonalizada);

            counterText.setForeground(Color.ORANGE);
            counterText.setFont(fontePersonalizada.deriveFont(Font.PLAIN, 40));
            counterText.setLocation(400,400);
            counterText.setSize(300, 300);

            controlsText.setForeground(Color.white);
            controlsText.setFont(fontePersonalizada.deriveFont(Font.PLAIN, 16));
            controlsText.setLocation(500, 400);
            controlsText.setSize(300, 300);

            controlsText.setText("<html>WASD - movimento player 1<br><br>" +
                    "Up/Down/Left/Right - movimento player 2<html>");

            this.add(controlsText);
            this.add(counterText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void characterImages() {
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
    }

    private void descriptionCharacters() {
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

    }

    private void selectImages() {
        Image selectImgScale1 = selectBImagesP1.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        Image selectImgScale2 = selectBImagesP2.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon selectImgP1 = new ImageIcon(selectImgScale1);
        ImageIcon selectImgP2 = new ImageIcon(selectImgScale2);
        selectP1.setIcon(selectImgP1);
        selectP2.setIcon(selectImgP2);

        add(selectP1);
        add(selectP2);

        Insets insets = this.getInsets();
        Dimension size = selectP1.getPreferredSize();
        selectP1.setBounds(150 + insets.left, 55 + insets.top,
                size.width, size.height);
        selectP2.setBounds(150 + insets.left, 115 + insets.top,
                size.width, size.height);
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    private void startCounter(int time){
        counter += time;
        counterText.setText(Integer.toString(abs(counter - 4000)/ 1000));
        SceneManager.SoundHandler.RunTimerSound();

        if(counter > 3000){
            this.sceneManager.showGameplay();
            gameStarted = true;
        }
    }
    private void checkIfPlayerIsReady(){
        int delay = 0;
        int interval = 1000;
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!gameStarted) {
                    if (player1Done && player2Done) {
                        counterText.setVisible(true);
                        startCounter(interval);
                    } else {
                        counter = 0;
                        counterText.setVisible(false);
                    }
                }
            }
        }, delay, interval);
    };

    private void setInputListeners() {
        Insets insets = this.getInsets();
        Dimension size = selectP1.getPreferredSize();
        gameFrame.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (!gameStarted) {
                switch (evt.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP:
                        if (!player1Done) {
                            SceneManager.SoundHandler.RunChooseSound();
                            if (player1Id > 1) player1Id--;
                            if (player1Id == 1) selectP1.setBounds(150 + insets.left, 55 + insets.top,
                                    size.width, size.height);
                            else if (player1Id == 2) selectP1.setBounds(150 + insets.left, 235 + insets.top,
                                    size.width, size.height);
                        }
                        break;

                    case java.awt.event.KeyEvent.VK_DOWN:
                        if (!player1Done) {
                            SceneManager.SoundHandler.RunChooseSound();
                            if (player1Id < quantMaxCharacters) player1Id++;
                            if (player1Id == 2) selectP1.setBounds(150 + insets.left, 235 + insets.top,
                                    size.width, size.height);
                            else if (player1Id == 3) selectP1.setBounds(150 + insets.left, 420 + insets.top,
                                    size.width, size.height);
                        }
                        break;

                    case java.awt.event.KeyEvent.VK_W:
                        if (!player2Done) {
                            SceneManager.SoundHandler.RunChooseSound();
                            if (player2Id > 1) player2Id--;
                            if (player2Id == 1) selectP2.setBounds(150 + insets.left, 115 + insets.top,
                                    size.width, size.height);
                            else if (player2Id == 2) selectP2.setBounds(150 + insets.left, 295 + insets.top,
                                    size.width, size.height);
                        }
                        break;

                    case KeyEvent.VK_S:
                        if (!player2Done) {
                            SceneManager.SoundHandler.RunChooseSound();
                            if (player2Id < quantMaxCharacters) player2Id++;
                            if (player2Id == 2) selectP2.setBounds(150 + insets.left, 295 + insets.top,
                                    size.width, size.height);
                            else if (player2Id == 3) selectP2.setBounds(150 + insets.left, 480 + insets.top,
                                    size.width, size.height);
                        }
                        break;

                    case KeyEvent.VK_ENTER:
                        SceneManager.SoundHandler.RunSound("resources/selectSoundFx.wav");
                        if (!player1Done) {
                            player1Done = true;
                            Image selectImgScale1 = selectBImageP1Selected.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                            ImageIcon selectImgP1 = new ImageIcon(selectImgScale1);
                            selectP1.setIcon(selectImgP1);
                        } else {
                            player1Done = false;
                            Image selectImgScale1 = selectBImagesP1.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                            ImageIcon selectImgP1 = new ImageIcon(selectImgScale1);
                            selectP1.setIcon(selectImgP1);
                        }
                        break;

                    case KeyEvent.VK_SPACE:
                        SceneManager.SoundHandler.RunSound("resources/selectSoundFx.wav");
                        if (!player2Done) {
                            player2Done = true;
                            Image selectImgScale2 = selectBImageP2Selected.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                            ImageIcon selectImgP2 = new ImageIcon(selectImgScale2);
                            selectP2.setIcon(selectImgP2);
                        } else {
                            player2Done = false;
                            Image selectImgScale2 = selectBImagesP2.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                            ImageIcon selectImgP2 = new ImageIcon(selectImgScale2);
                            selectP2.setIcon(selectImgP2);
                        }
                        break;
                }
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
