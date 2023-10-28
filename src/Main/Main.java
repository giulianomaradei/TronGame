package Main;

import Main.Game.Game;

public class Main {

    Game game;
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        Game game = new Game();
    }
}