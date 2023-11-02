package Main.Game;

import Main.Game.Panels.GameScene;
import Main.Game.Panels.MenuScene;
import Main.Game.Panels.Scene;
import Main.Game.Panels.SelectCharScene;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SceneManager {
    private GameFrame gameFrame;
    private Scene currentScene;

    public SceneManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void showMenu() {
        changeScene(new MenuScene(this));
    }

    public void showSelectionScene(){changeScene(new SelectCharScene(this));}

    public void showGameplay() {
        changeScene(new GameScene(this, gameFrame));
    }

    public void showEndScreen() {
        // Implemente a lógica para alternar para a tela final de maneira semelhante.
    }

    private void changeScene(Scene scene){
        if(currentScene != null){
            gameFrame.remove(currentScene);
        }
        currentScene = scene;
        gameFrame.add(currentScene);
        gameFrame.pack();
        gameFrame.revalidate();
        gameFrame.repaint();
        gameFrame.requestFocus();
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

}