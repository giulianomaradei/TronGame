package Main.Game;

import Main.Game.Panels.SelectCharScene;
import Main.Game.Scenes.Concrete.GameFrame;
import Main.Game.Scenes.Concrete.GameScene;
import Main.Game.Scenes.Concrete.MenuScene;
import Main.Game.Scenes.Contracts.Scene;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SceneManager {
    private GameFrame gameFrame;
    private Scene currentScene;
    private static SelectCharScene selectionScene;

    public SceneManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void showMenu() {
        changeScene(new MenuScene(this));
    }

    public void showSelectionScene(){changeScene(selectionScene = new Main.Game.Panels.SelectCharScene(this, gameFrame));}

    public void showGameplay() {
        changeScene(new GameScene(this, gameFrame));
    }

    public void showEndScreen() {
        // Implemente a lógica para alternar para a tela final de maneira semelhante.
    }

    public static SelectCharScene getSelectionScene() {
        return selectionScene;
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
        private static Clip music;
        private static Clip sound;
        private static Clip timerSound;
        private static Clip chooseSound;
        public static void RunMusic(String path){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
                music = AudioSystem.getClip();
                music.open(audio);
                music.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public static void RunSound(String path){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
                sound = AudioSystem.getClip();
                sound.open(audio);
                sound.start();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public static void RunTimerSound(){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File("resources/Sounds/timerSoundFx.wav"));
                timerSound = AudioSystem.getClip();
                timerSound.open(audio);
                timerSound.start();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public static void RunChooseSound(){
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File("resources/Sounds/chooseSoundFx.wav"));
                chooseSound = AudioSystem.getClip();
                chooseSound.open(audio);
                chooseSound.start();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e){
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        public static void StopMusic(){
            music.stop();
        }
    }
}