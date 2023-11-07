package Main;

import Main.Game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class GameObject extends Point implements Collidable, Renderable {

    private String spriteName;

    public GameObject(int x, int y){
        super(x, y);
        setX(x);
        setY(y);
    }

    public void setPositionInGrid(int x, int y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(i >= Game.gridWidth || j >= Game.gridHeight || i < 0 || j < 0){ // Se o objeto estiver fora da grid
            return;
        }

        Game.grid[i][j] = this; // Define a posição do objeto na grid
    }

    public void removePositionInGrid(int x, int y){
        int i = x / Game.cellSize;
        int j = y / Game.cellSize;

        if(i >= Game.gridWidth || j >= Game.gridHeight || i < 0 || j < 0){ // Se o objeto estiver fora da grid
            return;
        }

        Game.grid[i][j] = null; // Remove a posição do objeto na grid
    }

    public void setSprite(String spriteName){
        this.spriteName = spriteName; // Define o nome do sprite
    }

    public BufferedImage getSprite(String spriteName){
        return Game.imageCache.get(spriteName);
    } // Retorna o sprite do cache

    public String getSpriteName(){
        return spriteName;
    }

    public void render(Graphics g){ // Metodo default para render
        int x = this.getX();
        int y = this.getY();
        BufferedImage sprite = this.getSprite(this.getSpriteName());
        g.drawImage(sprite, x, y, null);
    }
}
